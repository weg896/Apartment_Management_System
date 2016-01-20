package junitTests;

import java.sql.Connection;
import org.junit.*;

import commands.*;
import containers.*;

import startup.Database;

/**
 * Tests the commands of the system
 * @author weixiong
 *
 */
public class RentalManagemenPartTest 
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
	public void TenantFeeCommandTest1(){
		System.out.println("****Test w1");
		TenantFeeCommand tfc = new TenantFeeCommand();
		tfc.tenantViewFee(7);
		Assert.assertTrue(tfc.wasSuccessful());
		try{
			Assert.assertTrue( null != FeesList.getInstance());
			Assert.assertFalse( FeesList.getInstance().isEmpty());
		}catch(Exception e){
			System.out.println("Exception of tenant fee command tenantViewFee--should not occur");
		}
	}
	
	@Test
	public void TenantFeeCommandTest2(){
		System.out.println("****Test w2");
		TenantFeeCommand tfc = new TenantFeeCommand();
		tfc.tenantPayHistory(7);
		Assert.assertTrue(tfc.wasSuccessful());
		try{
			Assert.assertTrue( null != FeesList.getInstance());
			Assert.assertFalse( FeesList.getInstance().isEmpty());
		}catch(Exception e){
			System.out.println("Exception of tenant fee command tenantPayHistory--should not occur");
		}
	}
	
	@Test
	public void TenantFeeCommandTest3(){
		System.out.println("****Test w3");
		TenantFeeCommand tfc = new TenantFeeCommand();
		tfc.tenantPayHistory(288);
		Assert.assertTrue(tfc.wasSuccessful());
		try{
			Assert.assertTrue( null != FeesList.getInstance());
			Assert.assertTrue( FeesList.getInstance().isEmpty());
		}catch(Exception e){
			System.out.println("Exception of tenant fee command tenantPayHistory--should not occur");
		}
	}
	
	@Test
	public void TenantFeeCommandTest4(){
		System.out.println("****Test w4");
		TenantFeeCommand tfc = new TenantFeeCommand();
		tfc.tenantViewFee(257);
		Assert.assertTrue(tfc.wasSuccessful());
		try{
			Assert.assertTrue( null != FeesList.getInstance());
			Assert.assertTrue( FeesList.getInstance().isEmpty());
		}catch(Exception e){
			System.out.println("Exception of tenant fee command tenantViewFee--should not occur");
		}
	}
	
	@Test
	public void ManagerCommandTest5(){
		System.out.println("****Test w5");
		ManageFeeCommand mfc = new ManageFeeCommand();
		mfc.getAllRoom(1);
		Assert.assertTrue(mfc.wasSuccessful());
		try{
			Assert.assertTrue( null != BasicRoomsList.getInstance());
			Assert.assertFalse( BasicRoomsList.getInstance().isEmpty());
		}catch(Exception e){
			System.out.println("Exception of tenant fee command tenantViewFee--should not occur");
		}
	}
	
	@Test
	public void ManagerCommandTest6(){
		System.out.println("****Test w6");
		ManageFeeCommand mfc = new ManageFeeCommand();
		mfc.getAllRoom(5555);
		Assert.assertTrue(mfc.wasSuccessful());
		try{
			Assert.assertTrue( null != BasicRoomsList.getInstance());
			Assert.assertTrue( BasicRoomsList.getInstance().isEmpty());
		}catch(Exception e){
			System.out.println("Exception of tenant fee command tenantViewFee--should not occur");
		}
	}
	
	@Test
	public void ManagerCommandTest7(){
		System.out.println("****Test w7");
		ManageFeeCommand mfc = new ManageFeeCommand();
		mfc.getAllRentTanent();
		Assert.assertTrue(mfc.wasSuccessful());
		try{
			Assert.assertTrue( null != BasicTenantsList.getInstance());
			Assert.assertFalse( BasicTenantsList.getInstance().isEmpty());
		}catch(Exception e){
			System.out.println("Exception of tenant fee command tenantViewFee--should not occur");
		}
	}
	
	@Test
	public void ManagerCommandTest8(){
		System.out.println("****Test w8");
		ManageFeeCommand mfc = new ManageFeeCommand();
		mfc.ManagerViewFeeHistory(null);
		Assert.assertTrue(mfc.wasSuccessful());
		try{
			Assert.assertTrue( null != FeesList.getInstance());
			Assert.assertFalse( FeesList.getInstance().isEmpty());
		}catch(Exception e){
			System.out.println("Exception of tenant fee command tenantViewFee--should not occur");
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/*
	@Test
	public void ModifyTenantRoom()
	{
			System.out.println("****Test 6");

		// Adds a new room for testing purposes
		RoomsCommands newRoom = new RoomsCommands();
		newRoom.addRoom(99, 101, 10, 1, 1000, 10);
		LinkedList<RoomData> roomsTestList = null;
		RoomData testRoom = new RoomData(99, 101, 10, 1, 1000, 10);
		
		try
		{
			roomsTestList = RoomsList.getInstance();
			Iterator<RoomData> iter = roomsTestList.iterator();
			
		} catch (Exception e)
		{
			
		}
		
		
		// Adds a new account for testing purposes
		CreateNewAccountCommand newAccount = new CreateNewAccountCommand();
		newAccount.createAccountForTenant("JunitModifyTestFN", "JunitModifyTestLN", 123456789, 0, "JunitModifyTestEmail@test.com", 2, roomID)
		
		TenantCommands modify = new TenantCommands();
		modify.modifyTenantRoom(tenant);
	}
	*/
	
	@AfterClass
	public static void tearDownAfterClass()
	{
		System.out.println("****Test 9");
		//CurrentStateCommand state = new CurrentStateCommand();
		//state.findCurState();
		/* This output, from the tests above and the output below,
		 * is too large to expect anyone to check it.
		 * It is included to show how the output can easily get too large
		 * to check.
		 */
		
		/*System.out.println("The final system with patients Pete, Sam, Bill, "
				+ "Linda, Sue, Alice, Jean, and Mary; \ndoctor Ann; "
				+ "surgeons Beth and Jeff; \nLinda in bed 32, Alice in bed 37, "
		        + "Bill in bed 38, \nand Sue and Jean having doctor Jeff is "
		        + state.getCurState());
		        */
		Database.closeConnection();
	}
}
