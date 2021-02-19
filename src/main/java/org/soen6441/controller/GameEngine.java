package org.soen6441.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.soen6441.model.*;


public class GameEngine {
	
	
	Scanner d_s = new Scanner(System.in);
	private String d_PlayerName;
	private Map d_Map;
	private ArrayList<Player> d_PlayerList;
	GameModel d_GameModel_Object = new GameModel(d_Map);
	
	
	public GameEngine(Map p_Map ){
		d_Map = p_Map;
	}
	public List<String> showall()
	{
		List<String> Names = new ArrayList<>();

		d_PlayerList=d_GameModel_Object.getAllPlayers();
		for(Player player:d_PlayerList)
		{
			Names.add(player.getPlayerName()+"->"+player.getCountriesSize());
		}
		
		return Names; 
		
	}
	
	/**
	 * This Method Will take input from player and add into the list
	 * 
	 * 
	 */
	public String GamePlayer(String p_Command,String p_Str) throws Exception
	{
		String[] l_commandArray = p_Str.split(" ");
		int l_Counter = 1;
		int l_AddCounter = 0;
		int l_RemoveCounter = 0;
		String l_ReturnString = "";
		while(l_Counter<l_commandArray.length) {
			if(l_commandArray[l_Counter].equals("-add")) 
			{
				d_GameModel_Object.addPlayer(l_commandArray[l_Counter+1]);
				l_Counter+=2;
				l_AddCounter+=1;
				
			}
			else if(l_commandArray[l_Counter].equals("-remove")) 
			{
				d_GameModel_Object.removePlayer(l_commandArray[l_Counter+1]);
				l_Counter+=2;
				l_RemoveCounter+=1;
			}
			else 
			{
				
				break;
			}
			}
		if(l_AddCounter>0) {
			l_ReturnString += "Number of Players Added : " + l_AddCounter + "\n";
		}
		if(l_RemoveCounter>0) {
			l_ReturnString += "Number of Players Removed : " + l_RemoveCounter + "\n";
		}
		
		return l_ReturnString;
	}
	/**
	 * This Method Will Assign all the countries to players
	 * assign army to each country for a player
	 * 
	 */
	public void AssignCountries()
	{
		d_GameModel_Object.startUpPhase();
	}
	

	
	
	/**
	 * This Method initializes the game by assigning armies and countries to players
	 * army to each country for a player
	 * 
	 */
	private void InitializeGame() 
	{
		
		///d_GameModel_Object.startUpPhase();


	}
	
	/**
	 * This Method is used to show the current status of the Map
	 * According to the current players
	 * 
	 */
	
	private void ShowMap() 
	{


	}
	/**
	 * This Method is used to show the  Load  Map from existing maps
	 * 
	 * 
	 */
	private void LoadMap() 
	{


	}
	
	public ArrayList<Player> getPlayerList() {
		return d_GameModel_Object.getAllPlayers();
	}
}
