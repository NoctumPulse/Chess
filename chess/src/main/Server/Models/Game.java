package Server.Models;

import Chess.Implementations.ChessGameImpl;

import java.util.Objects;

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

    public Game(int id, String whiteName, String blackName, String name) {
        gameID = id;
        whiteUsername = whiteName;
        blackUsername = blackName;
        gameName = name;
        game = new ChessGameImpl();
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameID, whiteUsername, blackUsername, gameName);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof Game)) {
            return false;
        } else if (obj == this) {
            return true;
        } else {
            Game newGame = (Game) obj;
            if (!Objects.equals(this.gameName, newGame.gameName)) {
                return false;
            } else if (!Objects.equals(this.gameID, newGame.gameID)) {
                return false;
            } else //for a really deep comparison we should compare ChessGameImpl but its unlikely that this comes up as an issue
                if (!Objects.equals(this.whiteUsername, newGame.whiteUsername)) {
                    return false;
                } else return Objects.equals(this.blackUsername, newGame.blackUsername);
        }
    }
}
