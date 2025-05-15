package adapter;

import java.io.IOException;

import exception.ParkingExceptions;
import modules.Address;

public interface CreateGarage {
	public void BuildGarage(Address address, int numParkingLevels, int numSpacesPerLevel) throws ParkingExceptions, IOException;
	public void printGarage(Address address);
}
