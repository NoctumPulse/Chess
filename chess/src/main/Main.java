import ChessPieces.*;
import Implementations.ChessBoardImpl;
import Implementations.ChessGameImpl;
import Implementations.ChessPositionImpl;
import chess.ChessGame;
import chess.InvalidMoveException;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, InvalidMoveException {
        ChessBoardImpl board = new ChessBoardImpl();
        ChessGameImpl game = new ChessGameImpl();
        board.addPiece(new ChessPositionImpl(7,3), new PawnImpl(ChessGame.TeamColor.WHITE));
        board.addPiece(new ChessPositionImpl(2, 5), new PawnImpl(ChessGame.TeamColor.BLACK));
        board.addPiece(new ChessPositionImpl(1,6), new QueenImpl(ChessGame.TeamColor.WHITE));
        game.setBoard(board);
        game.setTeamTurn(ChessGame.TeamColor.WHITE);
    }
}
