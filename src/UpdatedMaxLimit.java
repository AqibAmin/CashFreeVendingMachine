
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UpdatedMaxLimit implements ActionListener
{
    String rollNo;
    String prevLimit;
    String currLimit;
    JFrame parentRef;
    String pCNIC;
    JFrame mainFrame;
    JTextArea information;
    JPanel rollDetails, prevLimitDetails,
            currLimitDetails;
    JLabel rollLabel;    
    JLabel prevLimitLabel;
    JLabel currLimitLabel;
    JTextField rollField, prevLimitField, currLimitField;
    JButton ok;
    
    UpdatedMaxLimit(String roll,String prev,String curr,JFrame fr, String cnic)
    {
        parentRef=fr;
        pCNIC=cnic;
        rollNo=roll;
        prevLimit=prev;
        currLimit=curr;
        initUpdatedAmount();
    }
    void initUpdatedAmount()
    {
        mainFrame=new JFrame("Updated Limit");
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setLocation(500,360);
        mainFrame.setSize(new Dimension(350,200));
        mainFrame.getContentPane().setBackground(new Color(222, 240, 250));

        ok = new JButton("Ok");
        ok.addActionListener(this);
        ok.setBackground(new Color(230, 97, 14));
        ok.setForeground(new Color(255,255,255));
        ok.setFont(new Font("SanSerif", Font.BOLD, 14));
        ok.setFocusable(false);
        
        information=new JTextArea("");
        information.setEditable(false);
        information.setFocusable(false);
        information.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
        information.setBackground(new Color(222, 240, 250));
        information.setForeground(new Color(230, 97, 14));
        
        rollDetails = new JPanel(new FlowLayout());
        rollDetails.setBackground(new Color(222, 240, 250));
        prevLimitDetails = new JPanel(new FlowLayout());
        prevLimitDetails.setBackground(new Color(222, 240, 250));
        currLimitDetails = new JPanel(new FlowLayout());
        currLimitDetails.setBackground(new Color(222, 240, 250));
        
        rollLabel=new JLabel(" Roll No.");
        rollLabel.setFont(new Font("SanSerif", Font.BOLD, 16));
        rollLabel.setForeground(new Color(0, 112, 172));
        rollField=new JTextField(12);
        rollField.setText(rollNo);
        rollField.setEditable(false);
        rollField.setFont(new Font("SanSerif", Font.PLAIN, 14));
        rollField.setForeground(new Color(230, 97, 14));
        rollField.setHorizontalAlignment(JTextField.CENTER);
                
        prevLimitLabel=new JLabel(" Previous Limit");
        prevLimitLabel.setFont(new Font("SanSerif", Font.BOLD, 16));
        prevLimitLabel.setForeground(new Color(0, 112, 172));
        prevLimitField=new JTextField(12);
        prevLimitField.setText(prevLimit);
        prevLimitField.setEditable(false);
        prevLimitField.setFont(new Font("SanSerif", Font.PLAIN, 14));
        prevLimitField.setForeground(new Color(230, 97, 14));
        prevLimitField.setHorizontalAlignment(JTextField.CENTER);

        currLimitLabel=new JLabel(" Current Limit");
        currLimitLabel.setFont(new Font("SanSerif", Font.BOLD, 16));
        currLimitLabel.setForeground(new Color(0, 112, 172));
        currLimitField=new JTextField(12);
        currLimitField.setText(currLimit);
        currLimitField.setEditable(false);
        currLimitField.setFont(new Font("SanSerif", Font.PLAIN, 14));
        currLimitField.setForeground(new Color(230, 97, 14));
        currLimitField.setHorizontalAlignment(JTextField.CENTER);

        rollDetails.add(rollLabel,FlowLayout.LEFT);
        JTextArea temp1=new JTextArea("                      ",1,1);        
        temp1.setBackground(new Color(222, 240, 250));
        temp1.setEditable(false);
        temp1.setFocusable(false);
        rollDetails.add(temp1,FlowLayout.CENTER);
        rollDetails.add(rollField, FlowLayout.RIGHT);
        
        prevLimitDetails.add(prevLimitLabel,FlowLayout.LEFT);
        JTextArea temp3=new JTextArea("      ",1,1);
        temp3.setBackground(new Color(222, 240, 250));
        temp3.setEditable(false);
        temp3.setFocusable(false);
        prevLimitDetails.add(temp3,FlowLayout.CENTER);
        prevLimitDetails.add(prevLimitField,FlowLayout.RIGHT);
        
        currLimitDetails.add(currLimitLabel,FlowLayout.LEFT);
        JTextArea temp4=new JTextArea("         ",1,1);
        temp4.setBackground(new Color(222, 240, 250));
        temp4.setEditable(false);
        temp4.setFocusable(false);
        currLimitDetails.add(temp4, FlowLayout.CENTER);
        currLimitDetails.add(currLimitField, FlowLayout.RIGHT);        

        JTextArea temp=new JTextArea("\t\t                    ",1,1);        
        temp.setBackground(new Color(222, 240, 250));
        temp.setEditable(false);
        temp.setFocusable(false);
        
        mainFrame.add(rollDetails);
        mainFrame.add(prevLimitDetails);
        mainFrame.add(currLimitDetails);
        mainFrame.add(temp);        
        mainFrame.add(ok);
        
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }    

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getActionCommand().equals("Ok"))
        {
            mainFrame.dispose();
            parentRef.dispose();
            new ParentAccount(pCNIC);
        }    
    }
}