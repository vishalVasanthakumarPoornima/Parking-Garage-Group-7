package personel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import modules.ParkingLevel;
import modules.SystemLog;

public class Admin extends User implements Serializable { // implements Employee, Serializable {
  
	private static int count = 0;

    private String adminID;
    private String name;

    // Simulated system structures
    private List<ParkingLevel> levels = new ArrayList<>();

    
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