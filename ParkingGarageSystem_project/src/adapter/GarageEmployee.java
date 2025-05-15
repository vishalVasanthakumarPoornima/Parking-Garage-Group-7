package adapter;

import personel.Admin;
import personel.ParkingAttendant;

public interface GarageEmployee {

	void addParkingAttendent(ParkingAttendant parkingAttendant);
	void addAdmin(Admin admin);
	boolean validateUserCredentials(String username, String password);

}
