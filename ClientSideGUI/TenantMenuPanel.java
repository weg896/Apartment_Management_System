package ClientSideGUI;

import MainMenuGUI.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * the menu panel of @ClientSide, containing a column of buttons that link to main functions of client side
 * @author YUQING TAN
 */
public class TenantMenuPanel extends JPanel{
	
	public TenantMenuPanel(final int userID){
		
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		MenuButton[] buttons = new MenuButton[8];
		
		//add Search/Apply Room button
		buttons[0] = new MenuButton("Search/Apply Room",150);
		buttons[0].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				RoomSearchPanel pnSearchRoom = new RoomSearchPanel(userID);
				clearPanels();
				ClientSidePanel.pnl2.setLayout(new BorderLayout());
				ClientSidePanel.pnl2.add(pnSearchRoom, BorderLayout.NORTH);
				ClientSidePanel.pnl2.add(ClientSidePanel.pnl4, BorderLayout.CENTER);
				getTopLevelAncestor().setSize(840, 600);
				getTopLevelAncestor().validate();
				getTopLevelAncestor().repaint();
			}
	    });
		
		// add view payment button
		buttons[1] = new MenuButton("View Payment",150);
		buttons[1].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				TenantViewFeePanel viewFeePanel = new TenantViewFeePanel(userID);
				getTopLevelAncestor().setSize(840, 600);
				clearPanels();
				ClientSidePanel.pnl2.setLayout(new BorderLayout());
				JScrollPane scroPanel = new JScrollPane(viewFeePanel);
				scroPanel.setOpaque(false);
				scroPanel.getViewport().setOpaque(false);
				scroPanel.setBorder(null);
				ClientSidePanel.pnl2.add(scroPanel, BorderLayout.CENTER);
				getTopLevelAncestor().validate();
				getTopLevelAncestor().repaint();
			}
	    });
		
		// add view payment history button
		buttons[2] = new MenuButton("View History",150);
		buttons[2].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				TenantViewHistoryPanel viewHistoryPanel = new TenantViewHistoryPanel(userID);
				getTopLevelAncestor().setSize(840, 600);
				clearPanels();
				ClientSidePanel.pnl2.setLayout(new BorderLayout());
				JScrollPane scroPanel = new JScrollPane(viewHistoryPanel);
				scroPanel.setOpaque(false);
				scroPanel.getViewport().setOpaque(false);
				scroPanel.setBorder(null);
				ClientSidePanel.pnl2.add(scroPanel, BorderLayout.CENTER);
				getTopLevelAncestor().validate();
				getTopLevelAncestor().repaint();
			}
	    });
		
		//add Send Request button
		buttons[3] = new MenuButton("Send Request",150);
		buttons[3].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				NoticeSendPanel pnSendRequest = new NoticeSendPanel(userID);
				getTopLevelAncestor().setSize(840, 600);
				clearPanels();
				ClientSidePanel.pnl2.add(pnSendRequest);
				getTopLevelAncestor().validate();
				getTopLevelAncestor().repaint();
			}
	    });
		
		//add View Notifications button
		buttons[4] = new MenuButton("View Notifications",150);
		buttons[4].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				NoticeDisplayPanel pnNoticeDisplay = new NoticeDisplayPanel(userID, null);
				NoticeOptionPanel pnNoticeOption = new NoticeOptionPanel(userID);
				JScrollPane scNoticeDisplay = new JScrollPane(pnNoticeDisplay);
				scNoticeDisplay.setOpaque(false);
				scNoticeDisplay.getViewport().setOpaque(false);
				scNoticeDisplay.setBorder(null);

				clearPanels();
				ClientSidePanel.pnl2.setLayout(new BorderLayout());
				ClientSidePanel.pnl2.add(pnNoticeOption, BorderLayout.NORTH);
				ClientSidePanel.pnl2.add(ClientSidePanel.pnl4, BorderLayout.CENTER);
				ClientSidePanel.pnl4.add(scNoticeDisplay);
				getTopLevelAncestor().setSize(840, 600);
				getTopLevelAncestor().validate();
				getTopLevelAncestor().repaint();
			}
	    });
		
		//add View/Change Profile button
		buttons[5] = new MenuButton("View/Change Profile",150);
		buttons[5].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				TenantProfilePanel pnTenantProfile = new TenantProfilePanel(userID);
				getTopLevelAncestor().setSize(840, 600);
				clearPanels();
				ClientSidePanel.pnl2.add(pnTenantProfile);
				getTopLevelAncestor().validate();
				getTopLevelAncestor().repaint();
			}
	    });
		
		// add a button to logout
		buttons[6] = new MenuButton("Logout",150);
		//final JButton btLogout = new JButton("Logout");
		buttons[6].addActionListener(new ActionListener(){
				public void actionPerformed (ActionEvent event){
					getTopLevelAncestor().setVisible(false);
					LoginFrame frame = new LoginFrame();
					frame.setLocation(300, 300);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				}
			});
	
		// add a button to exit from the whole project
		buttons[7] = new MenuButton("Exit",150);
		buttons[7].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				System.exit(0);
			}
		});

		/*print out all buttons*/
		add(Box.createVerticalGlue());
		for (int i=0; i<8; i++){
	        buttons[i].setMaximumSize(new Dimension(150,30));
	        buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
	        add(buttons[i]);
			add(Box.createVerticalGlue());
		}
	}
	
	private void clearPanels(){
		ClientSidePanel.pnl2.removeAll();
		ClientSidePanel.pnl3.removeAll();
		ClientSidePanel.pnl4.removeAll();
	}

	public static final long serialVersionUID = 1;
}
