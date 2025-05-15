package modules;
import java.io.Serializable;

/*
 * Enum representing supported payment methods.
 */
enum PaymentMethodStatus {
    UNDEFINED, // Default state, not yet set
    CARD,      // Payment made via card
    CASH       // Payment made via cash
}

/*
 * The Payment class handles payment processing for a parking system.
 * It records payment details such as amount, method, and status,
 * and links each payment to a corresponding parking ticket.
 */
public class Payment implements Serializable {

    // Unique identifier for each payment
    private String paymentID;

    // Static counter for generating unique payment IDs
    private static int count = 0;

    // The amount paid for the ticket
    private double amount;

    // Method used for payment (CARD, CASH, etc.)
    private PaymentMethodStatus paymentMethod;

    // Status indicating whether the payment was successfully completed
    private boolean isPaid;

    // Associated parking ticket for which this payment is made
    private Ticket parkingTicket;

    /*
     * Constructor initializes a new Payment instance linked to a parking ticket.
     * Sets default amount to 0 and payment status to UNDEFINED.
     * parkingTicket the ticket for which this payment is being made
     */
    public Payment(Ticket parkingTicket) {
        this.setPaymentID("Payment" + String.valueOf(++count));
        this.setAmount(0.0);
        this.setPaymentMethod(PaymentMethodStatus.UNDEFINED);
        this.setPaid(false);
        this.setParkingTicket(parkingTicket);
    }

    /*
     * Processes payment using the calculated fee from the parking ticket.
     * Marks both the payment and ticket as paid.
     * paymentMethod The method used for payment (CARD or CASH)
     */
    public void processPayment(PaymentMethodStatus paymentMethod) {
        this.setAmount(this.parkingTicket.calculateFee());
        this.setPaymentMethod(paymentMethod);
        this.setPaid(true);
        getParkingTicket().setPaid();
    }

    /*
     * Processes payment with a specified amount (e.g., discounts or manual entries).
     * Marks both the payment and ticket as paid.
     * paymentMethod  The method used for payment
     * paymentAmount  The amount paid
     */
    public void processPayment(PaymentMethodStatus paymentMethod, double paymentAmount) {
        this.setPaymentMethod(paymentMethod);
        this.setAmount(paymentAmount);
        this.setPaid(true);
        getParkingTicket().setPaid();
    }

    // Getters and Setters

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentMethodStatus getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodStatus paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Ticket getParkingTicket() {
        return parkingTicket;
    }

    public void setParkingTicket(Ticket parkingTicket) {
        this.parkingTicket = parkingTicket;
    }

    // Static utility methods for accessing enum values

    public static PaymentMethodStatus getUndefinedPaymentStatus() {
        return PaymentMethodStatus.UNDEFINED;
    }

    public static PaymentMethodStatus getCashPaymentStatus() {
        return PaymentMethodStatus.CASH;
    }

    public static PaymentMethodStatus getCardPaymentStatus() {
        return PaymentMethodStatus.CARD;
    }
}
