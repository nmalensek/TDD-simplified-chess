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

    @BeforeEach
    void setUp() {
        board = new ChessBoard();
        blackQueen = new Queen(board, ChessPiece.Color.BLACK);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void assertNoLegalMoves() {
        Assertions.assertTrue(blackQueen.legalMoves().isEmpty());
    }
}