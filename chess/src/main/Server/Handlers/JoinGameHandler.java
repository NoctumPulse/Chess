package Server.Handlers;

/**
 * Handler Class for the Join Game Web API of the server.
 */
public class JoinGameHandler {
    /**
     * JoinGameService Class Object for the JoinGameHandler Class that processes the request of the handler.
     */
    JoinGameService service = null;

    /**
     * Default Constructor for the JoinGameHandler Class.
     * TO DO - understand what the handler needs
     */
    JoinGameHandler() {

    }

    /**
     * Static Private Inner Class of the JoinGameHandler class that represents the object conversion
     * of the server JSON request.
     */
    static private class JoinGameRequest {
        /**
         * Field representing the color of the player joining the Game.
         */
        public String playerColor;
        /**
         * Field representing the ID of the Game that is being joined.
         */
        public String gameID;
    }

    /**
     * Static Private Inner Class of the JoinGameHandler class that represents the result of the JoinGameService Class.
     */
    static private class JoinGameResult {
        /**
         * Field representing the ID of the Game that was joined.
         */
        public String gameID;
    }

    /**
     * Static Private Inner Class of the JoinGameHandler class that acts as the intermediary
     * between the JoinGameHandler and the Data Access Objects (DAOs).
     */
    static private class JoinGameService {
        /**
         * JoinGameResult object to hold the results of the JoinGameService operation.
         */
        public JoinGameResult serviceResult;

        /**
         * Default Constructor of the JoinGameService class that executes its operation and stores its result
         * in serviceResult;
         *
         * @param serverRequest The JoinGameRequest object made from the JSON server request
         */
        public JoinGameService(JoinGameRequest serverRequest) {
            serviceResult = processRequest(serverRequest);
        }

        /**
         * Method that creates Data Access Objects needed to process the request and runs their commands.
         *
         * @return Returns a JoinGameResult object with the game ID of the joined game.
         */
        private JoinGameResult processRequest(JoinGameRequest serverRequest) {
            JoinGameResult result = new JoinGameResult();
            return result;
        }
    }
}
