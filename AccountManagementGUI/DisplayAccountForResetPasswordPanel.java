package AccountManagementGUI;


import java.awt.Color;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

import MainMenuGUI.MenuButton;



public class DisplayAccountForResetPasswordPanel extends JPanel{
	
	private static final long serialVersionUID = 1;
	
	private SpringLayout.Constraints cons = null;
	
	public DisplayAccountForResetPasswordPanel(final ResultSet rs){
		
		setLayout(new SpringLayout());
		SpringLayout spLayout;
		
        try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }
        
        JLabel lbRoomNum = new JLabel("Name");
        JLabel lbPrice = new JLabel("Username");
		
		cons = spLayout.getConstraints(lbRoomNum);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(10));
		cons = spLayout.getConstraints(lbPrice);
        cons.setX(Spring.constant(120));
        cons.setY(Spring.constant(10));

        add(lbRoomNum);
        add(lbPrice);
        int rows = 1;
        
        try{
			while(rs.next()){
				lbRoomNum = new JLabel(rs.getString("u_last_name")+","+rs.getString("u_first_name")+""); 
				lbPrice = new JLabel(rs.getString("u_username")+""); 
				
				cons = spLayout.getConstraints(lbRoomNum);
		        cons.setX(Spring.constant(20));
		        cons.setY(Spring.constant(10 + rows*30));
				cons = spLayout.getConstraints(lbPrice);
		        cons.setX(Spring.constant(120));
		        cons.setY(Spring.constant(10 + rows*30));
		        
		        final String username = rs.getString("u_username");
				
				add(lbRoomNum);
				add(lbPrice);
				
				MenuButton btReset= new MenuButton("Reset Password",120);
				btReset.setForeground(Color.WHITE);
				btReset.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent evt){
						PasswordResetPanel p = new PasswordResetPanel(username);
						getTopLevelAncestor().setSize(1200, 500);
						AccountManagementMainPanel.pnl3.removeAll();
						AccountManagementMainPanel.pnl3.add(new JLabel(""));
						AccountManagementMainPanel.pnl3.add(p);
						getTopLevelAncestor().validate();
						getTopLevelAncestor().repaint();
					}
			    });
				
				cons = spLayout.getConstraints(btReset);
		        cons.setX(Spring.constant(180));
		        cons.setY(Spring.constant(3 + rows*30));

				add(btReset);
				rows++;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
        SpringLayout.Constraints pCons = spLayout.getConstraints(this);
        pCons.setConstraint(SpringLayout.SOUTH,
                            Spring.constant(10 + rows*30));
        pCons.setConstraint(SpringLayout.EAST,
                            Spring.constant(350));
        
	}

}
