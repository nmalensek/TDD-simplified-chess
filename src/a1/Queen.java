package a1;

import java.util.ArrayList;

public class Queen extends ChessPiece {

    public Queen(ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return this.getColor().equals(Color.WHITE) ? "\u2655" : "\u265B";
    }

    @Override
    public ArrayList<String> legalMoves() {
        return new ArrayList<>();
    }
}
