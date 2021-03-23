package org.soen6441.model.orders;

import org.soen6441.model.Country;
import org.soen6441.model.Order;
import org.soen6441.model.Player;

public class Airlift implements Order {
	Player d_Player;
	Country d_SourceCountry,d_TargetCountry;
	int d_NumArmies;

	public Airlift(Player p_player, Country p_SourceCountry, Country p_TargetCountry, int p_NumArmies) {
		d_Player = p_player;
		d_SourceCountry = p_SourceCountry;
		d_TargetCountry = p_TargetCountry;
		d_NumArmies = p_NumArmies;
	}

	@Override
	public void execute() {
		if(isValid()) {
			d_SourceCountry.setNoOfArmies(d_SourceCountry.getNoOfArmies()-d_NumArmies);
			d_TargetCountry.setNoOfArmies(d_TargetCountry.getNoOfArmies()+d_NumArmies);
		}
		d_Player.removeCard("Airlift");
	}

	public boolean isValid() {
		
		if(!d_Player.getCardList().contains("Airlift")) {
			System.out.println("Player does not have a Airlift card");
			return false;
		}
		if(d_SourceCountry==d_TargetCountry) {
			d_Player.setResult("The source country and target country cannot be same!");
			return false;
		}
		else if(d_SourceCountry.getNoOfArmies()-d_NumArmies < 1) {
			d_Player.setResult("The source country should be left with atleast one army!");
			return false;
		}
		else {
			if(d_Player.getCountryList().contains(d_SourceCountry) && d_Player.getCountryList().contains(d_TargetCountry)) {
				d_Player.setResult("The source country and target country belong to the same player");
				return true;
			}
			else {
				d_Player.setResult("You can only airlift armies to your own countries. "+d_TargetCountry+"does not belongs to "+d_Player);
				return false;
			}
		}
	}
}