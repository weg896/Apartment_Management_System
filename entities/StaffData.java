package entities;

/**
 * Group: 03
 * Class: CMPT 370
 */

/**
 * Contains the data for an individual tenant
 * @
 *
 */
public class StaffData
{
	/**
	 * The users id number
	 */
	private int userID;
	
	/**
	 * The title
	 */
	private String title;
	
	/**
	 * The first name of a tenant
	 */
	private String firstName;
	
	/**
	 * The last name of a tenant
	 */
	private String lastName;
	
	/**
	 * The phone number of a tenant
	 */
	private long phoneNumber;
	
	/**
	 * The contact type of a tenant
	 */
	private String contactType;
	
	/**
	 * The email address of a tenant
	 */
	private String emailAddress;
	
	
	/**
	 * The building id the tenant is in
	 */
	private int buildingId;
	
	/**
	 * The building name the tenant is in
	 */
	private String buildingName;
	
	private String homeAddress;
	
	
	/**
	 * Initializes the tenant data
	 * @param fName
	 * @param lName
	 * @param phone
	 * @param contact
	 * @param email
	 */
	public StaffData(int setId, String tit, String fName, String lName, long phone, 
			String contact, String email,int buildingNum, String building,
			String haddress)
	{
		userID = setId;
		title = tit;
		firstName = fName;
		lastName = lName;
		phoneNumber = phone;
		contactType = contact;
		emailAddress = email;
		buildingId = buildingNum;
		buildingName = building;
		homeAddress = haddress;
	}
	
	/**
	 * Returns the first name of a tenant
	 * @return String
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * Returns the last name of a tenant	
	 * @return String
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * returns the phone number of a tenant
	 * @return int
	 */
	public long getPhoneNumber()
	{
		return phoneNumber;
	}
	
	/**
	 * Returns the contact type of the tenant
	 * @return String
	 */
	public String getContactType()
	{
		return contactType;
	}
	
	/**
	 * Returns the email of the tenant
	 * @return String
	 */
	public String getEmailAddress()
	{
		return emailAddress;
	}
	
	/**
	 * Gets the room number the tenant is in
	 * @return int
	 */
	public String getHomeAddress()
	{
		return homeAddress;
	}
	
	/**
	 * Gets the building id
	 * @return int
	 */
	public int getBuildingId()
	{
		return buildingId;
	}
	
	/**
	 * Gets the building name
	 * @return string
	 */
	public String getBuildingName()
	{
		return buildingName;
	}
	
	/**
	 * gets the tenant id
	 * @return int
	 */
	public int getUserID()
	{
		return userID;
	}
	
	public String getTitle(){
		return title;
	}
	
}

