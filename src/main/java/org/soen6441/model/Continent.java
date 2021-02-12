package org.soen6441.model;


/**
 * Class for all the Continents  of the Map
 */

public class Continent {
	
	int d_ID=0;
	String d_Name;
	int d_ContinentControlValue;
	
	
	/**
	 * Continent Object Constructor
	 * @param p_ContinentID
	 * @param p_Name
	 * @param p_ContinentControlValue
	 */
	public Continent(int p_ContinentID,String p_Name, int p_ContinentControlValue)
	{
		
		d_ID=p_ContinentID;
		this.d_Name=p_Name;
		this.d_ContinentControlValue=p_ContinentControlValue;
	}	
	/**
	 * Method to return continent name
	 * */
	public String getContinentName() {
		return this.d_Name;
	}
	/**
	 * To get Continent Control Value
	 * @return
	 */
	public int getContinentControlValue() {
		return d_ContinentControlValue;
	}
	/**
	 * To get Continent ID
	 * @return ContinentID
	 */
	public int getContinentID() {
		return d_ID;
	}

}


