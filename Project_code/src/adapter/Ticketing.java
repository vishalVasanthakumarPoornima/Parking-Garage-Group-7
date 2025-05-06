package adapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import exception.ParkingExceptions;
import modules.Ticket;

public interface Ticketing {
	
    public Ticket issueTicket() throws ParkingExceptions;
    public boolean validateExit(Integer ticketID);

    public Set<Integer> getActiveTicketIDs();
}
