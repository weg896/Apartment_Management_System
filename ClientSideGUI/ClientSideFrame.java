package ClientSideGUI;

import javax.swing.*;

/**
 * the main frame of @ClientSide, for displaying all client side panels
 * @author YUQING TAN
 */
public class ClientSideFrame extends JFrame{

	public ClientSideFrame (int userID)
	{
		setTitle("Apartment Management System - Client Side");
		if (userID == 0){setSize(640, 600);} //0 means a guest
		else {setSize(200, 600);}
		setResizable(false);
		setLocation(100, 100);
		ClientSidePanel pnClientSide = new ClientSidePanel(userID);
		add(pnClientSide);
	}

	//public static ClientSideFrame frClientSide(){return this;}
	
	public static final long serialVersionUID = 1;
}
