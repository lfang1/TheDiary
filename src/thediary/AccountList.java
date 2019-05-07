/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thediary;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author lkf5083
 */
public class AccountList implements Serializable{
    
    private Database database;
    private ArrayList<Integer> accountIDs;
    private int lastAssginedID;
    
    public AccountList(Database d) {

        accountIDs = new ArrayList<>(10);
        database = d;
        accountIDs = database.readInAccountList();
        lastAssginedID = accountIDs.get(accountIDs.size() -1 );
             
    }
    
    public void addAccount(String username, String password,
                           ArrayList<String[]> securityQuestionsAndAnswers) {
 
        Account newAccount = new Account(lastAssginedID, username, password, 
                                         securityQuestionsAndAnswers);
        accountIDs.add(lastAssginedID + 1);
        database.writeOutAccount(newAccount);
        database.writeOutAccountList(accountIDs);
             
    }        
    
    
    public Account getAccount(int accountID) {
       
        return database.readInAccount(accountID);
        
    }

//FIXME: find a better way to delete account. (Need delete all associated dairy for this account)    
    public void deleteAccount(int accountID) {
        
        Account accountToDelete = new Account(accountID, null, null, null);
        database.writeOutAccount(accountToDelete);
        
    }

    public boolean checkIfUnique(String username) {
        
        boolean isUnique = false;
        
        if (username.isEmpty() || username == null) {
            
            isUnique = false;
            JOptionPane.showMessageDialog(null,"Username cannot be empty or null!", "Invalid username", JOptionPane.ERROR_MESSAGE);
            
            return isUnique;
            
        }
        
        for (int id : accountIDs) {
                        
            Account a = database.readInAccount(id);
            
            if ( a == null ) {
                
                continue;
                
            }
            
            if ( username.equals(a.getUsername()) ) {
                
                isUnique = false;
                JOptionPane.showMessageDialog(null,"This username was already used!", "Invalid username", JOptionPane.ERROR_MESSAGE);
                
                return isUnique;
                
            }

        } 
        
        isUnique = true;
        
        return isUnique;
        
    }  
    
    public int checkIfExist(String username) {
        
        int accountID = -1;
        
        if (username.isEmpty() || username == null) {
            
            accountID = -1;
            JOptionPane.showMessageDialog(null,"Username cannot be empty or null!", "Invalid username", JOptionPane.ERROR_MESSAGE);
            
            return accountID;
            
        }
        
        for (int id : accountIDs) {
                        
            Account a = database.readInAccount(id);
            
            if ( a == null ) {
                
                continue;
                
            }
            
            if ( username.equals(a.getUsername()) ) {
                
                accountID = id;
                                
                return accountID;
                
            }

        } 
        
        accountID = -1;
        JOptionPane.showMessageDialog(null,"This username was not found!", "Invalid username", JOptionPane.ERROR_MESSAGE);
        
        return accountID;
        
    }  
    
    
    public int verifyAccount(String username, String password) {
        
        if (username.isEmpty() || username == null || password.isEmpty() || password == null) {
                        
            JOptionPane.showMessageDialog(null, "Username and password can not be empty or null!", "Invalid input", JOptionPane.ERROR_MESSAGE);
            
            return -1;
                
        }              
                
        for (int id : accountIDs) {
                        
            Account a = database.readInAccount(id);
            
            if ( a == null ) {
                
                continue;
                
            }
                        
            if ( !username.equals(a.getUsername()) ) {
                
                continue;
                
            } 
            
            else {
                                             
                if ( password.equals(a.getPassword())) {
                    
                    return id;

                }
                
                else {

                    JOptionPane.showMessageDialog(null, "The password does not match the username!", "Wrong password", JOptionPane.ERROR_MESSAGE);
                    
                    return -1;
                    
                }

            }              
                        
        }
        
        JOptionPane.showMessageDialog(null, "Username not found!", "Wrong username", JOptionPane.ERROR_MESSAGE);
        
        return -1;
        
    }
    
    public boolean verifySecurityAnswers(String securityAnswerOne, String securityAnswerTwo) {
        
        return false;
        
    }
    
    
}
