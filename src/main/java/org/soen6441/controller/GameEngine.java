package org.soen6441.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.soen6441.view.CommandPrompt;


import java.awt.event.ActionEvent;				import java.awt.event.ActionListener;
import java.io.File;

import org.soen6441.model.Continent;			import org.soen6441.model.Country;
import org.soen6441.model.GameModelNew;			import org.soen6441.model.Player;
import org.soen6441.observerpattern.LogEntryBuffer;	import org.soen6441.utility.state.Startup;
import org.soen6441.utility.state.Edit;
import org.soen6441.utility.state.Phase;
import org.soen6441.utility.state.IssueOrder;
import org.soen6441.utility.state.GameSaved;

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
	private Phase d_GamePhase;
	private LogEntryBuffer d_LEB;
	static int NUM=0;

	/**
	 * This method returns the reference of the MapController
	 * @return Mapcontroller reference
	 */
	public MapController getMapController() {
		return this.d_MapController;
	}
	/**
	 * This method returns the reference of the command prompt view
	 * @return Command prompt reference
	 */
	public	CommandPrompt getViewObject() {
		return this.d_CpView;
	}
	/**
	 * This method returns the reference of player controller
	 * @return the player controller reference
	 */
	public PlayerController getPlayerController() {
		return this.d_PlayerController;
	}
	/**
	 * This method returns the reference of the game model new.
	 * @return the GameModelNew reference
	 */
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
		d_PlayerController = new PlayerController(d_GameModelNew,d_CpView, this);
		d_CpView.commandSendButtonListener(new CommandListener());
		setPhase(new Edit(this,getViewObject()));
		d_LEB=new LogEntryBuffer();
	}
	/**
	 * This method sets the phase in game engine
	 * @param p_phase object of phase to set
	 */
	public void setPhase(Phase p_phase) {
		d_GamePhase = p_phase;

	}
	/**
	 * This method returns the current phase
	 * @return object of current phase
	 */
	public Phase getPhase() {
		return this.d_GamePhase;

	}

	/**
	 * This is a child class of the GameEngine which listens to the actions performed by button in view. 
	 * This class implements the ActionListener and override the actionPerformed method.
	 * This class is responsible for passing data from view to models/child controllers.
	 */
	public class CommandListener  implements ActionListener {
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
				case "tournament":
					d_LEB.setResult(l_CommandStringFromInput);
					d_CpView.setCommandAcknowledgement(d_GamePhase.tournament("tournament",l_CommandStringFromInput));

					break;

				case "editcontinent" :

					d_LEB.setResult(l_CommandStringFromInput);
					d_CpView.setCommandAcknowledgement(d_GamePhase.editContinent("editcontinent",l_CommandStringFromInput));

					break;

				case "editcountry" :

					d_LEB.setResult(l_CommandStringFromInput);
					d_CpView.setCommandAcknowledgement(d_GamePhase.editCountry("editcountry",l_CommandStringFromInput));

					break;

				case "editneighbor" :

					d_LEB.setResult(l_CommandStringFromInput);
					d_CpView.setCommandAcknowledgement(d_GamePhase.editCountry("editneighbor",l_CommandStringFromInput));

					break;

				case "showmap": 

					d_LEB.setResult(l_CommandStringFromInput);
					d_GamePhase.showMap();

					break;

				case "savemap":

					d_LEB.setResult(l_CommandStringFromInput);
					d_CpView.setCommandAcknowledgement(d_GamePhase.saveMap(l_CommandStringFromInput));

					break;

				case "editmap":

					d_LEB.setResult(l_CommandStringFromInput);
					d_CpView.setCommandAcknowledgement(d_GamePhase.editMap(l_CommandStringFromInput));

					break;

				case "validatemap":

					d_LEB.setResult(l_CommandStringFromInput);
					d_CpView.setCommandAcknowledgement(d_GamePhase.validateMap());

					break;

				case "loadmap":
					d_LEB.setResult(l_CommandStringFromInput);
					d_CpView.setCommandAcknowledgement(d_GamePhase.loadMap(l_CommandStringFromInput));

					break;

				case "gameplayer":
					d_LEB.setResult(l_CommandStringFromInput);
					d_CpView.setCommandAcknowledgement(d_GamePhase.addPlayers("GamePlayer",l_CommandStringFromInput));

					break;

				case "assigncountries":
					d_LEB.setResult(l_CommandStringFromInput);
					d_GamePhase.assignCountries();

					break;

				case "reset":
					d_LEB.setResult(l_CommandStringFromInput);
					d_MapController.reset();
					d_CpView.setCommandAcknowledgement("The Map is Reset"+"\n");
					break;

				case "loadgame":
					d_LEB.setResult(l_CommandStringFromInput);
					loadGame(l_CommandStringFromInput);
					break;

				case "savegame":
					d_LEB.setResult(l_CommandStringFromInput);
					saveGame(l_CommandStringFromInput);
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
	 * This is a method to show all player details like  PlayerNames,armies,Countries owned  
	 * 
	 */
	public void showAllPlayerWithArmies() {
		d_LEB.setResult(":::::::::::::::::::::::::::: Players, Armies, Countries, Cards :::::::::::::::::::::::::::::::::::::::");
		d_PlayerList=d_GameModelNew.getAllPlayers();
		for(Player l_Player:d_PlayerList){
			d_LEB.setResult("\n"+l_Player.getPlayerName()+"-->"+"armies assigned:"+l_Player.getPlayerArmies());
			d_CpView.setCommandAcknowledgement("\n"+l_Player.getPlayerName()+"-->"+"armies assigned:"+l_Player.getPlayerArmies());
			d_LEB.setResult("\n"+"Countries Assigned: ");
			d_CpView.setCommandAcknowledgement("\n"+"Countries Assigned: ");

			for(Country l_Country:l_Player.getCountryList()) {
				d_LEB.setResult(l_Country.getCountryName()+ ",");
				d_CpView.setCommandAcknowledgement(l_Country.getCountryName()+ ",");
			}
			if(l_Player.getCardList().size()>0)
			{
				d_LEB.setResult("\n"+"Cards Assigned: ");
				d_CpView.setCommandAcknowledgement("\n"+"Cards Assigned: ");
				ArrayList<String> l_CardList =l_Player.getCardList();
				for(String l_Str:l_CardList)
				{
					d_LEB.setResult(l_Str+ ",");
					d_CpView.setCommandAcknowledgement(l_Str+ ",");
				}
			}
		}
		d_LEB.setResult(":::::::::::::::::::::::::::: Players, Armies, Countries, Cards :::::::::::::::::::::::::::::::::::::::");
	}

	/**
	 * This is a method to show all countries and continents, armies on each country, ownership, and connectivity
	 * <ul>
	 * <li>Map Phase : For Each Continent in Continent List, For each country in that continent, For each neighbor in that country</li>
	 * <li>Game Phase : Apart from continent, country and neighbors, it also shows player, thier ownership and their number of armies. </li>
	 * </ul>
	 * 
	 * @param p_GamePhase takes the phase object and shows the map accordingly
	 */
	public void showMap(Phase p_GamePhase) {
		if(!p_GamePhase.getClass().getSimpleName().equals("Edit")) {
			d_LEB.setResult(":::::::::::::::::::::::::::: ShowMap :::::::::::::::::::::::::::::::::::::::");
			d_PlayerList = d_GameModelNew.getAllPlayers();
			ArrayList<Continent> l_ContinentList = d_GameModelNew.getMap().getContinentList();
			if(l_ContinentList.size()>0) {
				d_LEB.setResult("\n");
				d_CpView.setCommandAcknowledgement("\n");
				for(Continent l_Continent:l_ContinentList) {
					d_LEB.setResult("Continent: "+l_Continent.getContinentName() + "\n");
					d_CpView.setCommandAcknowledgement("Continent: "+l_Continent.getContinentName() + "\n");
					ArrayList<Country> l_CountryList = l_Continent.getCountryList();
					d_LEB.setResult("\n");
					d_CpView.setCommandAcknowledgement("\n");
					for(Country l_Country:l_CountryList) {
						d_LEB.setResult("Country: "+ l_Country.getCountryName());
						d_CpView.setCommandAcknowledgement("Country: "+ l_Country.getCountryName());

						if(l_Country.getCountryOwnerPlayer()!=null){
							d_LEB.setResult("-->Owner: "+ l_Country.getCountryOwnerPlayer().getPlayerName());
							d_CpView.setCommandAcknowledgement("-->Owner: "+ l_Country.getCountryOwnerPlayer().getPlayerName());
							d_LEB.setResult("-->Armies deployed: "+ l_Country.getNoOfArmies());
							d_CpView.setCommandAcknowledgement("-->Armies deployed: "+ l_Country.getNoOfArmies());
						}

						ArrayList<String> l_NeighborList = l_Country.getBorder();
						if(l_NeighborList.size()>0) {
							d_LEB.setResult("\n"+"--> Borders : ");
							d_CpView.setCommandAcknowledgement("\n"+"--> Borders : ");
							for(String l_Str:l_NeighborList) {
								d_LEB.setResult(l_Str+ ",");
								d_CpView.setCommandAcknowledgement(l_Str+ ",");
							}	
						}
						d_LEB.setResult("\n");
						d_CpView.setCommandAcknowledgement("\n");
					}
					d_LEB.setResult("\n");
					d_CpView.setCommandAcknowledgement("\n");
				}
			}
		} else {
			ArrayList<Continent> l_ContinentList = d_GameModelNew.getMap().getContinentList();
			if(l_ContinentList.size()>0) {
				d_LEB.setResult("\n");
				d_CpView.setCommandAcknowledgement("\n");
				for(Continent l_Continent:l_ContinentList) {
					d_LEB.setResult("Continent: "+l_Continent.getContinentName() + "\n");
					d_CpView.setCommandAcknowledgement("Continent: "+l_Continent.getContinentName() + "\n");
					ArrayList<Country> l_CountryList = l_Continent.getCountryList();
					d_LEB.setResult("Countries:"+"\n");
					d_CpView.setCommandAcknowledgement("Countries:"+"\n");
					for(Country l_Country:l_CountryList) {
						d_LEB.setResult(l_Country.getCountryName());
						d_CpView.setCommandAcknowledgement(l_Country.getCountryName());
						ArrayList<String> l_NeighborList = l_Country.getBorder();
						if(l_NeighborList.size()>0) {
							d_LEB.setResult("--> Borders : ");
							d_CpView.setCommandAcknowledgement("--> Borders : ");
							for(String l_Str:l_NeighborList) {
								d_LEB.setResult(l_Str+ " ");
								d_CpView.setCommandAcknowledgement(l_Str+ " ");
							}
						}
						d_LEB.setResult("\n");
						d_CpView.setCommandAcknowledgement("\n");
					}
					d_LEB.setResult("\n");
					d_CpView.setCommandAcknowledgement("\n");
				}
			}
		}
		d_LEB.setResult(":::::::::::::::::::::::::::: ShowMap :::::::::::::::::::::::::::::::::::::::");
	}
	public void loadGame(String p_Command) {
		boolean flag=false;
		this.d_GameModelNew=GameModelNew.loadGame(p_Command.split(" ")[1]);
		if (this.d_GameModelNew==null) {
			d_CpView.setCommandAcknowledgement("\nGame not found");
			d_LEB.setResult("Game not found");
			return;
		}
		d_LEB.setResult("Game Loaded");
		d_CpView.setCommandAcknowledgement("\nGame Loaded");

		if(this.d_GameModelNew.getAllPlayers().size()<=1) {
			this.setPhase(new Startup(this,d_CpView));
		} else {
			ArrayList<Player> l_Players=this.d_GameModelNew.getAllPlayers();
			for(Player l_P : l_Players) {
				if(l_P.getCountriesSize()>0) {
					flag=true;
					break;
				}
			}
			if(flag==true) {
				this.setPhase(new IssueOrder(this,d_CpView));
			} else {
				this.setPhase(new Startup(this,d_CpView));
			}
		}
	}

	public void saveGame(String p_Command) {
		this.d_GameModelNew.saveGame(p_Command.split(" ")[1]);
		this.setPhase(new GameSaved(this,d_CpView));
	}

	public void tournament(String p_InputString) throws Exception {

		int l_M = 0, l_P = 0, l_G = 0, l_D = 0;
		String[] l_MapList = null;
		String[] l_PlayerStrategyList=null;
		ArrayList<Map> l_Maps=new ArrayList<Map>();
		ArrayList<File> l_Files=new ArrayList<File>();
		String l_Path="resource\\";
		String[] l_CommandArray = p_InputString.split(" ");
		if(l_CommandArray[1].equals("-M")){
			l_MapList=l_CommandArray[2].split(",");
			if(l_MapList.length>5||l_MapList.length<1)
				d_CpView.setCommandAcknowledgement("Number of Maps are not in range");//throw exception
			else 
				l_M=l_MapList.length;
			for(int i=0;i<l_M;i++) {
				File l_F = new File(l_Path+l_MapList[i]);
				if (!l_F.exists())
					d_CpView.setCommandAcknowledgement("File does not Exists");//throw exception
				else
					l_Files.add(l_F);
			}
		}
		if(l_CommandArray[3].equals("-P")){
			l_PlayerStrategyList=l_CommandArray[4].split(",");
			if(l_PlayerStrategyList.length>4||l_PlayerStrategyList.length<2)
				d_CpView.setCommandAcknowledgement("Number of Player strategies are are not in range");//throw exception
			else {
				l_P=l_PlayerStrategyList.length;
				System.out.println("Player strategy list size: "+l_PlayerStrategyList.length);
			}
		}
		if(l_CommandArray[5].equals("-G")){
			int l_NumGames=Integer.parseInt(l_CommandArray[6]);
			if(l_NumGames>5||l_NumGames<1)
				d_CpView.setCommandAcknowledgement("Number of Games are not in range");//throw exception
			else
				l_G=l_NumGames;
		}
		if(l_CommandArray[7].equals("-D")){
			int l_MaxTurns=Integer.parseInt(l_CommandArray[8]);
			if(l_MaxTurns>50||l_MaxTurns<10)
				d_CpView.setCommandAcknowledgement("Number of Maxturns are greater than 50 so first 50 Maxturns have been considered");//throw exception
			else
				l_D=l_MaxTurns;
		}
		HashMap<String, ArrayList<String>> tournamentResult = new HashMap<>();

		for (int i = 0; i < l_M; i++) {
			d_CpView.setCommandAcknowledgement("\n=============================================\n");
			d_CpView.setCommandAcknowledgement("\nMap number:"+(i+1)+"\n");
			ArrayList<String> result = new ArrayList<>();


			for (int j = 0; j < l_G; j++) {

				d_GameModelNew.getMap().loadMap(l_MapList[i]);
				d_CpView.setCommandAcknowledgement("\n=============================================\n");
				d_CpView.setCommandAcknowledgement("\nGame number:"+(j+1)+"\n");

				for(int k=0;k<l_P;k++) {
					d_GameModelNew.addPlayer("Player"+(NUM++),l_PlayerStrategyList[k]);
				}
				d_GameModelNew.tournamentstartUpPhase();
				int l_Noofturns=0;
				while(true)
				{
					d_GameModelNew.assignReinforcementArmies();
					this.getPlayerController().playerIssueOrder();
					this.getPlayerController().playerNextOrder();
					if(this.getPlayerController().getWinner()!=null) {
						result.add(this.getPlayerController().getWinner().getPlayerName());
						d_CpView.setCommandAcknowledgement(this.getPlayerController().getWinner().getPlayerName()+"is the winner");
						d_GameModelNew.getMap().reset();
						break;
					}

					if(l_Noofturns == l_D) {
						d_CpView.setCommandAcknowledgement("\nMatch is draw");
						result.add("Draw");
						d_GameModelNew.getAllPlayers().clear();
						d_GameModelNew.getMap().reset();
						break;
					}
					l_Noofturns++;
				}
				tournamentResult.put(l_MapList[i],result);
			}
		}
		printTournamentResult(l_M, l_G, l_D, tournamentResult, l_PlayerStrategyList);
	}

	private void printTournamentResult(int p_M, int p_G, int p_D, HashMap<String, ArrayList<String>> p_tournamentResult,
			String[] p_PlayerStrategyList) {

		String[] mapStrings = p_tournamentResult.keySet().toArray(new String[p_tournamentResult.keySet().size()]);
		d_CpView.setCommandAcknowledgement("\n");
		d_CpView.setCommandAcknowledgement("\n");
		d_CpView.setCommandAcknowledgement("=============================================\n");
		d_CpView.setCommandAcknowledgement("==============TOURNAMENT RESULT===============\n");
		d_CpView.setCommandAcknowledgement("=============================================\n");
		d_CpView.setCommandAcknowledgement("\n");
		StringBuffer mapNameString = new StringBuffer();
		for (int i = 0; i < mapStrings.length; i++) {
			mapNameString.append(mapStrings[i]+ ",");
		}
		d_CpView.setCommandAcknowledgement("\n");
		d_CpView.setCommandAcknowledgement("M:" + mapNameString);

		StringBuffer stratergiesNameString = new StringBuffer();
		for (int i = 0; i < p_PlayerStrategyList.length; i++) {
			stratergiesNameString.append(p_PlayerStrategyList[i] + ",");
		}
		d_CpView.setCommandAcknowledgement("P:" + stratergiesNameString);
		d_CpView.setCommandAcknowledgement("G:" + p_G);
		d_CpView.setCommandAcknowledgement("D:" + p_D);
		d_CpView.setCommandAcknowledgement("\n");
		d_CpView.setCommandAcknowledgement("\n");
		StringBuilder sb = new StringBuilder();
		sb.append("|");
		sb.append(getFormattedString(" "));
		for (int i = 0; i < p_G; i++) {
			sb.append("|");
			sb.append(getFormattedString("Game " + (i + 1)));
		}
		sb.append("|");
		d_CpView.setCommandAcknowledgement("\n");
		//d_CpView.setCommandAcknowledgement(getRepeatedFormattedString("-", sb.length()));
		d_CpView.setCommandAcknowledgement(sb.toString());
		//d_CpView.setCommandAcknowledgement(getRepeatedFormattedString("-", sb.length()));
		d_CpView.setCommandAcknowledgement("\n");
		for (int i = 0; i < mapStrings.length; i++) {

			StringBuilder sbMap = new StringBuilder();
			sbMap.append("|");
			sbMap.append(getFormattedString(mapStrings[i]));

			ArrayList<String> gameResults = p_tournamentResult.get(mapStrings[i]);
			for (int j = 0; j < p_G; j++) {
				sbMap.append("|");
				sbMap.append(getFormattedString(gameResults.get(j)));
			}
			d_CpView.setCommandAcknowledgement("\n");
			sbMap.append("|");
			d_CpView.setCommandAcknowledgement(sbMap.toString());
			//d_CpView.setCommandAcknowledgement(getRepeatedFormattedString("-", sb.length()));
		}
	}

	private String getRepeatedFormattedString(String input, int length) {
		StringBuilder str = new StringBuilder(input);
		for (int i = input.length(); i <= length - 1; i++)
			str.append(input);
		return str.toString();
	}

	private String getFormattedString(String input) {
		int length = 4;

		StringBuilder str = new StringBuilder(" " + input);
		for (int i = input.length(); i <= length; i++)
			str.append(" ");
		return str.toString();
	}
}