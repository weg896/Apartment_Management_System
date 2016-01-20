package junitTests;

import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.*;

import entities.*;
import commands.*;

import containers.BuildingsList;


import startup.Database;

/**
 * Tests the commands of the system
 * @author taylorwiebe
 *
 */
public class Tests 
{
//	private int buildingId;
	
	@BeforeClass
	public static void setUpBeforeClass()
	{
		System.out.println("****Test 1");
		
		try
		{
			Connection conn = Database.getInstance();
			Assert.assertTrue(conn != null);
		} catch (Exception e)
		{
			System.out.println("Failed to connect to the database.");
		}
		
	}
	
	@Test
	public void buildingDataCompareTest()
	{
		System.out.println("****Test 2");

		// checks buildings can be compared
		BuildingData building1 = new BuildingData(100, "test Address", "Test Name");
		BuildingData building2 = new BuildingData(100, "test Address", "Test Name");
		Assert.assertTrue(building1.equals(building2));
		BuildingData building3 = new BuildingData(101, "Different", "Different Name");
		Assert.assertFalse(building1.equals(building3));
	}
	
	@Test
	public void roomDataCompareTest()
	{
		System.out.println("****Test 3");

		// checks rooms can be compared
		RoomData room1 = new RoomData("Test", 1, 1, 1, 1, 1, 1, 1, 1);
		RoomData room2 = new RoomData("Test", 1, 1, 1, 1, 1, 1, 1, 1);
		Assert.assertTrue(room1.equals(room2));
		RoomData room3 = new RoomData("different", 2, 2, 2, 2, 2, 2, 2, 2);
		Assert.assertFalse(room3.equals(room2));
	}
	
	@Test
	public void tenantDataCompareTest()
	{
		System.out.println("****Test 4");

		// makes sure tenants are compareable
		TenantData tenant1 = new TenantData(1, 1, "Test", "Test", 1, "Test", "Test", 1, 1, "Test", "test");
		TenantData tenant2 = new TenantData(1, 1, "Test", "Test", 1, "Test", "Test", 1, 1, "Test", "test");
		Assert.assertTrue(tenant1.equals(tenant2));
		TenantData tenant3 = new TenantData(2, 2, "Different", "Different", 2, "Different", "Different", 2, 2, "Different", "test");
		Assert.assertFalse(tenant1.equals(tenant3));
	}
	
	@Test
	public void buildingCommandTests()
	{
		
		System.out.println("****Test 5");
		// tests add
		BuildingsCommand command = new BuildingsCommand();
		command.addBuilding("junittest", "junittest");
		LinkedList<BuildingData> building;
		try {
			building = BuildingsList.getInstance();
			Iterator<BuildingData> iter = building.iterator();
			boolean success = false;
			while (iter.hasNext())
			{
				BuildingData current = iter.next();
				if (current.getName().equals("junittest"))
				{
					success = true;
				}
			}
			Assert.assertTrue(success);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		command.removeBuilding("junittest", "junittest");
		
		try {
			building = BuildingsList.getInstance();
			Iterator<BuildingData> iter = building.iterator();
			boolean success = true;
			while (iter.hasNext())
			{
				BuildingData current = iter.next();
				if (current.getName().equals("junittest1"))
				{
					success = false;
				}
			}
			Assert.assertTrue(success);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@AfterClass
	public static void tearDownAfterClass()
	{
		System.out.println("****Test 10");
		Database.closeConnection();
	}
}
