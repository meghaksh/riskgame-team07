package org.soen6441.controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.soen6441.model.GameModelNew;
import org.soen6441.model.Order;
import org.soen6441.model.Player;
import org.soen6441.view.CommandPrompt;

/**
 * The Player Controller class controls the activities of all the players at once.
 * @author Zeal
 *
 */
public class PlayerController {
	private ArrayList <Player> d_Players;
	private String d_OrderString=null;
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
		System.out.println("Player controlle gamemodel new"+d_GameModelNew);
		d_Players = d_GameModelNew.getAllPlayers();
		d_CpView=p_CpView;
	}
	/**
	 * Set method to set the Order from GameController written in Command Prompt.
	 * @param p_OrderString Order written by the player
	 */
	public void setOrderString(String p_OrderString) {
		this.d_OrderString=p_OrderString;
	}
	/**
	 * The player_issue_order method asks each player to issue an order in a round robin fashion.
	 * The loop terminates when all the players are removed from the list.
	 * A player is removed from the list when all its armies are exhausted which is indicated from the result fetched from getResult method in Player class.
	 */
	public void player_issue_order() {
		System.out.println("in player issue order");
		ArrayList <Player> l_Players = d_Players;
		ArrayList <Player> l_PlayersClone = (ArrayList<Player>) d_Players.clone();
		int l_PlayerListSize = l_Players.size();
		System.out.println(l_PlayerListSize);
		for(Player p:d_Players) {
			System.out.println(p.getPlayerName());
		}

	
		int l_Flag =0;
		ArrayList<Player> l_RemovePlayerList = new ArrayList<Player>();
		while(l_PlayerListSize>0)	{
			for(Player l_TempPlayer : l_Players) {

				if(l_TempPlayer.getPlayerArmies()>0)
				{

					d_OrderAcknowledgment = "\n"+l_TempPlayer.getPlayerName()+" Enter deploy order";
					d_CpView.setCommandAcknowledgement(d_OrderAcknowledgment);

					String l_StringOrder = JOptionPane.showInputDialog(l_TempPlayer.getPlayerName()+" : Please Enter Your Deploy Order");
					l_TempPlayer.setOrder(l_StringOrder);
					l_TempPlayer.issue_order();
					String l_Result = l_TempPlayer.getResult();
					int l_ResultInteger = l_TempPlayer.getResultInteger();

					if(l_ResultInteger==2) {
						d_OrderAcknowledgment=l_Result;
						d_CpView.setCommandAcknowledgement(d_OrderAcknowledgment);
						l_Flag=1;l_RemovePlayerList.add(l_TempPlayer);
						System.out.println(l_TempPlayer.getPlayerName()+"'s armies have become zero");
					} else if(l_ResultInteger==3) {
						System.out.println(l_Result);
						d_OrderAcknowledgment = l_Result;
						d_CpView.setCommandAcknowledgement(d_OrderAcknowledgment);
						System.out.println("\nThis country does not belong to "+l_TempPlayer.getPlayerName());
					} else if(l_ResultInteger==4) {
						d_OrderAcknowledgment = l_Result+"\n Please enter the next order accordingly";
						d_CpView.setCommandAcknowledgement(d_OrderAcknowledgment);
						System.out.println(d_OrderAcknowledgment);
					} else if(l_ResultInteger==1){
						d_OrderAcknowledgment = l_Result;
						d_CpView.setCommandAcknowledgement(d_OrderAcknowledgment);
						System.out.println("\norder successfully added to "+l_TempPlayer.getPlayerName()+"'s order list");
					}
				}
				else
				{
					--l_PlayerListSize;
				}

			}
		}
		/*	if(l_Flag==1) {
				for(Player l_TempRemovePlayer : l_RemovePlayerList) {
					
					l_PlayersClone.remove(l_TempRemovePlayer);
				}
			}*/
		

	}
	/**
	 * This method iterates till the player list doesn't becomes empty. This means all the orders of all the players are executed.
	 * It works in a round robin fashion. All the players execute there orders one by one.
	 * The player who's all orders are executed is removed from the list.
	 */
	public void player_next_order() {
		ArrayList <Player> l_Players = d_Players;
		ArrayList <Player> l_PlayersClone = (ArrayList<Player>) d_Players.clone();
		while(!l_PlayersClone.isEmpty()) {
			Iterator l_It = l_Players.iterator();
			int l_Flag =0;
			ArrayList<Player> l_RemovePlayerList = new ArrayList<Player>();
			while(l_It.hasNext()) {
				Player l_Player = (Player)l_It.next(); 
				if(l_Player.getOrderSize()!=0) {
					Order l_Order = l_Player.next_order();
					System.out.println(l_Order.getOrder());
					l_Order.execute();
					String l_Result = l_Order.getExecuteResult();
					d_OrderAcknowledgment = l_Result;
					d_CpView.setCommandAcknowledgement(d_OrderAcknowledgment);
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
	}
}