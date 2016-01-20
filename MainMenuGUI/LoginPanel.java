package MainMenuGUI;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

import ClientSideGUI.ClientSideFrame;


import startup.Database;

import commands.LoginCommand;

/** 
 * The panel to obtain input to log in the system, 
 * and then it will start the main system. 
 */
public class LoginPanel extends JPanel 
{
	/**
	 * The panel for the entry of username.
	 */
	ValueEntryPanel userNamePanel;
	
	/**
	 * The panel for the entry of the password.
	 */
	ValuePasswordPanel passwordPanel;

	
	/**
	 * A panel for an error message, if an error should occur.
	 */
	JTextArea error = null;

	/**
	 * Create a panel with a field for the entry of username, 
	 * a field for entry of password and a submit button to 
	 * submit the data for login.
	 */
	public LoginPanel()
	{
		setOpaque(false);
		setLayout(new SpringLayout());
		SpringLayout spLayout = null;
		SpringLayout.Constraints cons = null;
		try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }
		
		JLabel prompt = new JLabel("AMS Login");
		prompt.setFont(new Font("Serif", Font.BOLD, 20));
		cons = spLayout.getConstraints(prompt);
        cons.setX(Spring.constant(150));
        cons.setY(Spring.constant(20));
		add(prompt);

		userNamePanel = new ValueEntryPanel("username",10,120,200);
		cons = spLayout.getConstraints(userNamePanel);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(60));
		add(userNamePanel);

		passwordPanel = new ValuePasswordPanel("password",10,120,200);
		cons = spLayout.getConstraints(passwordPanel);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(100));
		add(passwordPanel);

		MenuButton Submit = new MenuButton("Login",100);
		cons = spLayout.getConstraints(Submit);
        cons.setX(Spring.constant(60));
        cons.setY(Spring.constant(140));
		Submit.addMouseListener( new SubmitMouseListener());
		add(Submit);

		MenuButton GLogin = new MenuButton("Guest Login",120);
		cons = spLayout.getConstraints(GLogin);
        cons.setX(Spring.constant(180));
        cons.setY(Spring.constant(140));
        GLogin.addMouseListener( new SubmitMouseListener(){
			public void mouseClicked(MouseEvent evt){
				getTopLevelAncestor().setVisible(false);
				ClientSideFrame frame = new ClientSideFrame(0);
				frame.setLocation(300, 300);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
        });
		add(GLogin);
		
		MenuButton exitButton = new MenuButton("Cancel",100);
		cons = spLayout.getConstraints(exitButton);
        cons.setX(Spring.constant(120));
        cons.setY(Spring.constant(170));
		exitButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				Database.closeConnection();
				System.exit(0);
			}
	    });
		add(exitButton);

	}

	/**
	 * The listener for the press of the submit button.
	 * When it happens, obtain the data values from the fields, and
	 * try to login.  Afterwards, create and make visible the window
	 * with the main options for the user.
	 */
	private class SubmitMouseListener implements MouseListener
	{
		
		public SubmitMouseListener(){
		}
		
		public void mouseClicked (MouseEvent event)
		{
			if (error != null)
			{
				remove(error);  // remove error from previous submission
				error = null;
			}
			String name = userNamePanel.getValueAsString();
			String password = passwordPanel.getValueAsString();
			LoginCommand lgn = new LoginCommand();
			lgn.Login(name, password);
			if (lgn.wasSuccessful())
			{
				getTopLevelAncestor().setVisible(false);
				if(lgn.isManager()){
					MainMenuFrame frame = new MainMenuFrame(lgn.getUserID());  
					frame.setLocation(300, 300);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				}else{
					ClientSideFrame frame = new ClientSideFrame(lgn.getUserID());
					frame.setLocation(300, 300);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				}
				
			}
			else
			{
				/* Divide the error message into lines short enough to fit in the 
				 * window, and store the message in the error text area. */
				error = new JTextArea(SplitString.at(lgn.getErrorMessage(), 50));
				error.setMaximumSize(error.getPreferredSize());
				add(error);
				error.setAlignmentX(Component.CENTER_ALIGNMENT);
				//add(Box.createVerticalGlue());
				revalidate();
			}
		}


		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

	public static final long serialVersionUID = 1;
}