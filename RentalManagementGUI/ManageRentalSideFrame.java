package RentalManagementGUI;

import javax.swing.*;

public class ManageRentalSideFrame extends JFrame{

	public ManageRentalSideFrame (int userID)
	{
		setTitle("AMS Client Side");
		if (userID == 0){setSize(400, 500);}
		else {setSize(200, 500);}
		
		ManageRentalSidePanel pn = new ManageRentalSidePanel(userID);
		add(pn);
	}

	public static final long serialVersionUID = 1;
}
