package a1;

import java.util.ArrayList;

public class Knight extends ChessPiece {

    public Knight(ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return this.getColor().equals(Color.WHITE) ? "\u2658" : "\u265E";
    }

    @Override
    public ArrayList<String> legalMoves() {
        return new ArrayList<>();
    }
}
