package org.soen6441.model.orders;

import java.util.Iterator;

import org.soen6441.controller.PlayerController;
import org.soen6441.model.Country;
import org.soen6441.model.Order;
import org.soen6441.model.Player;

public class Bomb implements Order {
	
	private Player d_Player;
	private Country d_Country;
	private int d_NumArmies;
	private PlayerController d_Pc;
	
	

	public Bomb(Player p_player, Country p_TempCountry) {
		setPlayer(p_player);
		setCountry(p_TempCountry);
	}



	private void setCountry(Country p_TempCountry) {
		this.d_Country = p_TempCountry;
		
	}

	private void setPlayer(Player player) {
		this.d_Player = player;		
	}

	@Override
	public void execute() {
		if(isValid())
		{
			d_Country.setNoOfArmies(d_Country.getNoOfArmies()/2);
			
		}
		
	}
	
	public boolean isValid()
	{
		int l_Flag=0;

			Iterator<Country>l_It = d_Player.getCountryList().iterator();
			while(l_It.hasNext()) {
				Country l_TempCountry = (Country)l_It.next() ;
				if(d_Country==l_TempCountry) {
					l_Flag=1;
					break;
				}
			}
			if(l_Flag==1) {
				d_Player.setResult("\norder deploy "+d_Country.getCountryName()+" "+d_NumArmies+" added to list of "+d_Player.getPlayerName());
				return true;
			} else {
				d_Player.setResult("\nThis country "+d_Country.getCountryName()+" doesnot belongs to "+d_Player.getPlayerName());
				return false;
			}	
	}
}
