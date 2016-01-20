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

import entities.BuildingData;
import entities.BasicRoomData;
import commands.ManageFeeCommand;
import containers.*;
import MainMenuGUI.ValueEntryPanel;

public class ManageChangeFeePanel extends JPanel{
private static final long serialVersionUID = 1L;
	
	private String errorMessage = null;

	private JTextArea error = null;

	private JComboBox bcSlctBldn;
	
	private JComboBox bcRoom; 
	
	private ManageFeeCommand cmd;
	
	private ValueEntryPanel text;
	
	private JPanel lay1;
	
	private JPanel lay2;
	
	private JPanel lay3;
	
	private JPanel stor;
	
	public ManageChangeFeePanel(){
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); 
		add(Box.createVerticalGlue());
		JLabel lbSlctBldn = new JLabel("Select Building and room");
		
		error = new JTextArea("");
		cmd = new ManageFeeCommand();
		bcSlctBldn = new JComboBox();
		stor = new JPanel();
		bcRoom = new JComboBox();
		text = new ValueEntryPanel("Amount of Change", 20,150,100);
		lay1 = new JPanel();
		lay1.setLayout(new FlowLayout());
		
		LinkedList<BuildingData>  ls = null;
		try{	
			ls = BuildingsList.getInstance();
		}catch(Exception e){
			System.out.println(e.toString());
		}
		Iterator<BuildingData> itrBuilding = ls.iterator();
		while(itrBuilding.hasNext()){
			bcSlctBldn.addItem(itrBuilding.next());
		}
		lay1.add(lbSlctBldn);
		lay1.add(bcSlctBldn);
		lay1.add(stor);
		stor.add(bcRoom);
		bcSlctBldn.addActionListener(new SelectedListener());
		
		add(lay1);
		
		lay2 = new JPanel();
		
		lay2.setLayout(new FlowLayout());
		
		int buttonCount = 1;
		MenuButton[] buttons = new MenuButton[buttonCount];
		
		buttons[0] = new MenuButton("Change",60);
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
						Integer tempI = (Integer) bcRoom.getSelectedItem();
						BuildingData tempBD= (BuildingData) bcSlctBldn.getSelectedItem();
						cmd.ManagerChangeFee(amountChange, tempI.intValue(), tempBD.getId());
						errorMessage = "Change "+ tempBD.getName() +"-"+ tempI + " total $:" + amountChange + " Fee Success!";
						error = new JTextArea(errorMessage);
					}
				}catch(Exception e){
					errorMessage = "Unvalid input! or did not choice a building-room";
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
			int id = ((BuildingData) bcSlctBldn.getSelectedItem()).getId();
			
			LinkedList<BasicRoomData> lsbr =null;
			cmd.getAllRoom(id);
			try{
				lsbr = BasicRoomsList.getInstance();
			}catch(Exception e){
				System.out.println(e.toString());
			}
			
			Iterator<BasicRoomData> itrRoom = lsbr.iterator();
			
			stor.remove(bcRoom);
			bcRoom = new JComboBox();
			while(itrRoom.hasNext()){
				BasicRoomData da = itrRoom.next();
				bcRoom.addItem(da.getRoomID());
			}
			bcRoom.revalidate();
			stor.add(bcRoom);
			stor.revalidate();
			revalidate();
		}
	}
}
