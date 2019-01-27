package a1;

import java.util.ArrayList;

public class King extends ChessPiece {


    public King(ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return this.getColor().equals(Color.WHITE) ? "\u2654" : "\u265A";
    }

    @Override
    public ArrayList<String> legalMoves() {
        return null;
    }
}
