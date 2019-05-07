/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thediary;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author lkf5083
 */
public class RecoverUI extends JFrame{
    
    JPanel mainPanel;
    JPanel inputPanel;
    JPanel actionPanel;
    
    JLabel securityQuestionLabelOne;
    JTextField securityQuestionTextFieldOne;
    JLabel securityAnswerLabelOne;
    JTextField securityAnswerTextFieldOne;
    
    JLabel securityQuestionLabelTwo;
    JTextField securityQuestionTextFieldTwo;
    JLabel securityAnswerLabelTwo;
    JTextField securityAnswerTextFieldTwo;
    
    JButton recoverButton;
    JButton cancelButton;
    
    private Account recoverAccount;
    private ArrayList<String []> securityQuestionAndAnswers;
    
    RecoverUI(Account a){

        super("Recover Your User Account");
        recoverAccount = a;
        securityQuestionAndAnswers = recoverAccount.getSecurityQuestionAndAnswers();
        initialRecoverUI();
        actionRecover();
        actionCancel();

    }

    private void initialRecoverUI() {
        
        mainPanel = new JPanel();
        inputPanel = new JPanel();
        actionPanel = new JPanel();
        
        securityQuestionLabelOne = new JLabel("Securty Question 1");
        securityQuestionTextFieldOne = new JTextField(30);
        securityQuestionTextFieldOne.setEditable(false);
        securityQuestionTextFieldOne.setText(securityQuestionAndAnswers.get(0)[0]);
        securityAnswerLabelOne = new JLabel("Security Answer 1");
        securityAnswerTextFieldOne = new JTextField(30);
        
        securityQuestionLabelTwo = new JLabel("Securty Question 2");
        securityQuestionTextFieldTwo = new JTextField(30);
        securityQuestionTextFieldTwo.setEditable(false);
        securityQuestionTextFieldTwo.setText(securityQuestionAndAnswers.get(1)[0]);
        securityAnswerLabelTwo = new JLabel("Security Answer 2");
        securityAnswerTextFieldTwo = new JTextField(30);      

        recoverButton = new JButton("Recover");
        cancelButton = new JButton("Cancel");      
              
        addComponentsToInputPanel();
        addComponentsToActionPanel();
        
        addComponentsToMainPanel();
        
        setContentPane(mainPanel);
        
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
     
    }

    private void addComponentsToInputPanel() {
                  
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
               
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 40;
        c.gridy = 30;
        c.gridwidth = 15;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(securityQuestionLabelOne, c);
                        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 70;
        c.gridy = 30;
        c.gridwidth = 25;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(securityQuestionTextFieldOne, c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 40;
        c.gridy = 45;
        c.gridwidth = 15;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(securityAnswerLabelOne, c);
                        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 70;
        c.gridy = 45;
        c.gridwidth = 25;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(securityAnswerTextFieldOne, c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 40;
        c.gridy = 60;
        c.gridwidth = 15;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(securityQuestionLabelTwo, c);
                        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 70;
        c.gridy = 60;
        c.gridwidth = 25;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(securityQuestionTextFieldTwo, c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 40;
        c.gridy = 75;
        c.gridwidth = 15;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(securityAnswerLabelTwo, c);
                        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 70;
        c.gridy = 75;
        c.gridwidth = 25;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(securityAnswerTextFieldTwo, c);   
        
    }

    private void addComponentsToActionPanel() {
        
        actionPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
                
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 10;
        c.gridy = 10;
        c.gridwidth = 10;
        c.gridheight = 10;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(25, 25, 25, 25);
        actionPanel.add(recoverButton, c);
                
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 30;
        c.gridy = 10;
        c.gridwidth = 10;
        c.gridheight = 10;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(25, 25, 25, 25);
        actionPanel.add(cancelButton, c);   
        
    }

    private void addComponentsToMainPanel() {
        
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
                
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 50;
        c.gridy = 30;
        c.gridwidth = 800;
        c.gridheight = 600;
        c.weightx = 8;
        c.weighty = 6;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(20, 20, 20, 20);
        mainPanel.add(inputPanel, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 20;
        c.gridy = 800;
        c.gridwidth = 400;
        c.gridheight = 300;
        c.weightx = 4;
        c.weighty = 3;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(40, 40, 40, 40);
        mainPanel.add(actionPanel, c);
        
    }
        
    private void actionRecover() {
        
        recoverButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {

                String inputSecurityAnswerOne = securityAnswerTextFieldOne.getText();
                String inputSecurityAnswerTwo = securityAnswerTextFieldTwo.getText();           
                
                if (inputSecurityAnswerOne.isEmpty() || inputSecurityAnswerTwo.isEmpty()) {
                    
                    JOptionPane.showMessageDialog(null, "All security answers can not be empty!", "Invalid security answers", JOptionPane.ERROR_MESSAGE);
                    securityAnswerTextFieldOne.setText("");
                    securityAnswerTextFieldTwo.setText("");
                    
                    securityAnswerTextFieldOne.requestFocusInWindow();
                    
                    return;
                    
                }
                
                if (inputSecurityAnswerOne.equalsIgnoreCase(securityQuestionAndAnswers.get(0)[1])) {
                    
                    if (inputSecurityAnswerTwo.equalsIgnoreCase(securityQuestionAndAnswers.get(1)[1])) {
                        
                        UpdatePasswordUI updatePasswordUI = new UpdatePasswordUI(recoverAccount);
                        dispose();
                        
                    }
                    
                    else {
                        
                        JOptionPane.showMessageDialog(null, "Security answer two is wrong!", "Invalid security answer", JOptionPane.ERROR_MESSAGE);
                        securityAnswerTextFieldOne.setText("");
                        securityAnswerTextFieldTwo.setText("");
                    
                        securityAnswerTextFieldTwo.requestFocusInWindow();
                        
                    }
                    
                }
                               
                else {

                    JOptionPane.showMessageDialog(null, "Security answer one is wrong!", "Invalid security answer", JOptionPane.ERROR_MESSAGE);
                    securityAnswerTextFieldOne.setText("");
                    securityAnswerTextFieldTwo.setText("");

                    securityAnswerTextFieldOne.requestFocusInWindow();

                    return;

                }

            }
        
        });
        
    }

    private void actionCancel() {
        
        cancelButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent ae) {
                
                dispose();
                LoginUI loginUI = new LoginUI();                  
                
            }         
            
        });
        
    }
    
    
}
