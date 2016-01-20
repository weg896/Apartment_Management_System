package commands;

/**
 * Group: 03
 * Class: CMPT 370
 */


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import containers.*;
import entities.*;
import startup.Database;

/**
 * Contains the list of buildings in the system
 * @author taylorwiebe
 *
 */
public class BuildingsCommand 
{
	/**
	 * determines whether or not command is successful
	 */
	private boolean successful = false;
	
	/**
	 * Error message returned if command fails
	 */
	private String errorMessage = null;

	private BuildingData building;
	
	/**
	 * Initalizes the buildings that are currently in the system
	 * 
	 */
	public BuildingsCommand(BuildingData setBuilding)
	{
		Database.getInstance();
		building = setBuilding;
	}
	
	/**
	 * initalizes for a new building
	 */
	public BuildingsCommand()
	{
		Database.getInstance();
	}
	
	/**
	 * returns building
	 * @return building data
	 */
	public BuildingData getBuilding()
	{
		return building;
	}
	
	/**
	 * Adds a new building to the system
	 * @param buildingName
	 * @param buildingAddress
	 */
	public void addBuilding(String buildingName, String buildingAddress)
	{	
		errorMessage = null;
		
		ResultSet check = Database.runGetFromDatabaseSQL("SELECT b_id FROM Buildings WHERE b_address = '" + buildingName + "' AND b_name = '" + buildingAddress + "';");
		// checks item does not already exist
		try 
		{
			if(!check.next())
			{
				// gets the building from the database
				Database.modifyDatabase("INSERT INTO Buildings (b_address, b_name) VALUES ( '" + buildingName +"', '" + buildingAddress +"' );");
				
				// Makes sure the data was added
				ResultSet rs = Database.runGetFromDatabaseSQL("SELECT b_id FROM Buildings WHERE b_address = '" + buildingName + "' AND b_name = '" + buildingAddress + "';");
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
					errorMessage = "Building was not added.";
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
			errorMessage = "Building already exists.";
			return;
		}
		
		successful = true;
	}
		
	/**
	 * Modifies a buildings information
	 * @param buildingId
	 * @param setName
	 * @param setAddress
	 */
	public void modifyBuilding(String setName, String setAddress)
	{
		errorMessage = null;
		
		ResultSet check = Database.runGetFromDatabaseSQL("SELECT b_id FROM Buildings WHERE b_id = '" + building.getId() + "';");
		// checks item is in the database
		try 
		{
			if(check.next())
			{
				// gets modifies the buildings
				Database.modifyDatabase("UPDATE Buildings SET b_name = '" + setName + "', b_address = '" + setAddress + "' WHERE b_id = '" + building.getId() +"';");
				
				// Makes sure the data was modified
				ResultSet rs = Database.runGetFromDatabaseSQL("SELECT b_id FROM Buildings WHERE b_name = '" + setName + "' AND b_address = '" + setAddress + "';");
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
	 * Removes a building from the system
	 * @param buildingId
	 */
	public void removeBuilding()
	{
		errorMessage = null;
		
		ResultSet check = Database.runGetFromDatabaseSQL("SELECT b_id FROM Buildings WHERE b_id = '" + building.getId() + "';");
		// checks item does not already exist
		try 
		{
			if(check.next())
			{
				// gets the users from the database
				Database.modifyDatabase("DELETE FROM Buildings WHERE b_id = '" + building.getId() +"';");
				
				// Makes sure the data was removed
				ResultSet rs = Database.runGetFromDatabaseSQL("SELECT b_id FROM Buildings WHERE b_id = '" + building.getId() +"';");
				try
				{
					if(rs.next())
					{
						throw new Exception();
					}	
				}
				catch(Exception e)
				{
					successful = false;
					errorMessage = "Removing building failed";
				}
				
				successful = true;
			}
			else
			{
				throw new Exception();
			}
		}
		catch (Exception e)
		{
			successful = false;
			errorMessage = "Building does not exist";
			return;
		}
		
		successful = true;
	}
	
	
	/**
	 * Removes a building from the system
	 * @param buildingId
	 */
	public void removeBuilding(String buildingName, String buildingAddress)
	{
		errorMessage = null;
		
		ResultSet check = Database.runGetFromDatabaseSQL("SELECT b_id FROM Buildings WHERE b_name = '" + buildingName + "';");
		// checks item does not already exist
		try 
		{
			if(check.next())
			{
				// gets the users from the database
				Database.modifyDatabase("DELETE FROM Buildings WHERE b_name = '" + buildingName +"';");
				
				// Makes sure the data was removed
				ResultSet rs = Database.runGetFromDatabaseSQL("SELECT b_id FROM Buildings WHERE b_name = '" + buildingName +"';");
				try
				{
					if(rs.next())
					{
						throw new Exception();
					}	
				}
				catch(Exception e)
				{
					successful = false;
					errorMessage = "Removing building failed";
				}
				
				successful = true;
			}
			else
			{
				throw new Exception();
			}
		}
		catch (Exception e)
		{
			successful = false;
			errorMessage = "Building does not exist";
			return;
		}
		
		successful = true;
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
	
	
	/**
	 * tests methods
	 * @param argv
	 */
	public static void main(String[] argv)
	{
		
//		BuildingsCommand newList = new BuildingsCommand();
				
		Iterator<BuildingData> iter;
		try {
			iter = BuildingsList.getInstance().iterator();

			while (iter.hasNext())
			{
				BuildingData currentTenant = iter.next();
				System.out.print(currentTenant.getId() + " " + currentTenant.getAddress() + " " + currentTenant.getName() + "\n");
				System.out.print("\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		newList.addBuilding("testBuilding8", "110 test ave");
		
		System.out.print("\n \n NEW DATE \n");
		data = newList.getBuildingList();
		iter = data.iterator();
		while (iter.hasNext())
		{
			BuildingData currentTenant = iter.next();
			System.out.print(currentTenant.getId() + " " + currentTenant.getAddress() + " " + currentTenant.getName() + "\n");
			System.out.print("\n");
		}
		
		newList.removeBuilding(10);
		System.out.print("\n \n Delete data \n");
		data = newList.getBuildingList();
		iter = data.iterator();
		while (iter.hasNext())
		{
			BuildingData currentTenant = iter.next();
			System.out.print(currentTenant.getId() + " " + currentTenant.getAddress() + " " + currentTenant.getName() + "\n");
			System.out.print("\n");
		}
		
		newList.modifyBuilding(20, "Modify test", "124 modify address");
		System.out.print("\n \n Modify data \n");
		data = newList.getBuildingList();
		iter = data.iterator();
		while (iter.hasNext())
		{
			BuildingData currentTenant = iter.next();
			System.out.print(currentTenant.getId() + " " + currentTenant.getAddress() + " " + currentTenant.getName() + "\n");
			System.out.print("\n");
		}
		*/
	}
}
