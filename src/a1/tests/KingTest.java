package a1.tests;

import a1.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {
    ChessBoard board;
    King whiteKing;

    @BeforeEach
    void setUp() {
        board = new ChessBoard();
        whiteKing = new King(board, ChessPiece.Color.WHITE);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void assertNoLegalMovesAtStartOfGame() {
        board.initialize();
        try {
            assertTrue(board.getPiece("e1").legalMoves().isEmpty());
        } catch (IllegalPositionException e) {
            fail();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"e3","e5","f3","f4","f5","d3","d4","d5"})
    void assertCanMoveOneSpaceHorizontalOrDiagonal(String newPosition) {
        board.placePiece(whiteKing, "e4");

        try {
            board.move("e4", newPosition);
        } catch (IllegalMoveException e) {
            fail();
        }
    }

    @Test
    void assertCanOnlyCaptureOpponentPieces() {
        Pawn blackPawn = new Pawn(board, ChessPiece.Color.BLACK);
        board.placePiece(whiteKing, "e4");

        board.placePiece(new Pawn(board, ChessPiece.Color.WHITE), "e3");
        board.placePiece(blackPawn, "e5");

        Assertions.assertThrows(IllegalMoveException.class, () -> board.move("e4","e3"));

        try {
            board.move("e4","e5");
        } catch (IllegalMoveException e) {
            fail();
        }

        try {
            assertEquals(whiteKing, board.getPiece("e5"));
            assertEquals(null, board.getPiece("e4"));
        } catch (IllegalPositionException e) {
            fail();
        }
    }

    @Test
    void assertCannotMoveOffBoard() {
        board.placePiece(whiteKing, "e1");

        Assertions.assertThrows(IllegalMoveException.class, () -> board.move("e1", "e0"));
    }
}