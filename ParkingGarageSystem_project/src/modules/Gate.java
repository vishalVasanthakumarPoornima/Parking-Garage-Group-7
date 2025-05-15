package modules;

import java.io.Serializable;

public class Gate implements Serializable  {
	private boolean isOpen;

	public Gate() {
		
		this.isOpen = false;
	}
	
	public void openGate() {
		isOpen = true;
	}

	
	public void closeGate() {
		isOpen = false;
	}
}
