package commands;

import startup.Database;
import java.sql.*;
import java.util.LinkedList;
import java.util.Iterator;

import containers.FeesList;
import containers.*;
import entities.*;

public class ManageFeeCommand {
		
	// record the error message of the last command call
	private String errorMessage = null;
	
	// record the boolean status of the last command call 
	private boolean isSuccessful;
	
	
	public ManageFeeCommand(){
	};
		
	
	/**
	 * It let the manager to change the fee which the tenant did not pay
	 * @param conn
	 * @param amountChange the amount for update
	 * @return the ture if update successful, false otherwise
	 */
	public void ManagerChangeFee(double amountChange, int id, int building){	
		isSuccessful = false;
		errorMessage = "ManagerChangeFee functin just start";
		String changeFee = "UPDATE apartmentrooms "+
				"SET ar_cost = " + amountChange + 
				" WHERE ar_id = " + id +
				" AND ar_building_id = " + building + ";";
		Database.modifyDatabase(changeFee);
		isSuccessful = true;
	}
	
	/**
	 * The manager charge a specific tenant 
	 * @param charge double----the amount of additional charge
	 * @param nam string----the tenant's login name
	 */
	public void ManagerChargeTenantFee(double charge, String name){
		isSuccessful = false;
		errorMessage = "ManagerChargeTenantFee functin just start";
		String changeFee = "UPDATE fees " +
				"SET f_total_cost = " + charge + "+ f_total_cost " +
				"FROM users us " +
				"WHERE f_paid = false AND f_user_id = us.u_id AND us.u_username = '" + 
				name + "';";
		Database.modifyDatabase(changeFee);
		isSuccessful = true;
	}
	
	/**
	 * To get the history of the tenant payment
	 * @param name the string of the username
	 * @return the linkedlist<FeesData> the history of the payment
	 */
	public void ManagerViewFeeHistory(String name){
		isSuccessful = false;
		errorMessage = "ManagerViewFeeHistory functin just start";
		String viewHistory = "Select u.u_first_name, u.u_last_name, " + 
				"f.f_id, f.f_total_cost, f.f_date, f.f_paid, f.f_payment_due_date " + 
				"FROM fees f, users u " + 
				"WHERE f.f_user_id = u.u_id ";
		if(null != name){
			viewHistory = viewHistory + "AND u.u_username = '" + name + "'";
		}		
		viewHistory = viewHistory + ";";
		ResultSet rs = Database.runGetFromDatabaseSQL(viewHistory);
		
		if(null==rs){
			System.out.print(viewHistory);
		}
		LinkedList<FeesData> feeList = new LinkedList<FeesData>();
		try{
			while(rs.next()) {
				FeesData result = new FeesData();
				result.setFirstName(rs.getString("u_first_name"));
				result.setLastName(rs.getString("u_last_name"));
				result.setFeeID(rs.getInt("f_id"));
				result.setPaidStatus(rs.getBoolean("f_paid"));
				result.setTotalCost(rs.getDouble("f_total_cost"));
				result.setPaymentDueDate(rs.getDate("f_payment_due_date").toString());
				if(null != rs.getDate("f_date")){
					result.setPaidDate(rs.getDate("f_date").toString());
				}else{
					result.setPaidDate("not paid");
				}
				feeList.add(result);
			}
			
			isSuccessful = true;
			try{
				FeesList.reNewList(feeList);
			}catch(Exception e){
				System.out.println(e.toString());
			}
		}catch(SQLException e){
			isSuccessful = false;
			errorMessage = "the result set from ManagerViewFeeHistory throught the exception";
		}
	}

