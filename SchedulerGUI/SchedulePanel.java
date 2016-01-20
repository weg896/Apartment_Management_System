/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SchedulerGUI;

import javax.swing.*;

import MainMenuGUI.DisplayFrame;

import java.util.*;
import java.text.*;

import commands.*;
/**
 *
 * @author mjl079
 */
public class SchedulePanel extends JPanel{
private static final long serialVersionUID = 1L;            
    
private int userID = -1;

	@SuppressWarnings("rawtypes")
	private JComboBox cbMsgType;
	
	private JTextField txMsgTitle;
	
	private JTextArea txMsgContent;
	
	@SuppressWarnings("unused")
	private JTextArea error = null;

	private SpringLayout.Constraints cons = null;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SchedulePanel(int userID){
		this.userID = userID;
		setLayout(new SpringLayout());
		SpringLayout spLayout;
        try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }

		JLabel lbTitle = new JLabel("Compose a request");
		cons = spLayout.getConstraints(lbTitle);
        cons.setX(Spring.constant(200));
        cons.setY(Spring.constant(20));
		add(lbTitle);

		JLabel lbMsgType = new JLabel("Message Type:");
		cons = spLayout.getConstraints(lbMsgType);
        cons.setX(Spring.constant(30));
        cons.setY(Spring.constant(75));
		add(lbMsgType);
		
		String[] strMsgType = new String[]{
				"request for maintenance",
				"request for meeting",
				"complain",
				"move out"};
		cbMsgType = new JComboBox(strMsgType);
		cons = spLayout.getConstraints(cbMsgType);
        cons.setX(Spring.constant(200));
        cons.setY(Spring.constant(70));
		add(cbMsgType);

		JLabel lbMsgTitle = new JLabel("Message Title");
		cons = spLayout.getConstraints(lbMsgTitle);
        cons.setX(Spring.constant(30));
        cons.setY(Spring.constant(105));
		add(lbMsgTitle);
		
		txMsgTitle = new JTextField(50);
		cons = spLayout.getConstraints(txMsgTitle);
        cons.setX(Spring.constant(200));
        cons.setY(Spring.constant(100));
        cons.setWidth(Spring.constant(300));
        cons.setHeight(Spring.constant(25));
		add(txMsgTitle);
		
		JLabel lbMsgContent = new JLabel("Message Content");
		cons = spLayout.getConstraints(lbMsgContent);
        cons.setX(Spring.constant(30));
        cons.setY(Spring.constant(135));
		add(lbMsgContent);
		
		txMsgContent = new JTextArea();
		txMsgContent.setLineWrap(true);
		cons = spLayout.getConstraints(txMsgContent);
        cons.setX(Spring.constant(200));
        cons.setY(Spring.constant(130));
        cons.setWidth(Spring.constant(300));
        cons.setHeight(Spring.constant(200));
		add(txMsgContent);
		
	JButton btSend = new JButton("Send Message");
	btSend.addActionListener(new SendListener());
        cons = spLayout.getConstraints(btSend);
        cons.setX(Spring.constant(200));
        cons.setY(Spring.constant(350));
	add(btSend);
        }                


private class SendListener implements java.awt.event.ActionListener
{
    private Notification message;
    public void actionPerformed (java.awt.event.ActionEvent event)
		{
		
			message = new Notification();
                        message.updateType(1 + cbMsgType.getSelectedIndex());
			message.updateTitle(txMsgTitle.getText());
			message.updateContent(txMsgContent.getText());
			message.updateSender(userID);
                        message.updateRecipient(1);
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = new Date();
                        message.updateDate(dateFormat.format(date));
                        System.out.println(message.sender);
                        message.post();
                        DisplayFrame frame = new DisplayFrame("Notification Sent", "Message Sent");
				frame.setLocation(300, 300);
				frame.setVisible(true);
      		}

}
}