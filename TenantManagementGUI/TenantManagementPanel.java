package TenantManagementGUI;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
//import javax.swing.JButton;
import javax.swing.JPanel;

import MainMenuGUI.MainMenuFrame;
import MainMenuGUI.MenuButton;

public class TenantManagementPanel extends JPanel {
	
	public TenantManagementPanel(final int id){
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		add(Box.createVerticalGlue());
		/*
		// Create a building add button
		JButton addBuildingButton = new JButton("Add Tenant");
		addBuildingButton.setMaximumSize(addBuildingButton.getPreferredSize());
		add(addBuildingButton);
		addBuildingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		addBuildingButton.addActionListener(new 
			ActionListener(){
					public void actionPerformed (ActionEvent event){						
						AddModifyBuildingPanel buildingsPanel = new AddModifyBuildingPanel(null);
						getTopLevelAncestor().setSize(850, 500);
						clearPanels();
						TenantManagementMainPanel.pnl2.setLayout(new BorderLayout());
						TenantManagementMainPanel.pnl2.add(TenantManagementMainPanel.pnl4, BorderLayout.CENTER);
						TenantManagementMainPanel.pnl4.add(buildingsPanel, BorderLayout.CENTER);
						
						getTopLevelAncestor().validate();
						getTopLevelAncestor().repaint();
					}
		    });
		add(Box.createVerticalGlue());
		*/
		//add create building Manage button
		MenuButton manageBuildingButton = new MenuButton("Manage Tenants",180);
		manageBuildingButton.setMaximumSize(manageBuildingButton.getPreferredSize());
		add(manageBuildingButton);
		manageBuildingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		manageBuildingButton.addActionListener(new 
			ActionListener(){
					public void actionPerformed (ActionEvent event){
						//CreatAccountChooseIdentityPanel pnSearchRoom = new CreatAccountChooseIdentityPanel();
						
						TenantListPanel tenantListPanel = new TenantListPanel();
						getTopLevelAncestor().setSize(850, 500);
						clearPanels();
						TenantManagementMainPanel.pnl2.setLayout(new BorderLayout());
						TenantManagementMainPanel.pnl2.add(TenantManagementMainPanel.pnl4, BorderLayout.CENTER);
						TenantManagementMainPanel.pnl4.add(tenantListPanel, BorderLayout.CENTER);
						
						getTopLevelAncestor().validate();
						getTopLevelAncestor().repaint();
					}
		    });
		add(Box.createVerticalGlue());
		
		MenuButton backButton = new MenuButton("Back to Main Menu",180);
		backButton.setMaximumSize(backButton.getPreferredSize());
		add(backButton);
		backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		backButton.addActionListener(new 
			ActionListener(){
					public void actionPerformed (ActionEvent event){
						//CreatAccountChooseIdentityPanel pnSearchRoom = new CreatAccountChooseIdentityPanel();
						getTopLevelAncestor().setVisible(false);
						MainMenuFrame frame = new MainMenuFrame(id);
						frame.setLocation(300, 300);
						frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
						frame.setVisible(true);		
					}
		    });
		add(Box.createVerticalGlue());
	}
	
	private void clearPanels(){
	//	AccountManagementMainPanel.pnl2.removeAll();
		TenantManagementMainPanel.pnl3.removeAll();
		TenantManagementMainPanel.pnl4.removeAll();
	}
	
	public static final long serialVersionUID = 1;
}
