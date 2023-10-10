package ChessPieces;

import Implementations.ChessMoveImpl;
import Implementations.ChessPositionImpl;
import chess.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
public class KingImpl extends ChessPieceImpl {
    public KingImpl(ChessGame.TeamColor team){
        teamColor = team;
        pieceType = PieceType.KING;
    }
    public KingImpl(KingImpl kingToCopy){
        this.teamColor = kingToCopy.teamColor;
        this.pieceType = kingToCopy.pieceType;
    }
    @Override
    Collection<ChessMove> moveHelper(ChessBoard chessBoard, ChessPosition myPosition) {
        HashSet<ChessMove> kingMoves = new HashSet<>();
        //Normal Moves
        int[][] possibleMoves = {{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1}};
        for(int i = 0; i < 8; i++){
            ChessPositionImpl endPosition = new ChessPositionImpl((myPosition.getRow()+possibleMoves[i][0]),(myPosition.getColumn()+possibleMoves[i][1]));
            if(endPosition.getRow() >= 1 && endPosition.getRow() <= 8){
                if(endPosition.getColumn() >= 1 && endPosition.getColumn() <= 8){
                    if(chessBoard.getPiece(endPosition) != null){
                        if(chessBoard.getPiece(endPosition).getTeamColor() != chessBoard.getPiece(myPosition).getTeamColor()){
                            kingMoves.add(new ChessMoveImpl(myPosition, endPosition, null));
                        }
                    }
                    else{
                        kingMoves.add(new ChessMoveImpl(myPosition, endPosition, null));
                    }
                }
            }
        }
        //Castling - To Implement
        return kingMoves;
    }
}
