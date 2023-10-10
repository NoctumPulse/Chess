package Implementations;

import ChessPieces.*;
import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPiece;
import chess.ChessPosition;

public class ChessBoardImpl implements ChessBoard {
    ChessPiece[][] board;

    public ChessBoardImpl() {
        board = new ChessPiece[8][8];
    }

    public ChessBoardImpl(ChessBoard chessBoard) {
        board = new ChessPiece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPosition currentPosition = new ChessPositionImpl(i + 1, j + 1);
                if (chessBoard.getPiece(currentPosition) != null) {
                    switch (chessBoard.getPiece(currentPosition).getPieceType()) {
                        case PAWN ->
                                this.addPiece(currentPosition, new PawnImpl((PawnImpl) chessBoard.getPiece(currentPosition)));
                        case ROOK ->
                                this.addPiece(currentPosition, new RookImpl((RookImpl) chessBoard.getPiece(currentPosition)));
                        case KNIGHT ->
                                this.addPiece(currentPosition, new KnightImpl((KnightImpl) chessBoard.getPiece(currentPosition)));
                        case KING ->
                                this.addPiece(currentPosition, new KingImpl((KingImpl) chessBoard.getPiece(currentPosition)));
                        case BISHOP ->
                                this.addPiece(currentPosition, new BishopImpl((BishopImpl) chessBoard.getPiece(currentPosition)));
                        case QUEEN ->
                                this.addPiece(currentPosition, new QueenImpl((QueenImpl) chessBoard.getPiece(currentPosition)));
                    }
                }
            }
        }
    }

    @Override
    public void addPiece(ChessPosition position, ChessPiece piece) {
        switch (piece.getPieceType()) {
            case QUEEN -> board[(position.getRow() - 1)][(position.getColumn() - 1)] = new QueenImpl((QueenImpl) piece);
            case KING -> board[(position.getRow() - 1)][(position.getColumn() - 1)] = new KingImpl((KingImpl) piece);
            case BISHOP ->
                    board[(position.getRow() - 1)][(position.getColumn() - 1)] = new BishopImpl((BishopImpl) piece);
            case KNIGHT ->
                    board[(position.getRow() - 1)][(position.getColumn() - 1)] = new KnightImpl((KnightImpl) piece);
            case ROOK -> board[(position.getRow() - 1)][(position.getColumn() - 1)] = new RookImpl((RookImpl) piece);
            case PAWN -> board[(position.getRow() - 1)][(position.getColumn() - 1)] = new PawnImpl((PawnImpl) piece);
        }
    }

    @Override
    public ChessPiece getPiece(ChessPosition position) {
        return board[(position.getRow() - 1)][(position.getColumn() - 1)];
    }

    public void removePiece(ChessPosition position) {
        board[(position.getRow() - 1)][(position.getColumn() - 1)] = null;
    }

    @Override
    public void resetBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0) {
                    switch (j) {
                        case 0:
                        case 7:
                            board[i][j] = new RookImpl(ChessGame.TeamColor.WHITE);
                            break;
                        case 1:
                        case 6:
                            board[i][j] = new KnightImpl(ChessGame.TeamColor.WHITE);
                            break;
                        case 2:
                        case 5:
                            board[i][j] = new BishopImpl(ChessGame.TeamColor.WHITE);
                            break;
                        case 3:
                            board[i][j] = new QueenImpl(ChessGame.TeamColor.WHITE);
                            break;
                        case 4:
                            board[i][j] = new KingImpl(ChessGame.TeamColor.WHITE);
                    }
                } else if (i == 1) {
                    board[i][j] = new PawnImpl(ChessGame.TeamColor.WHITE);
                } else if (i == 6) {
                    board[i][j] = new PawnImpl(ChessGame.TeamColor.BLACK);
                } else if (i == 7) {
                    switch (j) {
                        case 0:
                        case 7:
                            board[i][j] = new RookImpl(ChessGame.TeamColor.BLACK);
                            break;
                        case 1:
                        case 6:
                            board[i][j] = new KnightImpl(ChessGame.TeamColor.BLACK);
                            break;
                        case 2:
                        case 5:
                            board[i][j] = new BishopImpl(ChessGame.TeamColor.BLACK);
                            break;
                        case 3:
                            board[i][j] = new QueenImpl(ChessGame.TeamColor.BLACK);
                            break;
                        case 4:
                            board[i][j] = new KingImpl(ChessGame.TeamColor.BLACK);
                    }
                } else {
                    board[i][j] = null;
                }
            }
        }
    }
}
