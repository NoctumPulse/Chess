package Server.Requests;

import chess.ChessGame;

/**
 * Class that represents the object conversion of the server Join Game JSON request.
 */
public class JoinGameRequest {
    /**
     * Field representing the color of the player joining the Game.
     */
    public ChessGame.TeamColor playerColor;
    /**
     * Field representing the ID of the Game that is being joined.
     */
    public int gameID;
}
