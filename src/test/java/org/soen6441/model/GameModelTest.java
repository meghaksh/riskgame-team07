package org.soen6441.model;
import org.soen6441.model.*;			import static org.junit.Assert.assertEquals;											
import java.util.ArrayList;				import java.util.List;import static org.junit.Assert.*;
import org.junit.Before;				import org.junit.Test;
public class GameModelTest {
	GameModelNew d_Game = new GameModelNew();
	ArrayList<Player> d_Check;
	List<String> l_Names;
	ArrayList<Player> d_List;
	List<String> l_d_CheckNames;
	Player c1,c2;
	private Map d_Map;
	@Before
	public void setTestContext() throws Exception {

		d_Check =  new ArrayList<Player>();
		l_Names = new ArrayList<>();
		l_d_CheckNames = new ArrayList<>();
		d_Map = new Map();
		d_Map.addContinent("asia","1");
		d_Map.addCountry("india","asia");
		d_Map.addCountry("china","asia");
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
	 * @throws Exception
	 */
	@Test 
	//	(expected= Exception.class)
	public void testAddPlayer() throws Exception {
		for(Player l:d_Check) {
			l_Names.add(l.getPlayerName());
		}
		for(Player l:d_Game.getAllPlayers()) {
			l_d_CheckNames.add(l.getPlayerName());
		}
		assertEquals(l_d_CheckNames,l_Names);
	}
	
	/**
	 * This test case d_Check the functionality of RemovePlayer method
	 * @throws Exception
	 */
	@Test 
	//	(expected= Exception.class)
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
	 * This test case d_Check the functionality of AssignReinforcements method
	 * @throws Exception
	 */
	@Test 
	////	(expected= Exception.class)
	public void testAssignReinforcements() throws Exception {
		d_Game.startUpPhase();
		for(Player l:d_Game.getAllPlayers()) {
			int value=l.getPlayerArmies();
			assertTrue(3<=value);
			
		}
	}
	
	/**
	 * This test case d_Check the functionality of whether player contains a particular country where he is deploying
	 * This a test case in IssueOrder Method
	 * @throws Exception
	 */
	@Test 
	////(expected= Exception.class)
	public void testIssueOrder() throws Exception {
		String l_command="deploy india 3";
		String l_expected="\nraj : Your armies have become zero now!!. You will not be able to issue an order";
		c1.setOrder(l_command);
		c1.setPlayerArmies(3);
		c1.addCountry(d_Map.getCountryList().get(0));
		c1.issue_order();
		String l_Result=c1.getResult();
		System.out.println(l_Result);
		assertEquals(l_expected,l_Result);
		
		String l_command1="deploy kenya 3";
		String l_expected1="\nThis country kenya doesnot belongs to raj";
		c1.setPlayerArmies(3);
		c1.setOrder(l_command1);
		c1.issue_order();
		String l_Result1=c1.getResult();
		assertEquals(l_expected1,l_Result1);
		
		
	}
	}