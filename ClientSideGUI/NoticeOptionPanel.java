package ClientSideGUI;

import entities.*;
import commands.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * the panel to choose view options of notifications @ClientSide
 * @author YUQING TAN
 */
public class NoticeOptionPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	JTextArea error = null;
	private JCheckBox ckUnread;
	private JCheckBox ckMonth;
	
	public NoticeOptionPanel(final int userID){
		
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(Box.createVerticalGlue());

		JLabel lbTitle = new JLabel("View Options:");
		lbTitle.setMaximumSize(lbTitle.getPreferredSize());
		add(lbTitle);
		lbTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(Box.createVerticalGlue());

		ckUnread = new JCheckBox("Show unread notifications only");
		ckUnread.setOpaque(false);
		ckUnread.setMaximumSize(ckUnread.getPreferredSize());
		ckUnread.setAlignmentX(Component.LEFT_ALIGNMENT);
		ckUnread.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent event){
				LinkedList<NoticeData> lsNotice = null;
				if (ckUnread.isSelected()) lsNotice = (new NoticeOptionCommand()).getUnread(userID);
				ClientSidePanel.reloadPNL(ClientSidePanel.pnl4, ClientSidePanel.TranspScroPn(new NoticeDisplayPanel(userID,lsNotice)));
			}
		});
		add(ckUnread);
		add(Box.createVerticalGlue());
		
		ckMonth = new JCheckBox("Show last 30 days notifications only");
		ckMonth.setOpaque(false);
		ckMonth.setMaximumSize(ckMonth.getPreferredSize());
		ckMonth.setAlignmentX(Component.LEFT_ALIGNMENT);
		ckMonth.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent event){
				LinkedList<NoticeData> lsNotice = null;
				if (ckMonth.isSelected()) lsNotice = (new NoticeOptionCommand()).getMonth(userID);
				ClientSidePanel.reloadPNL(ClientSidePanel.pnl4, ClientSidePanel.TranspScroPn(new NoticeDisplayPanel(userID,lsNotice)));
			}
		});
		add(ckMonth);
		add(Box.createVerticalGlue());
	}
}
