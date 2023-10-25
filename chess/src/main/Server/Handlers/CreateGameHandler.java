package Server.Handlers;

/**
 * Handler Class for the Create Game Web API of the server.
 */
public class CreateGameHandler {
    /**
     * CreateGameService Class Object for the CreateGameHandler Class that processes the request of the handler.
     */
    CreateGameService service = null;

    /**
     * Default Constructor for the CreateGameHandler Class.
     * TO DO - understand what the handler needs
     */
    CreateGameHandler() {

    }

    /**
     * Static Private Inner Class of the CreateGameHandler class that represents the object conversion
     * of the server JSON request.
     */
    static private class CreateGameRequest {
        /**
         * Field representing the requested name of the Game to be created in the server data.
         */
        public String gameName;
    }

    /**
     * Static Private Inner Class of the CreateGameHandler class that represents the result of the CreateGameService Class.
     */
    static private class CreateGameResult {
        /**
         * Field representing the ID of the Game that was created
         */
        public String gameID;
    }

    /**
     * Static Private Inner Class of the CreateGameHandler class that acts as the intermediary
     * between the CreateGameHandler and the Data Access Objects (DAOs).
     */
    static private class CreateGameService {
        /**
         * CreateGameResult object to hold the results of the CreateGameService operation.
         */
        public CreateGameResult serviceResult;

        /**
         * Default Constructor of the CreateGameService class that executes its operation and stores its result
         * in serviceResult;
         *
         * @param serverRequest The CreateGameRequest object made from the JSON server request
         */
        public CreateGameService(CreateGameRequest serverRequest) {
            serviceResult = processRequest(serverRequest);
        }

        /**
         * Method that creates Data Access Objects needed to process the request and runs their commands.
         *
         * @return Returns a CreateGameResult object with the game ID of the created game.
         */
        private CreateGameResult processRequest(CreateGameRequest serverRequest) {
            CreateGameResult result = new CreateGameResult();
            return result;
        }
    }
}
