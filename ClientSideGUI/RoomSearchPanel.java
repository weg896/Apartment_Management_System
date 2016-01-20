package ClientSideGUI;

import MainMenuGUI.*;
import containers.*;
import entities.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * the panel that completes building select and room search task @ClientSide
 * @author YUQING TAN
 */
public class RoomSearchPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String errorMessage = null;
	JTextArea error = null;
	private int userID = -1;
	@SuppressWarnings("rawtypes")
	private JComboBox bcSlctBldn;
	private Iterator<BuildingData> itrBuilding = null;
	private Iterator<RoomData> itrRoom = null;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RoomSearchPanel(int userID){
		
		this.userID = userID;
		setOpaque(false);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		add(new JLabel("Select Building"));
		bcSlctBldn = new JComboBox();
		try { itrBuilding = BuildingsList.getInstance().iterator(); } 
		catch (Exception e) { e.printStackTrace(); }
		while(itrBuilding.hasNext()) bcSlctBldn.addItem(itrBuilding.next().getName());
		add(bcSlctBldn);
		
		MenuButton btSearch = new MenuButton("Search",100);
		btSearch.addActionListener(new SearchListener());
		add(btSearch);
		
		if (userID == 0){
			MenuButton btLogout = new MenuButton("Logout",100);
			btLogout.addActionListener(new ActionListener(){
				public void actionPerformed (ActionEvent event){
					getTopLevelAncestor().setVisible(false);
					LoginFrame frame = new LoginFrame();
					frame.setLocation(300, 300);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				}
			});
			add(btLogout);
		}
	}
		
	private class SearchListener implements ActionListener
	{
		public void actionPerformed (ActionEvent event)
		{
			if (error != null)
			{
				remove(error);  // remove error from previous submission
				error = null;
			}
			int buildingID = -1;
			try {
				buildingID = BuildingsList.getInstance().get(bcSlctBldn.getSelectedIndex()).getId();
			} catch (Exception e) {
				e.printStackTrace();
			}
			LinkedList<RoomData> availRoom = new LinkedList<RoomData>();
			RoomData objRoom = null;
			try {
				itrRoom = RoomsList.getInstance().iterator();
			} catch (Exception e) {
				e.printStackTrace();
			}
			while (itrRoom.hasNext()){
				objRoom = itrRoom.next();
				if ( (objRoom.getBuildingId() == buildingID)
						&&(objRoom.getTenantNumber() < objRoom.getBedroomNumber()) ){
					availRoom.add(objRoom);
				}
			}
			RoomDisplayPanel pnApplyRoom = new RoomDisplayPanel(userID, availRoom);
			JScrollPane scApplyRoom = new JScrollPane(pnApplyRoom);
			scApplyRoom.setOpaque(false);
			scApplyRoom.getViewport().setOpaque(false);
			scApplyRoom.setBorder(null);
			ClientSidePanel.pnl4.removeAll();
			ClientSidePanel.pnl4.add(scApplyRoom);
			ClientSidePanel.pnl4.validate();
			ClientSidePanel.pnl4.repaint();
		}
	}
}
