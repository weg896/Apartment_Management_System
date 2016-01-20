package containers;

import java.sql.ResultSet;
import java.util.LinkedList;

import entities.NoticeData;

import startup.Database;

/**
 * Singleton for the notices list
 * @author YUQING TAN
 *
 */
public class NoticeList 
{
	/**
	 * A list of the notices in the system
	 */
	private static LinkedList<NoticeData> noticeList;
	
	/**
	 * Private constructor to ensure that no instance of this class is created. 
	 */
	public NoticeList() {}
	
	/**
	 * Initializes the linked list with the notices
	 * @return
	 * @throws Exception
	 */
	public static LinkedList<NoticeData> getInstance() throws Exception
	{
		if (noticeList == null)
		{
			noticeList = new LinkedList<NoticeData>();
		}
		
		populateNotice();
		return noticeList;
	}
	
	/**
	 * Populates the list with the data from the database
	 * @throws Exception
	 */
	private static void populateNotice() throws Exception
	{
		Database.getInstance();
		LinkedList<NoticeData> newData = new LinkedList<NoticeData>();
		
		// gets the notice from the database
		ResultSet rs = Database.runGetFromDatabaseSQL("SELECT * FROM notifications, notiftype WHERE nt_code = n_notice_type;");
		
		try
		{
			while(rs.next())
			{
				// creates a new notice object
				NoticeData notice = new NoticeData(
						rs.getInt("n_id"), 
						rs.getString("n_title"), 
						rs.getString("n_message"),
						rs.getInt("n_sender"),
						rs.getInt("n_receiver"),
						rs.getBoolean("n_read"),
						rs.getBoolean("n_vis"),
						rs.getString("nt_name"),
						rs.getString("n_date"));
				
				// adds new tenant to the linked list
				newData.add(notice);
			}
		}
		catch(Exception e)
		{
			throw new Exception();
		}
		noticeList = newData;
	}
}
