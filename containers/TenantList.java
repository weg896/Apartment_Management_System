package containers;

import java.sql.ResultSet;
import java.util.LinkedList;

import entities.*;

import startup.Database;

/**
 * Singleton for the tenants list
 * @author taylorwiebe
 *
 */
public class TenantList
{
	/**
	 * A linked list of the tenant data
	 */
	private static LinkedList<TenantData> tenantList;
	
	/**
	 * Private constructor to ensure that no instance of this class is created. 
	 */
	public TenantList() {}
	
	public static LinkedList<TenantData> getInstance() throws Exception
	{
		if (tenantList == null)
		{
			tenantList = new LinkedList<TenantData>();
		}
		
		// This method makes sure that everything is up to date in the list
		populateTenants();
		return tenantList;
	}
	
	public static void populateTenants() throws Exception
	{
		tenantList = new LinkedList<TenantData>();
		// gets the database connection
		Database.getInstance();
		
		LinkedList<TenantData> newData = new LinkedList<TenantData>();
		
		// gets the users from the database
		ResultSet rs = Database.runGetFromDatabaseSQL("SELECT u_username, u_id, u_first_name, u_last_name, u_phone, u_contact_type, u_email, (SELECT ar_room_number FROM apartmentrooms WHERE ar_id = (SELECT ur_room_id  FROM usersrooms WHERE ur_user_id = u_id)) as room_number, (SELECT ur_room_id  FROM usersrooms WHERE ur_user_id = u_id) as room_id, (SELECT ar_building_id FROM apartmentrooms WHERE ar_id = (SELECT ur_room_id  FROM usersrooms WHERE ur_user_id = u_id)) as building_id, (SELECT b_name FROM buildings WHERE b_id = (SELECT ar_building_id FROM apartmentrooms WHERE ar_id = (SELECT ur_room_id  FROM usersrooms WHERE ur_user_id = u_id))) as building_name FROM Users WHERE u_permission_status = '2' AND u_id <> '0';");
		
		try
		{
			while(rs.next())
			{
				//YUQING TAN adds a few lines here
				String contactType = null;
				if (rs.getInt("u_contact_type") == 0){
					contactType = "Phone";
				}else{
					contactType = "Email";
				}
				
				// creates a new tenant object
				TenantData tenant = new TenantData(rs.getInt("u_id"), rs.getInt("room_id"), rs.getString("u_first_name"), rs.getString("u_last_name"), rs.getLong("u_phone"), contactType, rs.getString("u_email"), rs.getInt("room_number"), rs.getInt("building_id"), rs.getString("building_name"), rs.getString("u_username"));
			
				// adds new tenant to the linked list
				newData.add(tenant);
			}
		}
		catch(Exception e)
		{
			throw new Exception();
		}
		tenantList = newData;
	}
}
