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
    public PawnImpl(ChessGame.TeamColor team){
        teamColor = team;
        pieceType = PieceType.PAWN;
    }
    public PawnImpl(PawnImpl pawnToCopy){
        this.teamColor = pawnToCopy.teamColor;
        this.pieceType = pawnToCopy.pieceType;
    }
    @Override
    Collection<ChessMove> moveHelper(ChessBoard chessBoard, ChessPosition myPosition) {
        HashSet<ChessMove> pawnMoves = new HashSet<>();
        ChessPositionImpl currentPosition;
        int[][] possibleMoves;
        //check color to know add or subtract
        switch(chessBoard.getPiece(myPosition).getTeamColor()){
            case WHITE:
                possibleMoves = new int[][] {{1, -1}, {1, 0}, {1, 1}};
                //check row to know if its first move and if I can add double move
                if(myPosition.getRow() == 2){
                    currentPosition = new ChessPositionImpl((myPosition.getRow()+1), myPosition.getColumn());
                    if(chessBoard.getPiece(currentPosition) == null){
                        currentPosition = new ChessPositionImpl((myPosition.getRow()+2), myPosition.getColumn());
                        if(chessBoard.getPiece(currentPosition) == null) {
                            pawnMoves.add(new ChessMoveImpl(myPosition, currentPosition, null));
                        }
                    }
                }
                //add potential single moves
                for(int i =0; i < 3; i++){
                    currentPosition = new ChessPositionImpl((myPosition.getRow()+possibleMoves[i][0]), (myPosition.getColumn()+possibleMoves[i][1]));
                    if(i != 1){
                        if(chessBoard.getPiece(currentPosition) != null){
                            if(chessBoard.getPiece(currentPosition).getTeamColor() != chessBoard.getPiece(myPosition).getTeamColor()){
                                if(currentPosition.getRow() == 8) {
                                    pawnMoves.add(new ChessMoveImpl(myPosition, currentPosition, PieceType.QUEEN));
                                    pawnMoves.add(new ChessMoveImpl(myPosition, currentPosition, PieceType.KNIGHT));
                                    pawnMoves.add(new ChessMoveImpl(myPosition, currentPosition, PieceType.ROOK));
                                    pawnMoves.add(new ChessMoveImpl(myPosition, currentPosition, PieceType.BISHOP));
                                }
                                else{
                                    pawnMoves.add(new ChessMoveImpl(myPosition, currentPosition, null));
                                }
                            }
                        }
                    }
                    else{
                        if(chessBoard.getPiece(currentPosition) == null) {
                            if (currentPosition.getRow() == 8) {
                                pawnMoves.add(new ChessMoveImpl(myPosition, currentPosition, PieceType.QUEEN));
                                pawnMoves.add(new ChessMoveImpl(myPosition, currentPosition, PieceType.KNIGHT));
                                pawnMoves.add(new ChessMoveImpl(myPosition, currentPosition, PieceType.ROOK));
                                pawnMoves.add(new ChessMoveImpl(myPosition, currentPosition, PieceType.BISHOP));
                            } else {
                                pawnMoves.add(new ChessMoveImpl(myPosition, currentPosition, null));
                            }
                        }
                    }
                }
                break;
            case BLACK:
                possibleMoves = new int[][] {{-1, -1}, {-1, 0}, {-1, 1}};
                //check row to know if its first move and if I can add double move
                if(myPosition.getRow() == 7){
                    currentPosition = new ChessPositionImpl((myPosition.getRow()-1), myPosition.getColumn());
                    if(chessBoard.getPiece(currentPosition) == null){
                        currentPosition = new ChessPositionImpl((myPosition.getRow()-2), myPosition.getColumn());
                        if(chessBoard.getPiece(currentPosition) == null) {
                            pawnMoves.add(new ChessMoveImpl(myPosition, currentPosition, null));
                        }
                    }
                }
                //add potential single moves
                for(int i =0; i < 3; i++){
                    currentPosition = new ChessPositionImpl((myPosition.getRow()+possibleMoves[i][0]), (myPosition.getColumn()+possibleMoves[i][1]));
                    if(i != 1){
                        if(chessBoard.getPiece(currentPosition) != null){
                            if(chessBoard.getPiece(currentPosition).getTeamColor() != chessBoard.getPiece(myPosition).getTeamColor()){
                                if(currentPosition.getRow() == 1) {
                                    pawnMoves.add(new ChessMoveImpl(myPosition, currentPosition, PieceType.QUEEN));
                                    pawnMoves.add(new ChessMoveImpl(myPosition, currentPosition, PieceType.KNIGHT));
                                    pawnMoves.add(new ChessMoveImpl(myPosition, currentPosition, PieceType.ROOK));
                                    pawnMoves.add(new ChessMoveImpl(myPosition, currentPosition, PieceType.BISHOP));
                                }
                                else{
                                    pawnMoves.add(new ChessMoveImpl(myPosition, currentPosition, null));
                                }
                            }
                        }
                    }
                    else{
                        if(chessBoard.getPiece(currentPosition) == null){
                            if(currentPosition.getRow() == 1) {
                                pawnMoves.add(new ChessMoveImpl(myPosition, currentPosition, PieceType.QUEEN));
                                pawnMoves.add(new ChessMoveImpl(myPosition, currentPosition, PieceType.KNIGHT));
                                pawnMoves.add(new ChessMoveImpl(myPosition, currentPosition, PieceType.ROOK));
                                pawnMoves.add(new ChessMoveImpl(myPosition, currentPosition, PieceType.BISHOP));
                            }
                            else{
                                pawnMoves.add(new ChessMoveImpl(myPosition, currentPosition, null));
                            }
                        }
                    }
                }
                break;
        }
        //en passant - to implement
        return pawnMoves;
    }
}
