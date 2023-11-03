package Server.Results;

/**
 * Class that represents the object conversion of the server Create Game Service JSON result.
 */
public class CreateGameResult {
    public int resultCode;
    public String message;
    /**
     * Field representing the ID of the Game that was created
     */
    public int gameID;

    public CreateGameResult(int ID) {
        resultCode = 200;
        message = null;
        gameID = ID;
    }

    public CreateGameResult(int code, String errorMessage) {
        resultCode = code;
        message = errorMessage;
        gameID = -1;
    }
}
