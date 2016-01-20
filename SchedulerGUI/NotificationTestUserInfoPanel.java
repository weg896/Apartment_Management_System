

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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import MainMenuGUI.MenuButton;

public class NotificationTestUserInfoPanel extends JPanel implements ActionListener{
int uID;
public NotificationTestUserInfoPanel(int userID) {    
        uID = userID;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        MenuButton viewNoticeButton = new MenuButton("View Notices",120);
        MenuButton sendNoticeButton = new MenuButton("Send Message",120);
        viewNoticeButton.setActionCommand("view");
        sendNoticeButton.setActionCommand("send");
        viewNoticeButton.addActionListener(this);
        sendNoticeButton.addActionListener(this);
		add(Box.createVerticalGlue());
        add(viewNoticeButton);
		add(Box.createVerticalGlue());
        add(sendNoticeButton);
		add(Box.createVerticalGlue());
}
@Override
    public void actionPerformed(ActionEvent e) {
        if ("view".equals(e.getActionCommand())) {
            JPanel newPanel = new ListViewPanel(uID);          
            JPanel newPanel2 = new CalendarViewPanel();           
            NotificationTestPanel.pn2.removeAll();
            NotificationTestPanel.pn3.removeAll();    
            NotificationTestPanel.pn2.add(newPanel);
            NotificationTestPanel.pn2.add(newPanel2);
            getTopLevelAncestor().setSize(1200, 600);
            getTopLevelAncestor().validate();
            getTopLevelAncestor().repaint();
        }
        if ("send".equals(e.getActionCommand())){
            JPanel newPanel = new NoticeSendPanel(uID);
            NotificationTestPanel.pn2.removeAll();
            NotificationTestPanel.pn2.add(newPanel);  
            getTopLevelAncestor().setSize(1200, 600);
            getTopLevelAncestor().validate();
            getTopLevelAncestor().repaint();			
        }
    }
}


