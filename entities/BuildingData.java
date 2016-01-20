package entities;

/**
 * Group: 03
 * Class: CMPT 370
 */

/**
 * Holds the data for a building
 * @author taylorwiebe
 *
 */
public class BuildingData 
{
	/**
	 * Database id
	 */
	private int id;
	
	/**
	 * Address of the building
	 */
	private String address;
	
	/**
	 * Name of the building
	 */
	private String name;
	
	/**
	 * Initializes a building
	 * @param setId database id
	 * @param setAddress address of building
	 * @param setName name of the building
	 */
	public BuildingData(int setId, String setAddress, String setName)
	{
		id = setId;
		address = setAddress;
		name = setName;
	}
	
	/**
	 * Gets the id of the building
	 * @return int
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * gets the address of the building
	 * @return String
	 */
	public String getAddress()
	{
		return address;
	}
	
	/**
	 * gets the name of the building
	 * @return String
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Gets the name and address string
	 * @return String
	 */
	public String toString()
	{
		return name + ";    Address: " + address;
	}
	
	/**
	 * Checks that a building is equal to another building
	 * @return True if equal, false if not
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof BuildingData)
		{
			BuildingData check = (BuildingData) obj;
			
			if (this.id == check.getId() && this.address == check.getAddress() && this.name.equals(check.getName()))
			{
				return true;
			}
		}
		
		
		return false;
	}
}
