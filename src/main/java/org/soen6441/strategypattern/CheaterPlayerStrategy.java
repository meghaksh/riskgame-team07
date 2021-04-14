package org.soen6441.strategypattern;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import org.soen6441.model.Country;
import org.soen6441.model.GameModelNew;
import org.soen6441.model.Order;
import org.soen6441.model.Player;
import org.soen6441.model.orders.Advance;
import org.soen6441.model.orders.Deploy;

public class CheaterPlayerStrategy extends Strategy implements Serializable {

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
		d_Leb.setResult("Cheater Player");
	}
	@Override
	public ArrayList<Country> toAttack()
	{
		ArrayList<Country> l_ReturnCountries = new ArrayList<Country>();
		Country l_AttackCountry = null;
		Country l_DefendingCountry = toDefend();
		String l_ReturnCountryName="";
		l_ReturnCountryName  = l_DefendingCountry.getBorder().get(rand.nextInt(l_DefendingCountry.getBorder().size()));
		for(Country l_TempCountry:d_GameModelNew.getMap().getCountryList())
		{
			if(l_TempCountry.getCountryName().equals(l_ReturnCountryName))
			{
				l_AttackCountry = l_TempCountry;
				break;
			}

		}
		l_ReturnCountries.add(0, l_DefendingCountry);
		l_ReturnCountries.add(1, l_AttackCountry);
		d_Player.setResult("The Cheater player is attacking on "+l_AttackCountry.getCountryName());
		return l_ReturnCountries;
	}
	@Override
	public Country toDefend()
	{
		Country l_ReturnCountry = null;
		try
		{
			l_ReturnCountry = d_Player.getCountryList().get(rand.nextInt(d_Player.getCountryList().size()));}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		d_Player.setResult("The Cheater player is defending from "+l_ReturnCountry.getCountryName());
		return l_ReturnCountry;
	}
	@Override
	public Order createOrder() {
		// TODO Auto-generated method stub
		int l_rndOrder = rand.nextInt(2);
		Order l_returnOrder = null;

		switch(l_rndOrder) 
		{
		case 0: 
			Country l_DefendCountry1 = toDefend();
			d_Leb.setResult("in cheater the armies are deployed to -" +l_DefendCountry1);
			l_returnOrder = new Deploy(d_Player,l_DefendCountry1,Math.max(rand.nextInt(d_Player.getPlayerArmies()),2));
			break;

		case 1: 
			ArrayList<Country> l_Countries = toAttack();
			if(l_Countries.get(0).getNoOfArmies()>1)
			{
				d_Leb.setResult("in cheater defending country - "+l_Countries.get(0)+" Attacking country - "+l_Countries.get(1)+" with armies - "+(l_Countries.get(0).getNoOfArmies()-1));
				l_returnOrder =  new Advance(d_Player,l_Countries.get(0),l_Countries.get(1),l_Countries.get(0).getNoOfArmies()-1);
			}
			else
			{
				d_Leb.setResult("in cheater the armies are deployed to -" +l_Countries.get(0));
				l_returnOrder = new Deploy(d_Player,l_Countries.get(0),Math.max(rand.nextInt(d_Player.getPlayerArmies()),2));
			}

			break;
		}

		d_Leb.setResult("in cheater player order is "+l_returnOrder);
		return l_returnOrder;
	}
	@Override
	public String strategyName() {
		// TODO Auto-generated method stub
		return "Cheater";
	}

}
