package commands;

import startup.Database;
import java.sql.*;
import java.util.LinkedList;
import java.util.Iterator;

import containers.FeesList;
import entities.*;

/**
 * the class for tenants manipulate their fee
 * @author weixiong guan
 *
 */
public class TenantFeeCommand {
	
	// record the error message of the last command call
	private String errorMessage = null;
	
	// record the boolean status of the last command call 
	private boolean isSuccessful;
	
	
	public TenantFeeCommand(){
	
	};
		
	
	/**
	 * For tenant to view their payment detail which they still need to pay
	 * @param conn:Connection----connect to the database
	 * @param name:String----the user account name
	 * @return if there are error, null; else return LinkedList<String[]> that contain the detail of the tenant fee
	 */
	public void tenantViewFee(int id){
		
		isSuccessful = false;
		errorMessage = "TenantViewFee functin just start";
		
		LinkedList<FeesData> feeList = new LinkedList<FeesData>();
		String viewFeeQuery = "SELECT f_id, f_total_cost, f_payment_due_date " + 
				"FROM fees " + 
				"WHERE  f_paid = false AND f_user_id = " + id + ";";
		
		ResultSet rs = Database.runGetFromDatabaseSQL(viewFeeQuery);
		try{
			while(rs.next()) {
				FeesData result = new FeesData();
				result.setFeeID(rs.getInt("f_id"));
				result.setTotalCost(rs.getDouble("f_total_cost"));
				result.setPaymentDueDate(rs.getDate("f_payment_due_date").toString());
				feeList.add(result);
			}
			try{
				FeesList.reNewList(feeList);
				isSuccessful = true;
			}catch(Exception e){
				isSuccessful = false;
				errorMessage = e.toString();
			}
		}catch(SQLException e){
			isSuccessful = false;
			errorMessage = "the result set from TenantViewFee throught the exception";
		}

	}
		
	/**
	 * This function is to handle the specific payment for a tenant
	 * @param conn:Connection----connect to the database
	 * @param name:String----the user account name
	 * @param fees:int----the fee that tenant needs to pay
	 * @param pay:int----the amount that the tenant current pay
	 * @return if payment done successfully, true; otherwise, false
	 */
	public void tenantPayFeeStep1(int pid){
		
		isSuccessful = false;
		errorMessage = "tenantPayFeeStep1 functin just start";
		
		String payFeeQuery = "UPDATE fees " +
					"SET f_date = current_date, f_paid = true " + 
					"WHERE f_id = '" + pid + "';";
		Database.modifyDatabase(payFeeQuery);
		isSuccessful = true;
	}
		
	/**
	 * To get the tenant payment history
	 * @param name the name of the tanent 
	 * @return the LinkedList<FeesDate>, null if there is error
	 */
	public void tenantPayHistory(int id){
		
		isSuccessful = false;
		errorMessage = "tenantPayHistory functin just start";
		
		LinkedList<FeesData> feeList = new LinkedList<FeesData>();
		String viewFeeQuery = "SELECT f.f_id, f.f_paid, f.f_total_cost, f.f_payment_due_date, f.f_date " + 
				"FROM fees f, users u " + 
				"WHERE f.f_user_id = u.u_id AND u.u_id = " + id + ";";
		
		ResultSet rs = Database.runGetFromDatabaseSQL(viewFeeQuery);
		try{
			while(rs.next()) {
				FeesData result = new FeesData();
				result.setFeeID(rs.getInt("f_id"));
				result.setPaidStatus(rs.getBoolean("f_paid"));
				result.setTotalCost(rs.getDouble("f_total_cost"));
				result.setPaymentDueDate(rs.getDate("f_payment_due_date").toString());
				if(null == rs.getDate("f_date")){
					result.setPaidDate("not pay");
				}else{
					result.setPaidDate(rs.getDate("f_date").toString());
				}
				feeList.add(result);
			}
			
			try{
				FeesList.reNewList(feeList);
				isSuccessful = true;
			}catch(Exception e){
				isSuccessful = false;
				errorMessage = e.toString();
			}
		}catch(SQLException e){
			isSuccessful = false;
			errorMessage = "the result set from tenantPayHistory throught the exception";
		}
	}
	
	/**
	 * TO get the error message
	 * @return the string of error message
	 */
	public String getErrorMessage(){
		return errorMessage;
	}
	
	/**
	 * To get the boolean the function run successfully or not
	 * @return boolean of the function run successfully or not
	 */
	public boolean wasSuccessful(){
		return isSuccessful;
	}
	
	// test this class
	public static void main(String[] arge){
		TenantFeeCommand tfc = new TenantFeeCommand();
		
		// test tenantViewFee
		LinkedList<FeesData> llfd = null;
		tfc.tenantViewFee(3);
		try{
			llfd = FeesList.getInstance();
		}catch(Exception e){
			System.out.println(e.toString());
		}
		if(false == tfc.wasSuccessful()){
			System.out.print(tfc.getErrorMessage());
		}
		
		Iterator<FeesData> a = llfd.iterator();
		while(a.hasNext()){
			FeesData fd = a.next();
			System.out.print(fd.getFeeID()+"\t");
			System.out.print(fd.getTotalCost()+"\t");
			System.out.print(fd.getPaymentDueDate()+"\n");
		}

		
		////////////////////////////////////////////////////////////////////////
		System.out.println("\n");
		// test tenantPayFeeStep1
		tfc.tenantPayFeeStep1(3);
		tfc.tenantPayFeeStep1(4);
		
		if(false == tfc.wasSuccessful()){
			System.out.print(tfc.getErrorMessage());
		}
		
		
		////////////////////////////////////////////////////////////////////////
		System.out.println("\n");
		// test tenantPayHistory
		tfc.tenantPayHistory(3);
		try{
			llfd = FeesList.getInstance();
		}catch(Exception e){
			System.out.println(e.toString());
		}
		if(false == tfc.wasSuccessful()){
			System.out.print(tfc.getErrorMessage());
		}
		
		a = llfd.iterator();
		while(a.hasNext()){
			FeesData fd = a.next();
			System.out.print(fd.getFeeID()+"\t");
			if(fd.getPaidStatus()){
				System.out.print("yes\t");
			}else{
				System.out.print("no\t");
			}
			System.out.print(fd.getTotalCost()+"\t");
			System.out.print(fd.getPaymentDueDate()+"\t");
			System.out.print(fd.getPaidDate()+"\n");
		}
	}
}

