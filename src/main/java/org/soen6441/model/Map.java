package org.soen6441.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;

/** 
 * Model class for Map
 */

public class Map {
	ArrayList<Country> Country_objects; 
	ArrayList<Continent> Continent_objects; 
	ArrayList<Integer> borders;  
	HashMap<Integer,ArrayList<Integer>> neighbors;

	/**
	 * Default constructor
	 * 
	 */



	public Map()
	{
		Country_objects=new ArrayList<Country>();
		new ArrayList<Integer>();
		new ArrayList<Continent>();
		new HashMap<Integer,ArrayList<Integer>>();
	}

	/**
	 * This method creates a user edited map
	 * @param filename
	 */

	public void CreateMap(String filename)
	{


	}


	public ArrayList<Country> getCountryList()
	{
		return this.Country_objects;
	}



	/**
	 * This method loads a map file given by the user
	 * @param filename
	 * @throws FileNotFoundException 
	 */
	public void LoadMap(String filename) throws FileNotFoundException
	{
		int count=0,controlvalue,continent_id;
		File file =new File(filename);
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine())
		{
			String line=sc.nextLine();
			if(line.contains("continents"))
			{
				line=sc.nextLine();
				while(!line.equals("") && sc.hasNextLine())
				{
					String[] arr = line.split(" ", 3);
					controlvalue=Integer.parseInt(arr[1]);
					Continent_objects.add(new Continent(arr[0],controlvalue));
					line=sc.nextLine();
				}
				
			}
			if(line.contains("countries"))
			{
				line=sc.nextLine();
				while(!line.equals("") && sc.hasNextLine())
				{
					count++;
					String[] arr1=line.split(" ",4);
					continent_id=Integer.parseInt(arr1[2]);
					Country_objects.add(new Country(count,arr1[1],continent_id));
					line=sc.nextLine();
				}

			}
			if(line.contains("borders"))
			{

				while(!line.equals("") && sc.hasNextLine())
				{
					line=sc.nextLine();
					String[] arr2=line.split(" ");
					Country obj=Country_objects.get(Integer.parseInt(arr2[0])-1);
					for(int k=1;k<arr2.length;k++)
					{
						
						obj.setBorder(Integer.parseInt(arr2[k]));

					}

					

					borders.clear();
					neighbors.put(Integer.parseInt(arr2[0]),borders);

				}



			}


		}

	}
}
