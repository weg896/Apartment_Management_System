package commands;

import java.sql.*;
import java.util.Date;
import java.text.*;

/**
 * the command that completes sending request task @ClientSide
 * @author YUQING TAN
 */
public class NoticeSendCommand {
	
	//record if the login succeed or not
	private boolean successful = false;
	
	//record the error if failed to login
	private String errorMessage = null;
	
	public void Send(int msgType, String msgTitle, String msgContent, int userID){
		
		String query = null;
		ResultSet rs;
		int newID = -1;
		int adminID = -1;
		//LinkedList<Integer> adminID = new LinkedList<Integer>();
		try{
			//try to find new id which is 1 plus current largest id
			query = "SELECT MAX(n_id) FROM notifications";
			rs = startup.Database.runGetFromDatabaseSQL(query);
			while(rs.next()){ newID = rs.getInt(1) + 1; }
			
			query = "SELECT u_id FROM users WHERE u_permission_status = 0";
			rs = startup.Database.runGetFromDatabaseSQL(query);
			while(rs.next()){
				adminID = (rs.getInt("u_id"));
				query = "INSERT INTO notifications VALUES (" +
					newID + ", '" +
					msgTitle + "', '" +
					msgContent + "', " +
					userID + ", " +
					adminID + ", " +
					"false, " +		// whether is read
					"true, '" +		// whether is visible
					getCurDate() + "', " +
					msgType + ")";
				startup.Database.modifyDatabase(query);
				newID++;
			}
			successful = true;
			
		}catch (SQLException e) {
			errorMessage = "Cannot query database";
			e.printStackTrace();
		}
	}
	
	private String getCurDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		return curDate;
	}

	public String getErrorMessage(){
		return errorMessage;
	}
	
	public boolean wasSuccessful(){
		return successful;
	}
	
}
