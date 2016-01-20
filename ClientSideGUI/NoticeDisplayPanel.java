package ClientSideGUI;

import MainMenuGUI.*;
import entities.*;
import containers.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


/**
 * the panel that displays notifications for a specific receiver @ClientSide
 * @author YUQING TAN
 */
public class NoticeDisplayPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	public NoticeDisplayPanel(int userID, LinkedList<NoticeData> lsNotice){
		
		setOpaque(false);
		NoticeData curNotice = null;
		Iterator<NoticeData> itrNotice = null;
		if (lsNotice == null){
			try {lsNotice = NoticeList.getInstance();}
			catch (Exception e1) {e1.printStackTrace();}}
		try {itrNotice = lsNotice.iterator();} 
		catch (Exception e) {e.printStackTrace();}
		
		add(new JLabel("Title"));
		add(new JLabel("Date"));
		add(new JLabel("Type"));
		add(new JLabel("View Message"));
        
		int rows = 1;
		while(itrNotice.hasNext()){
			curNotice = itrNotice.next(); 
			if (curNotice.getReceiver() == userID){
				add(new JLabel(curNotice.getTitle()));
				add(new JLabel(curNotice.getDate()));
				add(new JLabel(curNotice.getType()));
				
				MenuButton btNoticeDetail = new MenuButton("View Detail",100);
				
				final int nid = curNotice.getMsgID();
				final String title = curNotice.getTitle();
				final String msg = curNotice.getContent();
				final int sndr = curNotice.getSender();
				final int rcvr = curNotice.getReceiver();
				final boolean read = curNotice.getRead();
				final String type = curNotice.getType();
				final String date = curNotice.getDate();
				
				btNoticeDetail.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent evt){
						NoticeDetailPanel pnNoticeDetail = new NoticeDetailPanel(nid, title, msg, sndr, rcvr, read, type, date);
						ClientSidePanel.pnl3.removeAll();
						ClientSidePanel.pnl3.add(pnNoticeDetail);
						getTopLevelAncestor().setSize(1200, 600);
						getTopLevelAncestor().validate();
						getTopLevelAncestor().repaint();
					}
			    });
				add(btNoticeDetail);
				rows++;
			}
		}
		
		setLayout(new SpringLayout());
		SpringLayout spLayout;
        try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }
        Spring initialXSpring = Spring.constant(6);
        Spring initialYSpring = Spring.constant(6);
        Spring xPadSpring = Spring.constant(120);
        Spring yPadSpring = Spring.constant(20);
        SpringLayout.Constraints lastCons = null;
        SpringLayout.Constraints lastRowCons = null;
        for (int i = 0; i < rows*4; i++) {
            SpringLayout.Constraints cons = spLayout.getConstraints(this.getComponent(i));
            if (i % 4 == 0) { //start of new row
                lastRowCons = lastCons;
                cons.setX(initialXSpring);
                cons.setWidth(xPadSpring);
            } else { //x position depends on previous component
                cons.setX(Spring.sum(lastCons.getConstraint(SpringLayout.WEST),xPadSpring));
                cons.setWidth(xPadSpring);
            }
            if (i / 4 == 0) { //first row
                cons.setY(initialYSpring);
                cons.setHeight(yPadSpring);
            } else { //y position depends on previous row
                cons.setY(Spring.sum(lastRowCons.getConstraint(SpringLayout.SOUTH),yPadSpring));
                cons.setHeight(yPadSpring);
            }
            lastCons = cons;
        }
        
        SpringLayout.Constraints pCons = spLayout.getConstraints(this);
        pCons.setConstraint(SpringLayout.SOUTH,
                            Spring.constant(20 + rows*20));
        pCons.setConstraint(SpringLayout.EAST,
                            Spring.constant(450));
	}
}
