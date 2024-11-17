import java.util.*;
import java.awt.*;
import javax.swing.*;

public class AdminPanel extends JPanel {
    JLabel l[];
    JTextField tf[];
    static String[] label = "Bus_Id Bus_Capacity Seats_Available Source Destination Time_Available Date_Available".split(" ");
    static String[] button = "Add Update Delete Search".split(" ");

    JButton b[];
    SQLTools data;

    void DoOperation(String temp) {
        if (temp.equals("Add")) {
            String res = "";
            for (int i = 0; i < label.length; i++) {
                res += tf[i].getText() + " ";
            }
            res = res.trim();
            data.Add(res);
        } else if (temp.equals("Delete")) {
            data.Delete(tf[0].getText());

        } else if (temp.equals("Update")) {
                String res = "";
                for (int i = 0; i < label.length; i++) {
                    res += tf[i].getText() + " ";
                }
                res = res.trim();
                data.Update(res);
        } else if (temp.equals("Search")) {
            String[] res = data.Get(tf[0].getText()).split(" ");
            for (int i = 0; i < res.length; i++) {
                tf[i].setText(res[i]);
            }
        }
    }

    AdminPanel(){
        data=new SQLTools();
        setLayout(null);
        l=new JLabel[label.length];
        tf= new JTextField[label.length];
        b=new JButton[button.length];
        int xlstart = 20;
        int xtstart = 220;
        int ystart=20;
        for (int i=0;i<label.length;i++){
            l[i]=new JLabel(label[i]);
            tf[i]= new JTextField();
            l[i].setBounds(xlstart,ystart,150,20);
            tf[i].setBounds(xtstart,ystart,150,20);
            ystart+=20;
            add(l[i]);
            add(tf[i]);
        }
        xlstart = 20;
        xtstart = 220;
        ystart=150;
        JPanel jp=new JPanel();
        jp.setLayout(new GridLayout(2,2));
        jp.setBounds(20,220,300,100);
        for(int i=0;i<button.length;i++){
            b[i]=new JButton(button[i]);
            b[i].addActionListener((event)->{
               String temp= event.getActionCommand();
               DoOperation(temp);
            });
            jp.add(b[i]);
        }
        add(jp);
        setVisible(true);
        setSize(700,700);
    }
    public static void main(String[] args){
        JFrame jf= new JFrame();
        jf.setSize(700,700);
        jf.setVisible(true);
        jf.add(new AdminPanel());

    }
}
