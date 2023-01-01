
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

public class ChangeMaxLimit implements ActionListener
{
    String pCNIC;
    JFrame parentRef;
    JFrame mainFrame;
    JPanel userDetails, limitDetails,
            space, btns;
    JLabel userLabel;    
    JLabel limitLabel;
    JTextField userField, limitField;
    JButton update, cancel;

    ChangeMaxLimit(JFrame fr, String cnic)
    {
        parentRef=fr;
        pCNIC=cnic;
        initChangeMaxLimit();
    }
    void initChangeMaxLimit()
    {
        mainFrame=new JFrame("Change Limit");
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setLocation(500,360);
        mainFrame.setSize(new Dimension(300,210));
        mainFrame.getContentPane().setBackground(new Color(222, 240, 250));
        
        userDetails = new JPanel(new FlowLayout());
        userDetails.setBackground(new Color(222, 240, 250));
        limitDetails = new JPanel(new FlowLayout());
        limitDetails.setBackground(new Color(222, 240, 250));
        space = new JPanel(new FlowLayout());
        space.setBackground(new Color(222, 240, 250));
        btns = new JPanel(new FlowLayout());
        btns.setBackground(new Color(222, 240, 250));
        
        JTextArea temp2=new JTextArea("\n",1,1);        
        temp2.setBackground(new Color(222, 240, 250));
        temp2.setEditable(false);
        temp2.setFocusable(false);
        
        userLabel=new JLabel("Username");
        userLabel.setFont(new Font("SanSerif", Font.BOLD, 16));
        userLabel.setForeground(new Color(0, 112, 172));
        userField=new JTextField(14);
        userField.setFont(new Font("SanSerif", Font.PLAIN, 13));
        userField.setForeground(new Color(230, 97, 14));
        userField.setHorizontalAlignment(JTextField.CENTER);
                
        limitLabel=new JLabel("Max. Limit");
        limitLabel.setFont(new Font("SanSerif", Font.BOLD, 16));
        limitLabel.setForeground(new Color(0, 112, 172));
        limitField=new JTextField(14);
        limitField.setFont(new Font("SanSerif", Font.PLAIN, 13));
        limitField.setForeground(new Color(230, 97, 14));
        limitField.setHorizontalAlignment(JTextField.CENTER);
        
        update = new JButton("Update");
        update.addActionListener(this);
        update.setBackground(new Color(230, 97, 14));
        update.setForeground(new Color(255,255,255));
        update.setFont(new Font("SanSerif", Font.BOLD, 14));
        update.setFocusable(false);
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        cancel.setBackground(new Color(230, 97, 14));
        cancel.setForeground(new Color(255,255,255));
        cancel.setFont(new Font("SanSerif", Font.BOLD, 14));
        cancel.setFocusable(false);
        
        userDetails.add(userLabel,FlowLayout.LEFT);
        JTextArea temp1=new JTextArea("        ",1,1);        
        temp1.setBackground(new Color(222, 240, 250));
        temp1.setEditable(false);
        temp1.setFocusable(false);
        userDetails.add(temp1,FlowLayout.CENTER);
        userDetails.add(userField, FlowLayout.RIGHT);
        
        limitDetails.add(limitLabel,FlowLayout.LEFT);
        JTextArea temp3=new JTextArea("          ",1,1);
        temp3.setBackground(new Color(222, 240, 250));
        temp3.setEditable(false);
        temp3.setFocusable(false);
        limitDetails.add(temp3,FlowLayout.CENTER);
        limitDetails.add(limitField,FlowLayout.RIGHT);
                
        JTextArea temp5 = new JTextArea("\n\n\n\t ");
        temp5.setBackground(new Color(222, 240, 250));
        temp5.setEditable(false);
        temp5.setFocusable(false);
        
        btns.add(update);
        btns.add(cancel);
        
        space.add(temp5);
        
        mainFrame.add(temp2);
        mainFrame.add(userDetails);
        mainFrame.add(limitDetails);
        mainFrame.add(space);        
        mainFrame.add(btns);
        
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Update"))
        {
            String rollNo=userField.getText();
            String currLimit=limitField.getText();
            if(rollNo.equals("") || currLimit.equals(""))
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
            else if(!(rollNo.equals("") || currLimit.equals("")))
            {
                try 
            {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending_machine","root","");
            java.sql.Statement stm = con.createStatement();
            String query="SELECT c_dailylimit FROM c_account where c_rollno='"+rollNo+"'";
            ResultSet rs=stm.executeQuery(query);
            if(rs.next()==false)
            {
               JLabel label = new JLabel("No Child Exist with that rollNo in Database.");
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
                String prevLimit = rs.getString(1);
                Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending_machine","root","");
                java.sql.Statement stm1 = con1.createStatement();
                String query1="UPDATE c_account SET c_dailylimit = "+currLimit+" WHERE c_rollno = '"+rollNo+"'";
                stm1.executeUpdate(query1);
                new UpdatedMaxLimit(rollNo,prevLimit,currLimit,parentRef,pCNIC);
                mainFrame.dispose();
            }
            }  catch (SQLException ex) {
                Logger.getLogger(ParentSignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
        else if(e.getActionCommand().equals("Cancel"))
        {
            mainFrame.dispose();
        }
    }
   
}
