package BuildingManagementGUI;

import MainMenuGUI.MenuButton;
import RoomManagementGUI.RoomsManagementMainFrame;
//import commands.*;
//import startup.Database;

import javax.swing.*;

import containers.BuildingsList;
import entities.BuildingData;


import java.awt.event.*;
import java.util.Iterator;
import java.util.LinkedList;

public class BuildingsListPanel extends JPanel{

	private static final long serialVersionUID = 1;


	JTextArea error = null;
	
	private SpringLayout.Constraints cons = null;
	
	public BuildingsListPanel()
	{
		
		setLayout(new SpringLayout());
		SpringLayout spLayout;
        try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }

        JLabel buildingName = new JLabel("Name");
        JLabel buildingAddress = new JLabel("Address");
		
		cons = spLayout.getConstraints(buildingName);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(10));
		cons = spLayout.getConstraints(buildingAddress);
        cons.setX(Spring.constant(150));
        cons.setY(Spring.constant(10));

        add(buildingName);
        add(buildingAddress);
        int rows = 1;
        
        LinkedList<BuildingData> buildingsList;
		try {
			buildingsList = BuildingsList.getInstance();
			

	        Iterator<BuildingData> iter = buildingsList.iterator();
	        
	        while(iter.hasNext())
	        {
	        	final BuildingData currentBuilding = iter.next();
	        	buildingName = new JLabel(currentBuilding.getName());
	        	buildingAddress = new JLabel(currentBuilding.getAddress());
	        	
	        	cons = spLayout.getConstraints(buildingName);
		        cons.setX(Spring.constant(20));
		        cons.setY(Spring.constant(10 + rows*30));
				cons = spLayout.getConstraints(buildingAddress);
		        cons.setX(Spring.constant(150));
		        cons.setY(Spring.constant(10 + rows*30));
		        
		        add(buildingName);
		        add(buildingAddress);
		        
		        MenuButton viewRoomsButton = new MenuButton("View Rooms",100);
		        MenuButton btDel = new MenuButton("Modify/Delete",120);
				
				
				cons = spLayout.getConstraints(viewRoomsButton);
		        cons.setX(Spring.constant(250));
		        cons.setY(Spring.constant(3 + rows*30));
				cons = spLayout.getConstraints(btDel);
		        cons.setX(Spring.constant(400));
		        cons.setY(Spring.constant(3 + rows*30));

		        add(viewRoomsButton);
				add(btDel);
				rows++;
				
				btDel.addActionListener(new ActionListener(){
					public void actionPerformed (ActionEvent event){
						AddModifyBuildingPanel p = new AddModifyBuildingPanel(currentBuilding);
						getTopLevelAncestor().setSize(1200, 500);
						BuildingsManagementMainPanel.pnl3.removeAll();
						BuildingsManagementMainPanel.pnl3.add(new JLabel(""));
						BuildingsManagementMainPanel.pnl3.add(p);
						getTopLevelAncestor().validate();
						getTopLevelAncestor().repaint();
					}
			    });
				
				viewRoomsButton.addActionListener(new ActionListener(){
					public void actionPerformed (ActionEvent event){
						RoomsManagementMainFrame p = new RoomsManagementMainFrame(currentBuilding);
						p.setLocation(300, 300);
						p.setVisible(true);
					}
			    });
	        }
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
