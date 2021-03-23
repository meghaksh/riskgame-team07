package org.soen6441.model.orders;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.soen6441.controller.GameEngine;
import org.soen6441.model.Continent;
import org.soen6441.model.Country;
import org.soen6441.model.GameModelNew;
import org.soen6441.model.Map;
import org.soen6441.model.Player;
import org.soen6441.view.CommandPrompt;
/*
 * 1) When Advance(transfer/attack) on the non adjacent territory
 * 2) If Transfer is working properly. Source and target countries belong to same player and are adjacent
 * 3) If Attack is working properly. Source and target countries belong to different players and are adjacent
 * 4) At the end of the turn, card is assigned properly to all the players who conquer at least one country in the turn
 * 5) Player's non deployed armies in this turn is adjusted in the next turn
 * 6) Bomb command halves the number of armies on the target territory
 * 7) Bomb command on the non adjacent terittory 
 * 8) Any player that doesn't own a single country is removed from the player's list at the end of the turn (important to win the game)
 * 9) scenario when defender has more armies than attacker in the attack 
 * 10) blockade and negotiate orders working properly 
 * 11) deploy on another player's territory 
 * 12) any other command or invalid string while issuing the order 
 * 13) if player a has negotiated with b, then attack from both the player can not happen on each other. 
 * */

public class AdvanceTest {
	CommandPrompt d_CpView;
	GameModelNew d_GameModel;
	GameEngine d_Ge;
	Continent d_C0,d_C1;
	Country d_Country1,d_Country2,d_Country3,d_Country4,d_Country5;

	Player d_P1,d_P2;
	Map d_Map;
	Advance d_adv;

	@Before
	public void setTestContext() throws Exception {
		d_CpView= new CommandPrompt();

		d_C0 = new Continent("asia",0);
		d_C1 =  new Continent("africa",0);

		d_Country1 = new Country("india","asia");
		d_Country2 = new Country("china","asia");
		d_Country3 = new Country("japan","asia");
		d_Country4 = new Country("kenya","africa");
		d_Country5 = new Country("egypt","africa");

		d_Map = new Map();
		d_Map.addContinent(d_C0.getContinentName(), "1");
		d_Map.addContinent(d_C1.getContinentName(), "1");

		d_Map.addCountry("india","asia");
		d_Map.addCountry("china","asia");
		d_Map.addCountry("japan","asia");
		d_Map.addCountry("kenya","africa");
		d_Map.addCountry("egypt","africa");

		d_Map.addBorder("egypt", "kenya");
		d_Map.addBorder("kenya", "japan");
		d_Map.addBorder("japan", "china");
		d_Map.addBorder("china", "india");
		d_Map.addBorder("india", "kenya");
		d_Map.addBorder("kenya", "egypt");
		d_Map.addBorder("india", "japan");

		d_GameModel = new GameModelNew(d_Map);
		d_Ge= new GameEngine(d_CpView,d_GameModel);
		d_GameModel.addPlayer("raj");
		d_GameModel.addPlayer("kumar");
		d_P1 = new Player("raj",d_GameModel);
		d_P2 = new Player("kumar",d_GameModel);

		d_P1.addCountry(d_Country1);
		d_P1.addCountry(d_Country4);
		d_P2.addCountry(d_Country3);
		d_P2.addCountry(d_Country2);
		d_P2.addCountry(d_Country5);

		d_Country1.setCountryOwnerPlayer(d_P1);
		d_Country2.setCountryOwnerPlayer(d_P2);
		d_Country3.setCountryOwnerPlayer(d_P2);
		d_Country4.setCountryOwnerPlayer(d_P1);
		d_Country5.setCountryOwnerPlayer(d_P2);

		d_P1.setPlayerArmies(3);
		d_P2.setPlayerArmies(3);
		d_Country1.setNoOfArmies(3);
		d_Country5.setNoOfArmies(3);

	}

	@Test
	public void testValidateMap() throws Exception {
		String l_Actual="", l_Expected="Map is Valid";
		l_Actual = d_Map.validateMap();
		assertEquals(l_Expected,l_Actual);
	}

	@Test
	public void testNonAdjacentTerritory() {
		String l_Actual="", l_Expected="\nThe source country and target country are not neighbours!";
		d_adv = new Advance(d_P1,  d_Country1, d_Country5, 2);
		d_adv.execute();
		l_Actual = d_adv.d_Player.getResult();
		assertEquals(l_Expected,l_Actual);

	}
}
