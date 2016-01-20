package AccountManagementGUI;
import javax.swing.*;

public class ModifiyAccountFrame extends JFrame{
	
	public  ModifiyAccountFrame (int id, boolean isStaff)
	{
			setTitle("Account Management");
			setSize(600, 500);
			ModifyAccountPanel panel= new ModifyAccountPanel(id, isStaff);
			add(panel);
		
	}

	public static final long serialVersionUID = 1;
}
