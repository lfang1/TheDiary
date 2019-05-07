/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thediary;

/**
 *
 * @author Le
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginUI extends JFrame {

    JPanel mainPanel;
    JPanel inputPanel;
    JPanel actionPanel;
    JPanel recoverPanel;
    JLabel usernameLabel;
    JLabel passwordLabel;
    JTextField usernameTextField;
    JPasswordField passwordTextField;
    JButton loginButton;
    JButton registerButton;
    JButton recoverButton;

    LoginUI(){

        super("Welcome to Simple Diary");
        initialLoginUI();
        actionlogin();
        actionRegister();
        actionRecover();

    }
    
    public void initialLoginUI() {
        
        mainPanel = new JPanel();
        inputPanel = new JPanel();
        actionPanel = new JPanel();
        recoverPanel = new JPanel();
        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");
        usernameTextField = new JTextField(15);
        passwordTextField = new JPasswordField(15);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        recoverButton = new JButton("Forget the password?");
              
        addComponentsToInputPanel();
        addComponentsToActionPanel();
        addComponentsToRecoverPanel();
        
        addComponentsToMainPanel();
        
        setContentPane(mainPanel);
        
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
                
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
        c.anchor = GridBagConstraints.PAGE_START;
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
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(40, 40, 40, 40);
        mainPanel.add(actionPanel, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 50;
        c.gridy = 1500;
        c.gridwidth = 300;
        c.gridheight = 200;
        c.weightx = 3;
        c.weighty = 2;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(recoverPanel, c);
        
    }
    
    
    public void addComponentsToInputPanel() {
        
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
        c.gridx = 40;
        c.gridy = 45;
        c.gridwidth = 15;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(passwordLabel, c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 70;
        c.gridy = 30;
        c.gridwidth = 25;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(usernameTextField, c);
                
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 70;
        c.gridy = 45;
        c.gridwidth = 25;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(passwordTextField, c);
                
    }
   
    public void addComponentsToActionPanel() {
        
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
        actionPanel.add(loginButton, c);
                
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 30;
        c.gridy = 10;
        c.gridwidth = 10;
        c.gridheight = 10;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(25, 25, 25, 25);
        actionPanel.add(registerButton, c);
             
    }
    
    public void addComponentsToRecoverPanel() {
        
        recoverPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 80;
        c.gridy = 40;
        c.gridwidth = 10;
        c.gridheight = 10;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(25, 25, 25, 25);
        recoverPanel.add(recoverButton, c);
    
    }

    public void actionlogin(){

        loginButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {

                String inputUsername = usernameTextField.getText();
                String inputPassword = passwordTextField.getText();
                               
                Database d = new Database();
                AccountList al = new AccountList(d);
                                  
                int accountID = al.verifyAccount(inputUsername, inputPassword);
                                         
                if(accountID != -1) {

                    NavigationCntl navigationCntl = new NavigationCntl();
                    DiaryCntl diaryCntl = new DiaryCntl(navigationCntl, accountID);
//                    DiaryUI diaryUI = new DiaryUI(diaryCntl);
                    NewDiaryUI diaryUI = new NewDiaryUI(diaryCntl);
                    dispose();

                } 

                else {
                    
                    usernameTextField.setText("");
                    passwordTextField.setText("");
                    usernameTextField.requestFocusInWindow();

                }

            }
        });
    }
    
    public void actionRegister() {
        
        registerButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent ae) {
                
                dispose();
                RegisterUI registerUI = new RegisterUI();
                
            }         
            
        });
        
        
    }

    private void actionRecover() {
        
        recoverButton.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent ae) {
                
                dispose();
                VerifyUsernameUI verifyUsernameUI = new VerifyUsernameUI();
                
            }
            
        });
        
    }

}
