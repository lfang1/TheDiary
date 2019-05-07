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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Le
 */

public class VerifyUsernameUI extends JFrame {

    JLabel usernameLabel;
    JTextField usernameTextField;
    JPanel mainPanel;
    JPanel inputPanel;
    JPanel actionPanel;
    JButton verifyButton;
    JButton cancelButton;
    
    public VerifyUsernameUI() {
        
        super("Veirfy Your Username");
        initialVerifyUsernameUI();
        actionRecover();
        actionCancel();
        
    }

    private void initialVerifyUsernameUI() {
        
        usernameLabel = new JLabel("Username");
        usernameTextField = new JTextField(15);
        mainPanel = new JPanel();
        inputPanel = new JPanel();
        actionPanel = new JPanel();
        verifyButton = new JButton("Verify");
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
        inputPanel.add(usernameLabel, c);
              
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 70;
        c.gridy = 30;
        c.gridwidth = 25;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(usernameTextField, c);
        
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
        actionPanel.add(verifyButton, c);
                
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
        
        verifyButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {

                String inputUsername = usernameTextField.getText();
                               
                Database d = new Database();
                AccountList al = new AccountList(d);
                                  
                int accountID = al.checkIfExist(inputUsername);
                                         
                if(accountID != -1) {

                    Account recoverAccount = al.getAccount(accountID);
                    RecoverUI recoverUI = new RecoverUI(recoverAccount);
                    dispose();

                } 

                else {
                    
                    usernameTextField.setText("");
                    usernameTextField.requestFocusInWindow();

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

