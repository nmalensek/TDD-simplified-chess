package a1;

import java.util.ArrayList;

public class Rook extends ChessPiece {

    public Rook(ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return this.getColor().equals(Color.WHITE) ? "\u2656" : "\u265C";
    }

    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> moveList = new ArrayList<>();
        boolean[] forwardOrBack = {true, false};

        for (boolean forward : forwardOrBack) {
            walkRows(moveList, forward);
            walkColumns(moveList, forward);
        }

        return moveList;
    }

    private void walkRows(ArrayList<String> moves, boolean forward) {
        int rowIndex;

        rowIndex = forward ? this.row + 2 : this.row;
        while (true) {
            try {
                if (!checkForMove(rowIndex, this.column, moves)) {
                    break;
                }
            } catch (IllegalPositionException e) {
                break;
            }

            if (forward) {
                rowIndex++;
            } else {
                rowIndex--;
            }
        }
    }

    private void walkColumns(ArrayList<String> moves, boolean forward) {
        int columnIndex;

        columnIndex = forward ? this.column + 1 : this.column - 1;
        while (true) {
            try {
                if (!checkForMove(this.row + 1, columnIndex, moves)) {
                    break;
                }
            } catch (IllegalPositionException e) {
                break;
            }

            if (forward) {
                columnIndex++;
            } else {
                columnIndex--;
            }
        }
    }

    private boolean checkForMove(int rowIndex, int columnIndex, ArrayList<String> moveList) throws IllegalPositionException {
        ChessPiece piece = board.getPiece(String.valueOf(convertIntToChar(columnIndex)) + rowIndex);
        if (piece == null) {
            moveList.add(String.valueOf(convertIntToChar(columnIndex)) + rowIndex);
            return true;
        } else {
            if (piece.color == this.color) {
                return false;
            } else {
                moveList.add(String.valueOf(convertIntToChar(columnIndex)) + rowIndex);
                return false;
            }
        }
    }
}
