package Server.Handlers;

/**
 * Handler Class for the Logout Web API of the server.
 */
public class LogoutHandler {
    /**
     * LogoutService Class Object for the LogoutHandler Class that processes the request of the handler.
     */
    LogoutService service;

    /**
     * Default Constructor for the LogoutHandler Class.
     * TO DO - understand what the handler needs
     */
    LogoutHandler() {

    }

    /**
     * Static Private Inner Class of the LogoutHandler class that represents the object conversion
     * of the server JSON request.
     */
    static private class LogoutRequest {
        /**
         * Field representing the authToken of the user logging out.
         */
        public String authorization;
    }

    /**
     * Static Private Inner Class of the LogoutHandler class that represents the result of the LogoutService Class.
     */
    static private class LogoutResult {
        /**
         * Field that represents the message of any error that occurs or is empty if none occur.
         */
        public String message;
    }

    /**
     * Static Private Inner Class of the LogoutHandler class that acts as the intermediary
     * between the LogoutHandler and the Data Access Objects (DAOs).
     */
    static private class LogoutService {
        /**
         * LogoutResult object to hold the results of the LogoutService operation.
         */
        public LogoutResult serviceResult;

        /**
         * Default Constructor of the LogoutService class that executes its operation and stores its result
         * in serviceResult;
         *
         * @param serverRequest The LogoutRequest object made from the JSON server request
         */
        public LogoutService(LogoutRequest serverRequest) {
            serviceResult = processRequest(serverRequest);
        }

        /**
         * Method that creates Data Access Objects needed to process the request and runs their commands.
         *
         * @return Returns a LogoutResult object with the message of any errors that occurred or an empty string if none.
         */
        private LogoutResult processRequest(LogoutRequest serverRequest) {
            LogoutResult result = new LogoutResult();
            return result;
        }
    }
}
