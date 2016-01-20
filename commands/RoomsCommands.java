package commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import containers.RoomsList;
import entities.RoomData;

/**
 * Group: 03
 * Class: CMPT 370
 */

import startup.Database;

/**
 * All the commands for adding new/removing/modifying rooms for buildings
 * @author taylorwiebe
 *
 */
public class RoomsCommands
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
	 * stores the room
	 */
	private RoomData room;
	
	/**
	 * Initializes the list of rooms
	 */
	public RoomsCommands(RoomData setRoom)
	{
		Database.getInstance();
		room = setRoom;
	}
	
	public RoomsCommands()
	{
		Database.getInstance();
	}
	
	/**
	 * Adds a room to a building
	 * @param buildingId - the id of the building
	 * @param roomNumber - the new room number
	 * @param squareFootage - the square footage of the new room
	 * @param bedrooms - number of buildings in the new room
	 * @param price - the cost of the new room
	 * @param capacity - max occupants to the room
	 */
	public void addRoom(int roomNumber, int squareFootage, int bedrooms, int price, int capacity, int buildingId)
	{
		ResultSet check = Database.runGetFromDatabaseSQL("SELECT ar_id FROM apartmentrooms WHERE  ar_room_number = '" + roomNumber + "' AND ar_building_id = '" + buildingId + "';");
		// checks item does not already exist
		try 
		{
			if(!check.next())
			{
				// gets the building from the database
				Database.modifyDatabase("INSERT INTO apartmentrooms (ar_room_number, ar_square_footage, ar_bedrooms, ar_building_id, ar_cost, ar_capacity, ar_numtenants, ar_occupy) VALUES ( '" + roomNumber +"', '" + squareFootage + "', '" + bedrooms +"', '" + buildingId  +"', '" + price  +"', '" + capacity  +"', 0, 'false');");
				
				// Makes sure the data was added
				ResultSet rs = Database.runGetFromDatabaseSQL("SELECT ar_room_number FROM apartmentrooms WHERE ar_room_number = '" + roomNumber + "' AND ar_building_id = '" + buildingId + "';");
				try
				{
					// makes sure item was added
					if(!rs.next())
					{
						throw new Exception();
					}	
				}
				catch(Exception e)
				{
					successful = false;
					errorMessage = "Room was not added.";
					return;
				}
			}
			else
			{
				throw new Exception();
			}
		}
		catch (Exception e)
		{
			successful = false;
			errorMessage = "Room already exists.";
			return;
		}
		
		successful = true;
	}
	
	/**
	 * Removes a room from the system
	 * @param roomNumber - the room's number
	 * @param buildingId - the building's id
	 */
	public void removeRoom()
	{
		errorMessage = null;
		
		ResultSet check = Database.runGetFromDatabaseSQL("SELECT ar_room_number FROM apartmentrooms WHERE ar_room_number = '" + room.getRoomNumber() + "' AND ar_building_id = '" + room.getBuildingId() + "';");
		// checks item does not already exist
		try 
		{
			if(check.next())
			{
				// gets the users from the database
				Database.modifyDatabase("DELETE FROM apartmentrooms WHERE ar_room_number = '" + room.getRoomNumber() + "' AND ar_building_id = '" + room.getBuildingId() + "';");
				
				// Makes sure the data was removed
				ResultSet rs = Database.runGetFromDatabaseSQL("SELECT ar_room_number FROM apartmentrooms WHERE ar_room_number = '" + room.getRoomNumber() + "' AND ar_building_id = '" + room.getBuildingId() + "';");				try
				{
					if(rs.next())
					{
						throw new Exception();
					}	
				}
				catch(Exception e)
				{
					successful = false;
					errorMessage = "Removing room failed";
					return;
				}
				
			}
			else
			{
				throw new Exception();
			}
		}
		catch (Exception e)
		{
			successful = false;
			errorMessage = "Room does not exist";
			return;
		}
		successful = true;
	}
	
	/**
	 * Modifies a room's information	
	 * @param orgRoomNum - The original room number
	 * @param buildingId
	 * @param roomId
	 * @param roomNumber
	 * @param squareFootage
	 * @param bedrooms
	 * @param price
	 * @param capacity
	 * @param numberOfTenants
	 */
	public void modifyRoom(int roomNumber, int squareFootage, int bedrooms, float price, int capacity)
	{
		errorMessage = null;
		
		ResultSet check = Database.runGetFromDatabaseSQL("SELECT ar_room_number FROM apartmentrooms WHERE ar_id = '" + room.getRoomId() + "' AND ar_building_id = '" + room.getBuildingId() + "';");
		// checks item is in the database
		try 
		{
			if(check.next())
			{
				// modifies the room

				Database.modifyDatabase("UPDATE apartmentrooms SET ar_room_number = '" + roomNumber +"', ar_square_footage = '" + squareFootage + "', ar_bedrooms = '" + bedrooms +"', ar_cost = '" + price  +"', ar_capacity = '" + capacity  +"' WHERE ar_building_id = '" + room.getBuildingId() +"' AND ar_id = '" + room.getRoomId() + "';");

				// Makes sure the data was modified
				ResultSet rs = Database.runGetFromDatabaseSQL("SELECT ar_id FROM apartmentrooms WHERE ar_id = '" + room.getRoomId() + "' AND ar_building_id = '" + room.getBuildingId() + "';");

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
		}
		catch (SQLException e)
		{
			successful = false;
			errorMessage = "Building does not exist";
			return;
		}

		successful = true;
	}
	
	/**
	 * Gets the buildingId
	 * @return int
	 */
	public int getBuildingId()
	{
		return room.getBuildingId();
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
	
	public static void main(String[] argv)
	{
		//RoomsCommands rc = new RoomsCommands();
				
		Iterator<RoomData> iter;
		try 
		{
			iter = RoomsList.getInstance().iterator();

			while (iter.hasNext())
			{
				RoomData r = iter.next();
				System.out.print(r.getBuildingId() + " " + r.getBuildingName() + " " + r.getRoomNumber() + "\n");
				System.out.print("\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		// creates room
		rc.addRoom(1, 101, 100, 2, 500, 4);
		if (!rc.successful)
		{
			System.out.print(rc.errorMessage);
		}
		
		// Removes new room
		rc.removeRoom(101, 1);
		if (!rc.successful)
		{
			System.out.print(rc.errorMessage);
		}
		
		// adds new test room
		rc.addRoom(1, 101, 100, 2, 500, 4);
		if (!rc.successful)
		{
			System.out.print(rc.errorMessage);
		}
		
		// get id from roomid and room number
		//rc.modifyRoom(buildingId, roomId, roomNumber, squareFootage, bedrooms, price, capacity, numberOfTenants)
		if (!rc.successful)
		{
			System.out.print(rc.errorMessage);
		}*/
	}
}
