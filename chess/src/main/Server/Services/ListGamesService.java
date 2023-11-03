package Server.Services;

import Server.DAOs.LocalAuthDAO;
import Server.DAOs.LocalGameDAO;
import Server.Models.AuthToken;
import Server.Requests.ListGamesRequest;
import Server.Results.ListGamesResult;
import dataAccess.DataAccessException;

import java.util.Set;

/**
 * Class that acts as the intermediary
 * between the ListGamesHandler and the Data Access Objects (DAOs).
 */
public class ListGamesService {
    Object dataStorage;

    public ListGamesService(Object storage) {
        dataStorage = storage;
    }

    /**
     * Method that creates Data Access Objects needed to process the request and runs their commands.
     *
     * @return Returns a ListGamesResult object with a set of all games available.
     */
    public ListGamesResult listGames(ListGamesRequest serverRequest, String authToken) {
        try {
            LocalAuthDAO localAuthDAO = new LocalAuthDAO(dataStorage);
            AuthToken token = (AuthToken) localAuthDAO.find(authToken);
            if (token == null) {
                throw new DataAccessException("Error: unauthorized");
            }
            LocalGameDAO localGameDAO = new LocalGameDAO(dataStorage);
            Set<Object> games = localGameDAO.getAll();
            return new ListGamesResult(games);
        } catch (DataAccessException exception) {
            return switch (exception.getMessage()) {
                case "Error: unauthorized" -> new ListGamesResult(401, "Error: unauthorized");
                default -> new ListGamesResult(500, exception.getMessage());
            };
        }
    }
}
