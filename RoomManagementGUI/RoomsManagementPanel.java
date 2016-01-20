package RoomManagementGUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
//import javax.swing.JButton;
import javax.swing.JPanel;

import MainMenuGUI.MenuButton;


import entities.*;

public class RoomsManagementPanel extends JPanel {
	
	public RoomsManagementPanel(final BuildingData building)
	{
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		add(Box.createVerticalGlue());

		// Create a room add button
		MenuButton addBuildingButton = new MenuButton("Add Room",150);
		addBuildingButton.setMaximumSize(addBuildingButton.getPreferredSize());
		add(addBuildingButton);
		addBuildingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		addBuildingButton.addActionListener(new 
			ActionListener(){
					public void actionPerformed (ActionEvent event){						
						AddModifyRoomPanel roomsPanel = new AddModifyRoomPanel(null, building);
						getTopLevelAncestor().setSize(850, 500);
						clearPanels();
						RoomsManagementMainPanel.pnl2.setLayout(new BorderLayout());
						RoomsManagementMainPanel.pnl2.add(RoomsManagementMainPanel.pnl4, BorderLayout.CENTER);
						RoomsManagementMainPanel.pnl4.add(roomsPanel, BorderLayout.CENTER);
						
						getTopLevelAncestor().validate();
						getTopLevelAncestor().repaint();
					}
		    });
		add(Box.createVerticalGlue());
		
		//add create room Manage button
		MenuButton manageBuildingButton = new MenuButton("Manage Rooms",150);
		manageBuildingButton.setMaximumSize(manageBuildingButton.getPreferredSize());
		add(manageBuildingButton);
		manageBuildingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		manageBuildingButton.addActionListener(new 
			ActionListener(){
					public void actionPerformed (ActionEvent event){
						//CreatAccountChooseIdentityPanel pnSearchRoom = new CreatAccountChooseIdentityPanel();
						
						RoomsListPanel roomsPanel = new RoomsListPanel(building);
						getTopLevelAncestor().setSize(850, 500);
						clearPanels();
						RoomsManagementMainPanel.pnl2.setLayout(new BorderLayout());
						RoomsManagementMainPanel.pnl2.add(RoomsManagementMainPanel.pnl4, BorderLayout.CENTER);
						RoomsManagementMainPanel.pnl4.add(roomsPanel, BorderLayout.CENTER);
						
						getTopLevelAncestor().validate();
						getTopLevelAncestor().repaint();
					}
		    });
		add(Box.createVerticalGlue());
	}
	
	private void clearPanels(){
	//	AccountManagementMainPanel.pnl2.removeAll();
		RoomsManagementMainPanel.pnl3.removeAll();
		RoomsManagementMainPanel.pnl4.removeAll();
	}
	
	public static final long serialVersionUID = 1;
}
