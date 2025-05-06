package exception;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


public class ParkingExceptions extends Exception{	
	/* ParkingException​ ​is the custom exception handler for the application to keep track of errors that occur
       throughout runtime. 
       ParkingException implementation of fix method towards the model page Fix1to100, which can be used for fixing any
	   exception in entire application. Helper classes to delegate fixes for each method in addition to extension of
	   Exception. */
	
	/* Additional in Design (Component-based Programming)
	 * 2 helper classes authored to self-heal 2 packages in the application
	 * - fix methods in each helper will support 1 package; 
	 * 		helper 1 : model package    and  helper 2 : adapter package     */
	

	private static final long serialVersionUID = -3547200890979066757L;
	private int errorno;
	private String errormsg;
	
	public ParkingExceptions() {
		super();
		logException();
		printmyproblem();
	}
	
	public ParkingExceptions(String errormsg) {
		super();
		this.errormsg = errormsg;
		logException();
		printmyproblem();
	}
	
	public ParkingExceptions(int errorno) {
		super();
		this.errorno = errorno;
		fix(errorno);
		logException();
		printmyproblem();
	}
	
	public ParkingExceptions(int errorno, String errormsg) {
		super();
		this.errorno = errorno;
		this.errormsg = errormsg;
		logException();
		printmyproblem();
	}
	
	public ParkingExceptions(CloneNotSupportedException e) {
	
		super();
		logException();
		printmyproblem();
	}
	
	public ParkingExceptions(Exception e) {

		super(e);
		logException();
		printmyproblem();
	}

	// Getters
	public int getErrorno() { 	return errorno; }
	public String getErrormsg() {	return errormsg; }
	
	// Setters
	public void setErrorno(int errorno) { 	this.errorno = errorno; }
	public void setErrormsg(String errormsg) { 	this.errormsg = errormsg; }
	
	public void printmyproblem() {
		// printmyproblem: print message to console
		System.out.printf("\nParkingExceptions [errorno= %d, errormsg= %s]\n", getErrorno(), getErrormsg()); 
	}
	
	public String writemyproblem() {
		// writemyproblem: this sends out the error message for printing
		return "\nParkingExceptions [errorno= "+getErrorno()+", errormsg= "+getErrormsg()+"]\n"; 
	}
	
	public void logException() {
		/* logException: creates a local text file and keeps track of the ongoing errors
		 *               that occurs during runtime.                                     */

		try {
			File inFile = new File("log/ParkingExceptionsLog.txt");
			
			if (!inFile.exists()) {
				inFile.createNewFile();}
			Date date = new Date();
			
			FileWriter fileObj = new FileWriter(inFile, true);
			BufferedWriter buff = new BufferedWriter(fileObj);
			PrintWriter printObj = new PrintWriter(buff);
			
			
			printObj.append(date.toString() + ",\t" + writemyproblem());
			printmyproblem();
			printObj.close();

		} catch (IOException e) {
			System.out.println("Exception ERROR:");
			e.printStackTrace();
		}
	}
	
	public void fix(int errno) {
		
		switch (errno)
		{
			case 1:
				setErrormsg("File doesn't exist. Replacing with known file");
				break;
			case 2:
				setErrormsg("Invalid Input integer. Must be greater than or equal to zero.");
				break;
			case 3: 
				setErrormsg("Invalid or already used ticket.");
				break;
			case 4:
				setErrormsg("Inconvenience Issue: Parking Garage reached max capacity!");
				break;
			case 5:
				setErrormsg("Unauthorized user attempt on an admin operation.");
				break;
				
			case 6:
				setErrormsg("Given level number not within garage boundaries.");
				break;
			case 7:
				
				
		}
	}
}
