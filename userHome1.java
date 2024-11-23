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
    private JTextField textField;
    private JComboBox<String> sourceComboBox;
    private JTextField bookingSourceField;
    
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
        panel.setBackground(new Color(240, 230, 140));
        panel.setLayout(null);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblName.setBounds(35, 82, 131, 21);
        panel.add(lblName);

        JLabel lblMobileNumber = new JLabel("Mobile Number:");
        lblMobileNumber.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblMobileNumber.setBounds(35, 126, 131, 21);
        panel.add(lblMobileNumber);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblEmail.setBounds(35, 180, 131, 21);
        panel.add(lblEmail);

        JLabel lblGender = new JLabel("Gender:");
        lblGender.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblGender.setBounds(35, 226, 131, 21);
        panel.add(lblGender);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblAge.setBounds(35, 273, 131, 21);
        panel.add(lblAge);

        setName = new JLabel("");
        setName.setBounds(205, 82, 131, 21);
        panel.add(setName);

        setMob = new JLabel("");
        setMob.setBounds(205, 126, 131, 21);
        panel.add(setMob);

        setEmail = new JLabel("");
        setEmail.setBounds(205, 180, 131, 21);
        panel.add(setEmail);

        setGen = new JLabel("");
        setGen.setBounds(205, 226, 131, 21);
        panel.add(setGen);

        setAge = new JLabel("");
        setAge.setBounds(205, 273, 131, 21);
        panel.add(setAge);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblAddress.setBounds(35, 332, 131, 21);
        panel.add(lblAddress);

        setAdd = new JTextArea(); // Initialize the variable
        setAdd.setEditable(false);
        setAdd.setBounds(206, 316 , 252, 59);
        panel.add(setAdd);

        JButton update = new JButton("Change");
        update.setFont(new Font("Times New Roman", Font.BOLD, 12));
        update.setBounds(449, 395, 103, 31);
        update.addActionListener(e -> {
            // Placeholder for future functionality
        	userProfile up = new userProfile();
        	up.setVisible(true);
            setVisible(false);
        });
        panel.add(update);
        
        JButton btnNewButton = new JButton("Logout");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		UserLogin ul = new UserLogin() ;
        		setVisible(false);
        		ul.setVisible(true);
        	}
        });
        btnNewButton.setBounds(32, 10, 85, 21);
        panel.add(btnNewButton);

        return panel;
    }

    private JPanel createHistoryPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 230, 140));
        panel.setLayout(null);
        
        JButton btnLogout_1 = new JButton("Logout");
        btnLogout_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnLogout_1.setBounds(10, 6, 85, 21);
        btnLogout_1.addActionListener(e -> {
            UserLogin ul = new UserLogin();
            setVisible(false);
            ul.setVisible(true);
        });
        panel.add(btnLogout_1);
        
        JTextArea textArea = new JTextArea();
        textArea.setBounds(42, 61, 482, 219);
        panel.add(textArea);
        String temp=setName.getText();
        System.out.println(temp);
        //textArea.setText(getHistory(mobno));
        
        JButton btnNewButton_1 = new JButton("Update");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textArea.setText(getHistory(setMob.getText()));
        	}
        });
        btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
        btnNewButton_1.setBounds(246, 398, 109, 28);
        panel.add(btnNewButton_1);

        return panel;
    }

    private JPanel createBookingsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 230, 140));
        panel.setLayout(null);

        JLabel lblBookingMob = new JLabel("Source:");
        lblBookingMob.setBounds(30, 76, 100, 25);
        panel.add(lblBookingMob);

        bookingSourceField = new JTextField();
        bookingSourceField.setBounds(140, 76, 150, 25);
        
        
        panel.add(bookingSourceField);
        
        JButton btnNewButton = new JButton("Find");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String bkSource = bookingSourceField.getText();
        		String result[]=FindBus(bkSource).split("\n");
        		sourceComboBox.removeAllItems();
        		//sourceComboBox.removeAll();
        		for (int i=0;i<result.length;i++) {
        			System.out.println(result[i]);
        			sourceComboBox.addItem(result[i]);
        		}
        		
        	
        		
        	}
        });
        btnNewButton.setBounds(324, 77, 129, 23);
        panel.add(btnNewButton);

        JLabel lblSource = new JLabel("Source:");
        lblSource.setBounds(30, 111, 100, 25);
        panel.add(lblSource);

        sourceComboBox = new JComboBox<>();
        sourceComboBox.setBounds(140, 111, 313, 25);
        panel.add(sourceComboBox);

        JButton btnBook = new JButton("Book");
        btnBook.setBounds(227, 378, 100, 25);
        btnBook.addActionListener(e -> {
            try {
                String source = (String) sourceComboBox.getSelectedItem();
                System.out.println(source);
                String sour[]=source.split(",");
                String input= setMob.getText()+" "+setName.getText()+" "+sour[0]+sour[1]+sour[2]+" "+"9"; // Example seat number
                System.out.println(input);
                if (BusBookingIn(input)) {
                    JOptionPane.showMessageDialog(panel, "Booking successful.");
                } else {
                    JOptionPane.showMessageDialog(panel, "Booking failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(panel, "Booking failed: " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(btnBook);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnLogout.addActionListener(e -> {
            UserLogin ul = new UserLogin();
            setVisible(false);
            ul.setVisible(true);
        });
        btnLogout.setBounds(10, 10, 100, 21);
        panel.add(btnLogout);
        
        

        //populateComboBoxes(); // Call to populate combo boxes

        return panel;
    }
    
    public String getHistory(String mobNo) {
        StringBuffer result=new StringBuffer();
        try {
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Kishore@2011");
            String query = "SELECT * FROM bookings WHERE MobNo = ?;";
            PreparedStatement ps = con.prepareStatement(query);
           
            //ps.setInt(1, Integer.parseInt(mobNo));
            ps.setString(1, mobNo);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.append(rs.getString("MobNo")).append(" 	");
                result.append(rs.getString("UserName")).append("	 ");
                result.append(rs.getInt("BusId")).append("	 ");
                result.append(rs.getString("Source")).append("	 ");
                result.append(rs.getString("Destination")).append(" 	");
                result.append(rs.getString("SeatNo")).append("\n");
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            return null;
        }
        return result.toString().trim();
    }

    private JPanel createWalletPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 230, 140));
        panel.setLayout(null);

        // Label for displaying the wallet amount
        JLabel lblWalletAmount = new JLabel("Wallet Amount:");
        lblWalletAmount.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblWalletAmount.setHorizontalAlignment(SwingConstants.LEFT);
        lblWalletAmount.setBounds(130, 114, 96, 25);
        panel.add(lblWalletAmount);

        JLabel walletAmountLabel = new JLabel("0"); // Label to show wallet amount
        walletAmountLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        walletAmountLabel.setBounds(260, 114, 112, 25);
        panel.add(walletAmountLabel);

        // Load and display wallet amount from the database
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Kishore@2011");
             PreparedStatement ps = con.prepareStatement("SELECT wallet FROM user WHERE email = ? OR mobile = ?")) {
            ps.setString(1, email);
            ps.setString(2, mob);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                walletAmountLabel.setText(String.valueOf(rs.getInt("wallet")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Error fetching wallet amount: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Button to trigger wallet update
        JButton txtChange = new JButton("Update");
        txtChange.addActionListener(e -> {
            // Show pop-up for wallet update
            String newWalletAmount = JOptionPane.showInputDialog(this, "Enter new wallet amount:", "Update Wallet", JOptionPane.PLAIN_MESSAGE);
            if (newWalletAmount != null && !newWalletAmount.isEmpty()) {
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Kishore@2011");
                     PreparedStatement p = con.prepareStatement("UPDATE user SET wallet = ? WHERE email = ? OR mobile = ?")) {
                    p.setInt(1, Integer.parseInt(newWalletAmount));
                    p.setString(2, email);
                    p.setString(3, mob);
                    int rowsUpdated = p.executeUpdate();
                    if (rowsUpdated > 0) {
                        walletAmountLabel.setText(newWalletAmount); // Update label with new amount
                        JOptionPane.showMessageDialog(panel, "Wallet updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(panel, "Failed to update wallet.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Invalid wallet amount. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        txtChange.setFont(new Font("Times New Roman", Font.BOLD, 15));
        txtChange.setBounds(216, 189, 112, 31);
        panel.add(txtChange);

        // Logout button
        JButton btnNewButton = new JButton("Logout");
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnNewButton.addActionListener(e -> {
            UserLogin ul = new UserLogin();
            setVisible(false);
            ul.setVisible(true);
        });
        btnNewButton.setBounds(10, 15, 85, 21);
        panel.add(btnNewButton);

        return panel;
    }


    private void loadUser_Data() { // Corrected method name
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Kishore@2011");
             PreparedStatement p = con.prepareStatement("SELECT * FROM user WHERE email = ? OR mobile = ?")) {
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
                    setGen.setText("Not found");
                    setAge.setText("Not found");
                    setAdd.setText("Not found");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading user data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String FindBus(String source) {
        String query = "SELECT Bus_id, Source, Destination, Time_Available, Date_Available FROM bus WHERE Source = ? AND Seats_Available != 0;"; 
        StringBuilder result = new StringBuilder();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Kishore@2011");
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, source);
            ResultSet rs = ps.executeQuery();
            
            if (!rs.isBeforeFirst()) { // Check if the result set is empty
                return "No buses available from the specified source.";
            }

            while (rs.next()) {
                result.append(rs.getInt("Bus_id")).append(", ");
                result.append(rs.getString("Source")).append(", ");
                result.append(rs.getString("Destination")).append(", ");
                result.append(rs.getString("Time_Available")).append(", ");
                result.append(rs.getDate("Date_Available")).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "SQL error: " + e.getMessage();
        }

        return result.toString().trim();
    }
    
    public boolean BusBookingIn(String input) throws SQLException {    	
        String insertQuery = "INSERT INTO bookings (MobNo, UserName, BusId, Source, Destination, SeatNo) VALUES (?, ?, ?, ?, ?, ?);";
        String updateQuery = "UPDATE bus SET Seats_Available = Seats_Available - 1 WHERE Bus_id = ? AND Seats_Available > 0;";

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Kishore@2011")) {
            con.setAutoCommit(false); // Start transaction

            try (PreparedStatement ps = con.prepareStatement(insertQuery);
                 PreparedStatement updatePs = con.prepareStatement(updateQuery)) {
                String[] values = input.split(" ");

                // Insert booking data
                ps.setString(1, values[0]); // MobNo
                ps.setString(2, values[1]); // UserName
                ps.setInt(3, Integer.parseInt(values[2])); // BusId
                ps.setString(4, values[3]); // Source
                ps.setString(5, values[4]); // Destination
                ps.setString(6, values[5]); // SeatNo
                ps.executeUpdate();

                // Update seat availability
                updatePs.setInt(1, Integer.parseInt(values[2])); // BusId
                int rowsAffected = updatePs.executeUpdate();

                if (rowsAffected == 0) {
                    con.rollback(); // Revert changes if update failed
                    return false;
                }

                con.commit(); // Commit transaction
                return true;
            } catch (SQLException e) {
                con.rollback(); // Rollback transaction on failure
                throw e;
            }
        }
    }


    public static void main(String[] args) {
        // Example values for email and mobile
        userHome1 userh = new userHome1("example@example.com", " 1234567890");
        userh.setVisible(true);
    }
}
