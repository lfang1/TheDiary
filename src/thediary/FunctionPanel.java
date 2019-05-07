/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thediary;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author lkf5083
 */
class FunctionPanel extends JPanel {
    
    NewDiaryUI diaryUI;
    JPanel leftPanel;
    JPanel middlePanel;
    JPanel rightPanel;
    
    JButton saveButton;
    JButton clearButton;
    JButton turnLeftButton;
    JButton turnRightButton;   
    JButton exitButton;
    
    public FunctionPanel(NewDiaryUI diaryUI) {
        
        this.diaryUI = diaryUI;
        leftPanel = new JPanel();
        middlePanel = new JPanel();
        rightPanel = new JPanel();
        initializeFunctionPanel();
        
    }

    private void initializeFunctionPanel() {
        
        initializeLeftPanel();
        addComponentsToLeftPanel();
        initializeRightPanel();
        addComponentsToRightPanel();
        addComponentsToFunctionPanel();
        
    }

    private void initializeLeftPanel() {
        
        saveButton = new JButton("Save");
        saveButton.addActionListener( new ActionListener() {

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
                
            }
        
        } );    
        
        clearButton = new JButton("Clear");
        clearButton.addActionListener( new ActionListener() {
            
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

                    diaryUI.clearText();

                }                
                
            }
        
        } ); 
        
 
        turnLeftButton = new JButton("  <  ");
        turnLeftButton.addActionListener( new ActionListener() {
    
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
                
                diaryUI.turnPage(-1);           
                
            }
   
        });
        
        
        turnRightButton = new JButton("  >  ");
        turnRightButton.addActionListener( new ActionListener() {
    
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
                
                diaryUI.turnPage(1);           
                
            }
   
        });
     
        
    }

    private void addComponentsToLeftPanel() {
        
        leftPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 3;
        c.gridheight = 2;
        c.weightx = 0.01;
        c.weighty = 0.01;
        c.insets = new Insets(1, 1, 1, 1);
        leftPanel.add(saveButton, c);
        
        c = new GridBagConstraints();   
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 6;
        c.gridy = 1;
        c.gridwidth = 3;
        c.gridheight = 2;
        c.weightx = 0.01;
        c.weighty = 0.01;
        c.insets = new Insets(1, 1, 1, 1);
        leftPanel.add(clearButton, c);
        
        c = new GridBagConstraints();   
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 11;
        c.gridy = 1;
        c.gridwidth = 3;
        c.gridheight = 2;
        c.weightx = 0.01;
        c.weighty = 0.01;
        c.insets = new Insets(1, 1, 1, 1);
        leftPanel.add(turnLeftButton, c);
        
        c = new GridBagConstraints();   
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 16;
        c.gridy = 1;
        c.gridwidth = 3;
        c.gridheight = 2;
        c.weightx = 0.01;
        c.weighty = 0.01;
        c.insets = new Insets(1, 1, 1, 1);
        leftPanel.add(turnRightButton, c);
        
        
    }
    
    private void initializeRightPanel() {
        
        exitButton = new JButton("Exit");
        exitButton.addActionListener( new ActionListener() {

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
                
                diaryUI.setVisible(false);
                diaryUI.dispose();
                
                LoginUI loginUI = new LoginUI();
                
            }
        
        } );    
        
    }
    
    
    private void addComponentsToRightPanel() {
        
        rightPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 3;
        c.gridheight = 2;
        c.weightx = 0.01;
        c.weighty = 0.01;
        c.insets = new Insets(1, 1, 1, 1);
        rightPanel.add(exitButton, c);
        
    }
    

    private void addComponentsToFunctionPanel() {
        
        this.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 4;
        c.gridheight = 3;
        c.weightx = 0.01;
        c.weighty = 0.01;
        c.insets = new Insets(1, 1, 1, 1);
        this.add(leftPanel, c);
        c = new GridBagConstraints();
                
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 7;
        c.gridy = 1;
        c.gridwidth = 12;
        c.gridheight = 3;
        c.weightx = 0.03;
        c.weighty = 0.03;
        c.insets = new Insets(1, 1, 1, 1);
        this.add(middlePanel, c);
        c = new GridBagConstraints();
                        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 22;
        c.gridy = 1;
        c.gridwidth = 4;
        c.gridheight = 3;
        c.weightx = 0.01;
        c.weighty = 0.01;
        c.insets = new Insets(1, 1, 1, 1);
        this.add(rightPanel, c);
        c = new GridBagConstraints();
        
        
    }

    
}
