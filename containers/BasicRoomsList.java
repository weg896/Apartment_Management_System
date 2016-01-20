package containers;

//import java.sql.ResultSet;
import java.util.LinkedList;

import entities.BasicRoomData;

//import startup.Database;

/**
 * Singleton for the tenants list
 * @author taylorwiebe
 *
 */
public class BasicRoomsList
{
	/**
	 * A linked list of the tenant data
	 */
	private static LinkedList<BasicRoomData> List;
	
	/**
	 * Private constructor to ensure that no instance of this class is created. 
	 */
	private BasicRoomsList() {}
	
	public static LinkedList<BasicRoomData> getInstance() throws Exception
	{
		if (List == null)
		{
			throw new Exception("BasicRoomsList do not have data");
		}else{
			// This method makes sure that everything is up to date in the list
			return List;
		}
	}
	
	public static void reNewList(LinkedList<BasicRoomData> a) throws Exception
	{
		if(null == a){
			throw new Exception("BasicRoomsList parameter a is null");
		}else{
			List = a;
		}
	}
	
}
