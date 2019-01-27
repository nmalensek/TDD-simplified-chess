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
        return null;
    }
}
