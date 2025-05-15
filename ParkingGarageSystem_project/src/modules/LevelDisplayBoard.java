package modules;

import java.io.Serializable;

public class LevelDisplayBoard implements Serializable  {
	/*
	 	
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
		this.setFreeSpaces(freeSpaces-1);
	}
	public void leaveSpace() {
		this.setFreeSpaces(freeSpaces+1);
	}

}
