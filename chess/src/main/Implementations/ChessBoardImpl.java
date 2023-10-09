package Implementations;

import ChessPieces.*;
import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPiece;
import chess.ChessPosition;

public class ChessBoardImpl implements ChessBoard {
    ChessPiece[][] board;

    ChessBoardImpl(){
        board = new ChessPiece[8][8];
    }
    @Override
    public void addPiece(ChessPosition position, ChessPiece piece) {
        board[(position.getRow()-1)][(position.getColumn()-1)] = piece;
    }

    @Override
    public ChessPiece getPiece(ChessPosition position) {
        return board[(position.getRow()-1)][(position.getColumn()-1)];
    }

    @Override
    public void resetBoard() {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(i == 0){
                    switch (j){
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
                }
                else if(i == 1){
                    board[i][j] = new PawnImpl(ChessGame.TeamColor.WHITE,0, false);
                }
                else if(i == 6){
                    board[i][j] = new PawnImpl(ChessGame.TeamColor.BLACK, 0, false);
                }
                else if(i == 7){
                    switch (j){
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
                }
                else{
                    board[i][j] = null;
                }
            }
        }
    }
}
