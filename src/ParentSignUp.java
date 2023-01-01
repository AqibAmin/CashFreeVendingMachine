
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class ParentSignUp implements ActionListener
{
    JFrame mainFrame;
    JTextArea information;
    JPanel cnicDetails, userDetails,
            passDetails, cnfrmPassDetails, space, btns;
    JLabel cnicLabel;    
    JLabel userLabel;
    JLabel passLabel;
    JLabel cnfrmPassLabel;
    JTextField cnicField, userField;
    JPasswordField passField, cnfrmPassField;
    JButton signup, cancel;

    ParentSignUp()
    {
        initParentSignUp();
    }
    void initParentSignUp()
    {
        mainFrame=new JFrame("Create Parent Account");
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setLocation(470,300);
        mainFrame.setSize(new Dimension(450,380));
        mainFrame.getContentPane().setBackground(new Color(222, 240, 250));

        information=new JTextArea("\nEnter the following details to Create your Account\n\n");
        information.setEditable(false);
        information.setFocusable(false);
        information.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
        information.setBackground(new Color(222, 240, 250));
        information.setForeground(new Color(230, 97, 14));
        
        cnicDetails = new JPanel(new FlowLayout());
        cnicDetails.setBackground(new Color(222, 240, 250));
        userDetails = new JPanel(new FlowLayout());
        userDetails.setBackground(new Color(222, 240, 250));
        passDetails = new JPanel(new FlowLayout());
        passDetails.setBackground(new Color(222, 240, 250));
        cnfrmPassDetails = new JPanel(new FlowLayout());
        cnfrmPassDetails.setBackground(new Color(222, 240, 250));
        space = new JPanel(new FlowLayout());
        space.setBackground(new Color(222, 240, 250));
        btns = new JPanel(new FlowLayout());
        btns.setBackground(new Color(222, 240, 250));
        
        cnicLabel=new JLabel("CNIC No.");
        cnicLabel.setFont(new Font("SanSerif", Font.BOLD, 18));
        cnicLabel.setForeground(new Color(0, 112, 172));
        cnicField=new JTextField(16);
        cnicField.setFont(new Font("SanSerif", Font.PLAIN, 16));
        cnicField.setForeground(new Color(230, 97, 14));
        cnicField.setHorizontalAlignment(JTextField.CENTER);
        
        userLabel=new JLabel("Username");
        userLabel.setFont(new Font("SanSerif", Font.BOLD, 18));
        userLabel.setForeground(new Color(0, 112, 172));
        userField=new JTextField(16);
        userField.setFont(new Font("SanSerif", Font.PLAIN, 16));
        userField.setForeground(new Color(230, 97, 14));
        userField.setHorizontalAlignment(JTextField.CENTER);
        
        passLabel=new JLabel("Password");
        passLabel.setFont(new Font("SanSerif", Font.BOLD, 18));
        passLabel.setForeground(new Color(0, 112, 172));
        passField=new JPasswordField(16);
        passField.setFont(new Font("SanSerif", Font.PLAIN, 16));
        passField.setForeground(new Color(230, 97, 14));
        passField.setHorizontalAlignment(JTextField.CENTER);

        cnfrmPassLabel=new JLabel("Confirm Password");
        cnfrmPassLabel.setFont(new Font("SanSerif", Font.BOLD, 18));
        cnfrmPassLabel.setForeground(new Color(0, 112, 172));
        cnfrmPassField=new JPasswordField(16);
        cnfrmPassField.setFont(new Font("SanSerif", Font.PLAIN, 16));
        cnfrmPassField.setForeground(new Color(230, 97, 14));
        cnfrmPassField.setHorizontalAlignment(JTextField.CENTER);
        
        signup = new JButton("Sign-Up");
        signup.addActionListener(this);
        signup.setBackground(new Color(230, 97, 14));
        signup.setForeground(new Color(255,255,255));
        signup.setFont(new Font("SanSerif", Font.BOLD, 20));
        signup.setFocusable(false);
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        cancel.setBackground(new Color(230, 97, 14));
        cancel.setForeground(new Color(255,255,255));
        cancel.setFont(new Font("SanSerif", Font.BOLD, 20));
        cancel.setFocusable(false);
        
        cnicDetails.add(cnicLabel,FlowLayout.LEFT);
        JTextArea temp1=new JTextArea("\t      ",1,1);        
        temp1.setBackground(new Color(222, 240, 250));
        temp1.setEditable(false);
        temp1.setFocusable(false);
        cnicDetails.add(temp1,FlowLayout.CENTER);
        cnicDetails.add(cnicField, FlowLayout.RIGHT);
        
        userDetails.add(userLabel,FlowLayout.LEFT);
        JTextArea temp2=new JTextArea("\t   ",1,1);        
        temp2.setBackground(new Color(222, 240, 250));
        temp2.setEditable(false);
        temp2.setFocusable(false);
        userDetails.add(temp2,FlowLayout.CENTER);
        userDetails.add(userField,FlowLayout.RIGHT);

        passDetails.add(passLabel,FlowLayout.LEFT);
        JTextArea temp3=new JTextArea("\t    ",1,1);
        temp3.setBackground(new Color(222, 240, 250));
        temp3.setEditable(false);
        temp3.setFocusable(false);
        passDetails.add(temp3,FlowLayout.CENTER);
        passDetails.add(passField,FlowLayout.RIGHT);
        
        cnfrmPassDetails.add(cnfrmPassLabel,FlowLayout.LEFT);
        JTextArea temp4=new JTextArea("         ",1,1);
        temp4.setBackground(new Color(222, 240, 250));
        temp4.setEditable(false);
        temp4.setFocusable(false);
        cnfrmPassDetails.add(temp4, FlowLayout.CENTER);
        cnfrmPassDetails.add(cnfrmPassField, FlowLayout.RIGHT);        
        
        JTextArea temp5 = new JTextArea("\n\n\n\n\n\t\t   ",1,1);
        temp5.setBackground(new Color(222, 240, 250));
        temp5.setEditable(false);
        temp5.setFocusable(false);
        
        btns.add(signup);
        btns.add(cancel);
        
        space.add(temp5);
        
        mainFrame.add(information);
        mainFrame.add(cnicDetails);
        mainFrame.add(userDetails);
        mainFrame.add(passDetails);
        mainFrame.add(cnfrmPassDetails);
        mainFrame.add(space);        
        mainFrame.add(btns);
        
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Cancel"))
        {
            mainFrame.dispose();
        }
        else if(e.getActionCommand().equals("Sign-Up"))
        {
            String cnic=cnicField.getText();
            String user=userField.getText();
            String pass=passField.getText();
            String cnpass=cnfrmPassField.getText();
            if(cnic.equals("") || user.equals("") || pass.equals("") || cnpass.equals(""))
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
            else if(!pass.equals(cnpass))
            {
                JLabel label = new JLabel("Both Passwords are not the same.");
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
            else if(!(cnic.equals("") || user.equals("") || pass.equals("") || cnpass.equals("")))
            {
                try 
            {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending_machine","root","");
            java.sql.Statement stm = con.createStatement();
            String query="SELECT p_cnic FROM parent where p_cnic='"+cnic+"'";
            ResultSet rs=stm.executeQuery(query);
            if(rs.next()==false)
            {
               JLabel label = new JLabel("No parent Exist with that CNIC in Database.");
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
                Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending_machine","root","");
                java.sql.Statement stm1 = con.createStatement();
                String query1="INSERT INTO P_Account VALUES (0,'"+cnic+"','"+user+"','"+pass+"')";
                stm1.executeUpdate(query1);
                JLabel label = new JLabel("Parent Account Created Successfully.");
                label.setFont(new Font("Arial", Font.BOLD, 18));
                label.setForeground(new Color(230, 97, 14));
                 UIManager UI=new UIManager();
                 UI.put("OptionPane.background",new ColorUIResource(222, 240, 250));
                 UI.put("Panel.background",new ColorUIResource(222, 240, 250));
                final JOptionPane pane = new JOptionPane(label);
                final JDialog d = pane.createDialog((JFrame)null, "Congratulations!!");
                d.setLocation(500,450);
                d.setVisible(true);
                mainFrame.dispose();
            }
            }  catch (SQLException ex) {
                Logger.getLogger(ParentSignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }
}
