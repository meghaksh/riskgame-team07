package org.soen6441.strategypattern;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.soen6441.model.Continent;
import org.soen6441.model.Country;
import org.soen6441.model.GameModelNew;
import org.soen6441.model.Map;
import org.soen6441.model.Player;

public class AggresivePlayerStrategyTest {

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
		d_Player2 = new Player("zeal", d_GameModelNew);
		d_Strategy1 = new BenevolentPlayerStrategy(d_Player1, d_GameModelNew);
		d_Strategy2 = new AggresivePlayerStrategy(d_Player2, d_GameModelNew);
		d_Player1.setPlayerStrategy(d_Strategy1);
		d_Player2.setPlayerStrategy(d_Strategy2);
		d_Continent = new Continent("asia",3);
		d_Map.addContinent(d_Continent.getContinentName(), "3");
		d_Map.addCountry("india","asia");
		d_Map.addCountry("china","asia");
		d_Map.addCountry("japan","asia");
		d_Map.addBorder("india", "china");
		d_Map.addBorder("china", "india");
		d_Map.addBorder("japan", "china");
		d_Map.addBorder("china", "japan");
		d_Map.addBorder("india", "japan");
		d_Country1 = new Country("india","asia");
		d_Country2 = new Country("china","asia");
		d_Country3 = new Country("japan","asia");
		d_Player1.addCountry(d_Country1);
		d_Player1.addCountry(d_Country2);
		d_Player2.addCountry(d_Country3);
		d_Player1.setPlayerArmies(3);
		d_Player2.setPlayerArmies(3);
		d_Country1.setNoOfArmies(2);
		d_Country2.setNoOfArmies(3);
		d_Country1.setBorder("china");
		d_Country1.setBorder("japan");
		d_Country2.setBorder("japan");
		d_Country3.setBorder("china");
		d_Country3.setBorder("india");
		
	}
	@Test
	public void testDefendCheck() throws Exception {
		String l_Actual="";
		String l_Expected="The aggressive player is defending from china";
		AggresivePlayerStrategy l_Agress=new AggresivePlayerStrategy(d_Player1,d_GameModelNew);
		l_Agress.toDefend();
		l_Actual = d_Player1.getResult();
		assertEquals(l_Expected,l_Actual);
	}
	@Test
	public void testAttackFromCheck() throws Exception {
		String l_Actual="";
		String l_Expected="The aggressive player is attacking on japan";
		AggresivePlayerStrategy l_Agress=new AggresivePlayerStrategy(d_Player1,d_GameModelNew);
		l_Agress.toAttack();
		l_Actual = d_Player1.getResult();
		assertEquals(l_Expected,l_Actual);
	}
}
