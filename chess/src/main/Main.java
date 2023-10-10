import ChessPieces.*;
import Implementations.ChessBoardImpl;
import Implementations.ChessGameImpl;
import Implementations.ChessMoveImpl;
import Implementations.ChessPositionImpl;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;
import chess.InvalidMoveException;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException, InvalidMoveException {
        ChessBoardImpl board = new ChessBoardImpl();
        ChessGameImpl game = new ChessGameImpl();
        ChessPositionImpl kingStartPosition = new ChessPositionImpl(1,2);
        board.addPiece(kingStartPosition, new KingImpl(ChessGame.TeamColor.WHITE));
        ChessPositionImpl queenStartPosition = new ChessPositionImpl(7,8);
        board.addPiece(queenStartPosition, new QueenImpl(ChessGame.TeamColor.BLACK));
        ChessPositionImpl rookStartPosition = new ChessPositionImpl(1,8);
        board.addPiece(rookStartPosition, new RookImpl(ChessGame.TeamColor.WHITE));
        ChessPositionImpl knightStartPosition = new ChessPositionImpl(6,3);
        board.addPiece(knightStartPosition, new KnightImpl(ChessGame.TeamColor.BLACK));
        ChessPositionImpl bishopStartPosition = new ChessPositionImpl(2,3);
        board.addPiece(bishopStartPosition, new BishopImpl(ChessGame.TeamColor.WHITE));
        ChessPositionImpl pawnStartPosition = new ChessPositionImpl(6,7);
        board.addPiece(pawnStartPosition, new PawnImpl(ChessGame.TeamColor.BLACK));
        game.setBoard(board);
        game.setTeamTurn(ChessGame.TeamColor.WHITE);
        ChessPositionImpl kingEndPosition = new ChessPositionImpl(1,1);
        game.makeMove(new ChessMoveImpl(kingStartPosition, kingEndPosition, null));
        ChessPositionImpl queenEndPosition = new ChessPositionImpl(8,7);
        game.makeMove(new ChessMoveImpl(queenStartPosition, queenEndPosition, null));
        ChessPositionImpl rookEndPosition = new ChessPositionImpl(3,8);
        game.makeMove(new ChessMoveImpl(rookStartPosition, rookEndPosition, null));
        ChessPositionImpl knightEndPosition = new ChessPositionImpl(7,5);
        game.makeMove(new ChessMoveImpl(knightStartPosition,knightEndPosition, null));
        ChessPositionImpl bishopEndPosition = new ChessPositionImpl(1,2);
        game.makeMove(new ChessMoveImpl(bishopStartPosition,bishopEndPosition,null));
        ChessPositionImpl pawnEndPosition = new ChessPositionImpl(5,7);
        game.makeMove(new ChessMoveImpl(pawnStartPosition, pawnEndPosition, null));
    }
}
