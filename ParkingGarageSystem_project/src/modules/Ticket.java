package modules;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

enum TicketStatus {
	ACTIVE, 
	PAID
}

public class Ticket implements Serializable {
	
	private String ticketID;
	private static int count = 0;
	private LocalTime issuedTime;
	private LocalTime payoutTime;
	private double totalCharge;
	private TicketStatus parkingTicketStatus;

	public Ticket() {
		this.setTicketID(String.valueOf(++count));
		this.setIssuedTime(LocalTime.now());
		this.setTotalCharge(0.0);
		this.setParkingTicketStatus(TicketStatus.ACTIVE);
    }

	public double calculateFee() {
		this.setPayoutTime(LocalTime.now());
		Duration duration = Duration.between(issuedTime, payoutTime);
		long minutes = duration.toMinutes();
//		Charge is set at $3 per hour, 0.05 per minute
		double rate = 0.05;
		this.setTotalCharge(rate * minutes);
		System.out.println(totalCharge);
		//this.setParkingTicketStatus(TicketStatus.PAID);
		return this.totalCharge;

	}

	public String getTicketID() {
		return ticketID;
	}

	public void setTicketID(String ticketID) {
		this.ticketID = ticketID;
	}

	public LocalTime getEntryTime() {
		return issuedTime;
	}
	public String getIssuedTime() {
		Date date = new Date();
		return String.format("%02d:%02d", Integer.valueOf(date.getHours()), Integer.valueOf(date.getMinutes()));
	}
	public String getIssuedDate() {
		Date date = new Date();
		return String.format("%02d/%02d/%d", Integer.valueOf(date.getMonth()), 
				                        Integer.valueOf(date.getDate()), Integer.valueOf(date.getYear()+1900));
	}

	public void setIssuedTime(LocalTime issuedTime) {
		this.issuedTime = issuedTime;
	}

	public LocalTime getPayoutTime() {
		return payoutTime;
	}

	public void setPayoutTime(LocalTime payoutTime) {
		this.payoutTime = payoutTime;
	}

	public double getTotalCharge() {
		return totalCharge;
	}

	public void setTotalCharge(double totalCharge) {
		this.totalCharge = totalCharge;
	}

	public TicketStatus getParkingTicketStatus() {
		return parkingTicketStatus;
	}

	public void setParkingTicketStatus(TicketStatus parkingTicketStatus) {
		this.parkingTicketStatus = parkingTicketStatus;
	}
	public void setPaid() {
		this.setParkingTicketStatus(TicketStatus.PAID);
	}
	
	
	@SuppressWarnings("deprecation")
	public String toString() {
		
		Date date = new Date();
		return String.format("Ticket ID: %s\nIssue Date: %s\nIssue Time: %s\nTicket Status: %s\n", 
				getTicketID(),  
				getIssuedDate(), 
				getIssuedTime(), 
				getParkingTicketStatus());
	}

	public static TicketStatus getActiveTicketStatus() {
		return TicketStatus.ACTIVE;
	}

	public static TicketStatus getPaidTicketStatus() {
		return TicketStatus.PAID;
	}

}