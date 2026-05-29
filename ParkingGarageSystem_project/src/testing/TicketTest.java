package testing;

import modules.Ticket;
import modules.TicketStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class TicketTest {

    Ticket ticket;

    @BeforeEach
    public void setup() {
        ticket = new Ticket();
    }

    @Test
    public void testConstructorInitializesCorrectly() {
        assertNotNull(ticket.getTicketID());
        assertEquals(0.0, ticket.getTotalCharge());
        assertEquals(TicketStatus.ACTIVE, ticket.getParkingTicketStatus());
        assertNotNull(ticket.getEntryTime());
    }

    @Test
    public void testSetAndGetTicketID() {
        ticket.setTicketID("T100");
        assertEquals("T100", ticket.getTicketID());
    }

    @Test
    public void testSetAndGetIssuedTime() {
        LocalTime time = LocalTime.of(10, 15);
        ticket.setIssuedTime(time);
        assertEquals(time, ticket.getEntryTime());
    }

    @Test
    public void testSetAndGetPayoutTime() {
        LocalTime time = LocalTime.of(11, 45);
        ticket.setPayoutTime(time);
        assertEquals(time, ticket.getPayoutTime());
    }

    @Test
    public void testSetAndGetTotalCharge() {
        ticket.setTotalCharge(12.5);
        assertEquals(12.5, ticket.getTotalCharge());
    }

    @Test
    public void testSetAndGetParkingTicketStatus() {
        ticket.setParkingTicketStatus(TicketStatus.PAID);
        assertEquals(TicketStatus.PAID, ticket.getParkingTicketStatus());
    }

    @Test
    public void testSetPaidUpdatesStatus() {
        ticket.setPaid();
        assertEquals(TicketStatus.PAID, ticket.getParkingTicketStatus());
    }

    @Test
    public void testCalculateFeeComputesNonNegativeValue() {
        ticket.setIssuedTime(LocalTime.now().minusMinutes(10));
        double fee = ticket.calculateFee();
        assertTrue(fee >= 0);
        assertEquals(fee, ticket.getTotalCharge());
    }

    @Test
    public void testToStringContainsExpectedFields() {
        String str = ticket.toString();
        assertTrue(str.contains("Ticket ID"));
        assertTrue(str.contains("Issue Date"));
        assertTrue(str.contains("Issue Time"));
        assertTrue(str.contains("Ticket Status"));
    }
}
