package Server.Results;

/**
 * Class that represents the object conversion of the server Login Service JSON result.
 */
public class LoginResult {
    public int resultCode;
    public String message;
    /**
     * Field representing the password of the user who logged in.
     */
    public String username;
    /**
     * Field representing the authToken of the user who logged in.
     */
    public String authToken;

    public LoginResult(int code, String errorMessage) {
        resultCode = code;
        message = errorMessage;
        username = null;
        authToken = null;
    }

    public LoginResult(String name, String token) {
        resultCode = 200;
        message = null;
        username = name;
        authToken = token;
    }
}
