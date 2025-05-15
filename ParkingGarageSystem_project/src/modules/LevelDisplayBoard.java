package modules;

import java.io.Serializable;

public class LevelDisplayBoard implements Serializable  {
	/*
	 	class LevelDisplayBoard -
	 	   hardware sensor within a given floor.
	 	   Useful for within the system, enabling clients omit searching 
	 	   a parking level for an available parking space
	 */
	private int freeSpaces;
	
	public LevelDisplayBoard(int freeSpaces) {
		this.setFreeSpaces(freeSpaces);
	}
	
	public void displayNumFreeSpaces() {
		System.out.println((freeSpaces == 0) ? "Parking Level Full" : String.format("Available Spaces: %d", freeSpaces));
	}
	
	public int getFreeSpaces() {
		return freeSpaces;
	}
	
	public void setFreeSpaces(int freeSpaces) {
		this.freeSpaces = freeSpaces;
	}
	
	public void occupySpace() {
		// occupySpace: decrement the number of free spaces in level
		this.setFreeSpaces(freeSpaces-1);
	}
	public void leaveSpace() {
		// leaveSpace: increment the number of free spaces in level
		this.setFreeSpaces(freeSpaces+1);
	}

}
