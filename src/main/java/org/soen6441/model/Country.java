package org.soen6441.model;
import java.util.*;

/**
 * Class for all the countries/territories.
 * 
 *
 */
public class Country {

	int ID;
	String Name;
	int Continent_ID;
	ArrayList<Integer> neighbors;




	/**
	 * default constructor adding the id , name and continent id parameters to object 
	 */

	public Country(int ID, String Name, int Continent_ID)
	{

		this.Continent_ID=Continent_ID;
		this.ID=ID;
		this.Name=Name;
		neighbors=new ArrayList<Integer>();
	}

	/**
	 * Setting the neighbors/borders for each territory/country
	 */
	public  void setBorder(int borders)
	{
		this.neighbors.add(borders);
	}


	/**
	 * Getting the neighbors/borders for each territory/country
	 */
	public  ArrayList<Integer> getBorder()
	{
		return this.neighbors;
	}

}
