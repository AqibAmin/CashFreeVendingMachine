
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneLayout;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
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
public class ParentAccount  implements  ActionListener{
    String parentID;
    JFrame pAccFrame;
    JLabel statusLabel;
    JPanel tablePanel;
    JTable accStatus;
    JScrollPane sp;
    JButton addChild;
    JButton viewSpendings;
    JButton addAmount;
    JButton maxLimit;
    JButton signOut;
    
    ParentAccount(String id)
    {
        parentID = id;
        initParentAccount();
    }
    void initParentAccount()
    {
        pAccFrame=new JFrame("Parent Account");
        pAccFrame.setLayout(new FlowLayout());
        pAccFrame.setLocation(20,40);
        pAccFrame.setSize(new Dimension(1200,900));
        pAccFrame.getContentPane().setBackground(new Color(222, 240, 250));
        statusLabel=new JLabel("Children's Account Status");
        statusLabel.setForeground(new Color(0, 112, 172));
        statusLabel.setFont(new Font("Serif", Font.BOLD, 25));
        updateTable();
        addChild=new JButton("Add Child Account");
        addChild.addActionListener(this);
        addChild.setIcon(new ImageIcon("childAccount.jpg"));
        addChild.setHorizontalTextPosition(JButton.CENTER);
        addChild.setVerticalTextPosition(JButton.BOTTOM);
        addChild.setFocusable(false);
        addChild.setFont(new Font("Sans_Serif", Font.BOLD, 17));
        addChild.setForeground(new Color(0, 112, 172));
        addChild.setBackground(new Color(222, 240, 250));
        addChild.setBorderPainted(false);
        viewSpendings=new JButton("View Child's Spending");
        viewSpendings.addActionListener(this);
        viewSpendings.setIcon(new ImageIcon("spending.jpg"));
        viewSpendings.setHorizontalTextPosition(JButton.CENTER);
        viewSpendings.setVerticalTextPosition(JButton.BOTTOM);
        viewSpendings.setFocusable(false);
        viewSpendings.setFont(new Font("Sans_Serif", Font.BOLD, 17));
        viewSpendings.setForeground(new Color(0, 112, 172));
        viewSpendings.setBackground(new Color(222, 240, 250));
        viewSpendings.setBorderPainted(false);
        addAmount=new JButton("Add Amount");
        addAmount.addActionListener(this);
        addAmount.setIcon(new ImageIcon("addAmount.png"));
        addAmount.setHorizontalTextPosition(JButton.CENTER);
        addAmount.setVerticalTextPosition(JButton.BOTTOM);
        addAmount.setFocusable(false);
        addAmount.setFont(new Font("Sans_Serif", Font.BOLD, 17));
        addAmount.setForeground(new Color(0, 112, 172));
        addAmount.setBackground(new Color(222, 240, 250));
        addAmount.setBorderPainted(false);
        maxLimit=new JButton("Change Max. Limit");
        maxLimit.addActionListener(this);
        maxLimit.setIcon(new ImageIcon("maxLimit.png"));
        maxLimit.setHorizontalTextPosition(JButton.CENTER);
        maxLimit.setVerticalTextPosition(JButton.BOTTOM);
        maxLimit.setFocusable(false);
        maxLimit.setFont(new Font("Sans_Serif", Font.BOLD, 17));
        maxLimit.setForeground(new Color(0, 112, 172));
        maxLimit.setBackground(new Color(222, 240, 250));
        maxLimit.setBorderPainted(false);
        signOut=new JButton("Sign-Out");
        signOut.addActionListener(this);
        signOut.setFocusable(false);
        signOut.setFont(new Font("Sans_Serif", Font.BOLD, 25));
        signOut.setForeground(new Color(255,255,255));
        signOut.setBackground(new Color(230, 97, 14));
        
        JTextArea temp1=new JTextArea("\n",1,105);
        temp1.setEditable(false);
        temp1.setBackground(new Color(222, 240, 250));
        pAccFrame.add(temp1);
        pAccFrame.add(statusLabel);
        JTextArea temp2=new JTextArea("\n",1,90);
        temp2.setEditable(false);
        temp2.setBackground(new Color(222, 240, 250));
        pAccFrame.add(temp2);
        pAccFrame.add(sp);
        JTextArea temp3=new JTextArea("\n",1,105);
        temp3.setEditable(false);
        temp3.setBackground(new Color(222, 240, 250));
        pAccFrame.add(temp3);
        pAccFrame.add(addChild);
        pAccFrame.add(viewSpendings);
        pAccFrame.add(addAmount);
        pAccFrame.add(maxLimit);
        JTextArea temp4=new JTextArea("\n",1,105);
        temp4.setEditable(false);
        temp4.setBackground(new Color(222, 240, 250));
        pAccFrame.add(temp4);
        JTextArea temp5=new JTextArea("\n",1,80);
        temp5.setEditable(false);
        temp5.setBackground(new Color(222, 240, 250));
        pAccFrame.add(temp5);
        pAccFrame.add(signOut);
        pAccFrame.setVisible(true);
        pAccFrame.setResizable(false);
    }
    
