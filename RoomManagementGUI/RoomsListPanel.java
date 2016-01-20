package RoomManagementGUI;

import MainMenuGUI.MenuButton;
import commands.*;
//import startup.Database;

import javax.swing.*;

import containers.RoomsList;
import entities.BuildingData;
import entities.RoomData;

import java.awt.event.*;
import java.util.Iterator;
import java.util.LinkedList;

public class RoomsListPanel extends JPanel{

	private static final long serialVersionUID = 1;


	JTextArea error = null;
	
	private SpringLayout.Constraints cons = null;
	
	public RoomsListPanel(final BuildingData building)
	{
		
		setLayout(new SpringLayout());
		SpringLayout spLayout;
        try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }

        JLabel buildingName = new JLabel("Room Number");
        JLabel roomNumber = new JLabel("Building Name");
		
		cons = spLayout.getConstraints(buildingName);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(10));
		cons = spLayout.getConstraints(roomNumber);
        cons.setX(Spring.constant(120));
        cons.setY(Spring.constant(10));

        add(buildingName);
        add(roomNumber);
        int rows = 1;
        
        LinkedList<RoomData> rooms;
		try {
			rooms = RoomsList.getInstance();
			

	        Iterator<RoomData> iter = rooms.iterator();
	        
	        while(iter.hasNext())
	        {
	        	final RoomData currentRoom = iter.next();
	        	if (currentRoom.getBuildingId() == building.getId())
	        	{
	        		buildingName = new JLabel(currentRoom.getRoomNumber()+"");
		        	roomNumber = new JLabel(building.getName());
		        	
		        	cons = spLayout.getConstraints(buildingName);
			        cons.setX(Spring.constant(20));
			        cons.setY(Spring.constant(10 + rows*30));
					cons = spLayout.getConstraints(roomNumber);
			        cons.setX(Spring.constant(120));
			        cons.setY(Spring.constant(10 + rows*30));
			        
			        add(buildingName);
			        add(roomNumber);
			        
			        MenuButton viewRoomsInfoButton = new MenuButton("Room Info",100);
					MenuButton btDel = new MenuButton("Delete Room",120);
					
					
					cons = spLayout.getConstraints(viewRoomsInfoButton);
			        cons.setX(Spring.constant(250));
			        cons.setY(Spring.constant(3 + rows*30));
					cons = spLayout.getConstraints(btDel);
			        cons.setX(Spring.constant(400));
			        cons.setY(Spring.constant(3 + rows*30));

			        add(viewRoomsInfoButton);
					add(btDel);
					rows++;
					
					btDel.addActionListener(new ActionListener(){
						public void actionPerformed (ActionEvent event){
							RoomsCommands command = new RoomsCommands(currentRoom);
							command.removeRoom();
						}
					});
					
					viewRoomsInfoButton.addActionListener(new ActionListener(){
						public void actionPerformed (ActionEvent event)
						{
							AddModifyRoomPanel p = new AddModifyRoomPanel(currentRoom, building);
							getTopLevelAncestor().setSize(1200, 500);
							RoomsManagementMainPanel.pnl3.removeAll();
							RoomsManagementMainPanel.pnl3.add(new JLabel(""));
							RoomsManagementMainPanel.pnl3.add(p);
							getTopLevelAncestor().validate();
							getTopLevelAncestor().repaint();
						}
				    });
	        	}
	        }
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
