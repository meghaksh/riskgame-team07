package org.soen6441.model;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import org.soen6441.strategypattern.AggresivePlayerStrategy;
import org.soen6441.strategypattern.BenevolentPlayerStrategy;
import org.soen6441.strategypattern.CheaterPlayerStrategy;
import org.soen6441.strategypattern.HumanPlayerStrategy;
import org.soen6441.strategypattern.RandomPlayerStrategy;

/**
 * This is the  GameModelNew class of MVC model. 
 * This class has a references of  Models. 
 * This class acts as an Data info for other controllers.
 */
public class GameModelNew implements Serializable {
	private Map d_Map;
	private Player d_PlayerID;
	private ArrayList<Player> d_PlayerList;
	private Queue<Player> d_PlayerQueue= new LinkedList<Player>();
	private final int D_MINARMIES=3;

	/**
	 * Default constructor which initializes map and player List 
	 */
	public GameModelNew() {
		this.d_Map = new Map();
		this.d_PlayerList=new ArrayList<Player>();
	}
	/**
	 * This is parameterized constructor which takes Map parameter 
	 * @param p_Map parameter of map
	 */
	public GameModelNew(Map p_Map) {
		this.d_Map = p_Map;
		this.d_PlayerList=new ArrayList<Player>();
	}

	/**
	 * get Method for map
	 * @return returns map
	 */
	public Map getMap() {
		return this.d_Map;
	}

	public void setMap(Map p_Map) {
		this.d_Map = p_Map;
	}
	/**
	 * get Method for PlayerID 
	 * @return returns PlayerID
	 */
	public Player getPlayerId1() {
		return this.d_PlayerID;
	}

	/**
	 * set method for player id 
	 * @param d_PlayerID player id of player
	 */
	public void setPlayerId(Player d_PlayerID) {
		this.d_PlayerID = d_PlayerID;
	}

	/**
	 * this method will get all the players from the ArrayList
	 * 
	 * @return d_PlayerList, ArrayList of all the available players from player class
	 */
	public ArrayList<Player> getAllPlayers() {
		return this.d_PlayerList;
	}
	/**
	 * This method gets selected map.
	 *
	 * @return the selected map
	 */
	public Map getSelectedMap() {
		return this.d_Map;
	}

	/**
	 * This Method Adds the player based on user input from the command prompt.
	 * @param p_PlayerName player name of player
	 * @throws Exception if player size is more that country size or if player already exists
	 */
	public void addPlayer(String p_PlayerName,String strategy)throws Exception {
		if ((d_PlayerList.size() > getSelectedMap().getCountryList().size())) {
			throw new Exception("Reached Max Number of Players can be added to the game");
		}
		if (existDuplicatePlayer(p_PlayerName)) {
			throw new Exception("Please enter a differnt Player name as this name already exists");
		} else {
			Player l_PlayerObject = new Player(p_PlayerName, this);
			switch(strategy) {
			case "aggressive" :
				l_PlayerObject.setPlayerStrategy(new AggresivePlayerStrategy(l_PlayerObject,this));
				break;

			case "human" :
				l_PlayerObject.setPlayerStrategy(new HumanPlayerStrategy(l_PlayerObject,this));
				break;

			case "benevolent" :				
				l_PlayerObject.setPlayerStrategy(new BenevolentPlayerStrategy(l_PlayerObject,this));
				break;

			case "random": 
				l_PlayerObject.setPlayerStrategy(new RandomPlayerStrategy(l_PlayerObject,this));
				break;
			case "cheater": 
				
				l_PlayerObject.setPlayerStrategy(new CheaterPlayerStrategy(l_PlayerObject,this));
				
				break;
				
			default:
				System.out.println("Invalid Command. Please try again.\n");
				break;
			}
			System.out.println("strat"+l_PlayerObject.getPlayerStrategy());
			d_PlayerList.add(l_PlayerObject);
			
		
		}
	}

	/**
	 * This method checks for duplicate players
	 *
	 * @param p_PlayerName Name of the player
	 * @return boolean this returns boolean value based if player already exists or not
	 */
	public boolean existDuplicatePlayer(String p_PlayerName) {
		for (Player l_Player : d_PlayerList )
			if (l_Player.getPlayerName().equalsIgnoreCase(p_PlayerName))
				return true;
		return false;
	}
	/**
	 * This Method removes players 
	 * @param p_PlayerName Name of the player
	 * @throws Exception if player is not found
	 */
	public void removePlayer(String p_PlayerName) throws Exception {
		Iterator<Player> l_Iterator = this.d_PlayerList.iterator();
		boolean l_PlayerFound = false;
		while(l_Iterator.hasNext()) {
			Player l_TempPlayer = l_Iterator.next();
			if(l_TempPlayer.getPlayerName().equalsIgnoreCase(p_PlayerName)) {
				l_PlayerFound = true;
				l_Iterator.remove();

			}
		}
		if (l_PlayerFound == false) {
			throw new Exception("This Player does not exists");
		}
	}
	/**
	 * This method sets the player queue
	 *
	 * @param d_PlayerQueue the Player queue
	 *
	 */
	public  void setplayerQueue(Queue<Player> d_PlayerQueue) {
		this.d_PlayerQueue=d_PlayerQueue;


	}
	/**
	 * This method sets army count to all players.
	 *
	 * @param p_Count count the assigned army count 
	 */
	public void setPlayerArmies(int p_Count) {
		for (Player l_Player : getAllPlayers()) {
			l_Player.setPlayerArmies(p_Count);
		}
	}

