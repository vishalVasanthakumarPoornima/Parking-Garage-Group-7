
package scale;

import modules.Ticket;
import javax.swing.*;
import java.io.*;
import java.net.*;



public class EntryKioskClientGUI {

    // GUI then variables
    private JFrame frame;
    private JTextArea outputArea;

    private Ticket ticket;

    // Client variables
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;


    public EntryKioskClientGUI()  {

        this.frame = new JFrame("Entry Kiosk");


        JButton entryButton = new JButton("Print Ticket");
        this.outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        entryButton.addActionListener(e -> requestEntry());

        JPanel panel = new JPanel();
        panel.add(entryButton);
        panel.add(new JScrollPane(outputArea));

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }



    private void requestEntry() {
        // event to enter parking garage and to obtain a new ticket

        try {
        	this.socket = new Socket("localhost", 12345);
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
            
            
            // request to client handler for the EntryKiosk operations
            out.writeObject("ENTRY");
            
        
            this.ticket = (Ticket) in.readObject();
            if (ticket == null)
            	outputArea.setText("Ticket is not issued! Garage reached maximum capacity.");
            else
            	outputArea.setText("\tTicket Issued:\nTicket ID: " + ticket.getTicketID() + "\nEntry Time: " + ticket.getEntryTime());
        } 
		catch (IOException e){
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        catch (Exception e) {
            // Display a connection issue
            outputArea.setText("ERROR: failed to connect to the server.");
            e.printStackTrace();
        }
		finally {
			try{

				if (in != null)
					in.close();
				if (out != null)
					out.close();
				if (socket != null)
					socket.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
    }



    public static void main(String[] args) {
        new EntryKioskClientGUI();
    }
}
