package a1;

import java.util.ArrayList;

public abstract class ChessPiece {

    public enum Color {WHITE, BLACK}

    protected ChessBoard board; // the board it belongs to, default null
    protected int row; // the index of the horizontal rows 0..7
    protected int column; // the index of the vertical column 0..7
    protected Color color; // the color of the piece

    private int ILLEGAL_COLUMN = -1;

    public ChessPiece(ChessBoard board, Color color) {
        this.board = board;
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }


    public String getPosition() {
        String position = "";
        //add one to return coordinate values
        position += String.valueOf(convertIntToChar(this.column));
        position += this.row + 1;
        return position;
    }

    public void setPosition(String position) throws IllegalPositionException {
        if (position.length() != 2) { throw new IllegalPositionException(); }

        //check column
        if (convertCharToInt(position.charAt(0)) == ILLEGAL_COLUMN) {
            throw new IllegalPositionException();
        }

        //check row
        if (Character.getNumericValue(position.charAt(1)) < 1 ||
                Character.getNumericValue(position.charAt(1)) > 8) {
            throw new IllegalPositionException();
        }

        this.column = convertCharToInt(position.charAt(0));
        //subtract 1 to set index value
        this.row = Character.getNumericValue(position.charAt(1)) - 1;
    }

    abstract public String toString();
    abstract public ArrayList<String> legalMoves();


    protected char convertIntToChar(int number) {
        return Character.toChars(number + 'a')[0];
    }

    private int convertCharToInt(char letter) {
        if (letter - 'a' > 7 || letter - 'a' < 0) { return ILLEGAL_COLUMN; }
        return letter - 'a';
    }
}
