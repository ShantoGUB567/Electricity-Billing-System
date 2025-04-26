package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Bkash extends JFrame implements ActionListener{

    String meter;
    JButton back;
    Bkash(String meter) {
        this.meter = meter;
        
        JEditorPane j = new JEditorPane();
        j.setEditable(false);
        
        try {
            j.setPage("https://www.bkash.com/products-services/payment");
        } catch (Exception e) {
            j.setContentType("text/html");
            j.setText("<html>404<br>Could not load<html>");
            
        }
        
        JScrollPane pane = new JScrollPane(j);
        add(pane);
        
        back = new JButton("Back");
        back.setBounds(640, 20, 80, 30);
        back.addActionListener(this);
        j.add(back);
        
        setSize(800, 600);
        setLocation(300, 50);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new PayBill(meter);
    }
    
    public static void main(String[] args) {
        new Bkash("");
    }
}
