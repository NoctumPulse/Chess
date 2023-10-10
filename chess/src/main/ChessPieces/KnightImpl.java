package ChessPieces;

import Implementations.ChessMoveImpl;
import Implementations.ChessPositionImpl;
import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.Collection;
import java.util.HashSet;

public class KnightImpl extends ChessPieceImpl {
    public KnightImpl(ChessGame.TeamColor team) {
        teamColor = team;
        pieceType = PieceType.KNIGHT;
    }

    public KnightImpl(KnightImpl knightToCopy) {
        this.teamColor = knightToCopy.teamColor;
        this.pieceType = knightToCopy.pieceType;
    }

    @Override
    Collection<ChessMove> moveHelper(ChessBoard chessBoard, ChessPosition myPosition) {
        HashSet<ChessMove> knightMoves = new HashSet<>();
        int[][] possiblePositions = {{1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}};
        for (int i = 0; i < 8; i++) {
            ChessPosition endPosition = new ChessPositionImpl((myPosition.getRow() + possiblePositions[i][0]), (myPosition.getColumn() + possiblePositions[i][1]));
            if (endPosition.getRow() >= 1 && endPosition.getRow() <= 8) {
                if (endPosition.getColumn() >= 1 && endPosition.getColumn() <= 8) {
                    if (chessBoard.getPiece(endPosition) != null) {
                        if (chessBoard.getPiece(endPosition).getTeamColor() != chessBoard.getPiece(myPosition).getTeamColor()) {
                            knightMoves.add(new ChessMoveImpl(myPosition, endPosition, null));
                        }
                    } else {
                        knightMoves.add(new ChessMoveImpl(myPosition, endPosition, null));
                    }
                }
            }
        }
        return knightMoves;
    }
}
