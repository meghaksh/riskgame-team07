package org.soen6441.model;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


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
	 * @param p_Filename
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
	 * @param p_Filename
	 * @throws FileNotFoundException 
	 */
	public void  LoadMap(String p_Filename) throws FileNotFoundException
	{
		int l_ControlValue,l_ContinentID=1,l_CountryID;
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
					d_ContinentObjects.add(new Continent(l_ContinentID,l_arr[0],l_ControlValue));
					l_ContinentID++;
					l_line=sc.nextLine();
				}

			}
			if(l_line.contains("countries"))
			{
				l_line=sc.nextLine();
				while(!l_line.equals("") && sc.hasNextLine())
				{
					String[] l_arr1=l_line.split(" ",4);
					l_ContinentID=Integer.parseInt(l_arr1[2]);
					l_CountryID=Integer.parseInt(l_arr1[0]);
					d_CountryObjects.add(new Country(l_CountryID,l_arr1[1],l_ContinentID));
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
	 * It saves the user edited map
	 * @param p_Filename 
	 * @throws IOException 
	 */
	public void SaveMap(String p_Filename) throws IOException
	{
		ArrayList<Integer> l_borders= new ArrayList<Integer>();
		File file=new File(p_Filename);
		FileWriter fw = new FileWriter(file);
		PrintWriter pr = new PrintWriter(fw);
		pr.println("continent");
		for(Continent co: d_ContinentObjects)
		{
			pr.println(co.getContinentName()+" "+co.getContinentControlValue());
		}
		pr.println("");
		pr.println("countries");
		for(Country c: d_CountryObjects)
		{
			pr.println(c.d_ID+" "+c.d_Name+" "+c.d_ContinentID);
		}
		pr.println("");
		pr.println("borders");
		for(Country c: d_CountryObjects)
		{
			l_borders=c.getBorder();
			pr.print(c.d_ID+" ");
			for(int l_i: l_borders)
			{
				pr.print(l_i+" ");
			}
			pr.println("");
		}
		pr.close();
		fw.close();
		

	}
	public void AddContinent(String p_ContinentName, String p_ContinentControlValue) throws Exception {
		for(Continent l_contient:this.d_ContinentObjects) {
			if(l_contient.getContinentName().equalsIgnoreCase(p_ContinentName)) {
				throw new Exception("Continent Already Exists");
			}
		}
		this.d_ContinentObjects.add(new Continent(p_ContinentName, Integer.parseInt(p_ContinentControlValue)));
	}
	public void RemoveContinent(String p_ContinentName)throws Exception {
		Iterator<Continent> l_Iterator = this.d_ContinentObjects.iterator();
		boolean l_RemovedFlag = false;
		while(l_Iterator.hasNext()) {
			Continent l_TempContinent = l_Iterator.next();
			if(l_TempContinent.getContinentName().equalsIgnoreCase(p_ContinentName)) {
				l_Iterator.remove();
				l_RemovedFlag = true;
			}
		}
		if(!l_RemovedFlag){
			throw new Exception("Country in the list does not exist !!");
		}
	}
	public void AddCountry() {
		
	}
	public void RemoveCountry() {
		
	}
	public void RemoveAllCountryInContinent() {
		
	}
	public ArrayList<Continent> getContinentList(){
		return this.d_ContinentObjects;
	}

	public void getContinents() {
		for(Continent l_Continent: this.d_ContinentObjects) {
			System.out.println("ID :  " + l_Continent.getContinentID() +" Name : "+l_Continent.getContinentName());
		}
	}

}


