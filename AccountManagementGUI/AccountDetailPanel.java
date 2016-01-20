package AccountManagementGUI;


import java.awt.Color;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import MainMenuGUI.MenuButton;

public class AccountDetailPanel extends JPanel{

	private static final long serialVersionUID = 1;
	
	private SpringLayout.Constraints cons = null;

	public AccountDetailPanel(final int userID,String name, String username, long phone, String address, String email, String title, String contactType,
			String loc){
		
		setLayout(new SpringLayout());
		
		SpringLayout spLayout;
        try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }

        JLabel lbTitle = new JLabel("Account Detail");
		cons = spLayout.getConstraints(lbTitle);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(20));
        add(lbTitle);
        
        
        JLabel lbBldn1 = new JLabel("Name: ");
		cons = spLayout.getConstraints(lbBldn1);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(50));
        add(lbBldn1);

        JLabel lbBldn2 = new JLabel(name);
		cons = spLayout.getConstraints(lbBldn2);
        cons.setX(Spring.constant(180));
        cons.setY(Spring.constant(50));
        add(lbBldn2);
        
        JLabel lbBedrooms1 = new JLabel("Title: ");
		cons = spLayout.getConstraints(lbBedrooms1);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(80));
        add(lbBedrooms1);

        JLabel lbBedrooms2 = new JLabel(title+"");
		cons = spLayout.getConstraints(lbBedrooms2);
        cons.setX(Spring.constant(180));
        cons.setY(Spring.constant(80));
        add(lbBedrooms2);

        JLabel lbRoomnum1 = new JLabel("Username: ");
		cons = spLayout.getConstraints(lbRoomnum1);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(110));
        add(lbRoomnum1);

        JLabel lbRoomnum2 = new JLabel(username+"");
		cons = spLayout.getConstraints(lbRoomnum2);
        cons.setX(Spring.constant(180));
        cons.setY(Spring.constant(110));
        add(lbRoomnum2);
        
        JLabel lbArea5 = new JLabel("Working Building: ");
		cons = spLayout.getConstraints(lbArea5);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(140));
        add(lbArea5);
        
        JLabel lbArea6 = new JLabel(loc+"");
		cons = spLayout.getConstraints(lbArea6);
        cons.setX(Spring.constant(180));
        cons.setY(Spring.constant(140));
        add(lbArea6);

        JLabel lbArea1 = new JLabel("Home Address: ");
		cons = spLayout.getConstraints(lbArea1);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(170));
        add(lbArea1);

        JLabel lbArea2 = new JLabel(address+"");
		cons = spLayout.getConstraints(lbArea2);
        cons.setX(Spring.constant(180));
        cons.setY(Spring.constant(170));
        add(lbArea2);

        JLabel lbCost1 = new JLabel("Phone#: ");
		cons = spLayout.getConstraints(lbCost1);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(200));
        add(lbCost1);

        JLabel lbCost2 = new JLabel(String.valueOf(phone));
		cons = spLayout.getConstraints(lbCost2);
        cons.setX(Spring.constant(180));
        cons.setY(Spring.constant(200));
        add(lbCost2);

        JLabel lbTenants1 = new JLabel("Email: ");
		cons = spLayout.getConstraints(lbTenants1);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(230));
        add(lbTenants1);

        JLabel lbTenants2 = new JLabel(email+"");
		cons = spLayout.getConstraints(lbTenants2);
        cons.setX(Spring.constant(180));
        cons.setY(Spring.constant(230));
        add(lbTenants2);
        
        JLabel lbArea3 = new JLabel("Prefer Contact Type: ");
		cons = spLayout.getConstraints(lbArea3);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(260));
        add(lbArea3);

        JLabel lbArea4 = new JLabel(contactType+"");
		cons = spLayout.getConstraints(lbArea4);
        cons.setX(Spring.constant(180));
        cons.setY(Spring.constant(260));
        add(lbArea4);
        
        MenuButton change= new MenuButton("Change Info.",120);
        change.setForeground(Color.WHITE);
        cons = spLayout.getConstraints(change);
        cons.setX(Spring.constant(100));
        cons.setY(Spring.constant(300));
		add(change);
		change.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				ModifiyAccountFrame frame = new ModifiyAccountFrame (userID, true);
				frame.setLocation(500, 300);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
	    });
        
	}
	
	
	public AccountDetailPanel(final int userID,String name, String username, long phone, String email, String contactType,
			String building, int roomNum){
		
		setLayout(new SpringLayout());
		SpringLayout spLayout;
        try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }

        JLabel lbTitle = new JLabel("Account Detail");
		cons = spLayout.getConstraints(lbTitle);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(20));
        add(lbTitle);
        
        
        JLabel lbBldn1 = new JLabel("Name: ");
		cons = spLayout.getConstraints(lbBldn1);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(50));
        add(lbBldn1);

        JLabel lbBldn2 = new JLabel(name);
		cons = spLayout.getConstraints(lbBldn2);
        cons.setX(Spring.constant(180));
        cons.setY(Spring.constant(50));
        add(lbBldn2);
        
 

        JLabel lbRoomnum1 = new JLabel("Username: ");
		cons = spLayout.getConstraints(lbRoomnum1);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(80));
        add(lbRoomnum1);

        JLabel lbRoomnum2 = new JLabel(username+"");
		cons = spLayout.getConstraints(lbRoomnum2);
        cons.setX(Spring.constant(180));
        cons.setY(Spring.constant(80));
        add(lbRoomnum2);
        
        JLabel lbArea5 = new JLabel("Renting in Building: ");
		cons = spLayout.getConstraints(lbArea5);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(110));
        add(lbArea5);
        
        JLabel lbArea6 = new JLabel(building +"");
		cons = spLayout.getConstraints(lbArea6);
        cons.setX(Spring.constant(180));
        cons.setY(Spring.constant(110));
        add(lbArea6);

        JLabel lbArea1 = new JLabel("Room Number: ");
		cons = spLayout.getConstraints(lbArea1);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(140));
        add(lbArea1);

        JLabel lbArea2 = new JLabel(String.valueOf(roomNum)+"");
		cons = spLayout.getConstraints(lbArea2);
        cons.setX(Spring.constant(180));
        cons.setY(Spring.constant(140));
        add(lbArea2);

        JLabel lbCost1 = new JLabel("Phone#: ");
		cons = spLayout.getConstraints(lbCost1);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(170));
        add(lbCost1);

        JLabel lbCost2 = new JLabel(String.valueOf(phone));
		cons = spLayout.getConstraints(lbCost2);
        cons.setX(Spring.constant(180));
        cons.setY(Spring.constant(170));
        add(lbCost2);

        JLabel lbTenants1 = new JLabel("Email: ");
		cons = spLayout.getConstraints(lbTenants1);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(200));
        add(lbTenants1);

        JLabel lbTenants2 = new JLabel(email+"");
		cons = spLayout.getConstraints(lbTenants2);
        cons.setX(Spring.constant(180));
        cons.setY(Spring.constant(200));
        add(lbTenants2);
        
        JLabel lbArea3 = new JLabel("Prefer Contact Type: ");
		cons = spLayout.getConstraints(lbArea3);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(230));
        add(lbArea3);

        JLabel lbArea4 = new JLabel(contactType+"");
		cons = spLayout.getConstraints(lbArea4);
        cons.setX(Spring.constant(180));
        cons.setY(Spring.constant(230));
        add(lbArea4);
        
        MenuButton change= new MenuButton("Change Info.",120);
        change.setForeground(Color.WHITE);
        cons = spLayout.getConstraints(change);
        cons.setX(Spring.constant(100));
        cons.setY(Spring.constant(300));
		add(change);
		change.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				ModifiyAccountFrame frame = new ModifiyAccountFrame (userID, false);
				frame.setLocation(500, 300);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
	    });
	}
}
