package org.soen6441.model;
import java.util.*;

/**
 * Class for all the countries/territories.
 * 
 *
 */
public class Country {
	private static int d_COUNT = 0;
	int d_ID;
	String d_Name;
	//int d_ContinentID;
	String d_ContinentName;
	ArrayList<Integer> d_neighbors;
	int d_NoOfArmies;

	/**
	 * default constructor adding the id , name and continent id parameters to object
	 * @param CountryId, Country Name, Continent Id to which it belongs 
	 */
	public Country(int p_ID, String p_Name, int p_ContinentID)
	{
		//this.d_ContinentID=p_ContinentID;
		this.d_ID=p_ID;
		this.d_Name=p_Name;
		d_neighbors=new ArrayList<Integer>();
	}
	public Country(String p_Name, String p_continentName) {
		setCountryID(++d_COUNT);
		this.d_Name = p_Name;
		this.d_ContinentName = p_continentName;
		d_neighbors=new ArrayList<Integer>();
	}
	public String getContinentName() {
		return this.d_ContinentName;
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
		return 0;	
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
	
	public void setCountryID(int p_id) {
		d_ID = p_id;
	}
	
	@Override
	public boolean equals(Object p_Country) {
		if(this == p_Country) {
			return true;
		}
		if (p_Country == null || this.getClass() != p_Country.getClass()) {
			return false;
		}
		Country p1 = (Country)p_Country; 
		return this.getCountryName().equals(p1.getCountryName());
	}

}
