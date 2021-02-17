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
	private ArrayList<Player> d_PlayerList;
	private int d_PlayerCount;
	 Queue<Player> d_PlayerQueue= new LinkedList<Player>();
	private static final int MAX_PLAYERS = 6;
	ArrayList<Country> d_CountryObjects; 
	ArrayList<Continent> d_ContinentObjects;
	

	
	/**
	 * This is a constructor of GameModel class which will initialize the Map and set the gamePhase 
	 * @param intializing map parameter ,
	 */
	public GameModel(Map p_map) 
	{
		super();
		d_map=p_map;
		d_PlayerList=new ArrayList<Player>();
		
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
	public void addPlayer(String p_PlayerName)throws Exception {
//		if ((d_PlayerList.size() == d_PlayerCount && d_PlayerCount != 0) || d_PlayerList.size() == MAX_PLAYERS) 
//		{
//			return "Reached Max Number of Players can be added to the game";
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
	 *
	 * 
	 */
	
	public void startUpPhase() 
	{

		
		d_PlayerQueue.addAll(getAllPlayers());
		List<Country> l_CountryList = new ArrayList<>();

		l_CountryList  = getSelectedMap().getCountryList();		
		while (l_CountryList.size() > 0) 
		{	System.out.println("we are in main");
			Random l_Random = new Random();
			int l_index = l_Random.nextInt(l_CountryList.size());
			setPlayerId(d_PlayerQueue.remove());
			d_PlayerID.addCountry(l_CountryList.get(l_index));
			//getSelectedMap().searchCountry(l_CountryList.get(l_index)).setOwnedBy(d_PlayerID);
			//getSelectedMap().getCountryList().setOwnedBy(d_PlayerID);
			d_PlayerQueue.add(d_PlayerID);
			l_CountryList.remove(l_index);
		}
		
		//AssignReinforcementArmies();
		



	}
	
	/**
	 * This Method will assign armies to the players.
	 */
	public void AssignReinforcementArmies()
	{
//		int l_ArmyCount = ((d_PlayerID.getCountriesOwned().size())/3);
//		//for(Continent l_Continent:getSelectedMap().getOwnedContinents(d_PlayerID.getPlayerName())) 
//		{
//			//l_ArmyCount += l_Continent.getControlValue();
//		}
//		l_ArmyCount += d_PlayerID.getPlayerArmies();
//		l_ArmyCount= Math.max(l_ArmyCount, 3);
//		d_PlayerID.setPlayerArmies(l_ArmyCount);
		
	}
	



}