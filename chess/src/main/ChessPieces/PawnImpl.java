package ChessPieces;

import Implementations.ChessMoveImpl;
import Implementations.ChessPositionImpl;
import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.Collection;
import java.util.HashSet;

public class PawnImpl extends ChessPieceImpl {
    int moveCount;
    boolean movedTwo;
    public PawnImpl(ChessGame.TeamColor team, int moves, boolean farMove){
        teamColor = team;
        pieceType = PieceType.PAWN;
        moveCount = moves;
        movedTwo = farMove;
    }
    public int getMoveCount(){
        return moveCount;
    }
    public void incrementMoveCount(){
        moveCount++;
    }
    public boolean getMovedTwo(){
        return movedTwo;
    }

    public void setMovedTwo(boolean moved) {
        movedTwo = moved;
    }

    @Override
    Collection<ChessMove> moveHelper(ChessBoard chessBoard, ChessPosition myPosition) {
        HashSet<ChessMove> pawnMoves = new HashSet<>();
        //first move check if you can move two spaces
        if(moveCount == 0){
            ChessPosition endPosition = new ChessPositionImpl((myPosition.getRow()+2), myPosition.getColumn());
            if(chessBoard.getPiece(new ChessPositionImpl((myPosition.getRow()+1),myPosition.getColumn())) == null){
                pawnMoves.add(new ChessMoveImpl(myPosition, endPosition, null));
            }
        }
        //any other move
        int[][] possiblePositions = {{1,0},{1,-1},{1,1}};
        for(int i = 0; i < 3; i++){
            ChessPosition endPosition = new ChessPositionImpl((myPosition.getRow()+possiblePositions[i][0]), (myPosition.getColumn()+possiblePositions[i][1]));
            switch(i){
                case 0:
                    if(endPosition.getRow() < 8){
                        if(chessBoard.getPiece(endPosition) == null){
                            pawnMoves.add(new ChessMoveImpl(myPosition, endPosition, null));
                        }
                    }
                    else{
                        if(chessBoard.getPiece(endPosition) == null){
                            pawnMoves.add(new ChessMoveImpl(myPosition, endPosition, PieceType.QUEEN));
                            pawnMoves.add(new ChessMoveImpl(myPosition, endPosition, PieceType.ROOK));
                            pawnMoves.add(new ChessMoveImpl(myPosition, endPosition, PieceType.BISHOP));
                            pawnMoves.add(new ChessMoveImpl(myPosition, endPosition, PieceType.KNIGHT));
                        }
                    }
                    break;
                case 1:
                case 2:
                    if(endPosition.getRow() < 8){
                        if(chessBoard.getPiece(endPosition) != null){
                            if(chessBoard.getPiece(endPosition).getTeamColor() != chessBoard.getPiece(myPosition).getTeamColor()) {
                                pawnMoves.add(new ChessMoveImpl(myPosition, endPosition, null));
                            }
                        }
                    }
                    else{
                        if(chessBoard.getPiece(endPosition) != null){
                            if(chessBoard.getPiece(endPosition).getTeamColor() != chessBoard.getPiece(myPosition).getTeamColor()) {
                                pawnMoves.add(new ChessMoveImpl(myPosition, endPosition, PieceType.QUEEN));
                                pawnMoves.add(new ChessMoveImpl(myPosition, endPosition, PieceType.ROOK));
                                pawnMoves.add(new ChessMoveImpl(myPosition, endPosition, PieceType.BISHOP));
                                pawnMoves.add(new ChessMoveImpl(myPosition, endPosition, PieceType.KNIGHT));
                            }
                        }
                    }
                    break;
            }
        }
        //en passant - to implement
        return pawnMoves;
    }
}
