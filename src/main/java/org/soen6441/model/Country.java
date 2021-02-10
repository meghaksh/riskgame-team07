package org.soen6441.model;


/**
 * Class for all the countries/territories.
 * 
 *
 */
public class Country {
	
	int ID;
	String Name;
	int Continent_ID;
	
	
	
	
	/**
	 * default constructor adding the id , name and continent id parameters to object 
	 */
	
	public Country(int ID, String Name, int Continent_ID)
	{
		
		this.Continent_ID=Continent_ID;
		this.ID=ID;
		this.Name=Name;
		
	}
	
	

}
