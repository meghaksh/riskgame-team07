package org.soen6441.strategypattern;

import org.soen6441.model.GameModelNew;
import org.soen6441.model.Order;
import org.soen6441.model.Player;

public class AggresivePlayerStrategy extends Strategy {

	/*
	 * An aggressive computer player strategy that focuses on centralization of forces and then attack, 
	 * i.e. it deploys on its strongest country, then always attack with its strongest country, 
	 * then moves its armies in order to maximize aggregation of forces in one country.
	 */
	
	public AggresivePlayerStrategy(Player p_Player,GameModelNew p_GameModelNew) {
		
	}
	
	
	@Override
	public Order createOrder() {
		// TODO Auto-generated method stub
		return null;
	}

}
