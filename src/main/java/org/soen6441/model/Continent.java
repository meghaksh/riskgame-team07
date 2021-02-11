package org.soen6441.model;


/*
 * Class for all the Continents  of the Map
 */

public class Continent {
	
	static int d_ID=0;
	String d_Name;
	int d_ContinentControlValue;
	
	
	/*
	 * default constructor adding id, name and control value of continent to object
	 */
	public Continent(String p_Name, int p_ContinentControlValue)
	{
		this.d_ID=d_ID++;
		this.d_Name=p_Name;
		this.d_ContinentControlValue=p_ContinentControlValue;
	}

}


