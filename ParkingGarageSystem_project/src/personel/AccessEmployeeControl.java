package personel;

import java.util.HashSet;

public class AccessEmployeeControl {
	// for security - could be similar to having status enum
	//                but this gives more of a hands-on controller
	
	
	private HashSet<ParkingAttendant> authorizedAttendants;
    private HashSet<Admin> authorizedAdmins;

    
    public AccessEmployeeControl() {
    	this.authorizedAttendants = new HashSet<>(); 
    	this.authorizedAdmins = new HashSet<>();
    }
    
    // Giving attendants access rights.
    public void registerAttendants(ParkingAttendant attendant) {
        authorizedAttendants.add(attendant);
    }
    
    // Giving admin access rights.
    public void registerAdmin(Admin admin) {
    	authorizedAdmins.add(admin);
    }

    
    // check if given attendant is has certain access
    public boolean isAuthorized(ParkingAttendant attendant) {
        return authorizedAttendants.contains(attendant);
    }
    
    // check if given admin is has certain access
    public boolean isAuthorized(Admin admin) {
        return authorizedAdmins.contains(admin);
    }
    
}
