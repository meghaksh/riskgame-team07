package org.soen6441.strategypattern;

import java.util.Random;

import org.soen6441.model.Country;
import org.soen6441.model.GameModelNew;
import org.soen6441.model.Order;
import org.soen6441.model.Player;
import org.soen6441.model.orders.Deploy;

public class BenevolentPlayerStrategy extends Strategy {

	/*
	 * A benevolent computer player strategy that focuses on protecting its weak countries 
	 * (deploys on its weakest country, 
	 * never attacks, 
	 * then moves its armies in order to reinforce its weaker country).
	 * */
	
	private Random d_Random;
	private GameModelNew d_GameModelNew;
	private Player d_Player;
	
	public BenevolentPlayerStrategy(Player p_Player,GameModelNew p_GameModelNew) {
		this.d_GameModelNew = p_GameModelNew;
		this.d_Player = p_Player;
		d_Random = new Random();
	}
	
	public Country toDefend() {
		Country l_TempCountry=null;
		int l_NumberOfArmies = 0;
		for(Country l_Country : this.d_Player.getCountryList()) {
			if(l_Country.getNoOfArmies()<l_NumberOfArmies) {
				l_NumberOfArmies = l_Country.getNoOfArmies();
				l_TempCountry = l_Country;
			}
		}
		System.out.println("Weakest Country : " + l_TempCountry.getCountryName() + " Has armies : " + l_TempCountry.getNoOfArmies());
		return l_TempCountry;
	}
	
	public Country toMove() {
		return null;
	}
	
	@Override
	public Order createOrder() {
		// TODO Auto-generated method stub
		return new Deploy(this.d_Player, toDefend(), 5);
	}

}
