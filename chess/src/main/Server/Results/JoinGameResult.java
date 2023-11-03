package Server.Results;

/**
 * Class that represents the object conversion of the server Join Game Service JSON result.
 */
public class JoinGameResult {
    public int resultCode;
    public String message;

    public JoinGameResult() {
        resultCode = 200;
        message = null;
    }

    public JoinGameResult(int code, String errorMessage) {
        resultCode = code;
        message = errorMessage;
    }
}
