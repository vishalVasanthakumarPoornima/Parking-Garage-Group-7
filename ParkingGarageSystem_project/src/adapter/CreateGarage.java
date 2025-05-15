package adapter;

import java.io.IOException;

import exception.ParkingExceptions;
import modules.Address;

/*
 * The CreateGarage interface defines the basic setup operations for building and displaying
 * a parking garage in the system.
 *
 * Classes that implement this interface should handle how the garage is created
 * and how its layout or configuration is shown.
 */
public interface CreateGarage {

    /*
     * Builds a new parking garage at the given address.
     *
     * @param address The physical or logical location of the garage.
     * @param numParkingLevels Number of floors/levels in the garage.
     * @param numSpacesPerLevel Number of parking spots available on each level.
     * @throws ParkingExceptions If something goes wrong during the garage creation.
     * @throws IOException If there’s an input/output error (e.g., saving to a file).
     */
    public void BuildGarage(Address address, int numParkingLevels, int numSpacesPerLevel) throws ParkingExceptions, IOException;

    /*
     * Prints or displays details about the garage located at the specified address.
     * Useful for checking the setup or confirming that everything is configured correctly.
     * @param address The location of the garage to display.
     */
    public void printGarage(Address address);
}

