package BusBooking;

import javax.swing.*;
import java.awt.*;

public class userHome extends JFrame{
	
	public userHome(){
		setTitle("Home Page");
		setSize(1000,850);
		setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255,102,102,100));
        JLabel wel = new JLabel(" Welcome");
        wel.setBounds(635, 0, 150, 30);
        wel.setFont(new Font("Times New Roman",Font.BOLD,26));
        add(wel,BorderLayout.NORTH);
	    JPanel p = new JPanel();
	    p.setLayout(new BorderLayout());
	    p.setBorder(BorderFactory.createTitledBorder(" Home Page"));
	    JButton pro = new JButton("Profile");
	    pro.setBounds(0,50,100,30);
	    pro.setFont(new Font("Times New Roman",Font.BOLD,16));
	    JButton tic = new JButton("Tickets");
	    tic.setBounds(0,80,100,30);
	    tic.setFont(new Font("Times New Roman",Font.BOLD,16));
	    JButton His = new JButton("History");
	    His.setBounds(0,110,100,30);
	    His.setFont(new Font("Times New Roman",Font.BOLD,16));
	    JPanel sp = new JPanel(null);
	    sp.add(pro);
	    sp.add(tic);
	    sp.add(His);
	    p.add(sp);
	    /*ButtonGroup bugrop = new ButtonGroup();
	    bugrop.add(pro);
	    bugrop.add(tic);
	    p.add(bugrop);*/
	    add(p);
	}
	public static void main(String[] args) {
		userHome uh = new userHome();
	    uh.setVisible(true);
	}
}
