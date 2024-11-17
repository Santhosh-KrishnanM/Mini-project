import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BusBookingApp extends JFrame {
    private JTextField passengerNameField;
    private JTextField busNumberField;
    private JTextField seatNumberField;
    private JTextField dateField;
    private JTextField bookingIdField;
    private JTextArea outputArea;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/bus_booking_db";
    private static final String USER = "root";
    private static final String PASS = "password"; // replace with your MySQL root password

    public BusBookingApp() {
        setTitle("Bus Booking Application");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        passengerNameField = new JTextField(20);
        busNumberField = new JTextField(20);
        seatNumberField = new JTextField(20);
        dateField = new JTextField(20);
        bookingIdField = new JTextField(20);
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        panel.add(new JLabel("Passenger Name:"));
        panel.add(passengerNameField);
        panel.add(new JLabel("Bus Number:"));
        panel.add(busNumberField);
        panel.add(new JLabel("Seat Number:"));
        panel.add(seatNumberField);
        panel.add(new JLabel("Date of Journey (YYYY-MM-DD):"));
        panel.add(dateField);
        panel.add(new JLabel("Booking ID (for cancel/view):"));
        panel.add(bookingIdField);

        JButton bookButton = new JButton("Book Ticket");
        JButton cancelButton = new JButton("Cancel Ticket");
        JButton viewButton = new JButton("View Booking Details");

        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bookTicket();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelTicket();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewBookingDetails();
            }
        });

        panel.add(bookButton);
        panel.add(cancelButton);
        panel.add(viewButton);
        panel.add(new JScrollPane(outputArea));

        add(panel);
    }

    private void bookTicket() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String passengerName = passengerNameField.getText();
            String busNumber = busNumberField.getText();
            int seatNumber = Integer.parseInt(seatNumberField.getText());
            String dateOfJourney = dateField.getText();

            String sql = "INSERT INTO bus_booking (passenger_name, bus_number, seat_number, date_of_journey) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, passengerName);
            pstmt.setString(2, busNumber);
            pstmt.setInt(3, seatNumber);
            pstmt.setDate(4, Date.valueOf(dateOfJourney));
            pstmt.executeUpdate();

            outputArea.setText("Ticket booked successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            outputArea.setText("Error booking ticket.");
        }
    }

    private void cancelTicket() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            int bookingId = Integer.parseInt(bookingIdField.getText());

            String sql = "DELETE FROM bus_booking WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookingId);
            pstmt.executeUpdate();

            outputArea.setText("Ticket canceled successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            outputArea.setText("Error canceling ticket.");
        }
    }

    private void viewBookingDetails() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            int bookingId = Integer.parseInt(bookingIdField.getText());

            String sql = "SELECT * FROM bus_booking WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookingId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                outputArea.setText("Booking ID: " + rs.getInt("id") + "\n" +
                                   "Passenger Name: " + rs.getString("passenger_name") + "\n" +
                                   "Bus Number: " + rs.getString("bus_number") + "\n" +
                                   "Seat Number: " + rs.getInt("seat_number") + "\n" +
                                   "Date of Journey: " + rs.getDate("date_of_journey"));
            } else {
                outputArea.setText("No booking found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            outputArea.setText("Error viewing booking details.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BusBookingApp().setVisible(true);
            }
        });
    }
}
