package AccountManagementGUI;


import java.sql.*;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import MainMenuGUI.MenuButton;
import MainMenuGUI.ValueEntryPanel;


import startup.Database;

public class SearchAccountForPasswordPanel extends JPanel {
	
	JTextArea error = null;
	
	private ValueEntryPanel usernamePanel;
	
	public SearchAccountForPasswordPanel(){
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(Box.createVerticalGlue());
		
		usernamePanel = new ValueEntryPanel("Username:",20,120,200);
		usernamePanel.setMaximumSize(usernamePanel.getPreferredSize());
		add(usernamePanel);
		
		MenuButton searchButton= new MenuButton("Search",60);
		searchButton.setForeground(Color.WHITE);
		add(searchButton);
		searchButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				if (error != null)
				{
					remove(error);  // remove error from previous search
					error = null;
				}
				String username = usernamePanel.getValueAsString();
				
				ResultSet rs = Database.runGetFromDatabaseSQL("SELECT * FROM users WHERE u_username =  '" + username+ "';");
				
				if(rs != null){
					DisplayAccountForResetPasswordPanel  panel = new DisplayAccountForResetPasswordPanel(rs);
					JScrollPane scPanel = new JScrollPane(panel);
					AccountManagementMainPanel.pnl4.removeAll();
					AccountManagementMainPanel.pnl4.add(scPanel);
					AccountManagementMainPanel.pnl4.validate();
					AccountManagementMainPanel.pnl4.repaint();
				}
			}
	    });
	}
	
			public static final long serialVersionUID = 1;
	
}

