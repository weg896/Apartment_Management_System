package SchedulerGUI;

import MainMenuGUI.DisplayFrame;
import MainMenuGUI.MenuButton;
import MainMenuGUI.ReadImage;
import MainMenuGUI.SplitString;
import commands.NoticeSendCommand;
import commands.Notification;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class NoticeDisplayPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JTextArea error = null;
	private SpringLayout.Constraints cons = null;	
	@SuppressWarnings("rawtypes")
	private JComboBox cbMsgType;
	private JTextField txMsgTitle;
	private JTextArea txMsgContent;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public NoticeDisplayPanel(Notification msg){
		
		setOpaque(false);
		setLayout(new SpringLayout());
		SpringLayout spLayout;
        try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }

		JLabel lbTitle = new JLabel("Viewing Message");
		lbTitle.setFont(new Font("Serif", Font.BOLD, 20));
		cons = spLayout.getConstraints(lbTitle);
        cons.setX(Spring.constant(150));
        cons.setY(Spring.constant(20));
		add(lbTitle);

		JLabel lbMsgType = new JLabel("Message Type:");
		cons = spLayout.getConstraints(lbMsgType);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(80));
		add(lbMsgType);
		
		String[] strMsgType = new String[]{
				"request for maintenance",
				"request for meeting",
				"complain",
				"move out"};
		JLabel msgType = new JLabel(strMsgType[msg.type]);
                cons = spLayout.getConstraints(msgType);
        cons.setX(Spring.constant(150));
        cons.setY(Spring.constant(80));
		add(msgType);

		JLabel lbMsgTitle = new JLabel("Message Title:");
		cons = spLayout.getConstraints(lbMsgTitle);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(130));
		add(lbMsgTitle);
		
		txMsgTitle = new JTextField(50);
		txMsgTitle.setOpaque(false);
		txMsgTitle.setBorder(null);
		//txMsgTitle.getDocument().addDocumentListener(new TextListener());
		cons = spLayout.getConstraints(txMsgTitle);
        cons.setX(Spring.constant(150));
        cons.setY(Spring.constant(130));
        cons.setWidth(Spring.constant(400));
        cons.setHeight(Spring.constant(25));
                txMsgTitle.setText(msg.title);
		add(txMsgTitle);
		
		BufferedImage img1 = (new ReadImage()).tx_420_30();
		MenuButton bgMsgTitle = new MenuButton("",img1,img1);
		cons = spLayout.getConstraints(bgMsgTitle);
        cons.setX(Spring.constant(140));
        cons.setY(Spring.constant(125));
        cons.setWidth(Spring.constant(420));
        cons.setHeight(Spring.constant(30));
        add(bgMsgTitle);
		
		JLabel lbMsgContent = new JLabel("Message Content:");
		cons = spLayout.getConstraints(lbMsgContent);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(180));
		add(lbMsgContent);
		
		txMsgContent = new JTextArea();
		txMsgContent.setOpaque(false);
		txMsgContent.setLineWrap(true);
		txMsgContent.setWrapStyleWord(true);
                txMsgContent.setText(msg.content);
		//txMsgContent.getDocument().addDocumentListener(new TextListener());
		JScrollPane scMsgContent = new JScrollPane(txMsgContent); 
		scMsgContent.getViewport().setOpaque(false);
		scMsgContent.setOpaque(false);
		scMsgContent.setBorder(null);
		cons = spLayout.getConstraints(scMsgContent);
        cons.setX(Spring.constant(150));
        cons.setY(Spring.constant(180));
        cons.setWidth(Spring.constant(400));
        cons.setHeight(Spring.constant(260));
		add(scMsgContent);
		
		BufferedImage img2 = (new ReadImage()).tx_420_280();
		MenuButton bgMsgContent = new MenuButton("",img2,img2);
		cons = spLayout.getConstraints(bgMsgContent);
        cons.setX(Spring.constant(140));
        cons.setY(Spring.constant(170));
        cons.setWidth(Spring.constant(420));
        cons.setHeight(Spring.constant(280));
        add(bgMsgContent);
	
	}

}
