package org.soen6441.controller;

import java.util.ArrayList;						import java.util.List;
import java.awt.event.ActionEvent;				import java.awt.event.ActionListener;
import org.soen6441.model.Continent;			import org.soen6441.model.Country;
import org.soen6441.model.GameModelNew;			import org.soen6441.model.Player;
import org.soen6441.view.CommandPrompt;

/**
 * This is the main controller class of MVC model. 
 * This class has a references of View, Models and various child controllers. 
 * This class acts as an intermediary between models/controllers and view.
 */
public class GameController {
	private GameModelNew d_GameModelNew;
	private CommandPrompt d_CpView;
	private MapController d_MapController;
	private ArrayList<Player> d_PlayerList;
	private PlayerController d_PlayerController;

	/**
	 * This controller takes view and model as arguments and use throughout the game. 
	 * 
	 * @param p_CpView main view of the game.
	 * @param p_GameModel main model of the game.
	 */
	public GameController(CommandPrompt p_CpView, GameModelNew p_GameModel) {
		d_GameModelNew = p_GameModel;
		d_CpView = p_CpView;
		d_MapController = new MapController(this.d_GameModelNew.getMap());
		d_CpView.commandSendButtonListener(new CommandListener());
	}
	
	/**
	 * This is a child class of the GameController which listens to the actions performed by button in view. 
	 * This class implements the ActionListener and override the actionPerformed method.
	 * This class is responsible for passing data from view to models/child controllers.
	 */
	class CommandListener implements ActionListener{
		private boolean d_MapDone = false;
		
