package personel;
import java.io.Serializable;

import exception.ParkingExceptions;
import modules.Payment;
import modules.Ticket;

public class ParkingAttendant extends User implements Serializable { // implements Employee, Serializable {
   
	protected String parkingAttendantID;
    protected static int count = 0;
    protected String name;


    public ParkingAttendant(String name, String username, String password) {
        super(username, password);
        this.parkingAttendantID = "PA" + String.valueOf(++count);
        this.name = name;
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
        Payment payment;
		try {
			payment = new Payment(ticket);     
			payment.processPayment(Payment.getCardPaymentStatus());
			System.out.println("Payment successful for Ticket " + ticket.getTicketID() + ". Amount Paid: $" + payment.getAmount() + " via " + payment.getPaymentMethod());
  
		} catch (ParkingExceptions e) {
			e.printStackTrace();
		}
   
    }


    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    public String getParkingAttendantID() {
        return parkingAttendantID;
    }
    public String getUsername() {
    	return super.getUsername();
    }
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

}
