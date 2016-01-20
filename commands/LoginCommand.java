package commands;

import java.sql.*;

import startup.Database;

/**
 * It will search the database to check entered username
 * and password. if cannot find,then there is a errormessage
 * 
 */
public class LoginCommand{
	
	//record if the login succeed or not
	private boolean successful = false;
	
	//record the error if failed to login
	private String errorMessage = null;
	
	//record the user id
	private int userid=-1;
	
	public void Login(String name, String password){
		 //check if access database successfully
		
		if(Database.getInstance() == null ){
			errorMessage = "Failed to access database";
			System.out.print(Database.getInstance());
			successful = false;
			return;
		}else{
			String sql = "SELECT u_username, u_id FROM users WHERE u_username = '" + name + "' AND u_password = '" + password + "';";
			try{
				//check if user is in database
				ResultSet r = Database.runGetFromDatabaseSQL(sql);
				String temp = null;
				while(r.next()){
					temp = r.getString("u_username");
					this.userid = r.getInt("u_id");
				}
				if(temp == null){
					successful = false;
					errorMessage = "Wrong username or wrong password";
				}
				else{
					successful = true;
				}
			}catch (SQLException e) {
				errorMessage = "Cannot query database";
				e.printStackTrace();
			}
			
		}
	}
	
	public boolean isManager(){
		try{
			int permission = 0;
			//check if is a manager
			String sql = "SELECT u_permission_status FROM users WHERE u_id = " + this.getUserID();
			ResultSet r = Database.runGetFromDatabaseSQL(sql);
			while(r.next()){
				permission = r.getInt("u_permission_status");
			}
			if(permission == 0){
				return true;
			}
			else{
				return false;
			}
		}catch (SQLException e) {
			errorMessage = "Cannot query database";
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * To get the user id when it log in
	 * @return int user id
	 */
	public int getUserID(){
		return this.userid;
	}
	
	public String getErrorMessage(){
		return errorMessage;
	}
	
	public boolean wasSuccessful(){
		return successful;
	}

}
