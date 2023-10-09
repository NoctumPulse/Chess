package Implementations;

import chess.*;

import java.util.Collection;

public class ChessGameImpl implements ChessGame{
    TeamColor teamTurn;
    ChessBoard chessBoard;
    ChessGameImpl(){
        setTeamTurn(TeamColor.WHITE);
        chessBoard = new ChessBoardImpl();
    }
    ChessGameImpl(TeamColor team){
        setTeamTurn(team);
        chessBoard = new ChessBoardImpl();
    }
    ChessGameImpl(ChessBoard chess){
        setTeamTurn(TeamColor.WHITE);
        chessBoard = chess;
    }
    ChessGameImpl(TeamColor team, ChessBoard chess){
        setTeamTurn(team);
        chessBoard = chess;
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
        return null;
    }
    @Override
    public void makeMove(ChessMove move) throws InvalidMoveException {

    }
    @Override
    public boolean isInCheck(TeamColor teamColor) {
        return false;
    }
    @Override
    public boolean isInCheckmate(TeamColor teamColor) {
        return false;
    }
    @Override
    public boolean isInStalemate(TeamColor teamColor) {
        return false;
    }
    @Override
    public void setBoard(ChessBoard board) {

    }
    @Override
    public ChessBoard getBoard() {
        return null;
    }
}
