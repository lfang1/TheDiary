/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thediary;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author lkf5083
 */
public class NewDiaryUI extends JFrame {
    
    private DiaryCntl diaryCntl;
    private GregorianCalendar selectedDate;
//    private DailyEntry selectedDailyEntry;
    private int selectedDailyEntryID;
    private BackgroundPane background;
    private PagePanel pagePanel;
    private JPanel emptyPanel;
    private CalendarPanel calendarPanel;
    private FunctionPanel functionPanel;
    
    public NewDiaryUI(DiaryCntl theDiaryCntl) {
        
        super("Simple Diary");
        diaryCntl = theDiaryCntl;
        updateSelectedDailyEntryInfo(new GregorianCalendar());
        pagePanel = new PagePanel(this);
        emptyPanel = new JPanel();
        calendarPanel = new CalendarPanel(this);
        functionPanel = new FunctionPanel(this);
       
        initializeDiaryUI();
        
        this.pack();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }
    
    public boolean checkIfNeedToUpdate(GregorianCalendar newSelectedDate) {
        
        int newPageID = calToID(newSelectedDate);
        
        if (selectedDailyEntryID == newPageID) {
            
            return false;            
            
        }
        
        else {
            
            return true;
            
        }
        
        
        
    }
    
    public DailyEntry getDailyEntry(GregorianCalendar cal) {
        
        int dailyEntryID = calToID(cal);
        
        if (diaryCntl.getDailyEntry(dailyEntryID) == null)
            
        {
            
            diaryCntl.addDailyEntry(dailyEntryID, calToString(cal));
            
        }      
        
        return diaryCntl.getDailyEntry(dailyEntryID);
        
    }
    
    public void updateSelectedDailyEntryInfo(GregorianCalendar cal) {
       
        selectedDate = cal;
        selectedDailyEntryID = calToID(cal);      
//        selectedDailyEntry = getDailyEntry(cal);
        
    }
    
    public String calToString(GregorianCalendar cal) {
        
        String year = Integer.toString(cal.get(cal.YEAR));
        String month = cal.getDisplayName(cal.MONTH, cal.SHORT, Locale.getDefault());
        String day = Integer.toString(cal.get(cal.DAY_OF_MONTH));
        StringBuilder sb = new StringBuilder(month + " ");
        
        
        if (day.length() < 2) {
            
            sb.insert(sb.length(), "0" + day + " ");
            
        }
        
        else {
            
            sb.insert(sb.length(), day + " ");
            
        }
        
        sb.insert(sb.length(), year);
        
        return sb.toString();
        
    }   
    
    public int calToID(GregorianCalendar selectedDate) {
        
        String year = Integer.toString(selectedDate.get(selectedDate.YEAR));
        String month = Integer.toString(selectedDate.get(selectedDate.MONTH) + 1);
        String day = Integer.toString(selectedDate.get(selectedDate.DAY_OF_MONTH));
        StringBuilder sb = new StringBuilder(year);
        
        if (month.length() < 2) {
            
            sb.insert(4, "0" + month);
            
        }
        
        else {
            
            sb.insert(4, month);
            
        }
        
        if (day.length() < 2) {
            
            sb.insert(6, "0" + day);
            
        }
        
        else {
            
            sb.insert(6, day);
            
        }
        
        return Integer.parseInt(sb.toString());
        
    }

    private void initializeDiaryUI() {
        
        background = new BackgroundPane();            
        
        try {
        
            background.setBackground(ImageIO.read(new File("picture/wooden-desk1.jpg")));
            
        }
        
        catch (IOException exp) {
            
            exp.printStackTrace();
        }
        
        setContentPane(background);
        
        addComponentsToBackground();
        
        
        
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }

    private void addComponentsToBackground() {
        
        background.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
                
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 30;
        c.gridy = 3;
        c.gridwidth = 4;
        c.gridheight = 1;
        c.weightx = 0.01;
        c.weighty = 0.01;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.insets = new Insets(1, 1, 1, 1);
        background.add(calendarPanel, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 5;
        c.gridy = 5;
        c.gridwidth = 16;
        c.gridheight = 12;
        c.weightx = 0.48;
        c.weighty = 0.48;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(3, 3, 3, 3);
        background.add(pagePanel, c);
        
        //FIXME : add empty panel to limit the width of the pagepanel or change the location parameter for calendarpanel
        
        c = new GridBagConstraints();    
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 5;
        c.gridy = 35;
        c.gridwidth = 70;
        c.gridheight = 2;
        c.weightx = 0.35;
        c.weighty = 0.35;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.insets = new Insets(1, 1, 1, 1);
        background.add(functionPanel, c);
        
        c = new GridBagConstraints(); 
             
    }

    public String getTextOnThePage() {
        
        return pagePanel.getText();
        
    }
    
    public void setTextOnThePage(String text) {
        
        pagePanel.setText(text);
        
    }
   
    public void saveText() {
        
        diaryCntl.saveDailyEntry(selectedDailyEntryID, getTextOnThePage());
        
    }    
    
    public void clearText() {
        
        setTextOnThePage(calToString(selectedDate));
        saveText();
        
    }  
    
    public void turnPage(int number) {
        
        selectedDate.add(selectedDate.DAY_OF_MONTH, number);
        updateSelectedDailyEntryInfo(selectedDate);
        calendarPanel.updateTextOnThePage(selectedDate);
        
    }
    
}
