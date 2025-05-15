package modules;

import java.io.Serializable;

public class Address implements Serializable {
	
	private String street;
	private String city;
	private String state;
	private String zipcode;
	
	public Address(String street, String city, String state, String zipcode) {
	
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
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
	public void setState(String state) {
		this.state = state;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public String toString() {
		return String.format("\n\tStreet: %s\n\tCity: %s\n\tState: %s\n\tZipcode: %s\n", 
				getStreet(), getCity(), getState(), getZipcode());
	}
}
