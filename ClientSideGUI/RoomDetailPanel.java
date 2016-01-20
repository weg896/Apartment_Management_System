package ClientSideGUI;

import MainMenuGUI.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

/**
 * the panel that displays all available detailed information of a selected room @ClientSide
 * @author YUQING TAN
 */
public class RoomDetailPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private SpringLayout.Constraints cons = null;

	public RoomDetailPanel(final int userID, String building, int roomnum, int area, int cost, int bedrooms, int tenants){
		
		setOpaque(false);
		setLayout(new SpringLayout());
		SpringLayout spLayout;
        try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }

        JLabel lbTitle = new JLabel("Room Detail");
        lbTitle.setFont(new Font("Serif", Font.BOLD, 20));
		cons = spLayout.getConstraints(lbTitle);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(20));
        add(lbTitle);
        
        ValueDisplayPanel pnBldn = new ValueDisplayPanel("Building:",building,150); 
		cons = spLayout.getConstraints(pnBldn);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(80));
        add(pnBldn);

        ValueDisplayPanel pnRoom = new ValueDisplayPanel("Room Number:",roomnum+"",150); 
		cons = spLayout.getConstraints(pnRoom);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(120));
        add(pnRoom);

        ValueDisplayPanel pnArea = new ValueDisplayPanel("Square Footage:",area+" (ft)",150); 
		cons = spLayout.getConstraints(pnArea);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(160));
        add(pnArea);

        ValueDisplayPanel pnCost = new ValueDisplayPanel("Rate:","$"+cost+" / month",150); 
		cons = spLayout.getConstraints(pnCost);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(200));
        add(pnCost);

        ValueDisplayPanel pnBedrooms = new ValueDisplayPanel("<html>Number<br>of Bedrooms:<html>",bedrooms+"",150); 
		cons = spLayout.getConstraints(pnBedrooms);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(240));
        add(pnBedrooms);

        ValueDisplayPanel pnTenants = new ValueDisplayPanel("<html>Current Number<br>of Tenants:<html>",tenants+"",150); 
		cons = spLayout.getConstraints(pnTenants);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(280));
        add(pnTenants);

        MenuButton btCloseDetail = new MenuButton("Close Detail",120);
		cons = spLayout.getConstraints(btCloseDetail);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(320));
        btCloseDetail.addActionListener(new ActionListener(){
        	public void actionPerformed (ActionEvent event) {
				ClientSidePanel.pnl3.removeAll();
				if (userID == 0){ ClientSidePanel.pnl3.getTopLevelAncestor().setSize(640, 600); }
				else{ ClientSidePanel.pnl3.getTopLevelAncestor().setSize(840, 600); }
				ClientSidePanel.pnl3.getTopLevelAncestor().validate();
				ClientSidePanel.pnl3.getTopLevelAncestor().repaint();
        	}
        });
        add(btCloseDetail);   
	}
}
