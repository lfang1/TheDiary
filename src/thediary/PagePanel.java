/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thediary;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author lkf5083
 */
class PagePanel extends JPanel {
    
    NewDiaryUI newDiaryUI;
    JTextArea pageTextArea; 
    JScrollPane pageScrollPane;

        
    public PagePanel(NewDiaryUI newDiaryUI) {
        
        this.newDiaryUI = newDiaryUI;
        initializePagePanel();
        addComponentsToPagePanel();
                
    }

    private void initializePagePanel() {
        
        pageTextArea = new JTextArea();
        
        pageTextArea.setFont( new Font("Serif", Font.ITALIC, 16));
        pageTextArea.setLineWrap(true);
        pageTextArea.setWrapStyleWord(true);
        
        pageScrollPane = new JScrollPane(pageTextArea);
        pageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pageScrollPane.setPreferredSize(new Dimension(300, 400));
                
    }

    private void addComponentsToPagePanel() {
        
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 2;
        c.gridy = 5;
        c.gridwidth = 30;
        c.gridheight = 40;
        c.weightx = 0.3;
        c.weighty = 0.4;
        c.insets = new Insets(3, 3, 3, 3);
        add(pageScrollPane, c);
        
        c = new GridBagConstraints();
        
       
        
    }
    
    public String getText() {
        
        return pageTextArea.getText();
        
    }
    
    public void setText(String text) {
              
        pageTextArea.setText(text);
     
    }
    
    
}
