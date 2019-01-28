package a1;

import a1.helpers.LetterIntConverter;

import java.util.ArrayList;

public abstract class ChessPiece {

    public enum Color {WHITE, BLACK}

    protected ChessBoard board; // the board it belongs to, default null
    protected int row; // the index of the horizontal rows 0..7
    protected int column; // the index of the vertical column 0..7
    protected Color color; // the color of the piece

    private int ILLEGAL_COLUMN = -1;
    private char ILLEGAL_ROW = 'z';

    public ChessPiece(ChessBoard board, Color color) {
        this.board = board;
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }


    public String getPosition() {
        String position = "";
        position += LetterIntConverter.convertIntToChar(this.column);
        position += this.row;
        return position;
    }

    public void setPosition(String position) throws IllegalPositionException {
        if (position.length() != 2) { throw new IllegalPositionException(); }

        //check column
        if (LetterIntConverter.convertCharToInt(position.charAt(0)) == ILLEGAL_COLUMN) {
            throw new IllegalPositionException();
        }

        //check row
        try {
            Integer.parseInt(String.valueOf(position.charAt(1)));
        } catch (NumberFormatException e) {
            throw new IllegalPositionException();
        }

        if (Character.getNumericValue(position.charAt(1)) < 1 ||
                Character.getNumericValue(position.charAt(1)) > 8) {
            throw new IllegalPositionException();
        }

        this.column = LetterIntConverter.convertCharToInt(position.charAt(0));
        this.row = Character.getNumericValue(position.charAt(1));
    }

    abstract public String toString();
    abstract public ArrayList<String> legalMoves();

}
