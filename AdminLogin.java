package Miniproject;

import javax.swing.*;
import java.sql.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;

public class AdminLogin extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtmob;
	private JPasswordField passwordField;
	AdminLogin() {
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 613, 532);
        setTitle("admin Login Page");
        
        getContentPane().setLayout(null);
   
        JLabel head = new JLabel("Admin Login", JLabel.CENTER);
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
        lblNewLabel.setBounds(83, 52, 135, 30);
        panel.add(lblNewLabel);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        
        JTextField txtemail = new JTextField(20);
        txtemail.setBounds(263, 53, 200, 30);
        panel.add(txtemail);
        txtemail.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        JLabel l1 = new JLabel("OR");
        l1.setBounds(188, 113, 150, 30);
        panel.add(l1);
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        l1.setFont(new Font("Times New Roman", Font.PLAIN, 19));
                                
        JLabel l2 = new JLabel("Mobile Number");
        l2.setBounds(83, 168, 135, 30);
        panel.add(l2);
        l2.setHorizontalAlignment(SwingConstants.CENTER);
        l2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                                
        JButton b = new JButton("Login");
        b.setBounds(222, 330, 100, 30);
        panel.add(b);
        b.setFont(new Font("Times New Roman",Font.PLAIN,16));
                                 
        JLabel l3 = new JLabel("Not have an account");
        l3.setBounds(414, 316, 155, 30);
        panel.add(l3);
        l3.setFont(new Font("Times New Roman",Font.PLAIN,17));
                                               
        JButton b1 = new JButton("Sign Up");
        b1.setBounds(436, 356, 100, 30);
        panel.add(b1);
        b1.setFont(new Font("Times New Roman",Font.PLAIN,16));
        
        txtmob = new JTextField(20);
        txtmob.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        txtmob.setBounds(263, 168, 200, 30);
        panel.add(txtmob);
        
        JLabel lblpass = new JLabel("Password");
        lblpass.setHorizontalAlignment(SwingConstants.CENTER);
        lblpass.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblpass.setBounds(89, 240, 129, 30);
        panel.add(lblpass);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        passwordField.setBounds(263, 236, 200, 30);
        panel.add(passwordField);
        b1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		AdminRegister admin = new AdminRegister();
        		admin.setVisible(true);
        		setVisible(false);  
        	}
        });
        b.addActionListener(e -> {
        	try {
        		Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Kishore@2011");
                Statement stmt = con.createStatement();
                String sql = "Select * from admin where email='"+txtemail.getText()+"' or mobile='"+txtmob.getText().toString()+"' and password='"+passwordField.getPassword().toString()+"'";
                ResultSet rs = stmt.executeQuery(sql);
                if(rs.next()) {
                	String email = txtemail.getText();
                	String mob = txtmob.getText();
                	String pas = passwordField.getPassword().toString();
                	AdminHome adh = new AdminHome(email, mob,pas);
                	adh.setVisible(true) ;
                	setVisible(false);
                }else 
                	JOptionPane.showMessageDialog(null, "Invalid Login");
                con.close();
        	}
        	catch(Exception e1) {
				e1.printStackTrace();
        	}
        });

	}
	
	public static void main(String[] args) {
		AdminLogin admin = new AdminLogin();
		admin.setVisible(true);
	}
}
