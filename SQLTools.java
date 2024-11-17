import javax.swing.*;
import java.sql.*;
public class SQLTools {
    static Connection con;
    static Statement stmt;

    SQLTools() {
        try {
            System.out.println("hi");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/miniproject", "root", "Kingqueen*1");
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("I'm here:");
        }
    }

    public static boolean Add(String temp) {
        try {
            String up[] = temp.split(" ");
            String query = "INSERT INTO bus (Bus_Id, Bus_Capacity, Seats_Available, Source, Destination, Time_Available, Date_Available) VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(up[0]));
            ps.setInt(2, Integer.parseInt(up[1]));
            ps.setInt(3, Integer.parseInt(up[2]));
            ps.setString(4,up[3]);
            ps.setString(5, up[4]);
            ps.setString(6, up[5]);
            ps.setDate(7, java.sql.Date.valueOf(up[6]));

            System.out.println(ps);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Inserted");
            return true;

        } catch (SQLException | NumberFormatException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }


    public static boolean Update(String temp) {
        try {
            String up[] = temp.split(" ");
            String query = "UPDATE bus SET Bus_Capacity = ?, Seats_Available = ?, Source = ?, Destination = ?, Time_Available = ?, Date_Available = ? WHERE Bus_Id = ?;";
            PreparedStatement ps = con.prepareStatement(query);


            ps.setInt(1, Integer.parseInt(up[1]));
            ps.setInt(2, Integer.parseInt(up[2]));
            ps.setString(3, up[3]);
            ps.setString(4, up[4]);
            ps.setString(5, up[5]);
            ps.setDate(6, java.sql.Date.valueOf(up[6]));
            ps.setInt(7, Integer.parseInt(up[0]));

            System.out.println(ps);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Updated");
            return true;

        } catch (SQLException | NumberFormatException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
    public boolean Delete(String temp) {
        try {
            String tem = "delete from bus where Bus_ID="+temp+";";
            stmt.executeUpdate(tem);
            System.out.println(tem);
        } catch (SQLException e) {
            return false;
        }
        JOptionPane.showMessageDialog(null,"Deleted");
        return true;

    }

    public String Get(String temp) {
        StringBuilder result = new StringBuilder();

        try {
            String query = "SELECT * FROM bus WHERE Bus_id = ?;";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, Integer.parseInt(temp));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result.append(rs.getInt("Bus_id")).append(" ");
                result.append(rs.getInt("Bus_Capacity")).append(" ");
                result.append(rs.getInt("Seats_Available")).append(" ");
                result.append(rs.getString("Source")).append(" ");
                result.append(rs.getString("Destination")).append(" ");
                result.append(rs.getString("Time_Available")).append(" ");
                result.append(rs.getDate("Date_Available"));
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            return null;
        }
        return result.toString().trim(); // Removing any trailing spaces
    }
    public String FindBus(String source){
        String query = "SELECT Bus_id, Source, Destination, Time_Available, Date_Available FROM bus WHERE Source = ? AND Seats_Available != 0;";        StringBuilder result = new StringBuilder();

        try  {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, source);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.append(rs.getInt("Bus_id")).append(" ");
                result.append(rs.getString("Source")).append(" ");
                result.append(rs.getString("Destination")).append(" ");
                result.append(rs.getString("Time_Available")).append(" ");
                result.append(rs.getDate("Date_Available")).append(" ");
                result.append("\n");
            }

        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            return null;
        }
        JOptionPane.showMessageDialog(null,"Select your bus");
        return result.toString().trim();
    }
    public boolean BusBookingIn(String input){
        String query = "INSERT INTO bookings (MobNo, UserName, BusId, Source, Destination, SeatNo) VALUES (?, ?, ?, ?, ?, ?);";
        String updateQuery = "UPDATE bus SET Seats_Available = Seats_Available - 1 WHERE BusId = ? AND Seats_Available > 0;";

        try (PreparedStatement ps = con.prepareStatement(query)) {
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


}
