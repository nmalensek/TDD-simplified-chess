package a1;

import java.util.ArrayList;

public class King extends ChessPiece {
    private ArrayList<String> moveList = new ArrayList<>();

    public King(ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return this.getColor().equals(Color.WHITE) ? "\u2654" : "\u265A";
    }

    @Override
    public ArrayList<String> legalMoves() {
        moveList.clear();

        int[] potentialRows = {
                this.row - 1, this.row, this.row + 1
        };
        int[] potentialColumns = {
                this.column - 1, this.column, this.column + 1
        };

        ChessPiece pieceInSpace;
        for (int rowIndex : potentialRows) {
            for (int columnIndex : potentialColumns) {

            }
        }
        return null;
    }
}
