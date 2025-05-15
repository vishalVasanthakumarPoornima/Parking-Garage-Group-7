package modules;

import java.io.Serializable;

import exception.FixModel;
import exception.ParkingExceptions;

public class Address implements Serializable {
	/*
	 * Address object -
	 *    Contains the variables associated with a garage location
	 *        Street
	 *        City
	 *        State 
	 *        Zipcode
	 */
	private String street;
	private String city;
	private String state;
	private String zipcode;
	
	
	// Constructor
	public Address(String street, String city, String state, String zipcode) throws ParkingExceptions {
	
		this.street = street;
		this.city = city;
		setState(state);
		setZipcode(zipcode);
	
	}

	
	// Getters
	public String getStreet() {
		return street;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}	
	public String getZipcode() {
		return zipcode;
	}
	
	
	// Setters
	public void setStreet(String street) {
		this.street = street;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) throws ParkingExceptions {
		
		if (!FixModel.isValidStateCode(state))
			throw new ParkingExceptions(8);
		
		this.state = state;
	}
	public void setZipcode(String zipcode) throws ParkingExceptions {
		
		if (!FixModel.isValidZipCode(zipcode))
			throw new ParkingExceptions(7);
		
		try {
			Integer.valueOf(zipcode);
		} 
		catch (NumberFormatException e) {
			throw new ParkingExceptions("Zipcode must be in numbers");
		} 
		this.zipcode = zipcode;
	}
	
	@Override
	public String toString() {
		return String.format("\n\tStreet: %s\n\tCity: %s\n\tState: %s\n\tZipcode: %s\n", 
				getStreet(), getCity(), getState(), getZipcode());
	}
}
