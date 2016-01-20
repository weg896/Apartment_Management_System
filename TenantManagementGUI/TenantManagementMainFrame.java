package TenantManagementGUI;

import javax.swing.*;

public class TenantManagementMainFrame extends JFrame{

	public TenantManagementMainFrame(int id)
	{
		setTitle("Tenant Management");
		setSize(220, 500);
		TenantManagementMainPanel panel = new TenantManagementMainPanel(id);
		add(panel);
	}

	public static final long serialVersionUID = 1;
}