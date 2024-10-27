package BusBooking;
import java.awt.*;
import javax.swing.*;

public class UserLoginPage {
	public static void main(String[] args) {
		
		JFrame f = new JFrame("Admin Login Page");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 850);
        //Border border = BorderFactory.createLineBorder(Color.ORANGE);
        //f.add(border);
        
        f.setLayout(null);
   
        JLabel head = new JLabel("User Login", JLabel.CENTER);
        head.setFont(new Font("Times New Roman",Font.BOLD,26));
        head.setForeground(Color.red);
        head.setBounds(635, 0, 150, 30);  // Set bounds for the label
        f.add(head);
        //Color c =new Color(100,100,100,100)
        f.getContentPane().setBackground(new Color(102,0,153,100));
        JLabel l1 = new JLabel("Username:");
        l1.setFont(new Font("Times New Roman",Font.PLAIN,18));
        l1.setBounds(550, 100, 150, 30);
        //l1.setForeground(Color.GREEN);
        f.add(l1);

        JTextField t1 = new JTextField(20);
        t1.setFont(new Font("Times New Roman",Font.PLAIN,18));
        t1.setBounds(650, 100, 200, 30);
        f.add(t1);

        JLabel l2 = new JLabel("Password:");
        l2.setFont(new Font("Times New Roman",Font.PLAIN,18));
        l2.setBounds(550, 140, 150, 30);
        f.add(l2);

        JPasswordField t2 = new JPasswordField(20);
        t2.setBounds(650, 140, 200, 30);
        f.add(t2);

        JButton b = new JButton("Login");
        b.setFont(new Font("Times New Roman",Font.PLAIN,16));
        b.setBounds(630, 200, 100, 30);
        f.add(b);

        b.addActionListener(e -> {
            String username = t1.getText();
            String password = new String(t2.getPassword());

            if (username.equals("user") && password.equals("123")) {
                JOptionPane.showMessageDialog(f, "Login Successful!");
            }
            else if(username.equals("") && password.equals("")) {
            	JOptionPane.showMessageDialog(f, "Username and password not entered", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(f, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JLabel l3 = new JLabel("Not have an account?");
        l3.setFont(new Font("Times New Roman",Font.PLAIN,17));
        l3.setBounds(765, 235, 155, 50);
        //l2.setFont(new Font("Algerian", Font.PLAIN, 18));
        f.add(l3);
        
        JButton b1 = new JButton("Sign Up");
        b1.setFont(new Font("Times New Roman",Font.PLAIN,16));
        b1.setBounds(785, 275, 100, 30);
        f.add(b1);
        
        f.setVisible(true);
	}

}
