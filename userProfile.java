package Miniproject;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class userProfile {

    private JFrame frame;
    private JTextField txtName;
    private JTextField txtMob;
    private JTextField txtEmail;
    private JTextField txtAge;
    private JTextArea txtAdd;
    private JRadioButton rbMale, rbFemale;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                userProfile window = new userProfile();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    userProfile() {
        frame = new JFrame();
        frame.setBounds(100, 100, 668, 489);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(224, 255, 255));
        panel.setBounds(10, 28, 634, 414);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblName.setBounds(36, 10, 96, 27);
        panel.add(lblName);

        JLabel lblMobileNumber = new JLabel("Mobile Number:");
        lblMobileNumber.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblMobileNumber.setBounds(36, 66, 96, 27);
        panel.add(lblMobileNumber);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblEmail.setBounds(36, 121, 96, 27);
        panel.add(lblEmail);

        JLabel lblGender = new JLabel("Gender:");
        lblGender.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblGender.setBounds(36, 172, 96, 27);
        panel.add(lblGender);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblAge.setBounds(36, 219, 96, 27);
        panel.add(lblAge);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblAddress.setBounds(36, 267, 96, 27);
        panel.add(lblAddress);

        txtName = new JTextField();
        txtName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        txtName.setBounds(192, 10, 212, 23);
        panel.add(txtName);
        txtName.setColumns(10);

        txtMob = new JTextField();
        txtMob.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        txtMob.setColumns(10);
        txtMob.setBounds(192, 70, 212, 23);
        panel.add(txtMob);

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        txtEmail.setColumns(10);
        txtEmail.setBounds(192, 125, 212, 23);
        panel.add(txtEmail);

        txtAge = new JTextField();
        txtAge.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        txtAge.setColumns(10);
        txtAge.setBounds(192, 219, 212, 23);
        panel.add(txtAge);

        txtAdd = new JTextArea();
        txtAdd.setBounds(192, 272, 212, 59);
        panel.add(txtAdd);

        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        rbMale.setFont(new Font("Garamond", Font.BOLD, 16));
        rbFemale.setFont(new Font("Garamond", Font.BOLD, 16));

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBounds(192, 172, 200, 30);
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);
        panel.add(genderPanel);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveUserProfile();
            }
        });
        btnSave.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnSave.setBounds(252, 363, 111, 27);
        panel.add(btnSave);

        frame.setVisible(true);
    }

    // Added a WHERE clause for the update query
    private void saveUserProfile() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Kishore@2011")) {
            String query = "UPDATE register SET name=?, mobile=?, email=?, gender=?, age=?, address=? WHERE mobile=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, txtName.getText());
            ps.setString(2, txtMob.getText());
            ps.setString(3, txtEmail.getText());
            ps.setString(4, rbMale.isSelected() ? "Male" : "Female");
            ps.setInt(5, Integer.parseInt(txtAge.getText()));
            ps.setString(6, txtAdd.getText());
            ps.setString(7, txtMob.getText()); // Assuming mobile is the unique identifier for the record to be updated

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Update Successful!");
            } else {
                JOptionPane.showMessageDialog(frame, "No record updated. Please check the mobile number.");
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Update Failed: " + e.getMessage());
        }
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
