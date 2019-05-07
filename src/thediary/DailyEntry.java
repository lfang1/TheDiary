/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thediary;


import java.io.Serializable;

/**
 *
 * @author lkf5083
 */
public class DailyEntry implements Serializable, Cloneable {
    
    private final int dailyEntryID;
    private String text;
    
    public DailyEntry(int dailyEntryID, String text) {
        
        this.dailyEntryID = dailyEntryID;
        this.text = text;
        
    }
    
    public int getDailyEntryID() {
        
        return dailyEntryID;
        
    }
   
    
    public void setText(String text) {
        
        this.text = text;
        
    }
    
    public String getText() {
        
        return text;
        
    }
/*    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        
        return super.clone();
        
    }
*/
    
}
