
package TenantManagementGUI;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;

public class TenantManagementMainPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public static JPanel pnl1;
	public static JPanel pnl2;
	public static JPanel pnl3;
	public static JPanel pnl4;
	
	public TenantManagementMainPanel(int id){
		
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

		TenantManagementPanel panel = new TenantManagementPanel(id);
		pnl1.add(panel);			
		
		setLayout(new SpringLayout());
		SpringLayout spLayout;
        try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }
        Spring Width1 = null;
        Width1 = Spring.constant(200);
        Spring Width2 = Spring.constant(600);
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
