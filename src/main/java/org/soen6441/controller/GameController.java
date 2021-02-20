package org.soen6441.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import org.soen6441.model.Continent;
import org.soen6441.model.Country;
import org.soen6441.model.GameModelNew;
import org.soen6441.model.Player;
import org.soen6441.view.CommandPrompt;

public class GameController {
	private GameModelNew d_GameModelNew;
	private CommandPrompt d_CpView;
	private MapController d_MapController;
	private GameEngine d_GameEngine;
	private ArrayList<Player> d_PlayerList;
	PlayerController d_playerController;
	public GameController(CommandPrompt p_CpView, GameModelNew p_GameModel) {
		d_GameModelNew = p_GameModel;
		d_CpView = p_CpView;
		d_MapController = new MapController(this.d_GameModelNew.getMap());
		d_CpView.commandSendButtonListener(new CommandListener());
	}
	
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
				d_GameModelNew.addPlayer(l_commandArray[l_Counter+1]);
				l_Counter+=2;
				l_AddCounter+=1;
				
			}
			else if(l_commandArray[l_Counter].equals("-remove")) 
			{
				d_GameModelNew.removePlayer(l_commandArray[l_Counter+1]);
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
	
	
	public void AssignCountries()
	{
		d_GameModelNew.startUpPhase();
	}
	
	public List<String> showall()
	{
		List<String> Names = new ArrayList<>();

		d_PlayerList=d_GameModelNew.getAllPlayers();
		
		
		for(Player player:d_PlayerList)
			
		{
			System.out.println(player.getPlayerName()+""+"armiesassigned->"+player.getPlayerArmies());
			for(Country l_country:player.getCountryList()) 
		{
				System.out.println("Countriesowned"+l_country.getCountryName());
		}
			
	
			//Names.add(player.getPlayerName()+"->"+player.getCountriesSize()+"armiesassigned->"+player.getPlayerArmies());
		}
		
		return Names; 
		
	}
	
	
	
	public void showmap(Boolean p_bool) {
		if(p_bool) {
			System.out.println("Call gameplay wala showmap");
			
			ArrayList<Continent> l_continentList = d_GameModelNew.getMap().getContinentList();
			if(l_continentList.size()>0) {
				for(Continent l_continent:l_continentList) {
					d_CpView.setCommandAcknowledgement(l_continent.getContinentName() + "--->");
					ArrayList<Country> l_countryList = l_continent.getCountryList();
					for(Country l_country:l_countryList) {
						d_CpView.setCommandAcknowledgement(l_country.getCountryName() + ",");
						
						for(Player l_player: d_PlayerList) {
							if(l_player.getCountryList().contains(l_country)) {
								d_CpView.setCommandAcknowledgement("Owned By: "+l_player.getPlayerName() );
							}
						}
						
						ArrayList<String> l_NeighborList = l_country.getBorder();
						for(String l_Str:l_NeighborList) {
							d_CpView.setCommandAcknowledgement("Border : " + l_Str);
						}
					}
					d_CpView.setCommandAcknowledgement("\n");
				}
			}
			
		}
		else {
			System.out.println("Normal showmap");
			ArrayList<Continent> l_continentList = d_GameModelNew.getMap().getContinentList();
			if(l_continentList.size()>0) {
				for(Continent l_continent:l_continentList) {
					d_CpView.setCommandAcknowledgement(l_continent.getContinentName() + "--->");
					ArrayList<Country> l_countryList = l_continent.getCountryList();
					for(Country l_country:l_countryList) {
						d_CpView.setCommandAcknowledgement(l_country.getCountryName() + ",");
						ArrayList<String> l_NeighborList = l_country.getBorder();
						for(String l_Str:l_NeighborList) {
							d_CpView.setCommandAcknowledgement("Border : " + l_Str);
						}
					}
					d_CpView.setCommandAcknowledgement("\n");
				}
			}
		}
	}
	
	
	class CommandListener implements ActionListener{
		private boolean d_mapDone = false;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				String str = d_CpView.getCommandInput().trim();
				switch(str.split(" ")[0]){
					case "editcontinent" : 
						try {
							System.out.println("Inside GameController");
							String l_AckMsg = d_MapController.EditMap("editcontinent", str);
							d_CpView.setCommandAcknowledgement(l_AckMsg + "\n");
						}catch(Exception p_Exception) {
							d_CpView.setCommandAcknowledgement(p_Exception.getMessage());
							d_CpView.setCommandAcknowledgement("\n");
						}
						break;
					case "editcountry" :
						try {
							String l_AckMsg = d_MapController.EditMap("editcountry", str);
							d_CpView.setCommandAcknowledgement(l_AckMsg + "\n");
						}catch(Exception p_Exception) {
							d_CpView.setCommandAcknowledgement(p_Exception.getMessage());
							d_CpView.setCommandAcknowledgement("\n");
						}
						break;
					case "editneighbor" :
						String l_AckMsg = d_MapController.EditMap("editneighbor", str);
						d_CpView.setCommandAcknowledgement(l_AckMsg + "\n");
						break;
					case "showmap": showmap(d_mapDone);
//						if(d_mapDone) {
//							System.out.println("Call gameplay wala showmap");
//						}else {
//							System.out.println("Normal showmap");
//							ArrayList<Continent> l_continentList = d_GameModelNew.getMap().getContinentList();
//							if(l_continentList.size()>0) {
//								for(Continent l_continent:l_continentList) {
//									d_CpView.setCommandAcknowledgement(l_continent.getContinentName() + "--->");
//									ArrayList<Country> l_countryList = l_continent.getCountryList();
//									for(Country l_country:l_countryList) {
//										d_CpView.setCommandAcknowledgement(l_country.getCountryName() + ",");
//										ArrayList<String> l_NeighborList = l_country.getBorder();
//										for(String l_Str:l_NeighborList) {
//											d_CpView.setCommandAcknowledgement("Border : " + l_Str);
//										}
//									}
//									d_CpView.setCommandAcknowledgement("\n");
//								}
//							}
//						}
						break;
					case "savemap":
						try {
							d_MapController.SaveMap(str);
						}catch(Exception exp) {
							d_CpView.setCommandAcknowledgement(exp.getMessage());
						}
						
						break;
					case "editmap":
						try {
							d_MapController.LoadMap(str);
						}catch(Exception exp) {
							d_CpView.setCommandAcknowledgement(exp.getMessage());
						}
						break;
					case "validatemap":
						String l_Str = d_MapController.ValidateMap();
						d_CpView.setCommandAcknowledgement(l_Str);
						break;
					case "loadmap": 
						try {
							d_MapController.LoadMap(str);
							this.d_mapDone = true;
						}catch(Exception exp) {
							d_CpView.setCommandAcknowledgement(exp.getMessage());
						}
						break;
					case "gameplayer":
						try {
							String l_AckMsg1 = GamePlayer("GamePlayer", str);
							d_CpView.setCommandAcknowledgement(l_AckMsg1 + "\n");
						}catch(Exception p_Exception) {
							d_CpView.setCommandAcknowledgement(p_Exception.getMessage());
							d_CpView.setCommandAcknowledgement("\n");
						}
						break;
					case "assigncountries":
						AssignCountries();
						List<String> l_AckMsg1 = showall();
						d_CpView.setCommandAcknowledgement(l_AckMsg1 + "\n");
						d_playerController = new PlayerController(d_GameModelNew.getAllPlayers(),d_CpView);
						d_playerController.player_issue_order();
						d_playerController.player_next_order();
						
						break;
					case "deploy":d_playerController.setOrderString(str);
						break;
					case "show":
						List<String> l_AckMsg2 = showall();
						d_CpView.setCommandAcknowledgement(l_AckMsg2 + "\n");
						break;
						
					default:
						d_CpView.setCommandAcknowledgement("Invalid Command. Please try again.\n");
						break;
				}
				d_CpView.setCommandInput("");
			}catch(Exception exp) {
				System.out.println(exp);
			}
		}
	}	
}
