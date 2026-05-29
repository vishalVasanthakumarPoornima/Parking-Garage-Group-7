package testing;

import modules.Ticket;
import modules.TicketStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import personel.ParkingAttendant;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingAttendantTest {

    ParkingAttendant attendant;

    @BeforeEach
    public void setup() {
        attendant = new ParkingAttendant("John", "paUser", "paPass");
    }

    @Test
    public void testGetNameReturnsCorrectName() {
        assertEquals("John", attendant.getName());
    }

    @Test
    public void testSetNameChangesName() {
        attendant.setName("NewName");
        assertEquals("NewName", attendant.getName());
    }

    @Test
    public void testGetParkingAttendantIDIsPrefixedCorrectly() {
        assertTrue(attendant.getParkingAttendantID().startsWith("PA"));
    }

    @Test
    public void testGetUsernameReturnsCorrectValue() {
        assertEquals("paUser", attendant.getUsername());
    }

    @Test
    public void testHandlePaymentOnNullTicket() {
        assertDoesNotThrow(() -> attendant.handlePayment(null));
    }

    @Test
    public void testHandlePaymentOnValidTicket() {
        Ticket ticket = new Ticket();
        assertDoesNotThrow(() -> attendant.handlePayment(ticket));
        assertEquals(TicketStatus.PAID, ticket.getParkingTicketStatus());
    }

}
