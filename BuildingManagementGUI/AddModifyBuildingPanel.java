package BuildingManagementGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import MainMenuGUI.MenuButton;
import MainMenuGUI.ValueEntryPanel;

import entities.BuildingData;
import commands.BuildingsCommand;

public class AddModifyBuildingPanel extends JPanel
{
	private static final long serialVersionUID = 1;
	private SpringLayout.Constraints cons = null;

	
	public AddModifyBuildingPanel(final BuildingData currentBuilding)
	{		
		setLayout(new SpringLayout());
		SpringLayout spLayout;
		try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }
		
		final ValueEntryPanel nameTextBox = new ValueEntryPanel("Name",10,120,200);
        cons = spLayout.getConstraints(nameTextBox);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(40));
		add(nameTextBox);

		final ValueEntryPanel addressTextBox = new ValueEntryPanel("Address",10,120,200);
        cons = spLayout.getConstraints(addressTextBox);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(80));
		add(addressTextBox);
		
		MenuButton saveButton = new MenuButton("Save",60);
        cons = spLayout.getConstraints(saveButton);
        cons.setX(Spring.constant(120));
        cons.setY(Spring.constant(120));
		add(saveButton);
		
		if (currentBuilding != null)
		{
			nameTextBox.textField.setText(currentBuilding.getName());
			addressTextBox.textField.setText(currentBuilding.getAddress());
		}
		
		if (currentBuilding != null)
		{
			final BuildingsCommand commands = new BuildingsCommand(currentBuilding);

			MenuButton deleteButton = new MenuButton("Delete",60);
			
			 cons = spLayout.getConstraints(deleteButton);
		     cons.setX(Spring.constant(200));
		     cons.setY(Spring.constant(120));
			
			add(deleteButton);
			
			deleteButton.addActionListener(new ActionListener(){
				public void actionPerformed (ActionEvent event){
					commands.removeBuilding();
				}
		    });
			
		}
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent event){
				if (currentBuilding != null)
				{
					final BuildingsCommand commands = new BuildingsCommand(currentBuilding);
					commands.modifyBuilding(nameTextBox.textField.getText(), addressTextBox.textField.getText());
				}
				else
				{
					final BuildingsCommand commands = new BuildingsCommand();
					commands.addBuilding(nameTextBox.textField.getText(), addressTextBox.textField.getText());
				}
			}
	    });
	}
}
