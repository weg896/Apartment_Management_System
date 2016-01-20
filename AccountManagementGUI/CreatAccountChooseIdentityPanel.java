package AccountManagementGUI;




import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;

import MainMenuGUI.MenuButton;


public class CreatAccountChooseIdentityPanel extends JPanel{
	

	@SuppressWarnings("rawtypes")
	private JComboBox boxIdentity;
	
	private int permission;
	
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CreatAccountChooseIdentityPanel(){
		MenuButton buttons = null;
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(Box.createVerticalGlue());
		
		JLabel prompt = new JLabel("Identity For New Account:");
		add(prompt);
		
		String[] level = {"manager","staff","tenant"};
		boxIdentity = new JComboBox(level);
		boxIdentity.setMaximumSize(boxIdentity.getPreferredSize());
		add(boxIdentity);
		
		buttons = new MenuButton("Create Account",120);
		buttons.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				if( boxIdentity.getSelectedItem() == "manager"){
					permission = 0;
					CreateNewAccountForStaffPanel panel = new CreateNewAccountForStaffPanel(permission);
					AccountManagementMainPanel.pnl4.removeAll();
					AccountManagementMainPanel.pnl4.add(panel);
					AccountManagementMainPanel.pnl4.validate();
					AccountManagementMainPanel.pnl4.repaint();
				}else if(boxIdentity.getSelectedItem() == "staff"){
					permission = 1;
					CreateNewAccountForStaffPanel panel = new CreateNewAccountForStaffPanel(permission);
					AccountManagementMainPanel.pnl4.removeAll();
					AccountManagementMainPanel.pnl4.add(panel);
					AccountManagementMainPanel.pnl4.validate();
					AccountManagementMainPanel.pnl4.repaint();
				}
				else {
					permission = 2;
					CreateNewAccountForTenantfPanel panel = new CreateNewAccountForTenantfPanel(permission);
					AccountManagementMainPanel.pnl4.removeAll();
					AccountManagementMainPanel.pnl4.add(panel);
					AccountManagementMainPanel.pnl4.validate();
					AccountManagementMainPanel.pnl4.repaint();
				}
			}
	    });
		buttons.setMaximumSize(new Dimension(150,30));
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(buttons);

		
		
	}
	
	public static final long serialVersionUID = 1;

}
