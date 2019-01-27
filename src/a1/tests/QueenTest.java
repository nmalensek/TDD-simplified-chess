package a1.tests;

import a1.ChessBoard;
import a1.ChessPiece;
import a1.Queen;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
class QueenTest {
    private ChessBoard board;
    private Queen blackQueen;
    private Queen whiteQueen;

    @BeforeEach
    void setUp() {
        board = new ChessBoard();
        blackQueen = new Queen(board, ChessPiece.Color.BLACK);
        whiteQueen = new Queen(board, ChessPiece.Color.WHITE);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testToString() {

    }

    @Test
    void legalMoves() {
        Assertions.assertTrue(blackQueen.legalMoves().isEmpty());
    }
}