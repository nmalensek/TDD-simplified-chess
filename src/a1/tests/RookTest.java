package a1.tests;

import a1.*;
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
        board.placePiece(new Rook(board, ChessPiece.Color.BLACK), "d4");
        assertThrows(IllegalMoveException.class, () -> board.move("d4", "f6"));
        assertThrows(IllegalMoveException.class, () -> board.move("d4", "a1"));
    }

    @Test
    void assertExceptionIfTryToCaptureOwnPiece() {
        board.placePiece(new Rook(board, ChessPiece.Color.BLACK), "d4");
        board.placePiece(new Pawn(board, ChessPiece.Color.BLACK), "g4");
        assertThrows(IllegalMoveException.class, () -> board.move("d4", "g4"));
    }

    @Test
    void assertCanCaptureOtherPieces() {
        board.placePiece(new Rook(board, ChessPiece.Color.BLACK), "d4");
        board.placePiece(new Pawn(board, ChessPiece.Color.WHITE), "g4");
        try {
            board.move("d4", "g4");
        } catch (IllegalMoveException e) {
            fail();
        }
        try {
            assertTrue(board.getPiece("g4") instanceof Rook);
            assertNull(board.getPiece("d4"));
        } catch (IllegalPositionException e) {
            fail();
        }
    }

    @Test
    void assertCannotJumpOtherPieces() {
        board.placePiece(new Rook(board, ChessPiece.Color.BLACK), "d4");
        board.placePiece(new Pawn(board, ChessPiece.Color.BLACK), "e4");
        assertThrows(IllegalMoveException.class, () -> board.move("d4", "g4"));
    }

    @Test
    void assertCanMoveUnlimitedSpacesStraight() {
        board.placePiece(new Rook(board, ChessPiece.Color.BLACK), "d4");
        try {
            board.move("d4","d8");
            assertNull(board.getPiece("d4"));
            assertTrue(board.getPiece("d8") instanceof Rook);
            board.move("d8","h8");
            assertNull(board.getPiece("d8"));
            assertTrue(board.getPiece("h8") instanceof Rook);
        } catch (IllegalMoveException | IllegalPositionException e) {
            fail();
        }
    }

    @Test
    void assertBlockedByOtherPieces() {
        board.placePiece(new Rook(board, ChessPiece.Color.BLACK), "d4");
        board.placePiece(new Pawn(board, ChessPiece.Color.BLACK), "d2");
        board.placePiece(new Pawn(board, ChessPiece.Color.BLACK), "b4");
        board.placePiece(new Pawn(board, ChessPiece.Color.BLACK), "d6");
        board.placePiece(new Pawn(board, ChessPiece.Color.BLACK), "f4");
        try {
            assertEquals(4, board.getPiece("d4").legalMoves().size());
        } catch (IllegalPositionException e) {
            fail();
        }
    }
}