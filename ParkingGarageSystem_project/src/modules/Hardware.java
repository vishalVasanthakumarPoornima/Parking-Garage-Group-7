package modules;

import java.time.LocalDateTime;

public class Hardware {
    private String deviceID;
    private String type;
    private String location;
    private String status;
    private LocalDateTime lastChecked;
    private boolean isCritical;

    // Constructor
    public Hardware(String deviceID, String type, String location, boolean isCritical) {
        this.deviceID = deviceID;
        this.type = type;
        this.location = location;
        this.isCritical = isCritical;
        this.status = "active"; // default status
        this.lastChecked = LocalDateTime.now(); // initialized as now
    }

    // Getter methods
    public String getDeviceID() {
        return deviceID;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getLastChecked() {
        return lastChecked;
    }

    public boolean isCritical() {
        return isCritical;
    }

    // Setter methods
    public void setStatus(String status) {
        this.status = status;
    }

    public void updateLastChecked() {
        this.lastChecked = LocalDateTime.now();
    }

    // Record event - basic
    public void recordEvent(String eventDetails, String eventType) {
        SystemLog.recordEvent(eventDetails, eventType, null, this.deviceID);
    }

    // Record event - with user
    public void recordEvent(String eventDetails, String eventType, String userID) {
        SystemLog.recordEvent(eventDetails, eventType, userID, this.deviceID);
    }

    // Perform health check and log status
    public void performHealthCheck() {
        updateLastChecked();

        if ("offline".equalsIgnoreCase(this.status) && this.isCritical) {
            String message = "CRITICAL hardware offline: " + this.deviceID + " at " + this.location;
            recordEvent(message, "WARNING");
        } else if ("offline".equalsIgnoreCase(this.status)) {
            String message = "Non-critical hardware offline: " + this.deviceID + " at " + this.location;
            recordEvent(message, "INFO");
        } else {
            String message = "Hardware check OK: " + this.deviceID + " (" + this.status + ")";
            recordEvent(message, "INFO");
        }
    }

    // ToString method
    @Override
    public String toString() {
        return "Hardware{" +
                "deviceID='" + deviceID + '\'' +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                ", lastChecked=" + lastChecked +
                ", isCritical=" + isCritical +
                '}';
    }
}

//Example of driver
/*
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create hardware devices
        Hardware gate1 = new Hardware("GATE01", "gate", "Entrance A", true);      // Critical
        Hardware sensor1 = new Hardware("SENS01", "sensor", "Level 2", false);    // Non-critical
        Hardware camera1 = new Hardware("CAM01", "camera", "Exit B", true);       // Critical

        // Simulate hardware status changes
        gate1.setStatus("offline");     // Should trigger WARNING
        sensor1.setStatus("offline");   // Should trigger INFO
        camera1.setStatus("active");    // Should trigger INFO (OK)

        // Perform health checks
        gate1.performHealthCheck();
        sensor1.performHealthCheck();
        camera1.performHealthCheck();

        // Print hardware details
        System.out.println("\n--- Hardware Status ---");
        System.out.println(gate1);
        System.out.println(sensor1);
        System.out.println(camera1);

        // Show logs filtered by WARNING
        System.out.println("\n--- WARNING Logs ---");
        List<SystemLog> warningLogs = SystemLog.getLogsByType("WARNING");
        for (SystemLog log : warningLogs) {
            System.out.println(log);
        }

        // Show all logs (if you want everything)
        System.out.println("\n--- All Logs ---");
        List<SystemLog> allLogs = SystemLog.getLogsByType("INFO");
        allLogs.addAll(SystemLog.getLogsByType("WARNING"));
        for (SystemLog log : allLogs) {
            System.out.println(log);
        }
    }
}
*/