package modules;

import java.time.LocalDateTime;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class SystemLog {
    private String logID;
    private String eventDetails;
    private LocalDateTime timestamp;
    private String eventType; // INFO, ERROR, WARNING, SECURITY
    private String userID;
    private String hardwareID;

    private static List<SystemLog> logList = new ArrayList<>();
    private static int count = 0;

    // Constructor
    public SystemLog(String eventDetails, String eventType, String userID, String hardwareID) {
        this.logID = "LOG" + (++count);
        this.eventDetails = eventDetails;
        this.timestamp = LocalDateTime.now();
        this.eventType = eventType;
        this.userID = userID;
        this.hardwareID = hardwareID;
    }

    // Overloaded log creation methods
    public static void recordEvent(String eventDetails, String eventType) {
        SystemLog log = new SystemLog(eventDetails, eventType, null, null);
        logList.add(log);
        System.out.println("Log recorded: " + log.logID + " – " + log.eventDetails);
    }

    

    public static void recordEvent(String eventDetails, String eventType, String userID, String hardwareID) {
        SystemLog log = new SystemLog(eventDetails, eventType, userID, hardwareID);
        logList.add(log);
        System.out.println("Log recorded: " + log.logID + " – " + log.eventDetails);
    }

    
    // Get logs by type
    public static List<SystemLog> getLogsByType(String eventType) {
        List<SystemLog> filtered = new ArrayList<>();
        for (SystemLog log : logList) {
            if (log.eventType.equalsIgnoreCase(eventType)) {
                filtered.add(log);
            }
        }
        return filtered;
    }

    // Get logs by date range
    public static List<SystemLog> getLogsByDateRange(LocalDateTime from, LocalDateTime to) {
        List<SystemLog> filtered = new ArrayList<>();
        for (SystemLog log : logList) {
            if (log.timestamp.isAfter(from) && log.timestamp.isBefore(to)) {
                filtered.add(log);
            }
        }
        return filtered;
    }


    // Getters
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

    public static List<SystemLog> getAllLogs() {
        return logList;
    }
}





































// Print console summary
//public static void generateReport() {
//  System.out.println("==== Garage Report Summary ====");
//
//  Map<String, Integer> eventCounts = new HashMap<>();
//
//  for (SystemLog log : logList) {
//      String type = log.getEventType();
//      eventCounts.put(type, eventCounts.getOrDefault(type, 0) + 1);
//  }
//
//  System.out.println("Total Log Entries: " + logList.size());
//
//  for (String type : eventCounts.keySet()) {
//      System.out.printf("  - %s: %d%n", type, eventCounts.get(type));
//  }
//
//  // Optional: Payment summary
//  try {
//      System.out.println("Total Payments: " + Payment.getAmount());
//      System.out.println("Total Revenue: $" + Payment.getTotalRevenue());
//  } catch (Exception e) {
//      System.out.println("Payment data unavailable.");
//  }
//
//  System.out.println("Report generated successfully.");
//}





// Export logs to TXT or CSV with summary
//public static void exportLogsToFile(String format) {
//  String filename = "SystemLogs_" + LocalDateTime.now().toString().replace(":", "-") + "." + format.toLowerCase();
//
//  try (FileWriter writer = new FileWriter(filename)) {
//      // Summary
//      Map<String, Integer> eventCounts = new HashMap<>();
//      for (SystemLog log : logList) {
//          String type = log.getEventType();
//          eventCounts.put(type, eventCounts.getOrDefault(type, 0) + 1);
//      }
//
//      if (format.equalsIgnoreCase("csv")) {
//          writer.write("Summary Section\n");
//          writer.write("Total Logs," + logList.size() + "\n");
//          for (String type : eventCounts.keySet()) {
//              writer.write(type + "," + eventCounts.get(type) + "\n");
//          }
//
//          try {
//              writer.write("Total Payments," + String.valueOf(Payment.getAmount()) + "\n");           //
//              writer.write("Total Revenue," + Payment.getTotalRevenue() + "\n");
//          } catch (Exception e) {
//              writer.write("Total Payments,Unavailable\n");
//              writer.write("Total Revenue,Unavailable\n");
//          }
//
//          writer.write("\nLogID,EventType,EventDetails,Timestamp,UserID,HardwareID\n");
//          for (SystemLog log : logList) {
//              writer.write(String.format("%s,%s,%s,%s,%s,%s\n",
//                      log.getLogID(),
//                      log.getEventType(),
//                      log.getEventDetails().replace(",", " "),
//                      log.getTimestamp(),
//                      log.getUserID() == null ? "" : log.getUserID(),
//                      log.getHardwareID() == null ? "" : log.getHardwareID()));
//          }
//
//      } else if (format.equalsIgnoreCase("txt")) {
//          writer.write("==== System Log Summary ====\n");
//          writer.write("Total Logs: " + logList.size() + "\n");
//          for (String type : eventCounts.keySet()) {
//              writer.write("  - " + type + ": " + eventCounts.get(type) + "\n");
//          }
//
//          try {
//              writer.write("Total Payments: " + Payment.getAmount() + "\n");
//              writer.write("Total Revenue: $" + Payment.getTotalRevenue() + "\n");
//          } catch (Exception e) {
//              writer.write("Payment summary unavailable.\n");
//          }
//
//          writer.write("\n==== Full Log Entries ====\n\n");
//          for (SystemLog log : logList) {
//              writer.write(String.format(
//                      "LogID: %s\nEventType: %s\nEventDetails: %s\nTimestamp: %s\nUserID: %s\nHardwareID: %s\n\n",
//                      log.getLogID(),
//                      log.getEventType(),
//                      log.getEventDetails(),
//                      log.getTimestamp(),
//                      log.getUserID() == null ? "N/A" : log.getUserID(),
//                      log.getHardwareID() == null ? "N/A" : log.getHardwareID()));
//          }
//      } else {
//          System.out.println("Unsupported format: " + format);
//          return;
//      }
//
//      System.out.println("Logs exported successfully to: " + filename);
//
//  } catch (IOException e) {
//      System.out.println("Error exporting logs: " + e.getMessage());
//  }
//}
//Example Usage
/*
SystemLog.recordEvent("Entry gate opened", "INFO", "AD01", "GATE01");
SystemLog.recordEvent("Sensor failure on Level 3", "ERROR");

SystemLog.generateReport();
SystemLog.exportLogsToFile("txt");
SystemLog.exportLogsToFile("csv");
*/