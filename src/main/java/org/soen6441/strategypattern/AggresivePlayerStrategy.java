package org.soen6441.strategypattern;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import org.soen6441.model.Country;
import org.soen6441.model.GameModelNew;
import org.soen6441.model.Order;
import org.soen6441.model.Player;
import org.soen6441.model.orders.Advance;
import org.soen6441.model.orders.Deploy;

public class AggresivePlayerStrategy extends Strategy implements Serializable {

	/*
	 * An aggressive computer player strategy that focuses on centralization of forces and then attack, 
	 * i.e. it deploys on its strongest country, then always attack with its strongest country, 
	 * then moves its armies in order to maximize aggregation of forces in one country.
	 */
	private Random d_Random;
	private GameModelNew d_GameModelNew;
	private Player d_Player;
	public AggresivePlayerStrategy(Player p_Player,GameModelNew p_GameModelNew) {
		this.d_Player = p_Player;
		this.d_GameModelNew = p_GameModelNew;
		d_Random = new Random();
		d_Leb.setResult("Aggressive Player");
	}
	@Override
	public Country toDefend() {

		Country l_TempCountry=null;
		HashMap <Country,Integer> l_PlayerCountryMap = new HashMap<>();
		for(Country l_Country : d_Player.getCountryList())
		{
			l_PlayerCountryMap.put(l_Country, l_Country.getNoOfArmies());
		}
		List<Entry<Country, Integer>> list = new LinkedList<Entry<Country, Integer>>(l_PlayerCountryMap.entrySet()); 
		Collections.sort(list, new Comparator<Entry<Country, Integer>>()   
		{  
			public int compare(Entry<Country, Integer> o1, Entry<Country, Integer> o2)   
			{   
				return o2.getValue().compareTo(o1.getValue());  
			}  
		});
		l_TempCountry = list.get(0).getKey();
		d_Player.setResult("The aggressive player is defending from "+l_TempCountry.getCountryName());
		return l_TempCountry;
	}
	@Override
	public ArrayList<Country> toAttack()
	{

		ArrayList<Country> l_ReturnCountries = new ArrayList<Country>();
		Country l_Country = toDefend();
		Country l_ReturnCountry=null;
		ArrayList<Country> l_BorderCountriesList = new ArrayList<>();
		for(Country l_C : this.d_GameModelNew.getMap().getCountryList()) {
			if(l_Country.getBorder().contains(l_C.getCountryName())) {
				l_BorderCountriesList.add(l_C);
			}
		}
		l_ReturnCountry = l_BorderCountriesList.get(d_Random.nextInt(l_BorderCountriesList.size()));

		l_ReturnCountries.add(0,l_Country);
		l_ReturnCountries.add(0,l_ReturnCountry);
		d_Player.setResult("The aggressive player is attacking on "+l_ReturnCountry.getCountryName());
		return l_ReturnCountries;
	}

	/**
	 * Either deploy to the strongest country or attack from strongest country
	 */
	@Override
	public Order createOrder() {
		// TODO Auto-generated method stub
		int l_RandomInt = d_Random.nextInt(2);
		Order l_OrderToBeReturned = null;
		switch(l_RandomInt) {
		case 0: Country l_DefendCountry1 = toDefend();
				d_Leb.setResult("in agressive the armies are deployed to -" +l_DefendCountry1);
				l_OrderToBeReturned = new Deploy(this.d_Player, l_DefendCountry1, Math.max(d_Random.nextInt(d_Player.getPlayerArmies()),2));
				break;
		case 1: ArrayList<Country> l_Countries = toAttack();
		if(l_Countries.get(0).getNoOfArmies()>1)
		{
			d_Leb.setResult("in aggressive defending country - "+l_Countries.get(0)+" Attacking country - "+l_Countries.get(1)+" with armies- "+(l_Countries.get(0).getNoOfArmies()-1));
			l_OrderToBeReturned =  new Advance(this.d_Player, l_Countries.get(0), l_Countries.get(1), l_Countries.get(0).getNoOfArmies()-1);
		}
		else
		{
			d_Leb.setResult("in agrressive the armies are deployed to -" +l_Countries.get(0));
			l_OrderToBeReturned = new Deploy(this.d_Player, l_Countries.get(0),Math.max(d_Random.nextInt(d_Player.getPlayerArmies()),2));
		}
		break;	
		}
		d_Leb.setResult("in aggressive the order is - "+l_OrderToBeReturned);
		return l_OrderToBeReturned;
	}

	@Override
	public String strategyName() {
		return "Aggressive";
	}

}
