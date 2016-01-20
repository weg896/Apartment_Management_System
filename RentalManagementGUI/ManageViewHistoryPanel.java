package RentalManagementGUI;

import java.util.LinkedList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;


import entities.FeesData;
import commands.ManageFeeCommand;
import containers.FeesList;

public class ManageViewHistoryPanel extends JPanel{
	

	private SpringLayout.Constraints cons;
	private int userid=0;
	
	public ManageViewHistoryPanel(int id){
		
		userid = id;
		if(userid==0){}
		viewNowFunction();
		revalidate();
	}


	private void viewNowFunction(){
		ManageFeeCommand tfc = new ManageFeeCommand();
		tfc.ManagerViewFeeHistory(null);
		LinkedList<FeesData> llfd = null;
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

		///////////////////////
        	final int rowsConst = 75;
        	final int columnConst = 35;
			int rows = 0;
			int column = 0;
			removeAll();
			
			JLabel f_first_name =  new JLabel("First Name");
			cons = spLayout.getConstraints(f_first_name);
	        cons.setX(Spring.constant(10 + column*rowsConst));
	        cons.setY(Spring.constant(10 + rows*columnConst));
	        add(f_first_name);
			column++;
			
			JLabel f_last_name =  new JLabel("Last Name");
			cons = spLayout.getConstraints(f_last_name);
	        cons.setX(Spring.constant(10 + column*rowsConst));
	        cons.setY(Spring.constant(10 + rows*columnConst));
	        add(f_last_name);
			column++;
			
			JLabel f_id =  new JLabel("Fee ID");
			cons = spLayout.getConstraints(f_id);
	        cons.setX(Spring.constant(10 + column*rowsConst));
	        cons.setY(Spring.constant(10 + rows*columnConst));
	        add(f_id);
			column++;

			JLabel f_pay_status = new JLabel("Pay Status");
			cons = spLayout.getConstraints(f_pay_status);
	        cons.setX(Spring.constant(10 + column*rowsConst));
	        cons.setY(Spring.constant(10 + rows*columnConst));
	        add(f_pay_status);
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
			
			JLabel f_pay_date= new JLabel("Pay Date");
			cons = spLayout.getConstraints(f_pay_date);
	        cons.setX(Spring.constant(10 + column*rowsConst));
	        cons.setY(Spring.constant(10 + rows*columnConst));
	        add(f_pay_date);
			column=0;
			rows++;
			
			Iterator<FeesData> itr = llfd.iterator();
			while(itr.hasNext()){
				
				FeesData fd = itr.next();
				
				f_first_name =  new JLabel(fd.getFirstName()+"");
				cons = spLayout.getConstraints(f_first_name);
		        cons.setX(Spring.constant(10 + column*rowsConst));
		        cons.setY(Spring.constant(10 + rows*columnConst));
		        add(f_first_name);
				column++;
				
				f_last_name =  new JLabel(fd.getLastName()+"");
				cons = spLayout.getConstraints(f_last_name);
		        cons.setX(Spring.constant(10 + column*rowsConst));
		        cons.setY(Spring.constant(10 + rows*columnConst));
		        add(f_last_name);
				column++;
				
				f_id =  new JLabel(fd.getFeeID()+"");
				cons = spLayout.getConstraints(f_id);
		        cons.setX(Spring.constant(10 + column*rowsConst));
		        cons.setY(Spring.constant(10 + rows*columnConst));
		        add(f_id);
				column++;

				f_pay_status = new JLabel(fd.getPaidStatus()+"");
				cons = spLayout.getConstraints(f_pay_status);
		        cons.setX(Spring.constant(10 + column*rowsConst));
		        cons.setY(Spring.constant(10 + rows*columnConst));
		        add(f_pay_status);
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
				
				f_pay_date= new JLabel(fd.getPaidDate());
				cons = spLayout.getConstraints(f_pay_date);
		        cons.setX(Spring.constant(10 + column*rowsConst));
		        cons.setY(Spring.constant(10 + rows*columnConst));
		        add(f_pay_date);
				column=0;
				rows++;
			}
	        SpringLayout.Constraints pCons = spLayout.getConstraints(this);
	        pCons.setConstraint(SpringLayout.SOUTH,
	                            Spring.constant(10 + rows*columnConst));
	        pCons.setConstraint(SpringLayout.EAST,
	                            Spring.constant(rowsConst*8));
		}
	public static final long serialVersionUID = 1;
	}

