package Miniproject;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;


public class UserRegister {

	private JFrame frmSignUp;
	private JTextField txtname;
	private JTextField txtmob;
	private JTextField txtemail;
	private JTextField txtage;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRegister window = new UserRegister();
					window.frmSignUp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserRegister() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSignUp = new JFrame();
		frmSignUp.setTitle("Sign Up");
		frmSignUp.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\selva\\Downloads\\Related Wallpapers - Register Icon Png White Png Image With Transparent Background.jpg"));
		frmSignUp.getContentPane().setBackground(new Color(100, 149, 237));
		frmSignUp.setBounds(100, 100, 613, 532);
		frmSignUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSignUp.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sign Up");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 586, 35);
		frmSignUp.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(10, 35, 381, 450);
		frmSignUp.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel name = new JLabel("Name");
		name.setFont(new Font("Times New Roman", Font.BOLD, 13));
		name.setBounds(30, 30, 74, 24);
		panel.add(name);
		
		JLabel mob = new JLabel("Mobile No.");
		mob.setFont(new Font("Times New Roman", Font.BOLD, 13));
		mob.setBounds(30, 92, 74, 24);
		panel.add(mob);
		
		JLabel email = new JLabel("Email");
		email.setFont(new Font("Times New Roman", Font.BOLD, 13));
		email.setBounds(30, 154, 74, 24);
		panel.add(email);
		
		JLabel gender = new JLabel("Gender");
		gender.setFont(new Font("Times New Roman", Font.BOLD, 13));
		gender.setBounds(30, 223, 74, 24);
		panel.add(gender);
		
		JLabel age = new JLabel("Age");
		age.setFont(new Font("Times New Roman", Font.BOLD, 13));
		age.setBounds(30, 279, 74, 24);
		panel.add(age);
		
		JLabel address = new JLabel("Address");
		address.setFont(new Font("Times New Roman", Font.BOLD, 13));
		address.setBounds(30, 340, 74, 24);
		panel.add(address);
		
		txtname = new JTextField();
		txtname.setBounds(145, 33, 159, 19);
		panel.add(txtname);
		txtname.setColumns(10);
		
		txtmob = new JTextField();
		txtmob.setColumns(10);
		txtmob.setBounds(145, 95, 159, 19);
		panel.add(txtmob);
		
		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(145, 157, 159, 19);
		panel.add(txtemail);
		
		txtage = new JTextField();
		txtage.setColumns(10);
		txtage.setBounds(145, 282, 159, 19);
		panel.add(txtage);
		
		JTextArea txtadd = new JTextArea();
		txtadd.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtadd.setBounds(145, 340, 159, 73);
		panel.add(txtadd);
		
		JRadioButton rbmale = new JRadioButton("Male");
		rbmale.setBackground(new Color(255, 255, 255));
		rbmale.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		buttonGroup.add(rbmale);
		rbmale.setBounds(144, 225, 68, 21);
		panel.add(rbmale);
		
		JRadioButton rbfemale = new JRadioButton("Female");
		rbfemale.setBackground(new Color(255, 255, 255));
		rbfemale.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		buttonGroup.add(rbfemale);
		rbfemale.setBounds(230, 225, 74, 21);
		panel.add(rbfemale);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Kishore@2011");
                    String query = "insert into register values(?,?,?,?,?,?)";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1,txtname.getText());
                    ps.setString(2,txtmob.getText());
                    ps.setString(3,txtemail.getText());
                    if(rbmale.isSelected())
                    	ps.setString(4,rbmale.getText());
                    else
                    	ps.setString(4,rbfemale.getText());
                    ps.setInt(5,Integer.parseInt(txtage.getText()));  
                    ps.setString(6, txtadd.getText());
                    int i = ps.executeUpdate();
                    JOptionPane.showMessageDialog(btnNewButton, i+" Signed up successfully");
                    ps.close();
                    con.close();
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton.setBounds(145, 423, 85, 21);
		panel.add(btnNewButton);
		frmSignUp.setVisible(true);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
