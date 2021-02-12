package org.soen6441.model;
import java.util.*;

/**
 * Class for all the countries/territories.
 * 
 *
 */
public class Country {

	int d_ID;
	String d_Name;
	int d_ContinentID;
	ArrayList<Integer> d_neighbors;
	int d_NoOfArmies;




	/**
	 * default constructor adding the id , name and continent id parameters to object
	 * @param CountryId, Country Name, Continent Id to which it belongs 
	 */
	public Country(int p_ID, String p_Name, int p_ContinentID)
	{

		this.d_ContinentID=p_ContinentID;
		this.d_ID=p_ID;
		this.d_Name=p_Name;
		d_neighbors=new ArrayList<Integer>();
	}

	/**
	 * Method to set the borders of neighboring Countries
	 * @param borders
	 */
	public  void setBorder(int borders)
	{
		this.d_neighbors.add(borders);
	}


	/**
	 * Method to get the list of neighboring Countries
	 * @return
	 */
	public  ArrayList<Integer> getBorder()
	{
		return this.d_neighbors;
	}
	
	/**
	 * Method to get Continent ID
	 * @return Continent ID
	 */
	public int getContinentId() {
		return d_ContinentID;	
	}
	/**
	 * Method to get Country Name
	 * @return Country Name
	 */
	public String getCountryName() {
		return d_Name;
	}
	/**
	 * Method to get Country ID
	 * @return COuntry ID
	 */
	public int getCountryID() {
		return d_ID;
	}

}
