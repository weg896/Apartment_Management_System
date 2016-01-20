package commands;

/**
 * Group: 03
 * Class: CMPT 370
 */


import java.sql.ResultSet;
import java.util.Iterator;

import containers.TenantList;
import entities.TenantData;


import startup.Database;

/**
 * This class handles the list of users to display to the admins and managers
 * @author taylorwiebe
 *
 */
public class TenantCommands
{
	/**
	 * determines whether or not command is successful
	 */
	private boolean successful = false;
	
	/**
	 * Error message returned if command fails
	 */
	private String errorMessage = null;
	
	/**
	 * Initializes the tenant list
	 */
	public TenantCommands()
	{
		errorMessage = null;
		
		// gets the database connection
		Database.getInstance();
		
		try
		{
			TenantList.getInstance();
		}
		catch(Exception e)
		{
			successful = false;
			errorMessage = "Failed to get tenants list";
			return;
		}
		successful = true;		
	}
	
	public void modifyTenantRoom(TenantData tenant)
	{
		errorMessage = null;
		
		// gets the users from the database
		ResultSet check = Database.runGetFromDatabaseSQL("SELECT ur_room_id, ur_user_id FROM usersrooms WHERE ur_user_id = '" + tenant.getTenantId() + "';");
		
		try
		{
			if(check.next())
			{
				// modifies the room
				Database.modifyDatabase("UPDATE usersrooms SET ur_room_id = '" + tenant.getRoomId() + "' WHERE ur_user_id = '" + tenant.getTenantId() + "';");
				
				// Makes sure the data was modified
				ResultSet rs = Database.runGetFromDatabaseSQL("SELECT ur_user_id FROM usersrooms WHERE ur_room_id = '" + tenant.getRoomId() + "';");
				try
				{
					if(!rs.next())
					{
						throw new Exception();
					}	
				}
				catch(Exception e)
				{
					successful = false;
					errorMessage = "Data modification failed";
					return;
				}
			} 
			else
			{
				check = Database.runGetFromDatabaseSQL("SELECT u_id FROM users WHERE u_id = '" + tenant.getTenantId() + "';");
				if(check.next())
				{
					// modifies the room
					Database.modifyDatabase("INSERT INTO usersrooms (ur_room_id, ur_user_id) VALUES ('" + tenant.getRoomId() + "', '" + tenant.getTenantId() + "');");
					
					// Makes sure the data was modified
					ResultSet rs = Database.runGetFromDatabaseSQL("SELECT ur_user_id FROM usersrooms WHERE ur_room_id = '" + tenant.getRoomId() + "';");
					try
					{
						if(!rs.next())
						{
							throw new Exception();
						}	
					}
					catch(Exception e)
					{
						successful = false;
						errorMessage = "Data modification failed";
						return;
					}
				}
				else
				{
					successful = false;
					errorMessage = "User does not exist";
					return;
				}
			}
		}
		catch(Exception e)
		{
			successful = false;
			errorMessage = "Failed to get tenants list";
			return;
		}
		successful = true;
	}
	
	public static void main(String[] argv)
	{
	//	TenantCommands newList = new TenantCommands();
				
		Iterator<TenantData> iter;
		try
		{
			iter = TenantList.getInstance().iterator();
			while (iter.hasNext())
			{
				TenantData currentTenant = iter.next();
				System.out.print(currentTenant.getFirstName() + " " + currentTenant.getLastName() + " " + currentTenant.getEmailAddress());
				System.out.print("\n");
			}
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
