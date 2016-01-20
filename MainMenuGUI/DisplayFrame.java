package MainMenuGUI;


import javax.swing.JFrame;

import commands.RemoveAccountCommand;

public class DisplayFrame extends JFrame{
	/** The standard width for the frame. */
	public static final int DEFAULT_WIDTH = 350;
	
	/** The standard height for the frame. */
	public static final int DEFAULT_HEIGHT = 200;
	
	/** 
	 * Create the frame for login. 
	 */
	public DisplayFrame (String title, String info)
	{
		setTitle(title);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		DisplayPanel panel = new DisplayPanel(info);
		add(panel);
		
	}
	
	public DisplayFrame (String title, String info, final RemoveAccountCommand re,final int userID,final boolean isTenant)
	{
		setTitle(title);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		DisplayPanel panel = new DisplayPanel(info, re,userID,isTenant);
		add(panel);
		
	}

	public static final long serialVersionUID = 1;
}
