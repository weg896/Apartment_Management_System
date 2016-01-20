package TenantManagementGUI;

import javax.swing.JFrame;

public class TenantListFrame extends JFrame
{
	/** The standard width for the frame. */
	public static final int DEFAULT_WIDTH = 600;
	
	/** The standard height for the frame. */
	public static final int DEFAULT_HEIGHT = 300;
	
	/** 
	 * Create the frame for the entry of the ward information. 
	 * @throws Exception 
	 */
	public TenantListFrame() throws Exception
	{
		setTitle("Manange Tenants");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		TenantListPanel panel = new TenantListPanel();
		add(panel);
	}

	public static final long serialVersionUID = 1;
}
