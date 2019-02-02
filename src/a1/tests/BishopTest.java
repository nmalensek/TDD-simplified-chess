package a1.tests;

import a1.Bishop;
import a1.ChessBoard;
import a1.ChessPiece;
import a1.IllegalPositionException;
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
    @ValueSource(strings = {"abc", "11", "j1", "hh", "h9"})
    void testIllegalPositions(String position) {
        Assertions.assertThrows(IllegalPositionException.class, () -> whiteBishop.setPosition(position));
    }

    @Test
    void assertSuccessfulSetPositionWithEmptyBoard() {
        String positionString = "h8";
        try {
            whiteBishop.setPosition(positionString);
        } catch (IllegalPositionException e) {
            fail();
        }
        Assertions.assertEquals(positionString, whiteBishop.getPosition());
    }

    @ParameterizedTest
    @ValueSource(strings = {"g4, e4"})
    void assertExceptionOnNonDiagonalMove() {

    }

    @Test
    void assertNoLegalMovesAtStartOfGame() {

    }
}