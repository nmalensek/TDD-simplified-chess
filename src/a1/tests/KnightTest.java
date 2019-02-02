package a1.tests;

import a1.ChessBoard;
import a1.ChessPiece;
import a1.IllegalMoveException;
import a1.Knight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
class KnightTest {
    private Knight blackKnight;
    private Knight whiteKnight;
    private ChessBoard board;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        board = new ChessBoard();
        blackKnight = new Knight(board, ChessPiece.Color.BLACK);
        whiteKnight = new Knight(board, ChessPiece.Color.WHITE);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        blackKnight = null;
        whiteKnight = null;
    }

    @Test
    void testToStringBlack() {
        String expected = "\u265E";
        Assertions.assertEquals(expected, blackKnight.toString());
    }

    @Test
    void testToStringWhite() {
        String expected = "\u2658";
        Assertions.assertEquals(expected, whiteKnight.toString());
    }

    @Test
    void assertNoLegalMoves() {
        Assertions.assertTrue(whiteKnight.legalMoves().isEmpty());
    }

    @Test
    void assertNoMovesAllowed() {
        board.placePiece(blackKnight, "d6");
        Assertions.assertThrows(IllegalMoveException.class, () -> board.move("d6", "d5"));
    }
}