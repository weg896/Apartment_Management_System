package RoomManagementGUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import MainMenuGUI.DisplayFrame;
import MainMenuGUI.MenuButton;
import MainMenuGUI.ValueEntryPanel;

import entities.*;
import commands.RoomsCommands;

public class AddModifyRoomPanel extends JPanel
{
	private static final long serialVersionUID = 1;
	private SpringLayout.Constraints cons = null;

	public AddModifyRoomPanel(final RoomData currentRoom, final BuildingData building)
	{
		setLayout(new SpringLayout());
		SpringLayout spLayout;
		try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }
		
		final ValueEntryPanel roomNumberTextBox = new ValueEntryPanel("Room Number",20,200,100);
        cons = spLayout.getConstraints(roomNumberTextBox);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(40));
		add(roomNumberTextBox);

		final ValueEntryPanel squareFeetTextBox = new ValueEntryPanel("Square Feet",20,200,100);
        cons = spLayout.getConstraints(squareFeetTextBox);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(80));
		add(squareFeetTextBox);

		final ValueEntryPanel bedRoomNumberTextBox = new ValueEntryPanel("Number of Bedrooms",20,200,100);
        cons = spLayout.getConstraints(bedRoomNumberTextBox);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(120));
		add(bedRoomNumberTextBox);

		final ValueEntryPanel totalTenantsTextBox = new ValueEntryPanel("Total Capacity of Tenants",20,200,100);
        cons = spLayout.getConstraints(totalTenantsTextBox);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(160));
		add(totalTenantsTextBox);

		final ValueEntryPanel costTextBox = new ValueEntryPanel("Cost of Appartment Room",20,200,100);
        cons = spLayout.getConstraints(costTextBox);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(200));
		add(costTextBox);

		MenuButton saveButton = new MenuButton("Save",60);
        cons = spLayout.getConstraints(saveButton);
        cons.setX(Spring.constant(200));
        cons.setY(Spring.constant(240));
		add(saveButton);
		
		if (currentRoom != null)
		{
			roomNumberTextBox.textField.setText(currentRoom.getRoomNumber()+"");
			squareFeetTextBox.textField.setText(currentRoom.getSquareFootage()+"");
			bedRoomNumberTextBox.textField.setText(currentRoom.getBedroomNumber()+"");
			totalTenantsTextBox.textField.setText(currentRoom.getTenantNumber()+"");
			costTextBox.textField.setText(currentRoom.getCost()+"");

		}

		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent event){
				
				if (currentRoom != null)
				{
					final RoomsCommands commands = new RoomsCommands(currentRoom);
					try
					{
						commands.modifyRoom(Integer.parseInt(roomNumberTextBox.textField.getText()), Integer.parseInt(squareFeetTextBox.textField.getText()), Integer.parseInt(bedRoomNumberTextBox.textField.getText()), Integer.parseInt(costTextBox.textField.getText()), Integer.parseInt(totalTenantsTextBox.textField.getText()));
					} catch (Exception e)
					{
						DisplayFrame frame = new DisplayFrame("Error:", "<html>invalid input<html>");
						frame.setLocation(400, 400);
						frame.setVisible(true);
					}
				}
				else
				{
					final RoomsCommands commands = new RoomsCommands();
					try
					{
						commands.addRoom(Integer.parseInt(roomNumberTextBox.textField.getText()), Integer.parseInt(squareFeetTextBox.textField.getText()), Integer.parseInt(bedRoomNumberTextBox.textField.getText()), Integer.parseInt(costTextBox.textField.getText()), Integer.parseInt(totalTenantsTextBox.textField.getText()), building.getId());
					} catch (Exception e)
					{
						DisplayFrame frame = new DisplayFrame("Error:", "<html>invalid input<html>");
						frame.setLocation(400, 400);
						frame.setVisible(true);
					}
					
				}
			}
	    });
	}
}
