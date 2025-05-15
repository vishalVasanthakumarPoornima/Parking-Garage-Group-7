package adapter;

import exception.ParkingExceptions;
import modules.Address;
import personel.Admin;

public interface UpdateGarage {

	public void addParkingLevel(Admin admin, Address location, int numParkingSpace) throws ParkingExceptions;
	public void addParkingSpace(Admin admin, Address location, int levelNumber, int numParkingSpace) throws ParkingExceptions;
}
