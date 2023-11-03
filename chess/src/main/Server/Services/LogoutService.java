package Server.Services;

import Server.DAOs.LocalAuthDAO;
import Server.Models.AuthToken;
import Server.Requests.LogoutRequest;
import Server.Results.LogoutResult;
import dataAccess.DataAccessException;

/**
 * Class that acts as the intermediary
 * between the LogoutHandler and the Data Access Objects (DAOs).
 */
public class LogoutService {
    Object dataStorage;

    public LogoutService(Object storage) {
        dataStorage = storage;
    }

    /**
     * Method that creates Data Access Objects needed to process the request and runs their commands.
     *
     * @return Returns a LogoutResult object with the message of any errors that occurred or an empty string if none.
     */
    public LogoutResult logout(LogoutRequest serverRequest) {
        try {
            LocalAuthDAO localAuthDAO = new LocalAuthDAO(dataStorage);
            AuthToken token = (AuthToken) localAuthDAO.find(serverRequest.authorization);
            if (token == null) {
                throw new DataAccessException("Error: unauthorized");
            } else {
                AuthToken newToken = new AuthToken(null, token.username);
                localAuthDAO.update(token.username, newToken);
            }
            return new LogoutResult();
        } catch (DataAccessException exception) {
            return switch (exception.getMessage()) {
                case "Error: unauthorized" -> new LogoutResult(401, "Error: unauthorized");
                default -> new LogoutResult(500, exception.getMessage());
            };
        }
    }
}
