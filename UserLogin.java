package Miniproject;

import javax.swing.*;
import java.sql.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;

public class UserLogin extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtmob;
	UserLogin() {
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 613, 532);
        setTitle("User Login Page");
        
        getContentPane().setLayout(null);
   
        JLabel head = new JLabel("User Login", JLabel.CENTER);
        head.setFont(new Font("Times New Roman",Font.BOLD,26));
        head.setForeground(Color.red);
        head.setBounds(216, 10, 150, 30);  
        getContentPane().add(head);
        getContentPane().setBackground(new Color(255, 255, 255));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(32, 178, 170));
        panel.setBounds(10, 59, 579, 426);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Email");
        lblNewLabel.setBounds(61, 52, 135, 30);
        panel.add(lblNewLabel);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        
        JTextField txtemail = new JTextField(20);
        txtemail.setBounds(263, 53, 200, 30);
        panel.add(txtemail);
        txtemail.setFont(new Font("Times New Roman",Font.PLAIN,18));
        JLabel l1 = new JLabel("OR");
        l1.setBounds(188, 113, 150, 30);
        panel.add(l1);
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        l1.setFont(new Font("Times New Roman", Font.PLAIN, 19));
                                
        JLabel l2 = new JLabel("Mobile Number");
        l2.setBounds(83, 168, 135, 30);
        panel.add(l2);
        l2.setHorizontalAlignment(SwingConstants.CENTER);
        l2.setFont(new Font("Times New Roman",Font.PLAIN,18));
                                
        JButton b = new JButton("Login");
        b.setBounds(220, 236, 100, 30);
        panel.add(b);
        b.setFont(new Font("Times New Roman",Font.PLAIN,16));
                                 
        JLabel l3 = new JLabel("Not have an account");
        l3.setBounds(381, 293, 155, 30);
        panel.add(l3);
        l3.setFont(new Font("Times New Roman",Font.PLAIN,17));
                                               
        JButton b1 = new JButton("Sign Up");
        b1.setBounds(401, 333, 100, 30);
        panel.add(b1);
        b1.setFont(new Font("Times New Roman",Font.PLAIN,16));
        
        txtmob = new JTextField(20);
        txtmob.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        txtmob.setBounds(263, 168, 200, 30);
        panel.add(txtmob);
        b1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		UserRegister user = new UserRegister();
        		user.setVisible(true);
        		setVisible(false);  
        	}
        });
        b.addActionListener(e -> {
        	try {
        		Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Kishore@2011");
                Statement stmt = con.createStatement();
                String sql = "Select * from register where email='"+txtemail.getText()+"' or mobile='"+txtmob.getText().toString()+"' ";
                ResultSet rs = stmt.executeQuery(sql);
                if(rs.next()) {
                	String email = txtemail.getText();
                	String mob = txtmob.getText();
                	userHome1 uh = new userHome1(email, mob);
                	uh.setVisible(true) ;
                	setVisible(false);
                }else 
                	JOptionPane.showMessageDialog(null, "Login not Successfully");
                con.close();
        	}
        	catch(Exception e1) {
				e1.printStackTrace();
        	}
        });

	}
	
	public static void main(String[] args) {
		UserLogin user = new UserLogin();
		user.setVisible(true);
	}
}
