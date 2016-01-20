package containers;

import java.sql.ResultSet;
import java.util.LinkedList;

import startup.Database;

import entities.*;

public class RoomsList 
{
	/**
	 * The list of the rooms
	 */
	private static LinkedList<RoomData> roomsList;
	
	/**
	 * Populates the list from the database
	 * @return linked list of rooms
	 */
	public RoomsList() {}
	
	public static LinkedList<RoomData> getInstance() throws Exception
	{
		if (roomsList == null)
		{
			roomsList = new LinkedList<RoomData>();
		}
		
		populateRooms();
		return roomsList;
	}
	
	/**
	 * Populates the list from the database
	 * @return linked list of rooms
	 */
	private static void populateRooms() throws Exception
	{
		Database.getInstance();
		// gets the rooms from the database
		ResultSet rs = Database.runGetFromDatabaseSQL("SELECT ar_id, ar_room_number, ar_square_footage, ar_bedrooms, ar_building_id, ar_cost, ar_capacity, ar_numtenants, (SELECT b_name FROM buildings WHERE b_id = ar_building_id) as building_name FROM apartmentrooms;");
		LinkedList<RoomData> newData = new LinkedList<RoomData>();
		
		try
		{
			while(rs.next())
			{
				// creates a new tenant object
				 RoomData room = new RoomData(rs.getString("building_name"), rs.getInt("ar_id"), rs.getInt("ar_room_number"), rs.getInt("ar_square_footage"), rs.getInt("ar_bedrooms"), rs.getInt("ar_building_id"), rs.getInt("ar_cost"), rs.getInt("ar_capacity"), rs.getInt("ar_numtenants"));
				
				 // adds new tenant to the linked list
				 
				 newData.add(room);				
			}
			
		}
		catch(Exception e)
		{
			throw new Exception();
		}
		roomsList = newData;
	}
}
