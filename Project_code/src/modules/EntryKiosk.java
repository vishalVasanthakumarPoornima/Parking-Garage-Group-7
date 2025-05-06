package modules;

import java.io.Serializable;

import exception.ParkingExceptions;


public class EntryKiosk implements Serializable {
	
    private static final long serialVersionUID = 9183928684666442290L;
	private String id;
    private static int count = 0;
    private Gate gate;

    
    
    // 1-to-1
    private ParkingGarage parkingGarage;

    public EntryKiosk() {
    	this.id = String.format("EntryKiosk %d", ++count);
    	this.gate = new Gate();
    }
    
    public EntryKiosk(Gate gate) {
    	this.id = String.format("EntryKiosk %d", ++count);
    	this.gate = gate;
    }

    
    public synchronized Ticket printTicket() throws ParkingExceptions {
    	// create new ticket for client, update Garages capacity and hardware devices.
    	// Return Ticket
    	System.out.println(String.format("In entrykiosk, numAvailable%d", parkingGarage.getNumAvailable()));
    	if (parkingGarage.getNumAvailable() <= 0)
    		throw new ParkingExceptions(4);
    	Ticket ret_ticket = new Ticket();
    	gate.openGate();
    	parkingGarage.decrementAvailablity();
    	
    	gate.closeGate();
    	System.out.println(String.format("Ending entrykiosk, numAvailable%d", parkingGarage.getNumAvailable()));
		return ret_ticket;
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