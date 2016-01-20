package commands;
import startup.Database;
/**
 *
 * @author Michael Lucki
 */

public class Notification {
  
   
    public int type;
    public int sender;
    public int id;
    public String title;
    public String content;
    public String date;
    public int recipient;
    
    public Boolean post()
    {
        
        String query = new String(); 
 
        query = "INSERT INTO notifications (n_title, n_message, n_sender, n_receiver, n_read, n_vis, n_date, n_notice_type) "
            + "VALUES ('" + title + "', '" + content + "', " + sender + ", "
            + recipient + ", false, true, '" + date + "', " + type + ");";
        
        
        if (Database.getInstance() != null) 
        	Database.modifyDatabase(query);
        else 
            System.out.println("no database connected");
        return true;     
        
    }
    public void updateType(int msg_type)
    {        
        type = msg_type;
    }
    public void updateSender(int msg_sender) 
    {        
        sender = msg_sender;
    }
    public void updateContent(String msg_content)
    {
        content = msg_content;
    }
    public void updateRecipient(int msg_recipient)
    {
        recipient = msg_recipient;
    }
    public void updateTitle(String msg_name)
    {
        title = msg_name;
    }
    public void updateDate(String msg_date)
    {
        date = msg_date;
    } 
    public void updateID(int msg_id) {
        id = msg_id;
    }
    public Notification(int msg_sender, int msg_recipient, String msg_name, String msg_content, String msg_date, int msg_type) {
        sender = msg_sender;
        recipient = msg_recipient;
        title = msg_name;
        content = msg_content;
        date = msg_date;
        type = msg_type;
    }
    public Notification() {
    
        title = new String();
        date = new String();
        content = new String();
    }
    
}
