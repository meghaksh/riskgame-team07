package org.soen6441.strategypattern;

import java.util.HashMap;
import java.util.Random;

import org.soen6441.model.Country;
import org.soen6441.model.GameModelNew;
import org.soen6441.model.Order;
import org.soen6441.model.Player;
import org.soen6441.model.orders.Advance;
import org.soen6441.model.orders.Deploy;

public class CheaterPlayerStrategy extends Strategy {

	private Random rand;
	private GameModelNew d_GameModelNew;
	private Player d_Player;
	
	
	/*
	 * A cheater computer player strategy whose issueOrder() method conquers all the immediate neighboring enemy countries, 
	 * and then doubles the number of armies on its countries that have enemy neighbors. 
	 * Note that in order to achieve this, 
	 * the cheater’s strategy implementation will still be called when the issueOrder() method, 
	 * but will not end up creating orders, 
	 * but rather implement the above-stated behavior by directly affecting the map during the order creation phase
	 * */
	public CheaterPlayerStrategy(Player p_Player,GameModelNew p_GameModelNew) {
		this.d_GameModelNew = p_GameModelNew;
		d_Player = p_Player;
		rand = new Random();
		System.out.println("Cheater");
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
				return null;
	}

}
