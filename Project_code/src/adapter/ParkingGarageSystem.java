package adapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import exception.FixAdapter;
import exception.FixModel;
import exception.ParkingExceptions;
import modules.Address;
import modules.ParkingGarage;
import modules.Ticket;
import personel.AccessEmployeeControl;
import personel.Admin;
import personel.AuthenticationManager;
import personel.ParkingAttendant;
import personel.User;
import util.FileIO;

public abstract class ParkingGarageSystem {

	// contains certain garage to deal with accessing resource
	protected static ParkingGarage parkingGarage;
	
	protected static LinkedHashMap<String, ParkingGarage> pg1 = new LinkedHashMap<>();
	
	//
	protected static Map<Integer, Ticket> activeTickets = new HashMap<>();
	protected static ArrayList <ParkingAttendant> attendants;
	protected static ArrayList <Admin> admins;
	
	protected static AuthenticationManager authentication_manager;
	protected static AccessEmployeeControl access_employee_control;
	
	public ParkingGarageSystem() {
		attendants = new ArrayList<ParkingAttendant>();
		admins = new ArrayList<Admin>();
		
		authentication_manager = new AuthenticationManager();
		access_employee_control = new AccessEmployeeControl();
	}
	
	
	// Getter to expose this map through BuildGarage
	public static synchronized ParkingGarage getGarage(Address address) throws ParkingExceptions {
		
		if (pg1.containsKey(address.getZipcode()))
			return pg1.get(address.getZipcode());
		
		String serialGarageFile = String.format("/log%s%s_parkingGarage.ser", 
			address.getCity(), address.getZipcode());
		
		if (FixModel.isFileValid(serialGarageFile))
			throw new ParkingExceptions(1);
	
		parkingGarage = FileIO.readGarageObject(serialGarageFile);
		return parkingGarage;
			
		
	}

	
	public void addParkingLevel(Admin admin, Address location, int numParkingSpace) throws ParkingExceptions{
		
		if (FixAdapter.unAuthorized(access_employee_control, admin))
			throw new ParkingExceptions(5);
		
		parkingGarage = getGarage(location);
		parkingGarage.addParkingLevel(numParkingSpace);
		
	
		try {
			// update hashmap and serialized object file
			pg1.put(location.getZipcode(), parkingGarage);
			FileIO.writeGarageObject(parkingGarage);
		} catch (ParkingExceptions e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void addParkingSpace(Admin admin, Address location, int levelNumber, int numParkingSpace) throws ParkingExceptions{

		if (FixAdapter.unAuthorized(access_employee_control, admin))
			throw new ParkingExceptions(5);
		
		parkingGarage = getGarage(location);
		if (FixAdapter.isLevelOutOfBounds(levelNumber, ( parkingGarage = getGarage(location) )))
			throw new ParkingExceptions(6);
		
		parkingGarage.addSpaces_toLevel(levelNumber, numParkingSpace);
		
		try {
			// update hashmap and serialized object file
			pg1.put(location.getZipcode(), parkingGarage);
			FileIO.writeGarageObject(parkingGarage);
		} catch (ParkingExceptions e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	

	public void BuildGarage(Address address, int numParkingLevels, int numSpacesPerLevel) throws ParkingExceptions, IOException {
		
		ParkingGarage pGarage = new ParkingGarage(numParkingLevels, numSpacesPerLevel);
		pGarage.setAddress(address);

		FileIO.writeGarageObject(pGarage);
		
		// update hashmap and serialized object file
		pg1.put(address.getZipcode(), pGarage);
	}

	public void printGarage(Address address) {
		parkingGarage = pg1.get(address);
		System.out.println(parkingGarage);
		
	}
	
    public Ticket issueTicket() throws ParkingExceptions {

    	
        Ticket ticket = parkingGarage.getEntryKiosk().printTicket();
        activeTickets.put(Integer.valueOf(ticket.getTicketID()), ticket);

        FileIO.log("ENTRY: Ticket " + ticket.getTicketID() + " issued at " + ticket.getEntryTime());
        return ticket;
    }

    // Ticketing implementation
    public boolean validateExit(Integer ticketID) {
        //if it does not exist in active tickets,
        // then it was removed and PAID; thus exited the facility
        return (Ticket) activeTickets.get(ticketID) == null;
    }

    
    public Set<Integer> getActiveTicketIDs() {
    	return activeTickets.keySet();
    }
    
    // GarageEmployee's implementation - access rights to parking garage operations
	public synchronized void addParkingAttendent(ParkingAttendant parkingAttendant){
		
		attendants.add(parkingAttendant);
		
		authentication_manager.registerUser(parkingAttendant);
		access_employee_control.registerAttendants(parkingAttendant);
		
	}
	
	public synchronized void addAdmin(Admin admin) {
		admins.add(admin);
		
		authentication_manager.registerUser(admin);
		access_employee_control.registerAdmin(admin);
	}
	
	public boolean validateUserCredentials(String username, String password) {
		User user = authentication_manager.login(username, password);
		return (user != null);
	}
}
