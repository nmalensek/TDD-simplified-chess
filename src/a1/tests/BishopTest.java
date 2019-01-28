package a1.tests;

import a1.Bishop;
import a1.ChessBoard;
import a1.ChessPiece;
import a1.IllegalPositionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    @Test
    void testToString() {
    }

    @Test
    void testLegalMoves() {
    }

    @Test
    void testArgIsTwoChars() {
        String position = "abc";
        Assertions.assertThrows(IllegalPositionException.class, () -> whiteBishop.setPosition(position));
    }

    @Test
    void testFirstCharIsLetter() {
        String position = "11";
        Assertions.assertThrows(IllegalPositionException.class, () -> whiteBishop.setPosition(position));
    }

    @Test
    void testFirstCharIsInRange() {
        String position = "j1";
        Assertions.assertThrows(IllegalPositionException.class, () -> whiteBishop.setPosition(position));
    }

    @Test
    void testSecondCharIsNumber() {
        String position = "hh";
        Assertions.assertThrows(IllegalPositionException.class, () -> whiteBishop.setPosition(position));
    }

    @Test
    void testSecondCharIsInRange() {
        String position = "h9";
        Assertions.assertThrows(IllegalPositionException.class, () -> whiteBishop.setPosition(position));
    }

    @Test
    void testGetPositionStringConversion() {
        String positionString = "h8";
        try {
            whiteBishop.setPosition(positionString);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(positionString, whiteBishop.getPosition());
    }

    @Test
    void testGetPosition2() {
        String positionString = "a2";
        try {
            whiteBishop.setPosition(positionString);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(positionString, whiteBishop.getPosition());
    }
}