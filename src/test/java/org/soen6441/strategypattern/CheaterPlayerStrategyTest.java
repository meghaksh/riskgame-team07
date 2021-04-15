package org.soen6441.strategypattern;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.soen6441.model.Continent;
import org.soen6441.model.Country;
import org.soen6441.model.GameModelNew;
import org.soen6441.model.Map;
import org.soen6441.model.Player;

public class CheaterPlayerStrategyTest {
	/**
	 * Player object
	 */
	Player d_Player1,d_Player2;
	/**
	 * Map objects
	 */
	Map d_Map;
	/**
	 * Gamemodelnew object
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
	/**
	 * Country Objects
	 */
	Country d_Country1, d_Country2, d_Country3;

	@Before
	public void setTestContext() throws Exception {
		d_Map = new Map();
		d_GameModelNew = new GameModelNew(d_Map);
		d_Player1 = new Player("raj", d_GameModelNew);
		d_Player2 = new Player("zeal", d_GameModelNew);
		d_Continent = new Continent("asia",3);
		d_Map.addContinent(d_Continent.getContinentName(), "3");
		d_Map.addCountry("india","asia");
		d_Map.addCountry("china","asia");
		d_Map.addBorder("india", "china");
		d_Map.addBorder("china", "india");
		d_Country1 = new Country("india","asia");
		d_Country2 = new Country("china","asia");
		d_Player1.addCountry(d_Country1);
		d_Player2.addCountry(d_Country2);
		d_Player1.setPlayerArmies(3);
		d_Player2.setPlayerArmies(3);
		d_Country1.setNoOfArmies(2);
		d_Country2.setNoOfArmies(3);
		d_Country1.setBorder("china");
		d_Country2.setBorder("india");
		
	}
	/**
	 * This Method is to check whether the Player is defending on the Players own Country
	 */
	@Test
	public void testDefendCheck() {
		String l_Actual="";
		String l_Expected="The Cheater player is defending from india";
		CheaterPlayerStrategy l_cheater=new CheaterPlayerStrategy(d_Player1,d_GameModelNew);
		l_cheater.toDefend();
		l_Actual = d_Player1.getResult();
		assertEquals(l_Expected,l_Actual);
	}
	/**
	 * This Method is to check whether the Player is attacking on the other players Country
	 */
	@Test
	public void testAttackCheck(){
		String l_Actual="";
		String l_Expected="The Cheater player is attacking on china";
		CheaterPlayerStrategy l_cheater=new CheaterPlayerStrategy(d_Player1,d_GameModelNew);
		l_cheater.toAttack();
		l_Actual = d_Player1.getResult();
		assertEquals(l_Expected,l_Actual);
	}
	
}
