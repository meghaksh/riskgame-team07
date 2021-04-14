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
import org.soen6441.model.orders.Deploy;

public class BenevolentPlayerStrategy extends Strategy implements Serializable {

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
		d_Leb.setResult("Benevolent Player");
	}
	@Override
	public Country toDefend() {

		Country l_TempCountry=null;
		int l_NumberOfArmies = 0;
		HashMap <Country,Integer> l_PlayerCountryMap = new HashMap<>();

		for(Country l_Country : d_Player.getCountryList())
		{
			System.out.println("in for loop "+l_Country+" - "+l_Country.getNoOfArmies());
			l_PlayerCountryMap.put(l_Country, l_Country.getNoOfArmies());
			System.out.println(l_Country+" - "+l_Country.getNoOfArmies());
		}
		System.out.println("int benevolent treemap is "+l_PlayerCountryMap);
		List<Entry<Country, Integer>> list = new LinkedList<Entry<Country, Integer>>(l_PlayerCountryMap.entrySet()); 
		Collections.sort(list, new Comparator<Entry<Country, Integer>>()   
		{  
			public int compare(Entry<Country, Integer> o1, Entry<Country, Integer> o2)   
			{   
				return o1.getValue().compareTo(o2.getValue());  
			}  
		});
		l_TempCountry = list.get(0).getKey();

		System.out.println("left for loop  "+l_TempCountry);
		d_Leb.setResult("the Benevolent Player is defefnding country "+l_TempCountry.getCountryName()+" country with "+l_TempCountry.getNoOfArmies()+" armies");
		//System.out.println("Weakest Country : " + l_TempCountry.getCountryName() + " Has armies : " + l_TempCountry.getNoOfArmies());
		return l_TempCountry;
	}

	@Override
	public ArrayList<Country> toAttack()
	{
		return null;
	}

	@Override
	public Order createOrder() {
		// TODO Auto-generated method stub
		Order l_returnOrder=null;
		Country l_DefendCountry1 = toDefend();
		d_Leb.setResult("in cheater the armies are deployed to -" +l_DefendCountry1);
		l_returnOrder =  new Deploy(this.d_Player, l_DefendCountry1, Math.max(d_Random.nextInt(d_Player.getPlayerArmies()),2));
		d_Leb.setResult("in benevolent player the order issued is - "+l_returnOrder);
		return l_returnOrder;
	}

	@Override
	public String strategyName() {
		// TODO Auto-generated method stub
		return "Benevolent";
	}

}
