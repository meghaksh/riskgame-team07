package org.soen6441.controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.soen6441.model.Order;
import org.soen6441.model.Player;
import org.soen6441.view.CommandPrompt;
/**
 * The Player Controller class controls the activities of all the players at once.
 * @author Zeal
 *
 */
public class PlayerController {

	
	ArrayList <Player> d_players;
	String d_orderString=null;
	String d_orderAcknowledgment="";
	private CommandPrompt d_CpView;
	
	PlayerController(ArrayList p_players)
	{
		d_players = p_players;
	}
	PlayerController(ArrayList p_players,CommandPrompt p_CpView)
	{
		d_players = p_players;
		d_CpView=p_CpView;
	}
	public void setOrderFromView(String p_orderString)
	{
		d_orderString = p_orderString;
	}
	public String getOrderAcknowledgment()
	{
		return d_orderAcknowledgment;
	}
	public void setOrderString(String p_OrderString)
	{
		this.d_orderString=p_OrderString;
	}
	/**
	 * The player_issue_order method asks each player to issue an order in a round robin fashion.
	 * The loop terminates when all the players are removed from the list.
	 * A player is removed from the list when all its armies are exhausted which is indicated from the result fetched from getResult method in Player class.
	 */
	public void player_issue_order()
	{
		System.out.println("in player issue order");
		ArrayList <Player> l_players = d_players;
		for(Player p:d_players)
		{
			System.out.println(p.getPlayerName());
		}
		
		while(!l_players.isEmpty())
		{
System.out.println("inside while loop");
			
			int l_flag =0;
			Player l_removePlayer = new Player();
			for(Player l_tempPlayer : l_players)
			{
			
				l_flag = 0;
				System.out.println("inside nested while loop");
				d_orderAcknowledgment = l_tempPlayer.getPlayerName()+" Enter deploy order";
				d_CpView.setCommandAcknowledgement(d_orderAcknowledgment);
				
				String l_stringOrder = JOptionPane.showInputDialog("Please Enter Your Deploy Order");

				
				System.out.println("after wait for command " + l_stringOrder);


				
				System.out.println(l_stringOrder);

				l_tempPlayer.setOrder(l_stringOrder);
				l_tempPlayer.issue_order();
				String l_result = l_tempPlayer.getResult();
				if(l_result.contains("armies"))
				{
					l_flag=1;
					d_orderAcknowledgment = l_result+"/n Please enter the next order accordingly";
					System.out.println(d_orderAcknowledgment);
					
					int l_armies = l_tempPlayer.getPlayerArmies();
					if(l_armies==0)
					{
						l_flag=1;l_removePlayer = l_tempPlayer;
					}
				}
				if(l_result.contains("country"))
				{
					System.out.println(l_result);
					d_orderAcknowledgment = l_result;
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