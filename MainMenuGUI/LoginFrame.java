package MainMenuGUI;

import javax.swing.JFrame;


public class LoginFrame extends JFrame{
	/** The standard width for the frame. */
	public static final int DEFAULT_WIDTH = 400;
	
	/** The standard height for the frame. */
	public static final int DEFAULT_HEIGHT = 250;
	
	/** 
	 * Create the frame for login. 
	 */
	public LoginFrame ()
	{
		setTitle("AMS Login");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		LoginPanel panel = new LoginPanel();
		add(panel);
		
	}

	public static final long serialVersionUID = 1;
}
