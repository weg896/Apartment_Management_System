package entities;

/**
 * Group: 03
 * Class: CMPT 370
 */


/**
 * The data for a room
 * @author taylorwiebe
 *
 */
public class RoomData 
{
	/**
	 * The id of the room in the database
	 */
	private int roomId;
	
	/**
	 * The physical room number
	 */
	private int roomNumber;
	
	/**
	 * How big the room is
	 */
	private int squareFootage;
	
	/**
	 * The number of bedrooms in the apartment
	 */
	private int bedroomNumber;
	
	/**
	 * The building id of which the apartment is in
	 */
	private int buildingId;
	
	/**
	 * The base monthly rent of the apartment
	 */
	private int cost;
	
	/**
	 * Total number of tenants which can live there
	 */
	private int tenantNumber;
	
	/**
	 * name of the building
	 */
	private String buildingName;
	
	/**
	 * used for combo boxes
	 */
	private String roomListData;
	/**
	 * current tenants
	 */
	private int currentTenantsNumber;
	
	/**
	 * Initalizes the data for a room
	 * @param setRoomId - id of room in the database
	 * @param setRoomNumber - physical room number
	 * @param setSquareFootage - square footage of apartment
	 * @param setBedroomNumber - number of bedrooms
	 * @param setBuildingId - id of the building the apartment is in
	 * @param setCost - cost of the apartment
	 * @param setTenantNumber - number of tenants in the room
	 */
	public RoomData(String setBuildingName, int setRoomId, int setRoomNumber, int setSquareFootage, int setBedroomNumber, int setBuildingId, int setCost, int setTenantNumber, int setCurrentTenantsNumber)
	{
		buildingName = setBuildingName;
		roomId = setRoomId;
		roomNumber = setRoomNumber;
		squareFootage = setSquareFootage;
		bedroomNumber = setBedroomNumber;
		buildingId = setBuildingId;
		cost = setCost;
		tenantNumber = setTenantNumber;
		roomListData = getRoomNumber() + " " + getBuildingName();
		currentTenantsNumber = setCurrentTenantsNumber;
	}
	
	/**
	 * Gets the database id of the room
	 * @return int
	 */
	public int getRoomId()
	{
		return roomId;
	}
	
	/**
	 * Gets the physical number of the room
	 * @return int
	 */
	public int getRoomNumber()
	{
		return roomNumber;
	}
	
	/**
	 * Gets the square footage of the room
	 * @return - int
	 */
	public int getSquareFootage()
	{
		return squareFootage;
	}
	
	/**
	 * gets the number of bedrooms in an apartment
	 * @return int
	 */
	public int getBedroomNumber()
	{
		return bedroomNumber;
	}
	
	/**
	 * gets the id of the building the room is located in
	 * @return int
	 */
	public int getBuildingId()
	{
		return buildingId;
	}
	
	/**
	 * gets the cost of the room
	 * @return int
	 */
	public int getCost()
	{
		return cost;
	}
	
	/**
	 * gets the number of tenants that can be in a room
	 * @return int
	 */
	public int getTenantNumber()
	{
		return currentTenantsNumber;
	}
	
	/**
	 * returns the name of the building
	 * @return string
	 */
	public String getBuildingName()
	{
		return buildingName;
	}
	
	/**
	 * The name of the room in a combo box
	 * @return string
	 */
	public String getRoomListData()
	{
		return roomListData;
	}
	
	/**
	 * gets the current tenants in the room
	 * @return int
	 */
	public int getCurrentTenantsNumber()
	{
		return currentTenantsNumber;
	}
	
	/**
	 * Checks rooms are equal
	 * @return True if rooms are equal, false if not
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof RoomData)
		{
			RoomData check = (RoomData) obj;
			
			if (this.roomId == check.getRoomId() &&
					this.roomNumber == check.roomNumber && 
					this.squareFootage == check.squareFootage &&
					this.bedroomNumber == check.bedroomNumber &&
					this.buildingId == check.buildingId &&
					this.cost == check.cost &&
					this.tenantNumber == check.tenantNumber &&
					this.roomListData.equals(check.roomListData))
			{
				return true;
			}
		}
		
		
		return false;
	}
}
