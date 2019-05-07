/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thediary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


/**
 *
 * @author lkf5083
 */
public class Database implements Serializable {
    
    private int accountID;
    
    public Database(int accountID) {
        
        this.accountID = accountID;
        
    } 
    
    public Database() {
        
        accountID = 1000;
        
    }
        
    public void writeOutDailyEntryList(ArrayList<Integer> del) {
        
        String dirPath = "data/accounts/id" + accountID + "/dailyEntryList";
        String filePath = "data/accounts/id" + accountID + "/dailyEntryList" + "/DailyEntryList.ser";
        
        File checkDir = new File(dirPath);
        File checkFile = new File(filePath);
        
        if (!checkDir.exists()) {
            
            checkDir.mkdirs();
            
        }
        
        else {
            
//FIXME: Ask if delete orignal file before update it.
            
            if(checkFile.exists()) {
                
                checkFile.delete();
                                
            }
            
        }
        
        try {
            
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(del);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is aved in " + filePath);
            
        }
        
        catch (IOException i) {
            
            i.printStackTrace();
            
        } 
        
    }
    
    public ArrayList<Integer> readInDailyEntryList() {
        
        String filePath = "data/accounts/id" + accountID + "/dailyEntryList" + "/DailyEntryList.ser";
        //Check if a diary entry list already exist
        File check = new File(filePath);
        boolean exists = check.exists();
        
        if (!exists) {
            
            return new ArrayList<>();
            
        }
        
        ArrayList<Integer> newList = null;
        
        try {
            
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            newList = (ArrayList<Integer>) in.readObject();
            in.close();
            fileIn.close();;
            
        }
        
        catch (IOException i) {
            
            i.printStackTrace();
            
        }
        
        catch (ClassNotFoundException c) {
            
            System.out.println("DiaryEntryList class not found");
            c.printStackTrace();
            
        }
        
        return newList;
        
    }
     
    public void writeOutDailyEntry(DailyEntry de) {
 
        String dirPath = "data/accounts/id" + accountID + "/dairyEntries";
        String filePath = "data/accounts/id" + accountID + "/dairyEntries/" + de.getDailyEntryID() + ".ser";
        
        File checkDir = new File(dirPath);
        File checkFile = new File(filePath); 
                
        if (!checkDir.exists()) {
            
            checkDir.mkdirs();
            
        }

//FIXME: do I need to delete it before update?
        else {
            
            if (checkFile.exists()) {
                
                checkFile.delete();
                
            }
            
        }
        
        try {
            
            FileOutputStream fileOut = new FileOutputStream(filePath);            
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(de);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in " + filePath);                     
            
        }
        
        catch (IOException i) {
            
            i.printStackTrace();
            
        }
        
    }    
    
    public DailyEntry readInDailyEntry(int dailyEntryID) {

//FIXME: use accountID to name the replace "accountOne"       
        String filePath = "data/accounts/id" + accountID + "/dairyEntries/" + dailyEntryID + ".ser";
        //Check if a diary already exist
        File check = new File(filePath);
        boolean exists = check.exists();
        
        if (!exists) {
            
            return null;
            
        }
        
        DailyEntry newDiary = null;
        
        try {
            
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            newDiary = (DailyEntry) in.readObject();
            in.close();
            fileIn.close();;
            
        }
        
        catch (IOException i) {
            
            i.printStackTrace();
            
        }
        
        catch (ClassNotFoundException c) {
            
            System.out.println("DiaryEntry class not found");
            c.printStackTrace();
            
        }
        
        return newDiary;
        
    } 
        
    public void writeOutAccountList(ArrayList<Integer> al) {
        
        String dirPath = "data/accountList";
        String filePath = "data/accountList/AccountList.ser";
        
        File checkDir = new File(dirPath);
        File checkFile = new File(filePath);
        
        if (!checkDir.exists()) {
            
            checkDir.mkdirs();
            
        }

//FIXME: Do I need to delete it before update it?
        else {
            
            if (checkFile.exists()) {
                
                checkFile.delete();
                
            }            
            
        }
        
        try {
            
            FileOutputStream fileOut = new FileOutputStream(filePath);            
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(al);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in " + filePath);                     
            
        }
        
        catch (IOException i) {
            
            i.printStackTrace();
            
        }
        
    }
    
    public ArrayList<Integer> readInAccountList() {
        
        String filePath = "data/accountList/AccountList.ser";
        //Check if an AccountList already exists
        File check = new File(filePath);
        boolean exists = check.exists();
        
        if (!exists) {
            
            ArrayList<Integer> al = new ArrayList<>();
            al.add(1000);
            
            return al;
            
        }
        
        ArrayList<Integer> newList = null;
        
        try {
            
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            newList = (ArrayList<Integer>) in.readObject();
            in.close();
            fileIn.close();;
            
        }
        
        catch (IOException i) {
            
            i.printStackTrace();
            
        }
        
        catch (ClassNotFoundException c) {
            
            System.out.println("AccountList class not found");
            c.printStackTrace();
            
        }
        
        return newList;
        
    }
    
    public void writeOutAccount(Account a) {
 
        String dirPath = "data/accounts";
        String filePath = "data/accounts/id" + a.getAccountID()+ ".ser";
        
        File checkDir = new File(dirPath);
        File checkFile = new File(filePath);
       
        if (!checkDir.exists()) {
            
            checkDir.mkdirs();
            
        }
        
//FIXME: Do I need delete it before update?        
        else {
            
            if (checkFile.exists()) {
                
                checkFile.delete();
                
            }
            
        }
        
        try {
            
            FileOutputStream fileOut = new FileOutputStream(filePath);            
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(a);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in " + filePath);                     
            
        }
        
        catch (IOException i) {
            
            i.printStackTrace();
            
        }
        
    }
    
    public Account readInAccount(int accountID) {
       
        String filePath = "data/accounts/id" + accountID + ".ser";
        //Check if an account already exist
        File check = new File(filePath);
        boolean exists = check.exists();
        
        if (!exists) {
            
            return null;
            
        }
        
        Account newAccount = null;
        
        try {
            
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            newAccount = (Account) in.readObject();
            in.close();
            fileIn.close();;
            
        }
        
        catch (IOException i) {
            
            i.printStackTrace();
            
        }
        
        catch (ClassNotFoundException c) {
            
            System.out.println("Account class not found");
            c.printStackTrace();
            
        }
        
        return newAccount;
        
    }
    
    
}