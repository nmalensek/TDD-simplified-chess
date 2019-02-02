package a1;

import java.util.ArrayList;

public class Bishop extends ChessPiece {


    public Bishop(ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return this.getColor().equals(Color.WHITE) ? "\u2657" : "\u265D";
    }

    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> moveList = new ArrayList<>();

        moveList.addAll(walkPath(true, true, true, this.row + 2, this.column + 1));
        moveList.addAll(walkPath(true, true, false, this.row + 2, this.column - 1));
        moveList.addAll(walkPath(false, false, true, this.row, this.column + 1));
        moveList.addAll(walkPath(false, false, false, this.row, this.column - 1));

        return moveList;
    }

    private ArrayList<String> walkPath(boolean goingForward, boolean addRow, boolean addColumn, int startRow, int startColumn) {
        ArrayList<String> moves = new ArrayList<>();
        int currentRow = startRow;
        int currentColumn = startColumn;
        ChessPiece piece;
        String currentPosition;

        while(true) {
            currentPosition = String.valueOf(convertIntToChar(currentColumn)) + currentRow;
            try {
                piece = board.getPiece(currentPosition);
                if (piece == null) {
                    moves.add(currentPosition);
                } else {
                    if (piece.color == this.color) {
                        break;
                    }
                    moves.add(currentPosition);
                    break;
                }
            } catch (IllegalPositionException e) {
                break;
            }

            if (addRow) {
                currentRow++;
            } else {
                currentRow--;
            }

            if (addColumn) {
                currentColumn++;
            } else {
                currentColumn--;
            }
        }

        return moves;
    }
}