		/**
		 * {@inheritDoc}
		 * On click of the button in view, this method gets the string which user entered. 
		 * Based on the type of the command, it will call the method of specific controllers. 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String l_CommandStringFromInput = d_CpView.getCommandInput().trim();
				switch(l_CommandStringFromInput.split(" ")[0]){
				case "editcontinent" : 
					if(d_MapDone==false) {
					try {
						System.out.println("Inside GameController");
						String l_AckMsg = d_MapController.EditMap("editcontinent", l_CommandStringFromInput);
						d_CpView.setCommandAcknowledgement(l_AckMsg + "\n");
					}catch(Exception p_Exception) {
						d_CpView.setCommandAcknowledgement(p_Exception.getMessage());
						d_CpView.setCommandAcknowledgement("\n");
					}
					}else {
						d_CpView.setCommandAcknowledgement("Cant Edit Map In This Phase"+"\n");
					}
					break;
					
				case "editcountry" :
					if(d_MapDone==false) {
					try {
						String l_AckMsg = d_MapController.EditMap("editcountry", l_CommandStringFromInput);
						d_CpView.setCommandAcknowledgement(l_AckMsg + "\n");
					}catch(Exception p_Exception) {
						d_CpView.setCommandAcknowledgement(p_Exception.getMessage());
						d_CpView.setCommandAcknowledgement("\n");
					}
					}else {
						d_CpView.setCommandAcknowledgement("Cant Edit Map In This Phase"+"\n");
					}
					break;
					
				case "editneighbor" :
					if(d_MapDone==false) {
					String l_AckMsg = d_MapController.EditMap("editneighbor", l_CommandStringFromInput);
					d_CpView.setCommandAcknowledgement(l_AckMsg + "\n");
					}
					else {
						d_CpView.setCommandAcknowledgement("Cant Edit Map In This Phase"+"\n");
					}
					break;
					
				case "showmap": 
					showMap(d_MapDone);
					break;
					
				case "savemap":
					if(d_MapDone==false) {
					try {
						d_MapController.SaveMap(l_CommandStringFromInput);
					}catch(Exception exp) {
						d_CpView.setCommandAcknowledgement(exp.getMessage());
					}
					}else{
						d_CpView.setCommandAcknowledgement("Cant Save Map In This Phase"+"\n");
					}
					break;
					
				case "editmap":
					if(d_MapDone==false) {
					try {
						d_MapController.LoadMap(l_CommandStringFromInput);
					}catch(Exception exp) {
						d_CpView.setCommandAcknowledgement(exp.getMessage());
					}
					}else{
						d_CpView.setCommandAcknowledgement("Cant Edit Another Map In This Phase"+"\n");
					}
					break;
					
				case "validatemap":
					d_CpView.setCommandAcknowledgement(d_MapController.ValidateMap());
					break;
					
				case "loadmap": 
					try {
						d_MapController.LoadMap(l_CommandStringFromInput);
						this.d_MapDone = true;
					}catch(Exception exp) {
						d_CpView.setCommandAcknowledgement(exp.getMessage());
					}
					break;
					
				case "gameplayer":
					if(d_MapDone==true) {
					try {
						String l_AckMsg1 = editPlayer("GamePlayer", l_CommandStringFromInput);
						d_CpView.setCommandAcknowledgement(l_AckMsg1 + "\n");
					}catch(Exception p_Exception) {
						d_CpView.setCommandAcknowledgement(p_Exception.getMessage());
						d_CpView.setCommandAcknowledgement("\n");
					}
					}else{
						d_CpView.setCommandAcknowledgement("The Map is Not Loaded Yet to Add Players"+"\n");
					}
					break;
					
				case "assigncountries":
					if(d_MapDone==true) {
					AssignCountries();
					List<String> l_AckMsg1 = showAllPlayerWithArmies();
					d_CpView.setCommandAcknowledgement(l_AckMsg1 + "\n");
					d_PlayerController = new PlayerController(d_GameModelNew.getAllPlayers(),d_CpView);
					d_PlayerController.player_issue_order();
					d_PlayerController.player_next_order();
					}else{
						d_CpView.setCommandAcknowledgement("The Map is Not Loaded Yet to Add Assign Countries"+"\n");
					}
					break;
					
				case "deploy":
					d_PlayerController.setOrderString(l_CommandStringFromInput);
					break;
					
				case "show":
					if(d_MapDone==true) {
					List<String> l_AckMsg2 = showAllPlayerWithArmies();
					d_CpView.setCommandAcknowledgement(l_AckMsg2 + "\n");
					}else{
						d_CpView.setCommandAcknowledgement("The Map is Not Loaded Yet to Perform Show Operation"+"\n");
					}
					break;
					
				default:
					d_CpView.setCommandAcknowledgement("Invalid Command. Please try again.\n");
					break;
				}
				d_CpView.setCommandInput("");
			}catch(Exception p_Exception) {
				System.out.println("Exception in ActionPerformed Method in ActionListener : " + p_Exception.getMessage());
			}
		}
	}
	
	public String editPlayer(String p_Command,String p_Str) throws Exception {
		String[] l_CommandArray = p_Str.split(" ");
		int l_Counter = 1;
		int l_AddCounter = 0;
		int l_RemoveCounter = 0;
		String l_ReturnString = "";
		while(l_Counter<l_CommandArray.length) {
			if(l_CommandArray[l_Counter].equals("-add")) {
				d_GameModelNew.addPlayer(l_CommandArray[l_Counter+1]);
				l_Counter+=2;
				l_AddCounter+=1;

			} else if(l_CommandArray[l_Counter].equals("-remove")){
				d_GameModelNew.removePlayer(l_CommandArray[l_Counter+1]);
				l_Counter+=2;
				l_RemoveCounter+=1;
			} else {
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

	public void AssignCountries() {
		d_GameModelNew.startUpPhase();
	}

	public List<String> showAllPlayerWithArmies() {
		List<String> l_Names = new ArrayList<>();
		d_PlayerList=d_GameModelNew.getAllPlayers();
		for(Player player:d_PlayerList){
			System.out.println(player.getPlayerName()+""+"armiesassigned->"+player.getPlayerArmies());
			for(Country l_country:player.getCountryList()) {
				System.out.println("Countriesowned"+l_country.getCountryName());
			}
			//Names.add(player.getPlayerName()+"->"+player.getCountriesSize()+"armiesassigned->"+player.getPlayerArmies());
		}
		return l_Names; 
	}

	public void showMap(Boolean p_BooleanForGamePhaseStarted) {
		if(p_BooleanForGamePhaseStarted) {
			System.out.println("Call gameplay wala showmap");
			ArrayList<Continent> l_ContinentList = d_GameModelNew.getMap().getContinentList();
			if(l_ContinentList.size()>0) {
				for(Continent l_Continent:l_ContinentList) {
					d_CpView.setCommandAcknowledgement(l_Continent.getContinentName() + "--->");
					ArrayList<Country> l_CountryList = l_Continent.getCountryList();
					for(Country l_Country:l_CountryList) {
						d_CpView.setCommandAcknowledgement(l_Country.getCountryName() + ",");
						for(Player l_Player: d_PlayerList) {
							if(l_Player.getCountryList().contains(l_Country)) {
								d_CpView.setCommandAcknowledgement("Owned By: "+l_Player.getPlayerName() );
							}
						}
						ArrayList<String> l_NeighborList = l_Country.getBorder();
						for(String l_Str:l_NeighborList) {
							d_CpView.setCommandAcknowledgement("Border : " + l_Str);
						}
					}
					d_CpView.setCommandAcknowledgement("\n");
				}
			}
		} else {
			System.out.println("Normal showmap");
			ArrayList<Continent> l_ContinentList = d_GameModelNew.getMap().getContinentList();
			if(l_ContinentList.size()>0) {
				for(Continent l_Continent:l_ContinentList) {
					d_CpView.setCommandAcknowledgement(l_Continent.getContinentName() + "--->");
					ArrayList<Country> l_CountryList = l_Continent.getCountryList();
					for(Country l_Country:l_CountryList) {
						d_CpView.setCommandAcknowledgement(l_Country.getCountryName() + ",");
						ArrayList<String> l_NeighborList = l_Country.getBorder();
						for(String l_Str:l_NeighborList) {
							d_CpView.setCommandAcknowledgement("Border : " + l_Str);
						}
					}
					d_CpView.setCommandAcknowledgement("\n");
				}
			}
		}
	}	
	
}