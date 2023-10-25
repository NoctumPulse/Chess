package Server.Handlers;

/**
 * Handler Class for the Login Web API of the server.
 */
public class LoginHandler {
    /**
     * LoginService Class Object for the LoginHandler Class that processes the request of the handler.
     */
    LoginService service;

    /**
     * Default Constructor for the LoginHandler Class.
     * TO DO - understand what the handler needs
     */
    LoginHandler() {

    }

    /**
     * Static Private Inner Class of the LoginHandler class that represents the object conversion
     * of the server JSON request.
     */
    static private class LoginRequest {
        /**
         * Field representing the username of the user logging in.
         */
        public String username;
        /**
         * Field representing the password of the user logging in.
         */
        public String password;
    }

    /**
     * Static Private Inner Class of the LoginHandler class that represents the result of the LoginService Class.
     */
    static private class LoginResult {
        /**
         * Field representing the password of the user who logged in.
         */
        public String username;
        /**
         * Field representing the authToken of the user who logged in.
         */
        public String authToken;
    }

    /**
     * Static Private Inner Class of the LoginHandler class that acts as the intermediary
     * between the LoginHandler and the Data Access Objects (DAOs).
     */
    static private class LoginService {
        /**
         * LoginResult object to hold the results of the LoginService operation.
         */
        public LoginResult serviceResult;

        /**
         * Default Constructor of the LoginService class that executes its operation and stores its result
         * in serviceResult;
         *
         * @param serverRequest The LoginRequest object made from the JSON server request
         */
        public LoginService(LoginRequest serverRequest) {
            serviceResult = processRequest(serverRequest);
        }

        /**
         * Method that creates Data Access Objects needed to process the request and runs their commands.
         *
         * @return Returns a LoginResult object with username and authToken of the user who was logged in.
         */
        private LoginResult processRequest(LoginRequest serverRequest) {
            LoginResult result = new LoginResult();
            return result;
        }
    }
}
