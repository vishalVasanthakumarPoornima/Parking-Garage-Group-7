package testing;

import modules.Payment;
import modules.Ticket;
import modules.TicketStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    Payment payment;
    Ticket ticket;

    @BeforeEach
    public void setup() {
        ticket = new Ticket();
        payment = new Payment(ticket);
    }

    @Test
    public void testConstructorInitializesFields() {
        assertNotNull(payment.getPaymentID());
        assertEquals(0.0, payment.getAmount());
        assertEquals(Payment.getUndefinedPaymentStatus(), payment.getPaymentMethod());
        assertFalse(payment.getIsPaid());
        assertEquals(ticket, payment.getParkingTicket());
    }

    @Test
    public void testProcessPaymentSetsAmountAndStatus() {
        payment.processPayment(Payment.getCashPaymentStatus());
        assertEquals(ticket.calculateFee(), payment.getAmount());
        assertTrue(payment.getIsPaid());
        assertEquals(Payment.getCashPaymentStatus(), payment.getPaymentMethod());
        assertEquals(TicketStatus.PAID, ticket.getParkingTicketStatus());
    }

    @Test
    public void testProcessPaymentWithCustomAmount() {
        payment.processPayment(Payment.getCardPaymentStatus(), 25.50);
        assertEquals(25.50, payment.getAmount());
        assertEquals(Payment.getCardPaymentStatus(), payment.getPaymentMethod());
        assertTrue(payment.getIsPaid());
        assertEquals(TicketStatus.PAID, ticket.getParkingTicketStatus());
    }

    @Test
    public void testSettersAndGetters() {
        payment.setAmount(50.0);
        assertEquals(50.0, payment.getAmount());

        payment.setPaid(true);
        assertTrue(payment.getIsPaid());

        payment.setPaymentMethod(Payment.getCardPaymentStatus());
        assertEquals(Payment.getCardPaymentStatus(), payment.getPaymentMethod());

        Ticket anotherTicket = new Ticket();
        payment.setParkingTicket(anotherTicket);
        assertEquals(anotherTicket, payment.getParkingTicket());
    }
}
