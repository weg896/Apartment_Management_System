package AccountManagementGUI;

import MainMenuGUI.DisplayFrame;
import MainMenuGUI.MenuButton;
import commands.*;
//import startup.Database;


import javax.swing.*;


import java.awt.Color;
import java.awt.event.*;
import java.sql.*;

public class DeleteAccountPanel extends JPanel{

	private static final long serialVersionUID = 1;


	JTextArea error = null;
	
	private SpringLayout.Constraints cons = null;
	
	public DeleteAccountPanel(final ResultSet rs,boolean isTenant){
		
		if(isTenant){
			setLayout(new SpringLayout());
			SpringLayout spLayout;
	        try {
	        	spLayout = (SpringLayout)this.getLayout();
	        } catch (ClassCastException exc) {
	            System.err.println("The first argument to makeGrid must use SpringLayout.");
	            return;
	        }

	        JLabel lbRoomNum = new JLabel("Name");
	        JLabel lbPrice = new JLabel("Title");
			
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
					lbPrice = new JLabel(rs.getString("s_title")+""); 
					
					cons = spLayout.getConstraints(lbRoomNum);
			        cons.setX(Spring.constant(20));
			        cons.setY(Spring.constant(10 + rows*30));
					cons = spLayout.getConstraints(lbPrice);
			        cons.setX(Spring.constant(150));
			        cons.setY(Spring.constant(10 + rows*30));
					
					add(lbRoomNum);
					add(lbPrice);
					
					MenuButton btAccountDetail= new MenuButton("View Detail",100);
					MenuButton btDel= new MenuButton("Delete",60);
					btAccountDetail.setForeground(Color.WHITE);
					btDel.setForeground(Color.WHITE);
					
					final String name = rs.getString("u_last_name") + ", " + rs.getString("u_first_name");
					final String username  = rs.getString("u_username");
					final long phone = rs.getLong("u_phone");
					final String address = rs.getString("s_address");
					final String email = rs.getString("u_email");
					final String title = rs.getString("s_title");
					final String contactType;
					final String loc = rs.getString("b_name");
					final int userID = rs.getInt("u_id");
					if(rs.getInt("u_contact_type") == 0){
						contactType = "phone";
					}else{
						contactType = "email";
					}
					
					btAccountDetail.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent evt){
							AccountDetailPanel p = new AccountDetailPanel(userID,name, username, phone, address, email, title, contactType,loc);
							getTopLevelAncestor().setSize(1200, 500);
							AccountManagementMainPanel.pnl3.removeAll();
							AccountManagementMainPanel.pnl3.add(new JLabel(""));
							AccountManagementMainPanel.pnl3.add(p);
							getTopLevelAncestor().validate();
							getTopLevelAncestor().repaint();
						}
				    });
					
					btDel.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent evt){
							RemoveAccountCommand re = new RemoveAccountCommand();
							
							String info = "<html>Warning" + "<br>" +
							"Are you sure to delete account: " +username +"?<html>";
							DisplayFrame frame = new DisplayFrame("Warning", info,re,userID,false);
							frame.setLocation(500, 500);
							frame.setVisible(true);
						}
				    });
					
					
					cons = spLayout.getConstraints(btAccountDetail);
			        cons.setX(Spring.constant(250));
			        cons.setY(Spring.constant(3 + rows*30));
					cons = spLayout.getConstraints(btDel);
			        cons.setX(Spring.constant(400));
			        cons.setY(Spring.constant(3 + rows*30));

					add(btAccountDetail);
					add(btDel);
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
	                            Spring.constant(400));

		}
		
		
		else{
			setLayout(new SpringLayout());
			SpringLayout spLayout;
	        try {
	        	spLayout = (SpringLayout)this.getLayout();
	        } catch (ClassCastException exc) {
	            System.err.println("The first argument to makeGrid must use SpringLayout.");
	            return;
	        }

	        JLabel lbRoomNum = new JLabel("Name");
	        JLabel lbPrice = new JLabel("Building & Room#");
			
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
					lbPrice = new JLabel(rs.getString("b_name")+"; "+String.valueOf(rs.getInt("ar_room_number"))+""); 
					
					cons = spLayout.getConstraints(lbRoomNum);
			        cons.setX(Spring.constant(20));
			        cons.setY(Spring.constant(10 + rows*30));
					cons = spLayout.getConstraints(lbPrice);
			        cons.setX(Spring.constant(120));
			        cons.setY(Spring.constant(10 + rows*30));
					
					add(lbRoomNum);
					add(lbPrice);
					
					MenuButton btAccountDetail= new MenuButton("View Detail",100);
					MenuButton btDel= new MenuButton("Delete",60);
					btAccountDetail.setForeground(Color.WHITE);
					btDel.setForeground(Color.WHITE);
					
					final String name = rs.getString("u_last_name") + ", " + rs.getString("u_first_name");
					final String username  = rs.getString("u_username");
					final long phone = rs.getLong("u_phone");
					final String email = rs.getString("u_email");
					final String contactType;
					final String building = rs.getString("b_name");
					final int roomNum= rs.getInt("ar_room_number");
					final int userID = rs.getInt("u_id");
					if(rs.getInt("u_contact_type") == 0){
						contactType = "phone";
					}else{
						contactType = "email";
					}
					
					btAccountDetail.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent evt){
							AccountDetailPanel p = new AccountDetailPanel(userID,name,username,phone,email,contactType,
									 building,roomNum);
							getTopLevelAncestor().setSize(1200, 500);
							AccountManagementMainPanel.pnl3.removeAll();
							AccountManagementMainPanel.pnl3.add(new JLabel(""));
							AccountManagementMainPanel.pnl3.add(p);
							getTopLevelAncestor().validate();
							getTopLevelAncestor().repaint();
						}
				    });
					
					btDel.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent evt){
							RemoveAccountCommand re = new RemoveAccountCommand();
							
							String info = "<html>Warning" + "<br>" +
							"Are you sure to delete account: " +username +"?<html>";
							DisplayFrame frame = new DisplayFrame("Warning", info,re,userID,true);
							frame.setLocation(500, 500);
							frame.setVisible(true);
						}
				    });
					
					cons = spLayout.getConstraints(btAccountDetail);
			        cons.setX(Spring.constant(350));
			        cons.setY(Spring.constant(3 + rows*30));
					cons = spLayout.getConstraints(btDel);
			        cons.setX(Spring.constant(500));
			        cons.setY(Spring.constant(3 + rows*30));

					add(btAccountDetail);
					add(btDel);
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
	                            Spring.constant(500));

		}
		
	}	

}
