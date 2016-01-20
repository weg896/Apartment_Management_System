/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Michael Lucki
 */
package SchedulerGUI;

import java.util.LinkedList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import containers.*;
import commands.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NoticeViewPanel extends JPanel implements ActionListener{
        protected JPanel listPanel;
        protected JPanel messagePanel;
        
        public NoticeViewPanel(int userID) {
        /*NotificationsList viewList = new NotificationsList();
            try {
                LinkedList<Notification> nl = viewList.recieveMessages(userID);
            } catch (Exception ex) {
                Logger.getLogger(NoticeViewPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        */
        JRadioButton listView = new JRadioButton("List View");
        listView.addActionListener(this);    
        listView.setSelected(true);
        JRadioButton calendarView = new JRadioButton("Calendar View");
        calendarView.addActionListener(this);
        ButtonGroup viewType = new ButtonGroup();
            viewType.add(listView);
            viewType.add(calendarView);
        add(listView);
        add(calendarView);           
        

        
    }
     
    public void actionPerformed(ActionEvent e) {
    
    
    }
        
}
