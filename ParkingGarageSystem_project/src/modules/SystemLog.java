package modules;

import java.time.LocalDateTime;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The SystemLog class models a logging system that tracks events occurring 
 * within a system, such as INFO, WARNING, ERROR, or SECURITY-related messages.
 * Each log entry contains metadata such as timestamp, user ID, and hardware ID.
 */
public class SystemLog {
    
    // Unique ID for each log entry
    private String logID;

    // Description of the event
    private String eventDetails;

    // Timestamp when the event was logged
    private LocalDateTime timestamp;

    // Type of event: INFO, ERROR, WARNING, SECURITY
    private String eventType;

    // ID of the user associated with the event (can be null)
    private String userID;

    // ID of the hardware involved in the event (can be null)
    private String hardwareID;

    // Static list to store all logs (shared across all instances)
    private static List<SystemLog> logList = new ArrayList<>();

    // Static counter for generating unique log IDs
    private static int count = 0;

    /**
     * Constructor to initialize a new SystemLog object.
     * Automatically sets the timestamp to the current time and generates a unique log ID.
     *
     * @param eventDetails Description of the event
     * @param eventType    Type of event (e.g., INFO, ERROR)
     * @param userID       ID of the user (optional)
     * @param hardwareID   ID of the hardware (optional)
     */
    public SystemLog(String eventDetails, String eventType, String userID, String hardwareID) {
        this.logID = "LOG" + (++count);
        this.eventDetails = eventDetails;
        this.timestamp = LocalDateTime.now();
        this.eventType = eventType;
        this.userID = userID;
        this.hardwareID = hardwareID;
    }

    /**
     * Records a log event with basic details.
     * User ID and Hardware ID are left null.
     *
     * @param eventDetails Description of the event
     * @param eventType    Type of event (e.g., INFO, ERROR)
     */
    public static void recordEvent(String eventDetails, String eventType) {
        SystemLog log = new SystemLog(eventDetails, eventType, null, null);
        logList.add(log);
        System.out.println("Log recorded: " + log.logID + " – " + log.eventDetails);
    }

    /**
     * Records a detailed log event with user and hardware context.
     *
     * @param eventDetails Description of the event
     * @param eventType    Type of event (e.g., INFO, ERROR)
     * @param userID       ID of the user associated with the event
     * @param hardwareID   ID of the hardware associated with the event
     */
    public static void recordEvent(String eventDetails, String eventType, String userID, String hardwareID) {
        SystemLog log = new SystemLog(eventDetails, eventType, userID, hardwareID);
        logList.add(log);
        System.out.println("Log recorded: " + log.logID + " – " + log.eventDetails);
    }

    /**
     * Filters and returns logs by a specific event type.
     *
     * @param eventType Type of event to filter (case-insensitive)
     * @return List of logs matching the given type
     */
    public static List<SystemLog> getLogsByType(String eventType) {
        List<SystemLog> filtered = new ArrayList<>();
        for (SystemLog log : logList) 
            if (log.eventType.equalsIgnoreCase(eventType)) 
                filtered.add(log);
        return filtered;
    }

    /**
     * Filters and returns logs that fall within a specific date/time range.
     *
     * @param from Start of date range (exclusive)
     * @param to   End of date range (exclusive)
     * @return List of logs within the given time range
     */
    public static List<SystemLog> getLogsByDateRange(LocalDateTime from, LocalDateTime to) {
        List<SystemLog> filtered = new ArrayList<>();
        for (SystemLog log : logList) 
            if (log.timestamp.isAfter(from) && log.timestamp.isBefore(to)) 
                filtered.add(log); 
        return filtered;
    }

    // Getters for the fields

    public String getLogID() {
        return logID;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getEventType() {
        return eventType;
    }

    public String getUserID() {
        return userID;
    }

    public String getHardwareID() {
        return hardwareID;
    }

    /**
     * Returns the complete list of all log entries.
     *
     * @return List of all SystemLog entries
     */
    public static List<SystemLog> getAllLogs() {
        return logList;
    }
}
