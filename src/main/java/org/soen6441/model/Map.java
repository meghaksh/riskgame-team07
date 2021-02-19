package org.soen6441.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
		System.out.println("Came inside");
		int l_ControlValue,l_ContinentID=1,l_CountryID;
		String l_path="resource\\";
		File file =new File(l_path+p_Filename);
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine())
		{
			
			String l_line=sc.nextLine();
			if(l_line.contains("continent"))
			{
				
				l_line=sc.nextLine();
				while(!l_line.equals("") && sc.hasNextLine())
				{
					String[] l_arr = l_line.split(" ", 3);
					l_ControlValue=Integer.parseInt(l_arr[1]);
					this.d_ContinentObjects.add(new Continent(l_arr[0],l_ControlValue));
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
					this.d_CountryObjects.add(new Country(l_arr1[1],l_arr1[2]));
					l_line=sc.nextLine();
				}

			}
			if(l_line.contains("borders"))
			{

				while(!l_line.equals("") && sc.hasNextLine())
				{
					l_line=sc.nextLine();
					String[] l_arr2=l_line.split(" ");
					Country obj=this.d_CountryObjects.get(Integer.parseInt(l_arr2[0])-1);
					for(int l_k=1;l_k<l_arr2.length;l_k++)
					{

						obj.setBorder(Integer.parseInt(l_arr2[l_k]));

					}
					d_borders.clear();
					d_Neighbors.put(Integer.parseInt(l_arr2[0]),d_borders);

				}



			}


		}
		System.out.println("outof while");

	}
	/**
	 * It saves the user edited map
	 * @param p_Filename 
	 * @throws IOException 
	 */
	public void SaveMap(String p_Filename) throws Exception
	{
		ArrayList<Integer> l_borders= new ArrayList<Integer>();
		String l_path="resource\\";
		File file=new File(l_path+p_Filename);
		FileWriter fw = new FileWriter(file);
		PrintWriter pr = new PrintWriter(fw);
		pr.println("");
		pr.println("continents");
		if(d_ContinentObjects.size()<=0) {
			throw new Exception("No Continent to Save");
		}
		for(Continent co: d_ContinentObjects)
		{
			pr.println(co.getContinentName()+" "+co.getContinentControlValue());
		}
		pr.println("");
		pr.println("countries");
		for(Country c: d_CountryObjects)
		{
			String continentName = c.getContinentName();
			int continentId=0;
			for(Continent ct:d_ContinentObjects) {
				if(ct.getContinentName().equals(continentName)) {
					continentId = ct.getContinentID();
				}
			}
			pr.println(c.d_ID+" "+c.d_Name+" "+continentId);
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
				RemoveAllCountryInContinent(l_TempContinent);
				l_Iterator.remove();
				l_RemovedFlag = true;
			}
		}
		if(!l_RemovedFlag){
			throw new Exception("Country in the list does not exist !!");
		}
	}
	public void AddCountry(String p_countryName, String p_continentName)throws Exception {
		Country l_tempCountry = new Country(p_countryName, p_continentName);
		for(Country l_country : d_CountryObjects) {
			if(l_country.getCountryName().equalsIgnoreCase(p_countryName)) {
				throw new Exception("Country Already Exist");
			}
		}
		this.d_CountryObjects.add(l_tempCountry);
		for(Continent l_continent : d_ContinentObjects) {
			if(l_continent.getContinentName().equals(p_continentName)) {
				l_continent.addCountry(l_tempCountry);
			}
		}
	}
	public void RemoveCountry(String p_countryName)throws Exception {
		Iterator<Country> l_Iterator = this.d_CountryObjects.iterator();
		boolean l_RemovedFlag = false;
		while(l_Iterator.hasNext()) {
			Country l_tempCountry = l_Iterator.next();
			if(l_tempCountry.getCountryName().equalsIgnoreCase(p_countryName)) {
				l_Iterator.remove();
				l_RemovedFlag = true;
			}
		}
		if(!l_RemovedFlag){
			throw new Exception("Country in the list does not exist !!");
		}
	}
	public void RemoveAllCountryInContinent(Continent p_tempContinent)throws Exception {
		ArrayList<Country> l_tempCountryList = p_tempContinent.getCountryList();
		Iterator<Country> l_countriesOfContinent = l_tempCountryList.iterator();
		while(l_countriesOfContinent.hasNext()) {
			RemoveCountry(l_countriesOfContinent.next().getCountryName());
		}
	}
	
	public void AddBorder(String p_countryId, String p_neighbourId) {
		for(Country l_tempCountry : this.getCountryList()) {
			if(l_tempCountry.getCountryID()==Integer.parseInt(p_countryId)) {
				l_tempCountry.setBorder(Integer.parseInt(p_neighbourId));
			}
		}
		if(d_Neighbors.get(Integer.parseInt(p_countryId))==null) {
			d_Neighbors.put(Integer.parseInt(p_countryId), new ArrayList<>());
		}
		d_Neighbors.get(Integer.parseInt(p_countryId)).add(Integer.parseInt(p_neighbourId));
	}
	
	public void RemoveBorder(String p_countryId, String p_neighbourId) {
		if(d_Neighbors.containsKey(Integer.parseInt(p_countryId))) {
			ArrayList<Integer> l_tempList = d_Neighbors.get(Integer.parseInt(p_countryId));
		}
	}
	
	public ArrayList<Continent> getContinentList(){
		return this.d_ContinentObjects;
	}

	public void getContinents() {
		for(Continent l_Continent: this.d_ContinentObjects) {
			System.out.println("ID :  " + l_Continent.getContinentID() +" Name : "+l_Continent.getContinentName());
		}
	}
	public void getCountries() {
		for(Country l_country: this.d_CountryObjects) {
			System.out.println("ID : " + l_country.getCountryID() + " Name : " + l_country.getCountryName() + " ContinentID : " + l_country.getContinentId());
		}
	}

}


