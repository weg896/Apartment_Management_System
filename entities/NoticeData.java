package entities;

/**
 * Group: 03
 * Class: CMPT 370
 */


/**
 * The data for a notice
 * @author YUQING TAN
 *
 */
public class NoticeData
{
	
	private int msgID;
	
	/**
	 * The title of a message
	 */
	private String msgTitle;
	
	/**
	 * The content of a message
	 */
	private String msgContent;
	
	/**
	 * The sender of a message
	 */
	private int msgSender;

	/**
	 * The receiver of a message
	 */
	private int msgReceiver;

	/**
	 * Whether the message is read
	 */
	private boolean msgRead;

	/**
	 * Whether the message is visible
	 */
	private boolean msgVisible;
	
	/**
	 * The type of a message
	 */
	private String msgType;

	/**
	 * The sent date of a message
	 */
	private String msgDate;


	public NoticeData(int mid, String mTitle, String mContent, int mSender, int mReceiver, 
			boolean mRead, boolean mVis, String mType, String mDate)
	{
		msgID = mid;
		msgTitle = mTitle;
		msgContent = mContent;
		msgSender = mSender;
		msgReceiver = mReceiver;
		msgRead = mRead;
		msgVisible = mVis;
		msgType = mType;
		msgDate = mDate;
	}
	
	public int getMsgID(){
		return msgID;
	}
	
	/**
	 * Returns the title of a message
	 * @return String
	 */
	public String getTitle()
	{
		return msgTitle;
	}
	
	/**
	 * Returns the content of a message
	 * @return String
	 */
	public String getContent()
	{
		return msgContent;
	}
	
	/**
	 * Returns the sender ID of a message
	 * @return int
	 */
	public int getSender()
	{
		return msgSender;
	}

	/**
	 * Returns the receiver ID of a message
	 * @return int
	 */
	public int getReceiver()
	{
		return msgReceiver;
	}
	
	/**
	 * Returns whether the message is read
	 * @return boolean
	 */
	public boolean getRead()
	{
		return msgRead;
	}

	/**
	 * Returns whether the message is visible
	 * @return boolean
	 */
	public boolean getVisible()
	{
		return msgVisible;
	}

	/**
	 * Returns the type of a message
	 * @return int
	 */
	public String getType()
	{
		return msgType;
	}

	/**
	 * Returns the date of a message
	 * @return String
	 */
	public String getDate()
	{
		return msgDate;
	}
}