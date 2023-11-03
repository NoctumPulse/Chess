package Server.Services;

import Server.DAOs.LocalAuthDAO;
import Server.DAOs.LocalGameDAO;
import Server.Models.AuthToken;
import Server.Models.Game;
import Server.Requests.CreateGameRequest;
import Server.Results.CreateGameResult;
import dataAccess.DataAccessException;

/**
 * Class that acts as the intermediary
 * between the CreateGameHandler and the Data Access Objects (DAOs).
 */
public class CreateGameService {
    Object dataStorage;

    public CreateGameService(Object storage) {
        dataStorage = storage;
    }

    public CreateGameResult createGame(CreateGameRequest serverRequest, String authToken) {
        try {
            LocalAuthDAO localAuthDAO = new LocalAuthDAO(dataStorage);
            AuthToken token = (AuthToken) localAuthDAO.find(authToken);
            if (token == null) {
                throw new DataAccessException("Error: unauthorized");
            }
            if (serverRequest.gameName == null) {
                throw new DataAccessException("Error: bad request");
            }
            LocalGameDAO localGameDAO = new LocalGameDAO(dataStorage);
            Game newGame = new Game(localGameDAO.getNextGameID(), null, null, serverRequest.gameName);
            localGameDAO.insert(newGame);
            return new CreateGameResult(newGame.gameID);
        } catch (DataAccessException exception) {
            return switch (exception.getMessage()) {
                case "Error: bad request" -> new CreateGameResult(400, "Error: bad request");
                case "Error: unauthorized" -> new CreateGameResult(401, "Error: unauthorized");
                default -> new CreateGameResult(500, exception.getMessage());
            };
        }
    }
}
