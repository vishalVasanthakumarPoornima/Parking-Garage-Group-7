package modules;

import java.io.Serializable;

public class EntranceDisplayBoard implements Serializable {
	/* Should parking levels track number of available spaces
	 * 
	 * initially equal to number of spaces
	 * inc - adding space
	 * inc - space gets free
	 * dec - space gets occupied
	 */
	// hardware
	
	boolean isParkingAvailable;


	public EntranceDisplayBoard() {
		// start available when garage is created
		this.isParkingAvailable = true;
	}
	public void validateCapacity(int numAvailable) {
		isParkingAvailable = (numAvailable > 0) ? true : false;
	}
	
	public void displayIsParkingAvailable() {
		System.out.println((isParkingAvailable)?"Available Parking Here!": "Parking Garage is full.");
	}

}







// real-time?
//	ArrayList<LevelDisplayBoard>
// or
// implement capacity  - only changed when entryGate or exitGate opens   
// lOCKS