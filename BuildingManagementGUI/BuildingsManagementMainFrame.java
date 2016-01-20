package BuildingManagementGUI;

import javax.swing.*;

public class BuildingsManagementMainFrame extends JFrame{

	public BuildingsManagementMainFrame(int id)
	{
		setTitle("Building Management");
		setSize(220, 500);
		BuildingsManagementMainPanel panel = new BuildingsManagementMainPanel(id);
		add(panel);
	}

	public static final long serialVersionUID = 1;
}