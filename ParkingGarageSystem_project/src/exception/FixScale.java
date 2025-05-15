package exception;

import modules.Ticket;

public class FixScale {
	public FixScale() {}
	
	public static boolean isValidTicket(Ticket test){
		// 3
		return (test != null);
	}
	
	public static boolean checkTicketScan(String valueInt) {
		// 2). checkTicketScan - payment
		try {
			Integer.parseInt(valueInt);
			return true;
		}catch (NumberFormatException e) {
			return false;
		}
	}

}
