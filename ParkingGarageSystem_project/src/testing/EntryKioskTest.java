package testing;

import modules.EntryKiosk;
import modules.Gate;
import modules.ParkingGarage;
import modules.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.ParkingExceptions;

import static org.junit.jupiter.api.Assertions.*;

public class EntryKioskTest {

    EntryKiosk kiosk;
    ParkingGarage garage;

    @BeforeEach
    public void setup() throws ParkingExceptions {
        kiosk = new EntryKiosk();
        garage = new ParkingGarage(2, 2);
        kiosk.setParkingGarage(garage);
    }

    @Test
    public void testConstructorInitializesGateAndId() {
        assertNotNull(kiosk.getGate());
        assertNotNull(kiosk.getId());
    }

    @Test
    public void testPrintTicketReturnsNonNullTicket() throws ParkingExceptions {
        Ticket t = kiosk.printTicket();
        assertNotNull(t);
    }

    @Test
    public void testOpenAndCloseGateDelegation() {
        assertDoesNotThrow(() -> kiosk.openGate());
        assertDoesNotThrow(() -> kiosk.closeGate());
    }

    @Test
    public void testSetAndGetId() {
        kiosk.setId("ENTRY001");
        assertEquals("ENTRY001", kiosk.getId());
    }

    @Test
    public void testSetAndGetGate() {
        Gate newGate = new Gate();
        kiosk.setGate(newGate);
        assertEquals(newGate, kiosk.getGate());
    }

    @Test
    public void testSetAndGetParkingGarage() throws ParkingExceptions {
        ParkingGarage pg = new ParkingGarage(1, 1);
        kiosk.setParkingGarage(pg);
        assertEquals(pg, kiosk.getParkingGarage());
    }

    @Test
    public void testGetParkingGarageInitiallyNull() {
        EntryKiosk tempKiosk = new EntryKiosk();
        assertNull(tempKiosk.getParkingGarage());
    }

    @Test
    public void testToStringReturnsId() {
        String id = kiosk.getId();
        assertEquals(id, kiosk.toString());
    }
}
