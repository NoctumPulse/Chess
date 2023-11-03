package Server.Services;

import Server.DAOs.LocalAuthDAO;
import Server.DAOs.LocalUserDAO;
import Server.Models.AuthToken;
import Server.Models.User;
import Server.Requests.RegisterRequest;
import Server.Results.RegisterResult;
import dataAccess.DataAccessException;

import java.util.UUID;

/**
 * Class that acts as the intermediary
 * between the RegisterHandler and the Data Access Objects (DAOs).
 */
public class RegisterService {
    Object dataStorage;

    public RegisterService(Object storage) {
        dataStorage = storage;
    }

    /**
     * Method that creates Data Access Objects needed to process the request and runs their commands.
     *
     * @return Returns a RegisterResult object with the username and authToken of the user that was just added.
     */
    public RegisterResult register(RegisterRequest request) {
        try {
            if (request.username == null || request.password == null || request.email == null) {
                throw new DataAccessException("Error: bad request");
            }
            LocalUserDAO localUserDAO = new LocalUserDAO(dataStorage);
            User testUser = (User) localUserDAO.find(request.username);
            if (testUser != null) {
                throw new DataAccessException("Error: already taken");
            } else {
                User newUser = new User(request.username, request.password, request.email);
                String authToken = UUID.randomUUID().toString();
                localUserDAO.insert(newUser);
                LocalAuthDAO localAuthDAO = new LocalAuthDAO(dataStorage);
                localAuthDAO.insert(new AuthToken(authToken, request.username));
                return new RegisterResult(request.username, authToken);
            }
        } catch (DataAccessException exception) {
            return switch (exception.getMessage()) {
                case "Error: bad request" -> new RegisterResult(400, "Error: bad request");
                case "Error: already taken" -> new RegisterResult(403, "Error: already taken");
                default -> new RegisterResult(500, exception.getMessage());
            };
        }
    }
}
