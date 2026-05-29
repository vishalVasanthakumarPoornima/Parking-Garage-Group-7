package testing;

import exception.FixScale;
import modules.Ticket;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FixScaleTest {

    @Test
    public void testIsValidTicketWithNonNullTicket() {
        Ticket ticket = new Ticket();
        assertTrue(FixScale.isValidTicket(ticket));
    }

    @Test
    public void testIsValidTicketWithNullTicket() {
        Ticket ticket = null;
        assertFalse(FixScale.isValidTicket(ticket));
    }

    @Test
    public void testCheckTicketScanWithValidIntegerString() {
        assertTrue(FixScale.checkTicketScan("1234"));
    }

    @Test
    public void testCheckTicketScanWithNegativeIntegerString() {
        assertTrue(FixScale.checkTicketScan("-567"));
    }

    @Test
    public void testCheckTicketScanWithInvalidString() {
        assertFalse(FixScale.checkTicketScan("abc123"));
    }

    @Test
    public void testCheckTicketScanWithEmptyString() {
        assertFalse(FixScale.checkTicketScan(""));
    }
}
