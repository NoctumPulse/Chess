package Server.Results;

/**
 * Class that represents the object conversion of the server Register Service JSON result.
 */
public class RegisterResult {
    public int resultCode;
    public String message;
    /**
     * Field representing the username of the user that was added.
     */
    public String username;
    /**
     * Field representing the authToken of the user that was added.
     */
    public String authToken;

    public RegisterResult(int error, String errorMessage) {
        resultCode = error;
        message = errorMessage;
        username = null;
        authToken = null;
    }

    public RegisterResult(String user, String token) {
        resultCode = 200;
        message = null;
        username = user;
        authToken = token;
    }
}
