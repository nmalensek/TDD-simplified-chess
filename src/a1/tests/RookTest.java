package a1.tests;

import a1.ChessBoard;
import a1.IllegalPositionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {
    ChessBoard board;

    @BeforeEach
    void setUp() {
        board = new ChessBoard();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void assertNoLegalMovesAtStartOfGame() {
        board.initialize();
        try {
            assertTrue(board.getPiece("h1").legalMoves().isEmpty());
            assertTrue(board.getPiece("a1").legalMoves().isEmpty());
            assertTrue(board.getPiece("h8").legalMoves().isEmpty());
            assertTrue(board.getPiece("a8").legalMoves().isEmpty());
        } catch (IllegalPositionException e) {
            fail();
        }
    }

    @Test
    void assertExceptionOnDiagonalMove() {

    }

    @Test
    void assertExceptionIfTryToCaptureOwnPiece() {

    }

    @Test
    void assertCannotJumpOtherPieces() {

    }

    @Test
    void assertCanMoveUnlimitedSpacesStraight() {

    }
}