package AccountManagementGUI;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

import javax.swing.JPanel;

import MainMenuGUI.MainMenuFrame;
import MainMenuGUI.MenuButton;



public class AccountManagementPanel extends JPanel {
	
	public AccountManagementPanel(final int id){
		
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		MenuButton[] buttons = new MenuButton[4];
		
		add(Box.createVerticalGlue());
		
		//add create account button
		buttons[0] = new MenuButton("Create Account",180);
		buttons[0].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				CreatAccountChooseIdentityPanel pnSearchRoom = new CreatAccountChooseIdentityPanel();
				getTopLevelAncestor().setSize(850, 500);
				clearPanels();
				AccountManagementMainPanel.pnl2.setLayout(new BorderLayout());
				AccountManagementMainPanel.pnl2.add(pnSearchRoom, BorderLayout.NORTH);
				AccountManagementMainPanel.pnl2.add(AccountManagementMainPanel.pnl4, BorderLayout.CENTER);
				getTopLevelAncestor().validate();
				getTopLevelAncestor().repaint();
			}
	    });
		
		
		//add View and Delete account button
		buttons[1] = new MenuButton("View/Delete Account",180);
		buttons[1].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				SearchAccountPanel pnSearchAccount = new SearchAccountPanel();
				getTopLevelAncestor().setSize(850, 500);
				clearPanels();
				AccountManagementMainPanel.pnl2.setLayout(new BorderLayout());
				AccountManagementMainPanel.pnl2.add(pnSearchAccount, BorderLayout.NORTH);
				AccountManagementMainPanel.pnl2.add(AccountManagementMainPanel.pnl4, BorderLayout.CENTER);
				getTopLevelAncestor().validate();
				getTopLevelAncestor().repaint();
			}
	    });
		
		//add resetPassword
		buttons[2] = new MenuButton("Password Reset",180);
		buttons[2].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				SearchAccountForPasswordPanel pnSearchAccount = new SearchAccountForPasswordPanel();
				getTopLevelAncestor().setSize(850, 500);
				clearPanels();
				AccountManagementMainPanel.pnl2.setLayout(new BorderLayout());
				AccountManagementMainPanel.pnl2.add(pnSearchAccount, BorderLayout.NORTH);
				AccountManagementMainPanel.pnl2.add(AccountManagementMainPanel.pnl4, BorderLayout.CENTER);
				getTopLevelAncestor().validate();
				getTopLevelAncestor().repaint();
			}
	    });
		
		buttons[3] = new MenuButton("Back to Main Menu",180);
		buttons[3].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				getTopLevelAncestor().setVisible(false);
				MainMenuFrame frame = new MainMenuFrame(id);
				frame.setLocation(300, 300);
				frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				frame.setVisible(true);
			}
	    });
		
		for (int i=0; i<4; i++){
	        buttons[i].setMaximumSize(new Dimension(180,30));
	        buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
	        add(buttons[i]);
			add(Box.createVerticalGlue());
		}
		
		
	}
	
	private void clearPanels(){
		AccountManagementMainPanel.pnl2.removeAll();
		AccountManagementMainPanel.pnl3.removeAll();
		AccountManagementMainPanel.pnl4.removeAll();
	}
	
	public static final long serialVersionUID = 1;
}
