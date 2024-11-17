package Miniproject;

import java.sql.*;
import java.awt.Font;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class userHome1 extends JFrame {
    private static final long serialVersionUID = 1L;
    public static String email;
    public static String mob;

    // Declare instance variables for UI components
    private JLabel setName, setMob, setEmail, setGen, setAge;
    private JTextArea setAdd;

    userHome1(String email, String mob) {
        userHome1.email = email;
        userHome1.mob = mob;

        setTitle("User  Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        
        JTabbedPane tabPanel = new JTabbedPane();
        tabPanel.setBackground(Color.WHITE);

        // Profile Panel
        JPanel page1 = createProfilePanel();
        tabPanel.addTab("Profile", page1);

        // History Panel
        JPanel page2 = createHistoryPanel();
        tabPanel.addTab("History", page2);

        // Bookings Panel
        JPanel page3 = createBookingsPanel();
        tabPanel.addTab("Bookings", page3);

        // Wallet Panel
        JPanel page4 = createWalletPanel();
        tabPanel.addTab("Wallet", page4);

        getContentPane().add(tabPanel);
        setVisible(true);

        // Load user data
        loadUser_Data();
    }

    private JPanel createProfilePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblName.setBounds(35, 20, 131, 21);
        panel.add(lblName);

        JLabel lblMobileNumber = new JLabel("Mobile Number:");
        lblMobileNumber.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblMobileNumber.setBounds(35, 67, 131, 21);
        panel.add(lblMobileNumber);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblEmail.setBounds(35, 111, 131, 21);
        panel.add(lblEmail);

        JLabel lblGender = new JLabel("Gender:");
        lblGender.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblGender.setBounds(35, 164, 131, 21);
        panel.add(lblGender);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblAge.setBounds(35, 206, 131, 21);
        panel.add(lblAge);

        setName = new JLabel("");
        setName.setBounds(205, 20, 131, 21);
        panel.add(setName);

        setMob = new JLabel("");
        setMob.setBounds(205, 67, 131, 21);
        panel.add(setMob);

        setEmail = new JLabel("");
        setEmail.setBounds(205, 111, 131, 21);
        panel.add(setEmail);

        setGen = new JLabel("");
        setGen.setBounds(205, 164, 131, 21);
        panel.add(setGen);

        setAge = new JLabel("");
        setAge.setBounds(205, 206, 131, 21);
        panel.add(setAge);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblAddress.setBounds(35, 261, 131, 21);
        panel.add(lblAddress);

        setAdd = new JTextArea(); // Initialize the variable
        setAdd.setBounds(205, 259 , 252, 59);
        panel.add(setAdd);

        JButton update = new JButton("Change");
        update.setFont(new Font("Times New Roman", Font.BOLD, 12));
        update.setBounds(407, 361, 103, 31);
        update.addActionListener(e -> {
            // Placeholder for future functionality
            setVisible(false);
        });
        panel.add(update);

        return panel;
    }

    private JPanel createHistoryPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        JLabel label = new JLabel("No recent Booking");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        panel.add(label);
        return panel;
    }

    private JPanel createBookingsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        JLabel lblNoAvailableBookings = new JLabel("No available bookings");
        lblNoAvailableBookings.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblNoAvailableBookings.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblNoAvailableBookings);
        return panel;
    }

    private JPanel createWalletPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        JLabel lblWalletAmount = new JLabel("Wallet amount");
        lblWalletAmount.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblWalletAmount.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblWalletAmount);

        JLabel walletBalance = new JLabel("");
        walletBalance.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        panel.add(walletBalance);
        return panel;
    }

    private void loadUser_Data() { // Corrected method name
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Kishore@2011");
             PreparedStatement p = con.prepareStatement("SELECT * FROM register WHERE email = ? OR mobile = ?")) {
            p.setString(1, email);
            p.setString(2, mob);
            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) {
                    setName.setText(rs.getString("name"));
                    setMob.setText(rs.getString("mobile"));
                    setEmail.setText(rs.getString("email"));
                    setGen.setText(rs.getString("gender"));
                    setAge.setText(rs.getString("age"));
                    setAdd.setText(rs.getString("address"));
                } else {
                    setName.setText("Not found");
                    setMob.setText("Not found");
                    setEmail.setText("Not found");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading user data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        userHome1 userh = new userHome1(email, mob);
        userh.setVisible(true);
    }
}
