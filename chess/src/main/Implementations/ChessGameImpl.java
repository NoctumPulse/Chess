package Implementations;

import ChessPieces.*;
import chess.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ChessGameImpl implements ChessGame {
    TeamColor teamTurn;
    ChessBoard chessBoard;

    public ChessGameImpl() {
        setTeamTurn(TeamColor.WHITE);
        chessBoard = new ChessBoardImpl();
    }

    public ChessGameImpl(TeamColor team) {
        setTeamTurn(team);
        chessBoard = new ChessBoardImpl();
    }

    public ChessGameImpl(ChessBoard chess) {
        setTeamTurn(TeamColor.WHITE);
        chessBoard = new ChessBoardImpl(chess);
    }

    public ChessGameImpl(TeamColor team, ChessBoard chess) {
        setTeamTurn(team);
        chessBoard = new ChessBoardImpl(chess);
    }

    @Override
    public TeamColor getTeamTurn() {
        return teamTurn;
    }

    @Override
    public void setTeamTurn(TeamColor team) {
        teamTurn = team;
    }

    @Override
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        HashSet<ChessMove> pieceMoves = new HashSet<>(chessBoard.getPiece(startPosition).pieceMoves(chessBoard, startPosition));
        HashSet<ChessMove> validMoves = new HashSet<>();
        TeamColor teamColor = chessBoard.getPiece(startPosition).getTeamColor();
        for (ChessMove move : pieceMoves) {
            ChessBoardImpl testBoard = new ChessBoardImpl(chessBoard);
            testBoard.removePiece(move.getStartPosition());
            testBoard.addPiece(move.getEndPosition(), chessBoard.getPiece(startPosition));
            if (!isInCheckHelper(testBoard, teamColor)) {
                validMoves.add(move);
            }
        }
        return validMoves;
    }

    @Override
    public void makeMove(ChessMove move) throws InvalidMoveException {
        if (chessBoard.getPiece(move.getStartPosition()).getTeamColor() != teamTurn) {
            throw new InvalidMoveException("Not this team's turn");
        }
        ChessPositionImpl start = (ChessPositionImpl) move.getStartPosition();
        Set<ChessMove> legalMoves = new HashSet<>(validMoves(start));
        if (!legalMoves.contains(move)) {
            throw new InvalidMoveException("That move is illegal or not possible");
        }
        ChessPiece chessPiece = null;
        switch (chessBoard.getPiece(start).getPieceType()) {
            case QUEEN:
                chessPiece = new QueenImpl(chessBoard.getPiece(start).getTeamColor());
                break;
            case KING:
                chessPiece = new KingImpl(chessBoard.getPiece(start).getTeamColor());
                break;
            case BISHOP:
                chessPiece = new BishopImpl(chessBoard.getPiece(start).getTeamColor());
                break;
            case KNIGHT:
                chessPiece = new KnightImpl(chessBoard.getPiece(start).getTeamColor());
                break;
            case ROOK:
                chessPiece = new RookImpl(chessBoard.getPiece(start).getTeamColor());
                break;
            case PAWN:
                if (move.getPromotionPiece() != null) {
                    switch (move.getPromotionPiece()) {
                        case KING -> throw new InvalidMoveException("cannot promote to king");
                        case QUEEN -> chessPiece = new QueenImpl(chessBoard.getPiece(start).getTeamColor());
                        case BISHOP -> chessPiece = new BishopImpl(chessBoard.getPiece(start).getTeamColor());
                        case KNIGHT -> chessPiece = new KnightImpl(chessBoard.getPiece(start).getTeamColor());
                        case ROOK -> chessPiece = new RookImpl(chessBoard.getPiece(start).getTeamColor());
                        case PAWN -> throw new InvalidMoveException("cannot promote to pawn");
                    }
                } else {
                    chessPiece = new PawnImpl(chessBoard.getPiece(start).getTeamColor());
                }
                break;
        }
        ChessBoardImpl newBoard = new ChessBoardImpl(chessBoard);
        newBoard.removePiece(move.getStartPosition());
        newBoard.removePiece(move.getEndPosition());
        newBoard.addPiece(move.getEndPosition(), (ChessPiece) chessPiece);
        chessBoard = newBoard;
        if (teamTurn == TeamColor.WHITE) {
            setTeamTurn(TeamColor.BLACK);
        } else {
            setTeamTurn(TeamColor.WHITE);
        }
    }

    @Override
    public boolean isInCheck(TeamColor teamColor) {
        return isInCheckHelper(chessBoard, teamColor);
    }

    @Override
    public boolean isInCheckmate(TeamColor teamColor) {
        Set<ChessMove> allValidMoves = new HashSet<>();
        ChessPositionImpl currentPosition;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                currentPosition = new ChessPositionImpl(i + 1, j + 1);
                if (chessBoard.getPiece(currentPosition) != null) {
                    if (chessBoard.getPiece(currentPosition).getTeamColor() == teamColor) {
                        allValidMoves.addAll(validMoves(currentPosition));
                    }
                }
            }
        }
        return allValidMoves.isEmpty();
    }

    @Override
    public boolean isInStalemate(TeamColor teamColor) {
        Set<ChessMove> allValidMoves = new HashSet<>();
        ChessPositionImpl currentPosition;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                currentPosition = new ChessPositionImpl(i + 1, j + 1);
                if (chessBoard.getPiece(currentPosition) != null) {
                    if (chessBoard.getPiece(currentPosition).getTeamColor() == teamColor) {
                        allValidMoves.addAll(validMoves(currentPosition));
                    }
                }
            }
        }
        return (allValidMoves.isEmpty() && getTeamTurn() == teamColor);
    }

    @Override
    public void setBoard(ChessBoard board) {
        chessBoard = new ChessBoardImpl(board);
    }

    @Override
    public ChessBoard getBoard() {
        return chessBoard;
    }

    public boolean isInCheckHelper(ChessBoard chessBoard, TeamColor teamColor) {
        ChessPositionImpl kingPosition = null;
        ChessPositionImpl currentPosition;
        //find king
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                currentPosition = new ChessPositionImpl(i + 1, j + 1);
                if (chessBoard.getPiece(currentPosition) != null) {
                    if (chessBoard.getPiece(currentPosition).getPieceType() == ChessPiece.PieceType.KING) {
                        if (chessBoard.getPiece(currentPosition).getTeamColor() == teamColor) {
                            kingPosition = currentPosition;
                            break;
                        }
                    }
                }
            }
            if (kingPosition != null) {
                break;
            }
        }
        //if king is nonexistent like some tests have return false
        if (kingPosition == null) {
            return false;
        }
        //check if any enemy piece can take king
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                currentPosition = new ChessPositionImpl(i + 1, j + 1);
                if (chessBoard.getPiece(currentPosition) != null) {
                    if (chessBoard.getPiece(currentPosition).getTeamColor() != teamColor) {
                        HashSet<ChessMove> currentMoves = new HashSet<>(chessBoard.getPiece(currentPosition).pieceMoves(chessBoard, currentPosition));
                        switch (chessBoard.getPiece(currentPosition).getPieceType()) {
                            case PAWN:
                                if (currentMoves.contains(new ChessMoveImpl(currentPosition, kingPosition, null))) {
                                    return true;
                                } else if (currentMoves.contains(new ChessMoveImpl(currentPosition, kingPosition, ChessPiece.PieceType.QUEEN))) {
                                    return true;
                                } else if (currentMoves.contains(new ChessMoveImpl(currentPosition, kingPosition, ChessPiece.PieceType.BISHOP))) {
                                    return true;
                                } else if (currentMoves.contains(new ChessMoveImpl(currentPosition, kingPosition, ChessPiece.PieceType.ROOK))) {
                                    return true;
                                } else if (currentMoves.contains(new ChessMoveImpl(currentPosition, kingPosition, ChessPiece.PieceType.KNIGHT))) {
                                    return true;
                                }
                                break;
                            case QUEEN:
                            case KING:
                            case BISHOP:
                            case ROOK:
                            case KNIGHT:
                                if (currentMoves.contains(new ChessMoveImpl(currentPosition, kingPosition, null))) {
                                    return true;
                                }
                                break;
                        }
                    }
                }
            }
        }
        return false;
    }
}
