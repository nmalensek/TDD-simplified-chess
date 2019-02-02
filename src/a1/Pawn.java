package a1;

import java.util.ArrayList;

public class Pawn extends ChessPiece {

    public Pawn(ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return this.getColor().equals(Color.WHITE) ? "\u2659" : "\u265F";
    }

    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> moveList = new ArrayList<>();
        boolean shouldAdd = this.color == Color.WHITE;
        int newIndexStartPosition = addOrSubtractRow(this.row, shouldAdd, 2); //5
        int newIndexOtherwise = addOrSubtractRow(this.row, shouldAdd, 1); //4
        String newStartPositionString = String.valueOf(newIndexStartPosition + 1);
        String newOtherwisePositionString = String.valueOf(newIndexOtherwise + 1);

        int[] potentialColumns = {
            this.column - 1, this.column, this.column + 1
        };

        if ((this.row == 1 && this.color == Color.WHITE) || (this.row == 6 && this.color == Color.BLACK)) {
            String tempColumn = String.valueOf(convertIntToChar(this.column));

            if (spaceEmpty(tempColumn + newStartPositionString) && spaceEmpty(tempColumn + newOtherwisePositionString)) {
                moveList.add(tempColumn + newStartPositionString);
            }
        }

        for (int column : potentialColumns) {
            String spaceToCheck = convertIntToChar(column) + newOtherwisePositionString;
            if (column != this.column) {
                try {
                    if (!spaceEmpty(spaceToCheck)
                    && board.getPiece(spaceToCheck).color != this.color) {
                        moveList.add(spaceToCheck);
                    }
                } catch (IllegalPositionException e) {
                    //not a valid position, keep checking
                }
            } else {
                if (spaceEmpty(spaceToCheck)) {
                    moveList.add(spaceToCheck);
                }
            }
        }
        return moveList;
    }

    private int addOrSubtractRow(int row, boolean add, int amount) {
        return add ? row + amount : row - amount;
    }

    private boolean spaceEmpty(String position) {
        try {
            if (board.getPiece(position) == null) {
                return true;
            }
        } catch (IllegalPositionException e) {
            return false;
        }
        return false;
    }
}
