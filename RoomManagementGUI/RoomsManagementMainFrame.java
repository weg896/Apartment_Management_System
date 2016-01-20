package RoomManagementGUI;

import javax.swing.*;

import entities.*;

public class RoomsManagementMainFrame extends JFrame{

	public RoomsManagementMainFrame(BuildingData building)
	{
		setTitle("Room Management");
		setSize(200, 500);
		RoomsManagementMainPanel panel = new RoomsManagementMainPanel(building);
		add(panel);
	}

	public static final long serialVersionUID = 1;
}