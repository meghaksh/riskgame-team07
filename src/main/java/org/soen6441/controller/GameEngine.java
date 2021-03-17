package org.soen6441.controller;

import java.util.ArrayList;						import org.soen6441.view.CommandPrompt;


import java.awt.event.ActionEvent;				import java.awt.event.ActionListener;
import org.soen6441.model.Continent;			import org.soen6441.model.Country;
import org.soen6441.model.GameModelNew;			import org.soen6441.model.Player;
import org.soen6441.observerpattern.LogEntryBuffer;
import org.soen6441.observerpattern.Logger;
import org.soen6441.observerpattern.Observable;
import org.soen6441.utility.state.Edit;
import org.soen6441.utility.state.Phase;



/**
 * This is the main controller class of MVC model. 
 * This class has a references of View, Models and various child controllers. 
 * This class acts as an intermediary between models/controllers and view.
 */
public class GameEngine  {
	private GameModelNew d_GameModelNew;
	private CommandPrompt d_CpView;
	private MapController d_MapController;
	private ArrayList<Player> d_PlayerList;
	private PlayerController d_PlayerController;
	private Phase gamePhase;
	private LogEntryBuffer d_LEB;
	
	public MapController getMapController() {
		return this.d_MapController;
	}
	public	CommandPrompt getViewObject() {
		return this.d_CpView;
	}
	public PlayerController getPlayerController() {
		return this.d_PlayerController;
	}
	public GameModelNew getGameModel() {
		return this.d_GameModelNew;
	}
	
	
	/**
	 * This controller takes view and model as arguments and use throughout the game. 
	 * 
	 * @param p_CpView main view of the game.
	 * @param p_GameModel main model of the game.
	 */
	public GameEngine(CommandPrompt p_CpView, GameModelNew p_GameModel) {
		d_GameModelNew = p_GameModel;
		d_CpView = p_CpView;
		d_MapController = new MapController(this.d_GameModelNew.getMap());
		d_PlayerController = new PlayerController(d_GameModelNew,d_CpView);
		d_CpView.commandSendButtonListener(new CommandListener());
		setPhase(new Edit(this,getViewObject()));
		d_LEB=new LogEntryBuffer();
	}
	public void setPhase(Phase p_phase) {
		gamePhase = p_phase;
	
	}
	public Phase getPhase() {
		return this.gamePhase;
	
	}

