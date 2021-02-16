package org.soen6441.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.soen6441.model.Order;
import org.soen6441.model.Player;
/**
 * The Player Controller class controls the activities of all the players at once.
 * @author Zeal
 *
 */
public class PlayerController {

	Scanner d_scan;
	ArrayList <Player> d_players;
	String d_orderString="";
	
	PlayerController(ArrayList p_players)
	{
		d_scan =new Scanner(System.in);
		d_players = p_players;
	}
	public void setOrderFromView(String p_orderString)
	{
		d_orderString = p_orderString;
	}
	/**
	 * The player_issue_order method asks each player to issue an order in a round robin fashion.
	 * The loop terminates when all the players are removed from the list.
	 * A player is removed from the list when all its armies are exhausted which is indicated from the result fetched from getResult method in Player class.
	 */
	public void player_issue_order()
	{
		ArrayList <Player> l_players = d_players;
		
		while(!l_players.isEmpty())
		{
			Iterator it = l_players.iterator();
			int l_flag =0;
			Player l_removePlayer = new Player();
			while(it.hasNext())
			{
				l_flag = 0;
				String l_stringOrder = d_scan.nextLine();
				//String l_stringOrder = getOrderString();
				Player l_tempPlayer = (Player)it.next();
				l_tempPlayer.setOrder(l_stringOrder);
				l_tempPlayer.issue_order();
				String l_result = l_tempPlayer.getResult();
				if(l_result.contains("armies"))
				{
					l_flag=1;System.out.println(l_result+"/n Please enter the next order accordingly");
					int l_armies = l_tempPlayer.getPlayerArmies();
					if(l_armies==0)
					{
						l_flag=1;l_removePlayer = l_tempPlayer;
					}
				}
				if(l_result.contains("country"))
				{
					System.out.println(l_result);
				}
			}
			if(l_flag==1)
			{
				l_players.remove(l_removePlayer);
			}
		}
		
	}
	public void player_next_order()
	{
		ArrayList <Player> l_players = d_players;
		while(!l_players.isEmpty())
		{
			Iterator it = l_players.iterator();
			int l_flag =0;
			Player l_removePlayer = new Player();
			while(it.hasNext())
			{
				Player l_player = (Player)it.next(); 
				if(l_player.getOrderSize()!=0)
				{
				Order order = l_player.next_order();
				order.execute();
				}
				else
				{
					l_flag = 1; l_removePlayer = l_player;
				}
			}
			if(l_flag == 1)
			{
				l_players.remove(l_removePlayer);
			}
		}
		
		
	}
	
}