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
			int l_Flag = 0;
			String[] l_StringList = l_StringOrder.split(" ");
			String l_OrderType = l_StringList[0];
			
			System.out.println("in human player the command is not quit "+l_StringOrder);
			switch(l_OrderType) {

			case "deploy":
				if(l_StringList.length != 3)
				{
					System.out.println("Please enter valid number of parameters");
					break;
				}
				int l_NumArmies = Integer.parseInt(l_StringList[2]);
				for(Country l_TempCountry: d_GameModelNew.getSelectedMap().getCountryList() )
				{
					if(l_TempCountry.getCountryName().equals(l_StringList[1]))
					{
						return new Deploy(d_Player,l_TempCountry,l_NumArmies);
					}
				}
				break;
			case "advance" :
				if(l_StringList.length != 4)
				{
					System.out.println("Please enter valid number of parameters");
					break;
				}
				int l_NumArmies1 = Integer.parseInt(l_StringList[3]);
				Country l_SourceCountry = null, l_TargetCountry = null;
				for(Country l_TempCountry: d_GameModelNew.getSelectedMap().getCountryList() )
				{
					if(l_TempCountry.getCountryName().equals(l_StringList[1]))
					{
						l_SourceCountry = l_TempCountry;
						break;
					}
				}
				for(Country l_TempCountry: d_GameModelNew.getSelectedMap().getCountryList() )
				{
					if(l_TempCountry.getCountryName().equals(l_StringList[2]))
					{
						l_TargetCountry = l_TempCountry;
						break;
					}
				}
				return new Advance(d_Player,l_SourceCountry,l_TargetCountry,l_NumArmies1);
			case "bomb":
				if(l_StringList.length != 2)
				{
					System.out.println("Please enter valid number of parameters");
					break;
				}
				for(Country l_TempCountry: d_GameModelNew.getSelectedMap().getCountryList() )
				{
					if(l_TempCountry.getCountryName().equals(l_StringList[1]))
					{
						return new Bomb(d_Player,l_TempCountry);
					}
				}break;
			case "blockade":
				if(l_StringList.length != 2)
				{
					System.out.println("Please enter valid number of parameters");
					break;
				}
				for(Country l_TempCountry: d_GameModelNew.getSelectedMap().getCountryList() )
				{
					if(l_TempCountry.getCountryName().equals(l_StringList[1]))
					{
						return new Blockade(d_Player,l_TempCountry);

					}
				}
				break;
			case "airlift":
				if(l_StringList.length != 4)
				{
					System.out.println("Please enter valid number of parameters");
					break;
				}
				int l_NumArmies2 = Integer.parseInt(l_StringList[3]);
				Country l_SourceCountry1 = null, l_TargetCountry1 = null;

				for(Country l_TempCountry: d_GameModelNew.getSelectedMap().getCountryList() )
				{
					if(l_TempCountry.getCountryName().equals(l_StringList[1]))
					{
						l_SourceCountry1 = l_TempCountry;
						break;
					}
				}
				for(Country l_TempCountry: d_GameModelNew.getSelectedMap().getCountryList() )
				{
					if(l_TempCountry.getCountryName().equals(l_StringList[2]))
					{
						l_TargetCountry1 = l_TempCountry;
						break;
					}
				}
				return new Airlift(d_Player,l_SourceCountry1,l_TargetCountry1,l_NumArmies2);
			case "negotiate":
				if(l_StringList.length != 2)
				{
					System.out.println("Please enter valid number of parameters");
					break;
				}
				for(Player l_TempPlayer : d_GameModelNew.getAllPlayers()) {
					if(l_TempPlayer.getPlayerName().equals(l_StringList[1])) {
						return new Negotiate(d_Player, l_TempPlayer);
					}
				}

				break;

			default:
				break;

			}

			
		}
		return null;
		
	}

}
