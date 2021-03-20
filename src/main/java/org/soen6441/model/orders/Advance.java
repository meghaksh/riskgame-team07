package org.soen6441.model.orders;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.soen6441.model.Country;
import org.soen6441.model.Order;
import org.soen6441.model.Player;

public class Advance implements Order {
	Country d_SourceCountry,d_TargetCountry;
	Player d_Player;
	int d_NumArmies;
	
	public Advance() {
		
		
		/*Source should not be equal to target
		 * Source and target should be neighbors
		 * Source should have at least one army present after move/attack 
		 * win happens only when number of armies of defender is zero. and then only card is issued.
		 * Attack with the lesser number of armies than the player owns. */
		/*Player Reference who is issuing advance command. 
		 * From & To countries
		 * Number of Armies 
		 * 
		 * 
		 * 
		 * Check if both countries(To & From) belongs to the same player. 
		 * If yes, then simply move the armies to the "To" country from "From"
		 * If No, Write the logic for the attack. 
		 * Once attack is won, assign random card to the player who won.
		 *  (Country which wins the attack, search for it's owner and assign a card to that owner ) */
	}
	public Advance(Player l_Player, Country l_SourceCountry, Country l_TargetCountry, int l_NumArmies1) 
	{
		d_Player = l_Player;
		d_SourceCountry = l_SourceCountry;
		d_TargetCountry = l_TargetCountry;
		d_NumArmies = l_NumArmies1;
		
	}
	@Override
	public void execute()
	{
		
		
	}
	public int isValid()
	{
		if(d_SourceCountry==d_TargetCountry)
		{
			d_Player.setResult("The source country and target country cannot be same!");
			return 0;
		}
		if(!d_SourceCountry.getBorder().contains(d_TargetCountry))
		{
			d_Player.setResult("The source country and target country are not neighbours!");
			return 0;
		}
		if(d_SourceCountry.getNoOfArmies()-d_NumArmies < 1)
		{
			d_Player.setResult("The source country should be left with atleast one army!");
			return 0;
		}
		else
		{
			if(d_Player.getCountryList().contains(d_SourceCountry) && d_Player.getCountryList().contains(d_TargetCountry))
			{
				d_Player.setResult("The source country and target country belong to the same player");
				return 1;
			}
			else
			{
				return 2;
			}
		}
		
	}
}
