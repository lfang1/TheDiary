/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thediary;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author lkf5083
 */
public class DiaryUI extends JFrame implements ActionListener {
    
    // Instance Variables
    private DiaryCntl diaryCntl;
    private DailyEntry selectedDailyEntry;
    private int selectedDailyEntryID;
    private GregorianCalendar selectedDate;   
    
    private JFrame frame;
    private Container dateSelectionPane;
    private Container diaryMenuPane;
    private Container pane;
        
    // Two page of an opened diary
    private JTextArea inputArea;
    private JScrollPane pagePane;
    
    private JLabel backgroundPicture;
    
    // One button on both sides of the diary to go forward or backward;
    private JButton turnLeftInDiary;
    private JButton turnRightInDiary;
       
    // Located on top right of DiaryUI. Select the date information and jump to a corresponding DailyEntry
    private Hashtable month_Day;
    private JComboBox selectYear;
    private JComboBox selectMonth;
    private JComboBox selectDate;
    
    // Located on the bottom left of DiaryUI
    private JButton save;
    private JButton clear;
    private JButton turnLeft;
    private JButton turnRight;
    
    // Located on the bottom right of DiaryUI
    private JButton unmute;
    private JButton mute;
    private JButton music;
    private JButton exit;    
    
    public DiaryUI(DiaryCntl theDiaryCntl) {
        
        super("DiaryUI");
        diaryCntl = theDiaryCntl;
        selectedDate = new GregorianCalendar();
        updateSelectedDailyEntryInfo(selectedDate);
        initializeDiaryUI();

    }
    
    public void initializeDiaryUI() {
        
//        turnLeftInDiary = new JButton("<");
//        turnRightInDiary = new JButton(">");       
            
        unmute = new JButton("Unmute");
        mute = new JButton("Mute");
        music = new JButton("Music");
        exit = new JButton("Exit");
        
        //Create and set up the window.
//        frame = new JFrame("DiaryUI");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        //Use a label to set up the background picture;
        backgroundPicture = new JLabel();
        try {
            
            backgroundPicture.setIcon(new ImageIcon(ImageIO.read(new File("picture/wooden-desk1.jpg"))));
            
        } 
        
        catch (IOException ex) {
            
            ex.printStackTrace();
            
        }
        
//        frame.setContentPane(backgroundPicture);
        this.setContentPane(backgroundPicture);
  
//FIXME: figure out whether use JFrame or JPanel;        
/*        
        //Create the date selection pane.
        dateSelectionPane = new Container();

        
        //Set up and date slection pane.
        addComponentsToDateSelectionPane(frame.getContentPane());
        */

        
        //Create and set up the content pane.
        pane = new Container();
        
        
        //
        
        //Initialize the page.
        initializePageComponents();
        
        //Initialize the calendar.
        initializeCalendarComponents();
        
        //Initialize the function menu.
        initializeFunctionMenuComponents();
            
        
        //Set up the content pane;
//        addComponentsToPane(frame.getContentPane());
        this.addComponentsToPane(backgroundPicture);
        
        //Display the window.
//        frame.pack();
//        frame.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        
    }
    
