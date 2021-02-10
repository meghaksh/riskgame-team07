package org.soen6441.model;


/*
 * Class for all the Continents  of the Map
 */

public class Continent {
	
	int ID;
	String Name;
	int Continent_Control_Value;
	
	
	/*
	 * default constructor adding id, name and control value of continent to object
	 */
	public Continent(int ID, String Name, int Continent_Control_Value)
	{
		this.ID=ID;
		this.Name=Name;
		this.Continent_Control_Value=Continent_Control_Value;
	}

}


