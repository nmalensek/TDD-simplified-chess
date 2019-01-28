package a1;

import java.util.ArrayList;
import java.util.Map;

import static java.util.Map.entry;

public abstract class ChessPiece {

    public enum Color {WHITE, BLACK}

    protected ChessBoard board; // the board it belongs to, default null
    protected int row; // the index of the horizontal rows 0..7
    protected int column; // the index of the vertical column 0..7
    protected Color color; // the color of the piece

    private int ILLEGAL_COLUMN = -1;

    private static final Map<Integer, Character> intCharMap;
    static {
        intCharMap = Map.ofEntries(
                entry(0, 'a'),
                entry(1, 'b'),
                entry(2,'c'),
                entry(3,'d'),
                entry(4,'e'),
                entry(5, 'f'),
                entry(6,'g'),
                entry(7,'h')
        );
    }
    private static final Map<Character, Integer> charIntMap;
    static {
        charIntMap = Map.ofEntries(
                entry('a', 0),
                entry('b', 1),
                entry('c', 2),
                entry('d', 3),
                entry('e', 4),
                entry('f', 5),
                entry('g', 6),
                entry('h', 7)
        );
    }

    public ChessPiece(ChessBoard board, Color color) {
        this.board = board;
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }


    public String getPosition() {
        String position = "";
        position += convertIntToChar(this.column);
        position += this.row;
        return position;
    }

    public void setPosition(String position) throws IllegalPositionException {
        if (position.length() != 2) { throw new IllegalPositionException(); }

        //check column
        if (convertCharToInt(position.charAt(0)) == ILLEGAL_COLUMN) {
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

        this.column = convertCharToInt(position.charAt(0));
        this.row = Character.getNumericValue(position.charAt(1));
    }

    abstract public String toString();
    abstract public ArrayList<String> legalMoves();


    private char convertIntToChar(int number) {
        return intCharMap.get(number);
    }

    private int convertCharToInt(char letter) {
        if (!charIntMap.containsKey(letter)) { return ILLEGAL_COLUMN; }
        return charIntMap.get(letter);
    }
}
