package commands;

/**
 * Group: 03
 * Class: CMPT 370
 */

import entities.*;
import containers.*;
import java.sql.*;
import java.util.*;

/**
 * the command that contains multiple tenant profile modify tasks @ClientSide
 * @author YUQING TAN
 */
public class TenantProfileCommand{
	/**
	 * determines whether or not command is successful
	 */
	private boolean successful = false;
	
	/**
	 * Error message returned if command fails
	 */
	private String errorMessage = null;
	
	/**
	 * A list of the buildings in the system
	 */
	//LinkedList<TenantData> lsTenant = null;
	Iterator<TenantData> itrTenant = null;
	
	/**
	 * Initalizes the buildings that are currently in the system
	 */
	public TenantProfileCommand()
	{
	}
	

	public TenantData getTenantProfile(int userID){
		try {itrTenant = TenantList.getInstance().iterator();} 
		catch (Exception e) {e.printStackTrace();}
		TenantData curTenant = null;
		TenantData retTenant = null;
		while (itrTenant.hasNext()){
			curTenant = itrTenant.next();
			if (curTenant.getTenantId() == userID){
				retTenant = curTenant;
				break;
			}	
		}
		return retTenant;
	}

	/**
	 * Modifies a tenant information
	 * @param userID
	 * @param phone
	 */
	public void changeTenantPhone(int userID, long phone){
		if (null != getTenantProfile(userID)){
			// gets modifies the tenant
			startup.Database.modifyDatabase("UPDATE Users SET u_phone = " + phone + " WHERE u_id = " + userID);
			// Makes sure the data was modified
			ResultSet rs = startup.Database.runGetFromDatabaseSQL("SELECT u_id FROM Users WHERE u_id = " + userID + " AND u_phone = " + phone);
			try{if(!rs.next()){throw new Exception();}}
			catch(Exception e){
				successful = false;
				errorMessage = "Data modification failed";
				return;
			}
			successful = true;
		}
	}

	public void changeTenantEmail(int userID, String email){
		if (null != getTenantProfile(userID)){
			// gets modifies the tenant
			startup.Database.modifyDatabase("UPDATE Users SET u_email = '" + email + "' WHERE u_id = " + userID);
			// Makes sure the data was modified
			ResultSet rs = startup.Database.runGetFromDatabaseSQL("SELECT u_id FROM Users WHERE u_id = " + userID + " AND u_email = '" + email + "'");
			try{if(!rs.next()){throw new Exception();}}
			catch(Exception e){
				successful = false;
				errorMessage = "Data modification failed";
				return;
			}
			successful = true;
			try {TenantList.populateTenants();} 
			catch (Exception e) {e.printStackTrace();}
		}		
	}

	public void changeTenantCType(int userID, int ctype){
		if (null != getTenantProfile(userID)){
			// gets modifies the tenant
			startup.Database.modifyDatabase("UPDATE Users SET u_contact_type = " + ctype + " WHERE u_id = " + userID);
			// Makes sure the data was modified
			ResultSet rs = startup.Database.runGetFromDatabaseSQL("SELECT u_id FROM Users WHERE u_id = " + userID + " AND u_contact_type = " + ctype);
			try{if(!rs.next()){throw new Exception();}}
			catch(Exception e){
				successful = false;
				errorMessage = "Data modification failed";
				return;
			}
			successful = true;
			try {TenantList.populateTenants();} 
			catch (Exception e) {e.printStackTrace();}
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
}
