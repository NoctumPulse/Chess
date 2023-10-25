package Server.Handlers;

/**
 * Handler Class for the Register Web API of the server.
 */
public class RegisterHandler {
    /**
     * RegisterService Class Object for the RegisterHandler Class that processes the request of the handler.
     */
    RegisterService service;

    /**
     * Default Constructor for the RegisterHandler Class.
     * TO DO - understand what the handler needs
     */
    RegisterHandler() {

    }

    /**
     * Static Private Inner Class of the RegisterHandler class that represents the object conversion
     * of the server JSON request.
     */
    static private class RegisterRequest {
        /**
         * Field representing the username of the user being added.
         */
        public String username;
        /**
         * Field representing the password of the user being added.
         */
        public String password;
        /**
         * Field representing the email of the user being added.
         */
        public String email;
    }

    /**
     * Static Private Inner Class of the RegisterHandler class that represents the result of the RegisterService Class.
     */
    static private class RegisterResult {
        /**
         * Field representing the username of the user that was added.
         */
        public String username;
        /**
         * Field representing the authToken of the user that was added.
         */
        public String authToken;
    }

    /**
     * Static Private Inner Class of the RegisterHandler class that acts as the intermediary
     * between the RegisterHandler and the Data Access Objects (DAOs).
     */
    static private class RegisterService {
        /**
         * RegisterResult object to hold the results of the RegisterService operation.
         */
        public RegisterResult serviceResult;

        /**
         * Default Constructor of the RegisterService class that executes its operation and stores its result
         * in serviceResult;
         *
         * @param serverRequest The RegisterRequest object made from the JSON server request
         */
        public RegisterService(RegisterRequest serverRequest) {
            serviceResult = processRequest(serverRequest);
        }

        /**
         * Method that creates Data Access Objects needed to process the request and runs their commands.
         *
         * @return Returns a RegisterResult object with the username and authToken of the user that was just added.
         */
        private RegisterResult processRequest(RegisterRequest serverRequest) {
            RegisterResult result = new RegisterResult();
            return result;
        }
    }
}
