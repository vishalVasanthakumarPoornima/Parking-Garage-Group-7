package scale;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import adapter.BuildParkingGarage;
import adapter.ParkingGarageSystem;
import exception.FixScale;
import exception.ParkingExceptions;
import modules.Address;
import modules.ParkingGarage;
import modules.Payment;
import modules.Ticket;
import util.FileIO;
public class ParkingGarageServer {

    private static final int PORT = 12345;
    ParkingGarage garage;
    
    public ParkingGarageServer(ParkingGarage garage) throws IOException {
    	
    	this.garage = garage;
    	
        ServerSocket serverSocket = new ServerSocket(PORT);
        FileIO.log("Parking Garage Server started on port: " + PORT);
        while (true) {
        	Socket client = serverSocket.accept();
            new Thread(new ClientHandler(client, garage)).start();
        }
    	
    }

    
    public static void main(String[] args) throws IOException, ParkingExceptions {

    	
    	ParkingGarageSystem buildGarage = new BuildParkingGarage();
    	ParkingGarage pGarage = null;

    	Address address1 = new Address("8474 Nicolls St.", "Huntsville", "AL", "35803");
    	String serializedGarage = String.format("log/%s%s_parkingGarage.ser", address1.getCity(), address1.getZipcode());
    	System.out.println(serializedGarage);
    	pGarage = (ParkingGarage) FileIO.readGarageObject(serializedGarage);
    	System.out.println(pGarage.getAddress());
        
        new ParkingGarageServer(pGarage);
        
    }

}


class ClientHandler implements Runnable {
	
	// 
	private static final String LOG_FILE = "garage_log.txt";
	private static int nextTicketId = 1;
	private static Map<Integer, Ticket> tickets = new HashMap<>();
	
	// Parking Garage object
	private ParkingGarage parkingGarage;

	// Server variables
    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    
    public ClientHandler(Socket clientSocket, ParkingGarage parkingGarage) throws IOException{

        this.clientSocket = clientSocket;
        this.in = new ObjectInputStream(clientSocket.getInputStream());
        this.out = new ObjectOutputStream(clientSocket.getOutputStream());
        this.parkingGarage = parkingGarage;
    }


    @Override
    public void run() {

        try {

            String request = (String) in.readObject();

            if (request.equals("ENTRY")) {
                // In response to a request to enter from entrykiosk
                // create a new ticket for client and use parking garage's entry kiosk
            	System.out.println("ClientHandlers run() if ENTRY");
            	try {
            		Ticket ticket = parkingGarage.getEntryKiosk().printTicket();
            		
                    // Save all active tickets in hashmap
                    tickets.put(Integer.valueOf(ticket.getTicketID()), ticket);
                    
                    out.writeObject(ticket);
                    FileIO.log("ENTRY: Ticket " + ticket.getTicketID() + " issued at " + ticket.getEntryTime());
                    
            	} catch(ParkingExceptions e) {
            		e.printmyproblem();
            		out.writeObject(null);
            	}
       

            } 
            else if (request.equals("EXIT")) {
                // In response to a request to exit from exitKiosk
                //  - remove clients ticket from hashmap, then use 
                //  - ticket to calculate total fee for the clients payment
            	
            	
            	System.out.println("ClientHandlers run() if EXIT");
            	System.out.println(parkingGarage.getExitKiosk());
            	
            	// obtain ticketId
                int ticketId = in.readInt();
                Ticket ticket_to_exit = null;
                
                
                // match with tickets in system
                if (tickets.containsKey(ticketId)) {
                	ticket_to_exit = tickets.remove(ticketId);
                	
                }
                
                if (!FixScale.isValidTicket(ticket_to_exit)) {
                	// Ticket is invalid - input error
                	out.writeObject("Invalid or already used ticket.");
                	throw new ParkingExceptions(3);
                }        

                
                // Ticket is validated
                double parkingFee = parkingGarage.getExitKiosk().scanTicket_toExit(ticket_to_exit);
                if (parkingFee != -1) {
                	Payment payment = new Payment(ticket_to_exit);
                	
                	// send parkingFee and payment to exitclient
                	// to start a payment frame
                	out.writeDouble(parkingFee);
                	out.writeObject(payment);
                }
            
                // ticket payment settled, change its status, and update parking garage capacity
                ticket_to_exit.setPaid();
                
                
                long minutes = Duration.between(ticket_to_exit.getEntryTime(), LocalDateTime.now()).toMinutes();
                
                String result = String.format("\n\tTotal time: %2d minutes\n\tParking Rate: $3 per hour\n\tParking Fee: %.2f", minutes, parkingFee);
                out.writeObject(result);
                FileIO.log("EXIT: Ticket " + ticketId + " exited. " + result);

                // Print Balance Due: $
            }
        } 
        catch (ParkingExceptions e) {
        	System.out.println(e.writemyproblem());
        }
        catch (Exception e) {
            e.printStackTrace();
            FileIO.log("Error: " + e.getMessage());
        }

    }
    


}