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
		private boolean d_StartUpDone = false;
		
		/**
		 * {@inheritDoc}
		 * On click of the button in view, this method gets the string which user entered. 
		 * Based on the type of the command, it will call the method of specific controllers. 
		 */
		@Override
		public void actionPerformed(ActionEvent l_E) {
			try {
				String l_CommandStringFromInput = d_CpView.getCommandInput().trim();
				switch(l_CommandStringFromInput.split(" ")[0]){
				case "editcontinent" : 
					if(d_MapDone==false) {
					try {
						System.out.println("Inside GameController");
						String l_AckMsg = d_MapController.editMap("editcontinent", l_CommandStringFromInput);
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
						String l_AckMsg = d_MapController.editMap("editcountry", l_CommandStringFromInput);
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
						try {
					String l_AckMsg = d_MapController.editMap("editneighbor", l_CommandStringFromInput);
					d_CpView.setCommandAcknowledgement(l_AckMsg + "\n");
						}catch(Exception p_Exception) {
							d_CpView.setCommandAcknowledgement(p_Exception.getMessage());
							d_CpView.setCommandAcknowledgement("\n");
						}
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
						String l_Result=d_MapController.saveMap(l_CommandStringFromInput);
						d_CpView.setCommandAcknowledgement(l_Result+"\n");
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
						String l_Result=d_MapController.loadMap(l_CommandStringFromInput);
						d_CpView.setCommandAcknowledgement(l_Result+"\n");
					}catch(Exception exp) {
						d_CpView.setCommandAcknowledgement("The Mapfile Doesnt Exist. Please Create A New Map" +"\n");
						
					}
					}else{
						d_CpView.setCommandAcknowledgement("Cant Edit Another Map In This Phase"+"\n");
					}
					break;
					
				case "validatemap":
					d_CpView.setCommandAcknowledgement(d_MapController.validateMap());
					break;
					
				case "loadmap": 
					try {
						String l_Result=d_MapController.loadMap(l_CommandStringFromInput);
						this.d_MapDone = true;
						d_CpView.setCommandAcknowledgement(l_Result+"\n");
					}catch(Exception exp) {
						d_CpView.setCommandAcknowledgement(exp.getMessage());
					}
					break;
					
				case "gameplayer":
					if(d_MapDone==true & d_StartUpDone==false) {
					try {
						String l_AckMsg1 = editPlayer("GamePlayer", l_CommandStringFromInput);
						d_CpView.setCommandAcknowledgement(l_AckMsg1 + "\n");
					}catch(Exception p_Exception) {
						d_CpView.setCommandAcknowledgement(p_Exception.getMessage());
						d_CpView.setCommandAcknowledgement("\n");
					}
					}else{
						d_CpView.setCommandAcknowledgement("\n"+"The Map is Not Loaded Yet to Add Players or you are trying to remove or add players after the startup phase"+"\n");
					}
					break;
					
				case "assigncountries":
					if(d_MapDone==true) {
					try {
						
					AssignCountries();
					
					}
					catch(Exception p_Exception) {
						d_CpView.setCommandAcknowledgement(p_Exception.getMessage());
						d_CpView.setCommandAcknowledgement("\n");
						break;
					}
					d_StartUpDone=true;
					showAllPlayerWithArmies();
					d_CpView.setCommandAcknowledgement("\n");
					d_PlayerController = new PlayerController(d_GameModelNew,d_CpView);
					d_PlayerController.player_issue_order();
					d_PlayerController.player_next_order();
					}
					
					else{
						d_CpView.setCommandAcknowledgement("\n"+"The Map is Not Loaded Yet to Add Assign Countries"+"\n");
					}
					break;
					
				case "deploy":
					d_PlayerController.setOrderString(l_CommandStringFromInput);
					break;
					
				case "show":
					if(d_MapDone==true) {
					showAllPlayerWithArmies();
					d_CpView.setCommandAcknowledgement("\n");
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
	
	/**
	 * <p>
	 * this Method will take inputs from the user and will add or remove player according
	 * to the inputs provided by the user
	 * 
	 * @param p_Command this is command entered by the player
	 * @param p_Str  this is name entered by the player in the command prompt
	 * @return returns string acknowledgement based on the added or removed players
	 * @throws Exception this is user defined exception based on the add player or remove player method
	 */
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

	/**
	 * This Method will take assign countries from command prompt and will do 
	 * startup Phase as well as assigning reinforcements to the player  
	 * @throws Exception this is user defined exception based on AssignCountries if there are no players assigned
	 * then it will throw an exception
	 * 
	 */
	public void AssignCountries() throws Exception {
		d_GameModelNew.startUpPhase();
	}

	/**
	 * this is a method to show all player details like  PlayerNames,armies,Countriesowned  
	 * @return this returns a list with all player details
	 */
	public void showAllPlayerWithArmies() {
		d_PlayerList=d_GameModelNew.getAllPlayers();
		for(Player player:d_PlayerList){
			d_CpView.setCommandAcknowledgement("\n"+player.getPlayerName()+"-->"+"armies assigned:"+player.getPlayerArmies());
			d_CpView.setCommandAcknowledgement("\n"+"Countries Assigned: ");
			for(Country l_country:player.getCountryList()) {
				d_CpView.setCommandAcknowledgement(l_country.getCountryName()+ ",");
			}
			//Names.add(player.getPlayerName()+"->"+player.getCountriesSize()+"armiesassigned->"+player.getPlayerArmies());
		}
		
	}

	public void showMap(Boolean p_BooleanForGamePhaseStarted) {
		if(p_BooleanForGamePhaseStarted) {
			System.out.println("Call gameplay wala showmap");

			ArrayList<Continent> l_ContinentList = d_GameModelNew.getMap().getContinentList();
			if(l_ContinentList.size()>0) {
				d_CpView.setCommandAcknowledgement("\n");
				for(Continent l_Continent:l_ContinentList) {
					
					d_CpView.setCommandAcknowledgement("Continent: "+l_Continent.getContinentName() + "\n");
					ArrayList<Country> l_CountryList = l_Continent.getCountryList();
					d_CpView.setCommandAcknowledgement("Countries:"+"\n");
					for(Country l_Country:l_CountryList) {
						d_CpView.setCommandAcknowledgement(l_Country.getCountryName()+", ");
						if(this.d_PlayerList!=null) {
							System.out.println("Inside player list");
							for(Player l_Player: d_PlayerList) {
								System.out.println("hello");
								if(l_Player.getCountryList().contains(l_Country)) {
									System.out.println("xyz");
									d_CpView.setCommandAcknowledgement("Owned By: "+l_Player.getPlayerName() );
								}
							}
						}
						ArrayList<String> l_NeighborList = l_Country.getBorder();
						if(l_NeighborList.size()>0) {
							d_CpView.setCommandAcknowledgement("--> Borders : ");
							for(String l_Str:l_NeighborList) {
								d_CpView.setCommandAcknowledgement(l_Str+ ",");
							}
							
						}
						
						d_CpView.setCommandAcknowledgement("\n");
					}
					d_CpView.setCommandAcknowledgement("\n");
				}
			}
		} else {
			System.out.println("Normal showmap");
			ArrayList<Continent> l_ContinentList = d_GameModelNew.getMap().getContinentList();
			if(l_ContinentList.size()>0) {
				d_CpView.setCommandAcknowledgement("\n");
				for(Continent l_Continent:l_ContinentList) {
					
					d_CpView.setCommandAcknowledgement("Continent: "+l_Continent.getContinentName() + "\n");
					ArrayList<Country> l_CountryList = l_Continent.getCountryList();
					d_CpView.setCommandAcknowledgement("Countries:"+"\n");
					for(Country l_Country:l_CountryList) {
						d_CpView.setCommandAcknowledgement(l_Country.getCountryName() + ", ");
						ArrayList<String> l_NeighborList = l_Country.getBorder();
						if(l_NeighborList.size()>0) {
							d_CpView.setCommandAcknowledgement("--> Borders : ");
							for(String l_Str:l_NeighborList) {
								d_CpView.setCommandAcknowledgement(l_Str+ " ");
							}
							
						}
						
						d_CpView.setCommandAcknowledgement("\n");
					}
					d_CpView.setCommandAcknowledgement("\n");
				}
			}
		}
	}	

}