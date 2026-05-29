package testing;

import exception.ParkingExceptions;
import modules.ParkingLevel;
import modules.ParkingSpace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLevelTest {

    ParkingLevel level;

    @BeforeEach
    public void setup() {
        level = new ParkingLevel(1, 3);
    }

    @Test
    public void testConstructorInitializesFieldsCorrectly() {
        assertEquals(1, level.getLevelNumber());
        assertEquals(3, level.getParkingSpaces().size());
        assertEquals(3, level.getFloorDisplayBoard().getFreeSpaces());
    }

    @Test
    public void testOccupyAvailableSpaceOccupiesFirstFreeSpot() {
        ParkingSpace space = level.occupyAvailableSpace();
        assertNotNull(space);
        assertTrue(space.isOccupied());
        assertEquals(2, level.getFloorDisplayBoard().getFreeSpaces());
    }

    @Test
    public void testOccupyAvailableSpaceReturnsNullWhenFull() {
        level.occupyAvailableSpace();
        level.occupyAvailableSpace();
        level.occupyAvailableSpace();
        assertNull(level.occupyAvailableSpace());
    }

    @Test
    public void testAddParkingSpaceIncreasesSpaceCount() {
        int before = level.getParkingSpaces().size();
        level.addParkingSpace();
        int after = level.getParkingSpaces().size();
        assertEquals(before + 1, after);
    }

    @Test
    public void testFreeOccupiedSpaceFreesAnOccupiedSlot() {
        level.occupyAvailableSpace();
        int before = level.getFloorDisplayBoard().getFreeSpaces();
        boolean result = level.freeOccupiedSpace();
        int after = level.getFloorDisplayBoard().getFreeSpaces();
        assertTrue(result);
        assertEquals(before + 1, after);
    }

    @Test
    public void testFreeOccupiedSpaceReturnsFalseIfNoneAreOccupied() {
        assertFalse(level.freeOccupiedSpace());
    }

    @Test
    public void testGetParkingSpaceByNumReturnsCorrectObject() {
        ParkingSpace space = level.getParkingSpaceByNum(1);
        assertNotNull(space);
        assertEquals(1, space.getSpace_number());
    }

    @Test
    public void testGetParkingSpaceReturnsSameReference() {
        ParkingSpace s1 = level.getParkingSpaces().get(0);
        ParkingSpace s2 = level.getParkingSpace(s1);
        assertSame(s1, s2);
    }

    @Test
    public void testSetLevelNumberValid() throws ParkingExceptions {
        level.setLevelNumber(5);
        assertEquals(5, level.getLevelNumber());
    }

    @Test
    public void testSetLevelNumberThrowsOnNegative() {
        assertThrows(ParkingExceptions.class, () -> level.setLevelNumber(-1));
    }

    @Test
    public void testFreeSpaceByNumberFreesSlot() {
        ParkingSpace s = level.occupyAvailableSpace();
        int num = s.getSpace_number();
        level.freeSpace(num);
        assertFalse(s.isOccupied());
    }

    @Test
    public void testFreeSpaceByReferenceFreesSlot() {
        ParkingSpace s = level.occupyAvailableSpace();
        level.freeSpace(s);
        assertFalse(s.isOccupied());
    }

    @Test
    public void testToStringIncludesLevelInfo() {
        String output = level.toString();
        assertTrue(output.contains("Parking Level number:"));
        assertTrue(output.contains("Number of Parking Spaces:"));
    }
}
