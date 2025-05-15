package scale;

import javax.swing.*;

import modules.Payment;

import java.awt.*;
import java.awt.event.*;

public class PaymentFrame {
	
	enum PAYMENT_TYPE {CARD, CASH, MANUAL};

    private double balanceDue;
    private JFrame frame;
    private JLabel balanceLabel;
    private JTextField amountField;
    private JButton cardButton;
    private JButton cashButton;
    private JButton manualPaymentButton;
    private JLabel resultLabel;
	private Payment payment;


    public PaymentFrame(double balance, Payment payment) {
  
        this.balanceDue = balance;
        this.payment = payment;
        createPaymentUI();
    }

    private void createPaymentUI() {
    	
        this.frame = new JFrame("Payment");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 1, 10, 10));

        this.balanceLabel = new JLabel("Balance Due: $" + String.format("%.2f", balanceDue), SwingConstants.CENTER);
        this.amountField = new JTextField();
        amountField.setToolTipText("Enter cash amount here");

        this.cardButton = new JButton("Pay by Card");
        this.cashButton = new JButton("Pay by Cash (No Change)");
        this.manualPaymentButton = new JButton("Manual Payment (ask for assistance");
        this.resultLabel = new JLabel("", SwingConstants.CENTER);


        cardButton.addActionListener(e -> completePayment(frame, "Card payment accepted. Thank you!", PAYMENT_TYPE.CARD));
        cashButton.addActionListener(e -> cashPayment());
        
        manualPaymentButton.addActionListener(e -> manualPayment());

        frame.add(balanceLabel);
        frame.add(amountField);
        frame.add(cardButton);
        frame.add(cashButton);
        frame.add(manualPaymentButton);
        frame.add(resultLabel);

        frame.setSize(350, 250);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private void cashPayment(){
    	try {
    		
            double paid = Double.parseDouble(amountField.getText());
            
            if (paid >= balanceDue) {
                completePayment(frame, "Cash payment accepted. No change given!", PAYMENT_TYPE.CASH);
                payment.processPayment(Payment.getCardPaymentStatus(), paid);
            }
             else 
                resultLabel.setText("Insufficient payment. Please enter $" + balanceDue + " or more.");
            
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid amount. Please enter a valid number.");
        }
    	
    }
    
    private void manualPayment(){
    	// request for assistance
    	completePayment(frame, "Parking Attendant assistance is on the way. Thank you!", PAYMENT_TYPE.MANUAL);
    }

    private void completePayment(JFrame frame, String msgToFrame, PAYMENT_TYPE type_paid) {
        // log transaction. reset fields, etc.
    	
    	switch(type_paid) {
	    	case CASH:
	    		payment.processPayment(Payment.getCashPaymentStatus(), balanceDue);
	    		break;
	    	case CARD:
	    		payment.processPayment(Payment.getCardPaymentStatus(), balanceDue);
	    		break;
	    	case MANUAL:
	    		payment.processPayment(Payment.getUndefinedPaymentStatus(), balanceDue);
	    		break;
    	}
    
    	JOptionPane.showMessageDialog(frame, msgToFrame);
        frame.dispose();
    }


}
