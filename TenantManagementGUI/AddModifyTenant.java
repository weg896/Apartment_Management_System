package TenantManagementGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import MainMenuGUI.MenuButton;

import entities.*;
import commands.TenantCommands;
import containers.RoomsList;

public class AddModifyTenant extends JPanel
{
	private static final long serialVersionUID = 1;
	private SpringLayout.Constraints cons = null;

	@SuppressWarnings("unchecked")
	public AddModifyTenant(final TenantData tenant)
	{
		setLayout(new SpringLayout());
		SpringLayout spLayout;
		try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }
		
		JLabel firstNameLabel = new JLabel();
		JLabel lastNameLabel = new JLabel();
		JLabel buildingLabel = new JLabel();
		JLabel phoneNumberLabel = new JLabel();
		JLabel emailLabel = new JLabel();
		JLabel preferedContactLabel = new JLabel();
		JLabel preferedContactTextLabel = new JLabel();
		
		
		JLabel fnLabel = new JLabel();
		JLabel lnLabel = new JLabel();
		JLabel pLabel = new JLabel();
		JLabel eLabel = new JLabel();

		fnLabel.setText(tenant.getFirstName());
		lnLabel.setText(tenant.getLastName());
		pLabel.setText(tenant.getPhoneNumber()+"");
		eLabel.setText(tenant.getEmailAddress());


		firstNameLabel.setText("First Name");
		lastNameLabel.setText("Last Name");
		buildingLabel.setText("Room");
		phoneNumberLabel.setText("Phone number");
		emailLabel.setText("Email");
		preferedContactLabel.setText("Preferred Contact Type");		
		preferedContactTextLabel.setText(tenant.getContactType());		
		

		@SuppressWarnings("rawtypes")
		final JComboBox roomBuildingComboBox = new JComboBox();
		
		MenuButton saveButton = new MenuButton("Save",60);
		
		//populates combobox
		LinkedList<RoomData> rooms;
		try {
			rooms = RoomsList.getInstance();
			
			Iterator<RoomData> iter = rooms.iterator();
			while(iter.hasNext())
			{
				RoomData currentRoom = iter.next();
				roomBuildingComboBox.addItem(currentRoom.getRoomListData());
			}
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
        cons = spLayout.getConstraints(firstNameLabel);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(30));
        
        cons = spLayout.getConstraints(fnLabel);
        cons.setX(Spring.constant(225));
        cons.setY(Spring.constant(30));
        
        cons = spLayout.getConstraints(lastNameLabel);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(60));
        
        cons = spLayout.getConstraints(lnLabel);
        cons.setX(Spring.constant(225));
        cons.setY(Spring.constant(60));
        
        cons = spLayout.getConstraints(buildingLabel);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(90));
        
        cons = spLayout.getConstraints(roomBuildingComboBox);
        cons.setX(Spring.constant(225));
        cons.setY(Spring.constant(90));

        cons = spLayout.getConstraints(phoneNumberLabel);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(120));
        
        cons = spLayout.getConstraints(pLabel);
        cons.setX(Spring.constant(225));
        cons.setY(Spring.constant(120));
        
        
        cons = spLayout.getConstraints(emailLabel);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(150));
        
        cons = spLayout.getConstraints(eLabel);
        cons.setX(Spring.constant(225));
        cons.setY(Spring.constant(150));
        
        cons = spLayout.getConstraints(preferedContactLabel);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(180));
        
        cons = spLayout.getConstraints(preferedContactTextLabel);
        cons.setX(Spring.constant(225));
        cons.setY(Spring.constant(180));
        
        cons = spLayout.getConstraints(saveButton);
        cons.setX(Spring.constant(150));
        cons.setY(Spring.constant(210));
        

		add(firstNameLabel);
		add(fnLabel);
		add(lastNameLabel);
		add(lnLabel);
		add(buildingLabel);
		add(roomBuildingComboBox);
		add(phoneNumberLabel);
		add(pLabel);
		add(emailLabel);
		add(eLabel);
		add(preferedContactLabel);
		add(preferedContactTextLabel);
		add(saveButton);
		
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent event){				
				String room = String.valueOf(roomBuildingComboBox.getSelectedItem());
			
				LinkedList<RoomData> roomList;
				try {
					roomList = RoomsList.getInstance();
					Iterator<RoomData> iter = roomList.iterator();
					
					while (iter.hasNext())
					{
						RoomData currentRoom = iter.next();

						if (room.equals(currentRoom.getRoomListData()))
						{
							try
							{
								tenant.setRoomId(currentRoom.getRoomId());
							} catch(Exception e)
							{
								
							}
							break;
						}
					}
					
					TenantCommands modify = new TenantCommands();
					modify.modifyTenantRoom(tenant);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
			}
	    });
	}
}
