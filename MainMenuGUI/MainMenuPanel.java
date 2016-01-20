package MainMenuGUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import SchedulerGUI.*;
import TenantManagementGUI.*;
import BuildingManagementGUI.*;
import ClientSideGUI.*;

import AccountManagementGUI.AccountManagementMainFrame;
import RentalManagementGUI.ManageRentalSideFrame;

public class MainMenuPanel extends JPanel{

	public MainMenuPanel(final int id){
		
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		MenuButton[] buttons = new MenuButton[7];
		
		add(Box.createVerticalGlue());
		
		//add create account management button
		buttons[0] = new MenuButton("Account Management",180);
		buttons[0].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				getTopLevelAncestor().setVisible(false);
				AccountManagementMainFrame frame = new AccountManagementMainFrame(id);
				frame.setLocation(300, 300);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				
			}
	    });

		
		//add tenant Management button
		buttons[1] = new MenuButton("Tenant Management",180);
		buttons[1].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				 TenantManagementMainFrame tenantListFrame;
					try {
						tenantListFrame = new TenantManagementMainFrame(id);
						tenantListFrame.setLocation(300, 300);
						 tenantListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						 tenantListFrame.setVisible(true);
						 getTopLevelAncestor().setVisible(false);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 
			}				
	    });
	
		buttons[2] = new MenuButton("Building Management",180);
		buttons[2].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				BuildingsManagementMainFrame buildingListFrame;
				try {
					buildingListFrame = new BuildingsManagementMainFrame(id);
					buildingListFrame.setLocation(300, 300);
					buildingListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					buildingListFrame.setVisible(true);
					getTopLevelAncestor().setVisible(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    });
		
		buttons[3] = new MenuButton("Rent Management",180);
		buttons[3].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				getTopLevelAncestor().setVisible(false);
				ManageRentalSideFrame frame = new ManageRentalSideFrame(id);
				frame.setLocation(300, 300);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
	    });
		
		buttons[4] = new MenuButton("Client Side",180);
		buttons[4].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				getTopLevelAncestor().setVisible(true);
				ClientSideFrame frame = new ClientSideFrame(id);
				frame.setLocation(300, 300);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
	    });
		
		buttons[5] = new MenuButton("Scheduler",180);
		buttons[5].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				NotificationTestFrame frame = new NotificationTestFrame(id);
				frame.setLocation(300, 300);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
	    });	
		
		buttons[6] = new MenuButton("Log out",180);
		buttons[6].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				getTopLevelAncestor().setVisible(false);
				LoginFrame frame = new LoginFrame();
				frame.setLocation(300, 300);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
	    });	
		
		
		for (int i=0; i<7; i++){
	        buttons[i].setMaximumSize(new Dimension(180,30));
	        buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
	        add(buttons[i]);
			add(Box.createVerticalGlue());
		}
	}
	
	public static final long serialVersionUID = 1;
}
