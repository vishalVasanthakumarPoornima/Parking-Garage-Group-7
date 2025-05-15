package personel;
import java.io.Serializable;

import modules.Payment;
import modules.Ticket;

public class ParkingAttendant extends User implements Serializable { // implements Employee, Serializable {
    protected String parkingAttendantID;
    protected static int count = 0;
    protected String name;
    protected String username;
    protected String password;

    public ParkingAttendant(String name, String username, String password) {
    	super("PA" + String.valueOf(++count), password);
        this.parkingAttendantID = "PA" + String.valueOf(count);
        this.name = name;
//        this.setUsername(this.parkingAttendantID);
//        this.setPassword("password");
    }

    public void processTicket() {
        Ticket ticket = new Ticket();
        System.out.println("This ticket (" + ticket.getTicketID() + ") was created manually.");
    }

    public void handlePayment(Ticket ticket) {
        if (ticket == null) {
            System.out.println("No ticket provided for payment.");
            return;
        }
        if (ticket.getParkingTicketStatus() != Ticket.getActiveTicketStatus()) {
            System.out.println("Ticket " + ticket.getTicketID() + " cannot be paid. Current status: " + ticket.getParkingTicketStatus());
            return;
        }
        Payment payment = new Payment(ticket);
        payment.processPayment(Payment.getCardPaymentStatus());

        System.out.println("Payment successful for Ticket " + ticket.getTicketID() + ". Amount Paid: $" + payment.getAmount() + " via " + payment.getPaymentMethod());
    }


    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public String getParkingAttendantID() {
        return parkingAttendantID;
    }

//    public void setParkingAttendantID(String employeeID) {
//        this.parkingAttendantID = employeeID;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

//    public void setUsername(String username) {
//        this.username = username;
//    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

}