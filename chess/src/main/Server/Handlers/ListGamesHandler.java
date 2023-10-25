package Server.Handlers;

import java.util.TreeSet;

import Server.Models.Game;

/**
 * Handler Class for the List Games Web API of the server.
 */
public class ListGamesHandler {
    /**
     * ListGamesService Class Object for the ListGamesHandler Class that processes the request of the handler.
     */
    ListGamesService service = null;

    /**
     * Default Constructor for the ListGamesHandler Class.
     * TO DO - understand what the handler needs
     */
    ListGamesHandler() {

    }

    /**
     * Static Private Inner Class of the ListGamesHandler class that represents the object conversion
     * of the server JSON request.
     */
    static private class ListGamesRequest {
        /**
         * Field representing the authToken of the user asking for the list of games.
         */
        public String authorization;
    }

    /**
     * Static Private Inner Class of the ListGamesHandler class that represents the result of the ListGamesService Class.
     */
    static private class ListGamesResult {
        /**
         * Field representing set of Games available.
         */
        public TreeSet<Game> games;
    }

    /**
     * Static Private Inner Class of the ListGamesHandler class that acts as the intermediary
     * between the ListGamesHandler and the Data Access Objects (DAOs).
     */
    static private class ListGamesService {
        /**
         * ListGamesResult object to hold the results of the JoinGameService operation.
         */
        public ListGamesResult serviceResult;

        /**
         * Default Constructor of the ListGamesService class that executes its operation and stores its result
         * in serviceResult;
         *
         * @param serverRequest The ListGamesRequest object made from the JSON server request
         */
        public ListGamesService(ListGamesRequest serverRequest) {
            serviceResult = processRequest(serverRequest);
        }

        /**
         * Method that creates Data Access Objects needed to process the request and runs their commands.
         *
         * @return Returns a ListGamesResult object with a set of all games available.
         */
        private ListGamesResult processRequest(ListGamesRequest serverRequest) {
            ListGamesResult result = new ListGamesResult();
            return result;
        }
    }

}
