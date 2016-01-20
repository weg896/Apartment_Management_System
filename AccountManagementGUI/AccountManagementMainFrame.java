package AccountManagementGUI;

import javax.swing.*;

public class AccountManagementMainFrame extends JFrame{

	public AccountManagementMainFrame (final int id)
	{
		setTitle("Account Management");
		setSize(220, 500);
		AccountManagementMainPanel panel = new AccountManagementMainPanel(id);
		add(panel);
	}

	public static final long serialVersionUID = 1;
}