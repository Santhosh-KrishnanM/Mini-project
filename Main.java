package BusBooking;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Create an instance of the LoginPage and show it
    	JFrame f = new JFrame("Login Page");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 300);
        
        // Set a background image
        /*JLabel background = new JLabel(new ImageIcon("background.jpg")); // Replace with the path to your background image
        f.setContentPane(background);*/
        f.setLayout(null);  // Use null layout for absolute positioning

        // Create and position the login form components
        JLabel headApp = new JLabel("Bus Booking Login Page", JLabel.CENTER);
        headApp.setBounds(150, 20, 150, 50);  // Set bounds for the label
        f.add(headApp);
        
        JLabel head = new JLabel("User Login", JLabel.CENTER);
        head.setBounds(150, 45, 100, 30);  // Set bounds for the label
        f.add(head);

        JLabel l1 = new JLabel("Username:");
        l1.setBounds(50, 80, 100, 30);
        f.add(l1);

        JTextField t1 = new JTextField(20);
        t1.setBounds(150, 80, 150, 30);
        f.add(t1);

        JLabel l2 = new JLabel("Password:");
        l2.setBounds(50, 130, 100, 30);
        f.add(l2);

        JPasswordField t2 = new JPasswordField(20);
        t2.setBounds(150, 130, 150, 30);
        f.add(t2);

        JButton b = new JButton("Login");
        b.setBounds(150, 180, 100, 30);
        f.add(b);

        // Add the action listener for the login button
        b.addActionListener(e -> {
            String username = t1.getText();
            String password = new String(t2.getPassword());

            // Validate credentials
            if (username.equals("nishanth") && password.equals("miniproject")) {
                JOptionPane.showMessageDialog(f, "Login Successful!");
                HomePage homePage = new HomePage();
                homePage.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(f, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}

// LoginPage class to handle the login form
class HomePage extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HomePage() {
		// Set up the home page frame
		setTitle("Home Page");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setLayout(new FlowLayout());  
		// Add a welcome label
		JLabel welcomeLabel = new JLabel("Welcome to the Home Page!", JLabel.CENTER);
		add(welcomeLabel);
		setVisible(true);
	}
}

// HomePage class to handle the home screen

