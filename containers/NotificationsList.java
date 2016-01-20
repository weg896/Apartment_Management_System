package containers;

import java.sql.ResultSet;
import java.util.LinkedList;

import commands.Notification;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import startup.Database;
/**
 *
 * @author Michael Lucki
 */
public class NotificationsList {

        private static LinkedList<Notification> messageList;
        
        public NotificationsList() {
            
            messageList = new LinkedList<Notification>();
        }
        
        public static LinkedList<Notification> recieveMessages(int userID) {
            
            Database.getInstance();
            Notification newMessage;
            messageList.clear();
         
            ResultSet rs = Database.runGetFromDatabaseSQL("SELECT n_id, n_title, n_message, n_sender, n_read, n_vis, n_date, n_notice_type from notifications where n_receiver = " + String.valueOf(userID));
         try {
            while (rs.next()) {
                    newMessage = new Notification(rs.getInt("n_sender"), userID, rs.getString("n_title"), rs.getString("n_message"), rs.getString("n_date"), rs.getInt("n_notice_type"));
                    newMessage.updateID(rs.getInt("n_id"));
                    messageList.add(newMessage);
                }
            }  
            catch (SQLException ex) {
                    Logger.getLogger(NotificationsList.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            return messageList;
        }
}
