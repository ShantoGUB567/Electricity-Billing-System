package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class GenerateBill extends JFrame implements ActionListener{

    String meter;
    JButton bill;
    Choice cmonth;
    JTextArea area;
    GenerateBill(String meter) {
        this.meter = meter;
        
        setSize(500, 700);
        setLocation(550, 10);
        
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        
        JLabel heading = new JLabel("Generate Bill");
        JLabel meternumber = new JLabel(meter);
        
        cmonth = new Choice();
        
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        
        area = new JTextArea(50, 15);
        area.setText("\n\n\t--------Click on the---------\n\t Generate Bill Button to get\n\tthe bill of the Selected Month");
        area.setFont(new Font("Senserif", Font.ITALIC, 18));
        
        JScrollPane pane = new JScrollPane(area);
        
        bill = new JButton("Generate Bill");
        bill.addActionListener(this);
        
        panel.add(heading);
        panel.add(meternumber);
        panel.add(cmonth);
        add(panel, "North");
        
        add(pane, "Center");
        add(bill, "South");
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        try {
            Conn c = new Conn();
            
            String month = cmonth.getSelectedItem();
            
            area.setText("\tReliance Power Limited\nELECTRICITY BILL GENERATED FOR THE MONTH\n\tOF "+month+", 2022\n\n\n");
            
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
        
            if(rs.next()) {
                area.append("\n    Customer Name\t: " + rs.getString("name"));
                area.append("\n    Meter Number\t: " + rs.getString("meter_no"));
                area.append("\n    Address\t\t: " + rs.getString("address"));
                area.append("\n    City\t\t: " + rs.getString("city"));
                area.append("\n    District\t\t: " + rs.getString("state"));
                area.append("\n    Email\t\t: " + rs.getString("email"));
                area.append("\n    Phone\t\t: " + rs.getString("phone"));
                area.append("\n---------------------------------------------------");
                area.append("\n");
            }
            
            rs = c.s.executeQuery("select * from meter_info where meter_no = '"+meter+"'");
        
            if(rs.next()) {
                area.append("\n    Meter Location\t: " + rs.getString("meter_location"));
                area.append("\n    Meter Type\t\t: " + rs.getString("meter_type"));
                area.append("\n    Phase Code\t\t: " + rs.getString("phase"));
                area.append("\n    Bill Type\t\t: " + rs.getString("bill_type"));
                area.append("\n    Days\t\t: " + rs.getString("days"));
                area.append("\n---------------------------------------------------");
                area.append("\n");
            }
            
            rs = c.s.executeQuery("select * from tax");
        
            if(rs.next()) {
                area.append("\n");
                area.append("\n    Cost Per Unit\t: " + rs.getString("cost_per_unit"));
                area.append("\n    Meter Rent\t\t: " + rs.getString("cost_per_unit"));
                area.append("\n    Service Charge\t: " + rs.getString("service_charge"));
                area.append("\n    Service Tax\t\t: " + rs.getString("service_charge"));
                area.append("\n    Demand Charge\t: " + rs.getString("demand_charge"));
                area.append("\n    Fixed Tax\t\t: " + rs.getString("fixed_tax"));
                area.append("\n");
            }
            
            rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' and month='"+month+"'");
        
            if(rs.next()) {
                area.append("\n");
                area.append("\n    Current Month\t: " + rs.getString("month"));
                area.append("\n    Units Consumed\t: " + rs.getString("units"));
                area.append("\n    Total Charges\t: " + rs.getString("totalbill"));
                area.append("\n-------------------------------------------------------");
                area.append("\n    Total Payable\t: " + rs.getString("totalbill"));
                area.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GenerateBill("");
    }
}
