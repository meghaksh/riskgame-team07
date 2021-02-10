package org.soen6441.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * The Player class represents the actual player participating in the game.
 * @author Zeal
 *
 */
public class Player {
	String d_playerName="";
	int d_playerId;
	String d_playerColor = "";
    ArrayList<Country> d_countries = new ArrayList<Country>();
	Queue<Order> d_order = new LinkedList<Order>();
	Scanner d_scan;

	
	
	Player(String p_playerName,int p_playerId,String p_playerColor)
	{
		d_playerName = p_playerName;
		d_playerId = p_playerId;
		d_playerColor = p_playerColor;
	}
	
	public String getPlayerName()
	{
		return d_playerName;
	}
	
	public int getPlayerId()
	{
		return d_playerId;
	}
	
	public String getPlayerColor()
	{
		return d_playerColor;
	}
	public void setPlayerColor(String p_playerColor)
	{
		d_playerColor = p_playerColor;
	}
	public void issue_order()
	{
			
	}
	public Order nextOrder()
	{
		return d_order.remove();
	}
    
	
}
