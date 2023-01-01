
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

public class UpdatedAmount implements ActionListener
{
    JFrame parentRef;
    String pCNIC;
    String rollNo;
    String oldAmount;
    String newAmount;
    JFrame mainFrame;
    JTextArea information;
    JPanel rollDetails, prevBalDetails,
            currBalDetails;
    JLabel rollLabel;    
    JLabel prevBalLabel;
    JLabel currBalLabel;
    JTextField rollField, prevBalField, currBalField;
    JButton ok;
    
    UpdatedAmount(String roll,String old,String newAm,JFrame fr,String cnic)
    {
        String pCNIC=cnic;
        parentRef=fr;
        rollNo=roll;
        oldAmount=old;
        newAmount=newAm;
        initUpdatedAmount();
    }
    void initUpdatedAmount()
    {
        mainFrame=new JFrame("Updated Amount");
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
        prevBalDetails = new JPanel(new FlowLayout());
        prevBalDetails.setBackground(new Color(222, 240, 250));
        currBalDetails = new JPanel(new FlowLayout());
        currBalDetails.setBackground(new Color(222, 240, 250));
        
        rollLabel=new JLabel(" Roll No.");
        rollLabel.setFont(new Font("SanSerif", Font.BOLD, 16));
        rollLabel.setForeground(new Color(0, 112, 172));
        rollField=new JTextField(12);
        rollField.setText(rollNo);
        rollField.setEditable(false);
        rollField.setFont(new Font("SanSerif", Font.PLAIN, 14));
        rollField.setForeground(new Color(230, 97, 14));
        rollField.setHorizontalAlignment(JTextField.CENTER);
                
        prevBalLabel=new JLabel(" Previous Balance");
        prevBalLabel.setFont(new Font("SanSerif", Font.BOLD, 16));
        prevBalLabel.setForeground(new Color(0, 112, 172));
        prevBalField=new JTextField(12);
        prevBalField.setText(oldAmount);
        prevBalField.setEditable(false);
        prevBalField.setFont(new Font("SanSerif", Font.PLAIN, 14));
        prevBalField.setForeground(new Color(230, 97, 14));
        prevBalField.setHorizontalAlignment(JTextField.CENTER);

        currBalLabel=new JLabel(" Current Balance");
        currBalLabel.setFont(new Font("SanSerif", Font.BOLD, 16));
        currBalLabel.setForeground(new Color(0, 112, 172));
        currBalField=new JTextField(12);
        currBalField.setText(newAmount);
        currBalField.setEditable(false);
        currBalField.setFont(new Font("SanSerif", Font.PLAIN, 14));
        currBalField.setForeground(new Color(230, 97, 14));
        currBalField.setHorizontalAlignment(JTextField.CENTER);

        rollDetails.add(rollLabel,FlowLayout.LEFT);
        JTextArea temp1=new JTextArea("\t ",1,1);        
        temp1.setBackground(new Color(222, 240, 250));
        temp1.setEditable(false);
        temp1.setFocusable(false);
        rollDetails.add(temp1,FlowLayout.CENTER);
        rollDetails.add(rollField, FlowLayout.RIGHT);
        
        prevBalDetails.add(prevBalLabel,FlowLayout.LEFT);
        JTextArea temp3=new JTextArea("      ",1,1);
        temp3.setBackground(new Color(222, 240, 250));
        temp3.setEditable(false);
        temp3.setFocusable(false);
        prevBalDetails.add(temp3,FlowLayout.CENTER);
        prevBalDetails.add(prevBalField,FlowLayout.RIGHT);
        
        currBalDetails.add(currBalLabel,FlowLayout.LEFT);
        JTextArea temp4=new JTextArea("         ",1,1);
        temp4.setBackground(new Color(222, 240, 250));
        temp4.setEditable(false);
        temp4.setFocusable(false);
        currBalDetails.add(temp4, FlowLayout.CENTER);
        currBalDetails.add(currBalField, FlowLayout.RIGHT);        

        JTextArea temp=new JTextArea("\t\t                    ",1,1);        
        temp.setBackground(new Color(222, 240, 250));
        temp.setEditable(false);
        temp.setFocusable(false);
        
        mainFrame.add(rollDetails);
        mainFrame.add(prevBalDetails);
        mainFrame.add(currBalDetails);
        mainFrame.add(temp);        
        mainFrame.add(ok);
        
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Ok"))
        {
            mainFrame.dispose();
            parentRef.dispose();
            new ParentAccount(pCNIC); 
       }
    }
}