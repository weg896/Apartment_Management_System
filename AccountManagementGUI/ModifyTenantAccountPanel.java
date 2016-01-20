package AccountManagementGUI;

import MainMenuGUI.DisplayFrame;
import MainMenuGUI.MenuButton;
import MainMenuGUI.ReadImage;
import MainMenuGUI.ValueDisplayPanel;
import commands.*;

import javax.swing.*;

import entities.TenantData;

import java.awt.Color;
//import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.text.*;
//import java.util.*;

public class ModifyTenantAccountPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	private SpringLayout.Constraints cons = null;
	private SpringLayout spLayout;
 
	private TenantProfileCommand cmdTenant = null;
	private TenantData dataTenant = null;
	private int userID;
	
	private ViewPhonePanel pnViewPhone;
	private ChangePhonePanel pnChangePhone;
	private ViewEmailPanel pnViewEmail;
	private ChangeEmailPanel pnChangeEmail;
	private ViewCTypePanel pnViewCType;
	private ChangeCTypePanel pnChangeCType;
	private JFormattedTextField txPhone;
	private JTextField txEmail;
	private JRadioButton rbPhone;
	private JRadioButton rbEmail;
	
	public ModifyTenantAccountPanel(int userID){
		
		
		this.userID = userID;
		cmdTenant = new TenantProfileCommand();
		dataTenant = cmdTenant.getTenantProfile(userID);
		if (null == dataTenant){
			System.out.println("cannot access tenant profile");
			//errormessage = "cannot access tenant profile"
		}
		
		setLayout(new SpringLayout());
		
		try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }

        JLabel lbTitle = new JLabel("Tenant Profile");
		cons = spLayout.getConstraints(lbTitle);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(20));
        add(lbTitle);
        
        ValueDisplayPanel pnUsername = new ValueDisplayPanel("Name:", 
        		dataTenant.getFirstName()+" "+dataTenant.getLastName(),150); 
		cons = spLayout.getConstraints(pnUsername);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(80));
        add(pnUsername);

        ValueDisplayPanel pnBuilding = new ValueDisplayPanel("Building:",
        		dataTenant.getBuildingName(),150); 
		cons = spLayout.getConstraints(pnBuilding);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(120));
        add(pnBuilding);

        ValueDisplayPanel pnRoom = new ValueDisplayPanel("Room Number:",
        		dataTenant.getRoomNumber()+"",150); 
		cons = spLayout.getConstraints(pnRoom);
        cons.setX(Spring.constant(0));
        cons.setY(Spring.constant(160));
        add(pnRoom);
        
        ////////////////////////////////////////////////////////////
        
        JLabel lbPhone = new JLabel("Phone Number:");
		cons = spLayout.getConstraints(lbPhone);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(205));
        add(lbPhone);
        
        pnViewPhone = new ViewPhonePanel();
        cons = spLayout.getConstraints(pnViewPhone);
        cons.setX(Spring.constant(140));
        cons.setY(Spring.constant(200));
        cons.setWidth(Spring.constant(500));
        cons.setHeight(Spring.constant(50));
        add(pnViewPhone);
        pnViewPhone.setVisible(true);
        
        pnChangePhone = new ChangePhonePanel();
        cons = spLayout.getConstraints(pnChangePhone);
        cons.setX(Spring.constant(140));
        cons.setY(Spring.constant(200));
        cons.setWidth(Spring.constant(500));
        cons.setHeight(Spring.constant(50));
        add(pnChangePhone);
        pnChangePhone.setVisible(false);
        
        ////////////////////////////////////////////////////////////
        
        JLabel lbEmail = new JLabel("Email:");
		cons = spLayout.getConstraints(lbEmail);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(245));
        add(lbEmail);
        
        pnViewEmail = new ViewEmailPanel();
        cons = spLayout.getConstraints(pnViewEmail);
        cons.setX(Spring.constant(140));
        cons.setY(Spring.constant(240));
        cons.setWidth(Spring.constant(500));
        cons.setHeight(Spring.constant(50));
        add(pnViewEmail);
        pnViewEmail.setVisible(true);
        
        pnChangeEmail = new ChangeEmailPanel();
        cons = spLayout.getConstraints(pnChangeEmail);
        cons.setX(Spring.constant(140));
        cons.setY(Spring.constant(240));
        cons.setWidth(Spring.constant(500));
        cons.setHeight(Spring.constant(50));
        add(pnChangeEmail);
        pnChangeEmail.setVisible(false);
        
        ////////////////////////////////////////////////////////////

        JLabel lbCType = new JLabel("<html>Preferred<br>Contact Type:<html>");
		cons = spLayout.getConstraints(lbCType);
        cons.setX(Spring.constant(20));
        cons.setY(Spring.constant(270));
        cons.setWidth(Spring.constant(500));
        cons.setHeight(Spring.constant(50));
        add(lbCType);

        pnViewCType = new ViewCTypePanel();
        cons = spLayout.getConstraints(pnViewCType);
        cons.setX(Spring.constant(140));
        cons.setY(Spring.constant(280));
        cons.setWidth(Spring.constant(500));
        cons.setHeight(Spring.constant(50));
        add(pnViewCType);
        pnViewCType.setVisible(true);
        
        pnChangeCType = new ChangeCTypePanel();
        cons = spLayout.getConstraints(pnChangeCType);
        cons.setX(Spring.constant(140));
        cons.setY(Spring.constant(280));
        cons.setWidth(Spring.constant(500));
        cons.setHeight(Spring.constant(50));
        add(pnChangeCType);
        pnChangeCType.setVisible(false);
        
		MenuButton done= new MenuButton("Done",60);
		done.setForeground(Color.WHITE);
		cons = spLayout.getConstraints(done);
        cons.setX(Spring.constant(200));
        cons.setY(Spring.constant(350));
		done.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				getTopLevelAncestor().setVisible(false);
			}
	    });
        add(done);
		
	}
	
	private class ViewPhonePanel extends JPanel{
		private static final long serialVersionUID = 1L;
		public ViewPhonePanel(){
			setOpaque(false);
			setLayout(new SpringLayout());
			SpringLayout spLayout = null;
			try {
				spLayout = (SpringLayout)this.getLayout();
	        } catch (ClassCastException exc) {
	            System.err.println("The first argument to makeGrid must use SpringLayout.");
	            return;
	        }

			BufferedImage img = (new ReadImage()).tx_220_30_grey();

	        JLabel lbPhone = new JLabel(dataTenant.getPhoneNumber()+"");
			cons = spLayout.getConstraints(lbPhone);
	        cons.setX(Spring.constant(10));
	        cons.setY(Spring.constant(5));
	        cons.setWidth(Spring.constant(200));
	        cons.setHeight(Spring.constant(25));
	        add(lbPhone);

			MenuButton bgPhone = new MenuButton("",img,img);
			cons = spLayout.getConstraints(bgPhone);
	        cons.setX(Spring.constant(0));
	        cons.setY(Spring.constant(0));
	        cons.setWidth(Spring.constant(220));
	        cons.setHeight(Spring.constant(30));
	        add(bgPhone);
	        
	        MenuButton btChangePhone = new MenuButton("Change",60);
			cons = spLayout.getConstraints(btChangePhone);
	        cons.setX(Spring.constant(220));
	        cons.setY(Spring.constant(0));
	        btChangePhone.addActionListener(new ActionListener(){
	        	public void actionPerformed (ActionEvent event) {
					pnViewPhone.setVisible(false);
					pnChangePhone.setVisible(true);
				}
	        });
	        add(btChangePhone);
		}
	}

	private class ChangePhonePanel extends JPanel{
		private static final long serialVersionUID = 1L;
		public ChangePhonePanel(){
			setOpaque(false);
			setLayout(new SpringLayout());
			SpringLayout spLayout = null;
			try {
				spLayout = (SpringLayout)this.getLayout();
	        } catch (ClassCastException exc) {
	            System.err.println("The first argument to makeGrid must use SpringLayout.");
	            return;
	        }
			
			BufferedImage img = (new ReadImage()).tx_220_30();

			txPhone = new JFormattedTextField(NumberFormat.getIntegerInstance());
			txPhone.setColumns(15);
			txPhone.setOpaque(false);
			txPhone.setBorder(null);
	        cons = spLayout.getConstraints(txPhone);
	        cons.setX(Spring.constant(10));
	        cons.setY(Spring.constant(5));
	        cons.setWidth(Spring.constant(200));
	        cons.setHeight(Spring.constant(25));
	        add(txPhone);
	        
			MenuButton bgPhone = new MenuButton("",img,img);
			cons = spLayout.getConstraints(bgPhone);
	        cons.setX(Spring.constant(0));
	        cons.setY(Spring.constant(0));
	        cons.setWidth(Spring.constant(220));
	        cons.setHeight(Spring.constant(30));
	        add(bgPhone);

	        MenuButton btAcceptPhone = new MenuButton("Accept Change",120);
			cons = spLayout.getConstraints(btAcceptPhone);
	        cons.setX(Spring.constant(220));
	        cons.setY(Spring.constant(0));
	        btAcceptPhone.addActionListener(new ActionListener(){
	        	public void actionPerformed (ActionEvent event) {
					if (null == txPhone.getValue()){
						String info = "<html>invalid phone number<html>";
						DisplayFrame frame = new DisplayFrame("Error:", info);
						frame.setLocation(400, 400);
						frame.setVisible(true);
					}else{
						cmdTenant.changeTenantPhone(userID, (long)txPhone.getValue());
						reload();
					}
				}
	        });
	        add(btAcceptPhone);
	        
	        MenuButton btCancelPhone = new MenuButton("Cancel",60);
			cons = spLayout.getConstraints(btCancelPhone);
	        cons.setX(Spring.constant(350));
	        cons.setY(Spring.constant(0));
	        btCancelPhone.addActionListener(new ActionListener(){
	        	public void actionPerformed (ActionEvent event) {
					txPhone.setText("");
					pnViewPhone.setVisible(true);
					pnChangePhone.setVisible(false);
				}
	        });
	        add(btCancelPhone);
		}
	}
	
	private class ViewEmailPanel extends JPanel{
		private static final long serialVersionUID = 1L;
		public ViewEmailPanel(){
			setOpaque(false);
			setLayout(new SpringLayout());
			SpringLayout spLayout = null;
			try {
				spLayout = (SpringLayout)this.getLayout();
	        } catch (ClassCastException exc) {
	            System.err.println("The first argument to makeGrid must use SpringLayout.");
	            return;
	        }

			BufferedImage img = (new ReadImage()).tx_220_30_grey();

	        JLabel lbEmail = new JLabel(dataTenant.getEmailAddress()+"");
			cons = spLayout.getConstraints(lbEmail);
	        cons.setX(Spring.constant(10));
	        cons.setY(Spring.constant(5));
	        cons.setWidth(Spring.constant(200));
	        cons.setHeight(Spring.constant(25));
	        add(lbEmail);

			MenuButton bgEmail = new MenuButton("",img,img);
			cons = spLayout.getConstraints(bgEmail);
	        cons.setX(Spring.constant(0));
	        cons.setY(Spring.constant(0));
	        cons.setWidth(Spring.constant(220));
	        cons.setHeight(Spring.constant(30));
	        add(bgEmail);

	        MenuButton btChangeEmail = new MenuButton("Change",60);
			cons = spLayout.getConstraints(btChangeEmail);
	        cons.setX(Spring.constant(220));
	        cons.setY(Spring.constant(0));
	        btChangeEmail.addActionListener(new ActionListener(){
	        	public void actionPerformed (ActionEvent event) {
					pnViewEmail.setVisible(false);
					pnChangeEmail.setVisible(true);
				}
	        });
	        add(btChangeEmail);
		}
	}

	private class ChangeEmailPanel extends JPanel{
		private static final long serialVersionUID = 1L;
		public ChangeEmailPanel(){
			setOpaque(false);
			setLayout(new SpringLayout());
			SpringLayout spLayout = null;
			try {
				spLayout = (SpringLayout)this.getLayout();
	        } catch (ClassCastException exc) {
	            System.err.println("The first argument to makeGrid must use SpringLayout.");
	            return;
	        }
			
			BufferedImage img = (new ReadImage()).tx_220_30();

			txEmail = new JTextField(15);
	        add(txEmail);
			txEmail.setOpaque(false);
			txEmail.setBorder(null);
	        cons = spLayout.getConstraints(txEmail);
	        cons.setX(Spring.constant(10));
	        cons.setY(Spring.constant(5));
	        cons.setWidth(Spring.constant(200));
	        cons.setHeight(Spring.constant(25));

			MenuButton bgEmail = new MenuButton("",img,img);
			cons = spLayout.getConstraints(bgEmail);
	        cons.setX(Spring.constant(0));
	        cons.setY(Spring.constant(0));
	        cons.setWidth(Spring.constant(220));
	        cons.setHeight(Spring.constant(30));
	        add(bgEmail);

	        MenuButton btAcceptEmail = new MenuButton("Accept Change",120);
	        cons = spLayout.getConstraints(btAcceptEmail);
	        cons.setX(Spring.constant(220));
	        cons.setY(Spring.constant(0));
	        btAcceptEmail.addActionListener(new ActionListener(){
	        	public void actionPerformed (ActionEvent event) {
					if (null == txEmail.getText()){
						String info = "<html>invalid email address<html>";
						DisplayFrame frame = new DisplayFrame("Error:", info);
						frame.setLocation(400, 400);
						frame.setVisible(true);
					}else{
						cmdTenant.changeTenantEmail(userID, txEmail.getText());
						reload();
					}
				}
	        });
	        add(btAcceptEmail);
	        
	        MenuButton btCancelEmail= new MenuButton("Cancel",60);
	        cons = spLayout.getConstraints(btCancelEmail);
	        cons.setX(Spring.constant(350));
	        cons.setY(Spring.constant(0));
	        btCancelEmail.addActionListener(new ActionListener(){
	        	public void actionPerformed (ActionEvent event) {
					txEmail.setText("");
					pnViewEmail.setVisible(true);
					pnChangeEmail.setVisible(false);
				}
	        });
	        add(btCancelEmail);
		}
	}

	private class ViewCTypePanel extends JPanel{
		private static final long serialVersionUID = 1L;
		public ViewCTypePanel(){
			setOpaque(false);
			setLayout(new SpringLayout());
			SpringLayout spLayout = null;
			try {
				spLayout = (SpringLayout)this.getLayout();
	        } catch (ClassCastException exc) {
	            System.err.println("The first argument to makeGrid must use SpringLayout.");
	            return;
	        }

			BufferedImage img = (new ReadImage()).tx_220_30_grey();

	        JLabel lbCType = new JLabel(dataTenant.getContactType()+"");
			cons = spLayout.getConstraints(lbCType);
	        cons.setX(Spring.constant(10));
	        cons.setY(Spring.constant(5));
	        cons.setWidth(Spring.constant(200));
	        cons.setHeight(Spring.constant(25));
	        add(lbCType);

			MenuButton bgCType = new MenuButton("",img,img);
			cons = spLayout.getConstraints(bgCType);
	        cons.setX(Spring.constant(0));
	        cons.setY(Spring.constant(0));
	        cons.setWidth(Spring.constant(220));
	        cons.setHeight(Spring.constant(30));
	        add(bgCType);

	        MenuButton btChangeCType = new MenuButton("Change",60);
			cons = spLayout.getConstraints(btChangeCType);
	        cons.setX(Spring.constant(220));
	        cons.setY(Spring.constant(0));
	        btChangeCType.addActionListener(new ActionListener(){
	        	public void actionPerformed (ActionEvent event) {
					pnViewCType.setVisible(false);
					pnChangeCType.setVisible(true);
				}
	        });
	        add(btChangeCType);
		}
	}

	private class ChangeCTypePanel extends JPanel{
		private static final long serialVersionUID = 1L;
		public ChangeCTypePanel(){
			setOpaque(false);
			setLayout(new SpringLayout());
			SpringLayout spLayout = null;
			try {
				spLayout = (SpringLayout)this.getLayout();
	        } catch (ClassCastException exc) {
	            System.err.println("The first argument to makeGrid must use SpringLayout.");
	            return;
	        }

			rbPhone = new JRadioButton("Phone");
			rbEmail = new JRadioButton("Email");
			rbPhone.setOpaque(false);
			rbEmail.setOpaque(false);
			cons = spLayout.getConstraints(rbPhone);
	        cons.setX(Spring.constant(0));
	        cons.setY(Spring.constant(0));
			cons = spLayout.getConstraints(rbEmail);
	        cons.setX(Spring.constant(100));
	        cons.setY(Spring.constant(0));
			add(rbPhone);
			add(rbEmail);
			ButtonGroup bgCType = new ButtonGroup();
			bgCType.add(rbPhone);
			bgCType.add(rbEmail);
	        MenuButton btAcceptCType = new MenuButton("Accept Change",120);
			cons = spLayout.getConstraints(btAcceptCType);
	        cons.setX(Spring.constant(220));
	        cons.setY(Spring.constant(0));
	        btAcceptCType.addActionListener(new ActionListener(){
	        	public void actionPerformed (ActionEvent event) {
					if (rbPhone.isSelected()){ cmdTenant.changeTenantCType(userID, 0);reload(); }
					else if (rbEmail.isSelected()){ cmdTenant.changeTenantCType(userID, 1);reload(); }
					else {
						String info = "<html>invalid contact type<html>";
						DisplayFrame frame = new DisplayFrame("Error:", info);
						frame.setLocation(400, 400);
						frame.setVisible(true);
					}
				}
	        });
	        add(btAcceptCType);
	        
	        MenuButton btCancelCType = new MenuButton("Cancel",60);
			cons = spLayout.getConstraints(btCancelCType);
	        cons.setX(Spring.constant(350));
	        cons.setY(Spring.constant(0));
	        btCancelCType.addActionListener(new ActionListener(){
	        	public void actionPerformed (ActionEvent event) {
					rbPhone.setSelected(false);
					rbEmail.setSelected(false);
					pnViewCType.setVisible(true);
					pnChangeCType.setVisible(false);
				}
	        });
	        add(btCancelCType);
		}
	}
	
	private void reload(){
		ModifyTenantAccountPanel panel = new ModifyTenantAccountPanel(userID);
		ModifyAccountPanel.pnl1.removeAll();
		ModifyAccountPanel.pnl1.add(panel);
		ModifyAccountPanel.pnl1.validate();
		ModifyAccountPanel.pnl1.repaint();	
	}

}
