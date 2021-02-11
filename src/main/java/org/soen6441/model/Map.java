package org.soen6441.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;

/** 
 * Model class for Map
 */

public class Map {
	ArrayList<Country> d_CountryObjects; 
	ArrayList<Continent> d_ContinentObjects;
	ArrayList<Integer> d_borders;  
	HashMap<Integer,ArrayList<Integer>> d_Neighbors;

	/**
	 * Default constructor
	 * 
	 */



	public Map()
	{
		d_CountryObjects=new ArrayList<Country>();
		d_borders=new ArrayList<Integer>();
		d_ContinentObjects=new ArrayList<Continent>();
		d_Neighbors=new HashMap<Integer,ArrayList<Integer>>();
	}

	/**
	 * This method creates a user edited map
	 * @param filename
	 */

	public void CreateMap(String p_Filename)
	{


	}

	/**
	 * This method returns the list of country objects
	 * @return CountryObjects
	 */
	public ArrayList<Country> getCountryList()
	{
		return this.d_CountryObjects;
	}



	/**
	 * This method loads a map file given by the user
	 * @param filename
	 * @throws FileNotFoundException 
	 */
	public void  LoadMap(String p_Filename) throws FileNotFoundException
	{
		int l_Count=0,l_ControlValue,l_ContinentID;
		File file =new File(p_Filename);
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine())
		{
			String l_line=sc.nextLine();
			if(l_line.contains("continents"))
			{
				l_line=sc.nextLine();
				while(!l_line.equals("") && sc.hasNextLine())
				{
					String[] l_arr = l_line.split(" ", 3);
					l_ControlValue=Integer.parseInt(l_arr[1]);
					d_ContinentObjects.add(new Continent(l_arr[0],l_ControlValue));
					l_line=sc.nextLine();
				}
				
			}
			if(l_line.contains("countries"))
			{
				l_line=sc.nextLine();
				while(!l_line.equals("") && sc.hasNextLine())
				{
					l_Count++;
					String[] l_arr1=l_line.split(" ",4);
					l_ContinentID=Integer.parseInt(l_arr1[2]);
					d_CountryObjects.add(new Country(l_Count,l_arr1[1],l_ContinentID));
					l_line=sc.nextLine();
				}

			}
			if(l_line.contains("borders"))
			{

				while(!l_line.equals("") && sc.hasNextLine())
				{
					l_line=sc.nextLine();
					String[] l_arr2=l_line.split(" ");
					Country obj=d_CountryObjects.get(Integer.parseInt(l_arr2[0])-1);
					for(int l_k=1;l_k<l_arr2.length;l_k++)
					{
						
						obj.setBorder(Integer.parseInt(l_arr2[l_k]));

					}

					

					d_borders.clear();
					d_Neighbors.put(Integer.parseInt(l_arr2[0]),d_borders);

				}



			}


		}

	}
	/**
	 * it saves the user edited map
	 */
	public void SaveMap()
	{
	}
	
	}
	

