package commands;

import entities.*;
import containers.*;
import java.util.*;
import java.sql.*;

/**
 * the command to choose view options of notifications @ClientSide
 * @author YUQING TAN
 */
public class NoticeOptionCommand {
	
	public NoticeOptionCommand(){}
	
	public LinkedList<NoticeData> getUnread(int userID){
		/*
		String query = "SELECT * " +
				"FROM notifications, notiftype, users " +
				"WHERE nt_code = n_notice_type AND " +
				"n_sender = u_id AND " +
				"n_receiver = " + userID + " AND " +
				"n_read = false";
		*/
		LinkedList<NoticeData> lsNotice = new LinkedList<NoticeData>(); 
		Iterator<NoticeData> itrNotice = null;
		NoticeData curNotice = null;
		try {itrNotice = NoticeList.getInstance().iterator();} 
		catch (Exception e) {e.printStackTrace();}

		while (itrNotice.hasNext()){
			curNotice = itrNotice.next();
			if ((curNotice.getReceiver() == userID)&&(!curNotice.getRead())){
				lsNotice.add(curNotice);
			}
		}
		return lsNotice;
	}

	public LinkedList<NoticeData> getMonth(int userID){
		
		String query = "SELECT * " +
				"FROM notifications, notiftype, users " +
				"WHERE nt_code = n_notice_type AND " +
				"n_sender = u_id AND " +
				"n_receiver = " + userID + " AND " +
				"CAST(n_date AS DATE) > (current_date - 30)";
		
		ResultSet rs = startup.Database.runGetFromDatabaseSQL(query);
		LinkedList<NoticeData> lsNotice = new LinkedList<NoticeData>();
		try {
			while (rs.next()){
				lsNotice.add(new NoticeData(
						rs.getInt("n_id"),
						rs.getString("n_title"), 
						rs.getString("n_message"),
						rs.getInt("n_sender"),
						rs.getInt("n_receiver"),
						rs.getBoolean("n_read"),
						rs.getBoolean("n_vis"),
						rs.getString("nt_name"),
						rs.getString("n_date")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lsNotice;
	}
	
	public void MarkAsRead(int nid, boolean read){
		startup.Database.modifyDatabase("UPDATE notifications SET n_read = "+read+" WHERE n_id = "+nid);
	}
}
