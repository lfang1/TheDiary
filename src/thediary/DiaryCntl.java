/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thediary;


/**
 *
 * @author lkf5083
 */
public class DiaryCntl {
    
    // Instance variables
    private Diary diary;    
    private NavigationCntl navigationCntl;
    
    //Constructor
    public DiaryCntl(NavigationCntl navigationCntl, int accountID) {
        
        //Set up diary.
        diary = new Diary(accountID);       
        
        // Supports navagating back to the Main Menu
        this.navigationCntl = navigationCntl;                 
                
    }
   
    public void addDailyEntry(int dailyEntryID, String input) {
        
        diary.addDailyEntry(dailyEntryID, input);
        
    }
    
    public DailyEntry getDailyEntry(int dailyEntryID) {
        
        return diary.getDailyEntry(dailyEntryID);
        
    }
    
    public void saveDailyEntry(int dailyEntryID, String input) {
        
        diary.saveDailyEntry(dailyEntryID, input);
     
    }
    
}
