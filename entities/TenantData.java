package entities;

/**
 * Group: 03
 * Class: CMPT 370
 */

/**
 * Contains the data for an individual tenant
 * @author taylorwiebe
 *
 */
public class TenantData
{
	/**
	 * The tenants id number
	 */
	private int tenantId;
	
	/**
	 * The room id number
	 */
	private int roomId;
	
	/**
	 * The first name of a tenant
	 */
	private String firstName;
	
	/**
	 * The last name of a tenant
	 */
	private String lastName;
	
	/**
	 * The last name of a tenant
	 */
	private String username;
	
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
	 * The room the tenant is in
	 */
	private int roomNumber;
	
	/**
	 * The building id the tenant is in
	 */
	private int buildingId;
	
	/**
	 * The building name the tenant is in
	 */
	private String buildingName;
	
	
	/**
	 * Initializes the tenant data
	 * @param fName
	 * @param lName
	 * @param phone
	 * @param contact
	 * @param email
	 */
	public TenantData(int setId, int setRoomId, String fName, String lName, long phone, String contact, String email, int room, int buildingNum, String building, String setUsername)
	{
		tenantId = setId;
		roomId = setRoomId;
		firstName = fName;
		lastName = lName;
		phoneNumber = phone;
		contactType = contact;
		emailAddress = email;
		roomNumber = room;
		buildingId = buildingNum;
		buildingName = building;
		username = setUsername;
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
	 * Returns the first name of a tenant
	 * @return String
	 */
	public String getUsername()
	{
		return username;
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
	public int getRoomNumber()
	{
		return roomNumber;
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
	public int getTenantId()
	{
		return tenantId;
	}
	
	/**
	 * gets the room id
	 * @return int
	 */
	public int getRoomId()
	{
		return roomId;
	}
	
	/**
	 * sets the room id
	 * @param setId
	 */
	public void setRoomId(int setId)
	{
		roomId = setId;
	}
	
	/**
	 * Checks if tenants are equal
	 * @return true if tenants are equal, false if not
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof TenantData)
		{
			TenantData check = (TenantData) obj;
			
			if (this.tenantId == check.getTenantId() &&
				this.roomId == check.roomId &&
				this.firstName.equals(check.getFirstName()) &&
				this.lastName.equals(check.getLastName()) &&
				this.phoneNumber == check.getPhoneNumber() &&
				this.contactType.equals(check.getContactType()) &&
				this.emailAddress.equals(check.getEmailAddress()) &&
				this.roomNumber == check.getRoomNumber() &&
				this.buildingId == check.getBuildingId() &&
				this.buildingName.equals(check.getBuildingName()))
			{
				return true;
			}
		}
		
		return false;
	}
}
