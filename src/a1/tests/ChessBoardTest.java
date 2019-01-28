package a1.tests;

import a1.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChessBoardTest {
    ChessBoard chessBoard;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        chessBoard = new ChessBoard();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    void testInitialize() {
        chessBoard.initialize();
    }

    @Test
    void testGetPieceInInitialPosition() {

    }

    @Test
    void testArgIsTwoChars() {
        String position = "abc";
        Assertions.assertThrows(IllegalPositionException.class, () -> chessBoard.getPiece(position));
    }

    @Test
    void testFirstCharIsLetter() {
        String position = "11";
        Assertions.assertThrows(IllegalPositionException.class, () -> chessBoard.getPiece(position));
    }

    @Test
    void testFirstCharIsInRange() {
        String position = "j1";
        Assertions.assertThrows(IllegalPositionException.class, () -> chessBoard.getPiece(position));
    }

    @Test
    void testSecondCharIsNumber() {
        String position = "hh";
        Assertions.assertThrows(IllegalPositionException.class, () -> chessBoard.getPiece(position));
    }

    @Test
    void testSecondCharIsInRange() {
        String position = "h9";
        Assertions.assertThrows(IllegalPositionException.class, () -> chessBoard.getPiece(position));
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
}