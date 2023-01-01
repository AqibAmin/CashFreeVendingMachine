
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
import java.text.ParseException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Border;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
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
public class ChildAccount implements ActionListener{
    String childRollno;
    JFrame cAccFrame;
    JLabel statusMsg;
    JTable statusTable;
    JLabel selectionMsg;
    JPanel itemsPanel;
    JLabel[] itemsImg;
    JLabel[] itemsPriceTag;
    JTextField[] itemsPrice;
    JSpinner[] itemsSpinner;
    JButton placeOrder;
    JButton cart;
    JButton signOut;
    String[] priceStr;
    String[][] data;
    int[] spinMax;
    SpinnerModel[] spinValue;
    ChildAccount(String roll)
    {
        childRollno=roll;
        initChildAccount();
    }
    void initChildAccount()
    {
        cAccFrame=new JFrame("Child Account");
        cAccFrame.setLayout(new FlowLayout());
        cAccFrame.setLocation(20,40);
        cAccFrame.setSize(new Dimension(1200,900));
        cAccFrame.getContentPane().setBackground(new Color(222, 240, 250));
        statusMsg=new JLabel("Account Status");
        statusMsg.setForeground(new Color(0, 112, 172));
        statusMsg.setFont(new Font("Serif", Font.BOLD, 25));
        data = new String[1][4];
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending_machine","root","");
            java.sql.Statement stm = con.createStatement();
            String query="select c.c_name, c.c_rollno, a.c_balance, a.c_dailylimit from child c , c_account a where a.c_rollno=c.c_rollno and c.c_rollno='"+childRollno+"'";
            ResultSet rs=stm.executeQuery(query);
            if(rs.next())
            {
                data[0][0]=rs.getString(1);
                data[0][1]=rs.getString(2);
                data[0][2]=rs.getString(3);
                data[0][3]=rs.getString(4);
            }
        } catch (SQLException ex) {
                Logger.getLogger(ParentSignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        String[] columnNames = { "Name", "Roll Number", "Balance","Max. Limit" };
        statusTable = new JTable(data, columnNames){
        public boolean isCellEditable(int row, int column) {                
                return false;               
        };
        };
        statusTable.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        statusTable.setCellSelectionEnabled(false);
        statusTable.setColumnSelectionAllowed(false);
        statusTable.setRowHeight(30);
        statusTable.setRowSelectionAllowed(false);
        JScrollPane sp = new JScrollPane(new JScrollPane(statusTable)) {
        @Override
        public Dimension getPreferredSize() {
        return new Dimension(1100, 60);}
        };
        JTableHeader tableHeader = statusTable.getTableHeader();
        tableHeader.setForeground(new Color(0, 112, 172));
        tableHeader.setFont(new Font("Sans_Serif", Font.BOLD, 17));
        statusTable.setBackground(new Color(255, 148, 82));
        statusTable.setForeground(Color.WHITE);
        selectionMsg=new JLabel("Select Items");
        selectionMsg.setForeground(new Color(0, 112, 172));
        selectionMsg.setFont(new Font("Serif", Font.BOLD, 25));
        itemsPanel = new JPanel() {
        @Override
        public Dimension getPreferredSize() {
        return new Dimension(1100, 950);}
        };
        priceStr=new String[12];
        String[] imgStr={"lays.jpg","kurkure.jpg","pringles.jpg","korneez.png",
                           "coke.jpg","fruita.jpg","latte.jpg","pepsi.jpg",
                            "rio.jpg","oreo.jpg","cupcake.jpg","cocmo.jpg"};
        spinMax=new int[12];
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending_machine","root","");
            java.sql.Statement stm = con.createStatement();
            String query="select item_price,item_quantity from inventory";
            ResultSet rs=stm.executeQuery(query);
        int i=0;
        while(rs.next())
        {
            priceStr[i]=rs.getString(1);
            spinMax[i]=Integer.parseInt(rs.getString(2));
            i++;
        }
        } catch (SQLException ex) {
                Logger.getLogger(ParentSignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        itemsImg=new JLabel[12];
        itemsPriceTag=new JLabel[12];
        itemsPrice=new JTextField[12];
        itemsSpinner=new JSpinner[12];
        spinValue = new SpinnerModel[12];
        JTextArea[] tempArea= new JTextArea[12];
        for(int i=0;i<12;i=i=i+4)
        {
            itemsImg[i]=new JLabel(new ImageIcon(imgStr[i]));
            itemsImg[i+1]=new JLabel(new ImageIcon(imgStr[i+1]));
            itemsImg[i+2]=new JLabel(new ImageIcon(imgStr[i+2]));
            itemsImg[i+3]=new JLabel(new ImageIcon(imgStr[i+3]));
            
            itemsPriceTag[i]=new JLabel("Price: ");
            itemsPriceTag[i].setForeground(new Color(0, 112, 172));
            itemsPriceTag[i].setFont(new Font("Serif", Font.BOLD, 25));
            itemsPrice[i]=new JTextField(3);
            itemsPrice[i].setText(priceStr[i]);
            itemsPrice[i].setEditable(false);
            itemsPrice[i].setBackground(new Color(252, 208, 164));
            itemsPrice[i].setForeground(new Color(0, 112, 172));
            itemsPrice[i].setFont(new Font("Serif", Font.BOLD, 25));
            spinValue[i]=new SpinnerNumberModel(0,0,spinMax[i],1);
            itemsSpinner[i]=new JSpinner(spinValue[i]);
            itemsSpinner[i].setForeground(new Color(0, 112, 172));
            itemsSpinner[i].setFont(new Font("Serif", Font.BOLD, 20));
            
            itemsPriceTag[i+1]=new JLabel("Price: ");
            itemsPriceTag[i+1].setForeground(new Color(0, 112, 172));
            itemsPriceTag[i+1].setFont(new Font("Serif", Font.BOLD, 25));
            itemsPrice[i+1]=new JTextField(3);
            itemsPrice[i+1].setText(priceStr[i+1]);
            itemsPrice[i+1].setEditable(false);
            itemsPrice[i+1].setBackground(new Color(252, 208, 164));
            itemsPrice[i+1].setForeground(new Color(0, 112, 172));
            itemsPrice[i+1].setFont(new Font("Serif", Font.BOLD, 25));
            spinValue[i+1]=new SpinnerNumberModel(0,0,spinMax[i+1],1);
            itemsSpinner[i+1]=new JSpinner(spinValue[i+1]);
            itemsSpinner[i+1].setForeground(new Color(0, 112, 172));
            itemsSpinner[i+1].setFont(new Font("Serif", Font.BOLD, 20));
            
            itemsPriceTag[i+2]=new JLabel("Price: ");
            itemsPriceTag[i+2].setForeground(new Color(0, 112, 172));
            itemsPriceTag[i+2].setFont(new Font("Serif", Font.BOLD, 25));
            itemsPrice[i+2]=new JTextField(3);
            itemsPrice[i+2].setText(priceStr[i+2]);
            itemsPrice[i+2].setEditable(false);
            itemsPrice[i+2].setBackground(new Color(252, 208, 164));
            itemsPrice[i+2].setForeground(new Color(0, 112, 172));
            itemsPrice[i+2].setFont(new Font("Serif", Font.BOLD, 25));
            spinValue[i+2]=new SpinnerNumberModel(0,0,spinMax[i+2],1);
            itemsSpinner[i+2]=new JSpinner(spinValue[i+2]);
            itemsSpinner[i+2].setForeground(new Color(0, 112, 172));
            itemsSpinner[i+2].setFont(new Font("Serif", Font.BOLD, 20));
            
            itemsPriceTag[i+3]=new JLabel("Price: ");
            itemsPriceTag[i+3].setForeground(new Color(0, 112, 172));
            itemsPriceTag[i+3].setFont(new Font("Serif", Font.BOLD, 25));
            itemsPrice[i+3]=new JTextField(3);
            itemsPrice[i+3].setText(priceStr[i+3]);
            itemsPrice[i+3].setEditable(false);
            itemsPrice[i+3].setBackground(new Color(252, 208, 164));
            itemsPrice[i+3].setForeground(new Color(0, 112, 172));
            itemsPrice[i+3].setFont(new Font("Serif", Font.BOLD, 25));
            spinValue[i+3]=new SpinnerNumberModel(0,0,spinMax[i+3],1);
            itemsSpinner[i+3]=new JSpinner(spinValue[i+3]);
            itemsSpinner[i+3].setForeground(new Color(0, 112, 172));
            itemsSpinner[i+3].setFont(new Font("Serif", Font.BOLD, 20));
            itemsPanel.add(itemsImg[i]);
            itemsPanel.add(itemsImg[i+1]);
            itemsPanel.add(itemsImg[i+2]);
            itemsPanel.add(itemsImg[i+3]);
            itemsPanel.add(itemsPriceTag[i]);
            itemsPanel.add(itemsPrice[i]);
            itemsPanel.add(itemsSpinner[i]);
            tempArea[i]=new JTextArea("\n",1,7);
            tempArea[i].setEditable(false);
            tempArea[i].setBackground(new Color(252, 208, 164));
            itemsPanel.add(tempArea[i]);
            itemsPanel.add(itemsPriceTag[i+1]);
            itemsPanel.add(itemsPrice[i+1]);
            itemsPanel.add(itemsSpinner[i+1]);
            tempArea[i+1]=new JTextArea("\n",1,7);
            tempArea[i+1].setEditable(false);
            tempArea[i+1].setBackground(new Color(252, 208, 164));
            itemsPanel.add(tempArea[i+1]);
            itemsPanel.add(itemsPriceTag[i+2]);
            itemsPanel.add(itemsPrice[i+2]);
            itemsPanel.add(itemsSpinner[i+2]);
            tempArea[i+2]=new JTextArea("\n",1,7);
            tempArea[i+2].setEditable(false);
            tempArea[i+2].setBackground(new Color(252, 208, 164));
            itemsPanel.add(tempArea[i+2]);
            itemsPanel.add(itemsPriceTag[i+3]);
            itemsPanel.add(itemsPrice[i+3]);
            itemsPanel.add(itemsSpinner[i+3]);
        }
        itemsPanel.setBackground(new Color(252, 208, 164));
        JScrollPane sp2 = new JScrollPane(itemsPanel) {
        @Override
        public Dimension getPreferredSize() {
        return new Dimension(1100, 650);}
        };
        placeOrder=new JButton("Place Order");
        placeOrder.setFocusable(false);
        placeOrder.addActionListener(this);
        placeOrder.setFont(new Font("Sans_Serif", Font.BOLD, 25));
        placeOrder.setForeground(new Color(255,255,255));
        placeOrder.setBackground(new Color(230, 97, 14));
        
        cart=new JButton("View Cart");
        cart.addActionListener(this);
        cart.setFocusable(false);
        cart.setFont(new Font("Sans_Serif", Font.BOLD, 25));
        cart.setForeground(new Color(255,255,255));
        cart.setBackground(new Color(230, 97, 14));
        
        signOut=new JButton("Sign-Out");
        signOut.addActionListener(this);
        signOut.setFocusable(false);
        signOut.setFont(new Font("Sans_Serif", Font.BOLD, 25));
        signOut.setForeground(new Color(255,255,255));
        signOut.setBackground(new Color(230, 97, 14));
        cAccFrame.add(statusMsg);
        cAccFrame.add(sp);
        cAccFrame.add(selectionMsg);
        JTextArea temp3=new JTextArea("\n",1,90);
        temp3.setEditable(false);
        temp3.setBackground(new Color(222, 240, 250));
        cAccFrame.add(temp3);
        cAccFrame.add(sp2);
        JTextArea temp4=new JTextArea("\n",1,60);
        temp4.setEditable(false);
        temp4.setBackground(new Color(222, 240, 250));
        cAccFrame.add(temp4);
        cAccFrame.add(placeOrder);
        cAccFrame.add(cart);
        cAccFrame.add(signOut);
        cAccFrame.setVisible(true);
        cAccFrame.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Place Order"))
        {
            try 
        {
            int total=0;
            for(int i=0;i<12;i++)
            {
                try {
                    itemsSpinner[i].commitEdit();
                } catch (ParseException ex) {
                    Logger.getLogger(ChildAccount.class.getName()).log(Level.SEVERE, null, ex);
                }
                if((Integer)itemsSpinner[i].getValue()>0)
                {
                    total=total+((Integer)itemsSpinner[i].getValue()*Integer.parseInt(priceStr[i]));
                }
            }
            if(total>Integer.parseInt(data[0][2]))
            {
                JLabel label = new JLabel("OOPs!!!  Low Balance.");
                label.setFont(new Font("Arial", Font.BOLD, 18));
                label.setForeground(new Color(230, 97, 14));
                 UIManager UI=new UIManager();
                 UI.put("OptionPane.background",new ColorUIResource(222, 240, 250));
                 UI.put("Panel.background",new ColorUIResource(222, 240, 250));
                final JOptionPane pane = new JOptionPane(label);
                final JDialog d = pane.createDialog((JFrame)null, "Error Message");
                d.setLocation(540,450);
                d.setVisible(true);
            }
            else if(total>Integer.parseInt(data[0][3]))
            {
                JLabel label = new JLabel("OOPs!!!  Max Limit reached.");
                label.setFont(new Font("Arial", Font.BOLD, 18));
                label.setForeground(new Color(230, 97, 14));
                 UIManager UI=new UIManager();
                 UI.put("OptionPane.background",new ColorUIResource(222, 240, 250));
                 UI.put("Panel.background",new ColorUIResource(222, 240, 250));
                final JOptionPane pane = new JOptionPane(label);
                final JDialog d = pane.createDialog((JFrame)null, "Error Message");
                d.setLocation(540,450);
                d.setVisible(true);
            }
            else
            {
                JLabel label = new JLabel("Order Placed SuccessFully.");
                label.setFont(new Font("Arial", Font.BOLD, 18));
                label.setForeground(new Color(230, 97, 14));
                 UIManager UI=new UIManager();
                 UI.put("OptionPane.background",new ColorUIResource(222, 240, 250));
                 UI.put("Panel.background",new ColorUIResource(222, 240, 250));
                final JOptionPane pane = new JOptionPane(label);
                final JDialog d = pane.createDialog((JFrame)null, "Congratulations!!");
                d.setLocation(540,450);
                d.setVisible(true);
                Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending_machine","root","");
                java.sql.Statement stm1 = con1.createStatement();
                String name="";
                for(int i=0;i<12;i++)
                {
                try {
                    itemsSpinner[i].commitEdit();
                } catch (ParseException ex) {
                    Logger.getLogger(ChildAccount.class.getName()).log(Level.SEVERE, null, ex);
                }
                if((Integer)itemsSpinner[i].getValue()>0)
                {
                    String quan=Integer.toString(spinMax[i]-(Integer)itemsSpinner[i].getValue());
                    String query1="UPDATE inventory SET item_quantity = "+quan+" WHERE item_id = "+Integer.toString(i)+";";
                    stm1.executeUpdate(query1);
                    query1="select item_name from inventory where item_id="+Integer.toString(i)+";";
                    ResultSet rs= stm1.executeQuery(query1);
                    if(rs.next())
                        name=rs.getString(1);
                    query1="INSERT INTO c_spending_history VALUES ('"+childRollno+"',CURRENT_TIMESTAMP,'"+name+"',"+priceStr[i]+","+itemsSpinner[i].getValue()+")";
                    stm1.executeUpdate(query1);
                }
                }
                String bal=Integer.toString(Integer.parseInt(data[0][2])-total);
                String query1="UPDATE c_account SET c_balance = "+bal+" WHERE c_rollno = '"+childRollno+"';";
                stm1.executeUpdate(query1);
                new ChildAccount(childRollno);
                cAccFrame.dispose();
            }
        } 
            catch (SQLException ex) {
                Logger.getLogger(ParentSignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getActionCommand().equals("View Cart"))
        {
            int[] spinVal=new int[12];
            Arrays.fill(spinVal,0);
            for(int i=0;i<12;i++)
            {
                try {
                    itemsSpinner[i].commitEdit();
                } catch (ParseException ex) {
                    Logger.getLogger(ChildAccount.class.getName()).log(Level.SEVERE, null, ex);
                }
                if((Integer)itemsSpinner[i].getValue()>0)
                {
                    spinVal[i]=(Integer)itemsSpinner[i].getValue();
                }
            }
            new Cart(spinVal);
        }
        else if(e.getActionCommand().equals("Sign-Out"))
        {
            cAccFrame.dispose();
            new MainMenu();
        }
    }
}

