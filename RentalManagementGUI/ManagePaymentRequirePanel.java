package RentalManagementGUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import MainMenuGUI.MenuButton;
import MainMenuGUI.ValueEntryPanel;

import entities.BasicTenantDate;
import commands.ManageFeeCommand;
import containers.BasicTenantsList;

public class ManagePaymentRequirePanel extends JPanel{
private static final long serialVersionUID = 1L;
	
	private String errorMessage = null;

	private JTextArea error = null;
	
	private JTextArea monthPriceText;

	private JComboBox bcSlctBldn;
	 
	private ManageFeeCommand cmd;
	
	private ValueEntryPanel text;
	
	private JPanel lay1;
		
	private JPanel lay2;
	
	private JPanel lay3;
	
	private JPanel stor;
	
	private double currentPrice = 0.0;
	
	private double totalPrice = 0.0;
	
	public ManagePaymentRequirePanel(){
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); 
		add(Box.createVerticalGlue());
		JLabel lbSlctBldn = new JLabel("Select Tenant");
		
		monthPriceText = new JTextArea("");
		error = new JTextArea("");
		cmd = new ManageFeeCommand();
		bcSlctBldn = new JComboBox();
		stor = new JPanel();
		text = new ValueEntryPanel("Additional Charge", 20, 150,100);
		
		lay1 = new JPanel();
		lay1.setLayout(new FlowLayout());
		
		LinkedList<BasicTenantDate>  ls = null;
		cmd.getAllRentTanent();
		try{
			ls = BasicTenantsList.getInstance();
		}catch(Exception e){
			System.out.print(e.toString());
		}
		Iterator<BasicTenantDate> itr = ls.iterator();
		while(itr.hasNext()){
			bcSlctBldn.addItem(itr.next());
		}
		lay1.add(lbSlctBldn);
		lay1.add(bcSlctBldn);
		lay1.add(new JLabel("Month Price"));
		lay1.add(stor);
		stor.add(monthPriceText);
		bcSlctBldn.addActionListener(new SelectedListener());
		add(lay1);
		
		
		lay2 = new JPanel();
		
		lay2.setLayout(new FlowLayout());
		
		int buttonCount = 1;
		MenuButton[] buttons = new MenuButton[buttonCount];
		
		buttons[0] = new MenuButton("Charge",60);
		buttons[0].setMaximumSize(buttons[0].getPreferredSize());
		lay2.add(text);
		lay2.add(buttons[0]);
		add(lay2);
		
		lay3 = new JPanel();
		lay3.add(error);
		add(lay3);
		
		buttons[0].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				double amountChange;
				lay3.removeAll();
				try{
					amountChange = text.getValueAsDouble();
					if(amountChange < 0){
						errorMessage = "amount change can not be less than 0!";
						error = new JTextArea(errorMessage);
					}else{						
						BasicTenantDate temp = (BasicTenantDate) bcSlctBldn.getSelectedItem();
						totalPrice = amountChange+currentPrice;
						cmd.ManageSetupPayment(temp.getUserID(), totalPrice); 
						errorMessage = "Charge " + temp.getFullName() + " total $:" + totalPrice + " Success!";
						error = new JTextArea(errorMessage);
					}
				}catch(Exception e){
					errorMessage = "Unvalid input or did not choice a tenant!";
					error = new JTextArea(errorMessage);
				}
				lay3.add(error);
				error.revalidate();
				lay3.revalidate();
				revalidate();
				repaint();
			} 
		});
		
	}
		
	private class SelectedListener implements ActionListener
	{
		public SelectedListener(){
		}
		public void actionPerformed (ActionEvent event)
		{
			if (error != null)
			{
				remove(error);  // remove error from previous submission
				error = null;
			}
			int id = ((BasicTenantDate) bcSlctBldn.getSelectedItem()).getUserID();
	
			currentPrice = cmd.getMonthPayment(id);
			
			stor.removeAll();
			monthPriceText = new JTextArea("$:" + currentPrice);
			stor.add(monthPriceText);
			stor.revalidate();
			revalidate();
			repaint();
		}
	}
}
