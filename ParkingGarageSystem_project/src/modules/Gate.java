package modules;

import java.io.Serializable;

/**
 * The Gate class represents a simple gate that can be opened or closed.
 * It implements Serializable to allow instances to be saved and restored.
 */
public class Gate implements Serializable {

    // Flag indicating whether the gate is currently open (true) or closed (false)
    private boolean isOpen;

     // Constructor initializes the gate to a closed state.
    public Gate() {
        this.isOpen = false;
    }
 
     //Opens the gate by setting the isOpen flag to true.
    public void openGate() {
        isOpen = true;
    }


     // Closes the gate by setting the isOpen flag to false.
    public void closeGate() {
        isOpen = false;
    }

    /**
     * Returns the current state of the gate.
     * @return true if the gate is open, false if it is closed.
     */
    public boolean isOpen() {
        return isOpen;
    }
}
