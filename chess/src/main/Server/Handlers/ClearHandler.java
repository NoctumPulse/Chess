package Server.Handlers;

/**
 * Handler Class for the Clear Web API of the server.
 */
public class ClearHandler {
    /**
     * ClearService Class Object for the ClearHandler Class that processes the request of the handler.
     */
    ClearService service;

    /**
     * Default Constructor for the ClearHandler Class.
     * TO DO - understand what the handler needs
     */
    ClearHandler() {

    }

    static private class ClearResult {
        /**
         * Field that represents the message of any error that occurs or is empty if none occur.
         */
        public String message;
    }

    /**
     * Static Private Inner Class of the ClearHandler class that acts as the intermediary
     * between the ClearHandler and the Data Access Objects (DAOs).
     */
    static private class ClearService {
        /**
         * ClearResult object to hold the results of the ClearService operation.
         */
        ClearResult clearResult = null;

        /**
         * Default Constructor for the ClearService class. Since there is no HTTP body there is no parameter to pass in.
         * It sets clearResult equal to the processRequest function return value.
         */
        public ClearService() {
            clearResult = processRequest();
        }

        /**
         * Method that creates Data Access Objects for each data type in the Database and runs their clear commands.
         *
         * @return Returns a ClearResult object with its message set to any error encountered or empty if no error occurred
         */
        private ClearResult processRequest() {
            ClearResult result = new ClearResult();
            return result;
        }
    }

}
