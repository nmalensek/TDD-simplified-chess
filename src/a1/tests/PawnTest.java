package a1.tests;

import a1.ChessBoard;
import a1.ChessPiece;
import a1.Pawn;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {
    ChessBoard board;
    Pawn whitePawn;
    Pawn blackPawn;

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

    }

    @Test
    void assertCanMoveOneSpaceFromStartPosition() {

    }

    @ParameterizedTest
    @ValueSource(strings = {"c6, d4, d5"})
    void assertExceptionIfNotMovingOneSpaceForwardInLaterPosition(String position) {

    }
}