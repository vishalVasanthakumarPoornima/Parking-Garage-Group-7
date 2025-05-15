package modules;

import java.time.LocalDateTime;

/**
 * The Hardware class models a physical device in the system, such as a gate, sensor, or terminal.
 * It tracks the device's metadata, current status, criticality, and allows health checks and event logging.
 */
public class Hardware {
    // Unique identifier for the hardware device
    private String deviceID;

    // Type of device (e.g., gate, camera, sensor)
    private String type;

    // Physical location of the device
    private String location;

    // Current operational status (e.g., "active", "offline")
    private String status;

    // Timestamp of the last health check or status update
    private LocalDateTime lastChecked;

    // Indicates whether this hardware is critical to operations
    private boolean isCritical;

    /**
     * Constructor for initializing a Hardware object with required attributes.
     *
     * @param deviceID    Unique ID for the device
     * @param type        Type/category of the device
     * @param location    Physical location of the hardware
     * @param isCritical  Flag indicating if the device is critical
     */
    public Hardware(String deviceID, String type, String location, boolean isCritical) {
        this.deviceID = deviceID;
        this.type = type;
        this.location = location;
        this.isCritical = isCritical;
        this.status = "active"; // Default status on creation
        this.lastChecked = LocalDateTime.now(); // Timestamp set to current time
    }

    // Getter methods for all fields

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

    /**
     * Updates the operational status of the hardware.
     *
     * @param status New status to set (e.g., "active", "offline")
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Updates the lastChecked timestamp to the current time.
     */
    public void updateLastChecked() {
        this.lastChecked = LocalDateTime.now();
    }

    /**
     * Logs a system event related to this hardware without a user ID.
     *
     * @param eventDetails Description of the event
     * @param eventType    Type of event (INFO, WARNING, ERROR, etc.)
     */
    public void recordEvent(String eventDetails, String eventType) {
        SystemLog.recordEvent(eventDetails, eventType, null, this.deviceID);
    }

    /**
     * Logs a system event related to this hardware with an associated user ID.
     *
     * @param eventDetails Description of the event
     * @param eventType    Type of event (INFO, WARNING, ERROR, etc.)
     * @param userID       ID of the user associated with the event
     */
    public void recordEvent(String eventDetails, String eventType, String userID) {
        SystemLog.recordEvent(eventDetails, eventType, userID, this.deviceID);
    }

    /**
     * Performs a health check on the hardware.
     * - Updates the lastChecked time.
     * - Logs a WARNING if a critical device is offline.
     * - Logs INFO for non-critical devices or devices that are online.
     */
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

    /**
     * Returns a string representation of the Hardware object.
     *
     * @return formatted string with device information
     */
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
