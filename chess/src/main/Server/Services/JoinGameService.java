package Server.Services;

import Server.DAOs.LocalAuthDAO;
import Server.DAOs.LocalGameDAO;
import Server.Models.AuthToken;
import Server.Models.Game;
import Server.Requests.JoinGameRequest;
import Server.Results.JoinGameResult;
import dataAccess.DataAccessException;

/**
 * Class that acts as the intermediary
 * between the JoinGameHandler and the Data Access Objects (DAOs).
 */
public class JoinGameService {
    Object dataStorage;

    public JoinGameService(Object storage) {
        dataStorage = storage;
    }

    /**
     * Method that creates Data Access Objects needed to process the request and runs their commands.
     *
     * @return Returns a JoinGameResult object
     */
    public JoinGameResult joinGame(JoinGameRequest serverRequest, String token) {
        try {
            LocalAuthDAO localAuthDAO = new LocalAuthDAO(dataStorage);
            AuthToken authToken = (AuthToken) localAuthDAO.find(token);
            if (authToken == null) {
                throw new DataAccessException("Error: unauthorized");
            }
            LocalGameDAO localGameDAO = new LocalGameDAO(dataStorage);
            Game currentGame = (Game) localGameDAO.find(serverRequest.gameID);
            if (currentGame == null) {
                throw new DataAccessException("Error: bad request");
            }
            if (serverRequest.playerColor != null) {
                switch (serverRequest.playerColor) {
                    case WHITE:
                        if (currentGame.whiteUsername != null) {
                            throw new DataAccessException("Error: already taken");
                        }
                        currentGame.whiteUsername = authToken.username;
                        break;
                    case BLACK:
                        if (currentGame.blackUsername != null) {
                            throw new DataAccessException("Error: already taken");
                        }
                        currentGame.blackUsername = authToken.username;
                        break;
                }
                localGameDAO.update(serverRequest.gameID, currentGame);
            }
            return new JoinGameResult();
        } catch (DataAccessException exception) {
            return switch (exception.getMessage()) {
                case "Error: bad request" -> new JoinGameResult(400, "Error: bad request");
                case "Error: unauthorized" -> new JoinGameResult(401, "Error: unauthorized");
                case "Error: already taken" -> new JoinGameResult(403, "Error: already taken");
                default -> new JoinGameResult(500, exception.getMessage());
            };
        }
    }
}
