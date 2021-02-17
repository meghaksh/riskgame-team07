package org.soen6441.model;

import java.util.ArrayList;

/**
 * Class for all the Continents  of the Map
 */
public class Continent {
	private static int d_COUNT=0;
	private int d_ID;
	private String d_Name;
	private int d_ContinentControlValue;
	private ArrayList<Country> d_countryList;

	public Continent(String p_Name, int p_ContinentControlValue) {
		setContinentID(++d_COUNT);
		this.d_Name=p_Name;
		this.d_ContinentControlValue=p_ContinentControlValue;
		d_countryList = new ArrayList<Country>();
	}
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
	 * 
	 * @return ContinentID
	 */
	public int getContinentID() {
		return d_ID;
	}
	public void setContinentID(int p_ContinentID) {
		d_ID = p_ContinentID;
	}

	@Override
	public boolean equals(Object p_Continent) {
		if(this == p_Continent) {
			return true;
		}
		if (p_Continent == null || this.getClass() != p_Continent.getClass()) {
			return false;
		}
		Continent p1 = (Continent)p_Continent; 
		return this.getContinentName().equals(p1.getContinentName());
	}
	
	public void addCountry(Country p_country) {
		this.d_countryList.add(p_country);
	}
	public ArrayList<Country> getCountryList(){
		return this.d_countryList;
	}
}


