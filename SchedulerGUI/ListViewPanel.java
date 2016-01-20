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

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.*;
import startup.*;
import commands.*;
import containers.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListViewPanel extends JPanel{
    JComboBox viewReadBox, viewFromBox;
    JScrollPane sPane;
    NotificationsList msgList;
    JList messageJList;
    DefaultListModel messageList;
    LinkedList<Notification> messages;
    DateFormat df;
    int uID;    
    
    public ListViewPanel(int userID) {
        uID = userID;
        msgList = new NotificationsList();
        messages = msgList.recieveMessages(uID);    
        messageList= new DefaultListModel();
        messageJList = new JList(messageList);
        updateList();
        messageJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        messageJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        messageJList.setVisibleRowCount(-1);
        messageJList.addListSelectionListener(new ListSelector());
        JScrollPane listScroller = new JScrollPane(messageJList);
        listScroller.setPreferredSize(new Dimension(300, 80));                
        
        add(listScroller);
        
    }
    public void updateList() {
        messageList.removeAllElements();
        messages.clear();
        messages = msgList.recieveMessages(uID);
        ListIterator<Notification> i = messages.listIterator(0);
        while (i.hasNext()){
            Notification tempMsg = i.next();
            messageList.addElement(new String(tempMsg.title));            

        }
        messageJList.validate();
        messageJList.repaint();
    }
    
    public void updateList(Date start, Date end) {
        messageJList.removeAll();
        messages.clear();
        messages = msgList.recieveMessages(uID);
        ListIterator<Notification> i = messages.listIterator(0);
        while (i.hasNext()){
            Notification tempMsg = i.next();
            try {
                if (( start.compareTo(df.parse(tempMsg.date)) <= 0) && ( end.compareTo(df.parse(tempMsg.date))) >= 0) {
                    messageList.addElement(new String(tempMsg.title));                    
                } else {                    
                    i.remove();
                }
            } catch (ParseException ex) {
                Logger.getLogger(ListViewPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        validate();
        repaint();
    }
    
    public class ListSelector implements ListSelectionListener {
       public void valueChanged(ListSelectionEvent e) {
            System.out.println("hello");
            JList lsm = (JList)e.getSource();             
            int index = lsm.getSelectedIndex();
            Notification msg = messages.get(index);
            System.out.println(String.valueOf(index));
            NotificationTestPanel.pn1.removeAll();
            NotificationTestPanel.pn1.add(new NoticeDisplayPanel(msg));
            NotificationTestPanel.pn1.validate();
            NotificationTestPanel.pn1.repaint();
            getTopLevelAncestor().validate();
            getTopLevelAncestor().repaint();
        } 
        
    }
       
        
}
    
    