	/**
	 *  To get all the room that for a specific building
	 * @param id the building id
	 * @return 
	 */
	public void getAllRoom(int id){
		isSuccessful = false;
		errorMessage = "getAllBuildingName functin just start";
		String nameQuery = "select ar_id " + 
				"from apartmentrooms " + 
				"where ar_building_id = " + id +";";
		ResultSet rs = Database.runGetFromDatabaseSQL(nameQuery);
		
		LinkedList<BasicRoomData> nameList = new LinkedList<BasicRoomData>();
		try{
			while(rs.next()) {
				
				BasicRoomData result = new BasicRoomData(rs.getInt("ar_id"));
				nameList.add(result);
			}
			try{
				BasicRoomsList.reNewList(nameList);
				isSuccessful = true;
			}catch(Exception e){
				isSuccessful = false;
				errorMessage = e.toString();
			}
		}catch(SQLException e){
			isSuccessful = false;
			errorMessage = "the result set from getAllBuildingName throught the exception";
		}
	}
	
	
	/**
	 * Set up the payment for the 
	 * @param id
	 */
	public void ManageSetupPayment(int id, double price){
		isSuccessful = false;
		errorMessage = "ManageSetupPayment functin just start";
		String setupQuery = "insert into fees(f_user_id, f_paid, f_payment_due_date, f_total_cost) " +
				"values(" + id + ", false, current_date+30, " + price + ");";
		Database.modifyDatabase(setupQuery);
		isSuccessful = true;
	}
	
	/**
	 * Helping function to get all the user name that has rent the rooms
	 * return 
	 */
	public void getAllTenantNameWhoRent(){
		isSuccessful = false;
		errorMessage = "getAllTenantNameWhoRent functin just start";
		String nameQuery = "SELECT DISTINCT us.u_first_name,us.u_last_name, us.u_id FROM users us, usersrooms ur " + 
				"WHERE us.u_id = ur.ur_user_id;";
		ResultSet rs = Database.runGetFromDatabaseSQL(nameQuery);
		LinkedList<FeesData> nameList = new LinkedList<FeesData>();
		try{
			while(rs.next()) {
				FeesData result = new FeesData();
				result.setFirstName(rs.getString("u_first_name"));
				result.setLastName(rs.getString("u_last_name"));
				result.setUserID(rs.getInt("u_id"));
				nameList.add(result);
			}
			try{
				FeesList.reNewList(nameList);
				isSuccessful = true;
			}catch(Exception e){
				isSuccessful = false;
				errorMessage = e.toString();
			}		
		}catch(SQLException e){
			isSuccessful = false;
			errorMessage = "the result set from getAllTenantNameWhoRent throught the exception";
		}
		
	}
	
	/**
	 *  To get the current month payment of a specific tenant
	 * @param id the tenant id
	 * @return the price of that month
	 */
	public double getMonthPayment(int id){
		isSuccessful = false;
		double price = 0.0;
		errorMessage = "getMonthPayment functin just start";
		String nameQuery = "select sum(ar_cost) as sumprice from apartmentrooms ap, usersrooms ur " +
				"where ur.ur_room_id = ap.ar_id and " + 
				"ur.ur_user_id = " + id +";";
		ResultSet rs = Database.runGetFromDatabaseSQL(nameQuery);
		try{
			while(rs.next()){
				price = rs.getDouble("sumprice");
			}
			if(0 < price ){
				isSuccessful = true;
				return price;
			}else{
				isSuccessful = false;
				errorMessage = "the price could not be less than 0 in getMonthPayment";
				return 0.0;
			}
		}catch(SQLException e){
			isSuccessful = false;
			errorMessage = "the result set from getMonthPayment throught the exception";
			return 0.0;
		}
	}		
			
