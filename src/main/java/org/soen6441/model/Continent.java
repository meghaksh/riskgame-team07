package org.soen6441.model;

import java.util.ArrayList;

/**
 * Class for all the Continents  of the Map
 */
public class Continent {
	private static int D_Count=0;
	private int d_ID;
	private String d_Name;
	private int d_ContinentControlValue;
	private ArrayList<Country> d_CountryList;

	public Continent(String p_Name, int p_ContinentControlValue) {
		setContinentID(++D_Count);
		this.d_Name=p_Name;
		this.d_ContinentControlValue=p_ContinentControlValue;
		d_CountryList = new ArrayList<Country>();
	}
	
	public static void setCount(int p_count)
	{
		D_Count=p_count;
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
	
	/**
	 * To set Continent ID
	 * 
	 * @param p_ContinentID
	 */
	public void setContinentID(int p_ContinentID) {
		d_ID = p_ContinentID;
	}

	/**
	 * 
	 */
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

	/**
	 * To add Country to the Continent
	 * @param p_country
	 */
	public void addCountry(Country p_country) {
		this.d_CountryList.add(p_country);
	}
	
	/**
	 * To return the arraylist of all the countries in the continent
	 * @return
	 */
	public ArrayList<Country> getCountryList(){
		return this.d_CountryList;
	}
}


