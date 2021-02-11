package org.soen6441.model;

import java.util.ArrayList;

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
	 * This method used to get the game phases from enumuerator 
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



}