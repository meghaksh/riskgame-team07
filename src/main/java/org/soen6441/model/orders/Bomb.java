package org.soen6441.model.orders;

import org.soen6441.model.Country;
import org.soen6441.model.Order;
import org.soen6441.model.Player;

public class Bomb implements Order {

	private Player d_Player;
	private Player d_PlayerBombed;
	private Country d_Country;
	private int d_NoOfArmies;

	public Bomb(Player p_player, Country p_TempCountry) {
		this.set_Player(p_player);
		this.d_Country = p_TempCountry;
		setBombedPlayer();
	}

	private void setBombedPlayer() {
		this.d_PlayerBombed = d_Country.getCountryOwnerPlayer();
		System.out.println("The country owner of "+d_Country.getCountryName() + "is "+d_PlayerBombed.getPlayerName());
	}

	@Override
	public void execute() {
		System.out.println("Inside bomb execute");
		if(isValid())
		{
			d_NoOfArmies = d_Country.getNoOfArmies()/2;
			int l_NoOfArmiesReduced = d_Country.getNoOfArmies() - d_NoOfArmies;
			d_Country.setNoOfArmies(d_Country.getNoOfArmies() - d_NoOfArmies);
			d_PlayerBombed.setPlayerArmies(d_PlayerBombed.getPlayerArmies() - l_NoOfArmiesReduced);
		}
		get_Player().removeCard("Bomb");

	}

	public boolean isValid()
	{
		if(!get_Player().getCardList().contains("Bomb")) {
			get_Player().setResult("Player does not have a bomb card");
			return false;
		}

		if(get_Player().getCountryList().contains(d_Country)) {
			get_Player().setResult("Player cannot bomb its own country");
			return false;
		}
		for(Country l_Country : get_Player().getCountryList()) {
			if(!l_Country.getBorder().contains(d_Country.getCountryName())) {
				get_Player().setResult("The bombing country is not a neighbour of player");
				return false;				
			}
		}
		System.out.println("The order is valid");
		return true;

	}

	public Player get_Player() {
		return d_Player;
	}

	public void set_Player(Player d_Player) {
		this.d_Player = d_Player;
	}
}
