package personel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import modules.ParkingLevel;
import modules.SystemLog;

/*
 * The Admin class represents a system administrator who has access to system-level
 * controls such as managing parking levels. It extends the User class and implements
 * Serializable to allow for object persistence.
 */
public class Admin extends User implements Serializable { // implements Employee, Serializable {
  
	private static int count = 0;//Static counter to generate unique admin IDs

    private String adminID; //Unique ID for the Admin
    private String name;//Name of administrator 

    // Simulated system structures
    private List<ParkingLevel> levels = new ArrayList<>();

    /*
     * Constructor that creates a new Admin object with a unique admin ID.
     * Calls the superclass constructor with default credentials (e.g., password "admin123").
     *
     */
    public Admin(String name) {
    	super("AD" + (++count), "admin123");
        this.adminID = "AD" + count;
        setName(name);
    }

    
//    public void addParkingLevel(int levelNum, int numSpaces) {
//        ParkingLevel newLevel = new ParkingLevel(levelNum, numSpaces);
//        levels.add(newLevel);
//        SystemLog.recordEvent(String.format("Added new parking level: %d", levelNum), "INFO", adminID, null);
//        System.out.println(String.format("Parking level %d added by admin %s\n", levelNum, getName()));
//    }

    
//    public void addParkingSpace(int levelNum, String spaceID) {
//        for (ParkingLevel level : levels) {
//            if (level.getLevelNumber() == levelNum) {
//                level.addSpace(new ParkingSpace(spaceID));
//                SystemLog.recordEvent("Added parking space " + spaceID + " to level " + levelName, "INFO", adminID, null);
//                System.out.println("Parking space '" + spaceID + "' added to level '" + levelName + "'");
//                return;
//            }
//        }
//        System.out.println("Level '" + levelName + "' not found.");
//    }
//    public Report generateReport() {
//        Report report = new Report();
//        report.generateSummary();
//        SystemLog.recordEvent("Admin generated report", "INFO", adminID, null);
//        return report;
//    }



    // Getters
    public String getAdminID() {
        return adminID;
    }
    public String getName() {
        return name;
    }
    
    // Setters
    public void setName(String name) {
        this.name = name;
    }


    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
