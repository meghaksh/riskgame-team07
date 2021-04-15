package org.soen6441.model;				import static org.junit.Assert.assertEquals;											
import java.util.ArrayList;				import java.util.List;import static org.junit.Assert.*;
import org.junit.Before;				import org.junit.Test;
import org.soen6441.controller.GameEngine;
import org.soen6441.utility.state.Phase;
import org.soen6441.view.CommandPrompt;

/**
 * This is a class to test the methods of GameModel class
 */
public class GameModelTest {
	GameModelNew d_Game = new GameModelNew();
	GameEngine d_Ge;
	CommandPrompt d_CpView;
	ArrayList<Player> d_Check;
	List<String> d_Names;
	ArrayList<Player> d_List;
	List<String> d_CheckNames;
	Player d_C1,d_C2;
	Phase d_P;
	private Map d_Map;
	/**
	 * To set up the context for test cases
	 * @throws Exception relevant for the map creation phase
	 */
	@Before
	public void setTestContext() throws Exception {
		d_CpView= new CommandPrompt();
		d_Ge= new GameEngine(d_CpView,d_Game);
		d_Check =  new ArrayList<Player>();
		d_Names = new ArrayList<>();
		d_CheckNames = new ArrayList<>();
		d_Map = new Map();
		d_Map.addContinent("asia","1");
		d_Map.addCountry("india","asia");
		d_Map.addCountry("china","asia");
		d_Map.addCountry("japan","asia");
		d_Game = new GameModelNew(d_Map);
		d_Game.addPlayer("raj","human");
		d_Game.addPlayer("kumar","human");
		d_C1 = new Player("raj",d_Game);
		d_C2 = new Player("kumar",d_Game);
		d_Check.add(d_C1);
		d_Check.add(d_C2);
	}

	/**
	 * This test case d_Check the functionality of addplayer method
	 * 
	 */
	@Test 
	public void testAddPlayer() {
		for(Player l_Player:d_Check) {
			d_Names.add(l_Player.getPlayerName());
		}
		for(Player l_Player:d_Game.getAllPlayers()) {
			d_CheckNames.add(l_Player.getPlayerName());
		}
		assertEquals(d_CheckNames,d_Names);
	}
	/**
	 * To test addPlayer() and check if player already exists or not
	 */
	@Test 
	public void testAddPlayerAlreadyExist() {
		String l_ExpectedMessage="Please enter a differnt Player name as this name already exists";
		String l_ActualMessage = "";
		try {
			d_Game.addPlayer("raj","human");
		} catch (Exception p_E) {
			l_ActualMessage = p_E.getMessage();
		}
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}

	/**
	 * To test addPlayer() to see if maximum players have been reached or not
	 */
	@Test 
	public void testAddPlayerReachedMax() {
		String l_ExpectedMessage="Reached Max Number of Players can be added to the game";
		String l_ActualMessage = "";
		try {
			d_Game.addPlayer("zeal","human");
			d_Game.addPlayer("alpha","human");
			d_Game.addPlayer("beta","human");
		} catch (Exception p_E) {
			l_ActualMessage = p_E.getMessage();
		}
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}


	/**
	 * This test case d_Check the functionality of RemovePlayer method
	 * @throws Exception Player to be removed does not exist
	 */
	@Test 
	public void testRemovePlayer() throws Exception {
		d_Game.removePlayer("raj");
		for(Player l_Player:d_Check) {
			d_Names.add(l_Player.getPlayerName());
		}
		for(Player l_Player:d_Game.getAllPlayers()) {
			d_CheckNames.add(l_Player.getPlayerName());
		}
		assertFalse(d_CheckNames.equals(d_Names));
	}

	/**
	 * To test removePlayer() and check if Player exists or not
	 */
	@Test 
	public void testRemovePlayerNotExists() {
		String l_ExpectedMessage="This Player does not exists";
		String l_ActualMessage = "";
		try {
			d_Game.removePlayer("shilpa");
		}catch (Exception p_E) {
			l_ActualMessage = p_E.getMessage();
		}
		assertEquals(l_ExpectedMessage,l_ActualMessage);

	}

	/**
	 * This test case d_Check the functionality of AssignReinforcements method
	 * @throws Exception To check if the number of players is less than number of countries or not
	 */
	@Test 
	public void testAssignReinforcements() throws Exception {
		d_Game.assignReinforcementArmies();
		for(Player l_Player:d_Game.getAllPlayers()) {
			int l_Value=l_Player.getPlayerArmies();
			assertTrue(3<=l_Value);
		}
	}
	@Test 
	public void testSaveLoadGamePLayercheck() throws Exception {
		GameModelNew l_Actual = null;
		int l_Expected=3;
		int l_ActualValue=0;
		d_Game.startUpPhase();
		String l_ExpectedMessage="Countrysize is 0";
		d_Game.saveGame("SaveTest.txt");
		l_Actual=d_Game.loadGame("SaveTest.txt");
		l_ActualValue=l_Actual.getAllPlayers().size();
		assertEquals(l_Expected,l_ActualValue);
	}
	
}