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
	ArrayList<String> d_neighbors;
	int d_NoOfArmies;

	public Country(String p_Name, String p_continentName) {
		setCountryID(++d_COUNT);
		this.d_Name = p_Name;
		this.d_ContinentName = p_continentName;
		d_neighbors=new ArrayList<String>();
	}
	public String getContinentName() {
		return this.d_ContinentName;
	}
	/**
	 * Method to set the borders of neighboring Countries
	 * @param borders
	 */
	public  void setBorder(String p_Border)
	{
		this.d_neighbors.add(p_Border);
	}


	/**
	 * Method to get the list of neighboring Countries
	 * @return
	 */
	public  ArrayList<String> getBorder()
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
	
	public void removeBorder(String p_Border) {
		Iterator l_Iterator = this.d_neighbors.iterator();
		while(l_Iterator.hasNext()) {
			if(l_Iterator.next().toString().equals(p_Border)) {
				l_Iterator.remove();
			}
		}
	}
	
	public int getNoOfArmies() {
		return d_NoOfArmies;
	}
}
