package TenantManagementGUI;

import BuildingManagementGUI.AddModifyBuildingPanel;
import BuildingManagementGUI.BuildingsManagementMainPanel;
import MainMenuGUI.MenuButton;
//import commands.*;
//import startup.Database;

import javax.swing.*;

import containers.TenantList;
import entities.TenantData;


import java.awt.event.*;
import java.util.Iterator;
import java.util.LinkedList;

public class TenantListPanel extends JPanel{

	private static final long serialVersionUID = 1;


	JTextArea error = null;
	
	private SpringLayout.Constraints cons = null;
	
	public TenantListPanel()
	{
		
		setLayout(new SpringLayout());
		SpringLayout spLayout;
        try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }

        JLabel name = new JLabel("Name");
        JLabel building = new JLabel("Building");
		
		cons = spLayout.getConstraints(name);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(10));
		cons = spLayout.getConstraints(building);
        cons.setX(Spring.constant(150));
        cons.setY(Spring.constant(10));

        add(name);
        add(building);
        int rows = 1;
        
        LinkedList<TenantData> tenantList;
		try {
			tenantList = TenantList.getInstance();
			

	        Iterator<TenantData> iter = tenantList.iterator();
	        
	        while(iter.hasNext())
	        {
	        	final TenantData currentTenant = iter.next();
	        	name = new JLabel(currentTenant.getFirstName()+" "+ currentTenant.getLastName());
	        	building = new JLabel(currentTenant.getBuildingName() + " " + currentTenant.getRoomNumber());
	        	
	        	cons = spLayout.getConstraints(name);
		        cons.setX(Spring.constant(20));
		        cons.setY(Spring.constant(10 + rows*30));
				cons = spLayout.getConstraints(building);
		        cons.setX(Spring.constant(150));
		        cons.setY(Spring.constant(10 + rows*30));
		        
		        add(name);
		        add(building);
		        
		        MenuButton viewTenantButton = new MenuButton("View Tenant",100);
				MenuButton btDel = new MenuButton("Delete",60);
				
				
				cons = spLayout.getConstraints(viewTenantButton);
		        cons.setX(Spring.constant(250));
		        cons.setY(Spring.constant(3 + rows*30));
				cons = spLayout.getConstraints(btDel);
		        cons.setX(Spring.constant(400));
		        cons.setY(Spring.constant(3 + rows*30));

		        add(viewTenantButton);
				//add(btDel);
				rows++;
				
				btDel.addActionListener(new ActionListener(){
					public void actionPerformed (ActionEvent event){
						AddModifyBuildingPanel p = null;
						getTopLevelAncestor().setSize(1200, 500);
						BuildingsManagementMainPanel.pnl3.removeAll();
						BuildingsManagementMainPanel.pnl3.add(new JLabel(""));
						BuildingsManagementMainPanel.pnl3.add(p);
						getTopLevelAncestor().validate();
						getTopLevelAncestor().repaint();
					}
			    });
				
				viewTenantButton.addActionListener(new ActionListener(){
					public void actionPerformed (ActionEvent event){
						
						AddModifyTenant p = new AddModifyTenant(currentTenant);
						getTopLevelAncestor().setSize(1220, 500);
						TenantManagementMainPanel.pnl3.removeAll();
						TenantManagementMainPanel.pnl3.add(p);
						getTopLevelAncestor().validate();
						getTopLevelAncestor().repaint();
						
					}
			    });
	        }
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
