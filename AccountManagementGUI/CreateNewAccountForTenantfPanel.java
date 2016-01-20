package AccountManagementGUI;



import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import startup.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

import commands.*;
import containers.BuildingsList;
import entities.BuildingData;
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CreateNewAccountForTenantfPanel extends JPanel{
	
	private ValueEntryPanel FirstNamePanel;
	
	private ValueEntryPanel LastNamePanel;
	
	private ValueEntryPanel phonePanel;
	
	private ValueEntryPanel emailPanel;
		
	//0-phone,   1- email
	int preferContact;

	private JTextArea error = null;
	
	private JComboBox boxPreferContact;
	
	private JComboBox building;
	
	public JComboBox rooms;
	
	private JPanel a;
	
	private int roomID;
	

	/**
	 * 
	 */
	public CreateNewAccountForTenantfPanel(int permission)
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
		
		
		
		JLabel prompt2 = new JLabel("Current Renting Room:");
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
		building.addActionListener(new SelectedListener());
		add(Box.createVerticalGlue());
		
		
		rooms = new JComboBox();
		rooms.setMaximumSize(rooms.getPreferredSize());
		a = new JPanel();
		JLabel prompt3 = new JLabel("Room Number:");
		prompt3.setMaximumSize(prompt3.getPreferredSize());
		a.add(prompt3);
		prompt2.setAlignmentX(Component.CENTER_ALIGNMENT);
		a.add(rooms);
		add(a);
		rooms.setAlignmentX(Component.CENTER_ALIGNMENT);
		revalidate();
		
		
		MenuButton Submit = new MenuButton("Submit",100);
		Submit.setForeground(Color.WHITE);
		Submit.addMouseListener(new SubmitMouseListener(permission));
		Submit.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Submit);
		add(Box.createVerticalGlue());
	}
	
	//this is listener for selecting building
	private class SelectedListener implements ActionListener{
		public SelectedListener(){
		}
		
		public void actionPerformed (ActionEvent event){
			 JComboBox combo = (JComboBox)event.getSource();
			 BuildingData buildg=(BuildingData) combo.getSelectedItem();
			 
			ResultSet rs1 = Database.runGetFromDatabaseSQL("SELECT distinct ar_room_number FROM apartmentrooms WHERE ar_building_id = "
					+ buildg.getId() +" AND ar_occupy = 'false'  ORDER BY ar_room_number;");		
			try
			{
				a.remove(rooms);
				rooms = new JComboBox();
				while(rs1.next())
				{
						rooms.addItem(String.valueOf(rs1.getInt("ar_room_number")));
				}
				
				rooms.setMaximumSize(rooms.getPreferredSize());
				a.add(rooms);
				rooms.setAlignmentX(Component.CENTER_ALIGNMENT);
				a.revalidate();
				revalidate();			
	
			}
			catch(Exception e)
			{
				System.out.print("Statment failed");
				e.printStackTrace();
			}				
		}
		
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
			
			long phone = phonePanel.getValueAsLong();
			String fName = FirstNamePanel.getValueAsString();
			String LName = LastNamePanel.getValueAsString();
			String email = emailPanel.getValueAsString();
			
			if(boxPreferContact.getSelectedItem() == "phone"){
				preferContact = 0;
			}
			else{
				preferContact = 1;
			}
				
			BuildingData data =(BuildingData) building.getSelectedItem();
			int buildingID = data.getId();
			int roomNum = Integer.valueOf((String) rooms.getSelectedItem());
			
			
			ResultSet rs = Database.runGetFromDatabaseSQL("SELECT ar_id FROM apartmentrooms WHERE ar_room_number =  "
					+ roomNum + " AND ar_building_id = " + buildingID + ";");
			
			try {
				while(rs.next()){
					roomID = rs.getInt("ar_id");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			CreateNewAccountCommand ca = new CreateNewAccountCommand();
			ca.createAccountForTenant(fName, LName, phone, preferContact, email, defpermission, roomID);
			
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
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
	public static final long serialVersionUID = 1;
}




