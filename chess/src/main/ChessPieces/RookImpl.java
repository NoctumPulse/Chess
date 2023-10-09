package ChessPieces;

import Implementations.ChessMoveImpl;
import Implementations.ChessPositionImpl;
import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.Collection;
import java.util.HashSet;

public class RookImpl extends ChessPieceImpl {
    public RookImpl(ChessGame.TeamColor team){
        teamColor = team;
        pieceType = PieceType.ROOK;
    }
    @Override
    Collection<ChessMove> moveHelper(ChessBoard chessBoard, ChessPosition myPosition) {
        HashSet<ChessMove> rookMoves = new HashSet<>();
        for(int i = 0; i < 4; i++){
            boolean obstacleNotFound = true;
            ChessPosition currentEndPosition = null;
            int[][] possibleMoveDirections = {{1,0},{0,1},{-1,0},{0,-1}};
            int spaces = 1;
            while(obstacleNotFound){
                currentEndPosition = new ChessPositionImpl((myPosition.getRow()+(spaces*possibleMoveDirections[i][0])),(myPosition.getColumn()+(spaces*possibleMoveDirections[i][1])));
                if(currentEndPosition.getRow() >= 1 && currentEndPosition.getRow() <= 8){
                    if(currentEndPosition.getColumn() >= 1 && currentEndPosition.getColumn() <= 8){
                        if(chessBoard.getPiece(currentEndPosition) == null){
                            rookMoves.add(new ChessMoveImpl(myPosition, currentEndPosition, null));
                            spaces++;
                        }
                        else{
                            if(chessBoard.getPiece(currentEndPosition).getTeamColor() != chessBoard.getPiece(myPosition).getTeamColor()){
                                rookMoves.add(new ChessMoveImpl(myPosition, currentEndPosition, null));
                            }
                            obstacleNotFound = false;
                        }
                    }
                    else{
                        obstacleNotFound = false;
                    }
                }
                else{
                    obstacleNotFound = false;
                }
            }
        }
        return rookMoves;
    }
}
