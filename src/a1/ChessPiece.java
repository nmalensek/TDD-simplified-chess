package a1;

import java.util.ArrayList;

public abstract class ChessPiece {

    public enum Color {WHITE, BLACK}

    protected ChessBoard board; // the board it belongs to, default null
    protected int row; // the index of the horizontal rows 0..7
    protected int column; // the index of the vertical column 0..7
    protected Color color; // the color of the piece
    protected String position;

    public ChessPiece(ChessBoard board, Color color) {
        this.board = board;
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    //TODO: add row int to char converter
    public String getPosition() {
        return Integer.toString(this.row) + Integer.toString(this.column);
    }

    //TODO: check for illegal positions
    public void setPosition(String position) throws IllegalPositionException {
        this.position = position;
    }

    abstract public String toString();
    abstract public ArrayList<String> legalMoves();
}
