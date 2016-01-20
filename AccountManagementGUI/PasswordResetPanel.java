package AccountManagementGUI;

import MainMenuGUI.DisplayFrame;
import MainMenuGUI.MenuButton;
import MainMenuGUI.SplitString;
import MainMenuGUI.ValuePasswordPanel;
import commands.*;


import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class PasswordResetPanel extends JPanel{
	
	private static final long serialVersionUID = 1;
	
	private ValuePasswordPanel passwordPanel1;
	
	private ValuePasswordPanel passwordPanel2;
	
	private JTextArea error = null;
	
	public PasswordResetPanel(String username){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		
		add(Box.createVerticalGlue());
		
		JLabel prompt = new JLabel("Reset Password");
		prompt.setMaximumSize(prompt.getPreferredSize());
		add(prompt);
		prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());
		
		passwordPanel1 = new ValuePasswordPanel("New Password:",20,120,200);
		passwordPanel1.setMaximumSize(passwordPanel1.getPreferredSize());
		add(passwordPanel1);
		passwordPanel1.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());
		
		passwordPanel2 = new ValuePasswordPanel("Enter Again:",20,120,200);
		passwordPanel2.setMaximumSize(passwordPanel2.getPreferredSize());
		add(passwordPanel2);
		passwordPanel2.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());
		
		
		MenuButton submitButton= new MenuButton("Submit",60);
		submitButton.setForeground(Color.WHITE);
		submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(submitButton);
		submitButton.addMouseListener(new SubmitMouseListener(username));
		add(Box.createVerticalGlue());
		
		
	}
	
	private class SubmitMouseListener implements MouseListener{
		private String un = null;
		public  SubmitMouseListener (String username){
			un = username;
		}
		
		public void mouseClicked (MouseEvent event){
			if (error != null)
			{
				remove(error);  // remove error from previous submission
				error = null;
			}
			if(!passwordPanel1.getValueAsString().equals(passwordPanel2.getValueAsString()) ){
				error = new JTextArea(SplitString.at("Two passwords are different, enter again", 50));
				error.setMaximumSize(error.getPreferredSize());
				add(error);
				error.setAlignmentX(Component.CENTER_ALIGNMENT);
				add(Box.createVerticalGlue());
				revalidate();
			}
			else{
				ResetPasswordCommand c = new ResetPasswordCommand();
				c.resetPasswordCommand(un, passwordPanel1.getValueAsString());
				if(c.wasSuccessful()){
					String info = "<html>Congratulations!" + "<br>" + "The Password Changes Successfully!<html>";
					DisplayFrame frame = new DisplayFrame("Congratulation", info);
					frame.setLocation(300, 300);
					frame.setVisible(true);
				}
				else{
					error = new JTextArea(SplitString.at(c.getErrorMessage(), 50));
					error.setMaximumSize(error.getPreferredSize());
					add(error);
					error.setAlignmentX(Component.CENTER_ALIGNMENT);
					add(Box.createVerticalGlue());
					revalidate();
				}
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	

}
