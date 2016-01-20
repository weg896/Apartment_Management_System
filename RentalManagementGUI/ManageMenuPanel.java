package RentalManagementGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

import MainMenuGUI.MainMenuFrame;
import MainMenuGUI.MenuButton;

public class ManageMenuPanel extends JPanel{

	public ManageMenuPanel(final int userid){
		
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		int buttonCount = 4;
		MenuButton[] buttons = new MenuButton[buttonCount];
		//////
		buttons[0] = new MenuButton("View History",150);
		buttons[0].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
						ManageViewHistoryPanel viewHistory = new ManageViewHistoryPanel(userid);
						getTopLevelAncestor().setSize(650, 500);
						clearPanels();
						ManageRentalSidePanel.pnl2.setLayout(new BorderLayout());
						JScrollPane scroPanel = new JScrollPane(viewHistory);
						ManageRentalSidePanel.pnl2.add(scroPanel, BorderLayout.CENTER);
						getTopLevelAncestor().validate();
						getTopLevelAncestor().repaint();
					}
		    });
		
		buttons[1] = new MenuButton("Change Room Fee",150);
		buttons[1].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
						ManageChangeFeePanel changeFee = new ManageChangeFeePanel();
						getTopLevelAncestor().setSize(650, 500);
						clearPanels();
						ManageRentalSidePanel.pnl2.setLayout(new BorderLayout());
						ManageRentalSidePanel.pnl2.add(changeFee, BorderLayout.CENTER);
						getTopLevelAncestor().validate();
						getTopLevelAncestor().repaint();
					}
		    });
		
		buttons[2] = new MenuButton("Charge Tenant",150);
		buttons[2].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
						ManagePaymentRequirePanel changeFee = new ManagePaymentRequirePanel();
						getTopLevelAncestor().setSize(650, 500);
						clearPanels();
						ManageRentalSidePanel.pnl2.setLayout(new BorderLayout());
						ManageRentalSidePanel.pnl2.add(changeFee, BorderLayout.CENTER);
						getTopLevelAncestor().validate();
						getTopLevelAncestor().repaint();
					}
		    });
		
		buttons[3] = new MenuButton("Back to Main Menu",150);
		buttons[3].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				getTopLevelAncestor().setVisible(false);
				MainMenuFrame frame = new MainMenuFrame(userid);
				frame.setLocation(300, 300);
				frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				frame.setVisible(true);
			}
	    });
		
		add(Box.createVerticalGlue());
		for (int i=0; i<buttonCount; i++){
	        buttons[i].setMaximumSize(new Dimension(150,30));
	        buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
	        buttons[i].setForeground(Color.WHITE);
			add(buttons[i]);
			add(Box.createVerticalGlue());
		}
	}
	
	private void clearPanels(){
		ManageRentalSidePanel.pnl2.removeAll();
		ManageRentalSidePanel.pnl3.removeAll();
		ManageRentalSidePanel.pnl4.removeAll();
	}

	
	
	public static void main(String[] arge){
		ManageRentalSideFrame jf = new ManageRentalSideFrame(1);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.revalidate();
	}
	public static final long serialVersionUID = 1;
}