	/**
	 * This is a child class of the GameEngine which listens to the actions performed by button in view. 
	 * This class implements the ActionListener and override the actionPerformed method.
	 * This class is responsible for passing data from view to models/child controllers.
	 */
	public class CommandListener  implements ActionListener {
		private boolean d_MapDone = false;
		private boolean d_StartUpDone = false;
		private boolean d_AssignCountriesDone = false;
		
		
		
		
		
		
		
		
		
		
		/**
		 * {@inheritDoc}
		 * On click of the button in view, this method gets the string which user entered. 
		 * Based on the type of the command, it will call the method of specific controllers.
		 * <ul>
		 * <li>editcontinent, editcountry, editneighbor commands are handled by the map controller's editmap method.</li>
		 * <li>savemap, loadmap, editmap, validatemap commands are also handled by the map controller's respective methods.</li>
		 * <li>gameplayer, showmap commands are handled by the GameEngine's respective methods.</li>
		 * <li>For all the methods called from the various cases here, feedback is shown on the view.</li>
		 * </ul>  
		 */
		@Override
		public void actionPerformed(ActionEvent l_E) {
			try {
				String l_CommandStringFromInput = d_CpView.getCommandInput().trim();
				switch(l_CommandStringFromInput.split(" ")[0]){
				case "editcontinent" :
					
					d_LEB.setResult(l_CommandStringFromInput);
					d_CpView.setCommandAcknowledgement(gamePhase.editContinent("editcontinent",l_CommandStringFromInput));
						
					/*
					 * if(d_MapDone==false) { try { String l_AckMsg
					 * =d_MapController.editMap("editcontinent", l_CommandStringFromInput);
					 * 
					 * d_CpView.setCommandAcknowledgement(l_AckMsg + "\n"); }catch(Exception
					 * p_Exception){ d_CpView.setCommandAcknowledgement(p_Exception.getMessage());
					 * d_CpView.setCommandAcknowledgement("\n"); } }else {
					 * d_CpView.setCommandAcknowledgement("Cant Edit Map In This Phase"+"\n"); }
					 */
					 
					break;

				case "editcountry" :
					
					d_LEB.setResult(l_CommandStringFromInput);
					d_CpView.setCommandAcknowledgement(gamePhase.editCountry("editcountry",l_CommandStringFromInput));
					/*if(d_MapDone==false) {
						try {
							String l_AckMsg = d_MapController.editMap("editcountry", l_CommandStringFromInput);
							d_CpView.setCommandAcknowledgement(l_AckMsg + "\n");
						}catch(Exception p_Exception) {
							d_CpView.setCommandAcknowledgement(p_Exception.getMessage());
							d_CpView.setCommandAcknowledgement("\n");
						}
					}else {
						d_CpView.setCommandAcknowledgement("Cant Edit Map In This Phase"+"\n");
					}*/
					break;

				case "editneighbor" :
					
					d_LEB.setResult(l_CommandStringFromInput);
					d_CpView.setCommandAcknowledgement(gamePhase.editCountry("editneighbor",l_CommandStringFromInput));
					/*if(d_MapDone==false) {
						try {
							String l_AckMsg = d_MapController.editMap("editneighbor", l_CommandStringFromInput);
							d_CpView.setCommandAcknowledgement(l_AckMsg + "\n");
						}catch(Exception p_Exception) {
							d_CpView.setCommandAcknowledgement(p_Exception.getMessage());
							d_CpView.setCommandAcknowledgement("\n");
						}
					} else {
						d_CpView.setCommandAcknowledgement("Cant Edit Map In This Phase"+"\n");
					}*/
					break;

				case "showmap": 
					
					d_LEB.setResult(l_CommandStringFromInput);
					gamePhase.showMap();
					break;

				case "savemap":
					
					d_LEB.setResult(l_CommandStringFromInput);
					d_CpView.setCommandAcknowledgement(gamePhase.saveMap(l_CommandStringFromInput));
					/*if(d_MapDone==false) {
						try {
							String l_Result=d_MapController.saveMap(l_CommandStringFromInput);
							d_CpView.setCommandAcknowledgement(l_Result+"\n");
						}catch(Exception p_Exception) {
							d_CpView.setCommandAcknowledgement(p_Exception.getMessage()+"\n");
						}
					}else{
						d_CpView.setCommandAcknowledgement("Cant Save Map In This Phase"+"\n");
					}*/
					break;

				case "editmap":
					
					d_LEB.setResult(l_CommandStringFromInput);
					d_CpView.setCommandAcknowledgement(gamePhase.editMap(l_CommandStringFromInput));
					/*
					if(d_MapDone==false) {
						try {
							String l_Result=d_MapController.loadMap(l_CommandStringFromInput);
							d_CpView.setCommandAcknowledgement(l_Result+"\n");
						}catch(Exception p_Exception) {
							d_CpView.setCommandAcknowledgement("The Mapfile Doesnt Exist. Please Create A New Map" +"\n");
						}
					}else{
						d_CpView.setCommandAcknowledgement("Cant Edit Another Map In This Phase"+"\n");
					}*/
					break;

				case "validatemap":
					
					d_LEB.setResult(l_CommandStringFromInput);
					d_CpView.setCommandAcknowledgement(gamePhase.validateMap());
					/*if(d_MapDone==false) {
						try {
							d_CpView.setCommandAcknowledgement(d_MapController.validateMap());
						}catch(Exception p_Exception) {
							d_CpView.setCommandAcknowledgement(p_Exception.getMessage() +"\n");
						}
					}else {
						d_CpView.setCommandAcknowledgement("Cant validate Map In This Phase"+"\n");
					}*/
					break;

				case "loadmap":
					d_LEB.setResult(l_CommandStringFromInput);
					d_CpView.setCommandAcknowledgement(gamePhase.loadMap(l_CommandStringFromInput));
					/*try {
						String l_Result=d_MapController.loadMap(l_CommandStringFromInput);
						this.d_MapDone = true;
						d_CpView.setCommandAcknowledgement(l_Result+"\n");
					}catch(Exception p_Exception) {
						d_CpView.setCommandAcknowledgement(p_Exception.getMessage()+"\n");
					}*/
					break;

				case "gameplayer":
					d_LEB.setResult(l_CommandStringFromInput);
					d_CpView.setCommandAcknowledgement(gamePhase.addPlayers("GamePlayer",l_CommandStringFromInput));
					/*if(d_MapDone==true & d_StartUpDone==false) {
						try {
							String l_AckMsg1 = editPlayer("GamePlayer", l_CommandStringFromInput);
							d_CpView.setCommandAcknowledgement(l_AckMsg1 + "\n");
						}catch(Exception p_Exception) {
							d_CpView.setCommandAcknowledgement(p_Exception.getMessage()+"\n");
							d_CpView.setCommandAcknowledgement("\n");
						}
					}else{
						if(d_MapDone==false)
							d_CpView.setCommandAcknowledgement("\n"+"The Map is Not Loaded Yet to Add Players "+"\n");
						if(d_StartUpDone==true)
							d_CpView.setCommandAcknowledgement("\n"+"You are trying to remove or add players after the startup phase"+"\n");
					}*/
					break;

				case "assigncountries":
					d_LEB.setResult(l_CommandStringFromInput);
					gamePhase.assignCountries();
					/*if(d_MapDone==true & d_AssignCountriesDone==false) {
						try {	
							assignCountries();
						}
						catch(Exception p_Exception) {
							d_CpView.setCommandAcknowledgement(p_Exception.getMessage());
							d_CpView.setCommandAcknowledgement("\n");
							break;
						}
						d_AssignCountriesDone=true;
						d_StartUpDone=true;
						showAllPlayerWithArmies();
						d_CpView.setCommandAcknowledgement("\n");
						d_PlayerController = new PlayerController(d_GameModelNew,d_CpView);
						d_PlayerController.playerIssueOrder();
						d_PlayerController.playerNextOrder();
					} else 
					{
						if(d_MapDone==false)
							d_CpView.setCommandAcknowledgement("\n"+"The Map is Not Loaded Yet to Add Assign Countries "+"\n");
						if(d_AssignCountriesDone==true)
							d_CpView.setCommandAcknowledgement("\n"+"StartUp Phase is already completed "+"\n");
					}*/
					break;

				case "deploy":
					break;

				case "show":
					if(d_MapDone==true) {
						showAllPlayerWithArmies();
						d_CpView.setCommandAcknowledgement("\n");
					}else{
						d_CpView.setCommandAcknowledgement("The Map is Not Loaded Yet to Perform Show Operation"+"\n");
					}
					break;

				case "reset":
					d_LEB.setResult(l_CommandStringFromInput);
					d_MapController.reset();
					d_CpView.setCommandAcknowledgement("The Map is Reset"+"\n");
					break;

				default:
					d_LEB.setResult(l_CommandStringFromInput);
					d_CpView.setCommandAcknowledgement("Invalid Command. Please try again.\n");
					d_LEB.setResult("Invalid Command. Please try again.\n");
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
	 * @return l_ReturnString returns string acknowledgement based on the added or removed players
	 * @throws Exception this is user defined exception based on the add player or remove player method
	 */
	public String editPlayer(String p_Command,String p_Str) throws Exception {
		String[] l_CommandArray = p_Str.split(" ");
		int l_Counter = 1;
		int l_AddCounter = 0;
		int l_RemoveCounter = 0;
		String l_ReturnString = "";
		if(l_CommandArray.length < 3)
			throw new Exception("Please provide valid Parameters to add player");
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
	public void assignCountries() throws Exception {
		d_GameModelNew.startUpPhase();
	}

	/**
	 * This is a method to show all player details like  PlayerNames,armies,Countriesowned  
	 * 
	 */
	public void showAllPlayerWithArmies() {
		d_PlayerList=d_GameModelNew.getAllPlayers();
		for(Player l_Player:d_PlayerList){
			d_CpView.setCommandAcknowledgement("\n"+l_Player.getPlayerName()+"-->"+"armies assigned:"+l_Player.getPlayerArmies());
			d_CpView.setCommandAcknowledgement("\n"+"Countries Assigned: ");
			for(Country l_Country:l_Player.getCountryList()) {
				d_CpView.setCommandAcknowledgement(l_Country.getCountryName()+ ",");
			}
		}
	}

	/**
	 * This is a method to show all countries and continents, armies on each country, ownership, and connectivity
	 * <ul>
	 * <li>Map Phase : For Each Continent in Continent List, For each country in that continent, For each neighbor in that country</li>
	 * <li>Game Phase : Apart from continent, country and neighbors, it also shows player, thier ownership and their number of armies. </li>
	 * </ul>
	 * 
	 * @param p_BooleanForGamePhaseStarted takes boolean value to show map for map phase or game phase
	 */
	public void showMap(Phase gamePhase) {
		if(!gamePhase.getClass().getSimpleName().equals("Edit")) {
			System.out.println("reaching if");
			d_PlayerList = d_GameModelNew.getAllPlayers();
			ArrayList<Continent> l_ContinentList = d_GameModelNew.getMap().getContinentList();
			if(l_ContinentList.size()>0) {
				d_CpView.setCommandAcknowledgement("\n");
				for(Continent l_Continent:l_ContinentList) {
					d_CpView.setCommandAcknowledgement("Continent: "+l_Continent.getContinentName() + "\n");
					ArrayList<Country> l_CountryList = l_Continent.getCountryList();
					d_CpView.setCommandAcknowledgement("\n");
					for(Country l_Country:l_CountryList) {
						d_CpView.setCommandAcknowledgement("Country: "+ l_Country.getCountryName());
						if(this.d_PlayerList!=null) {
							for(Player l_Player: d_PlayerList) {
								if(l_Player.getCountryList().contains(l_Country)) {
									d_CpView.setCommandAcknowledgement("\n"+"-->Owner: "+l_Player.getPlayerName() );
									d_CpView.setCommandAcknowledgement("\n"+"-->Armies deployed: "+l_Country.getNoOfArmies());
								}
							}
						}
						ArrayList<String> l_NeighborList = l_Country.getBorder();
						if(l_NeighborList.size()>0) {
							d_CpView.setCommandAcknowledgement("\n"+"--> Borders : ");
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
			System.out.println("reaching else");
			ArrayList<Continent> l_ContinentList = d_GameModelNew.getMap().getContinentList();
			if(l_ContinentList.size()>0) {
				d_CpView.setCommandAcknowledgement("\n");
				for(Continent l_Continent:l_ContinentList) {
					d_CpView.setCommandAcknowledgement("Continent: "+l_Continent.getContinentName() + "\n");
					ArrayList<Country> l_CountryList = l_Continent.getCountryList();
					d_CpView.setCommandAcknowledgement("Countries:"+"\n");
					for(Country l_Country:l_CountryList) {
						d_CpView.setCommandAcknowledgement(l_Country.getCountryName());
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