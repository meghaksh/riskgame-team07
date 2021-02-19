package org.soen6441.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.soen6441.model.Continent;
import org.soen6441.model.Country;
import org.soen6441.model.Map;
import org.soen6441.view.CommandPrompt;

public class MapController {
	private Map d_mapModel;
	private CommandPrompt d_cpView;
	private boolean d_mapDone;
	public MapController(CommandPrompt p_cp, Map p_map){
		d_cpView = p_cp;
		d_mapModel = p_map;
		d_cpView.commandSendButtonListener(new CommandListener());
	}
	
	class CommandListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				String str = d_cpView.getCommandInput().trim();
				switch(str.split(" ")[0]){
					case "editcontinent" : 
						try {
							String l_AckMsg = EditMap("editcontinent", str);
							d_cpView.setCommandAcknowledgement(l_AckMsg + "\n");
						}catch(Exception p_Exception) {
							d_cpView.setCommandAcknowledgement(p_Exception.getMessage());
							d_cpView.setCommandAcknowledgement("\n");
						}
						break;
					case "editcountry" :
						try {
							String l_AckMsg = EditMap("editcountry", str);
							d_cpView.setCommandAcknowledgement(l_AckMsg + "\n");
						}catch(Exception p_Exception) {
							d_cpView.setCommandAcknowledgement(p_Exception.getMessage());
							d_cpView.setCommandAcknowledgement("\n");
						}
						break;
					case "editneighbour" :
						break;
					case "showmap":
						if(d_mapDone) {
							System.out.println("Call gameplay wala showmap");
						}else {
							System.out.println("Normal showmap");
							ArrayList<Continent> l_continentList = d_mapModel.getContinentList();
							if(l_continentList.size()>0) {
								for(Continent l_continent:l_continentList) {
									d_cpView.setCommandAcknowledgement(l_continent.getContinentName() + "--->");
									ArrayList<Country> l_countryList = l_continent.getCountryList();
									for(Country l_country:l_countryList) {
										d_cpView.setCommandAcknowledgement(l_country.getCountryName() + ",");
									}
									d_cpView.setCommandAcknowledgement("\n");
								}
							}
						}
						break;
					case "savemap":
						try {
							SaveMap(str);
						}catch(Exception exp) {
							d_cpView.setCommandAcknowledgement(exp.getMessage());
						}
						
						break;
					case "editmap":
						try {
							LoadMap(str);
						}catch(Exception exp) {
							d_cpView.setCommandAcknowledgement(exp.getMessage());
						}
						break;
					case "validatemap":
						break;
					case "loadmap": 
						try {
							LoadMap(str);
						}catch(Exception exp) {
							d_cpView.setCommandAcknowledgement(exp.getMessage());
						}
						
						break;
					case "gameplayer":
						break;
					case "assigncountries":
						break;
					case "deploy":
						break;
					default:
						d_cpView.setCommandAcknowledgement("Invalid Command. Please try again.\n");
						break;
				}
				d_cpView.setCommandInput("");
			}catch(Exception exp) {
				System.out.println(exp);
			}
		}
	}
	public void SaveMap(String p_str)throws Exception {
		String[] l_commandArray = p_str.split(" ");
		d_mapModel.SaveMap(l_commandArray[1]);
	}
	public void LoadMap(String p_str)throws Exception{
		String[] l_commandArray = p_str.split(" ");
		if(l_commandArray[0].equals("editmap"))
			{
			d_mapModel.LoadMap(l_commandArray[1]);
			System.out.print("editphase");
			}
		else
		{
			d_mapModel.LoadMap(l_commandArray[1]);
			d_mapDone=true;
		}
		
	}
	public String EditMap(String p_command, String p_str)throws Exception {
		String[] l_commandArray = p_str.split(" ");
		int l_counter = 1;
		int l_addContinentCounter = 0;
		int l_removeContinentCounter = 0;
		int l_addCountryCounter=0;
		int l_removeCountryCounter=0;
		String l_ReturnString = "";
		while(l_counter<l_commandArray.length) {
			if(l_commandArray[l_counter].equals("add")) {
				switch(p_command) {
					case "editcontinent":
						d_mapModel.AddContinent(l_commandArray[l_counter+1],l_commandArray[l_counter+2]);
						l_counter+=3;
						l_addContinentCounter+=1;
						break;
					case "editcountry":
						d_mapModel.AddCountry(l_commandArray[l_counter+1],l_commandArray[l_counter+2]);
						l_counter+=3;
						l_addCountryCounter+=1;
						break;
					case "editneighbor":
						break;
				}

			}else if(l_commandArray[l_counter].equals("remove")) {
				switch(p_command) {
					case "editcontinent":
						d_mapModel.RemoveContinent(l_commandArray[l_counter+1]);
						l_counter+=2;
						l_removeContinentCounter+=1;
						break;
					case "editcountry":
						d_mapModel.RemoveCountry(l_commandArray[l_counter+1]);
						l_counter+=2;
						l_removeCountryCounter+=1;
						break;
				}
				
			}else {
				break;
			}
		}
		if(l_addContinentCounter>0) {
			l_ReturnString += "Number of Continents Added : " + l_addContinentCounter + "\n";
		}
		if(l_removeContinentCounter>0) {
			l_ReturnString += "Number of Continents Removed : " + l_removeContinentCounter + "\n";
		}
		if(l_addCountryCounter>0) {
			l_ReturnString += "Number of Countries Added : " + l_addCountryCounter + "\n";
		}
		if(l_removeCountryCounter>0) {
			l_ReturnString += "Number of Countries Removed : " + l_removeCountryCounter + "\n";
		}
		d_mapModel.getContinents();
		if(d_mapModel.getCountryList().size()>0) {
			d_mapModel.getCountries();
		}
		return l_ReturnString;
	}
}

