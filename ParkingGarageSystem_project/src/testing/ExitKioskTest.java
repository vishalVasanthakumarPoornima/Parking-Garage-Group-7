package testing;

import modules.ExitKiosk;
import modules.Gate;
import modules.ParkingGarage;
import modules.Ticket;
import modules.TicketStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.ParkingExceptions;

import static org.junit.jupiter.api.Assertions.*;

public class ExitKioskTest {

    ExitKiosk kiosk;
    Ticket activeTicket;
    Ticket inactiveTicket;
    ParkingGarage garage;

    @BeforeEach
    public void setup() throws ParkingExceptions {
        kiosk = new ExitKiosk();
        garage = new ParkingGarage(2, 2);
        kiosk.setParkingGarage(garage);
        activeTicket = new Ticket();
        inactiveTicket = new Ticket();
        inactiveTicket.setParkingTicketStatus(TicketStatus.PAID);
    }

    @Test
    public void testConstructorInitializesGateAndId() {
        assertNotNull(kiosk.getGate());
        assertNotNull(kiosk.getId());
    }

    @Test
    public void testScanTicketReturnsTrueForActiveTicket() {
        assertTrue(kiosk.scanTicket(activeTicket));
    }

    @Test
    public void testScanTicketReturnsFalseForInactiveTicket() {
        assertFalse(kiosk.scanTicket(inactiveTicket));
    }

    @Test
    public void testScanTicketReturnsFalseForNullTicket() {
        assertFalse(kiosk.scanTicket(null));
    }

    @Test
    public void testScanTicketToExitReturnsFeeForActiveTicket() {
        double fee = kiosk.scanTicket_toExit(activeTicket);
        assertTrue(fee >= 0);
    }

    @Test
    public void testScanTicketToExitReturnsNegativeForInactiveTicket() {
        assertEquals(-1, kiosk.scanTicket_toExit(inactiveTicket));
    }

    @Test
    public void testScanTicketToExitReturnsNegativeForNullTicket() {
        assertEquals(-1, kiosk.scanTicket_toExit(null));
    }

    @Test
    public void testOpenCloseGateDoesNotThrow() {
        assertDoesNotThrow(() -> kiosk.openGate());
        assertDoesNotThrow(() -> kiosk.closeGate());
    }

    @Test
    public void testSetAndGetId() {
        kiosk.setId("EXIT001");
        assertEquals("EXIT001", kiosk.getId());
    }

    @Test
    public void testSetAndGetGate() {
        Gate g = new Gate();
        kiosk.setGate(g);
        assertEquals(g, kiosk.getGate());
    }

    @Test
    public void testSetAndGetParkingGarage() throws ParkingExceptions {
        ParkingGarage pg = new ParkingGarage(2, 2);
        kiosk.setParkingGarage(pg);
        assertEquals(pg, kiosk.getParkingGarage());
    }

    @Test
    public void testToStringReturnsId() {
        assertEquals(kiosk.getId(), kiosk.toString());
    }
}
