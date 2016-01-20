package RentalManagementGUI;

import javax.swing.*;

public class ManageRentalSidePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public static JPanel pnl1;
	public static JPanel pnl2;
	public static JPanel pnl3;
	public static JPanel pnl4;
	
	public ManageRentalSidePanel(int userID){
		
		pnl1 = new JPanel();
		pnl2 = new JPanel();
		pnl3 = new JPanel();
		pnl4 = new JPanel();
		pnl1.setLayout(new BoxLayout(pnl1, BoxLayout.PAGE_AXIS));
		pnl2.setLayout(new BoxLayout(pnl2, BoxLayout.PAGE_AXIS));
		pnl3.setLayout(new BoxLayout(pnl3, BoxLayout.PAGE_AXIS));
		pnl4.setLayout(new BoxLayout(pnl4, BoxLayout.PAGE_AXIS));
		
		add(pnl1);
		add(pnl2);
		add(pnl3);

		ManageMenuPanel tmPanel = new ManageMenuPanel(userID);
		pnl1.add(tmPanel);			
		
		setLayout(new SpringLayout());
		SpringLayout spLayout;
        try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }
        Spring Width1 = null;
        if (userID == 0){Width1 = Spring.constant(0);}
        else {Width1 = Spring.constant(200);}
        Spring Width2 = Spring.constant(400);
        Spring Width3 = Spring.constant(400);
        Spring Height = Spring.constant(450);
        SpringLayout.Constraints cons1 = spLayout.getConstraints(pnl1);
        cons1.setX(Spring.constant(0));
        cons1.setY(Spring.constant(0));
        cons1.setWidth(Width1);
        cons1.setHeight(Height);
        SpringLayout.Constraints cons2 = spLayout.getConstraints(pnl2);
        cons2.setX(Width1);
        cons2.setY(Spring.constant(0));
        cons2.setWidth(Width2);
        cons2.setHeight(Height);
        SpringLayout.Constraints cons3 = spLayout.getConstraints(pnl3);
        cons3.setX(Spring.sum(Width1, Width2));
        cons3.setY(Spring.constant(0));
        cons3.setWidth(Width3);
        cons3.setHeight(Height);
	}
}
