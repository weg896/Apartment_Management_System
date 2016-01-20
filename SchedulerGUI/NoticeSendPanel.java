package SchedulerGUI;

import MainMenuGUI.DisplayFrame;
import MainMenuGUI.MenuButton;
import MainMenuGUI.ReadImage;
import MainMenuGUI.SplitString;
import commands.NoticeSendCommand;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * the panel that completes entering and sending request task @ClientSide
 * @author YUQING TAN
 */
public class NoticeSendPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JTextArea error = null;
	private SpringLayout.Constraints cons = null;
	private int userID = -1;
	@SuppressWarnings("rawtypes")
	private JComboBox cbMsgType;
	private JTextField txMsgTitle;
	private JTextArea txMsgContent;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public NoticeSendPanel(int userID){

		this.userID = userID;
		setOpaque(false);
		setLayout(new SpringLayout());
		SpringLayout spLayout;
        try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }

		JLabel lbTitle = new JLabel("Compose a request");
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
		cbMsgType = new JComboBox(strMsgType);
		cons = spLayout.getConstraints(cbMsgType);
        cons.setX(Spring.constant(150));
        cons.setY(Spring.constant(80));
		add(cbMsgType);

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
		
		MenuButton btSubmit = new MenuButton("Submit",100);
		btSubmit.addActionListener(new SubmitListener());
		cons = spLayout.getConstraints(btSubmit);
        cons.setX(Spring.constant(150));
        cons.setY(Spring.constant(450));
		add(btSubmit);
	}

	private class SubmitListener implements ActionListener
	{
		public void actionPerformed (ActionEvent event)
		{
			if (error != null)
			{
				remove(error);  // remove error from previous submission
				error = null;
			}
			int msgType = 1 + cbMsgType.getSelectedIndex();
			String msgTitle = txMsgTitle.getText();
			String msgContent = txMsgContent.getText();
			NoticeSendCommand sr = new NoticeSendCommand();
			sr.Send(msgType, msgTitle, msgContent, userID);
			if (sr.wasSuccessful())
			{
				String info = "<html>Your message " + msgTitle + " is sent to administrators successfully.<html>";
				DisplayFrame frame = new DisplayFrame("Prompt", info);
				frame.setLocation(300, 300);
				frame.setVisible(true);
			}
			else
			{
				/* Divide the error message into lines short enough to fit in the 
				 * window, and store the message in the error text area. */
				error = new JTextArea(SplitString.at(sr.getErrorMessage(), 50));
				error.setMaximumSize(error.getPreferredSize());
				add(error);
				error.setAlignmentX(Component.CENTER_ALIGNMENT);
				add(Box.createVerticalGlue());
				revalidate();
			}
		}
	}
}
