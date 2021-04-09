package org.soen6441.strategypattern;

import java.util.HashMap;

import javax.swing.JOptionPane;

import org.soen6441.model.Country;
import org.soen6441.model.GameModelNew;
import org.soen6441.model.Order;
import org.soen6441.model.Player;
import org.soen6441.model.orders.Advance;
import org.soen6441.model.orders.Airlift;
import org.soen6441.model.orders.Blockade;
import org.soen6441.model.orders.Bomb;
import org.soen6441.model.orders.Deploy;
import org.soen6441.model.orders.Negotiate;

public class HumanPlayerStrategy extends Strategy {
	
	private GameModelNew d_GameModelNew;
	Player d_Player;
	HashMap <Player,Boolean> d_CheckArmies = new HashMap<>();
	int d_PlayerListSize;
	boolean d_decreasePlayerListSize;
	
	public HumanPlayerStrategy(Player p_Player,GameModelNew p_GameModelNew)
	{
		this.d_GameModelNew = p_GameModelNew;
		d_Player = p_Player;
		System.out.println("Human");
	}

	public void setCheckArmies(HashMap<Player,Boolean> l_CheckArmies)
	{
		d_CheckArmies= l_CheckArmies;
	}
	public void setPlayerListSize(int l_PlayerListSize)
	{
		d_PlayerListSize=l_PlayerListSize;
	}
	public HashMap<Player,Boolean> getCheckArmies()
	{
		return d_CheckArmies;
	}
	public int getPlayerListSize()
	{
		return d_PlayerListSize;
	}
	public boolean getDecreasePlayerListSize()
	{
		return d_decreasePlayerListSize;
	}
	@Override
	public Order createOrder() {
		// TODO Auto-generated method stub
		String l_StringOrder = JOptionPane.showInputDialog(d_Player.getPlayerName()+" : Please Enter Your Order");
		d_decreasePlayerListSize = false;
		System.out.println("in human player createorder");
		if(l_StringOrder.equalsIgnoreCase("quit"))
		{
			System.out.println("in human player the command is quit");
			d_CheckArmies.put(d_Player, true);
			d_decreasePlayerListSize = true;
			//--d_PlayerListSize;
		}
		else
		{
		
		}
		return null;
		
	}

}
