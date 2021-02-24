package org.soen6441.model;
import org.soen6441.model.*;			import static org.junit.Assert.assertEquals;											
import java.util.ArrayList;				import java.util.List;import static org.junit.Assert.*;
import org.junit.Before;				import org.junit.Test;

/**
 * 
 * This is a class to test the methods of GameModel class
 *
 */
public class GameModelTest {
	GameModelNew d_Game = new GameModelNew();
	ArrayList<Player> d_Check;
	List<String> l_Names;
	ArrayList<Player> d_List;
	List<String> l_d_CheckNames;
	Player c1,c2;
	private Map d_Map;
	/**
	 * To set up the context for test cases
	 * @throws Exception relevant for the map creation phase
	 */
	@Before
	public void setTestContext() throws Exception {

		d_Check =  new ArrayList<Player>();
		l_Names = new ArrayList<>();
		l_d_CheckNames = new ArrayList<>();
		d_Map = new Map();
		d_Map.addContinent("asia","1");
		d_Map.addCountry("india","asia");
		d_Map.addCountry("china","asia");
		d_Map.addCountry("japan","asia");
		d_Game = new GameModelNew(d_Map);
		d_Game.addPlayer("raj");
		d_Game.addPlayer("kumar");
		c1 = new Player("raj",d_Game);
		c2 = new Player("kumar",d_Game);
		d_Check.add(c1);
		d_Check.add(c2);
	}

	/**
	 * This test case d_Check the functionality of addplayer method
	 * 
	 */
	@Test 
	public void testAddPlayer() {
		for(Player l:d_Check) {
			l_Names.add(l.getPlayerName());
		}
		for(Player l:d_Game.getAllPlayers()) {
			l_d_CheckNames.add(l.getPlayerName());
		}
		assertEquals(l_d_CheckNames,l_Names);
	}
	/**
	 * To test addPlayer() and check if player already exists or not
	 */
	@Test 
	public void testAddPlayerAlreadyExist() {
		String l_ExpectedMessage="Please enter a differnt Player name as this name already exists";
		String l_ActualMessage = "";
		try {
			d_Game.addPlayer("raj");
		} catch (Exception e) {
			l_ActualMessage = e.getMessage();
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
			d_Game.addPlayer("zeal");
			d_Game.addPlayer("alpha");

		} catch (Exception e) {
			l_ActualMessage = e.getMessage();
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
		for(Player l:d_Check) {
			l_Names.add(l.getPlayerName());
		}
		for(Player l:d_Game.getAllPlayers()) {
			l_d_CheckNames.add(l.getPlayerName());
		}
		assertFalse(l_d_CheckNames.equals(l_Names));
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
		}catch (Exception e) {
			l_ActualMessage = e.getMessage();
		}
		assertEquals(l_ExpectedMessage,l_ActualMessage);

	}

	/**
	 * This test case d_Check the functionality of AssignReinforcements method
	 * @throws Exception To check if the number of players is less than number of countries or not
	 */
	@Test 
	public void testAssignReinforcements() throws Exception {
		d_Game.startUpPhase();
		for(Player l:d_Game.getAllPlayers()) {
			int value=l.getPlayerArmies();
			assertTrue(3<=value);

		}
	}

	/**
	 * This test case is to check functionality of whether player has deployed all the armies that are assigned to player
	 * 
	 */
	@Test 
	public void testIssueOrder() {
		String l_Command="deploy india 3";
		String l_Expected="\nraj : Your armies have become zero now!!. You will not be able to issue an order";
		c1.setOrder(l_Command);
		c1.setPlayerArmies(3);
		c1.addCountry(d_Map.getCountryList().get(0));
		c1.issue_order();
		String l_Result=c1.getResult();
		assertEquals(l_Expected,l_Result);
	}
	/**
	 * This test cases is to check whether player holds that country that he is trying to deploy
	 * 
	 */
	@Test 
	public void testIssueOrder1() {

		String l_Command1="deploy kenya 3";
		String l_Expected1="\nThis country kenya doesnot belongs to raj";
		c1.setPlayerArmies(3);
		c1.addCountry(d_Map.getCountryList().get(0));
		c1.setOrder(l_Command1);
		c1.issue_order();
		String l_Result1=c1.getResult();
		assertEquals(l_Expected1,l_Result1);
	}
	/**
	 * This test cases is to check whether no of armies deployed less than the player has
	 * 
	 */
	@Test 
	public void testIssueOrder2() {

		String l_Command2="deploy india 2";
		String l_Expected2="\norder deploy india 2 added to list of raj";
		c1.setPlayerArmies(3);
		c1.addCountry(d_Map.getCountryList().get(0));
		c1.setOrder(l_Command2);
		c1.issue_order();
		String l_Result2=c1.getResult();
		assertEquals(l_Expected2,l_Result2);
	}
	/**
	 * This test cases is to check whether no of armies deployed more than the player has
	 * 
	 */
	@Test 
	public void testIssueOrderArmySize() {

		String l_Command3="deploy india 4";
		String l_Expected3="\nraj ; you have only 3 number of armies! Please enter the next order accordingly";
		c1.setPlayerArmies(3);
		c1.addCountry(d_Map.getCountryList().get(0));
		c1.setOrder(l_Command3);
		c1.issue_order();
		String l_Result3=c1.getResult();
		assertEquals(l_Expected3,l_Result3);
	}
}