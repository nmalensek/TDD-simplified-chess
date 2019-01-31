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
                this.row, this.row + 1, this.row + 2
        };
        int[] potentialColumns = {
                this.column - 1, this.column, this.column + 1
        };

        String potentialPosition;
        ChessPiece pieceInSpace;
        for (int rowIndex : potentialRows) {
            for (int columnIndex : potentialColumns) {
                potentialPosition = String.valueOf(convertIntToChar(columnIndex)) + rowIndex;
                try {
                    pieceInSpace = board.getPiece(potentialPosition);
                    if (pieceInSpace == null || pieceInSpace.color != this.color) {
                        moveList.add(potentialPosition);
                    }
                } catch (IllegalPositionException e) {
                    //ignore, don't add to legal moves.
                }
            }
        }

        return  moveList;
    }
}
