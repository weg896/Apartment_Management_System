package commands;

import startup.Database;
import java.sql.*;

public class RemoveAccountCommand {

		private boolean successful = false;
		
		//record the error if failed to login
		private String errorMessage = null;
		
		//remove manager/staff account
		public void removeStaffAccount(int userID){
			
			try{
				
				Database.modifyDatabase("DELETE FROM stafflocations WHERE sl_user_id = " + userID + ";");
				Database.modifyDatabase("DELETE FROM staff WHERE s_user_id = " + userID + ";");
				Database.modifyDatabase("DELETE FROM users WHERE u_id = " + userID + ";");
				successful = true;
			}catch(Exception e){
				successful = false;
				errorMessage = "Failed to delete account";
			}
		}
		
		//remove an tenant
		public void removeTenantAccount(int userID){
			
			try{
				
				ResultSet rs = Database.runGetFromDatabaseSQL("SELECT ur_room_id FROM usersrooms WHERE ur_user_id = " +
				userID + ";");
				int roomID = 0;
				while(rs.next()){
					roomID = rs.getInt("ur_room_id");
				}
				
				Database.modifyDatabase("UPDATE apartmentrooms SET ar_occupy = 'false' WHERE ar_id = "+roomID+";");
				Database.modifyDatabase("DELETE FROM usersrooms WHERE ur_user_id = " + userID + ";");
				Database.modifyDatabase("DELETE FROM users WHERE u_id = " + userID + ";");
				successful = true;
			}catch(Exception e){
				successful = false;
				errorMessage = "Failed to delete account";
			}
		}
		
		public String getErrorMessage(){
			return errorMessage;
		}
		
		public boolean wasSuccessful(){
			return successful;
		}
}
