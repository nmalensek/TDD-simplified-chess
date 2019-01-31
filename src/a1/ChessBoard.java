package a1;

import java.util.ArrayList;

public class ChessBoard {
    private ChessPiece[][] board;

    private int ILLEGAL_COLUMN = -1;

    private static final char[] columnCharArray = {
            'a','b','c','d','e','f','g','h'
    };

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
            placePiece(leftRook, 'a' + firstRowCoordinate);
            Knight leftKnight = new Knight(this, color);
            placePiece(leftKnight, 'b' + firstRowCoordinate);
            Bishop leftBishop = new Bishop(this, color);
            placePiece(leftBishop, 'c' + firstRowCoordinate);
            Queen queen = new Queen(this, color);
            placePiece(queen, 'd' + firstRowCoordinate);
            King king = new King(this, color);
            placePiece(king, 'e' + firstRowCoordinate);
            Bishop rightBishop = new Bishop(this, color);
            placePiece(rightBishop, 'f' + firstRowCoordinate);
            Knight rightKnight = new Knight(this, color);
            placePiece(rightKnight, 'g' + firstRowCoordinate);
            Rook rightRook = new Rook(this, color);
            placePiece(rightRook, 'h' + firstRowCoordinate);

            for (int i = 0; i < board[0].length; i++) {
                Pawn pawn = new Pawn(this, color);
                placePiece(pawn, columnCharArray[i] + secondRowCoordinate);
            }

        }
    }

    public ChessPiece getPiece(String position) throws IllegalPositionException {
        if (!inputCoordinatesValid(position)) { throw new IllegalPositionException(); }

        int row = Character.getNumericValue(position.charAt(1)) - 1;
        int column = convertCharToInt(position.charAt(0));

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

        //placement's fine, update the board.
        board[Character.getNumericValue(position.charAt(1)) - 1][convertCharToInt(position.charAt(0))] = piece;
        return true;
    }

    public void move(String fromPosition, String toPosition) throws IllegalMoveException {
        ChessPiece pieceToMove;
        ArrayList<String> pieceMoves;
        boolean successfulMove = false;
        try {
            pieceToMove = this.getPiece(fromPosition);
        } catch (IllegalPositionException e) {
            throw new IllegalMoveException();
        }

        if (pieceToMove == null) { throw new IllegalMoveException(); }

        pieceMoves = pieceToMove.legalMoves();

        if (pieceMoves == null || pieceMoves.size() == 0) { throw new IllegalMoveException(); }

        if (pieceMoves.contains(toPosition)) {
            successfulMove = this.placePiece(pieceToMove, toPosition);
        }

        if (!successfulMove) { throw new IllegalMoveException(); }

        board[Character.getNumericValue(fromPosition.charAt(1)) - 1][convertCharToInt(fromPosition.charAt(0))] = null;
    }

    private boolean inputCoordinatesValid(String coordinates) {
        if (coordinates.length() != 2) { return false; }

        //check column
        if (convertCharToInt(coordinates.charAt(0)) == ILLEGAL_COLUMN) {
            return false;
        }

        //check row
        if (Character.getNumericValue(coordinates.charAt(1)) < 1 ||
                Character.getNumericValue(coordinates.charAt(1)) > 8) {
            return false;
        }

        return true;
    }

    private char convertIntToChar(int number) {
        return Character.toChars(number + 'a')[0];
    }

    private int convertCharToInt(char letter) {
        if (letter - 'a' > 7 || letter - 'a' < 0) { return ILLEGAL_COLUMN; }
        return letter - 'a';
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
