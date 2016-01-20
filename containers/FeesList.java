package containers;

//import java.sql.ResultSet;
import java.util.LinkedList;

import entities.FeesData;

//import startup.Database;

/**
 * Singleton for the tenants list
 * @author taylorwiebe
 *
 */
public class FeesList
{
	/**
	 * A linked list of the tenant data
	 */
	private static LinkedList<FeesData> List;
	
	/**
	 * Private constructor to ensure that no instance of this class is created. 
	 */
	private FeesList() {}
	
	public static LinkedList<FeesData> getInstance() throws Exception
	{
		if (List == null)
		{
			throw new Exception("feesList do not have data");
		}else{
			// This method makes sure that everything is up to date in the list
			return List;
		}
	}
	
	public static void reNewList(LinkedList<FeesData> a) throws Exception
	{
		if(null == a){
			throw new Exception("FeesList parameter a is null");
		}else{
			List = a;
		}
	}
	
}
