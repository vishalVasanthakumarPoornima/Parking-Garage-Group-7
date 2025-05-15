package modules;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Customer {
    private String name;
    private String contactInfo;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    // Constructor
    public Customer(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    // Method to simulate entering the garage
    public void enterGarage() {
        entryTime = LocalDateTime.now();
        System.out.println(name + " entered the parking garage at " + formatDateTime(entryTime));
    }

    // Method to simulate exiting the garage
    public void exitGarage() {
        exitTime = LocalDateTime.now();
        System.out.println(name + " exited the parking garage at " + formatDateTime(exitTime));
    }

    // Helper method to format LocalDateTime
    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
}
/*
 * public class Main {
    public static void main(String[] args) {
        // Create a new Customer
        Customer customer1 = new Customer("Jane Doe", "jane.doe@example.com");

        // Customer enters the garage
        customer1.enterGarage();

        // Simulate some parking activity (wait a few seconds)
        try {
            Thread.sleep(3000); // Wait for 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Customer exits the garage
        customer1.exitGarage();
    }
}
*/