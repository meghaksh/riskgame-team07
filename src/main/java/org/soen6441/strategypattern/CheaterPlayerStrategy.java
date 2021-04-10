package org.soen6441.strategypattern;

import org.soen6441.model.GameModelNew;
import org.soen6441.model.Order;
import org.soen6441.model.Player;

public class CheaterPlayerStrategy extends Strategy {

	/*
	 * A cheater computer player strategy whose issueOrder() method conquers all the immediate neighboring enemy countries, 
	 * and then doubles the number of armies on its countries that have enemy neighbors. 
	 * Note that in order to achieve this, 
	 * the cheater’s strategy implementation will still be called when the issueOrder() method, 
	 * but will not end up creating orders, 
	 * but rather implement the above-stated behavior by directly affecting the map during the order creation phase
	 * */
	public CheaterPlayerStrategy(Player p_Player,GameModelNew p_GameModelNew) {
		
	}
	@Override
	public Order createOrder() {
		// TODO Auto-generated method stub
		return null;
	}

}
