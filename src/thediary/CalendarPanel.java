/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thediary;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author lkf5083
 */
public class CalendarPanel extends JPanel {
    
    private GregorianCalendar selectedDate;
    private NewDiaryUI diaryUI;
    private Hashtable month_Day;
    private JComboBox selectYear;
    private JComboBox selectMonth;
    private JComboBox selectDate;
    
    public CalendarPanel(NewDiaryUI diaryUI) {
        
        selectedDate = new GregorianCalendar();
        this.diaryUI = diaryUI;
        initializeCalendarPanel();
        addComponentsToCalendarPanel();
        //initialize text for inputArea
        updateTextOnThePage(selectedDate);
        
    }

    private void initializeCalendarPanel() {
            
        selectYear = new JComboBox();
        Vector years = new Vector(302);
        month_Day = new Hashtable();
        
        years.add("Select Year");
        
        for (int y = 1850; y <= 2150; y++) {
           
            years.add(y);
            
        }
        
        selectYear.setModel(new DefaultComboBoxModel(years));
        selectYear.setSelectedItem( selectedDate.get(selectedDate.YEAR) );
        selectYear.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                dateSelectionActionPerformed(event);
                dailyEntrySelectionOnCalendarActionPerformed(event);

            }

        });
        
        // Prevent actions events from being fired when the up/down arrow keys are used
        //selectYear.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
               
        selectMonth = new JComboBox();
        String[] months = {"Select Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        selectMonth.setModel(new DefaultComboBoxModel(months));
        selectMonth.setSelectedItem( selectedDate.getDisplayName(selectedDate.MONTH, selectedDate.LONG, Locale.getDefault()) );
        selectMonth.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                dateSelectionActionPerformed(event);
                dailyEntrySelectionOnCalendarActionPerformed(event);

            }

        });
        
        // Prevent actions events from being fired when the up/down arrow keys are used
        selectMonth.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        
        // Create sub combo box with multiple models
        
        String[] longMonths = {"January", "March", "May", "July", "August", "October", "December"};
        
        String[] shortMonths = {"April", "June", "September", "November"};
        
        String[] thirtyOneDays = {"Select Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", 
            "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        
        String[] thirtyDays = {"Select Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", 
            "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
        
        String[] twentyNineDays = {"Select Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", 
            "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29"};
        
        String[] twentyEightDays = {"Select Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", 
            "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"};
        
        
        
        selectDate = new JComboBox();
        
//FIXME: find a way to get rid of extra days in the beginning;

        int selectedMonth = (int) selectMonth.getSelectedIndex();
        
        switch (selectedMonth) {
            
            case 1: case 3: case 5:
            case 7: case 8: case 10:
            case 12:
                
                selectDate.setModel(new DefaultComboBoxModel(thirtyOneDays));   
                break;
            
            case 4: case 6:
            case 9: case 11:
                
                selectDate.setModel(new DefaultComboBoxModel(thirtyDays));   
                break;    
                
            case 2:
                
                if (selectedDate.isLeapYear((int) selectYear.getSelectedItem())) {
                    
                    selectDate.setModel(new DefaultComboBoxModel(twentyNineDays));
                    
                }
            
                else {
                    
                    selectDate.setModel(new DefaultComboBoxModel(twentyEightDays));
                    
                }
                break;
            
            default:
                selectDate.setModel(new DefaultComboBoxModel(thirtyOneDays));
                break;
                
        }
                                   
        selectDate.setSelectedItem( Integer.toString(selectedDate.get(selectedDate.DATE)) );
        selectDate.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                dailyEntrySelectionOnCalendarActionPerformed(event);

            }

        });
        
        selectDate.setPrototypeDisplayValue("XXXXXXXXX"); // JDK 1.4
        
        for (int i = 0; i < longMonths.length; i++) {
            
            month_Day.put(longMonths[i], thirtyOneDays);
                      
        }
        
        for (int i = 0; i < shortMonths.length; i++) {
            
            month_Day.put(shortMonths[i], thirtyDays);
            
        }      
        
    }
    
    public void dailyEntrySelectionOnCalendarActionPerformed(ActionEvent event) {
        
        int y = selectYear.getSelectedIndex();
        int m = selectMonth.getSelectedIndex();
        int d = selectDate.getSelectedIndex();
        
        if (y == 0 || m == 0 || d == 0) {
            
            return;
            
        }
        
        int year = (int) selectYear.getSelectedItem();
        int month = m - 1;
        int date = d;
    
        GregorianCalendar newSelectedDate = new GregorianCalendar(year, month, date);        
        
        if (diaryUI.checkIfNeedToUpdate(newSelectedDate)) {
            
          
            final JOptionPane dailyEntryOptionPane = new JOptionPane(
                    "Would you like to save the current text?\n",
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.YES_NO_OPTION);
        
        //FIXME: ask what does this passage mean?        
                //You can't use pane.createDialog() because that
                //method sets up the JDialog with a property change
                //listener that automatically closes the window
                //when a button is clicked.               
            final JDialog dialog = new JDialog(diaryUI, 
                                        "Confirm your action",
                                        true);

            dialog.setContentPane(dailyEntryOptionPane);
            dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            dailyEntryOptionPane.addPropertyChangeListener(

    //FIXME: ask what is lambda experssion.                

                new PropertyChangeListener() {

                    public void propertyChange(PropertyChangeEvent e) {

                        String prop = e.getPropertyName();

                        if (dialog.isVisible()
                        && (e.getSource() == dailyEntryOptionPane)
                        && (JOptionPane.VALUE_PROPERTY.equals(prop))) {

                            dialog.setVisible(false);

                        }                  
                    }               
                } );

            dialog.pack();
            dialog.setLocationRelativeTo(diaryUI);
            dialog.setVisible(true);

            int value = ((Integer) dailyEntryOptionPane.getValue()).intValue();
            if(value == JOptionPane.YES_OPTION) {

                diaryUI.saveText();

            }                           
            
            selectedDate = newSelectedDate;
            diaryUI.updateSelectedDailyEntryInfo(selectedDate);       
            updateTextOnThePage(selectedDate);       
            
        }
       
     
    }
    
    public void updateTextOnThePage(GregorianCalendar cal) {
        
        selectedDate = cal;
        int year = selectedDate.get(selectedDate.YEAR);
        int month = selectedDate.get(selectedDate.MONTH) + 1;
        int date = selectedDate.get(selectedDate.DAY_OF_MONTH);
        
        selectYear.setSelectedItem((Object)year);
        selectMonth.setSelectedIndex(month);
        selectDate.setSelectedIndex(date);
      
        String text = diaryUI.getDailyEntry(selectedDate).getText();
        
        if (text.isEmpty()) {
            
            text = diaryUI.calToString(selectedDate);
            
        }

        diaryUI.setTextOnThePage(text); 
        
    }
    
    public void dateSelectionActionPerformed(ActionEvent event) {
        
        Object year = selectYear.getSelectedItem();
        String month = (String) selectMonth.getSelectedItem();
        
        if (selectYear.getSelectedIndex() == 0 || selectMonth.getSelectedIndex() == 0) {
                
                selectDate.setModel( new DefaultComboBoxModel(new String[] {"Select Date"}) );
                
                return;
                
            }
        
        if ( "February".equals(month)) {
            
            GregorianCalendar gCal = new GregorianCalendar();
            String[] twentyNineDays = {"Select Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", 
            "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29"};
        
            String[] twentyEightDays = {"Select Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", 
            "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"};
                        
            if (gCal.isLeapYear((int) year)) {
            
                month_Day.put("February", twentyNineDays);
            
            }
        
            else {
            
                month_Day.put("February", twentyEightDays);
            
            }
            
        }
        
        Object o = month_Day.get( month );
 
        if (o == null) {
            
            return;
            
        }
        
        else {
            
            selectDate.setModel( new DefaultComboBoxModel( (String[])o ) );
            
        } 
        
    }  

    private void addComponentsToCalendarPanel() {
        
        setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 45;
        c.gridy = 5;
        c.gridwidth = 8;
        c.gridheight = 2;
        c.weightx = 0.1;
        c.weighty = 0.1;
        add(selectMonth, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 55;
        c.gridy = 5;
        c.gridwidth = 8;
        c.gridheight = 2;
        c.weightx = 0.1;
        c.weighty = 0.1;
        add(selectDate, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 65;
        c.gridy = 5;
        c.gridwidth = 8;
        c.gridheight = 2;
        c.weightx = 0.1;
        c.weighty = 0.1;
        add(selectYear, c);
        
        
    }
    
    
    
    
}
