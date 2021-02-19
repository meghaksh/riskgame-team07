package org.soen6441.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import org.soen6441.model.Continent;
import org.soen6441.model.Country;
import org.soen6441.model.GameModelNew;
import org.soen6441.view.CommandPrompt;

public class GameController {
	private GameModelNew d_GameModel;
	private CommandPrompt d_CpView;
	private MapController d_MapController;
	public GameController(CommandPrompt p_CpView, GameModelNew p_GameModel) {
		d_GameModel = p_GameModel;
		d_CpView = p_CpView;
		d_MapController = new MapController(this.d_GameModel.getMap());
		d_CpView.commandSendButtonListener(new CommandListener());
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
					case "showmap":
						if(d_mapDone) {
							System.out.println("Call gameplay wala showmap");
						}else {
							System.out.println("Normal showmap");
							ArrayList<Continent> l_continentList = d_GameModel.getMap().getContinentList();
							if(l_continentList.size()>0) {
								for(Continent l_continent:l_continentList) {
									d_CpView.setCommandAcknowledgement(l_continent.getContinentName() + "--->");
									ArrayList<Country> l_countryList = l_continent.getCountryList();
									for(Country l_country:l_countryList) {
										d_CpView.setCommandAcknowledgement(l_country.getCountryName() + ",");
									}
									d_CpView.setCommandAcknowledgement("\n");
								}
							}
						}
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
						break;
					case "assigncountries":
						break;
					case "deploy":
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
