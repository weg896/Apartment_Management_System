package MainMenuGUI;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import commands.*;

public class DisplayPanel extends JPanel {
	public DisplayPanel(String info){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(Box.createVerticalGlue());
		
		JLabel prompt = new JLabel(info);
		prompt.setMaximumSize(prompt.getPreferredSize());
		add(prompt);
		prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());
		
	}
	
	public DisplayPanel(String info, final RemoveAccountCommand re,final int userID,final boolean isTenant){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(Box.createVerticalGlue());
		
		JLabel prompt = new JLabel(info);
		prompt.setMaximumSize(prompt.getPreferredSize());
		add(prompt);
		prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());
		
		MenuButton yesButton = new MenuButton("Yes", 60);
		yesButton.setMaximumSize(yesButton.getPreferredSize());
		add(yesButton);
		yesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		yesButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				if(!isTenant){
					re.removeStaffAccount(userID);
					getTopLevelAncestor().setVisible(false);
				}
				else{
					re.removeTenantAccount(userID);
					getTopLevelAncestor().setVisible(false);
				}
			}
	    });
		add(Box.createVerticalGlue());
		
		MenuButton noButton = new MenuButton("No", 60);
		noButton.setMaximumSize(noButton.getPreferredSize());
		add(noButton);
		noButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		noButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				getTopLevelAncestor().setVisible(false);
			}
	    });
		add(Box.createVerticalGlue());
		
	}
	public static final long serialVersionUID = 1;
}
