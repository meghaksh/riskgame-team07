package org.soen6441.model.orders;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import org.soen6441.model.Country;
import org.soen6441.model.Order;
import org.soen6441.model.Player;

public class Airlift implements Order {
	Player d_Player;
	Country d_SourceCountry,d_TargetCountry;
	int d_NumArmies;


	
	public Airlift(Player l_player, Country l_SourceCountry, Country l_TargetCountry, int l_NumArmies)
	{
		d_Player = l_player;
		d_SourceCountry = l_SourceCountry;
		d_TargetCountry = l_TargetCountry;
		d_NumArmies = l_NumArmies;
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		 
		 if(isValid())
		 {
			 d_SourceCountry.setNoOfArmies(d_SourceCountry.getNoOfArmies()-d_NumArmies);
			 d_TargetCountry.setNoOfArmies(d_TargetCountry.getNoOfArmies()+d_NumArmies);
		 }
		 d_Player.removeCard("Airlift");
		
		
	}
	
	
	public boolean isValid()
	{
		if(d_SourceCountry==d_TargetCountry)
		{
			d_Player.setResult("The source country and target country cannot be same!");
			return false;
		}
		else if(d_SourceCountry.getNoOfArmies()-d_NumArmies < 1)
		{
			d_Player.setResult("The source country should be left with atleast one army!");
			return false;
		}
		else
		{
			if(d_Player.getCountryList().contains(d_SourceCountry) && d_Player.getCountryList().contains(d_TargetCountry))
			{
				d_Player.setResult("The source country and target country belong to the same player");
				return true;
			}
			else
			{
				d_Player.setResult("You can only airlift armies to your own countries. "+d_TargetCountry+"does not belongs to "+d_Player);
				return false;
			}
		}
		
	}

}
