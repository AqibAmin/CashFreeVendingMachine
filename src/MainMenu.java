
import java.awt.BorderLayout;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aqiba
 */
public class MainMenu implements ActionListener{
    JFrame mainFrame;
    JLabel icon;
    JTextArea information;
    JLabel userNameLabel;
    JLabel passwordLabel;
    JTextField userNameField;
    JPasswordField passwordField;
    JButton parentLogin;
    JButton childLogin;
    JButton createParentAccount;
    JButton manageInventory;
    MainMenu()
    {
        initMainMenu();
    }
    void initMainMenu()
    {
        mainFrame=new JFrame("Vending Machine");
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setLocation(20,40);
        mainFrame.setSize(new Dimension(1200,900));
        mainFrame.getContentPane().setBackground(new Color(222, 240, 250));
        icon=new JLabel(new ImageIcon("logo.png"));
        information=new JTextArea("\n\nHi there ! Welcome to PUCIT Vending machine, "
                + "we provide your \nchild the best services and we don't"
                + " want him to have cash at the\ntime of purchase, "
                + "we keep track of your childs spending for you\nand"
                + " thats a free of cost service, all you have to do is "
                + "to create an               "
                + " \naccount for you and your child and set a limit "
                + "of  his/her   cash \nspending daily and then deposit the amount "
                + "in his/her account.",10,1);
        information.setEditable(false);
        information.setFont(new Font("Serif", Font.BOLD, 25));
        information.setBackground(new Color(222, 240, 250));
        information.setForeground(new Color(230, 97, 14));
        userNameLabel=new JLabel("User Name");
        userNameLabel.setFont(new Font("Serif", Font.BOLD, 25));
        userNameLabel.setForeground(new Color(0, 112, 172));
        passwordLabel=new JLabel("Password ");
        passwordLabel.setFont(new Font("Serif", Font.BOLD, 25));
        passwordLabel.setForeground(new Color(0, 112, 172));
        userNameField=new JTextField(20);
        userNameField.setFont(new Font("Serif", Font.BOLD, 20));
        userNameField.setForeground(new Color(230, 97, 14));
        passwordField =new JPasswordField(20);
        passwordField.setForeground(new Color(230, 97, 14));
        passwordField.setFont(new Font("Serif", Font.BOLD, 20));
        parentLogin=new JButton("Parent Login");
        parentLogin.addActionListener(this);
        parentLogin.setBackground(new Color(0, 112, 172));
        parentLogin.setForeground(new Color(255,255,255));
        parentLogin.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        parentLogin.setFocusable(false);
        childLogin=new JButton("Child Login");
        childLogin.addActionListener(this);
        childLogin.setBackground(new Color(0, 112, 172));
        childLogin.setForeground(new Color(255,255,255));
        childLogin.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        childLogin.setFocusable(false);
        createParentAccount=new JButton("Create Parent Account");
        createParentAccount.addActionListener(this);
        createParentAccount.setIcon(new ImageIcon("createAccount.jpg"));
        createParentAccount.setHorizontalTextPosition(JButton.CENTER);
        createParentAccount.setVerticalTextPosition(JButton.BOTTOM);
        createParentAccount.setFocusable(false);
        createParentAccount.setFont(new Font("Sans_Serif", Font.BOLD, 15));
        createParentAccount.setForeground(new Color(230, 97, 14));
        createParentAccount.setBackground(new Color(222, 240, 250));
        createParentAccount.setBorderPainted(false);
        manageInventory=new JButton("Manage Inventory");
        manageInventory.addActionListener(this);
        manageInventory.setIcon(new ImageIcon("inventory.jpg"));
        manageInventory.setHorizontalTextPosition(JButton.CENTER);
        manageInventory.setVerticalTextPosition(JButton.BOTTOM);
        manageInventory.setFocusable(false);
        manageInventory.setFont(new Font("Sans_Serif", Font.BOLD, 17));
        manageInventory.setForeground(new Color(0, 112, 172));
        manageInventory.setBackground(new Color(222, 240, 250));
        manageInventory.setBorderPainted(false);
        
        mainFrame.add(icon);
        mainFrame.add(information);
        JTextArea temp1=new JTextArea("\n",1,30);
        temp1.setBackground(new Color(222, 240, 250));
        mainFrame.add(temp1);
        mainFrame.add(userNameLabel);
        mainFrame.add(userNameField);
        JTextArea temp2=new JTextArea("\n",1,30);
        temp2.setBackground(new Color(222, 240, 250));
        mainFrame.add(temp2);
        JTextArea temp3=new JTextArea("\n",1,31);
        temp3.setBackground(new Color(222, 240, 250));
        mainFrame.add(temp3);
        mainFrame.add(passwordLabel);
        mainFrame.add(passwordField);
        JTextArea temp4=new JTextArea("\n",1,30);
        temp4.setBackground(new Color(222, 240, 250));
        mainFrame.add(temp4);
        JTextArea temp5=new JTextArea("\n",1,37);
        temp5.setBackground(new Color(222, 240, 250));
        mainFrame.add(temp5);
        mainFrame.add(parentLogin);
        mainFrame.add(childLogin);
        JTextArea temp6=new JTextArea("\n",1,105);
        temp6.setBackground(new Color(222, 240, 250));
        mainFrame.add(temp6);
        mainFrame.add(createParentAccount);
        JTextArea temp7=new JTextArea("\n",1,105);
        temp7.setBackground(new Color(222, 240, 250));
        mainFrame.add(temp7);
        JTextArea temp8=new JTextArea("\n",1,80);
        temp8.setBackground(new Color(222, 240, 250));
        mainFrame.add(temp8);
        mainFrame.add(manageInventory);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Create Parent Account"))
        {
            ParentSignUp pSign=new ParentSignUp();
        }
        else if(e.getActionCommand().equals("Parent Login"))
        {
            String user=userNameField.getText();
            String pass=passwordField.getText();
            if(user.equals("") || pass.equals(""))
            {
                JLabel label = new JLabel("There's an Invalid Input.");
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
            else if(!(user.equals("") || pass.equals("")))
            {
            try 
            {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending_machine","root","");
            java.sql.Statement stm = con.createStatement();
            String query="SELECT p_account_id FROM p_account where ('"+user+"','"+pass+"')=any(select p_username,p_password from p_account)";
            ResultSet rs=stm.executeQuery(query);
            if(rs.next()==false)
            {
               JLabel label = new JLabel("No Parent Exist against that login in Database.");
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
                String p_id=rs.getString(1);
                ParentAccount pAcc=new ParentAccount(p_id);
                mainFrame.dispose();
            }  
            }catch (SQLException ex) {
                Logger.getLogger(ParentSignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
        else if(e.getActionCommand().equals("Child Login"))
        {
            String user1=userNameField.getText();
            String pass1=passwordField.getText();
            if(user1.equals("") || pass1.equals(""))
            {
                JLabel label = new JLabel("There's an Invalid Input.");
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
            else if(!(user1.equals("") || pass1.equals("")))
            {
                try 
            {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending_machine","root","");
            java.sql.Statement stm = con.createStatement();
            String query="SELECT c_rollno FROM c_account where ('"+user1+"','"+pass1+"')=any(select c_rollno,c_password from c_account)";
            ResultSet rs=stm.executeQuery(query);
            if(rs.next()==false)
            {
               JLabel label = new JLabel("No Child Exist against that login in Database.");
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
                ChildAccount cAcc=new ChildAccount(user1);
                mainFrame.dispose();
            }  
            }catch (SQLException ex) {
                Logger.getLogger(ParentSignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
        else if(e.getActionCommand().equals("Manage Inventory"))
        {
            new Inventory();
            mainFrame.dispose();
        }
    }
}
