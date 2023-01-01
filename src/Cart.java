
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aqiba
 */
public class Cart implements ActionListener{
    int[] spinVal;
    JFrame cartFrame;
    JScrollPane pane;
    JTable cartTable;
    JButton close;
    
    Cart(int[] val)
    {
        spinVal=val;
        initCart();
    }
    void initCart()
    {
        cartFrame=new JFrame("Cart");
        cartFrame.setLayout(new FlowLayout());
        cartFrame.setLocation(360,360);
        cartFrame.setSize(new Dimension(580,330));
        cartFrame.getContentPane().setBackground(new Color(222, 240, 250));
        
        String[][] data =new String[12][3];
        Arrays.fill(data[0],"");
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending_machine","root","");
            java.sql.Statement stm = con.createStatement();
            String query="select item_name,item_price from inventory";
            ResultSet rs=stm.executeQuery(query);
        int i=0,j=0;
        while(rs.next())
        {
            if(spinVal[j]>0)
            {
                data[i][0]=rs.getString(1);
                data[i][1]=rs.getString(2);
                data[i][2]= Integer.toString(spinVal[j]);
                i++;
            }
            j++;
        }
        } catch (SQLException ex) {
                Logger.getLogger(ParentSignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        String[] columnNames = { "Item Name", "Item Price", "Item Quantity"};
        cartTable = new JTable(data, columnNames){
        public boolean isCellEditable(int row, int column) {                
                return false;               
        };
        };
        cartTable.setFont(new Font("Serif", Font.BOLD, 20));
        cartTable.setCellSelectionEnabled(false);
        cartTable.setColumnSelectionAllowed(false);
        cartTable.setRowHeight(30);
        cartTable.setRowSelectionAllowed(false);
        pane = new JScrollPane(new JScrollPane(cartTable)) {
        @Override
        public Dimension getPreferredSize() {
        return new Dimension(550, 250);}
        };
        JTableHeader tableHeader = cartTable.getTableHeader();
        tableHeader.setForeground(new Color(0, 112, 172));
        tableHeader.setFont(new Font("Sans_Serif", Font.BOLD, 17));
        cartTable.setBackground(new Color(255, 148, 82));
        cartTable.setForeground(Color.WHITE);
        
        close = new JButton("Close");
        close.addActionListener(this);
        close.setBackground(new Color(0, 112, 172));
        close.setForeground(new Color(255,255,255));
        close.setFont(new Font("SanSerif", Font.BOLD, 14));
        close.setFocusable(false);
        
        cartFrame.add(pane);
        cartFrame.add(close);
        cartFrame.setVisible(true);
        cartFrame.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Close"))
            cartFrame.dispose();
    }
}
