package org.soen6441.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * This is the  GameModelNew class of MVC model. 
 * This class has a references of  Models. 
 * This class acts as an Data info for other controllers.
 */
public class GameModelNew {
	private Map d_Map;
	private Player d_PlayerID;
	private ArrayList<Player> d_PlayerList;
	private int d_PlayerCount;
	private Queue<Player> d_PlayerQueue= new LinkedList<Player>();

	public GameModelNew() 
	{
		d_Map = new Map();
		d_PlayerList=new ArrayList<Player>();
	}

	public Map getMap() {
		return this.d_Map;
	}



	public Player getPlayerId1() 
	{
		return d_PlayerID;
	}
	/**
	 * This method returns the  player id.
	 * @return d_PlayerID of current  player.
	 */
	public void setPlayerId(Player d_PlayerID) 
	{

		this.d_PlayerID = d_PlayerID;
	}
	/**
	 * this method will get all the players from the ArrayList
	 * 
	 * @return d_PlayerList, ArrayList of all the available players from player class
	 */
	public ArrayList<Player> getAllPlayers() {
		return d_PlayerList;
	}
	/**
	 * this method sets the  player count
	 *
	 * @return the GameModel
	 */
	public void setplayerCount() {
		this.d_PlayerCount=this.d_PlayerList.size();

	}

	public Player getPlayerId() {
		return this.d_PlayerID;
	}


	/**
	 * This method gets selected map.
	 *
	 * @return the selected map
	 */
	public Map getSelectedMap() {

		return d_Map;

	}
	/**
	 * Adds the player.
	 *
	 * @param playerName the player name
	 * @return Player name  that has added
	 */
	public void addPlayer(String p_PlayerName)throws Exception {

		//		if ((d_PlayerList.size() >= getSelectedMap().getCountryList().size())) 
		//		{
		//			throw new Exception("Reached Max Number of Players can be added to the game");
		//		}
		if (existDuplicatePlayer(p_PlayerName)) {
			throw new Exception("Please enter a differnt Player name as this name already exists");

		} else {
			Player l_Player_Object = new Player(p_PlayerName);
			d_PlayerList.add(l_Player_Object);
			//return "Player " + p_PlayerName + " added to the game";
		}

	}

	/**
	 * This method checks for duplicate players
	 *
	 * @param playerName Name of the player
	 * @return true, if there is any duplicates
	 */
	public boolean existDuplicatePlayer(String p_PlayerName) {
		for (Player Player : d_PlayerList )
			if (Player .getPlayerName().equalsIgnoreCase(p_PlayerName))
				return true;

		return false;

	}
	/**
	 * This Method removes players 
	 *
	 * @param playerName the player name
	 * @return the string
	 */
	public void removePlayer(String p_PlayerName) throws Exception
	{
		Player l_CurrentPlayer;
		boolean l_PlayerFound = false;
		for (Player player:d_PlayerList) {
			l_CurrentPlayer = player;
			if (l_CurrentPlayer.getPlayerName().equalsIgnoreCase(p_PlayerName)) {
				l_PlayerFound = true;
				d_PlayerList.remove(d_PlayerList.indexOf(player));
				//return ("Player " + p_PlayerName + " This Player removed from the game");

			}
		}
		if (l_PlayerFound == false) {
			throw new Exception("\"This Player not found");

		}
		//return " ";

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
	 * @param count the assigned army count 
	 */
	public void setPlayerArmies(int p_count) {
		for (Player l_Player : getAllPlayers()) {
			l_Player.setPlayerArmies(p_count);
		}
	}

	/**
	 * This method Assign all the countries randomly to the players
	 * @throws Exception 
	 *
	 * 
	 */

	public void startUpPhase() throws Exception 
	{
		if(getAllPlayers().size()>0)
		{
			d_PlayerQueue.addAll(getAllPlayers());
			List<Country> l_CountryList = new ArrayList<>();

			l_CountryList  = getSelectedMap().getCountryList();		
			while (l_CountryList.size() > 0) 
			{	
				Random l_Random = new Random();
				int l_index = l_Random.nextInt(l_CountryList.size());
				setPlayerId(d_PlayerQueue.remove());
				getPlayerId1().addCountry(l_CountryList.get(l_index));
				System.out.println(getPlayerId1());
				d_PlayerQueue.add(d_PlayerID);
				l_CountryList.remove(l_index);
			}

			AssignReinforcementArmies();
		}
		else
		{
			throw new Exception ("\"Please enter players using gameplayer add command");

		}

	}

	/**
	 * This Method will assign armies to the players.
	 */
	public void AssignReinforcementArmies() throws Exception
	{	
		if(getAllPlayers().size()>0)
		{
			for (Player l_Player : getAllPlayers()) {
				int l_ArmyCount = ((l_Player.getCountriesSize())/3);
				for(Continent l_Continent:l_Player.getContinentList()) 
				{
					l_ArmyCount += l_Continent.getContinentControlValue();
				}
				l_ArmyCount += l_Player.getPlayerArmies();
				l_ArmyCount= Math.max(l_ArmyCount, 3);
				l_Player.setPlayerArmies(l_ArmyCount);
			}
		}
		else
		{
			throw new Exception ("\"Please enter players using gameplayer add command");

		}
	}


}
