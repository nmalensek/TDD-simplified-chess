package a1.tests;

import a1.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {
    ChessBoard board;

    @BeforeEach
    void setUp() {
        board = new ChessBoard();
        board.initialize();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void assertCanMoveTwoSpacesFromStartPosition() {
        try {
            board.move("c7","c5");
            board.move("e2","e4");
        } catch (IllegalMoveException e) {
            fail();
        }
        try {
            assertNull(board.getPiece("c7"));
            assertNull(board.getPiece("e2"));
            assertTrue(board.getPiece("c5") instanceof Pawn);
            assertTrue(board.getPiece("e4") instanceof Pawn);
        } catch (IllegalPositionException e) {
            fail();
        }
    }

    @Test
    void assertCanMoveOneSpaceFromStartPosition() {
        try {
            board.move("c7","c6");
            board.move("e2","e3");
        } catch (IllegalMoveException e) {
            fail();
        }
        try {
            assertNull(board.getPiece("c7"));
            assertNull(board.getPiece("e2"));
            assertTrue(board.getPiece("c6") instanceof Pawn);
            assertTrue(board.getPiece("e3") instanceof Pawn);
        } catch (IllegalPositionException e) {
            fail();
        }
    }

    @Test
    void assertExceptionIfNotMovingOneSpaceForwardInLaterPosition() {
        try {
            board.move("c7", "c5");
        } catch (IllegalMoveException e) {
            fail();
        }
        assertThrows(IllegalMoveException.class, () -> board.move("c5", "c3"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"c6","b5","b6","d5","d6"})
    void assertCanOnlyMoveForward(String position) {
        try {
            board.move("c7","c5");
        } catch (IllegalMoveException e) {
            fail();
        }

        assertThrows(IllegalMoveException.class, () -> board.move("c5", position));
    }

    @Test
    void assertCanCaptureOnDiagonal() {
        board.placePiece(new Pawn(board, ChessPiece.Color.WHITE), "d6");

        try {
            board.move("c7", "d6");
        } catch (IllegalMoveException e) {
            fail();
        }
        try {
            assertTrue(board.getPiece("d6") instanceof Pawn);
            assertNull(board.getPiece("c7"));
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
    }
}