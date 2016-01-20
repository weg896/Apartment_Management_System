package commands;


/**
 * Group: 03
 * Class: CMPT 370
 */

import java.sql.*;
import java.util.*;
import startup.Database;
import containers.StaffList;
import entities.StaffData;


/**
 * Contains the list of staff in the system
 * 
 * 
 */
public class StaffProfileCommand{
	/**
	 * determines whether or not command is successful
	 */
	private boolean successful = false;
	
	/**
	 * Error message returned if command fails
	 */
	private String errorMessage = null;
	
	LinkedList<StaffData> sList;
		
	/**
	 * Initalizes the buildings that are currently in the system
	 */
	public StaffProfileCommand()
	{
		try {
			sList = StaffList.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private boolean checkIdentity(int userID){
		errorMessage = null;
		ResultSet check = Database.runGetFromDatabaseSQL("SELECT u_id FROM Users WHERE u_id = " + userID);
		// checks item is in the database
		try {
			if(check.next()){
				return true;
			}
		}
		catch (SQLException e)
		{
			successful = false;
			errorMessage = "Staff does not exist";
			return false;
		}
		return true;
	}

	public StaffData getStaffProfile(int userID){
		if (checkIdentity(userID)){
			StaffData curTenant = null;
			StaffData dataTenant = null;
			
			try {
				sList = StaffList.getInstance();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Iterator<StaffData> iter;
			try {
				iter = sList.iterator();
				while (iter.hasNext()){
					curTenant = iter.next();
					if (curTenant.getUserID() == userID){
						dataTenant = curTenant;
						break;
					}	
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return dataTenant;
			
		}
		else{
			return null;
		}
	}

	/**
	 * Modifies a tenant information
	 * @param userID
	 * @param phone
	 */
	public void changeStaffPhone(int userID, long phone){
		if (checkIdentity(userID)){
			// gets modifies the tenant
			Database.modifyDatabase("UPDATE Users SET u_phone = " + phone + " WHERE u_id = " + userID);
			// Makes sure the data was modified
			ResultSet rs = Database.runGetFromDatabaseSQL("SELECT u_id FROM Users WHERE u_id = " + userID + " AND u_phone = " + phone);
			try{if(!rs.next()){throw new Exception();}}
			catch(Exception e){
				successful = false;
				errorMessage = "Data modification failed";
				return;
			}
			successful = true;
			try {
				StaffList.populateStaffs();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void changeStaffEmail(int userID, String email){
		if (checkIdentity(userID)){
			// gets modifies the tenant
			Database.modifyDatabase("UPDATE Users SET u_email = '" + email + "' WHERE u_id = " + userID);
			// Makes sure the data was modified
			ResultSet rs = Database.runGetFromDatabaseSQL("SELECT u_id FROM Users WHERE u_id = " + userID + " AND u_email = '" + email + "'");
			try{if(!rs.next()){throw new Exception();}}
			catch(Exception e){
				successful = false;
				errorMessage = "Data modification failed";
				return;
			}
			successful = true;
			try {
				StaffList.populateStaffs();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}

	public void changeStaffCType(int userID, int ctype){
		if (checkIdentity(userID)){
			// gets modifies the tenant
			Database.modifyDatabase("UPDATE Users SET u_contact_type = " + ctype + " WHERE u_id = " + userID);
			// Makes sure the data was modified
			ResultSet rs = Database.runGetFromDatabaseSQL("SELECT u_id FROM Users WHERE u_id = " + userID + " AND u_contact_type = " + ctype);
			try{if(!rs.next()){throw new Exception();}}
			catch(Exception e){
				successful = false;
				errorMessage = "Data modification failed";
				return;
			}
			successful = true;
			try {
				StaffList.populateStaffs();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	public void changeStaffHomeAddress(int userID, String homeaddress){
		if (checkIdentity(userID)){
			// gets modifies the tenant
			Database.modifyDatabase("UPDATE staff SET s_address = '" + homeaddress + "' WHERE s_user_id = " + userID);
			// Makes sure the data was modified
			ResultSet rs = Database.runGetFromDatabaseSQL("SELECT s_user_id FROM staff WHERE s_user_id = " + userID + " AND s_address = '" + homeaddress+"'");
			try{if(!rs.next()){throw new Exception();}}
			catch(Exception e){
				successful = false;
				errorMessage = "Data modification failed";
				return;
			}
			successful = true;
			try {
				StaffList.populateStaffs();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}

	/**
	 * retrieve whether or not a command was successful
	 * @return boolean
	 */
	public boolean wasSuccessful()
	{
		return successful;
	}
	
	/**
	 * get the error message that was created
	 * @return String
	 */
	public String getErrorMessage()
	{
		return errorMessage;
	}
	
	public static void main(String[] argv){
		Iterator<StaffData> iter;
		try {
			iter = StaffList.getInstance().iterator();

			while (iter.hasNext())
			{
				StaffData currentTenant = iter.next();
				System.out.print(currentTenant.getUserID() + " " + currentTenant.getHomeAddress() + " " + currentTenant.getLastName() + "\n");
				System.out.print("\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