    void updateTable()
    {
        String[][] data =new String[13][4];
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending_machine","root","");
            java.sql.Statement stm = con.createStatement();
            String query="select c.c_name,c.c_rollno,a.c_balance,a.c_dailylimit from child c, c_account a where a.c_rollno=c.c_rollno";
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
        String[] columnNames = { "Name", "Roll Number", "Balance","Max. Limit" };
        accStatus = new JTable(data, columnNames){
        public boolean isCellEditable(int row, int column) {                
                return false;               
        };
        };
        accStatus.setFont(new Font("Serif", Font.BOLD, 20));
        accStatus.setCellSelectionEnabled(false);
        accStatus.setColumnSelectionAllowed(false);
        accStatus.setRowHeight(30);
        accStatus.setRowSelectionAllowed(false);
        sp = new JScrollPane(new JScrollPane(accStatus)) {
        @Override
        public Dimension getPreferredSize() {
        return new Dimension(1100, 390);}
        };
        JTableHeader tableHeader = accStatus.getTableHeader();
        tableHeader.setForeground(new Color(0, 112, 172));
        tableHeader.setFont(new Font("Sans_Serif", Font.BOLD, 17));
        accStatus.setBackground(new Color(255, 148, 82));
        accStatus.setForeground(Color.WHITE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Add Child Account"))
        {
            ChildSignUp child=new ChildSignUp(parentID);
        }
        else if(e.getActionCommand().equals("View Child's Spending"))
        {
            Connection con = null;
            try {
                String[] rollNoStr=new String[10];
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending_machine","root","");
                java.sql.Statement stm = con.createStatement();
                String query="select c_rollno from c_account where p_account_id='"+parentID+"'";
                ResultSet rs=stm.executeQuery(query);
                int i=0;
                while(rs.next())
                {
                    rollNoStr[i]=rs.getString(1);
                    i++;
                }
                String[] str=new String[i];
                for(int j=0;j<i;j++)
                    str[j]=rollNoStr[j];
                if(i==0)
                {
                    JLabel label = new JLabel("No Child Account Exist with that Parent Account in Database.");
                    label.setFont(new Font("Arial", Font.BOLD, 18));
                    label.setForeground(new Color(230, 97, 14));
                    UIManager UI=new UIManager();
                    UI.put("OptionPane.background",new ColorUIResource(222, 240, 250));
                    UI.put("Panel.background",new ColorUIResource(222, 240, 250));
                    final JOptionPane pane = new JOptionPane(label);
                    final JDialog d = pane.createDialog((JFrame)null, "Error Message");
                    d.setLocation(500,450);
                    d.setVisible(true);
                }
                else
                {
                    new ChildSpending(str,0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ParentAccount.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else if(e.getActionCommand().equals("Add Amount"))
        {
            new AddAmount(pAccFrame,parentID);
        }
        else if(e.getActionCommand().equals("Change Max. Limit"))
        {
            new ChangeMaxLimit(pAccFrame,parentID);
        }
        else if(e.getActionCommand().equals("Sign-Out"))
        {
            pAccFrame.dispose();
            new MainMenu();
        }
    }
}
