package org.soen6441.strategypattern;

import java.util.Random;

import org.soen6441.model.Country;
import org.soen6441.model.GameModelNew;
import org.soen6441.model.Order;
import org.soen6441.model.Player;
import org.soen6441.model.orders.Advance;
import org.soen6441.model.orders.Deploy;

public class RandomPlayerStrategy extends Strategy {

	private Random rand;
	private GameModelNew d_GameModelNew;
	private Player d_Player;
	/*
	 * A random computer player strategy that deploys on a random country,
	 * attacks random neighboring countries, and moves armies randomly between its countries
	 * */
	public RandomPlayerStrategy(Player p_Player,GameModelNew p_GameModelNew)
	{
		this.d_GameModelNew = p_GameModelNew;
		d_Player = p_Player;
		rand = new Random();
		System.out.println("Random");
	}
	
	protected Country toAttack()
	{
		return d_GameModelNew.getMap().getCountryList().get(rand.nextInt(d_GameModelNew.getMap().getCountryList().size()-1));
	}
	protected Country toDefend()
	{
		return d_Player.getCountryList().get(rand.nextInt(d_Player.getCountryList().size()-1));
	}
	
	
	@Override
	public Order createOrder() {
		// TODO Auto-generated method stub
		
		System.out.println("in random player create order");
		int l_rndOrder = rand.nextInt(3);
		int l_randomNoOfArmies;
		if(rand.nextInt(5)!=0)
		{
			switch(l_rndOrder) 
			{
			case 0: return new Deploy(d_Player,toDefend(),rand.nextInt(10));
			
			case 1: Country l_defendCountry = toDefend();
					return new Advance(d_Player,l_defendCountry,toAttack(),rand.nextInt(l_defendCountry.getNoOfArmies()+5));
			}
		}
		
		return null;
	}
}
