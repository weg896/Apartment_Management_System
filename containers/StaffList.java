package containers;

import java.sql.ResultSet;
import java.util.LinkedList;

import entities.StaffData;



import startup.Database;

/**
 * Singleton for the tenants list
 * 
 *
 */
public class StaffList
{
	/**
	 * A linked list of the tenant data
	 */
	private static LinkedList<StaffData> StaffList;
	
	/**
	 * Private constructor to ensure that no instance of this class is created. 
	 */
	public StaffList() {}
	
	public static LinkedList<StaffData> getInstance() throws Exception
	{
		if (StaffList == null)
		{
			StaffList = new LinkedList<StaffData>();
		}
		
		// This method makes sure that everything is up to date in the list
		populateStaffs();
		return StaffList;
	}
	
	public static void populateStaffs() throws Exception
	{
		StaffList = new LinkedList<StaffData>();
		// gets the database connection
		Database.getInstance();
		LinkedList<StaffData> newData = new LinkedList<StaffData>();
		String contact = null;
		
		// gets the users from the database
		ResultSet rs = Database.runGetFromDatabaseSQL("SELECT * FROM users, staff, stafflocations, buildings WHERE s_user_id = " +
				"sl_user_id AND sl_user_id = u_id AND b_id = sl_building_id AND u_permission_status IN(1,0);");
		try
		{
			while(rs.next())
			{
				if(rs.getInt("u_contact_type") == 0){
					contact = "phone";
				}else{
					contact = "email";
				}
				// creates a new tenant object
				StaffData staff = new StaffData(rs.getInt("u_id"), rs.getString("s_title"), rs.getString("u_first_name"), 
						rs.getString("u_last_name"), rs.getLong("u_phone"), contact, rs.getString("u_email"), 
						rs.getInt("b_id"), rs.getString("b_name"),rs.getString("s_address"));
			
				// adds new tenant to the linked list
				newData.add(staff);
				
			}
		}
		catch(Exception e)
		{
			throw new Exception();
		}
		StaffList = newData;
	}
}

