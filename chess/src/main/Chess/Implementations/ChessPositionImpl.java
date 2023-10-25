package Chess.Implementations;

import chess.ChessPosition;

import java.util.Objects;

public class ChessPositionImpl implements ChessPosition {
    final int myRow;
    final int myColumn;

    public ChessPositionImpl(int row, int column) {
        myRow = row;
        myColumn = column;
    }

    @Override
    public int getRow() {
        return myRow;
    }

    @Override
    public int getColumn() {
        return myColumn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPositionImpl that = (ChessPositionImpl) o;
        return myRow == that.myRow && myColumn == that.myColumn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(myRow, myColumn);
    }

    @Override
    public String toString() {
        String output = "{" +
                myRow +
                ", " +
                myColumn +
                "}";
        return output;
    }
}
