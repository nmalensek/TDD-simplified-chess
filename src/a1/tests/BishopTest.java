package a1.tests;

import a1.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnitPlatform.class)
public class BishopTest {
    private ChessBoard board;
    private Bishop whiteBishop;
    private Bishop blackBishop;

    @BeforeEach
    void setUp() {
        board = new ChessBoard();
        whiteBishop = new Bishop(board, ChessPiece.Color.WHITE);
        blackBishop = new Bishop(board, ChessPiece.Color.BLACK);
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest
    @ValueSource(strings = {"g4, e4"})
    void assertExceptionOnNonDiagonalMove(String position) {
        board.placePiece(whiteBishop, "f4");
        Assertions.assertThrows(IllegalMoveException.class, () -> board.move("f4", position));
    }

    @ParameterizedTest
    @ValueSource(strings = {"h1","b1","a8", "f5"})
    void assertCanMoveUnlimitedDiagonalSpaces(String newPosition) {
        board.placePiece(whiteBishop, "e4");
        try {
            board.move("e4", newPosition);
            assertTrue(board.getPiece(newPosition) instanceof Bishop);
            assertNull(board.getPiece("e4"));
        } catch (IllegalMoveException | IllegalPositionException e) {
            fail();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"d3","a4"})
    void assertNoLegalMovesAtStartOfGame(String newPosition) {
        board.initialize();
        Assertions.assertThrows(IllegalMoveException.class, () -> board.move("c1", newPosition));
    }

    @ParameterizedTest
    @ValueSource(strings = {"b1", "c6", "g6"})
    void assertCannotJumpOtherPieces(String newPosition) {
        board.placePiece(whiteBishop, "e4");
        board.placePiece(new Pawn(board, ChessPiece.Color.WHITE), "c2");
        board.placePiece(new Pawn(board, ChessPiece.Color.WHITE), "d5");
        board.placePiece(new Pawn(board, ChessPiece.Color.WHITE), "f5");

        Assertions.assertEquals(4, whiteBishop.legalMoves().size());
        Assertions.assertThrows(IllegalMoveException.class, () -> board.move("e4", newPosition));
    }

    @Test
    void assertCanOnlyCapturePiecesOfOppositeColor() {
        board.placePiece(whiteBishop, "e4");
        board.placePiece(new Pawn(board, ChessPiece.Color.BLACK), "c2");
        board.placePiece(new Pawn(board, ChessPiece.Color.WHITE), "b3");

        try {
            board.move("e4", "c2");
            assertTrue(board.getPiece("c2") instanceof Bishop);
            assertNull(board.getPiece("e4"));
        } catch (IllegalMoveException | IllegalPositionException e) {
            fail();
        }

        Assertions.assertThrows(IllegalMoveException.class, () -> board.move("c2", "b3"));
    }
}