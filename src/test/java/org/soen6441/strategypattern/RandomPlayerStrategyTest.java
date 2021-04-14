package org.soen6441.strategypattern;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.soen6441.model.Continent;
import org.soen6441.model.Country;
import org.soen6441.model.GameModelNew;
import org.soen6441.model.Map;
import org.soen6441.model.Player;

public class RandomPlayerStrategyTest {

	Player d_Player1,d_Player2;
	Map d_Map;
	GameModelNew d_GameModelNew;
	Strategy d_Strategy1,d_Strategy2;
	Continent d_Continent;
	Country d_Country1, d_Country2, d_Country3;

	@Before
	public void setTestContext() throws Exception {
		d_Map = new Map();
		d_GameModelNew = new GameModelNew(d_Map);
		d_Player1 = new Player("raj", d_GameModelNew);
		d_Continent = new Continent("asia",3);
		d_Map.addContinent(d_Continent.getContinentName(), "3");
		d_Map.addCountry("india","asia");;
		d_Country1 = new Country("india","asia");
		d_Player1.addCountry(d_Country1);
		d_Player1.setPlayerArmies(3);
		d_Country1.setNoOfArmies(2);
		
	}
	

}
