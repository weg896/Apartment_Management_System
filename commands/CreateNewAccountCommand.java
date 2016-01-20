package commands;

import java.sql.*;

import startup.Database;

public class CreateNewAccountCommand {
	
	//record if the login succeed or not
	private boolean successful = false;
	
	//record the error if failed to login
	private String errorMessage = null;
	
	private String userName = null;
	
	private String defPassword = null;
	
	//create account for manager and staff
	public void createAccountForStaff(String fName, String LName, long phone,
			int contact, String email, int permission, int buildingID, String title, String address){
		
		
		if(Database.getInstance() == null){
			errorMessage = "Failed to access database";
			successful = false;
			return;
		}else{
			try{
				//try to find new id which is 1 plus current largest id
				String curMaxID= "SELECT MAX(u_id) FROM users;";
				ResultSet rsMaxID = Database.runGetFromDatabaseSQL(curMaxID);
				int newID = 0;
				while(rsMaxID.next()){
					newID = rsMaxID.getInt(1) + 1;
				}
				
				//generate a random password
				defPassword = RandomAlphaNumericString(6);
				
				//generate a user name: first letter of firstName + first letter of lastName + ID
				userName =  fName.toLowerCase().substring(0, 1) + LName.toLowerCase().substring(0, 1) + newID;
				
				//insert all the info to the database
				String insert1 = "INSERT INTO users VALUES ( "+ newID +",'"
							+ fName + "','"
							+ LName + "',"
							+ contact + ",'"
							+ email + "',"
							+ permission + ",'"
							+ defPassword + "','"
							+ userName + "',"
							+ phone + ");";
				
				Database.modifyDatabase(insert1);
				
				String insert2 = "INSERT INTO staff VALUES ( "+ newID +",'"
						+ title + "','"
						+ address + "');";
				
				Database.modifyDatabase(insert2);
				
				
				String insert3 = "INSERT INTO stafflocations VALUES ( "+ newID +","
						+ buildingID + ");";
				
				Database.modifyDatabase(insert3);
				
				
				successful = true;
				
			}catch (SQLException e) {
				errorMessage = "Cannot query database";
				e.printStackTrace();
			}
			
		}
	}
	
	
	//create account for tenant
		public void createAccountForTenant(String fName, String LName, long phone,
				int contact, String email, int permission, int roomID){
			
			
			
			if(Database.getInstance() == null){
				errorMessage = "Failed to access database";
				successful = false;
				return;
			}else{
				try{
					//try to find new id which is 1 plus current largest id
					String curMaxID= "SELECT MAX(u_id) FROM users;";
					ResultSet rsMaxID = Database.runGetFromDatabaseSQL(curMaxID);
					int newID = 0;
					while(rsMaxID.next()){
						newID = rsMaxID.getInt(1) + 1;
					}
					
					//generate a random password
					defPassword = RandomAlphaNumericString(6);
					
					//generate a user name: first letter of firstName + first letter of lastName + ID
					userName =  fName.toLowerCase().substring(0, 1) + LName.toLowerCase().substring(0, 1) + newID;
					
					//insert all the info to the database
					String insert1 = "INSERT INTO users VALUES ( "+ newID +",'"
								+ fName + "','"
								+ LName + "',"
								+ contact + ",'"
								+ email + "',"
								+ permission + ",'"
								+ defPassword + "','"
								+ userName + "',"
								+ phone + ");";
					
					Database.modifyDatabase(insert1);
					
					String insert2 = "INSERT INTO usersrooms VALUES ( "+ roomID +","
							+ newID + ");";
					
					Database.modifyDatabase(insert2);
					
					Database.modifyDatabase("UPDATE apartmentrooms set ar_occupy = 'true' where ar_id = " +roomID+";");
					
					successful = true;
					
				}catch (SQLException e) {
					errorMessage = "The account has already been registered";
					e.printStackTrace();
				}
				
			}
		}
		

	public String RandomAlphaNumericString(int size){
	    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    String ret = "";
	    int length = chars.length();
	    for (int i = 0; i < size; i ++){
	        ret += chars.split("")[ (int) (Math.random() * (length - 1)) ];
	    }
	    return ret;
	}
	
	public String getNewUserName(){
		return userName;
	}
	
	public String getNewPassword(){
		return defPassword;
	}
	
	
	
	public String getErrorMessage(){
		return errorMessage;
	}
	
	public boolean wasSuccessful(){
		return successful;
	}
	
}
