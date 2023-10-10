package Implementations;

import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.Objects;

public class ChessMoveImpl implements ChessMove {
    ChessPosition startPosition;
    ChessPosition endPosition;
    ChessPiece.PieceType promotionPiece;
    public ChessMoveImpl(ChessPosition start, ChessPosition end, ChessPiece.PieceType promotion){
        startPosition = new ChessPositionImpl(start.getRow(), start.getColumn());
        endPosition = new ChessPositionImpl(end.getRow(), end.getColumn());
        promotionPiece = promotion;
    }
    @Override
    public ChessPosition getStartPosition() {
        return startPosition;
    }

    @Override
    public ChessPosition getEndPosition() {
        return endPosition;
    }

    @Override
    public ChessPiece.PieceType getPromotionPiece() {
        return promotionPiece;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessMoveImpl chessMove = (ChessMoveImpl) o;
        return Objects.equals(startPosition, chessMove.startPosition) && Objects.equals(endPosition, chessMove.endPosition) && promotionPiece == chessMove.promotionPiece;
    }
    @Override
    public int hashCode() {
        return Objects.hash(startPosition, endPosition, promotionPiece);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Start: ");
        output.append(startPosition.toString());
        output.append("\n");
        output.append("End: ");
        output.append(endPosition.toString());
        if(promotionPiece != null) {
            output.append("\n");
            output.append("Promotion: ");
            output.append(promotionPiece.name());
        }
        return output.toString();
    }
}
