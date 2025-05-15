package adapter;

/**
 * BuildParkingGarage brings everything together for the parking garage system.
 * 
 * It extends the core system (ParkingGarageSystem) and implements several interfaces 
 * that handle different parts of the garage's functionality:
 * 
 * - CreateGarage: for setting up or creating new garages
 * - Ticketing: for managing tickets (issuing, validating, etc.)
 * - GarageEmployee: for employee actions like gate control or monitoring
 * - UpdateGarage: for making changes or updates to the garage setup
 * 
 * This class acts like the main access point for all these features—
 * combining everything into one place to manage the garage more easily.
 */
public class BuildParkingGarage 
    extends ParkingGarageSystem 
    implements CreateGarage, Ticketing, GarageEmployee, UpdateGarage {

    // You'll implement all the specific logic from the interfaces here.
    // For now, this just sets up the structure and connections.
}
