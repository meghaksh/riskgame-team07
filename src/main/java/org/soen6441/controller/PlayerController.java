package org.soen6441.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.soen6441.model.GameModelNew;
import org.soen6441.model.Order;
import org.soen6441.model.Player;
import org.soen6441.model.orders.Blockade;
import org.soen6441.model.orders.Deploy;
import org.soen6441.view.CommandPrompt;

/**
 * The Player Controller class controls the activities of all the players at once.
 */
public class PlayerController {
	private ArrayList <Player> d_Players;
	private String d_OrderAcknowledgment="";
	private CommandPrompt d_CpView;
	private GameModelNew d_GameModelNew;
	/**
	 * Constructor of Player controller
	 * @param p_Players list of players 
	 * @param p_CpView object of command prompt for communicating with player
	 */
	PlayerController(GameModelNew p_GameModelNew,CommandPrompt p_CpView) {
		d_GameModelNew = p_GameModelNew;
		d_Players = d_GameModelNew.getAllPlayers();
		d_CpView=p_CpView;
	}

	/**
	 * The player_issue_order method asks each player to issue an order in a round robin fashion.
	 * The loop terminates when the armies of all the players are exhausted.
	 * The acknowledgement are passed on to the view.
	 */
	public void playerIssueOrder() {
		ArrayList <Player> l_Players = d_Players;
		HashMap <Player,Boolean> l_CheckArmies = new HashMap<>();
		for(Player l_TempPlayer:l_Players) {
			l_CheckArmies.put(l_TempPlayer,false);
		}
		int l_PlayerListSize = l_Players.size();	
		while(l_PlayerListSize>0)	{
			Iterator<Player>l_It = l_Players.iterator();
			while(l_It.hasNext()) {
				Player l_Player = (Player)l_It.next(); 
				if(l_CheckArmies.get(l_Player)==false)
				{
					d_OrderAcknowledgment = "\n"+l_Player.getPlayerName()+" Enter order";
					d_CpView.setCommandAcknowledgement(d_OrderAcknowledgment);
					String l_StringOrder = JOptionPane.showInputDialog(l_Player.getPlayerName()+" : Please Enter Your Order");
					if(l_StringOrder.equalsIgnoreCase("quit"))
					{
						l_CheckArmies.put(l_Player, true);
						--l_PlayerListSize;
					}
					else
					{
						l_Player.setOrder(l_StringOrder);
						l_Player.issue_order();
						String l_Result = l_Player.getResult();
						d_OrderAcknowledgment=l_Result;
						d_CpView.setCommandAcknowledgement(d_OrderAcknowledgment);
					}
				}
			}
		}
	}
	/**
	 * <p>
	 * this Method will take inputs from the user and will add or remove player according
	 * to the inputs provided by the user
	 * 
	 * @param p_Command this is command entered by the player
	 * @param p_Str  this is name entered by the player in the command prompt
	 * @return l_ReturnString returns string acknowledgement based on the added or removed players
	 * @throws Exception this is user defined exception based on the add player or remove player method
	 */
	public String editPlayer(String p_Command,String p_Str) throws Exception {
		String[] l_CommandArray = p_Str.split(" ");
		int l_Counter = 1;
		int l_AddCounter = 0;
		int l_RemoveCounter = 0;
		String l_ReturnString = "";
		if(l_CommandArray.length < 3)
			throw new Exception("Please provide valid Parameters to add player");
		while(l_Counter<l_CommandArray.length) {
			if(l_CommandArray[l_Counter].equals("-add")) {
				d_GameModelNew.addPlayer(l_CommandArray[l_Counter+1]);
				l_Counter+=2;
				l_AddCounter+=1;
			} else if(l_CommandArray[l_Counter].equals("-remove")){
					d_GameModelNew.removePlayer(l_CommandArray[l_Counter+1]);
				
				l_Counter+=2;
				l_RemoveCounter+=1;
			} else {
				break;
			}
		}
		if(l_AddCounter>0) {
			l_ReturnString += "Number of Players Added : " + l_AddCounter + "\n";
		}
		if(l_RemoveCounter>0) {
			l_ReturnString += "Number of Players Removed : " + l_RemoveCounter + "\n";
		}
		return l_ReturnString;
	}
	/**
	 * This method iterates till the player list doesn't becomes empty. This means all the orders of all the players are executed.
	 * It works in a round robin fashion. All the players execute there orders one by one.
	 * The player who's all orders are executed is removed from the list.
	 */
	public void playerNextOrder() {
		ArrayList <Player> l_Players = d_Players;
		ArrayList <Player> l_PlayersClone = (ArrayList<Player>) d_Players.clone();
		while(!l_PlayersClone.isEmpty()) {
			Iterator<Player>l_It = l_Players.iterator();
			int l_Flag =0;
			ArrayList<Player> l_RemovePlayerList = new ArrayList<Player>();
			while(l_It.hasNext()) {
				Player l_Player = (Player)l_It.next(); 
				if(l_Player.getOrderSize()!=0) {
					Order l_Order = l_Player.next_order();
					if(l_Order.getClass().getName().equals("org.soen6441.model.orders.Deploy")) {
						Deploy l_DeployOrder = (Deploy) l_Order;
						l_DeployOrder.execute();
					}else if(l_Order.getClass().getName().equals("org.soen6441.model.orders.Blockade")) {
						Blockade l_DeployOrder = (Blockade) l_Order;
						l_DeployOrder.execute();
					}
					
					//System.out.println(l_Order.getOrder());
					
					//String l_Result = l_Order.getExecuteResult();
					//d_OrderAcknowledgment = l_Result;
					//d_CpView.setCommandAcknowledgement(d_OrderAcknowledgment);
				} else {
					l_Flag = 1; l_RemovePlayerList.add(l_Player);
				}
			}
			if(l_Flag == 1) {
				for(Player l_TempRemovePlayer : l_RemovePlayerList) {
					l_PlayersClone.remove(l_TempRemovePlayer);
				}
			}
		}
		d_CpView.setCommandAcknowledgement("\nOrders are Succesfully Executed!!");
	}
}