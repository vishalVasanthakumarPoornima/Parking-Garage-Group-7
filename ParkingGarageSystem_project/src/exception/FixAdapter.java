package exception;

import modules.ParkingGarage;
import personel.AccessEmployeeControl;
import personel.Admin;

public class FixAdapter {
	// helper class associated with Adapter package
	// and provides usage for ParkingExceptions
	// 
	
	
	
	public static boolean unAuthorized(AccessEmployeeControl aec, Admin admin){
	
		return (!aec.isAuthorized(admin));
	}
	
	public static boolean isLevelOutOfBounds(int level_trial, ParkingGarage garage) {
	
		return (level_trial <= 0 || level_trial > garage.getNumLevels());
	}
	

}
