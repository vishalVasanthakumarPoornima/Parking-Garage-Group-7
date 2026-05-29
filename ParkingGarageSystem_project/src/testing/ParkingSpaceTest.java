package testing;

import modules.ParkingSpace;
import exception.ParkingExceptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingSpaceTest {

    @Test
    public void testConstructorInitializesCorrectly() {
        ParkingSpace space = new ParkingSpace(10);
        assertEquals(10, space.getSpace_number());
        assertFalse(space.isOccupied());
    }

    @Test
    public void testSetSpaceNumberAcceptsValidValue() throws ParkingExceptions {
        ParkingSpace space = new ParkingSpace(5);
        space.setSpace_number(15);
        assertEquals(15, space.getSpace_number());
    }

    @Test
    public void testSetSpaceNumberThrowsOnNegative() {
        ParkingSpace space = new ParkingSpace(5);
        assertThrows(ParkingExceptions.class, () -> space.setSpace_number(-1));
    }

    @Test
    public void testOccupyAndFreeSpaceChangesState() {
        ParkingSpace space = new ParkingSpace(1);
        space.setOccupiedSpace();
        assertTrue(space.isOccupied());
        space.setFreeSpace();
        assertFalse(space.isOccupied());
    }

    @Test
    public void testToStringReflectsStatus() {
        ParkingSpace space = new ParkingSpace(7);
        String output = space.toString();
        assertTrue(output.contains("Parking Space number: 7"));
        assertTrue(output.contains("Free"));
        space.setOccupiedSpace();
        assertTrue(space.toString().contains("Occupied"));
    }

    @Test
    public void testEqualsReturnsTrueForIdenticalObjects() throws ParkingExceptions {
        ParkingSpace s1 = new ParkingSpace(1);
        ParkingSpace s2 = new ParkingSpace(1);
        s2.setSpace_number(s1.getSpace_number());
        assertNotEquals(s1, s2);
        assertEquals(s1, s1);
    }

    @Test
    public void testEqualsReturnsFalseForDifferentTypes() {
        ParkingSpace s1 = new ParkingSpace(1);
        assertNotEquals(s1, "not a space");
    }
}
