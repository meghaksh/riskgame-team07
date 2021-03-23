package org.soen6441.model.orders;

import java.util.Iterator;

import org.soen6441.model.Country;
import org.soen6441.model.Order;
import org.soen6441.model.Player;

public class Deploy implements Order{

	private Player d_Player;
	private Country d_Country;
	private int d_NumArmies;

	public Deploy(Player p_Player, Country p_Country, int p_NumArmies) {
		// TODO Auto-generated constructor stub
		d_Player = p_Player;
		d_Country = p_Country;
		d_NumArmies = p_NumArmies;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(isValid())
		{
			d_Player.setPlayerArmies(d_Player.getPlayerArmies() - d_NumArmies);
			d_Country.setNoOfArmies(d_Country.getNoOfArmies()+d_NumArmies);
		}


	}
	public boolean isValid()
	{
		int l_Flag=0;
		if(d_NumArmies <= d_Player.getPlayerArmies()){
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
		} else {
			d_Player.setResult("\n"+d_Player.getPlayerName()+" ; you have only "+d_Player.getPlayerArmies()+" number of armies! Please enter the next order accordingly");
			return false;
		}


	}

}
