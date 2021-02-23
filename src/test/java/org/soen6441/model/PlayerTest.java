package org.soen6441.model;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;


/**
 * Unit test for Player CLass.
 */
public class PlayerTest extends TestCase
{
/*
 * Test Case for Player cannot deploy more armies than in the reinforcement pool
 */
	Continent c0,c1;
	Country cc1,cc2,cc3,cc4,cc5;
	Map l_Map;
	GameModelNew d_GameModelNew;
	
	@Before
	public void setTestContext() throws Exception {		
		c0 = new Continent("asia",0);
		c1 =  new Continent("africa",0);
		cc1 = new Country("india","asia");
		cc2 = new Country("china","asia");
		cc3 = new Country("japan","asia");
		cc4 = new Country("kenya","africa");
		cc5 = new Country("egypt","africa");
		
		l_Map = new Map();
		l_Map.addContinent(c0.getContinentName(), "1");
		l_Map.addContinent(c1.getContinentName(), "1");
		l_Map.addCountry("india","asia");
		l_Map.addCountry("china","asia");
		l_Map.addCountry("japan","asia");
		l_Map.addCountry("kenya","africa");
		l_Map.addCountry("egypt","africa");
		
		d_GameModelNew = new GameModelNew(l_Map);
		
		d_GameModelNew.addPlayer("Akshita");
		d_GameModelNew.addPlayer("Zeal");
		
		d_GameModelNew.startUpPhase();		
	}
	
	@Test
	public void test() {
		
//		for (Player l_Player: d_GameModelNew.getAllPlayers()) {
//			System.out.println(l_Player.getPlayerName() + "has" +l_Player.getPlayerArmies() +" armies" );
//		}
//		
	}
}
