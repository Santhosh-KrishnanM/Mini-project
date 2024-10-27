package BusBooking;

import javax.swing.*;
import java.awt.*;
//import javax.swing.border.Border;

class UserRegister {
    public static void main(String[] args) {
        JFrame f = new JFrame("Sign Up");
        f.setSize(800, 700);
        f.setLayout(new GridLayout(3, 1));
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        // Header Panel
        JPanel head = new JPanel();
        head.setBounds(50, 50, 300, 150); 
        head.setLayout(null);
        JLabel h = new JLabel("USER SIGN UP");
        h.setBounds(550, 130, 300, 100);  // Centered horizontally at the top
        h.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        h.setForeground(Color.red);
        head.add(h);
        f.add(head);
        
        // Personal Details Panel
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBorder(BorderFactory.createTitledBorder("Personal Details"));
        p1.setPreferredSize(new Dimension(800, 600)); 
        JLabel name = new JLabel("Student Name:");
        name.setBounds(550, 10, 150, 30);
        name.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        p1.add(name);
        JTextField n = new JTextField(25);
        n.setBounds(650, 10, 200, 30);
        p1.add(n);
        
        JLabel dob = new JLabel("Date of Birth:");
        dob.setBounds(550, 50, 150, 30);
        dob.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        p1.add(dob);
        JTextField d = new JTextField(15);
        d.setBounds(650, 50, 200, 30);
        p1.add(d);
        
        JLabel age = new JLabel("Age:");
        age.setBounds(550, 90, 150, 30);
        age.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        p1.add(age);
        JTextField a = new JTextField(5);
        a.setBounds(650, 90, 200, 30);
        p1.add(a);
        
        JLabel gen = new JLabel("Gender:");
        gen.setBounds(550, 130, 150, 30);
        gen.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        p1.add(gen);
        
        JRadioButton jb1 = new JRadioButton("Male");
        jb1.setFont(new Font("Garamond", Font.BOLD,16));
        JRadioButton jb2 = new JRadioButton("Female");
        jb2.setFont(new Font("Garamond", Font.BOLD,16));
        ButtonGroup jbg = new ButtonGroup();
        jbg.add(jb1);
        jbg.add(jb2);
        
        JPanel genderp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderp.setBounds(650, 130, 200, 30);
        genderp.add(jb1);
        genderp.add(jb2);
        p1.add(genderp);
        
        JLabel father = new JLabel("Father Name:");
        father.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        father.setBounds(550, 170, 150, 30);
        p1.add(father);
        JTextField fat = new JTextField(25);
        fat.setBounds(650, 170, 200, 30);
        p1.add(fat);
        
        JLabel mother = new JLabel("Mother Name:");
        mother.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        mother.setBounds(550, 210, 150, 30);
        p1.add(mother);
        JTextField mo = new JTextField(25);
        mo.setBounds(650, 210, 150, 30);
        p1.add(mo);
        f.add(p1);
        
        // Academic Details Panel
        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBorder(BorderFactory.createTitledBorder("Academic Details"));
        JTextField dpt, cgpa;
        JLabel dept = new JLabel("Department: ");
        dept.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        dept.setBounds(550, 10, 150, 30);
        //dept.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        p2.add(dept);
        dpt = new JTextField(10);
        dpt.setBounds(650, 10, 200, 30);
        p2.add(dpt);
        
        JLabel yearLabel = new JLabel("Year: ");
        yearLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        yearLabel.setBounds(550, 50, 150, 30);
        p2.add(yearLabel);
        String[] str = {"I", "II", "III", "IV"};
        JComboBox<String> cb1 = new JComboBox<>(str);
        cb1.setBounds(650, 50, 200, 30);
        p2.add(cb1);
        
        JLabel semesterLabel = new JLabel("Semester: ");
        semesterLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        semesterLabel.setBounds(550, 90, 150, 30);
        p2.add(semesterLabel);
        String[] sem = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII"};
        JComboBox<String> cb2 = new JComboBox<>(sem);
        cb2.setBounds(650, 90, 200, 30);
        p2.add(cb2);
        
        JLabel cgpaLabel = new JLabel("CGPA: ");
        cgpaLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        cgpaLabel.setBounds(550, 130, 150, 30);
        p2.add(cgpaLabel);
        cgpa = new JTextField(10);
        cgpa.setBounds(650, 130, 200, 30);
        p2.add(cgpa);
        
        JLabel quota = new JLabel("Quota: ");
        quota.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        quota.setBounds(550, 170, 150, 30);
        p2.add(quota);
        
        JCheckBox mq = new JCheckBox("MQ");
        mq.setFont(new Font("Garamond", Font.PLAIN, 15));
        mq.setBounds(650, 170, 50, 30);
        p2.add(mq);
        
        JCheckBox gq = new JCheckBox("GQ");
        gq.setFont(new Font("Garamond", Font.PLAIN, 15));
        gq.setBounds(700, 170, 100, 30);
        p2.add(gq);
        
        f.add(p2);
        
        // Set frame properties
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
