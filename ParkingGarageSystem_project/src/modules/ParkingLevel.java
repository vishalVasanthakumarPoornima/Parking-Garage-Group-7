package modules;

import java.util.*;
import exception.FixModel;
import exception.ParkingExceptions;
import util.FileIO;
import java.io.Serializable;


public class ParkingLevel implements Serializable {

	
	private static int count = 0;
	private int id;
	private int levelNumber;
	
	private int num_spaces;                  
	private ArrayList<ParkingSpace> parkingSpaces;
	private LevelDisplayBoard floorDisplayBoard;
	
	public void updateDisplayBoard() {
		floorDisplayBoard.displayNumFreeSpaces();	
	}
	
	
	public ParkingLevel(int levelNumber, int num_spaces) {
		this.id = ++count;
		
		this.levelNumber = levelNumber;
		this.num_spaces = num_spaces;        // 
		
		this.parkingSpaces = new ArrayList <ParkingSpace>();
		for (int index = 0; index < num_spaces; index++)
			this.parkingSpaces.add(new ParkingSpace(index + 1));
		this.setFloorDisplayBoard(new LevelDisplayBoard(num_spaces));
	}
	
	
	// Getters
	public int getId() { return id; }
	public int getLevelNumber() { return levelNumber; }	
	public LevelDisplayBoard getFloorDisplayBoard() {
		return floorDisplayBoard;
	}
	
	public ArrayList<ParkingSpace> getParkingSpaces() {
		return parkingSpaces;
	}

	public ParkingSpace getParkingSpaceByNum(int spaceNum) {
		// iterates through the parkingspace array list
		// and locate the space with the specified space number
		//
		
		for (ParkingSpace space : parkingSpaces)
			if (space.getSpace_number()==spaceNum)
				return space;
	    return null;  
	}
	
	public ParkingSpace getParkingSpace(ParkingSpace _space) {
		// getParkingSpace: iterates through the parkingspace array list
		// and locate the ParkingSpace argument
		
		for (ParkingSpace space : parkingSpaces)
			if (space == _space)
				return space;
	    return null;  
	}
	
	public ParkingSpace occupyAvailableSpace() {
		// find the first available 'free' Space
		// set status to occupy
		// returns ParkingSpace : if ticket may want to reference a space
		// cond: throw LevelUnavailableException
		// update the level display board 
		
		for (ParkingSpace space : parkingSpaces)
			if (!space.isOccupied()) {
				space.setOccupiedSpace();
				floorDisplayBoard.occupySpace();
				return space;
			}
		return null;
	}


	// setters
	
	public void setLevelNumber(int levelNumber) throws ParkingExceptions {
		if (!FixModel.isValidInteger(levelNumber))
			throw new ParkingExceptions(2, "Invalid Input integer. Must be greater than or equal to zero.");
	
		this.levelNumber = levelNumber;

	}
	
	public void setFloorDisplayBoard(LevelDisplayBoard floorDisplayBoard) {
		this.floorDisplayBoard = floorDisplayBoard;
	}
	
	
	
	public void addParkingSpace() {
		// adds new Parking Space in arraylist
		// increment number of spaces
		// update LevelDisplayBoard
		parkingSpaces.add(new ParkingSpace(this.num_spaces + 1));
		num_spaces += 1;
		floorDisplayBoard.leaveSpace();
	}
	
	public void freeSpace(int spaceNumber) {
		// locates parking space by parking space number
		// and attempts to unoccupy space
		
		ParkingSpace space;
		if ((space = this.getParkingSpaceByNum(spaceNumber))==null)
			return;
		if (!space.isOccupied()) {
			FileIO.log("Error: Parking space is already free to park.");
			System.out.println("Error: Parking space is already free to park.");
		}
		else {
			space.setFreeSpace();
			floorDisplayBoard.leaveSpace();
		}
		
	}
	
	public void freeSpace(ParkingSpace _space) {
		// locates parking space by parking reference
		// and attempts to unoccupy space
		
		ParkingSpace space;
		if ((space = this.getParkingSpace(_space)) == null)
			return;
		
		if (!space.isOccupied()) {
			FileIO.log("Error: Parking space is already free to park.");
			System.out.println("Error: Parking space is already free to park.");
		}
		else {
			space.setFreeSpace();
			floorDisplayBoard.leaveSpace();
		}
	}
	
	public boolean freeOccupiedSpace() {
		// iterates through parkingSpace array list and 
		// locates a available space to occupy
		
		for (ParkingSpace space : parkingSpaces)
			if (space.isOccupied()) {
				space.setFreeSpace();
				floorDisplayBoard.leaveSpace();
				return true;
			}
		return false;
	}
	
	
	@Override
	public String toString() {
		
		String ls = String.format("\nParking Level number: %d\nNumber of Parking Spaces: %d\n", 
				getLevelNumber(), parkingSpaces.size()) 
				+ "\n\tList of parking spaces\n\t______________________\n";
		for (ParkingSpace space : parkingSpaces)
			ls += space;
		return ls;
	}

}
