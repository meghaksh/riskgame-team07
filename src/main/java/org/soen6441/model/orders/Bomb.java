package org.soen6441.model.orders;

import java.util.Iterator;

import org.soen6441.controller.PlayerController;
import org.soen6441.model.Country;
import org.soen6441.model.GameModelNew;
import org.soen6441.model.Order;
import org.soen6441.model.Player;

public class Bomb implements Order {
	
	private Player d_Player;
	private Country d_Country;
	private int d_NumArmies;
	private GameModelNew d_gmn;
	
	

	public Bomb(Player p_player, Country p_TempCountry) {
		setPlayer(p_player);
		setCountry(p_TempCountry);
		setGameModelNew();
	}
	
	private void setGameModelNew() {
		this.d_gmn = d_Player.getGameModel();
	}

	private void setCountry(Country p_TempCountry) {
		this.d_Country = p_TempCountry;
	}

	private void setPlayer(Player player) {
		this.d_Player = player;		
	}

	@Override
	public void execute() {
		System.out.println("Inside bomb execute");
		if(isValid())
		{
			d_Country.setNoOfArmies(d_Country.getNoOfArmies()/2);
			for(Player l_Player : d_gmn.getAllPlayers()) {
				for(Country l_Country: l_Player.getCountryList()) {
					if(l_Country.equals(d_Country)) {
						l_Player.setPlayerArmies(l_Player.getPlayerArmies()- d_Country.getNoOfArmies()/2 );
					}
				}
			}
		}
	}
	
	public boolean isValid()
	{
		int l_Flag=0;

			Iterator<Country>l_It = d_Player.getCountryList().iterator();
			while(l_It.hasNext()) {
				Country l_TempCountry = (Country)l_It.next() ;
				System.out.println("Country"+ l_TempCountry.getCountryName());
				if(l_TempCountry.getCountryName().equals(d_Country.getCountryName())) {
//					d_Player.setResult("\n Player cannot bomb its own country");
					System.out.println("Player cannot bomb its own country" + d_Country.getCountryName());
					return false;
				}
				if(l_TempCountry.getBorder().contains(d_Country.getCountryName())) {
					l_Flag=1;
					break;
				}
			}
			if(l_Flag==1) {
//				d_Player.setResult("\norder bomb "+d_Country.getCountryName()+" added to list of "+d_Player.getPlayerName());
				System.out.println("\norder bomb "+d_Country.getCountryName()+" added to list of "+d_Player.getPlayerName());

				return true;
			} else {
//				d_Player.setResult("\nThe country "+d_Country.getCountryName()+" is not a neighbour of "+d_Player.getPlayerName()+"'s countries.");
				System.out.println("\nThe country "+d_Country.getCountryName()+" is not a neighbour of "+d_Player.getPlayerName()+"'s countries.");

				return false;
			}	
	}
}