     public void updateSelectedDailyEntryInfo(GregorianCalendar cal) {
       
        selectedDailyEntryID = calToID(cal);
        
        if (diaryCntl.getDailyEntry(selectedDailyEntryID) == null) {
            
            diaryCntl.addDailyEntry(selectedDailyEntryID, calToString(cal));
            
        }
        
        selectedDailyEntry = diaryCntl.getDailyEntry(selectedDailyEntryID);

        
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
       
    
    public void initializeFunctionMenuComponents() {
        
    
        save = new JButton("Save");
        save.addActionListener( new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                
                final JOptionPane dailyEntryOptionPane = new JOptionPane(
                        "Would you like to save the current text?\n",
                        JOptionPane.QUESTION_MESSAGE,
                        JOptionPane.YES_NO_OPTION);
        
        //FIXME: ask what does this passage mean?        
                //You can't use pane.createDialog() because that
                //method sets up the JDialog with a property change
                //listener that automatically closes the window
                //when a button is clicked.               
                final JDialog dialog = new JDialog(frame, 
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
                dialog.setLocationRelativeTo(frame);
                dialog.setVisible(true);

                int value = ((Integer) dailyEntryOptionPane.getValue()).intValue();
                if(value == JOptionPane.YES_OPTION) {

                    diaryCntl.saveDailyEntry(selectedDailyEntryID, inputArea.getText());
                    updateSelectedDailyEntryInfo(selectedDate);

                }                
                
            }
        
        } );    

        clear = new JButton("Clear");
        clear.addActionListener( new ActionListener() {
            
            public void actionPerformed( ActionEvent event ) {
                
                clearButtonActionPerformed(event);
                
            }

            private void clearButtonActionPerformed(ActionEvent event) {
                
                final JOptionPane dailyEntryOptionPane = new JOptionPane(
                        "Would you like to delete the current text?\n",
                        JOptionPane.QUESTION_MESSAGE,
                        JOptionPane.YES_NO_OPTION);
        
        //FIXME: ask what does this passage mean?        
                //You can't use pane.createDialog() because that
                //method sets up the JDialog with a property change
                //listener that automatically closes the window
                //when a button is clicked.
                final JDialog dialog = new JDialog(frame, 
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
                dialog.setLocationRelativeTo(frame);
                dialog.setVisible(true);

                int value = ((Integer) dailyEntryOptionPane.getValue()).intValue();
                if(value == JOptionPane.YES_OPTION) {

                    diaryCntl.saveDailyEntry(selectedDailyEntryID, calToString(selectedDate));
                    updateSelectedDailyEntryInfo(selectedDate);
                    inputArea.setText(selectedDailyEntry.getText());

                }                
                
            }
        
        } ); 
        
        turnLeft = new JButton("<");
        turnLeft.addActionListener( new ActionListener() {
    
            public void actionPerformed(ActionEvent event) {
                
                selectedDate.add(selectedDate.DATE, -1);
                
                selectYear.setSelectedItem( selectedDate.get(selectedDate.YEAR) );
                selectMonth.setSelectedItem( selectedDate.getDisplayName(selectedDate.MONTH, Calendar.LONG, Locale.getDefault()) );
                selectDate.setSelectedItem( Integer.toString(selectedDate.get(selectedDate.DATE)) );               
                
                updateSelectedDailyEntryInfo(selectedDate);           
                
                String text = selectedDailyEntry.getText();
                
                if (text.isEmpty() || text == null) {
                    
                    inputArea.setText(calToString(selectedDate));
                    
                }
                
                inputArea.setText(text);                               
                
            }    
        
        });
        
        turnRight = new JButton(">");
        turnRight.addActionListener( new ActionListener() {
    
            public void actionPerformed(ActionEvent event) {
                
                selectedDate.add(selectedDate.DATE, 1);
                
                selectYear.setSelectedItem( selectedDate.get(selectedDate.YEAR) );
                selectMonth.setSelectedItem( selectedDate.getDisplayName(selectedDate.MONTH, Calendar.LONG, Locale.getDefault()) );
                selectDate.setSelectedItem( Integer.toString(selectedDate.get(selectedDate.DATE)) );
                
                updateSelectedDailyEntryInfo(selectedDate); 
                
                String text = selectedDailyEntry.getText();
                
                if (text.isEmpty() || text == null) {
                    
                    inputArea.setText(calToString(selectedDate));
                    
                }
                
                inputArea.setText(text);                               
                
            }
      
        });
       
    }
    
    
    public void initializePageComponents() {
        
        inputArea = new JTextArea();
        inputArea.setFont( new Font("Serif", Font.ITALIC, 16));
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        
        pagePane = new JScrollPane(inputArea);
        pagePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pagePane.setPreferredSize(new Dimension(300, 400));     
        
    }
    
    public void initializeCalendarComponents() {
        
        inputArea.setText(selectedDailyEntry.getText());
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

                februaryDateSelectionActionPerformed(event);
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

                februaryDateSelectionActionPerformed(event);
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
        
        selectDate = new JComboBox();
        
//FIXME: find a way to get rid of extra days in the beginning;
        
        selectDate.setModel(new DefaultComboBoxModel(thirtyOneDays));
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

        selectedDate = new GregorianCalendar(year, month, date);
        updateSelectedDailyEntryInfo(selectedDate);
        
        String displayText = selectedDailyEntry.getText();
        
        if (displayText.isEmpty() || displayText == null) {
            
            displayText = calToString(selectedDate);
            
        }
        
        inputArea.setText(displayText);               
        
    }
    
    

    public void februaryDateSelectionActionPerformed(ActionEvent event) {
        
        Object year = selectYear.getSelectedItem();
        String month = (String) selectMonth.getSelectedItem();
        
        if (selectYear.getSelectedIndex() == 0 || selectMonth.getSelectedIndex() == 0) {
                
                selectDate.setModel( new DefaultComboBoxModel(new String[] {"Select Date"}) );
                
                return;
                
            }
        
        if ( month == "February") {
            
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
      
    }
  
    public void addComponentsToDairyMenuPane() {
        
        diaryMenuPane = new Container();
        diaryMenuPane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(1, 1, 1, 1);
        diaryMenuPane.add(save, c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 10;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(1, 1, 1, 1);
        diaryMenuPane.add(clear, c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 14;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(1, 1, 1, 1);
        diaryMenuPane.add(turnLeft, c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 18;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(1, 1, 1, 1);
        diaryMenuPane.add(turnRight, c);   
        
    }
    
    
    
    public void addComponentsToDateSelectionPane(Container p) {
        
        dateSelectionPane = p;       
	dateSelectionPane.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 6;
        c.gridheight = 2;
        c.weightx = 1;
        c.weighty = 1;
        dateSelectionPane.add(selectYear, c);
        
        
        
    }

    private void addComponentsToPane(Container pane) {
        
/*        final boolean shouldFill = true;
        final boolean shouldWeightX = true;
        final boolean RIGHT_TO_LEFT = false;
        
        this.pane = pane;
        
        
        if (RIGHT_TO_LEFT) {
            
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            
        }

	pane.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
        
	if (shouldFill) {
            
	//maximum height, maximum width
	c.fill = GridBagConstraints.BOTH;
        
	}

	if (shouldWeightX) {
            
	c.weightx = 0.5;
        
	}
*/
//        this.pane = pane;
        pane.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 3;
        c.gridy = 8;
        c.gridwidth = 15;
        c.gridheight = 20;
        c.weightx = 3;
        c.weighty = 4;
        c.insets = new Insets(2, 5, 2, 5);
        pane.add(pagePane, c);                        

/*        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 3;
        c.gridy = 35;
        c.gridwidth = 15;
        c.gridheight = 6;
        c.weightx = 3;
        c.weighty = 4;
        c.insets = new Insets(2, 5, 2, 5);   
        pane.add(diaryMenuPane, c);  
        
        c.insets = new Insets(0, 0, 0, 0);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 3;
        c.gridy = 28;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.02;
        c.weighty = 0.02;
        pane.add(turnLeftInDiary, c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 34;
        c.gridy = 28;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.02;
        c.weighty = 0.02;
        pane.add(turnRightInDiary, c);
*/
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 3;
        c.gridy = 40;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.weightx = 0.05;
        c.weighty = 0.05;
        c.insets = new Insets(1, 1, 1, 1);
        pane.add(save, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 10;
        c.gridy = 40;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.weightx = 0.05;
        c.weighty = 0.05;
        c.insets = new Insets(1, 1, 1, 1);
        pane.add(clear, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 15;
        c.gridy = 40;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.weightx = 0.05;
        c.weighty = 0.05;
        c.insets = new Insets(1, 1, 1, 1);
        pane.add(turnLeft, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 20;
        c.gridy = 40;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.weightx = 0.05;
        c.weighty = 0.05;
        c.insets = new Insets(1, 1, 1, 1);
        pane.add(turnRight, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 60;
        c.gridy = 40;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.weightx = 0.05;
        c.weighty = 0.05;
        pane.add(unmute, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 65;
        c.gridy = 40;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.weightx = 0.05;
        c.weighty = 0.05;
        pane.add(mute, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 70;
        c.gridy = 40;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.weightx = 0.05;
        c.weighty = 0.05;
        pane.add(music, c);

//FIXME: placement of buttons
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 75;
        c.gridy = 40;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.weightx = 0.05;
        c.weighty = 0.05;
        pane.add(exit, c);
        
//        //Reset anchor to default;
//        c.anchor = GridBagConstraints.CENTER;
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 45;
        c.gridy = 5;
        c.gridwidth = 8;
        c.gridheight = 2;
        c.weightx = 0.1;
        c.weighty = 0.1;
        pane.add(selectMonth, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 55;
        c.gridy = 5;
        c.gridwidth = 8;
        c.gridheight = 2;
        c.weightx = 0.1;
        c.weighty = 0.1;
        pane.add(selectDate, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 65;
        c.gridy = 5;
        c.gridwidth = 8;
        c.gridheight = 2;
        c.weightx = 0.1;
        c.weighty = 0.1;
        pane.add(selectYear, c);
       
         
    }
    
    
}
