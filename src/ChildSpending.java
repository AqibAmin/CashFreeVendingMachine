
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
import javax.swing.JComboBox;
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
public class ChildSpending implements ActionListener{
    String[] rollNoStr;
    int selectionIndex;
    JFrame spendingFrame;
    JScrollPane pane;
    JTable spendingTable;
    JButton close,view;
    JComboBox childCombo;
    
    ChildSpending(String[] str,int ind)
    {
        rollNoStr=str;
        selectionIndex=ind;
        initChildSpending();
    }
    public void initChildSpending()
    {
        spendingFrame=new JFrame("Child Spending");
        spendingFrame.setLayout(new FlowLayout());
        spendingFrame.setLocation(260,300);
        spendingFrame.setSize(new Dimension(800,430));
        spendingFrame.getContentPane().setBackground(new Color(222, 240, 250));
        childCombo=new JComboBox(rollNoStr);
        childCombo.setSelectedIndex(selectionIndex);
        String[][] data =new String[30][4];
        Arrays.fill(data[0],"");
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending_machine","root","");
            java.sql.Statement stm = con.createStatement();
            String query="select purchase_date,item_name,item_price,item_quantity from c_spending_history where c_rollno='"+childCombo.getSelectedItem()+"'";
            ResultSet rs=stm.executeQuery(query);
            int i=0;
            while(rs.next())
            {
                data[i][0]=rs.getString(1);
                data[i][1]=rs.getString(2);
                data[i][2]=rs.getString(3);
                data[i][3]=rs.getString(4);
                i++;
            }
            
        } catch (SQLException ex) {
                Logger.getLogger(ParentSignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        String[] columnNames = { "Date Time", "Item Name", "Item Price","Item Quantity"};
        spendingTable = new JTable(data, columnNames){
        public boolean isCellEditable(int row, int column) {                
                return false;               
        };
        };
        spendingTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        spendingTable.setFont(new Font("Serif", Font.BOLD, 20));
        spendingTable.setCellSelectionEnabled(false);
        spendingTable.setColumnSelectionAllowed(false);
        spendingTable.setRowHeight(30);
        spendingTable.setRowSelectionAllowed(false);
        pane = new JScrollPane(spendingTable) {
        @Override
        public Dimension getPreferredSize() {
        return new Dimension(780, 300);}
        };
        JTableHeader tableHeader = spendingTable.getTableHeader();
        tableHeader.setForeground(new Color(0, 112, 172));
        tableHeader.setFont(new Font("Sans_Serif", Font.BOLD, 17));
        spendingTable.setBackground(new Color(255, 148, 82));
        spendingTable.setForeground(Color.WHITE);
        
        close = new JButton("Close");
        close.addActionListener(this);
        close.setBackground(new Color(0, 112, 172));
        close.setForeground(new Color(255,255,255));
        close.setFont(new Font("SanSerif", Font.BOLD, 14));
        close.setFocusable(false);
        view = new JButton("View");
        view.addActionListener(this);
        view.setBackground(new Color(0, 112, 172));
        view.setForeground(new Color(255,255,255));
        view.setFont(new Font("SanSerif", Font.BOLD, 14));
        view.setFocusable(false);
        spendingFrame.add(childCombo);
        spendingFrame.add(view);
        spendingFrame.add(pane);
        spendingFrame.add(close);
        spendingFrame.setVisible(true);
        spendingFrame.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Close"))
            spendingFrame.dispose();
        if(e.getActionCommand().equals("View"))
        {
            new ChildSpending(rollNoStr,childCombo.getSelectedIndex());
            spendingFrame.dispose();
        }
    }
}
