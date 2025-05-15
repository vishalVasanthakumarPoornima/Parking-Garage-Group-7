package scale;

import javax.swing.*;
import modules.Payment;
import java.io.*;
import java.net.*;



public class ExitKioskClientGUI {

	// GUI variables
    private JFrame frame;
    
    private JLabel label;
    private JButton exitButton;
    private JTextField ticketField;
    private JTextArea outputArea;
    private JPanel panel;
    
    // Client Variables
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;


    public ExitKioskClientGUI() {
    	
        this.frame = new JFrame("Exit Kiosk");
        
        
        this.label = new JLabel("Enter Ticket ID:");
        this.ticketField = new JTextField(10);
        this.exitButton = new JButton("Request Exit");
        this.outputArea = new JTextArea(5, 30);
        
        outputArea.setEditable(false);
        exitButton.addActionListener(e -> requestExit());

        this.panel = new JPanel();
        panel.add(label);
        panel.add(ticketField);
        panel.add(exitButton);
        panel.add(new JScrollPane(outputArea));

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void requestExit() {
    	
        try {
      
        	socket = new Socket("localhost", 12345);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            		
            
            // request to client handler for the ExitKiosk operations
            out.writeObject("EXIT");
            out.writeInt(Integer.parseInt(ticketField.getText()));
            out.flush();
            
            double balanceDue = (double) in.readDouble();
            Payment payment = (Payment) in.readObject();
            
            String response = (String) in.readObject();
            outputArea.setText(response);
            
            
            // start a new Payment frame
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                	new PaymentFrame(balanceDue, payment);
                }
            });


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
        new ExitKioskClientGUI();
    }
    
}
