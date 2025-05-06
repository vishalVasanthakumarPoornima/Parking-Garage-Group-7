package driver;
import modules.*;
import personel.Admin;
import adapter.*;
import java.io.IOException;
import exception.*;




public class Driver {

	public static void main(String[] args) {
		
		try {
			
			ParkingGarageSystem buildGarage = new BuildParkingGarage();
			
			
			// 1st parking garage
			Address address1 = new Address("8474 Nicolls St.", "Huntsville", "AL", "35803");
			buildGarage.BuildGarage(address1, 1, 1);

			
			//  2nd parking garage
			Address address2 = new Address("98 East St Margarets Drive", "Winter Park", "FL", "32792");
			buildGarage.BuildGarage(address2, 10, 5);

			
			
			// print 1st again
			System.out.println("1st Parking Garage location: \n"+buildGarage.getGarage(address1).getAddress()+"\n\n");
			
			
			Admin admin1 = new Admin("name1");
			Admin admin2 = new Admin("name2");
			Admin admin3 = new Admin("name3");
			Admin admin4 = new Admin("name4");
	
			
			
			buildGarage.addAdmin(admin1);
			buildGarage.addAdmin(admin3);
			buildGarage.addAdmin(admin4);
			
			
			
			buildGarage.addParkingLevel(admin1, address1, 5);
			try {
				buildGarage.addParkingLevel(admin2, address1, 5);
			} catch (ParkingExceptions pe) {
				pe.printmyproblem();
			}
			buildGarage.addParkingSpace(admin1, address1, 5, 3);
			System.out.println("\tadded 3 parking spaces to level 5!!!\n\n");
			
			try {
				buildGarage.addParkingSpace(admin2, address2, 5, 3);
			} catch (ParkingExceptions pe) {
				pe.printmyproblem();
			}
			try {
				// test levelOut of Bounds
				buildGarage.addParkingSpace(admin1, address1, 7, 3);
			} catch (ParkingExceptions pe) {
				pe.printmyproblem();
			}
			
			
			
			
			// log in
			if (buildGarage.validateUserCredentials(admin1.getAdminID(), "admin123"))
				System.out.println("admin1 logged in");
			else
				System.out.println("admin1 not logged in");
			
			
			if (buildGarage.validateUserCredentials(admin2.getAdminID(), "admin123"))
				System.out.println("admin2 logged in");
			else
				System.out.println("admin2 not logged in");
			
			
			
			
			
		} catch(ParkingExceptions e) {
			System.out.println(e.writemyproblem());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
