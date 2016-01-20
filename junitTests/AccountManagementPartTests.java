package junitTests;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.*;

import startup.Database;
import commands.*;

/**
 * Test commands
 * @author Yang
 *
 */
public class AccountManagementPartTests {
	
	@BeforeClass
	public static void setUpBeforeClass(){	
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
	public void LoginCommandTest(){
		LoginCommand lgn1 = new LoginCommand();
		lgn1.Login("ad1", "0000");
		Assert.assertTrue("Failed to Log in using valid username and password", lgn1.wasSuccessful());
		lgn1.Login("sdf","23453");
		Assert.assertFalse("Failed to not Log in using invalid username and password", lgn1.wasSuccessful());
		
		LoginCommand lgn2 = new LoginCommand();
		lgn2.Login("ad1", "0000");
		Assert.assertTrue("Failed to identify manager", lgn2.isManager());
	}
	
	@Test
	public void RemoveAccountCommandTest(){
		RemoveAccountCommand rac = new RemoveAccountCommand();
		
		Database.modifyDatabase("INSERT INTO users VALUES(999,'a','a',0,'a@a',1,'555','aa',6534)");
		Database.modifyDatabase("INSERT INTO staff VALUES(999,'staff','test address')");
		Database.modifyDatabase("INSERT INTO stafflocations VALUES(999,2)");
		rac.removeStaffAccount(999);
		Assert.assertTrue("Failed to delete a staff account",rac.wasSuccessful());
		
		Database.modifyDatabase("INSERT INTO users VALUES(998,'a','a',0,'a@a',2,'555','aa',6534)");
		Database.modifyDatabase("INSERT INTO usersrooms VALUES(5502,998)");
		Database.modifyDatabase("UPDATE apartmentrooms SET ar_occupy = 'true' WHERE ar_id = 5502");
		rac.removeTenantAccount(998);
		Assert.assertTrue("Failed to delete a tenant account",rac.wasSuccessful());
	}
	
	@Test
	public void CreateNewAccountCommandTest(){
		CreateNewAccountCommand cac = new CreateNewAccountCommand();
		RemoveAccountCommand rac = new RemoveAccountCommand();
		
		cac.createAccountForStaff("a", "a", 123, 0, "ac@ad", 0,2 , "manager", "ave");
		Assert.assertNotNull("Failed to add a staff account",Database.runGetFromDatabaseSQL("SELECT * FROM users WHERE u_first_name = 'a'" +
				"AND u_last_name = 'a'"));
		ResultSet rs = Database.runGetFromDatabaseSQL("SELECT * FROM users WHERE u_first_name = 'a'" +
				"AND u_last_name = 'a'");
		try {
			while(rs.next()){
				rac.removeStaffAccount(rs.getInt("u_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cac.createAccountForTenant("b", "b", 123, 0, "b@b", 2, 10);
		Assert.assertNotNull("Failed to add a tenant account",Database.runGetFromDatabaseSQL("SELECT * FROM users WHERE u_first_name = 'b'" +
				"AND u_last_name = 'b'"));
		ResultSet rs1 = Database.runGetFromDatabaseSQL("SELECT * FROM users WHERE u_first_name = 'b'" +
				"AND u_last_name = 'b'");
		try {
			while(rs1.next()){
				rac.removeTenantAccount(rs1.getInt("u_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void ResetPasswordCommandTest(){
		ResetPasswordCommand rpc = new ResetPasswordCommand();
		CreateNewAccountCommand cac = new CreateNewAccountCommand();
		RemoveAccountCommand rac = new RemoveAccountCommand();
		
		cac.createAccountForStaff("a", "a", 123, 0, "ac@ad", 0,2 , "manager", "ave");
		ResultSet rs = Database.runGetFromDatabaseSQL("SELECT * FROM users WHERE u_first_name = 'a'" +
				"AND u_last_name = 'a'");
		try {
			while(rs.next()){
				rpc.resetPasswordCommand(rs.getString("u_username"), "abc123");
				Assert.assertNotNull("Failed to change password",Database.runGetFromDatabaseSQL("SELECT * FROM users WHERE u_username = '" +
						rs.getString("u_username") + "' AND u_password = 'abc123'"));			
				rac.removeStaffAccount(rs.getInt("u_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Test
	public void StaffProfileCommandTest(){
		StaffProfileCommand spc = new StaffProfileCommand();
		CreateNewAccountCommand cac = new CreateNewAccountCommand();
		RemoveAccountCommand rac = new RemoveAccountCommand();
		
		cac.createAccountForStaff("a", "a", 123, 0, "ac@ad", 0,2 , "manager", "ave");
		ResultSet rs = Database.runGetFromDatabaseSQL("SELECT * FROM users WHERE u_first_name = 'a'" +
				"AND u_last_name = 'a'");
		try {
			while(rs.next()){

				Assert.assertNotNull("Failed to grab staff profile", spc.getStaffProfile(rs.getInt("u_id")));
				
				spc.changeStaffCType(rs.getInt("u_id"), 1);
				Assert.assertNotNull("Failed to change staff preferred contact type", Database.runGetFromDatabaseSQL("SELECT * FROM users WHERE u_id = " +
						rs.getString("u_id") + " AND u_contact_type = 1"));
				
				spc.changeStaffEmail(rs.getInt("u_id"), "b@b");
				Assert.assertNotNull("Failed to change staff email address", Database.runGetFromDatabaseSQL("SELECT * FROM users WHERE u_id = " +
						rs.getString("u_id") + " AND u_email = 'b@b'"));
				
				spc.changeStaffPhone(rs.getInt("u_id"), 8520);
				Assert.assertNotNull("Failed to change staff phone number", Database.runGetFromDatabaseSQL("SELECT * FROM users WHERE u_id = " +
						rs.getString("u_id") + " AND u_phone = 8520"));
				
				spc.changeStaffHomeAddress(rs.getInt("u_id"), "earth");
				Assert.assertNotNull("Failed to change staff home address", Database.runGetFromDatabaseSQL("SELECT * FROM staff WHERE s_user_id = " +
						rs.getString("u_id") + " AND s_address = 'earth'"));
							
				rac.removeStaffAccount(rs.getInt("u_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	
	
	
}
