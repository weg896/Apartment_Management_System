package entities;

public class FeesData {
	
	// these fields are directly associated to the database's table "fees"
	private int f_id;
	private int f_user_id;
	private boolean f_paid;
	private String f_date;
	private String f_payment_due_date;
	private double f_total_cost;
	
	// extra info
	private String firstname;
	private String lastname;
	private String username;
	
	public FeesData(){
		
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	/**
	 * To set the fee's ID number
	 */
	public void setFeeID(int f_id){
		this.f_id = f_id;
	}
	
	/**
	 * To set the user's ID number
	 */
	public void setUserID(int f_user_id){
		this.f_user_id = f_user_id;
	}
	
	/**
	 *  To set the paid status of the user
	 */
	public void setPaidStatus(boolean f_paid){
		this.f_paid = f_paid;
	}
	
	/**
	 * To set the date of payment done
	 */
	public void setPaidDate(String f_data){
		this.f_date = f_data;
	}
	
	/**
	 * To set the payment due date
	 */
	public void setPaymentDueDate(String f_payment_due_date){
		this.f_payment_due_date = f_payment_due_date;
	}
	
	/**
	 * To set the amount that the user should pay
	 */
	public void setTotalCost(double f_total_cost){
		this.f_total_cost = f_total_cost;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	/**
	 * To get the fee's ID number
	 * @return the fee's ID number 
	 */
	public int getFeeID(){
		return f_id;
	}
	
	/**
	 * To get the user's ID number
	 * @return the user's ID number
	 */
	public int getUserID(){
		return f_user_id;
	}
	
	/**
	 *  To get the paid status of the user
	 * @return true if the user had paid the fee, false otherwise
	 */
	public boolean getPaidStatus(){
		return f_paid;
	}
	
	/**
	 * To get the date of payment done
	 * @return the date that the user paid the, null if the user does pay
	 */
	public String getPaidDate(){
		return f_date;
	}
	
	/**
	 * To get the payment due date
	 * @return the payment due date
	 */
	public String getPaymentDueDate(){
		return f_payment_due_date;
	}
	
	/**
	 * To get the amount that the user should pay
	 * @return the total amount that the user should pay
	 */
	public double getTotalCost(){
		return f_total_cost;
	}
	
	
	// extra functions
	/**
	 * To set the tenant first name
	 * @param first the string of the tenant first name
	 */
	public void setFirstName(String first){
		this.firstname = first;
	}
	
	/**
	 * To set the tenant last name
	 * @param last the string of the tenant last name
	 */
	public void setLastName(String last){
		this.lastname = last;
	}
	
	/**
	 * To set the tenant user name
	 * @param user the string of the tenant user name
	 */
	public void setUserName(String user){
		this.username = user;
	}
	
	///////////////////////
	/**
	 * To get the tenant first name
	 * @return the string of the tenant first name
	 */
	public String getFirstName(){
		return firstname;
	}
	
	/**
	 * To get the tenant first name
	 * @return the string of the tenant last name
	 */
	public String getLastName(){
		return lastname;
	}
	
	/**
	 * To get the tenant user name
	 * @param user the string of the tenant user name
	 */
	public String getUserName(){
		return username;
	}
	//////////////////////////////////////////////////
}
