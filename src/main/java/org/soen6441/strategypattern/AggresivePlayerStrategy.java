package org.soen6441.strategypattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;

import org.soen6441.model.Country;
import org.soen6441.model.GameModelNew;
import org.soen6441.model.Order;
import org.soen6441.model.Player;
import org.soen6441.model.orders.Advance;
import org.soen6441.model.orders.Deploy;

public class AggresivePlayerStrategy extends Strategy {

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
	}
	
	public Country attackFrom() {
		System.out.println("in aggressive attcakfrom");
		Country l_TempCountry=null;
		int l_NumberOfArmies = 0;
		HashMap <Country,Integer> l_PlayerCountryMap = new HashMap<>();
		System.out.println("player name - "+d_Player);
		for(Country l_Country : d_Player.getCountryList())
		{
			System.out.println("in for loop "+l_Country+" - "+l_Country.getNoOfArmies());
			l_PlayerCountryMap.put(l_Country, l_Country.getNoOfArmies());
			System.out.println(l_Country+" - "+l_Country.getNoOfArmies());
		}
		System.out.println("int aggressive hashmap is "+l_PlayerCountryMap);
		List<Entry<Country, Integer>> list = new LinkedList<Entry<Country, Integer>>(l_PlayerCountryMap.entrySet()); 
		Collections.sort(list, new Comparator<Entry<Country, Integer>>()   
		{  
			public int compare(Entry<Country, Integer> o1, Entry<Country, Integer> o2)   
			{   
				return o2.getValue().compareTo(o1.getValue());  
			}  
		});
		System.out.println("in aggressive the list got sorted");
		l_TempCountry = list.get(0).getKey();
		/*for(Country l_Country : this.d_Player.getCountryList()) {
			if(l_Country.getNoOfArmies()>l_NumberOfArmies) {
				l_NumberOfArmies = l_Country.getNoOfArmies();
				l_TempCountry = l_Country;
			}
		}*/
		System.out.println("Strongest Country : " + l_TempCountry + " Has armies : " + l_TempCountry.getNoOfArmies());
		return l_TempCountry;
	}
	
	public Country attackTo() {
		
		System.out.println("in aggressive attack to");
		Country l_Country = attackFrom();
		ArrayList<Country> l_BorderCountriesList = new ArrayList<>();
		for(Country l_C : this.d_GameModelNew.getMap().getCountryList()) {
			if(l_Country.getBorder().contains(l_C.getCountryName())) {
				l_BorderCountriesList.add(l_C);
			}
		}
		System.out.println("leaving attackTo in agressive");
		return l_BorderCountriesList.get(d_Random.nextInt(l_BorderCountriesList.size()));
	}
	
	/**
	 * Either deploy to the strongest country or attack from strongest country
	 */
	@Override
	public Order createOrder() {
		// TODO Auto-generated method stub
		System.out.println("in aggressive create order");
		int l_RandomInt = d_Random.nextInt(2);
		Order l_OrderToBeReturned = null;
		switch(l_RandomInt) {
			case 0:
				l_OrderToBeReturned = new Deploy(this.d_Player, attackFrom(), 5);
				break;
			case 1:
				l_OrderToBeReturned =  new Advance(this.d_Player, attackFrom(), attackTo(), attackFrom().getNoOfArmies()-1);
				break;	
		}
		System.out.println("in aggressive the order is - "+l_OrderToBeReturned);
		return l_OrderToBeReturned;
	}

}
