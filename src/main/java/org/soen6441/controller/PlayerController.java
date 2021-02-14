package org.soen6441.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.soen6441.model.Player;
/**
 * The Player Controller class controls the activities of all the players at once.
 * @author Zeal
 *
 */
public class PlayerController {

	Scanner d_scan;
	ArrayList <Player> d_players;
	
	PlayerController(ArrayList p_players)
	{
		d_scan =new Scanner(System.in);
		d_players = p_players;
	}
	/**
	 * The player_issue_order method asks each player to issue an order in a round robin fashion.
	 * The loop terminates when all the players are removed from the list.
	 * A player is removed from the list when all its armies are exhausted which is indicated from the result fetched from getResult method in Player class.
	 */
	public void player_issue_order()
	{
		while(!d_players.isEmpty())
		{
			Iterator it = d_players.iterator();
			int l_flag =0;
			Player l_removePlayer = new Player();
			while(it.hasNext())
			{
				l_flag = 0;
				String l_stringOrder = d_scan.nextLine();
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
				d_players.remove(l_removePlayer);
			}
		}
		
	}
	
}