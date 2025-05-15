package modules;

import java.io.Serializable;

import util.FileIO;

public class ExitKiosk implements Serializable {
	
    private static final long serialVersionUID = -5762735825299829729L;
	private String id;
    private static int count = 0;
    private Gate gate;
    
    // 1-to-1
    private ParkingGarage parkingGarage;
    

    public ExitKiosk() {
    	this.setId("ExitKiosk" + String.valueOf(++count));
    	this.setGate(new Gate());
    }
    
    public ExitKiosk(Gate gate) {
    	this.setId("ExitKiosk" + String.valueOf(++count));
    	this.setGate(gate);
    }

   

	public boolean scanTicket(Ticket ticket) {
		
    	if (ticket != null && ticket.getParkingTicketStatus() == TicketStatus.ACTIVE) {
    		// log
    		System.out.println("Ticket scanned!");
    		return true;
    	} else {
    		// log
    		System.out.println("Invalid ticket!");
    		return false;    		
    	}
    }
	
	
	
	public double scanTicket_toExit(Ticket ticket) {
		
    	if (ticket != null && ticket.getParkingTicketStatus() == TicketStatus.ACTIVE) {
    	
    		FileIO.log("Ticket Scanned!");
    		System.out.println("Ticket scanned!");
    		parkingGarage.exitParkingGarage();
    		return ticket.calculateFee();
    	} else {
    		
    		FileIO.log("Invalid or used ticket! <   max clients credit options (=   >");
    		System.out.println("Invalid or used ticket! <   max clients credit options (=   >");
    		return -1;    		 
    	}
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Gate getGate() {
		return gate;
	}

	public void setGate(Gate gate) {
		this.gate = gate;
	}
	
	public void openGate() {
		getGate().openGate();
	}
	
	public void closeGate() {
		getGate().closeGate();
	}
	
	public String toString() {
		return getId();
	}

	public ParkingGarage getParkingGarage() {
		return parkingGarage;
	}

	public void setParkingGarage(ParkingGarage parkingGarage) {
		this.parkingGarage = parkingGarage;
	}
	
}