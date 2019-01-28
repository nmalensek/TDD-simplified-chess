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
        String firstRowCoordinate;
        String secondRowCoordinate;
        for (ChessPiece.Color color : ChessPiece.Color.values()) {
            firstRowCoordinate = color == ChessPiece.Color.WHITE ? "1" : "8";
            secondRowCoordinate = color == ChessPiece.Color.WHITE ? "2" : "7";

            Rook leftRook = new Rook(this, color);
            placePiece(leftRook, intCharMap.get(0) + firstRowCoordinate);
            updateBoard(leftRook, intCharMap.get(0) + firstRowCoordinate);
            Knight leftKnight = new Knight(this, color);
            placePiece(leftKnight, intCharMap.get(1) + firstRowCoordinate);
            updateBoard(leftKnight, intCharMap.get(1) + firstRowCoordinate);
            Bishop leftBishop = new Bishop(this, color);
            placePiece(leftBishop, intCharMap.get(2) + firstRowCoordinate);
            updateBoard(leftBishop, intCharMap.get(2) + firstRowCoordinate);
            Queen queen = new Queen(this, color);
            placePiece(queen, intCharMap.get(3) + firstRowCoordinate);
            updateBoard(queen, intCharMap.get(3) + firstRowCoordinate);
            King king = new King(this, color);
            placePiece(king, intCharMap.get(4) + firstRowCoordinate);
            updateBoard(king, intCharMap.get(4) + firstRowCoordinate);
            Bishop rightBishop = new Bishop(this, color);
            placePiece(rightBishop, intCharMap.get(5) + firstRowCoordinate);
            updateBoard(rightBishop, intCharMap.get(5) + firstRowCoordinate);
            Knight rightKnight = new Knight(this, color);
            placePiece(rightKnight, intCharMap.get(6) + firstRowCoordinate);
            updateBoard(rightKnight, intCharMap.get(6) + firstRowCoordinate);
            Rook rightRook = new Rook(this, color);
            placePiece(rightRook, intCharMap.get(7) + firstRowCoordinate);
            updateBoard(rightRook, intCharMap.get(7) + firstRowCoordinate);

            for (int i = 0; i < board[0].length; i++) {
                Pawn pawn = new Pawn(this, color);
                placePiece(pawn, intCharMap.get(i) + secondRowCoordinate);
                updateBoard(pawn, intCharMap.get(i) + secondRowCoordinate);
            }

        }
    }

    public ChessPiece getPiece(String position) throws IllegalPositionException {
        if (!inputCoordinatesValid(position)) { throw new IllegalPositionException(); }

        int row = Character.getNumericValue(position.charAt(1)) - 1;
        int column = charIntMap.get(position.charAt(0));

        return board[row][column];
    }

    public boolean placePiece(ChessPiece piece, String position) {
        if (piece == null) { return false; }
        ChessPiece pieceInSpace;

        try {
            pieceInSpace = getPiece(position);
        } catch (IllegalPositionException e) {
            return false;
        }

        if (pieceInSpace != null && pieceInSpace.color == piece.color) { return false; }

        //capture piece if present
        pieceInSpace = null;

        try {
            piece.setPosition(position);
        } catch (IllegalPositionException e) {
            return false;
        }
        return true;
    }

    public void move(String fromPosition, String toPosition) throws IllegalMoveException {
        ChessPiece pieceToMove;
        boolean successfulMove = false;
        try {
            pieceToMove = this.getPiece(fromPosition);
        } catch (IllegalPositionException e) {
            throw new IllegalMoveException();
        }

        if (pieceToMove == null || pieceToMove.legalMoves() == null) { throw new IllegalMoveException(); }

        if (pieceToMove.legalMoves().contains(toPosition)) {
            successfulMove = this.placePiece(pieceToMove, toPosition);
        }

        if (!successfulMove) { throw new IllegalMoveException(); }

        updateBoard(pieceToMove, toPosition);
    }

    private void updateBoard(ChessPiece piece, String coordinates) {
        board[Character.getNumericValue(coordinates.charAt(1)) - 1][charIntMap.get(coordinates.charAt(0))] = piece;
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
