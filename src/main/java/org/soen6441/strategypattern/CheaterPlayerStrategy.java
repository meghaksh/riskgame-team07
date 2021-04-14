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
	protected ArrayList<Country> toAttack()
	{
		ArrayList<Country> l_ReturnCountries = new ArrayList<Country>();
		Country l_AttackCountry = null;
		Country l_DefendingCountry = toDefend();
		String l_ReturnCountryName="";
		System.out.println("in cheater to attack method");
		l_ReturnCountryName  = l_DefendingCountry.getBorder().get(rand.nextInt(l_DefendingCountry.getBorder().size()));
		System.out.println("in cheater before for loop: "+l_ReturnCountryName);
		for(Country l_TempCountry:d_GameModelNew.getMap().getCountryList())
		{
			if(l_TempCountry.getCountryName().equals(l_ReturnCountryName))
			{
				l_AttackCountry = l_TempCountry;
				break;
			}
			
		}
		System.out.println("in cheater after for loop attcked country - "+l_AttackCountry.getCountryName());
		l_ReturnCountries.add(0, l_DefendingCountry);
		l_ReturnCountries.add(1, l_AttackCountry);
		System.out.println("in cheater the returning arraylist -"+l_ReturnCountries.get(0).getCountryName()+" , "+l_ReturnCountries.get(1).getCountryName());
		/*try {
		l_ReturnCountry = d_GameModelNew.getMap().getCountryList().get(rand.nextInt(d_GameModelNew.getMap().getCountryList().size()));}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
		d_Leb.setResult("The cheater player is attacking to "+l_AttackCountry.getCountryName());
		return l_ReturnCountries;
	}
	protected Country toDefend()
	{
		Country l_ReturnCountry = null;
		try
		{
		l_ReturnCountry = d_Player.getCountryList().get(rand.nextInt(d_Player.getCountryList().size()));}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		d_Leb.setResult("The cheater player is attacking from "+l_ReturnCountry.getCountryName());
		return l_ReturnCountry;
	}
	@Override
	public Order createOrder() {
		// TODO Auto-generated method stub
		int l_rndOrder = rand.nextInt(2);
		Order l_returnOrder = null;
		
			switch(l_rndOrder) 
			{
			case 0: System.out.println("in cheater case 0 deploy order");
			Country l_DefendCountry1 = toDefend();
					d_Leb.setResult("in cheater the armies are deployed to -" +l_DefendCountry1);
					l_returnOrder = new Deploy(d_Player,l_DefendCountry1,rand.nextInt(10));
					break;
			
			case 1: System.out.println("in cheater case 1 advance order");
					
					ArrayList<Country> l_Countries = toAttack();
					/*while(!l_DefendCountry.getBorder().contains(l_AttackCountry))
					{
						l_AttackCountry = toAttack();
					}*/
					d_Leb.setResult("in cheater defending country - "+l_Countries.get(0)+" Attacking country - "+l_Countries.get(1));
					l_returnOrder =  new Advance(d_Player,l_Countries.get(0),l_Countries.get(1),rand.nextInt(l_Countries.get(0).getNoOfArmies()+5));
					
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
