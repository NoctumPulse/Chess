package Server.Results;

import Server.Models.Game;

import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents the object conversion of the server List Games Service JSON result.
 */
public class ListGamesResult {
    public int resultCode;
    public String message;
    /**
     * Field representing set of Games available.
     */
    public HashSet<Game> games;

    public ListGamesResult(Set<Object> serverGames) {
        resultCode = 200;
        message = null;
        games = new HashSet<>();
        for (Object game : serverGames) {
            Game castGame = (Game) game;
            games.add(castGame);
        }
    }

    public ListGamesResult(int code, String errorMessage) {
        resultCode = code;
        message = errorMessage;
        games = null;
    }
}
