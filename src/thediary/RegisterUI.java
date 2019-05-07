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
import java.util.ArrayList;

public class RegisterUI extends JFrame {

    JPanel mainPanel;
    JPanel inputPanel;
    JPanel actionPanel;
    
    JLabel usernameLabel;
    JLabel passwordLabel;
    JTextField usernameTextField;
    JPasswordField passwordTextField;
    JLabel repeatPasswordLabel;
    JPasswordField repeatPasswordTextField;
    
    
    JLabel securityQuestionLabelOne;
    JComboBox securityQuestionComboBoxOne;
    JLabel securityAnswerLabelOne;
    JTextField securityAnswerTextFieldOne;
    
    JLabel securityQuestionLabelTwo;
    JComboBox securityQuestionComboBoxTwo;
    JLabel securityAnswerLabelTwo;
    JTextField securityAnswerTextFieldTwo;
    
    JButton confirmButton;
    JButton cancelButton;

    RegisterUI(){

        super("Register Your User Account");
        initialRegisterUI();
        actionConfirm();
        actionCancel();

    }
    
    public void initialRegisterUI() {
        
        mainPanel = new JPanel();
        inputPanel = new JPanel();
        actionPanel = new JPanel();
        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");
        usernameTextField = new JTextField(15);
        passwordTextField = new JPasswordField(15);
        repeatPasswordLabel = new JLabel("Repeat Password");
        repeatPasswordTextField = new JPasswordField(15);;
        
        securityQuestionLabelOne = new JLabel("Securty Question 1");
        securityQuestionComboBoxOne = new JComboBox();
        String[] questionGroupOne = {"Select your security question one", "Which year is your mother's birthday?", "Which year did you enter your first high school?", "Which year did you graduate from your middle school?"};
        securityQuestionComboBoxOne.setModel(new DefaultComboBoxModel(questionGroupOne));
        securityQuestionComboBoxOne.setSelectedItem(0);
        securityAnswerLabelOne = new JLabel("Security Answer 1");
        securityAnswerTextFieldOne = new JTextField(15);
        
        securityQuestionLabelTwo = new JLabel("Securty Question 2");
        securityQuestionComboBoxTwo = new JComboBox();
        String[] questionGroupTwo = {"Select your security question two", "Who is your best friend in your high school?", "Who is your favoriate teacher in your middle school?", "What is the name of the street that you group up with?"};
        securityQuestionComboBoxTwo.setModel(new DefaultComboBoxModel(questionGroupTwo));
        securityQuestionComboBoxTwo.setSelectedItem(0);
        securityAnswerLabelTwo = new JLabel("Security Answer 2");
        securityAnswerTextFieldTwo = new JTextField(15);       
        
        confirmButton = new JButton("Confirm");
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
        c.gridx = 70;
        c.gridy = 30;
        c.gridwidth = 25;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(usernameTextField, c);
        
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
        c.gridy = 45;
        c.gridwidth = 25;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(passwordTextField, c);
        
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 40;
        c.gridy = 60;
        c.gridwidth = 15;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(repeatPasswordLabel, c);
                        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 70;
        c.gridy = 60;
        c.gridwidth = 25;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(repeatPasswordTextField, c);
  
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 40;
        c.gridy = 75;
        c.gridwidth = 15;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(securityQuestionLabelOne, c);
                        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 70;
        c.gridy = 75;
        c.gridwidth = 25;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(securityQuestionComboBoxOne, c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 40;
        c.gridy = 90;
        c.gridwidth = 15;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(securityAnswerLabelOne, c);
                        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 70;
        c.gridy = 90;
        c.gridwidth = 25;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(securityAnswerTextFieldOne, c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 40;
        c.gridy = 105;
        c.gridwidth = 15;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(securityQuestionLabelTwo, c);
                        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 70;
        c.gridy = 105;
        c.gridwidth = 25;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(securityQuestionComboBoxTwo, c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 40;
        c.gridy = 120;
        c.gridwidth = 15;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(securityAnswerLabelTwo, c);
                        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 70;
        c.gridy = 120;
        c.gridwidth = 25;
        c.gridheight = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        inputPanel.add(securityAnswerTextFieldTwo, c);   
                
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
        actionPanel.add(confirmButton, c);
                
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

    public void actionConfirm(){

        confirmButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {

                String inputUsername = usernameTextField.getText();
                String inputPassword = passwordTextField.getText();
                String repeatPassword = repeatPasswordTextField.getText();
                String inputSecurityQuestionOne = (String) securityQuestionComboBoxOne.getSelectedItem();
                String inputSecurityAnswerOne = securityAnswerTextFieldOne.getText();
                String[] inputSecurityQuestionAndAnswerOne = {inputSecurityQuestionOne, inputSecurityAnswerOne};
                
                String inputSecurityQuestionTwo = (String) securityQuestionComboBoxTwo.getSelectedItem();
                String inputSecurityAnswerTwo = securityAnswerTextFieldTwo.getText();
                String[] inputSecurityQuestionAndAnswerTwo = {inputSecurityQuestionTwo, inputSecurityAnswerTwo};
                ArrayList<String[]> inputSecurityQuestionsAndAnswers = new ArrayList<String[]>();
                inputSecurityQuestionsAndAnswers.add(inputSecurityQuestionAndAnswerOne);
                inputSecurityQuestionsAndAnswers.add(inputSecurityQuestionAndAnswerTwo);               
                
                if (securityQuestionComboBoxOne.getSelectedIndex() == 0 || securityQuestionComboBoxTwo.getSelectedIndex() == 0 
                        || inputSecurityAnswerOne.isEmpty() || inputSecurityAnswerTwo.isEmpty()) {
                    
                    JOptionPane.showMessageDialog(null, "All security questions must be chosen and security answers can not be empty!", "Unselected security question", JOptionPane.ERROR_MESSAGE);
                    usernameTextField.setText(inputUsername);
                    passwordTextField.setText("");
                    repeatPasswordTextField.setText("");
                    securityQuestionComboBoxOne.setSelectedIndex(0);
                    securityAnswerTextFieldOne.setText("");
                    securityQuestionComboBoxTwo.setSelectedIndex(0);
                    securityAnswerTextFieldTwo.setText("");
                    
                    usernameTextField.requestFocusInWindow();
                    
                    return;
                    
                }
                
                               
                Database d = new Database();
                AccountList al = new AccountList(d);
                
                
                boolean isUnique = al.checkIfUnique(inputUsername);
                
                if (isUnique) {
                    
                    if (inputPassword.isEmpty() || inputPassword == null
                          || repeatPassword.isEmpty() || repeatPassword == null) {
                        
                        JOptionPane.showMessageDialog(null,"Password cannot be empty or null!", "Invalid password", JOptionPane.ERROR_MESSAGE);
                        usernameTextField.setText(inputUsername);
                        passwordTextField.setText("");
                        repeatPasswordTextField.setText("");
                        securityQuestionComboBoxOne.setSelectedItem(inputSecurityQuestionOne);
                        securityAnswerTextFieldOne.setText(inputSecurityAnswerOne);
                        securityQuestionComboBoxTwo.setSelectedItem(inputSecurityQuestionTwo);
                        securityAnswerTextFieldTwo.setText(inputSecurityAnswerTwo);                       
                        
                        passwordTextField.requestFocusInWindow();
                        
                        return;
                    }
                    
                    else if (!inputPassword.equals(repeatPassword)) {
                        
                        JOptionPane.showMessageDialog(null,"Password and repeat password should be the same!", "Different password", JOptionPane.ERROR_MESSAGE);
                        usernameTextField.setText(inputUsername);
                        passwordTextField.setText("");
                        repeatPasswordTextField.setText("");
                        securityQuestionComboBoxOne.setSelectedItem(inputSecurityQuestionOne);
                        securityAnswerTextFieldOne.setText(inputSecurityAnswerOne);
                        securityQuestionComboBoxTwo.setSelectedItem(inputSecurityQuestionTwo);
                        securityAnswerTextFieldTwo.setText(inputSecurityAnswerTwo);                       
            
                        passwordTextField.requestFocusInWindow();
                        
                    }
                    
                    else {
                        
                        al.addAccount(inputUsername, inputPassword, inputSecurityQuestionsAndAnswers);
                        JOptionPane.showMessageDialog(null,"Your account has been created successfully!");
                        LoginUI loginUI = new LoginUI();
                        dispose();
                        
                        return;
                        
                    }

                } 

                else {
                               
                    usernameTextField.setText("");
                    passwordTextField.setText("");
                    repeatPasswordTextField.setText("");
                    securityQuestionComboBoxOne.setSelectedIndex(0);
                    securityAnswerTextFieldOne.setText("");
                    securityQuestionComboBoxTwo.setSelectedIndex(0);
                    securityAnswerTextFieldTwo.setText("");

                    usernameTextField.requestFocusInWindow();

                    return;

                }

            }
            
        });
        
    }
    
    public void actionCancel() {
        
        cancelButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent ae) {
                
                dispose();
                LoginUI loginUI = new LoginUI();                  
                
            }         
            
        });
                
    }

}
