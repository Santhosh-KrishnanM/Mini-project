package BusBooking;
import javax.swing.*; 
//import java.awt.*;

public class Main1 {
    public static void main(String[] args) {
        UserLogin loginPage = new UserLogin();
        loginPage.setVisible(true);
    }
}

class UserLogin extends JFrame{

	public UserLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(445, 335);
        
        setLayout(null);  

        JLabel headApp = new JLabel("Bus Booking Login Page", JLabel.CENTER);
        headApp.setBounds(135, 20, 140, 50);  
        add(headApp);
        
        JLabel head = new JLabel("User Login", JLabel.CENTER);
        
       
        head.setBounds(150, 45, 100, 30);  

        JLabel l1 = new JLabel("Username:");
        l1.setBounds(50, 80, 100, 30);
        add(l1);

        JTextField t1 = new JTextField(20);
        t1.setBounds(150, 80, 150, 30);
        add(t1);

        JLabel l2 = new JLabel("Password:");
        l2.setBounds(50, 130, 100, 30);
        add(l2);

        JPasswordField t2 = new JPasswordField(20);
        t2.setBounds(150, 130, 150, 30);
        add(t2);

        JButton b = new JButton("Login");
        b.setBounds(150, 180, 100, 30);
        add(b);

        b.addActionListener(e -> {
            String username = t1.getText();
            String password = new String(t2.getPassword());

            // Validate credentials
            if (username.equals("nishanth") && password.equals("miniproject")) {
                JOptionPane.showMessageDialog(b, "Login Successful!");
            }
            else if(username.equals("") && password.equals("")) {
            	JOptionPane.showMessageDialog(b, "Username and password not entered", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(b, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        JLabel l3 = new JLabel("Not have an account");
        l3.setBounds(280, 200, 125, 50);
        add(l3);
        
        JButton b1 = new JButton("Sign Up");
        b1.setBounds(295, 235, 100, 30);
        add(b1);

        setVisible(true);
       
	}

}
