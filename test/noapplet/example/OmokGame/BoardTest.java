package noapplet.example.OmokGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Player p1 = new Player("Boba");
    Player p2 = new Player("Djin");
    Board fullBoard = new Board(15);
    Board blankBoard = new Board(15);

    @BeforeEach
    void setUp() {
        int size = 15;
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fullBoard.placeStone(i,j,p1);
            }
        }
    }

    @Test
    void size() {
        Board board = new Board(15);
        assertEquals(board.size(), 15);
        assertNotEquals(board.size(), 20);
    }

    @Test
    void clear() {
        fullBoard.clear();
        assertFalse(blankBoard.isFull());
    }

    @Test
    void isFull() {
        assertTrue(fullBoard.isFull());
        assertFalse(blankBoard.isFull());
    }

    @Test
    void placeStone() {
        Board board = new Board(15);
        board.placeStone(10, 5, p1);
        board.placeStone(7, 8, p2);
        board.placeStone(4, 9, p1);
        board.placeStone(12, 11, p2);
        assertTrue(board.isOccupiedBy(10, 5, p1));
        assertTrue(board.isOccupiedBy(12, 11, p2));
        assertFalse(board.isOccupiedBy(7, 3, p1));
        assertFalse(board.isOccupiedBy(3, 14, p2));
    }

    @Test
    void isEmpty() {
        Board board = new Board(15);
        board.placeStone(5,5,p1);
        assertTrue(board.isEmpty(1,3));
        assertFalse(board.isEmpty(5,5));
    }

    @Test
    void isOccupied() {
        Board board = new Board(15);
        String Name1 = "seth";
        String Name2 = "Matt";
        board.placeStone(1, 3, p1);
        board.placeStone(5, 2, p2);
        assertFalse(board.isOccupied(4, 7));
        assertFalse(board.isOccupied(5, 1));
        assertFalse(board.isOccupied(14, 0));
        assertTrue(board.isOccupied(1, 3));
        assertTrue(board.isOccupied(5, 2));
    }

    @Test
    void isOccupiedBy() {
        Board board = new Board(15);
        String Name1 = "al";
        String Name2 = "Von";
        board.placeStone(10, 5, p1);
        board.placeStone(7, 8, p2);
        board.placeStone(4, 9, p1);
        board.placeStone(12, 11, p2);
        assertTrue(board.isOccupiedBy(10, 5, p1));
        assertTrue(board.isOccupiedBy(12, 11, p2));
        assertFalse(board.isOccupiedBy(7, 3, p1));
        assertFalse(board.isOccupiedBy(3, 14, p2));
    }

    @Test
    void playerAt() {

        Board board = new Board(15);
        board.placeStone(2, 12, p1);
        board.placeStone(11, 3, p2);
        board.placeStone(9, 7, p1);
        board.placeStone(5, 1, p2);
        assertEquals(p1, board.playerAt(2, 12));
        assertEquals(p2, board.playerAt(11, 3));
        assertEquals(p1, board.playerAt(9, 7));
        assertEquals(p2, board.playerAt(5, 1));
    }

    @Test
    void isWonBy() {
        Board board = new Board(15);
        board.placeStone(0, 0, p1);
        board.placeStone(1, 1, p1);
        board.placeStone(2, 2, p1);
        board.placeStone(3, 3, p1);
        board.placeStone(4, 4, p1);
        assertTrue(board.isWonBy(p1));

    }
}