package Server.Services;

import Server.DAOs.LocalAuthDAO;
import Server.DAOs.LocalUserDAO;
import Server.Models.AuthToken;
import Server.Models.User;
import Server.Requests.LoginRequest;
import Server.Results.LoginResult;
import dataAccess.DataAccessException;

import java.util.Objects;
import java.util.UUID;

/**
 * Class that acts as the intermediary
 * between the LoginHandler and the Data Access Objects (DAOs).
 */
public class LoginService {
    Object dataStorage;

    public LoginService(Object storage) {
        dataStorage = storage;
    }

    /**
     * Method that creates Data Access Objects needed to process the request and runs their commands.
     *
     * @return Returns a LoginResult object with username and authToken of the user who was logged in.
     */
    public LoginResult login(LoginRequest serverRequest) {
        try {
            LocalUserDAO localUserDAO = new LocalUserDAO(dataStorage);
            User currentUser = (User) localUserDAO.find(serverRequest.username);
            if (currentUser != null) {
                if (!Objects.equals(currentUser.password, serverRequest.password)) {
                    throw new DataAccessException("Error: unauthorized");
                } else {
                    String newAuthToken = UUID.randomUUID().toString();
                    LocalAuthDAO localAuthDAO = new LocalAuthDAO(dataStorage);
                    localAuthDAO.update(serverRequest.username, new AuthToken(newAuthToken, serverRequest.username));
                    return new LoginResult(serverRequest.username, newAuthToken);
                }
            } else {
                throw new DataAccessException("Error: unauthorized");
            }
        } catch (DataAccessException exception) {
            return switch (exception.getMessage()) {
                case "Error: unauthorized" -> new LoginResult(401, "Error: unauthorized");
                default -> new LoginResult(500, exception.getMessage());
            };
        }
    }
}
