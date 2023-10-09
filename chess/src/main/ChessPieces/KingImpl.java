package ChessPieces;

import Implementations.ChessMoveImpl;
import Implementations.ChessPositionImpl;
import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
public class KingImpl extends ChessPieceImpl {
    public KingImpl(ChessGame.TeamColor team){
        teamColor = team;
        pieceType = PieceType.KING;
    }
    @Override
    Collection<ChessMove> moveHelper(ChessBoard chessBoard, ChessPosition myPosition) {
        HashSet<ChessMove> kingMoves = new HashSet<>();
        //Normal Moves
        for(int i = (myPosition.getRow()-1); i < (myPosition.getRow()+2); i++){
            for(int j = (myPosition.getColumn()-1); j < (myPosition.getColumn()+2); j++){
                if(i == 0){
                    break;
                }
                if(j != 0){
                    ChessPosition endPosition = new ChessPositionImpl(i,j);
                    if(chessBoard.getPiece(endPosition) == null){
                        kingMoves.add(new ChessMoveImpl(myPosition, endPosition, null));
                    }
                    else if(chessBoard.getPiece(endPosition).getTeamColor() != chessBoard.getPiece(myPosition).getTeamColor()){
                        kingMoves.add(new ChessMoveImpl(myPosition, endPosition, null));
                    }
                }
            }
        }
        //Castling - To Implement
        return kingMoves;
    }
}
