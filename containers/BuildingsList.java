package containers;

import java.sql.ResultSet;
import java.util.LinkedList;

import startup.Database;

import entities.*;

/**
 * Singleton for the buildings list
 * @author taylorwiebe
 *
 */
public class BuildingsList 
{
	/**
	 * A list of the buildings in the system
	 */
	private static LinkedList<BuildingData> buildingList;
	
	/**
	 * Private constructor to ensure that no instance of this class is created. 
	 */
	public BuildingsList() {}
	
	/**
	 * Initializes the linked list with the buildings
	 * @return
	 * @throws Exception
	 */
	public static LinkedList<BuildingData> getInstance() throws Exception
	{
		if (buildingList == null)
		{
			buildingList = new LinkedList<BuildingData>();
		}
		
		populateBuildings();
		return buildingList;
	}
	
	/**
	 * Populates the list with the data from the database
	 * @throws Exception
	 */
	private static void populateBuildings() throws Exception
	{
		Database.getInstance();
		LinkedList<BuildingData> newData = new LinkedList<BuildingData>();
		
		// gets the buildings from the database
		ResultSet rs = Database.runGetFromDatabaseSQL("SELECT b_id, b_address, b_name FROM Buildings;");
		
		try
		{
			while(rs.next())
			{
				// creates a new tenant object
				BuildingData building = new BuildingData(rs.getInt("b_id"), rs.getString("b_address"), rs.getString("b_name"));
				
				// adds new tenant to the linked list
				newData.add(building);
			}
		}
		catch(Exception e)
		{
			throw new Exception();
		}
		buildingList = newData;
	}
}
