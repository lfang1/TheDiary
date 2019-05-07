/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thediary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 *
 * @author lkf5083
 */
class DailyEntryList implements Serializable {

    private ArrayList<Integer> dailyEntryIDs;
    private Database database;
    
    public DailyEntryList(Database d) {
        
        dailyEntryIDs = new ArrayList<>(301);
        database = d;
        dailyEntryIDs = d.readInDailyEntryList();
        
    }
    
    public void addDailyEntry(int dailyEntryID, String text) {
 
        DailyEntry theDailyEntry = new DailyEntry(dailyEntryID, text);
        dailyEntryIDs.add(dailyEntryID);
        database.writeOutDailyEntry(theDailyEntry);
        database.writeOutDailyEntryList(dailyEntryIDs);
     
    }        
    
    
    public DailyEntry getDailyEntry(int dailyEntryID) {
       
        return database.readInDailyEntry(dailyEntryID);
        
    }

    
    public ArrayList<Integer> search(String keyword) {
        
        ArrayList<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < dailyEntryIDs.size(); i++) {
            
            String currentText = database.readInDailyEntry(i).getText();
            
            if (currentText == null || currentText.isEmpty()) {
                
                continue;
                
            }
            
            if (currentText.contains(keyword)) {
                
                result.add(i);
                
            }
            
        }
        
        return result;
        
    }
    
}
