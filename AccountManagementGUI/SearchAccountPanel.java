package AccountManagementGUI;


import java.sql.*;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Box;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import MainMenuGUI.MenuButton;


import startup.Database;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class SearchAccountPanel extends JPanel {
	
	private JComboBox boxIdentity;
	
	JTextArea error = null;
	

	public SearchAccountPanel(){
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(Box.createVerticalGlue());
		
		JLabel prompt = new JLabel("Select Account Type: ");
		add(prompt);
		
		String[] level = {"manager","tenant","staff"};
		boxIdentity = new JComboBox(level);
		boxIdentity.setMaximumSize(boxIdentity.getPreferredSize());
		add(boxIdentity);
		
		MenuButton searchButton = new MenuButton("Search",60);
		searchButton.setForeground(Color.WHITE);
		add(searchButton);
		searchButton.addMouseListener(new SubmitMouseListener());
	}
	
	private class SubmitMouseListener implements MouseListener
	{
		public void mouseClicked (MouseEvent event)
		{
			if (error != null)
			{
				remove(error);  // remove error from previous search
				error = null;
			}
			if( boxIdentity.getSelectedItem() == "tenant"){
				
				
				ResultSet rs = Database.runGetFromDatabaseSQL("SELECT * FROM users, apartmentrooms, buildings, usersrooms WHERE u_id = ur_user_id " +
						"AND ur_room_id = ar_id AND ar_building_id = b_id AND u_permission_status = 2;");
				if(rs != null){
					DeleteAccountPanel panel = new DeleteAccountPanel(rs,false);
					JScrollPane scPanel = new JScrollPane(panel);
					AccountManagementMainPanel.pnl4.removeAll();
					AccountManagementMainPanel.pnl4.add(scPanel);
					AccountManagementMainPanel.pnl4.validate();
					AccountManagementMainPanel.pnl4.repaint();
				}

			}else if(boxIdentity.getSelectedItem() == "manager"){
				
				ResultSet rs = Database.runGetFromDatabaseSQL("SELECT * FROM users, staff, stafflocations, buildings WHERE s_user_id = " +
						"sl_user_id AND sl_user_id = u_id AND b_id = sl_building_id AND u_permission_status = 0;");
				if(rs != null){
					DeleteAccountPanel panel = new DeleteAccountPanel(rs,true);
					JScrollPane scPanel = new JScrollPane(panel);
					AccountManagementMainPanel.pnl4.removeAll();
					AccountManagementMainPanel.pnl4.add(scPanel);
					AccountManagementMainPanel.pnl4.validate();
					AccountManagementMainPanel.pnl4.repaint();
				}
			}
			else{
				
				ResultSet rs = Database.runGetFromDatabaseSQL("SELECT * FROM users, staff, stafflocations, buildings WHERE s_user_id = " +
						"sl_user_id AND sl_user_id = u_id AND b_id = sl_building_id AND u_permission_status = 1;");
				if(rs != null){
					DeleteAccountPanel panel = new DeleteAccountPanel(rs,true);
					JScrollPane scPanel = new JScrollPane(panel);
					AccountManagementMainPanel.pnl4.removeAll();
					AccountManagementMainPanel.pnl4.add(scPanel);
					AccountManagementMainPanel.pnl4.validate();
					AccountManagementMainPanel.pnl4.repaint();
				}
			}
		
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
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
		
	}
		
		public static final long serialVersionUID = 1;
	
}
