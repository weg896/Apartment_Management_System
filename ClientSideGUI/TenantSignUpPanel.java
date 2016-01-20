package ClientSideGUI;

import MainMenuGUI.*;
import commands.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.text.*;
import javax.swing.*;

/**
 * the panel for a guest user (without real account) to sign up @ClientSide
 * @author YUQING TAN
 */
public class TenantSignUpPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextArea error = null;
	private SpringLayout.Constraints cons = null;
	private JTextField txFstName;
	private JTextField txLstName;
	private JFormattedTextField txPhnNum;
	private JTextField txEmlAddr;
	private JRadioButton rbPhnNum;
	private JRadioButton rbEmlAddr;
	private boolean redir;
	private int roomnum;
	private String building;
	
	public TenantSignUpPanel(boolean redir, int roomnum, String building){
		this.redir = redir;
		this.roomnum = roomnum;
		this.building = building;
		
		setOpaque(false);
		setLayout(new SpringLayout());
		SpringLayout spLayout;
        try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }

        JLabel lbTitle = new JLabel("Sign up");
        lbTitle.setFont(new Font("Serif", Font.BOLD, 20));
		cons = spLayout.getConstraints(lbTitle);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(20));
        add(lbTitle);
        
        ////////////////////////////////////////////////////////////
        
		BufferedImage img1 = (new ReadImage()).tx_120_30();
        
		JLabel lbFstName = new JLabel("First Name:");
		cons = spLayout.getConstraints(lbFstName);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(80));
		add(lbFstName);
		
		txFstName = new JTextField(50);
		txFstName.setOpaque(false);
		txFstName.setBorder(null);
		cons = spLayout.getConstraints(txFstName);
        cons.setX(Spring.constant(150));
        cons.setY(Spring.constant(80));
        cons.setWidth(Spring.constant(100));
        cons.setHeight(Spring.constant(25));
		add(txFstName);

		MenuButton bgFstName = new MenuButton("",img1,img1);
		cons = spLayout.getConstraints(bgFstName);
        cons.setX(Spring.constant(140));
        cons.setY(Spring.constant(75));
        cons.setWidth(Spring.constant(120));
        cons.setHeight(Spring.constant(30));
        add(bgFstName);
		
		JLabel lbLstName = new JLabel("Last Name:");
		cons = spLayout.getConstraints(lbLstName);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(120));
		add(lbLstName);
		
		txLstName = new JTextField(50);
		txLstName.setOpaque(false);
		txLstName.setBorder(null);
		cons = spLayout.getConstraints(txLstName);
        cons.setX(Spring.constant(150));
        cons.setY(Spring.constant(120));
        cons.setWidth(Spring.constant(100));
        cons.setHeight(Spring.constant(25));
		add(txLstName);

		MenuButton bgLstName = new MenuButton("",img1,img1);
		cons = spLayout.getConstraints(bgLstName);
        cons.setX(Spring.constant(140));
        cons.setY(Spring.constant(115));
        cons.setWidth(Spring.constant(120));
        cons.setHeight(Spring.constant(30));
        add(bgLstName);
        
        ////////////////////////////////////////////////////////////

		BufferedImage img2 = (new ReadImage()).tx_220_30();
		
		JLabel lbPhnNum = new JLabel("Phone Number:");
		cons = spLayout.getConstraints(lbPhnNum);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(160));
		add(lbPhnNum);

		txPhnNum = new JFormattedTextField(NumberFormat.getIntegerInstance());
		txPhnNum.setColumns(15);
		txPhnNum.setOpaque(false);
		txPhnNum.setBorder(null);
		cons = spLayout.getConstraints(txPhnNum);
        cons.setX(Spring.constant(150));
        cons.setY(Spring.constant(160));
        cons.setWidth(Spring.constant(200));
        cons.setHeight(Spring.constant(25));
        add(txPhnNum);

		MenuButton bgPhnNum = new MenuButton("",img2,img2);
		cons = spLayout.getConstraints(bgPhnNum);
        cons.setX(Spring.constant(140));
        cons.setY(Spring.constant(155));
        cons.setWidth(Spring.constant(220));
        cons.setHeight(Spring.constant(30));
        add(bgPhnNum);
        
		JLabel lbEmlAddr = new JLabel("Email Address:");
		cons = spLayout.getConstraints(lbEmlAddr);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(200));
		add(lbEmlAddr);
		
		txEmlAddr = new JTextField(100);
		txEmlAddr.setOpaque(false);
		txEmlAddr.setBorder(null);
		cons = spLayout.getConstraints(txEmlAddr);
        cons.setX(Spring.constant(150));
        cons.setY(Spring.constant(200));
        cons.setWidth(Spring.constant(200));
        cons.setHeight(Spring.constant(25));
		add(txEmlAddr);
        
		MenuButton bgEmlAddr = new MenuButton("",img2,img2);
		cons = spLayout.getConstraints(bgEmlAddr);
        cons.setX(Spring.constant(140));
        cons.setY(Spring.constant(195));
        cons.setWidth(Spring.constant(220));
        cons.setHeight(Spring.constant(30));
        add(bgEmlAddr);
		
        ////////////////////////////////////////////////////////////
        
		JLabel lbCType = new JLabel("<html>Preferred<br>Contact Type:</html>");
		cons = spLayout.getConstraints(lbCType);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(240));
		add(lbCType);
		
		rbPhnNum = new JRadioButton("Phone");
		rbEmlAddr = new JRadioButton("Email");
		rbPhnNum.setOpaque(false);
		rbEmlAddr.setOpaque(false);
		cons = spLayout.getConstraints(rbPhnNum);
        cons.setX(Spring.constant(150));
        cons.setY(Spring.constant(240));
		cons = spLayout.getConstraints(rbEmlAddr);
        cons.setX(Spring.constant(210));
        cons.setY(Spring.constant(240));
		add(rbPhnNum);
		add(rbEmlAddr);
		ButtonGroup bgCType = new ButtonGroup();
		bgCType.add(rbPhnNum);
		bgCType.add(rbEmlAddr);

		MenuButton btSubmit = new MenuButton("Submit",100);
		btSubmit.addActionListener(new SubmitListener());
		cons = spLayout.getConstraints(btSubmit);
        cons.setX(Spring.constant(150));
        cons.setY(Spring.constant(300));
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
			String CType = null;
			if (rbPhnNum.isSelected()) CType = "Phone";
			else if (rbEmlAddr.isSelected()) CType = "Email";
			String msgContent = null; 
			msgContent = "Tenant Request for Creating Account"+
					";\n First Name: "+txFstName.getText()+
					";\n Last Name: "+txLstName.getText()+
					";\n Phone Number: "+txPhnNum.getText()+
					";\n Email Address: "+txEmlAddr.getText()+
					";\n Preferred Contact Type: "+CType+";";
			if (redir) {
				msgContent = msgContent+
						 "\n Tenant Request for Applying Room"+
						";\n Building: "+building+
						";\n Room Number: "+roomnum+";";
			}
				NoticeSendCommand sr = new NoticeSendCommand();
				sr.Send(6, "Tenant Request for Creating Account", msgContent, 0);
				// 6 is the msg type 'request for creating account'
				// 0 means sender is a guest
			if (sr.wasSuccessful())
			{
				String info = null;
				if (redir)
					info = "<html>Confirmation" +
							"<br>You (" + txFstName.getText() + " " + txLstName.getText() + ") " +
							"have started application on room #" + roomnum + " in " + building + "." +
							"<br>Your Request for Creating Account and Applying Room is sent to administrators successfully.<html>";
				else
					info = "<html>Your Request for Creating Account is sent to administrators successfully.<html>";
				DisplayFrame frame = new DisplayFrame("Prompt", info);
				frame.setLocation(200, 200);
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
