import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import javafx.application.*;
import java.util.*;

import java.io.*;
import java.util.*;
import java.text.*;


public class VCCloudFrame extends JFrame
{    
   private static final int FRAME_WIDTH = 650;
   private static final int FRAME_HEIGHT = 900;

   private JTextField idField;
   private JTextField infoField;
   private JTextField timeField;

   private JButton addUserButton;
   private JButton submitButton;
   private JButton clearButton;

   private JLabel idLabel;
   private JLabel infoLabel;
   private JLabel timeLabel;

   private JRadioButton ownerButton;
   private JRadioButton clientButton;

   private String userData = "";


   public VCCloudFrame()
   {  
      createLabels();
      createTextField();
      createButton();
      createComboBox();
      createPanel();
      
      setTitle("Vehicular Cloud Console");
      setSize(FRAME_WIDTH, FRAME_HEIGHT);
   }

   private void createLabels()
   {
      idLabel = new JLabel();
      idLabel.setBounds(75, 150, 100, 100);

      infoLabel = new JLabel();
      infoLabel.setBounds(75, 250, 300, 100);

      timeLabel = new JLabel();
      timeLabel.setBounds(75, 350, 300, 100);
   }

   class OwnerButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
        idLabel.setText("Enter Owner ID");
        infoLabel.setText("Enter Vehicle Information");
        timeLabel.setText("Enter Approximate Residency Time of the Vehicle");
      }            
   }

   class clientButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
        idLabel.setText("Enter Client ID");
        infoLabel.setText("Enter Approximate Job Duration");
        timeLabel.setText("Enter Job Deadline");
      }            
   }

   private void createComboBox()
   {
        ownerButton = new JRadioButton("Owner");
        clientButton = new JRadioButton("Client");

        ownerButton.setBounds(100,100,100,30);    
        clientButton.setBounds(200,100,100,30);   

        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(ownerButton);
        bgroup.add(clientButton);

        ActionListener ownerButtonlistener = new OwnerButtonListener();
        ownerButton.addActionListener(ownerButtonlistener);

        ActionListener clientButtonlistener = new clientButtonListener();
        clientButton.addActionListener(clientButtonlistener);

   }

   private void createTextField()
   {
      final int FIELD_WIDTH = 10;

      idField = new JTextField(FIELD_WIDTH);
      idField.setBounds(75, 225, 300, 30);

      infoField = new JTextField(FIELD_WIDTH);
      infoField.setBounds(75, 325, 300, 30);

      timeField = new JTextField(FIELD_WIDTH);
      timeField.setBounds(75, 425, 300, 30);
   }


   class SubmitButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
        addUser();
        clearText();
        
        try 
        {
            File file = new File("C:\\Users\\faiza\\OneDrive\\Desktop\\UserData.txt");
            
            if (!file.exists()) file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(userData);
            bw.close();
            
            System.out.println("Done");
         } 
         catch (IOException e) 
         {
            e.printStackTrace();
         } 
      }            
   }
      
   class ClearButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         clearText();
      }            
   }

   class AddUserButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
        addUser();
        clearText();
      }            
   }

   private void addUser()
   { 
        String idText = idField.getText();
        String infoText = infoField.getText();
        String timeText = timeField.getText();
        
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        String strDate = dateFormat.format(date);  

        if (ownerButton.isSelected()) userData += "Owner,";
        else if (clientButton.isSelected()) userData += "Client,";

        userData += idText + "," + infoText + "," + timeText + "," + strDate + '\n';
   }

   private void clearText()
   {
        idField.setText("");
        infoField.setText("");
        timeField.setText("");
   }

   private void createButton()
   {
      submitButton = new JButton("Submit");
      submitButton.setBounds(75, 625, 100, 30);  
      ActionListener listener = new SubmitButtonListener();
      submitButton.addActionListener(listener);

      clearButton = new JButton("Clear");
      clearButton.setBounds(275, 525, 100, 30);  
      ActionListener clearListener = new ClearButtonListener();
      clearButton.addActionListener(clearListener);

      addUserButton = new JButton("Add User");
      addUserButton.setBounds(75, 525, 100, 30);  
      ActionListener addUserButtonListener = new AddUserButtonListener();
      addUserButton.addActionListener(addUserButtonListener);
   }

   private void createPanel()
   {
      JPanel panel = new JPanel();
      
      panel.setLayout(null);
      panel.add(idField);
      panel.add(infoField);
      panel.add(infoLabel);
      panel.add(timeField);
      panel.add(timeLabel);
      panel.add(submitButton);
      panel.add(clearButton);
      panel.add(addUserButton);
      panel.add(idLabel);  
      panel.add(ownerButton); 
      panel.add(clientButton);
      add(panel);
   } 

   public static void main(String[] args) 
   {  
      JFrame frame = new VCCloudFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }
}