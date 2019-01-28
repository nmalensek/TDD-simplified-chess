package a1;

import java.util.Map;

import static java.util.Map.entry;

public class ChessBoard {
    private ChessPiece[][] board;

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

    public ChessBoard() {
        board = new ChessPiece[8][8];
    }

    public void initialize() {
        for (ChessPiece.Color color : ChessPiece.Color.values()) {

        }
    }

    public ChessPiece getPiece(String position) throws IllegalPositionException {
        if (!inputCoordinatesValid(position)) { throw new IllegalPositionException(); }

        int row = Character.getNumericValue(position.charAt(1)) - 1;
        int column = charIntMap.get(position.charAt(0));

        return board[row][column];
    }

    public boolean placePiece(ChessPiece piece, String position) {
        return false;
    }

    public void move(String fromPosition, String toPosition) throws IllegalMoveException {
        //needs to call getPiece, then placePiece
    }

    private boolean inputCoordinatesValid(String coordinates) {
        if (coordinates.length() != 2) { return false; }

        //check column
        if (convertCharToInt(coordinates.charAt(0)) == ILLEGAL_COLUMN) {
            return false;
        }

        //check row
        try {
            Integer.parseInt(String.valueOf(coordinates.charAt(1)));
        } catch (NumberFormatException e) {
            return false;
        }

        if (Character.getNumericValue(coordinates.charAt(1)) < 1 ||
                Character.getNumericValue(coordinates.charAt(1)) > 8) {
            return false;
        }

        return true;
    }

    private char convertIntToChar(int number) {
        return intCharMap.get(number);
    }

    private int convertCharToInt(char letter) {
        if (!charIntMap.containsKey(letter)) { return ILLEGAL_COLUMN; }
        return charIntMap.get(letter);
    }

    public String toString(){
        String chess="";
        String upperLeft = "\u250C";
        String upperRight = "\u2510";
        String horizontalLine = "\u2500";
        String horizontal3 = horizontalLine + "\u3000" + horizontalLine;
        String verticalLine = "\u2502";
        String upperT = "\u252C";
        String bottomLeft = "\u2514";
        String bottomRight = "\u2518";
        String bottomT = "\u2534";
        String plus = "\u253C";
        String leftT = "\u251C";
        String rightT = "\u2524";

        String topLine = upperLeft;
        for (int i = 0; i<7; i++){
            topLine += horizontal3 + upperT;
        }
        topLine += horizontal3 + upperRight;

        String bottomLine = bottomLeft;
        for (int i = 0; i<7; i++){
            bottomLine += horizontal3 + bottomT;
        }
        bottomLine += horizontal3 + bottomRight;
        chess+=topLine + "\n";

        for (int row = 7; row >=0; row--){
            String midLine = "";
            for (int col = 0; col < 8; col++){
                if(board[row][col]==null) {
                    midLine += verticalLine + " \u3000 ";
                } else {midLine += verticalLine + " "+board[row][col]+" ";}
            }
            midLine += verticalLine;
            String midLine2 = leftT;
            for (int i = 0; i<7; i++){
                midLine2 += horizontal3 + plus;
            }
            midLine2 += horizontal3 + rightT;
            chess+=midLine+ "\n";
            if(row>=1)
                chess+=midLine2+ "\n";
        }

        chess+=bottomLine;
        return chess;
    }

    public static void main(String[] args) throws IllegalMoveException {
        ChessBoard board = new ChessBoard();
        board.initialize();
        System.out.println(board);
        board.move("c2", "c4");
        System.out.println(board);
    }
}
