package testing;

import modules.LevelDisplayBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LevelDisplayBoardTest {

    @Test
    public void testConstructorInitializesFreeSpaces() {
        LevelDisplayBoard board = new LevelDisplayBoard(10);
        assertEquals(10, board.getFreeSpaces());
    }

    @Test
    public void testSetAndGetFreeSpaces() {
        LevelDisplayBoard board = new LevelDisplayBoard(5);
        board.setFreeSpaces(3);
        assertEquals(3, board.getFreeSpaces());
    }

    @Test
    public void testOccupySpaceReducesFreeSpacesByOne() {
        LevelDisplayBoard board = new LevelDisplayBoard(5);
        board.occupySpace();
        assertEquals(4, board.getFreeSpaces());
    }

    @Test
    public void testLeaveSpaceIncreasesFreeSpacesByOne() {
        LevelDisplayBoard board = new LevelDisplayBoard(2);
        board.leaveSpace();
        assertEquals(3, board.getFreeSpaces());
    }
}
