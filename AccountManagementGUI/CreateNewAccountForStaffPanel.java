package AccountManagementGUI;


import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JComboBox;

import MainMenuGUI.DisplayFrame;
import MainMenuGUI.MenuButton;
import MainMenuGUI.SplitString;
import MainMenuGUI.ValueEntryPanel;

import java.util.Iterator;
import java.util.LinkedList;

import commands.*;
import containers.BuildingsList;
import entities.BuildingData;
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CreateNewAccountForStaffPanel extends JPanel{
	
	private ValueEntryPanel FirstNamePanel;
	
	private ValueEntryPanel LastNamePanel;
	
	private ValueEntryPanel phonePanel;
	
	private ValueEntryPanel AddressPanel;
	
	
	private ValueEntryPanel emailPanel;
	
	
	//0-phone,   1- email
	int preferContact;

	private JTextArea error = null;
	
	private JComboBox boxPreferContact;
	
	private JComboBox building;

	/**
	 * 
	 */
	public CreateNewAccountForStaffPanel(int permission)
	{	
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(Box.createVerticalGlue());
		
		
		JLabel prompt = new JLabel("Create Account");
		prompt.setMaximumSize(prompt.getPreferredSize());
		add(prompt);
		prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());
		
		FirstNamePanel = new ValueEntryPanel("First Name",20,120,200);
		FirstNamePanel.setMaximumSize(FirstNamePanel.getPreferredSize());
		add(FirstNamePanel);
		FirstNamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());

		LastNamePanel = new ValueEntryPanel("Last Name",20,120,200);
		LastNamePanel.setMaximumSize(LastNamePanel.getPreferredSize());
		add(LastNamePanel);
		LastNamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());
		
		AddressPanel = new ValueEntryPanel("Home Address",20,120,200);
		AddressPanel.setMaximumSize(AddressPanel.getPreferredSize());
		add(AddressPanel);
		AddressPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());
		

		phonePanel = new ValueEntryPanel("Phone Number",20,120,200);
		phonePanel.setMaximumSize(phonePanel.getPreferredSize());
		add(phonePanel);
		phonePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());
		
		emailPanel = new ValueEntryPanel("Email Address",20,120,200);
		emailPanel.setMaximumSize(emailPanel.getPreferredSize());
		add(emailPanel);
		emailPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());
		
		JLabel prompt1 = new JLabel("Prefered Contact Type:");
		prompt1.setMaximumSize(prompt1.getPreferredSize());
		add(prompt1);
		prompt1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		String[] contactType = {"phone","email"};
		boxPreferContact = new JComboBox(contactType);
		boxPreferContact.setMaximumSize(boxPreferContact.getPreferredSize());
		add(boxPreferContact);
		boxPreferContact.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());
		
		JLabel prompt2 = new JLabel("Work On Which Building:");
		prompt2.setMaximumSize(prompt2.getPreferredSize());
		add(prompt2);
		prompt2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		LinkedList<BuildingData> lst = null;
		try {
			lst = BuildingsList.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Iterator<BuildingData> ite = lst.iterator();
		int i = 0;
		BuildingData[] buildings = new BuildingData[lst.size()];
		while(ite.hasNext()){
			BuildingData current = ite.next();
		    buildings[i] = current;
		    i++;
		}
		building = new JComboBox(buildings);
		building.setMaximumSize(building.getPreferredSize());
		add(building);
		building.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());
		
		
		MenuButton Submit = new MenuButton("Submit",100);
		Submit.setForeground(Color.WHITE);
		Submit.addMouseListener(new SubmitMouseListener(permission));
		Submit.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Submit);
		add(Box.createVerticalGlue());
		
	}

	/**
	 * The listener for the press of the submit button.
	 * When it happens, obtain the data values from the fields, and
	 * try to login.  Afterwards, create and make visible the window
	 * with the main options for the user.
	 */
	private class SubmitMouseListener implements MouseListener
	{
		private int defpermission;
		private String title;
		public SubmitMouseListener(int permission){
			defpermission = permission;
		}
	
		public void mouseClicked (MouseEvent event)
		{
			if (error != null)
			{
				remove(error);  // remove error from previous submission
				error = null;
			}
			
			String fName = FirstNamePanel.getValueAsString();
			String LName = LastNamePanel.getValueAsString();
			long phone = phonePanel.getValueAsLong();
			String email = emailPanel.getValueAsString();
			String address = AddressPanel.getValueAsString();
			
			if(boxPreferContact.getSelectedItem() == "phone"){
				preferContact = 0;
			}
			else{
				preferContact = 1;
			}
				
			if(defpermission == 0){
				title = "manager";
			}else{
				title = "staff";
			}
			
			BuildingData data =(BuildingData) building.getSelectedItem();
			int buildingID = data.getId();
			CreateNewAccountCommand ca = new CreateNewAccountCommand();
			ca.createAccountForStaff(fName, LName, phone, preferContact, 
					email, defpermission, buildingID, title, address);
			
			if (ca.wasSuccessful())
			{
				
				getTopLevelAncestor().setVisible(true);
				String info = "<html>Congratulations!" + "<br>" + "Here are new account username and password:" + "<br>"
				+ "username: " + ca.getNewUserName() + "<br>" + "password: " + ca.getNewPassword() + "<html>";
				DisplayFrame frame = new DisplayFrame("Congratulation", info);
				frame.setLocation(300, 300);
				frame.setVisible(true);
		
			}
			else
			{
				/* Divide the error message into lines short enough to fit in the 
				 * window, and store the message in the error text area. */
				error = new JTextArea(SplitString.at(ca.getErrorMessage(), 50));
				error.setMaximumSize(error.getPreferredSize());
				add(error);
				error.setAlignmentX(Component.CENTER_ALIGNMENT);
				add(Box.createVerticalGlue());
				revalidate();
			}
		}

	

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
	public static final long serialVersionUID = 1;
}
