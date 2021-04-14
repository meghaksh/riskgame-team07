package org.soen6441.strategypattern;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

import org.soen6441.model.Country;
import org.soen6441.model.GameModelNew;
import org.soen6441.model.Order;
import org.soen6441.model.Player;
import org.soen6441.model.orders.Advance;
import org.soen6441.model.orders.Deploy;

public class RandomPlayerStrategy extends Strategy implements Serializable {

	private Random rand;
	private GameModelNew d_GameModelNew;
	private Player d_Player;
	HashMap <Player,Boolean> d_CheckArmies = new HashMap<>();
	boolean d_decreasePlayerListSize;
	/*
	 * A random computer player strategy that deploys on a random country,
	 * attacks random neighboring countries, and moves armies randomly between its countries
	 * */
	public RandomPlayerStrategy(Player p_Player,GameModelNew p_GameModelNew)
	{
		this.d_GameModelNew = p_GameModelNew;
		d_Player = p_Player;
		rand = new Random();
		d_Leb.setResult("Random Player");
	}
	
	protected Country toAttack()
	{
		Country l_ReturnCountry=null;
		l_ReturnCountry = d_GameModelNew.getMap().getCountryList().get(rand.nextInt(d_GameModelNew.getMap().getCountryList().size()));
		d_Leb.setResult("The Random Player is attacking on "+l_ReturnCountry.getCountryName()+" country");
		return l_ReturnCountry;
	}
	protected Country toDefend()
	{
		Country l_ReturnCountry=null;
		l_ReturnCountry = d_Player.getCountryList().get(rand.nextInt(d_Player.getCountryList().size()));
		d_Leb.setResult("The Random Player is attacking from "+l_ReturnCountry.getCountryName()+" country");
		return l_ReturnCountry;
	}
	public boolean getDecreasePlayerListSize()
	{
		return d_decreasePlayerListSize;
	}
	public HashMap<Player,Boolean> getCheckArmies()
	{
		return d_CheckArmies;
	}
	public void setCheckArmies(HashMap<Player,Boolean> l_CheckArmies)
	{
		d_CheckArmies= l_CheckArmies;
	}
	
	@Override
	public Order createOrder() {
		// TODO Auto-generated method stub
		d_decreasePlayerListSize = false;
		int l_rndOrder = rand.nextInt(2);
		Order l_returnOrder = null;
		
			switch(l_rndOrder) 
			{
			case 0: l_returnOrder = new Deploy(d_Player,toDefend(),Math.max(rand.nextInt(d_Player.getPlayerArmies()),2));
					break;
			
			case 1: Country l_defendCountry = toDefend();
					if(l_defendCountry.getNoOfArmies()>1) {
					l_returnOrder =  new Advance(d_Player,l_defendCountry,toAttack(),rand.nextInt(l_defendCountry.getNoOfArmies()+5));}
					else
					{
						l_returnOrder = new Deploy(d_Player,l_defendCountry,Math.max(rand.nextInt(d_Player.getPlayerArmies()),2));
					}
					break;
					
			}
		d_Leb.setResult("in random player order is "+l_returnOrder);
		return l_returnOrder;
	}

	@Override
	public String strategyName() {
		// TODO Auto-generated method stub
		return "Random";
	}
}

