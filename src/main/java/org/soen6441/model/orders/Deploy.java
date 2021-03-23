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
		set_Player(p_Player);
		d_Country = p_Country;
		d_NumArmies = p_NumArmies;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(isValid())
		{
			get_Player().setPlayerArmies(get_Player().getPlayerArmies() - d_NumArmies);
			d_Country.setNoOfArmies(d_Country.getNoOfArmies()+d_NumArmies);
		}


	}
	public boolean isValid()
	{
		int l_Flag=0;
		if(d_NumArmies <= get_Player().getPlayerArmies()){
			Iterator<Country>l_It = get_Player().getCountryList().iterator();
			while(l_It.hasNext()) {
				Country l_TempCountry = (Country)l_It.next() ;
				if(d_Country==l_TempCountry) {
					l_Flag=1;
					break;
				}
			}
			if(l_Flag==1) {
				get_Player().setResult("\norder deploy "+d_Country.getCountryName()+" "+d_NumArmies+" added to list of "+get_Player().getPlayerName());
				return true;
			} else {
				get_Player().setResult("\nThis country "+d_Country.getCountryName()+" doesnot belongs to "+get_Player().getPlayerName());
				return false;
			}
		} else {
			get_Player().setResult("\n"+get_Player().getPlayerName()+" ; you have only "+get_Player().getPlayerArmies()+" number of armies! Please enter the next order accordingly");
			return false;
		}


	}

	public Player get_Player() {
		return d_Player;
	}

	public void set_Player(Player d_Player) {
		this.d_Player = d_Player;
	}

}
