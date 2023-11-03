package Server.Results;

/**
 * Class that represents the object conversion of the server Clear Service JSON result.
 */
public class ClearResult {
    /**
     * Data object representing the result code from the ClearService handleRequest function
     */
    public int resultCode;
    /**
     * Data object representing the error message if any from the ClearService handleRequest function
     */
    public String message;

    /**
     * Default constructor for the ClearResult class that represents the lack of an error;
     */
    public ClearResult() {
        resultCode = 200;
        message = null;
    }

    /**
     * Constructor for the ClearResult class that represents if an error was thrown during the ClearService handleRequest function;
     */
    public ClearResult(String errorMessage) {
        resultCode = 500;
        message = errorMessage;
    }
}
