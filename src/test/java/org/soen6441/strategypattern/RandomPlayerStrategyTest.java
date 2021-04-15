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
	/**
	 * Player objects 
	 */
	Player d_Player1,d_Player2;
	/**
	 * Map object
	 */
	Map d_Map;
	/**
	 * GameModelNew Object
	 */
	GameModelNew d_GameModelNew;
	/**
	 * Strategy Object	
	 */
	Strategy d_Strategy1,d_Strategy2;
	/**
	 * Continent Object
	 */
	Continent d_Continent;
	/*
	 * Country objects
	 */
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
	/**
	 * This Method is to check whether the Player is defending on random Country
	 */
	@Test
	public void testDefendCheck(){
		String l_Actual="";
		String l_Expected="The Random player is defending from india";
		RandomPlayerStrategy l_random=new RandomPlayerStrategy(d_Player1,d_GameModelNew);
		l_random.toDefend();
		l_Actual = d_Player1.getResult();
		assertEquals(l_Expected,l_Actual);
	}
	

}
