/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thediary;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author lkf5083
 */
class Account implements Serializable{
    
    private final int accountID;
    private final String username;
    private String password;
    private final ArrayList<String[]> securityQuestionsAndAnswers;

    Account(int accountID, String username, String password, 
            ArrayList<String[]> securityQuestionsAndAnswers ) {
        
        this.accountID = accountID;
        this.username = username;
        this.password = password;
        this.securityQuestionsAndAnswers = securityQuestionsAndAnswers;
               
    }
    
    public int getAccountID() {
        
        return accountID;
        
    }
    
    public String getUsername() {
        
        return username;
        
    }
    
    public void setPassword(String newPassword) {
        
        password = newPassword;    
        
    }
    
    public String getPassword() {
        
        return password;
        
    }
    
    public ArrayList<String[]> getSecurityQuestionAndAnswers() {
        
        return securityQuestionsAndAnswers;
        
    }
  
    
    
    
    
}
