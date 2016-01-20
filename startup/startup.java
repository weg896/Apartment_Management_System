package startup;


import javax.swing.JFrame;

import MainMenuGUI.LoginFrame;


/**
 * This class starts the system and build connection to database
 * The first window is login window
 *
 */
public class startup {
	
	public static void main(String[ ] args)
	{
		Database.getInstance();
		LoginFrame frame = new LoginFrame();
		frame.setLocation(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	            Database.closeConnection();
	        }
	    }, "Shutdown-thread"));
	}

}
