
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
public class Inventory implements ActionListener{
    JFrame invenFrame;
    JLabel statusMsg;
    JPanel itemsPanel;
    JLabel[] itemsImg;
    JLabel[] itemsPriceTag;
    JTextField[] itemsPrice;
    JSpinner[] itemsSpinner;
    JButton update;
    JButton close;
    String[] priceStr;
    String[][] data;
    String[] tempQuantity;
    int[] spinMax;
    SpinnerModel[] spinValue;
    Inventory()
    {
        initInventory();
    }
    void initInventory()
    {
        invenFrame=new JFrame("Inventory Management");
        invenFrame.setLayout(new FlowLayout());
        invenFrame.setLocation(20,40);
        invenFrame.setSize(new Dimension(1200,830));
        invenFrame.getContentPane().setBackground(new Color(222, 240, 250));
        statusMsg=new JLabel("Inventory Status");
        statusMsg.setForeground(new Color(0, 112, 172));
        statusMsg.setFont(new Font("Serif", Font.BOLD, 25));
        
        itemsPanel = new JPanel() {
        @Override
        public Dimension getPreferredSize() {
        return new Dimension(1100, 950);}
        };
        priceStr=new String[12];
        tempQuantity=new String[12];
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
            tempQuantity[i]=rs.getString(2);
            spinMax[i]=50;
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
        update=new JButton("Update Inventory");
        update.setFocusable(false);
        update.addActionListener(this);
        update.setFont(new Font("Sans_Serif", Font.BOLD, 25));
        update.setForeground(new Color(255,255,255));
        update.setBackground(new Color(230, 97, 14));
        
        
        close=new JButton("Close");
        close.addActionListener(this);
        close.setFocusable(false);
        close.setFont(new Font("Sans_Serif", Font.BOLD, 25));
        close.setForeground(new Color(255,255,255));
        close.setBackground(new Color(230, 97, 14));
        invenFrame.add(statusMsg);
        JTextArea temp3=new JTextArea("\n",1,100);
        temp3.setEditable(false);
        temp3.setBackground(new Color(222, 240, 250));
        invenFrame.add(temp3);
        invenFrame.add(sp2);
        JTextArea temp4=new JTextArea("\n",1,60);
        temp4.setEditable(false);
        temp4.setBackground(new Color(222, 240, 250));
        invenFrame.add(temp4);
        invenFrame.add(update);
        invenFrame.add(close);
        invenFrame.setVisible(true);
        invenFrame.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Update Inventory"))
        {
            Connection con1;
            try {
                con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending_machine","root","");
                java.sql.Statement stm1 = con1.createStatement();
                for(int i=0;i<12;i++)
                {
                    String quan=Integer.toString(Integer.parseInt(tempQuantity[i])+(Integer)itemsSpinner[i].getValue());
                    String query1="UPDATE inventory SET item_quantity = "+quan+" WHERE item_id = "+Integer.toString(i)+";";
                    stm1.executeUpdate(query1);
                    query1="UPDATE inventory SET item_price = "+itemsPrice[i].getText()+" WHERE item_id = "+Integer.toString(i)+";";
                    stm1.executeUpdate(query1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
            }
            new Inventory();
            invenFrame.dispose();
        }
        else if(e.getActionCommand().equals("Close"))
        {
            new MainMenu();
            invenFrame.dispose();
            
        }
    }
}

