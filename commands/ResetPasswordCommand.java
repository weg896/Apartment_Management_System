package commands;

import java.sql.ResultSet;

import startup.Database;

public class ResetPasswordCommand {
	private boolean successful = false;
	
	//record the error if failed to login
	private String errorMessage = null;
	
	//remove manager/staff account
	public void resetPasswordCommand(String username, String newPassword){
		
		try{
			
			
			ResultSet rs = Database.runGetFromDatabaseSQL("SELECT * FROM users WHERE u_username =  '" + username+ "';");
			if(rs == null){
				errorMessage = "This username does not exist in system";
				successful = false;
				return;
			}
			else{
				Database.modifyDatabase("UPDATE users SET u_password = '" + newPassword + "' WHERE u_username =  '" + username+ "';");
				successful = true;
			}
			
		}catch(Exception e){
			successful = false;
			errorMessage = "Failed to update account";
		}
	}
	
	public String getErrorMessage(){
		return errorMessage;
	}
	
	public boolean wasSuccessful(){
		return successful;
	}
}
