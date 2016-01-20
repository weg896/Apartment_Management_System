package ClientSideGUI;

//import java.sql.*;
import java.util.LinkedList;
import java.util.Iterator;

/*import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
*/

//import javax.swing.Box;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JTextArea;
import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.JScrollPane;
import javax.swing.Spring;
import javax.swing.SpringLayout;


import entities.FeesData;
import commands.TenantFeeCommand;
import containers.FeesList;

public class TenantViewHistoryPanel extends JPanel{
	
//	private String userName = null;
//	private JTextField doubleInput = null;
//	private JScrollPane scroPanel = new JScrollPane();
//	private JPanel resultPanel = new JPanel();
//	private JPanel errorPanel = new JPanel();
	private SpringLayout.Constraints cons;
	private int userid;
	
	public TenantViewHistoryPanel(int id){
		setOpaque(false);
		userid = id;
		viewNowFunction();
		revalidate();
	}


	private void viewNowFunction(){
		TenantFeeCommand tfc = new TenantFeeCommand();
		LinkedList<FeesData> llfd = null;
		tfc.tenantPayHistory(this.userid);
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
        	final int columnConst = 35;
			int rows = 0;
			int column = 0;
			removeAll();
			
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
	                            Spring.constant(400));
		}
	public static final long serialVersionUID = 1;
	}

