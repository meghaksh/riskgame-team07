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




	/**
	 * default constructor adding the id , name and continent id parameters to object 
	 */

	public Country(int p_ID, String p_Name, int p_ContinentID)
	{

		this.d_ContinentID=p_ContinentID;
		this.d_ID=p_ID;
		this.d_Name=p_Name;
		d_neighbors=new ArrayList<Integer>();
	}

	/**
	 * Setting the neighbors/borders for each territory/country
	 */
	public  void setBorder(int borders)
	{
		this.d_neighbors.add(borders);
	}


	/**
	 * Getting the neighbors/borders for each territory/country
	 */
	public  ArrayList<Integer> getBorder()
	{
		return this.d_neighbors;
	}

}
