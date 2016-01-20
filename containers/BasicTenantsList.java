package containers;

//import java.sql.ResultSet;
import java.util.LinkedList;

import entities.BasicTenantDate;

//import startup.Database;

/**
 * Singleton for the tenants list
 * @author taylorwiebe
 *
 */
public class BasicTenantsList
{
	/**
	 * A linked list of the tenant data
	 */
	private static LinkedList<BasicTenantDate> List;
	
	/**
	 * Private constructor to ensure that no instance of this class is created. 
	 */
	private BasicTenantsList() {}
	
	public static LinkedList<BasicTenantDate> getInstance() throws Exception
	{
		if (List == null)
		{
			throw new Exception("BasicRoomsList do not have data");
		}else{
			// This method makes sure that everything is up to date in the list
			return List;
		}
	}
	
	public static void reNewList(LinkedList<BasicTenantDate> a) throws Exception
	{
		if(null == a){
			throw new Exception("basicRoomData parameter a is null");
		}else{
			List = a;
		}
	}
	
}
