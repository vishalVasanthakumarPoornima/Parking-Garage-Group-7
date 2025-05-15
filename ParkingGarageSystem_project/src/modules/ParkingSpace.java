package modules;
import exception.FixModel;
import exception.*;
import java.io.Serializable;
public class ParkingSpace  implements Serializable{
	/*
	 * space_number : with respect to (u)id within a given 
	 *                ParkingLevel [int]
	 *                - maybe level 1: 1 - 100
	 *                              2: 101 - 200
	 *                  ??? if using id
	 *                
	 * isOccupied : boolean
	 *    // could be enum if more states.  FREE, OCCUPIED, RESERVED (if applicable)   
	 */
	
	private static int count = 0;
	private int id;
	
	private int space_number; 
	private boolean isOccupied;
	
	
	// Constructor
	public ParkingSpace(int space_number) {
		
		this.id = ++count;  //
		this.space_number = space_number;
		this.isOccupied = false;
	}

	
	
	// Getters
	public int getId() { return id; }

	public int getCount() { return count; }

	public int getSpace_number() { return space_number; }

	
	
	// Setters
	public void setSpace_number(int space_number) throws ParkingExceptions {
		// keep if not setting equal to count
		if (!FixModel.isValidInteger(space_number))
			throw new ParkingExceptions(2, "Invalid Input integer. Must be greater than or equal to zero.");
		this.space_number = space_number;
	}

	public void setOccupiedSpace() { this.isOccupied = true; }
	public void setFreeSpace() { this.isOccupied = false; }
	
	
	
	//
	public boolean isOccupied() {
		// Boolean check space if being occupied
		return isOccupied == true;
	}
	
	
	@Override
	public String toString() {
		return String.format("\nParking Space number: %d\nParking Status: %s\n", 
				this.getSpace_number(),
				isOccupied() ? "Occupied" : "Free");
	}
	@Override
	public boolean equals(Object otherSpace) {
		// Override object functinality when comparing
		// to another object
		
		
		if (otherSpace == this)
			return true;
		if (otherSpace.getClass() != this.getClass())
			return false;
		
		ParkingSpace other_space = (ParkingSpace) otherSpace;
		return Integer.compare(this.id, other_space.id) == 0
				&& Integer.compare(this.space_number, other_space.space_number) == 0 ;
	}
	


}
