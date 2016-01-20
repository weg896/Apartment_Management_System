package ClientSideGUI;

import entities.*;
import commands.*;
import MainMenuGUI.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.*;

/**
 * the panel that displays all available detailed information of a selected notification @ClientSide
 * @author YUQING TAN
 */
public class NoticeDetailPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private SpringLayout.Constraints cons = null;

	public NoticeDetailPanel(final int nid, String title, String msg, int sndr, int rcvr, boolean read, String type, String date){

		setOpaque(false);
		setLayout(new SpringLayout());
		SpringLayout spLayout;
        try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }

        JLabel lbTitle = new JLabel("Notification Detail");
        lbTitle.setFont(new Font("Serif", Font.BOLD, 20));
        cons = spLayout.getConstraints(lbTitle);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(20));
        add(lbTitle);
        
        ValueDisplayPanel pnTitle = new ValueDisplayPanel("Title:",title,120); 
		cons = spLayout.getConstraints(pnTitle);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(80));
        add(pnTitle);

    
        //TenantData dataSndr = cmdUser.getTenantProfile(sndr);
        //JLabel lbSndr2 = new JLabel(dataSndr.getFirstName() + " " + dataSndr.getLastName());
        
        
        ValueDisplayPanel pnSndr = new ValueDisplayPanel("Sender:","Administrator",120); 
		cons = spLayout.getConstraints(pnSndr);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(120));
        add(pnSndr);

        ValueDisplayPanel pnRcvr = new ValueDisplayPanel("Receiver:","You",120); 
		cons = spLayout.getConstraints(pnRcvr);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(160));
        add(pnRcvr);

        ValueDisplayPanel pnDate = new ValueDisplayPanel("Date:",date,120); 
		cons = spLayout.getConstraints(pnDate);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(200));
        add(pnDate);

        ValueDisplayPanel pnType = new ValueDisplayPanel("Notice Type:",type,120); 
		cons = spLayout.getConstraints(pnType);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(240));
        add(pnType);

		BufferedImage img = (new ReadImage()).tx_220_160_grey();
        
        JLabel lbMsg1 = new JLabel("Content: ");
		cons = spLayout.getConstraints(lbMsg1);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(290));
        add(lbMsg1);

        JTextArea lbMsg2 = new JTextArea(msg);
        lbMsg2.setOpaque(false);
        lbMsg2.setEditable(false);
        lbMsg2.setLineWrap(true);
        lbMsg2.setWrapStyleWord(true);
		JScrollPane scMsgContent = new JScrollPane(lbMsg2); 
		scMsgContent.getViewport().setOpaque(false);
		scMsgContent.setOpaque(false);
		scMsgContent.setBorder(null);
		cons = spLayout.getConstraints(scMsgContent);
        cons.setX(Spring.constant(120));
        cons.setY(Spring.constant(290));
        cons.setWidth(Spring.constant(200));
        cons.setHeight(Spring.constant(140));
		add(scMsgContent);
        
		MenuButton lbMsg3 = new MenuButton("",img,img);
		cons = spLayout.getConstraints(lbMsg3);
        cons.setX(Spring.constant(110));
        cons.setY(Spring.constant(280));
        cons.setWidth(Spring.constant(220));
        cons.setHeight(Spring.constant(160));
        add(lbMsg3);
        
		final JCheckBox ckRead = new JCheckBox("Mark as read");
		cons = spLayout.getConstraints(ckRead);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(450));
        ckRead.setSelected(read);
		ckRead.setOpaque(false);
		ckRead.setMaximumSize(ckRead.getPreferredSize());
		ckRead.setAlignmentX(Component.LEFT_ALIGNMENT);
		ckRead.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent event){
				(new NoticeOptionCommand()).MarkAsRead(nid, ckRead.isSelected());
			}
		});
		add(ckRead);
        
        
        MenuButton btCloseDetail = new MenuButton("Close Detail",120);
		cons = spLayout.getConstraints(btCloseDetail);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(480));
        btCloseDetail.addActionListener(new ActionListener(){
        	public void actionPerformed (ActionEvent event) {
				ClientSidePanel.pnl3.removeAll();
				ClientSidePanel.pnl3.getTopLevelAncestor().setSize(840, 600);
				ClientSidePanel.pnl3.getTopLevelAncestor().validate();
				ClientSidePanel.pnl3.getTopLevelAncestor().repaint();
        	}
        });
        add(btCloseDetail);
	}
}
