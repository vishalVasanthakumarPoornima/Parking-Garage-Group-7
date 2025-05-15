package exception;
import java.io.File;


public class FixModel {
	
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
	
    public static void validateZipCode(int zipCode) throws IllegalArgumentException {
    	// continue for ParkingGarage
        String zipCodeStr = String.valueOf(zipCode);

        if (zipCodeStr.length() != 5) {
            throw new IllegalArgumentException("ZIP code must be 5 digits.");
        }

        try {
            Integer.parseInt(zipCodeStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ZIP code must contain only digits.", e);
        }
    }

   

}