package org.soen6441.model;

import java.util.*;

public class GameModel 
{	/**
 	*Enumerator for all the game phases
 	*/
	public enum Phases
	{
		Startup, Reinforcement,IssueOrder,ExecuteOrder
	}
	private Phases d_gamePhase;
	private Map d_map;
	private Player d_PlayerID;
	private ArrayList<Player> d_PlayerList = new ArrayList<Player>();
	private int d_PlayerCount;
	private Queue<Player> d_PlayerQueue;
	private static final int MAX_PLAYERS = 6;
	
	/**
	 * This is a constructor of GameModel class which will initialize the Map and set the gamePhase 
	 * @param intializing map parameter ,
	 */
	public GameModel(Map d_map) 
	{
		super();
		this.d_map = d_map;
		this.setGamePhase(Phases.Startup);
	}
	/**
	 * This method used to get the game phases from enumerator 
	 * 
	 * @return d_gamePhase
	 */
	public Phases getGamePhase() {
		return d_gamePhase;
	}
	/**
	 * This method used to set phase of the game 
	 * @param gamePhase we have different phases like startup and reinforcement,
	 */
	public void setGamePhase(Phases d_gamePhase) {
		this.d_gamePhase = d_gamePhase;
	}
	/**
	 * This method returns the  player id.
	 * @return d_PlayerID of current  player.
	 */
	public Player getPlayerId() 
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
	/**
	 * This method gets selected map.
	 *
	 * @return the selected map
	 */
	public Map getSelectedMap() {
		return d_map;

	}
	/**
	 * Adds the player.
	 *
	 * @param playerName the player name
	 * @return Player name  that has added
	 */
	public String addPlayer(String p_PlayerName) {
		if ((d_PlayerList.size() == d_PlayerCount && d_PlayerCount != 0) || d_PlayerList.size() == MAX_PLAYERS) 
		{
			return "Reached Max Number of Players can be added to the game";
		}
		if (existDuplicatePlayer(p_PlayerName)) {

			return "Please enter a differnt Player name as this name already exists";
		} else {
			//Player l_Player_Object = new Player(d_PlayerList.size() + 1, p_PlayerName);
			//d_PlayerList.add(l_Player_Object);
			return "Player " + p_PlayerName + " added to the game";
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
	public String removePlayer(String p_PlayerName) 
	{
		Player l_CurrentPlayer;
		boolean l_PlayerFound = false;
		for (Player player:d_PlayerList) {
			l_CurrentPlayer = player;
			if (l_CurrentPlayer.getPlayerName().equalsIgnoreCase(p_PlayerName)) {
				l_PlayerFound = true;
				//for(Country Country:getSelectedMap().getOwnedCountries(p_PlayerName))(mapclass)
					//Country.setOwnedBy(null);(country)
				d_PlayerList.remove(d_PlayerList.indexOf(player));
				return ("Player " + p_PlayerName + " This Player removed from the game");

			}
		}
		if (l_PlayerFound == false) {
			return ("This Player not found");
		}
		return " ";

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
	 *
	 * 
	 */
	
	public void startUpPhase() 
	{

		d_PlayerQueue.clear();
		d_PlayerQueue.addAll(getAllPlayers());
		List<Country> l_CountryList = new ArrayList<>();

		l_CountryList  = getSelectedMap().getCountryList();
		while (l_CountryList.size() > 0) 
		{
			Random l_Random = new Random();
			int l_index = l_Random.nextInt(l_CountryList.size());
			setPlayerId(d_PlayerQueue.remove());
			//d_PlayerID.setCountriesOwned(l_CountryList.get(l_index));
			//getSelectedMap().searchCountry(l_CountryList.get(l_index)).setOwnedBy(d_PlayerID);
			d_PlayerQueue.add(d_PlayerID);
			l_CountryList.remove(l_index);
		}
		
	
		



	}
	



}