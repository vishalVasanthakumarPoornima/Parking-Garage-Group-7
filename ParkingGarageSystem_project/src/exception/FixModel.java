package exception;
import java.io.File;


public class FixModel {
	// helper class associated with Model package
	// and provides usage for ParkingExceptions
	// 
	
	// FixModel - helper class via package model
	public FixModel() {}
	
	public static boolean isValidInteger(int test){
		// 1). isValidInteger - condition 
		return (test >= 0);
	}
	
	public static boolean checkdataEntry(String valueFloat) {
		// 2). checkdataEntry - payment
		return Float.isNaN(Float.valueOf(valueFloat));
	}
	
	public static boolean isFileValid(String filename) {
		// 3). isFileValid - searches current working directory for given file
		return new File(filename).exists();
	}
	
	public static boolean isParkingAvailable(int numAvailable) {
		return (numAvailable > 0);
	}
	
    public static void validateZipCode(int zipCode) throws ParkingExceptions {
    	// continue for ParkingGarage
        String zipCodeStr = String.valueOf(zipCode);

        if (zipCodeStr.length() != 5) {
            throw new ParkingExceptions("ZIP code must be 5 digits.");
        }

        try {
            Integer.parseInt(zipCodeStr);
        } catch (NumberFormatException e) {
            throw new ParkingExceptions("ZIP code must contain only digits.");
        }
    }

    public static boolean isValidZipCode(String zipCode) {
    	// isValidZipCode : return true if length of integer string is exactly 5
        if (zipCode.length() != 5) 
        	return false;
        return true;
     
    }
    
    public static boolean isValidStateCode(String stateCode) {
    	// isValidStateCode : return true if length of integer string is exactly 2
        return (stateCode.length() == 2);
    }
   
    

}

