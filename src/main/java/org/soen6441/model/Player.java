package org.soen6441.model;

import java.util.ArrayList;
import java.util.Iterator;
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
	int d_armies;
    ArrayList<Country> d_countries = new ArrayList<Country>();
	Queue<Order> d_order = new LinkedList<Order>();
	Scanner d_scan;
	String d_result="";
	String d_stringOrder="";

	public Player()
	{
		
	}
	
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
	public void setPlayerArmies(int p_armies)
	{
		d_armies = p_armies;
	}
	public int getPlayerArmies()
	{
		return d_armies;
	}
	public String getResult()
	{
		return d_result;
	}
	public void setOrder(String p_order)
	{
		d_stringOrder = p_order;
	}
	public void issue_order()
	{
		int l_flag = 0;
		//d_scan = new Scanner(System.in);
		//String l_stringOrder = d_scan.nextLine();
		String[] l_stringList = d_stringOrder.split(" ");
		if(Integer.parseInt(l_stringList[2]) <= d_armies)
		{
			Iterator l_it = d_countries.iterator();
			while(l_it.hasNext())
			{
				Country l_tempCountry = (Country)l_it.next() ;
				if(Integer.parseInt(l_stringList[1])==l_tempCountry.d_ID)
				{
					l_flag=1;
					break;
				}
			}
			if(l_flag==1)
			{
				d_armies-= Integer.parseInt(l_stringList[2]);
				d_order.add(new Order(d_stringOrder));
				d_result = "order added to list of "+d_playerName;
			}
			else
			{
				d_result = "This country "+l_stringList[1]+" doesnot belongs to "+d_playerName;
			}
			
		}
		else
		{
			d_result = d_playerName+" has "+d_armies+" number of armies";
		}
		
		
	}
	public Order nextOrder()
	{
		return d_order.remove();
	}
    
	
}
