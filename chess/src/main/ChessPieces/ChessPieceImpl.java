package ChessPieces;

import chess.*;

import java.util.Collection;

abstract class ChessPieceImpl implements ChessPiece {
    ChessGame.TeamColor teamColor;
    PieceType pieceType;
    @Override
    public ChessGame.TeamColor getTeamColor() {
        return teamColor;
    }
    @Override
    public PieceType getPieceType() {
        return pieceType;
    }
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return moveHelper(board, myPosition);
    }
    abstract Collection<ChessMove> moveHelper(ChessBoard chessBoard, ChessPosition myPosition);
}
