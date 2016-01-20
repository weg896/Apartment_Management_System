package ClientSideGUI;

import MainMenuGUI.*;
import entities.*;
import commands.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * the panel that displays all available rooms in selected building @ClientSide
 * @author YUQING TAN
 */
public class RoomDisplayPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private String errorMessage = null;
	JTextArea error = null;
	private SpringLayout.Constraints cons = null;
	
	public RoomDisplayPanel(final int userID, final LinkedList<RoomData> availRoom){
		
		setOpaque(false);
		setLayout(new SpringLayout());
		SpringLayout spLayout;
        try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }

        JLabel lbRoomNum = new JLabel("Room Number");
        JLabel lbPrice = new JLabel("Price ($)");
		cons = spLayout.getConstraints(lbRoomNum);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(10));
		cons = spLayout.getConstraints(lbPrice);
        cons.setX(Spring.constant(160));
        cons.setY(Spring.constant(10));
        add(lbRoomNum);
        add(lbPrice);
        int rows = 1;
        
        Iterator<RoomData> itrRoom = availRoom.iterator(); 
        RoomData objRoom = null;
		while(itrRoom.hasNext()){
			objRoom = itrRoom.next();
			lbRoomNum = new JLabel(objRoom.getRoomNumber()+""); 
			lbPrice = new JLabel(objRoom.getCost()+""); 
			cons = spLayout.getConstraints(lbRoomNum);
		    cons.setX(Spring.constant(20));
		    cons.setY(Spring.constant(10 + rows*30));
			cons = spLayout.getConstraints(lbPrice);
		    cons.setX(Spring.constant(160));
		    cons.setY(Spring.constant(10 + rows*30));
			add(lbRoomNum);
			add(lbPrice);
			
			MenuButton btRoomDetail = new MenuButton("View Detail",100);
			MenuButton btApply = new MenuButton("Apply",60);
			final String building = objRoom.getBuildingName();
			final int roomnum = objRoom.getRoomNumber();
			final int area = objRoom.getSquareFootage();
			final int cost = objRoom.getCost();
			final int bedrooms = objRoom.getBedroomNumber();
			final int tenants = objRoom.getTenantNumber();
			
			btRoomDetail.addActionListener(new ActionListener(){
	        	public void actionPerformed (ActionEvent event) {
					RoomDetailPanel pnRoomDetail = new RoomDetailPanel(userID, building, roomnum, area, cost, bedrooms, tenants);
					ClientSidePanel.pnl3.removeAll();
					ClientSidePanel.pnl3.add(pnRoomDetail);
					if (userID == 0){ getTopLevelAncestor().setSize(1000, 600); }
					else{ getTopLevelAncestor().setSize(1200, 600); }
					getTopLevelAncestor().validate();
					getTopLevelAncestor().repaint();
				}
		    });

			btApply.addActionListener(new ActionListener(){
	        	public void actionPerformed (ActionEvent event) {
					if (0 == userID)	//still a guest
					{
						String info = "<html>Sorry!" + "<br>" + 
						"You are not allowed to apply for room as a guest." + "<br>" +
						"Please REGISTER or LOGIN first.<html>";
						DisplayFrame frame = new DisplayFrame("Error", info);
						frame.setLocation(500, 500);
						frame.setVisible(true);
						
						TenantSignUpPanel pnSignUp = new TenantSignUpPanel(true, roomnum, building);
						ClientSidePanel.pnl3.removeAll();
						ClientSidePanel.pnl3.add(pnSignUp);
						getTopLevelAncestor().setSize(1000, 600);
						getTopLevelAncestor().validate();
						getTopLevelAncestor().repaint();
					}
					else
					{
						String msgContent = "Tenant Request for Applying Room"+
								";\n User ID: "+userID+
								";\n Building: "+building+
								";\n Room Number: "+roomnum+";";
						NoticeSendCommand sr = new NoticeSendCommand();
						sr.Send(7, "Tenant Request for Applying Room", msgContent, userID);
						// 7 is the msg type 'request for applying room'
						if (sr.wasSuccessful())
						{
							TenantProfileCommand cmdTenant = new TenantProfileCommand();
							TenantData dataTenant = cmdTenant.getTenantProfile(userID);
							String info = "<html>Confirmation" +
									"<br>You (" + dataTenant.getFirstName() + " " + dataTenant.getLastName() + ") " +
									"have started application on room #" + roomnum + " in " + building + "." +
									"<br>Your Request for Applying Room is sent to administrators successfully.<html>";
							DisplayFrame frame = new DisplayFrame("Confirmation", info);
							frame.setLocation(200, 200);
							frame.setVisible(true);
						}
						else
						{
							/* Divide the error message into lines short enough to fit in the 
							 * window, and store the message in the error text area. */
							error = new JTextArea(SplitString.at(errorMessage, 50));
							error.setMaximumSize(error.getPreferredSize());
							add(error);
							error.setAlignmentX(Component.CENTER_ALIGNMENT);
							add(Box.createVerticalGlue());
							revalidate();
						}
					}
				}
		    });
			
			cons = spLayout.getConstraints(btRoomDetail);
		    cons.setX(Spring.constant(240));
		    cons.setY(Spring.constant(0 + rows*30));
			cons = spLayout.getConstraints(btApply);
		    cons.setX(Spring.constant(360));
		    cons.setY(Spring.constant(0 + rows*30));
			add(btRoomDetail);
			add(btApply);
			rows++;
		}
		
        SpringLayout.Constraints pCons = spLayout.getConstraints(this);
        pCons.setConstraint(SpringLayout.SOUTH,
                            Spring.constant(10 + rows*30));
        pCons.setConstraint(SpringLayout.EAST,
                            Spring.constant(555));
	}
}
