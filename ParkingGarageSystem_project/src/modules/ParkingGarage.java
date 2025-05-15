package modules;

import java.util.ArrayList;
import exception.*;
import java.io.Serializable;

public class ParkingGarage implements Serializable{
	
	
	private int numAvailable;
	private Address address;
	
	private int numLevels;
	private ArrayList<ParkingLevel> parkingLevels;
	
	private EntranceDisplayBoard entranceBoard;   
	private EntryKiosk entryKiosk;    // MANY CLIENTS FOR BOTH KIOSKS
	private ExitKiosk exitKiosk;      // - SHARE CAPACITY RESOURCE
	
	
	
	public ParkingGarage(int numLevels, int numSpacesPerLevel) throws ParkingExceptions {
		// how should we get parking space count
		// maybe if differs per floor 
		//           create ParkingGarage then addParkingLevels
		
		this.setNumLevels(numLevels);
		
		this.parkingLevels = new ArrayList <ParkingLevel>();
		for (int index = 1; index < numLevels+1; index++)
			parkingLevels.add(new ParkingLevel(index, numSpacesPerLevel));
			
		this.entranceBoard = new EntranceDisplayBoard();
		
		this.entryKiosk = new EntryKiosk();
		entryKiosk.setParkingGarage(this);
		
		this.exitKiosk = new ExitKiosk();
		exitKiosk.setParkingGarage(this);
		
		this.setNumAvailable(numLevels * numSpacesPerLevel);
	}
	

	
	// Getters
	public Address getAddress() {
		return address;
	}
	public int getNumLevels() {
		return numLevels;
	}
	public ArrayList<ParkingLevel> getParkingLevels() {
		return parkingLevels;
	}
	public EntranceDisplayBoard getEntranceBoard() {
		return entranceBoard;
	}
	public EntryKiosk getEntryKiosk() {
		return entryKiosk;
	}
	public ExitKiosk getExitKiosk() {
		return exitKiosk;
	}
	public int getNumAvailable() {
		return numAvailable;
	}


	
	// Setters
	public void setAddress(Address address) {
		this.address = address;
	}
	public void setNumLevels(int numLevels) throws ParkingExceptions {
		if (!FixModel.isValidInteger(numLevels))
			throw new ParkingExceptions(2, "Invalid Input integer. Must be greater than or equal to zero.");
		this.numLevels = numLevels;
	}
	public void setParkingLevels(ArrayList<ParkingLevel> parkingLevels) {
		this.parkingLevels = parkingLevels;
	}
	public void setEntranceBoard(EntranceDisplayBoard entranceBoard) {
		this.entranceBoard = entranceBoard;
	}
	public void setEntryKiosk(EntryKiosk entryKiosk) {
		this.entryKiosk = entryKiosk;
	}
	public void setExitKiosk(ExitKiosk exitKiosk) {
		this.exitKiosk = exitKiosk;
	}
	public void setNumAvailable(int numAvailable) {
		this.numAvailable = numAvailable;
	}
	
	
	
	public void addParkingLevel(int numSpacesPerLevel) {
		//  addParkingLevel: adds a new element in the 
		// Parking Level Array List and updates
		// the appropriate variables
		
		int newLevelNum = getNumLevels() + 1;
		try {
			
			setNumLevels(newLevelNum);
			parkingLevels.add(new ParkingLevel(newLevelNum, numSpacesPerLevel));
		} catch (ParkingExceptions e) {
			System.out.println(e.writemyproblem());
		}
		
		
	}
	
	public synchronized void addSpaces_toLevel(int parkingLevel, int spaces_to_add) {
		// addSpaces_toLevel: locates the given ParkingLevel by 
		//                    the level number and adds on to parking spaces
		ParkingLevel temp = null;
		for (ParkingLevel level : parkingLevels) 
			if ( (temp=level).getLevelNumber()  ==  parkingLevel ) 
				break;
			
		for (int i=0; i<spaces_to_add; i++)
			temp.addParkingSpace();
	}
	
	
	public Ticket enterParkingGarage() throws ParkingExceptions {
		// enterParkingGarage: return a new ticket and utilize entryKiosk
		
		// if parking is full
		if (!FixModel.isParkingAvailable(getNumAvailable()))
			throw new ParkingExceptions(4);
		
		// open gate when spaces are available
		entryKiosk.openGate();

		// find first available spot
		ParkingSpace space = null;
		for (ParkingLevel level : parkingLevels)
			if (level.freeOccupiedSpace()) {
				level.getFloorDisplayBoard().occupySpace();
				break;
			}
		
		// at this point space holds the parkingSpace 
		// that changed its state
		return entryKiosk.printTicket();
	}
	
	
	
	public boolean exitParkingGarage() {

		// go through all levels to find an occupied space 
		// free up space and update capacity variables
		for (ParkingLevel level : parkingLevels) 
			if (level.freeOccupiedSpace()) 
				return true;
			
		return false;
	}
	
	
	
	
	public synchronized void decrementAvailablity() {
		// decrementAvailability: decrement capacity tracker and update entrance display board
		setNumAvailable(getNumAvailable() - 1);
		entranceBoard.validateCapacity(getNumAvailable());
		
	}
	public synchronized void incrementAvailability() {
		// incrementAvailability: increment capacity tracker and update entrance display board
		setNumAvailable(getNumAvailable() + 1);
		entranceBoard.validateCapacity(getNumAvailable());
	}
	

	@Override
	public String toString() {
		String ls = String.format("\n\t\tParking Garage\nNumber of Levels: %d\nAddress\n_______\n%s\n", getNumLevels(), getAddress());
		for (ParkingLevel level : parkingLevels)
			ls += level;
		return ls;	
	}

}
