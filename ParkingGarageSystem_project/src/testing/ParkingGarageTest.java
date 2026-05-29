package testing;

import modules.ParkingGarage;
import modules.Address;
import modules.Ticket;
import exception.ParkingExceptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingGarageTest {

    ParkingGarage garage;

    @BeforeEach
    public void setup() throws ParkingExceptions {
        garage = new ParkingGarage(2, 2);
        Address address = new Address("123 Main", "Fremont", "CA", "94536");
        garage.setAddress(address);
    }

    @Test
    public void testConstructorInitializesLevelsAndCapacity() {
        assertEquals(2, garage.getNumLevels());
        assertEquals(4, garage.getNumAvailable());
        assertNotNull(garage.getEntryKiosk());
        assertNotNull(garage.getExitKiosk());
        assertNotNull(garage.getEntranceBoard());
        assertEquals("94536", garage.getAddress().getZipcode());
    }

    @Test
    public void testAddParkingLevelIncreasesLevelCount() {
        garage.addParkingLevel(3);
        assertEquals(3, garage.getNumLevels());
    }

    @Test
    public void testAddSpacesToLevel() {
        int before = garage
                .getParkingLevels()
                .get(0)
                .getFloorDisplayBoard()
                .getFreeSpaces();

        garage.addSpaces_toLevel(1, 2);

        int after = garage
                .getParkingLevels()
                .get(0)
                .getFloorDisplayBoard()
                .getFreeSpaces();

        assertEquals(before + 2, after);
    }

    @Test
    public void testEnterParkingGarageDecrementsAvailability() throws ParkingExceptions {
        int before = garage.getNumAvailable();
        Ticket t = garage.enterParkingGarage();
        int after = garage.getNumAvailable();
        assertNotNull(t);
        assertEquals(before - 1, after);
    }

    @Test
    public void testEnterParkingGarageThrowsIfFull() {
        try {
            for (int i = 0; i < 4; i++) {
                garage.enterParkingGarage();
            }
        } catch (ParkingExceptions e) {
            fail("Should not have thrown while filling up: " + e.getMessage());
        }
        assertThrows(ParkingExceptions.class, () -> garage.enterParkingGarage());
    }

    @Test
    public void testExitParkingGarageIncrementsAvailability() throws ParkingExceptions {
        garage.enterParkingGarage();
        int before = garage.getNumAvailable();
        boolean result = garage.exitParkingGarage();
        int after = garage.getNumAvailable();
        assertTrue(result);
        assertEquals(before + 1, after);
    }

    @Test
    public void testExitParkingGarageFailsIfEmpty() {
        boolean result = garage.exitParkingGarage();
        assertFalse(result);
    }

    @Test
    public void testDecrementAndIncrementAvailabilityMethods() {
        int before = garage.getNumAvailable();
        garage.decrementAvailablity();
        assertEquals(before - 1, garage.getNumAvailable());

        garage.incrementAvailability();
        assertEquals(before, garage.getNumAvailable());
    }

    @Test
    public void testToStringContainsExpectedInfo() {
        String output = garage.toString();
        assertTrue(output.contains("Parking Garage"));
        assertTrue(output.contains("Number of Levels:"));
        assertTrue(output.contains("Address"));
    }
}
