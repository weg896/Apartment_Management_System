package AccountManagementGUI;

import javax.swing.*;

/**
 * the main panel of client side, to contain all other client side panels
 * 
 */
public class ModifyAccountPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/*ClientSideFrame will expand horizontally, having 3 big panels inside the main panel ClientSidePanel*/
	public static JPanel pnl1;//left panel, for displaying menu
	
	public ModifyAccountPanel(int userID,boolean isStaff){
	    //try { img = ImageIO.read(new File("src/image/bgClientSide.jpg")); }
	   // catch (IOException e) { e.printStackTrace(); }
		
		pnl1 = new JPanel();
		pnl1.setLayout(new BoxLayout(pnl1, BoxLayout.PAGE_AXIS));
		add(pnl1);
		
		if(!isStaff){
			ModifyTenantAccountPanel panel= new ModifyTenantAccountPanel(userID);
			pnl1.add(panel);
		}else{
			ModifyStaffAccountPanel panel= new ModifyStaffAccountPanel(userID);
			pnl1.add(panel);
		}	
		
		setLayout(new SpringLayout());
		SpringLayout spLayout;
        try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }
        Spring Width1 = null;//width pf pnl1
        if (userID == 0){Width1 = Spring.constant(0);}
        else {Width1 = Spring.constant(600);}
        Spring Height = Spring.constant(500);//height of pnl1, pnl2 and pnl3
        SpringLayout.Constraints cons1 = spLayout.getConstraints(pnl1);
        cons1.setX(Spring.constant(0));
        cons1.setY(Spring.constant(0));
        cons1.setWidth(Width1);
        cons1.setHeight(Height);
		
	}
	
   /* public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
    }*/
}