	/**
	 * To get all the user that rent the room
	 * @param arge
	 */
	public void getAllRentTanent(){
		isSuccessful = false;
		errorMessage = "getAllRentTanent functin just start";
		String nameQuery = "select u.u_id, u.u_first_name, u.u_last_name " +
				"from users u, usersrooms ur " +
				"where ur.ur_user_id = u.u_id and u.u_permission_status = 2;";
		ResultSet rs = Database.runGetFromDatabaseSQL(nameQuery);
		LinkedList<BasicTenantDate> nameList = new LinkedList<BasicTenantDate>();
		try{
			while(rs.next()) {
				BasicTenantDate result = new BasicTenantDate(rs.getInt("u_id"), rs.getString("u_first_name"), rs.getString("u_last_name"));
				nameList.add(result);
			}
			try{
				BasicTenantsList.reNewList(nameList);
				isSuccessful = true;
			}catch(Exception e){
				isSuccessful = false;
				errorMessage = e.toString();
			}
		}catch(SQLException e){
			isSuccessful = false;
			errorMessage = "the result set from getAllRentTanent throught the exception";
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
	

	
	// test the class
	public static void main(String arge[]){
		ManageFeeCommand mfc = new ManageFeeCommand();
		mfc.ManagerChangeFee(9999.0, 5502, 1);
		mfc.ManagerChargeTenantFee(900.9, "ab7");
		
		////////////////////////////////////////////////////////////////////////
		LinkedList<FeesData> llfd = null;
		mfc.getAllTenantNameWhoRent();
		try{
			llfd = FeesList.getInstance();
		}catch(Exception e){
			System.out.print(e.toString());
		}
		Iterator<FeesData> a = llfd.iterator();
		
		while(a.hasNext()){
			FeesData fd = a.next();
			System.out.print(fd.getFirstName()+"\t");
			System.out.print(fd.getLastName()+"\t");
			System.out.print(fd.getUserID()+"\n");
		}
		////////////////////////////////////////////////////////////////////////
		System.out.println("\n");
		mfc.ManagerViewFeeHistory(null);
		try{
			llfd = FeesList.getInstance();
		}catch(Exception e){
			System.out.print(e.toString());
		}
		if(false == mfc.wasSuccessful()){
			System.out.print(mfc.getErrorMessage());
		}
		
		a = llfd.iterator();
		while(a.hasNext()){
			FeesData fd = a.next();
			System.out.print(fd.getFirstName()+"\t");
			System.out.print(fd.getLastName()+"\t");
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
		
		//////////////////////////////////////////////////////////////////////
		System.out.println("\n");
		mfc.ManagerViewFeeHistory(null);
		try{
			llfd = FeesList.getInstance();
		}catch(Exception e){
			System.out.print(e.toString());
		}
		if(false == mfc.wasSuccessful()){
			System.out.print(mfc.getErrorMessage());
		}
		
		a = llfd.iterator();
		while(a.hasNext()){
			FeesData fd = a.next();
			System.out.print(fd.getFirstName()+"\t");
			System.out.print(fd.getLastName()+"\t");
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
		
		////////////////////////////////////////////////////////////////////////
		System.out.println("\n");		
		mfc.getAllTenantNameWhoRent();
		try{
			llfd = FeesList.getInstance();
		}catch(Exception e){
			System.out.print(e.toString());
		}
		a = llfd.iterator();
		while(a.hasNext()){
			FeesData fd = a.next();
			System.out.print(fd.getFirstName()+"\t");
			System.out.print(fd.getLastName()+"\t");
			System.out.print(fd.getUserID()+"\n");
		}
		
		////////////////////////////////////////////////////////////////////////
		System.out.println("\n");		
		LinkedList<BasicTenantDate> lltd = null;
		mfc.getAllRentTanent();
		try{
			lltd = BasicTenantsList.getInstance();
		}catch(Exception e){
			System.out.print(e.toString());
		}
		Iterator<BasicTenantDate> c = lltd.iterator();
		while(c.hasNext()){
			BasicTenantDate td = c.next();
			System.out.print(td.getFullName() +"\t");
			System.out.print(td.getUserID()+"\n");
		}
		
		////////////////////////////////////////////////////////////////////////
		System.out.println("\n");		
		mfc.getAllRoom(1);
		LinkedList<BasicRoomData> llbr = null;
		try{
			llbr = BasicRoomsList.getInstance();
		}catch(Exception e){
			System.out.println(e.toString());
		}
		Iterator<BasicRoomData> d = llbr.iterator();
		while(c.hasNext()){
			BasicRoomData br = d.next();
			System.out.print(br.getRoomID() +"\n");
		}
		
		////////////////////////////////////////////////////////////////////////
		System.out.println("\n");
		System.out.println("total price for 7"+ mfc.getMonthPayment(7));
		System.out.println("total price for 8"+ mfc.getMonthPayment(8));
		System.out.println("total price for 22"+ mfc.getMonthPayment(22));
	}
	
}
