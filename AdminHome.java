package Miniproject;

import java.sql.*;
import java.awt.Font;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminHome extends JFrame {
    private static final long serialVersionUID = 1L;
    public static String email;
    public static String mob;
    public static String pass;
  

    // Declare instance variables for UI components
    private JLabel setName, setMob, setEmail, setGen, setAge;
    private JTextArea setAdd;
    private JTextField textField;
    private JComboBox<String> sourceComboBox;
    String mobno;

    // Fields for Add Ticket panel
    private JTextField busIDField, busCapacityField, seatsAvailableField, sourceField, destinationField, timeAvailableField, dateAvailableField;
    private JTextField bookingSourceField;

    // Constructor
    AdminHome(String email, String mob, String pass) {
        AdminHome.email = email;
        AdminHome.mob = mob;
        AdminHome.pass = pass;
        //System.out.println(mob);
        
     

        setTitle("Admin Home");
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
        
        // Add Ticket Panel
        JPanel page5 = createAddTicketPanel();
        tabPanel.addTab("Add Ticket", page5);

        getContentPane().add(tabPanel);
        setVisible(true);
     // Load user data
        loadUser_Data();
        
        mobno=setMob.getText();
    }

    private JPanel createWalletPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(216, 191, 216));
        panel.setLayout(null);
        JLabel lblWalletAmount = new JLabel("Wallet amount");
        lblWalletAmount.setBounds(140, 32, 96, 25);
        lblWalletAmount.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblWalletAmount.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblWalletAmount);
        
        textField = new JTextField("0");
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setEditable(true);
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        textField.setBounds(277, 30, 112, 31);
        panel.add(textField);
        textField.setColumns(10);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> {
            String walletAmount = textField.getText();
            // Simple validation for empty string
            if (walletAmount.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Enter a wallet amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Kishore@2011");
                 PreparedStatement p = con.prepareStatement("UPDATE admin SET wallet = ? WHERE email = ? OR mobile = ?")) {
                p.setString(1, walletAmount);
                p.setString(2, email);
                p.setString(3, mob);
                int rowsUpdated = p.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(panel, "Wallet updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(panel, "Failed to update wallet. Check your account.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(panel, "Database error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 15));
        btnUpdate.setBounds(209, 89, 112, 31);
        panel.add(btnUpdate);
        
        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(10, 10, 105, 21);
        panel.add(btnLogout);
        
        return panel;
    }

    // Creating profile panel
    private JPanel createProfilePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(216, 191, 216));
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

        setAdd = new JTextArea();
        setAdd.setBounds(206, 316, 252, 59);
        panel.add(setAdd);

        JButton update = new JButton("Change");
        update.setFont(new Font("Times New Roman", Font.BOLD, 12));
        update.setBounds(449, 395, 103, 31);
        update.addActionListener(e -> {
            userProfile up = new userProfile();
            up.setVisible(true);
            setVisible(false);
        });
        panel.add(update);

        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(e -> {
            AdminLogin ul = new AdminLogin();
            setVisible(false);
            ul.setVisible(true);
        });
        btnLogout.setBounds(0, 10, 85, 21);
        panel.add(btnLogout);

        return panel;
    }

    // Creating history panel
    private JPanel createHistoryPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(216, 191, 216));
        panel.setLayout(null);
        
        JButton btnLogout_1 = new JButton("Logout");
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

    // Creating bookings panel
    private JPanel createBookingsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(216, 191, 216));
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
            ps.setString(1,mobNo);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.append(rs.getString("MobNo")).append(" 	");
                result.append(rs.getString("UserName")).append("	 ");
                result.append(rs.getInt("BusId")).append("	 ");
                result.append(rs.getString("Source")).append("	 ");
                result.append(rs.getString("Destination")).append(" 	");
                result.append(rs.getString("SeatNo")).append("	 ").append("\n");
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            return null;
        }
        return result.toString().trim();
    }

    

    // Creating Add Ticket panel
    private JPanel createAddTicketPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(216, 191, 216));
        panel.setLayout(null);
        
        JLabel lblBusID = new JLabel("Bus ID:");
        lblBusID.setBounds(30, 20, 100, 25);
        panel.add(lblBusID);

        busIDField = new JTextField();
        busIDField.setBounds(140, 20, 150, 25);
        panel.add(busIDField);

        JLabel lblBusCapacity = new JLabel("Capacity:");
        lblBusCapacity.setBounds(30, 60, 100, 25);
        panel.add(lblBusCapacity);

        busCapacityField = new JTextField();
        busCapacityField.setBounds(140, 60, 150, 25);
        panel.add(busCapacityField);

        JLabel lblSeatsAvailable = new JLabel("Seats Available:");
        lblSeatsAvailable.setBounds(30, 100, 100, 25);
        panel.add(lblSeatsAvailable);

        seatsAvailableField = new JTextField();
        seatsAvailableField.setBounds(140, 100, 150, 25);
        panel.add(seatsAvailableField);

        JLabel lblSource = new JLabel("Source:");
        lblSource.setBounds(30, 140, 100, 25);
        panel.add(lblSource);

        sourceField = new JTextField();
        sourceField.setBounds(140, 140, 150, 25);
        panel.add(sourceField);

        JLabel lblDestination = new JLabel("Destination:");
        lblDestination.setBounds(30, 180, 100, 25);
        panel.add(lblDestination);

        destinationField = new JTextField();
        destinationField.setBounds(140, 180, 150, 25);
        panel.add(destinationField);

        JLabel lblTimeAvailable = new JLabel("Time Available:");
        lblTimeAvailable.setBounds(30, 220, 100, 25);
        panel.add(lblTimeAvailable);

        timeAvailableField = new JTextField();
        timeAvailableField.setBounds(140, 220, 150, 25);
        panel.add(timeAvailableField);

        JLabel lblDateAvailable = new JLabel("Date Available:");
        lblDateAvailable.setBounds(30, 260, 100, 25);
        panel.add(lblDateAvailable);

        dateAvailableField = new JTextField();
        dateAvailableField.setBounds(140, 260, 150, 25);
        panel.add(dateAvailableField);

        JButton btnAdd = new JButton("Add Ticket");
        btnAdd.setBounds(30, 300, 120, 25);
        btnAdd.addActionListener(e -> {
            String input = busIDField.getText() + " " +
                    busCapacityField.getText() + " " +
                    seatsAvailableField.getText() + " " +
                    sourceField.getText() + " " +
                    destinationField.getText() + " " +
                    timeAvailableField.getText() + " " +
                    dateAvailableField.getText();

            if (Add(input)) {
                JOptionPane.showMessageDialog(panel, "Bus added successfully!");
            } else {
                JOptionPane.showMessageDialog(panel, "Failed to add bus.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(btnAdd);

        JButton btnUpdate = new JButton("Update Ticket");
        btnUpdate.setBounds(160, 300, 120, 25);
        btnUpdate.addActionListener(e -> {
            String input = busIDField.getText() + " " +
                    busCapacityField.getText() + " " +
                    seatsAvailableField.getText() + " " +
                    sourceField.getText() + " " +
                    destinationField.getText() + " " +
                    timeAvailableField.getText() + " " +
                    dateAvailableField.getText();

            if (Update(input)) {
                JOptionPane.showMessageDialog(panel, "Bus updated successfully!");
            } else {
                JOptionPane.showMessageDialog(panel, "Failed to update bus.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(btnUpdate);

        JButton btnDelete = new JButton("Delete Ticket");
        btnDelete.setBounds(290, 300, 120, 25);
        btnDelete.addActionListener(e -> {
            String busID = busIDField.getText();
            if (Delete(busID)) {
                JOptionPane.showMessageDialog(panel, "Bus deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(panel, "Failed to delete bus.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(btnDelete);

        JButton btnGet = new JButton("Get Ticket Info");
        btnGet.setBounds(420, 300, 120, 25);
        btnGet.addActionListener(e -> {
            String busID = busIDField.getText();
            String info = Get(busID);
            if (info != null) {
                JOptionPane.showMessageDialog(panel, info, "Bus Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panel, "No data found for this Bus ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(btnGet);

        return panel;
    }

    // Load user data from the database
    private void loadUser_Data() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Kishore@2011");
             PreparedStatement p = con.prepareStatement("SELECT * FROM admin WHERE email = ? OR mobile = ?")) {
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

    public static boolean Add(String temp) {
        try {
            String[] up = temp.split(" ");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Kishore@2011");
            String query = "INSERT INTO bus (Bus_Id, Bus_Capacity, Seats_Available, Source, Destination, Time_Available, Date_Available) VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(up[0]));
            ps.setInt(2, Integer.parseInt(up[1]));
            ps.setInt(3, Integer.parseInt(up[2]));
            ps.setString(4, up[3]);
            ps.setString(5, up[4]);
            ps.setString(6, up[5]);
            ps.setDate(7, java.sql.Date.valueOf(up[6]));

            ps.executeUpdate();
            return true;

        } catch (SQLException | NumberFormatException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static boolean Update(String temp) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Kishore@2011");
            String[] up = temp.split(" ");
            String query = "UPDATE bus SET Bus_Capacity = ?, Seats_Available = ?, Source = ?, Destination = ?, Time_Available = ?, Date_Available = ? WHERE Bus_Id = ?;";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, Integer.parseInt(up[1]));
            ps.setInt(2, Integer.parseInt(up[2]));
            ps.setString(3, up[3]);
            ps.setString(4, up[4]);
            ps.setString(5, up[5]);
            ps.setDate(6, java.sql.Date.valueOf(up[6]));
            ps.setInt(7, Integer.parseInt(up[0]));

            ps.executeUpdate();
            return true;

        } catch (SQLException | NumberFormatException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean Delete(String temp) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Kishore@2011");
            String tem = "DELETE FROM bus WHERE Bus_ID = ?;";
            PreparedStatement stmt = con.prepareStatement(tem);
            stmt.setString(1, temp);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    public String Get(String temp) {
        StringBuilder result = new StringBuilder();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Kishore@2011");
            String query = "SELECT * FROM bus WHERE Bus_id = ?;";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, Integer.parseInt(temp));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result.append("Bus ID: ").append(rs.getInt("Bus_id")).append("\n");
                result.append("Capacity: ").append(rs.getInt("Bus_Capacity")).append("\n");
                result.append("Seats Available: ").append(rs.getInt("Seats_Available")).append("\n");
                result.append("Source: ").append(rs.getString("Source")).append("\n");
                result.append("Destination: ").append(rs.getString("Destination")).append("\n");
                result.append("Time Available: ").append(rs.getString("Time_Available")).append("\n");
                result.append("Date Available: ").append(rs.getDate("Date_Available")).append("\n");
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            return null;
        }
        return result.toString().trim(); // Removing any trailing spaces
    }

    public boolean BusBookingIn(String input){
        String query = "INSERT INTO bookings (MobNo, UserName, BusId, Source, Destination, SeatNo) VALUES (?, ?, ?, ?, ?, ?);";
        String updateQuery = "UPDATE bus SET Seats_Available = Seats_Available - 1 WHERE BusId = ? AND Seats_Available > 0;";

        
        try{
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Kishore@2011");
        	PreparedStatement ps = con.prepareStatement(query);
            PreparedStatement updatePs = con.prepareStatement(updateQuery);
            String[] values = input.split(" ");
            updatePs.setInt(1, Integer.parseInt(values[2]));
            ps.setString(1, values[0]); // MobNo
            ps.setString(2, values[1]); // UserName
            ps.setInt(3, Integer.parseInt(values[2])); // BusId
            ps.setString(4, values[3]); // Source
            ps.setString(5, values[4]); // Destination
            ps.setInt(6, Integer.parseInt(values[5])); // SeatNo

            System.out.println(ps); // Printing the prepared statement for debugging
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Inserted");


        } catch (SQLException | NumberFormatException e) {
            System.err.println("SQL error: " + e.getMessage());
            return false;
        }
        return true;
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
        } catch (Exception e) {
            e.printStackTrace();
           // return "SQL error: " + e.getMessage();
        }
        //con.close();
        return result.toString().trim();
    }

    public static void main(String[] args) {
        AdminHome adminh = new AdminHome("example@example.com", "1234567890", "password123");
        adminh.setVisible(true);
    }
}
