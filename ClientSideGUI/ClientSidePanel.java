package ClientSideGUI;

import MainMenuGUI.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * the main panel of @ClientSide, to contain all other client side panels
 * @author YUQING TAN
 */
public class ClientSidePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/*ClientSideFrame will expand horizontally, having 3 big panels inside the main panel ClientSidePanel*/
	public static JPanel pnl1 = null; //left panel, for displaying menu
	public static JPanel pnl2 = null; //middle panel, for displaying main content
	public static JPanel pnl3 = null; //right panel, shows optionally, for displaying details
	public static JPanel pnl4 = null; //a panel embedded in pnl2, makes it easier to reload content
	private BufferedImage img = null; //background image
	
	public ClientSidePanel(int userID){
		img = (new ReadImage()).bgClientSide();
		
		pnl1 = new JPanel();
		pnl2 = new JPanel();
		pnl3 = new JPanel();
		pnl4 = new JPanel();
		pnl1.setOpaque(false);
		pnl2.setOpaque(false);
		pnl3.setOpaque(false);
		pnl4.setOpaque(false);
		pnl1.setLayout(new BoxLayout(pnl1, BoxLayout.PAGE_AXIS));
		pnl2.setLayout(new BoxLayout(pnl2, BoxLayout.PAGE_AXIS));
		pnl3.setLayout(new BoxLayout(pnl3, BoxLayout.PAGE_AXIS));
		pnl4.setLayout(new BoxLayout(pnl4, BoxLayout.PAGE_AXIS));		
		add(pnl1);
		add(pnl2);
		add(pnl3);
		
        Spring Width1 = null; //width of pnl1
		if (userID == 0){
			Width1 = Spring.constant(0);
			RoomSearchPanel pnRoomSearch = new RoomSearchPanel(0);
			ClientSidePanel.pnl2.setLayout(new BorderLayout());
			ClientSidePanel.pnl2.add(pnRoomSearch, BorderLayout.NORTH);
			ClientSidePanel.pnl2.add(ClientSidePanel.pnl4, BorderLayout.CENTER);
		}else{
			Width1 = Spring.constant(200);
			TenantMenuPanel pnTenantMenu = new TenantMenuPanel(userID);
			pnl1.add(pnTenantMenu);
		}
		
		setLayout(new SpringLayout());
		SpringLayout spLayout;
        try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }
        Spring Width2 = Spring.constant(600); //width of pnl2
        Spring Width3 = Spring.constant(400); //width of pnl3
        Spring Height = Spring.constant(540); //height of pnl1, pnl2 and pnl3
        SpringLayout.Constraints cons1 = spLayout.getConstraints(pnl1);
        cons1.setX(Spring.constant(0));
        cons1.setY(Spring.constant(30));
        cons1.setWidth(Width1);
        cons1.setHeight(Height);
        SpringLayout.Constraints cons2 = spLayout.getConstraints(pnl2);
        cons2.setX(Width1);
        cons2.setY(Spring.constant(30));
        cons2.setWidth(Width2);
        cons2.setHeight(Height);
        SpringLayout.Constraints cons3 = spLayout.getConstraints(pnl3);
        cons3.setX(Spring.sum(Width1, Width2));
        cons3.setY(Spring.constant(30));
        cons3.setWidth(Width3);
        cons3.setHeight(Height);
	}
	
    public void paintComponent(Graphics g) {
    	if (img != null){
    		super.paintComponent(g);
    		g.drawImage(img, 0, 0, this);
    	}
    }

    public static Container TranspScroPn(Container c){
		JScrollPane r = new JScrollPane(c);
		r.getViewport().setOpaque(false);
		r.setOpaque(false);
		r.setBorder(null);
		return r;
    }
    
    public static void reloadPNL(Container pnl, Container c){
    	pnl.removeAll();
    	pnl.add(c);
    	pnl.validate();
    	pnl.repaint();
    }
}
