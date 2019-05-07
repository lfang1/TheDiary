/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thediary;
;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author lkf5083
 */
public class Diary implements Serializable{
        
    private ArrayList<Integer> dailyEntryList;
    private Database database;
    
    public Diary(int accountID) {

        //Set up a list to store daily entry IDs.
        dailyEntryList = new ArrayList<>();
        
        //Set up a database.
        database = new Database(accountID);
        
        dailyEntryList = database.readInDailyEntryList();
      
    }

    
    public void addDailyEntry(int id, String text) {
          
        dailyEntryList.add(id);
        database.writeOutDailyEntry(new DailyEntry(id, text));
        database.writeOutDailyEntryList(dailyEntryList);
        
    }
    
    public void saveDailyEntry(int id, String text) {
        
        database.writeOutDailyEntry(new DailyEntry(id, text));
        
    }
    
    public DailyEntry getDailyEntry(int id) {
       
        return database.readInDailyEntry(id);
        
    }        

/*    
    private void fillDailyEntryList() {
        
        for (int y = 1850; y <= 2150; y++) {
            
            GregorianCalendar gc = new GregorianCalendar(); 
            
            for (int m = 0; m < 12; m++) {
                
                int amountOfDays;
                
                switch (m + 1) {
                    
                    case 1: case 3: case 5:
                    case 7: case 8: case 10:
                    case 12:                        
                        amountOfDays = 31;                        
                        break;
                    
                    case 4: case 6: 
                    case 9: case 11:                        
                        amountOfDays = 30;
                        break;
                        
                    case 2:
                        if (gc.isLeapYear(y)) {
                            
                            amountOfDays = 29;
                            
                        }
                    
                        else {
                            
                            amountOfDays = 28;
                            
                        }
                        break;
                    
                    default:
                        System.err.println("Invalid month");
                        amountOfDays = 31;
                        break;
                                        
                }
                
                for (int d = 0; d < amountOfDays; d++) {
                    
                    GregorianCalendar cal = new GregorianCalendar(y, m, d);
                    int id = createDailyEntryID(cal); 
                    
                    dailyEntries.addDailyEntry(id, cal);
                    
                }
                            
            }
                     
        }              
          
    }
*/
      
}