	/**
	 * <p>
	 * This Method is the startup Phase method
	 * </p>
	 * <ul>
	 * <li>This Method will take all the players in a queue
	 * <li>Then it will check for all the countries available in a map 
	 * <li>It will run the loop until all the countries are assigned to players
	 * <li>Now it will Randomly assign countries to all players and remove the assigned countries from the list
	 * <li>Finally once the assigning countries to players is completed it will call AssignReinforcementArmies Method
	 * </ul> 
	 * @throws Exception if there are no players in the list
	 *
	 */
	public void startUpPhase() throws Exception {
		if(getAllPlayers().size()>1) {
			d_PlayerQueue.addAll(getAllPlayers());

			List<Country> l_CountryList = new ArrayList<>();
			l_CountryList  = (List<Country>) getSelectedMap().getCountryList().clone();		
			while (l_CountryList.size() > 0) {	
				Random l_Random = new Random();
				int l_Index = l_Random.nextInt(l_CountryList.size());
				setPlayerId(d_PlayerQueue.remove());
				System.out.println("playerid"+getPlayerId1());
				getPlayerId1().addCountry(l_CountryList.get(l_Index));
				l_CountryList.get(l_Index).setCountryOwnerPlayer(getPlayerId1());
				d_PlayerQueue.add(d_PlayerID);
				l_CountryList.remove(l_Index);
			}
			for(Player l_player:getAllPlayers())
			{
				for(Country L_country:l_player.getCountryList())
				{
				System.out.println("player"+l_player+"country"+L_country.getCountryName());
				}
			}

		} else {
			if(getAllPlayers().size()==0) {
				throw new Exception ("Please enter players using gameplayer add command");
			} else {
				throw new Exception ("One Player Found. Please enter more players using gameplayer add command");
			}
		}
		this.d_PlayerList.add(new Player("Neutral Player", this));
	}

	/**
	 * <p>
	 * This Method will assign armies to the players.
	 * <ul>
	 * <li>This Method will check if they players size is greater than 0
	 * <li>If it is greater than 0 it will check for no of countries assigned for player /3 and assign armies count
	 * <li>If a PLayer holds complete continent Then we will add its continent value to the player army count
	 * <li>Now here we have used Max to assign Minimum armies to players of the army count is less than 3 else it gives max armies
	 * <li>Once the respective armies has been assigned to that particular player it will set armies to the player based on armycount
	 * </ul>
	 * @throws Exception this Method will throw an Exception if player size is less than 0
	 * 
	 */
	public void assignReinforcementArmies() throws Exception {	
		int l_ContinentValue=0;
		for (Player l_Player : getAllPlayers()) {
			l_Player.setContinentsList();
		}
	
		if(getAllPlayers().size()>0) {
			for (Player l_Player : getAllPlayers()) {
				if(!l_Player.getPlayerName().equals("Neutral Player")) { 
					int l_ArmyCount = ((l_Player.getCountriesSize())/3);
					for(Continent l_Continent:l_Player.getContinentList()) {
						l_ContinentValue =  l_Continent.getContinentControlValue();
					}
					l_ArmyCount= Math.max(l_ArmyCount, D_MINARMIES);
					l_Player.setPlayerArmies(l_ArmyCount+ l_ContinentValue+l_Player.getPlayerArmies());
					l_ContinentValue=0;
					
				}
			}
		} else {
			throw new Exception ("\"Please enter players using gameplayer add command");
		}
	}
	public  void saveGame(String p_FileName) {
		String l_File = p_FileName;
		System.out.println(p_FileName);
		try {
			FileOutputStream fileOut = new FileOutputStream("savedgames\\" + l_File);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
		
	}
	
	public static GameModelNew loadGame(String p_FileName) {
		GameModelNew game = null;
		try {
			System.out.println("gameTitle:" + p_FileName);
			FileInputStream fileIn = new FileInputStream("savedgames\\" + p_FileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			game = (GameModelNew) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			return null;
		} catch (ClassNotFoundException c) {
			return null;
		}
		return game;
	}
	public void tournamentstartUpPhase() throws Exception {
		List<Country> l_CountryList = (List<Country>) this.getSelectedMap().getCountryList().clone();	
		int playerIndex = 0, playerCount = this.getAllPlayers().size();
		ArrayList<Integer> tempList = new ArrayList<>();
		ArrayList<Player> l_playerList= this.getAllPlayers();
		// Clearing the country list pf players
		System.out.println("In startup tournament:");
		for(Player l_Player : this.getAllPlayers()) {
			System.out.println("Player: "+l_Player+ " name:"+l_Player.getPlayerName());
			for(Country l_Country: l_Player.getCountryList()) {
				System.out.println("Country: "+ l_Country.getCountryName());
			}
		}
		
		
		// Here creating the list with indexes
		for (int i = 0; i < l_CountryList.size(); i++) {
			tempList.add(i);
		}
		// Shuffling the list for randomness
		Collections.shuffle(tempList, new Random());

		// assigning the shuffled countries from tempList to the players one by
		// one
		for (int i = 0; i < l_CountryList.size(); i++) {
			if (playerIndex == playerCount)
				playerIndex = 0;
			Country newCountry = l_CountryList.get(tempList.get(i));
			l_playerList.get(playerIndex).addCountry(newCountry);
			newCountry.setCountryOwnerPlayer(l_playerList.get(playerIndex));
			l_CountryList.remove(tempList.get(i));
			System.out.println("Temp get i"+tempList.get(i)+" Country: "+ l_CountryList.get(tempList.get(i)).getCountryName());
			playerIndex++;
		}
		for(Player l_player:getAllPlayers()) {
			for(Country L_country:l_player.getCountryList())
			{
			System.out.println("player"+l_player+"country are"+L_country.getCountryName());
			}
		}
	}
}