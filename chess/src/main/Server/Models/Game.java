package Server.Models;

import Chess.Implementations.ChessGameImpl;

/**
 * Class that represents a chess game.
 */
public class Game {
    /**
     * field that contains the ID number of the game.
     */
    public int gameID;
    /**
     * field that contains the username of the white player.
     */
    public String whiteUsername;
    /**
     * field that contains the username of the black player.
     */
    public String blackUsername;
    /**
     * field that contains the display name of the game.
     */
    public String gameName;
    /**
     * Actual chess game board object of the game.
     */
    public ChessGameImpl game;
}
