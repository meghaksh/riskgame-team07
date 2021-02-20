package org.soen6441.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.soen6441.model.Continent;
import org.soen6441.model.Country;
import org.soen6441.model.GameModel;
import org.soen6441.model.GameModelNew;
import org.soen6441.model.Map;
import org.soen6441.model.Player;

/**
 * 
 * Class to show all continents and countries and their respective neighbors
 *
 */
public class ShowMap {

	InputStream d_is;
	BufferedReader d_br;
	String d_data="";
	Map d_map;
	GameModelNew d_gameModel;

	/**
	 * COnstructor that takes Map file as input and reads it line by line and calls mapDetails function
	 * @throws IOException
	 */
	public ShowMap() throws IOException {

		d_is = this.getClass().getResourceAsStream("bigeurope.map");
		d_br = new BufferedReader(new InputStreamReader(d_is));
		d_map = new Map();

		System.out.println("Reading the text File");

		while((d_data = d_br.readLine())!=null) {
			System.out.println(d_data);
		}
	}

	/**
	 * Used to load and save the Map
	 * @throws IOException
	 */
	void mapDetails() throws IOException{
		d_map.LoadMap("bigeurope.map");
		//d_map.SaveMap("Map.txt");	
	}


	/**
	 * This method is used to  print out the map in text form to show the continents, its countries and the neighbouring countries
	 */
	void printMap() {
		System.out.println("continent");
		for(Continent l_co: d_map.getContinentList())
		{
			System.out.println("Continent : "+l_co.getContinentName()+" "+l_co.getContinentControlValue()+ " "+l_co.getContinentID());
			for (Country l_cn : d_map.getCountryList()) {
				if(l_cn.getContinentId() == l_co.getContinentID()) {
					System.out.println("Country: "+l_cn.getCountryName());
					System.out.println("   Neighbours: ");
//					for (int i : l_cn.getBorder()) {
//						for(Country cn : d_map.getCountryList()) {
//							if(cn.getCountryID() == i) {
//								System.out.print(" "+cn.getCountryName());
//							}
//						}
//					}
					System.out.println("");

				}

			}
			System.out.println("-----------------------");
		}
	}

	public void showmap2() {
		ArrayList<Player> l_playerList = d_gameModel.getAllPlayers();
		for(Continent l_co: d_map.getContinentList())
		{
			System.out.println("Continent : "+l_co.getContinentName()+" "+l_co.getContinentControlValue()+ " "+l_co.getContinentID());
			for (Country l_cn : d_map.getCountryList()) {
				if(l_cn.getContinentId() == l_co.getContinentID()) {
					System.out.println("Country: "+l_cn.getCountryName());
					
					for(Player l_player : l_playerList) {
						if(l_player.getCountryList().contains(l_cn)) {
							System.out.println("Owned By: "+l_player.getPlayerName() );
						}
					}
					
					System.out.println("   Neighbours: ");
//					for (int i : l_cn.getBorder()) {
//						for(Country cn : d_map.getCountryList()) {
//							if(cn.getCountryID() == i) {
//								System.out.print(" "+cn.getCountryName());
//							}
//						}
//					}
					System.out.println("");

				}

			}
			System.out.println("-----------------------");
		}
		
	}
	public static void main(String []args) throws IOException {
		ShowMap shmp = new ShowMap();
		shmp.mapDetails();
		shmp.printMap();
	}

}
