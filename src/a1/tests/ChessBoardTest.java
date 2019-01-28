package a1.tests;

import a1.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChessBoardTest {
    ChessBoard chessBoard;
    ChessPiece piece;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        chessBoard = new ChessBoard();
        chessBoard.initialize();
        piece = new Rook(chessBoard, ChessPiece.Color.BLACK);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        piece = null;
    }

    @Test
    void testGetPieceInInitialPosition() {
        try {
            Assertions.assertTrue((chessBoard.getPiece("a1")) instanceof Rook);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void initializeMiddleIsNull() {
        ChessPiece piece = null;
        int[] rowIndices = new int[] {
                2,3,4,5
        };
        char[] columnIndices = new char[] {
          'a','b','c','d','e','f','g','h'
        };
        int row;
        char column;

        for (int i = 0; i < rowIndices.length; i++) {
            row = rowIndices[i] + 1;
            for (int j = 0; j < columnIndices.length; j++) {
                column = columnIndices[j];
                try {
                    piece = chessBoard.getPiece(String.valueOf(column) + row);
                    if (piece != null) { break; }
                } catch (IllegalPositionException e) {
                    e.printStackTrace();
                }
            }
        }
        assertSame(null, piece);
    }

    @Test
    void pawnsInitializedCorrectly() {
        ChessPiece piece = null;
        int[] rowIndices = new int[] {
                1,6
        };
        char[] columnIndices = new char[] {
                'a','b','c','d','e','f','g','h'
        };
        int row;
        char column;

        for (int i = 0; i < rowIndices.length; i++) {
            row = rowIndices[i] + 1;
            for (int j = 0; j < columnIndices.length; j++) {
                column = columnIndices[j];
                try {
                    piece = chessBoard.getPiece(String.valueOf(column) + row);
                    if (!(piece instanceof Pawn)) { break; }
                } catch (IllegalPositionException e) {
                    e.printStackTrace();
                }
            }
        }
        assertTrue(piece instanceof Pawn);
    }

    @Test
    void backRowsInitializedCorrectly() {

    }

    @Test
    void testArgIsTwoCharsGet() {
        String position = "abc";
        Assertions.assertThrows(IllegalPositionException.class, () -> chessBoard.getPiece(position));
    }

    @Test
    void testFirstCharIsLetterGet() {
        String position = "11";
        Assertions.assertThrows(IllegalPositionException.class, () -> chessBoard.getPiece(position));
    }

    @Test
    void testFirstCharIsInRangeGet() {
        String position = "j1";
        Assertions.assertThrows(IllegalPositionException.class, () -> chessBoard.getPiece(position));
    }

    @Test
    void testSecondCharIsNumberGet() {
        String position = "hh";
        Assertions.assertThrows(IllegalPositionException.class, () -> chessBoard.getPiece(position));
    }

    @Test
    void testSecondCharIsInRangeGet() {
        String position = "h9";
        Assertions.assertThrows(IllegalPositionException.class, () -> chessBoard.getPiece(position));
    }

    @Test
    void testArgIsTwoCharsSet() {
        String position = "abc";
        Assertions.assertFalse(chessBoard.placePiece(piece, position));
    }

    @Test
    void testFirstCharIsLetterSet() {
        String position = "11";
        Assertions.assertFalse(chessBoard.placePiece(piece, position));
    }

    @Test
    void testFirstCharIsInRangeSet() {
        String position = "j1";
        Assertions.assertFalse(chessBoard.placePiece(piece, position));
    }

    @Test
    void testSecondCharIsNumberSet() {
        String position = "hh";
        Assertions.assertFalse(chessBoard.placePiece(piece, position));
    }

    @Test
    void testSecondCharIsInRangeSet() {
        String position = "h9";
        Assertions.assertFalse(chessBoard.placePiece(piece, position));
    }

    @Test
    void testPieceNotNull() {
        Assertions.assertFalse(chessBoard.placePiece(null, "e4"));
    }

    @Test
    void testPieceOfSameColorInSpace() {
        Pawn secondPiece = new Pawn(chessBoard, ChessPiece.Color.BLACK);
        Assertions.assertFalse(chessBoard.placePiece(secondPiece, "h8"));
    }

    @Test
    void validPiecePlacement() {
        Pawn secondPiece = new Pawn(chessBoard, ChessPiece.Color.BLACK);
        Assertions.assertTrue(chessBoard.placePiece(secondPiece, "e4"));
    }

    @Test
    void testGetPositionStringConversion() {
        String positionString = "h8";
        ChessPiece piece = null;
        try {
            piece = chessBoard.getPiece(positionString);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(piece instanceof Rook);
        assertSame(piece.getColor(), ChessPiece.Color.BLACK);
    }

    @Test
    void testGetPosition2() {
        String positionString = "a2";
        ChessPiece piece = null;
        try {
            piece = chessBoard.getPiece(positionString);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(piece instanceof Pawn);
        assertSame(piece.getColor(), ChessPiece.Color.WHITE);
    }

    @Test
    void assertException_WhenNoPieceToMove() {
        Assertions.assertThrows(IllegalMoveException.class, () -> chessBoard.move("b5", "b6"));
    }

    @Test
    void assertException_PieceIllegalMove() {
        Assertions.assertThrows(IllegalMoveException.class, () -> chessBoard.move("a1", "a3"));
    }

    @Test
    void assertSuccessfulMove() {

    }

}