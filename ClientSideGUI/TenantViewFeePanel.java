package ClientSideGUI;


import java.util.LinkedList;
import java.util.Iterator;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import MainMenuGUI.MenuButton;


import entities.FeesData;
import commands.TenantFeeCommand;
import containers.FeesList;

public class TenantViewFeePanel extends JPanel{
	private JPanel errorPanel = new JPanel();
	private SpringLayout.Constraints cons;
	private int userid;
	
	public TenantViewFeePanel(int id){
		setOpaque(false);
		userid = id;
		viewNowFunction();
		revalidate();
	}


	private void viewNowFunction(){
		TenantFeeCommand tfc = new TenantFeeCommand();
		LinkedList<FeesData> llfd = null;
		tfc.tenantViewFee(this.userid);
		try{
			llfd = FeesList.getInstance();
		}catch(Exception e){
			System.out.print(e.toString());
		}
		if(false == tfc.wasSuccessful()){
			System.out.print(tfc.getErrorMessage());
		}
		
		setLayout(new SpringLayout());
		SpringLayout spLayout;
        try {
        	spLayout = (SpringLayout)this.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeGrid must use SpringLayout.");
            return;
        }

        	final int rowsConst = 75;
        	final int columnConst = 50;
			int rows = 0;
			int column = 0;
			removeAll();
			
			JLabel f_id =  new JLabel("Fee ID");
			cons = spLayout.getConstraints(f_id);
	        cons.setX(Spring.constant(10 + column*rowsConst));
	        cons.setY(Spring.constant(10 + rows*columnConst));
			add(f_id);
			column++;

			JLabel f_total_cost = new JLabel("Total Cost");
			cons = spLayout.getConstraints(f_total_cost);
	        cons.setX(Spring.constant(10 + column*rowsConst));
	        cons.setY(Spring.constant(10 + rows*columnConst));
			add(f_total_cost);
			column++;
			
			JLabel f_due_date= new JLabel("Due Date");
			cons = spLayout.getConstraints(f_due_date);
	        cons.setX(Spring.constant(10 + column*rowsConst));
	        cons.setY(Spring.constant(10 + rows*columnConst));
			add(f_due_date);
			column++;
			
			JLabel f_pay_now= new JLabel("Pay Now");
			cons = spLayout.getConstraints(f_pay_now);
	        cons.setX(Spring.constant(10 + column*rowsConst));
	        cons.setY(Spring.constant(10 + rows*columnConst));
			add(f_pay_now);
			column=0;
			rows++;
			
			Iterator<FeesData> itr = llfd.iterator();
			while(itr.hasNext()){
				
				FeesData fd = itr.next();
				
				f_id =  new JLabel(fd.getFeeID()+"");
				cons = spLayout.getConstraints(f_id);
		        cons.setX(Spring.constant(10 + column*rowsConst));
		        cons.setY(Spring.constant(10 + rows*columnConst));
				add(f_id);
				column++;

				f_total_cost = new JLabel(fd.getTotalCost()+"");
				cons = spLayout.getConstraints(f_total_cost);
		        cons.setX(Spring.constant(10 + column*rowsConst));
		        cons.setY(Spring.constant(10 + rows*columnConst));
				add(f_total_cost);
				column++;
				
				f_due_date= new JLabel(fd.getPaymentDueDate());
				cons = spLayout.getConstraints(f_due_date);
		        cons.setX(Spring.constant(10 + column*rowsConst));
		        cons.setY(Spring.constant(10 + rows*columnConst));
				add(f_due_date);
				column++;
				
				MenuButton payNowButton = new MenuButton("pay now", 100);
				cons = spLayout.getConstraints(payNowButton);
		        cons.setX(Spring.constant(10 + column*rowsConst));
		        cons.setY(Spring.constant(10 + rows*columnConst));
				add(payNowButton);
				column++;
				column=0;
				rows++;

				payNowFuncton(payNowButton, fd.getFeeID());
			}
			
	        SpringLayout.Constraints pCons = spLayout.getConstraints(this);
	        pCons.setConstraint(SpringLayout.SOUTH,
	                            Spring.constant(10 + rows*30));
	        pCons.setConstraint(SpringLayout.EAST,
	                            Spring.constant(400));
		}

	
	private void payNowFuncton(final MenuButton payNowButton, final int id){
		payNowButton.setMaximumSize(payNowButton.getPreferredSize());
		
		payNowButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		payNowButton.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent evt){
						TenantFeeCommand tfc = new TenantFeeCommand();
						tfc.tenantPayFeeStep1(id);
						
						if(tfc.wasSuccessful()){
							payNowButton.setVisible(false);
						}else{
							errorPanel.removeAll();
							errorPanel.add(new JLabel(tfc.getErrorMessage()));
						}
						revalidate();
					}
		    });
	}
	public static final long serialVersionUID = 1;
	
}
