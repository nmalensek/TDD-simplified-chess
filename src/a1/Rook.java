package a1;

import java.util.ArrayList;

public class Rook extends ChessPiece{

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
        boolean[] forwardOrBack = { true, false };

        for (boolean forward : forwardOrBack) {
            walkPath(moveList, forward, true);
            walkPath(moveList, forward, false);
        }

        return moveList;
    }

    private void walkPath(ArrayList<String> moves, boolean forward, boolean rows) {
        int rowIndex;
        int columnIndex;

        if (forward) {
            rowIndex = rows ? this.row + 2 : this.row + 1;
            columnIndex = rows ? this.column : this.column + 1;
        } else {
            rowIndex = rows ? this.row : this.row + 1;
            columnIndex = rows ? this.column : this.column - 1;
        }
        ChessPiece pieceInSquare;
        while(true) {
            try {
                pieceInSquare = board.getPiece(String.valueOf(convertIntToChar(columnIndex)) + rowIndex);
                if (pieceInSquare == null) {
                    moves.add(String.valueOf(convertIntToChar(columnIndex)) + rowIndex);
                } else {
                    if (pieceInSquare.color == this.color) {
                        break;
                    } else {
                        moves.add(String.valueOf(convertIntToChar(columnIndex)) + rowIndex);
                        break;
                    }
                }
            } catch (IllegalPositionException e) {
                break;
            }

            if (forward) {
                if (rows) {
                    rowIndex++;
                } else {
                    columnIndex++;
                }
            } else {
                if (rows) {
                    rowIndex--;
                } else {
                    columnIndex--;
                }
            }
        }
    }
}
