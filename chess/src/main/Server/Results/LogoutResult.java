package Server.Results;

/**
 * Class that represents the object conversion of the server Logout Service JSON result.
 */
public class LogoutResult {
    public int resultCode;
    /**
     * Field that represents the message of any error that occurs or is empty if none occur.
     */
    public String message;

    public LogoutResult() {
        resultCode = 200;
        message = null;
    }

    public LogoutResult(int code, String errorMessage) {
        resultCode = code;
        message = errorMessage;
    }
}
