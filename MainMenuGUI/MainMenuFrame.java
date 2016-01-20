package MainMenuGUI;

import javax.swing.JFrame;



public class MainMenuFrame extends JFrame{
	/** The standard width for the frame. */
	public static final int DEFAULT_WIDTH = 350;
	
	/** The standard height for the frame. */
	public static final int DEFAULT_HEIGHT = 500;
	
	/** 
	 * Create the frame for the main menu containing four options. 
	 */
	public MainMenuFrame (final int id)
	{
		setTitle("Main Menu");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		MainMenuPanel  panel = new MainMenuPanel(id);
		add(panel);
	}

	public static final long serialVersionUID = 1;
}
