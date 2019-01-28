package a1.tests;

import a1.ChessBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChessBoardTest {
    ChessBoard chessBoard;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        chessBoard = new ChessBoard();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    void testInitialize() {

    }

}