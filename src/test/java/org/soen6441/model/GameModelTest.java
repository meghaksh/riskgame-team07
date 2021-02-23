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
		String l_command="deploy india 4";
		String[] l_CommandArray = l_command.split(" ");
		String l_country=l_CommandArray[1];
		d_Game.startUpPhase();
		boolean l_flag=false;
		String l_result="";
		for(Player l:d_Game.getAllPlayers()) {
			if(l.getPlayerName().equalsIgnoreCase("raj")) {	
				l.setPlayerArmies(Integer.parseInt(l_CommandArray[2]));
				l.setOrder(l_command);

				for(Country l_C:l.getCountryList()) {
					if(l_C.getCountryName().equalsIgnoreCase(l_country)) {
						l_flag=true;
					}
				}
				l.issue_order();
				l_result=l.getResult();
			}
		}
		assertTrue(l_flag);
	}
	
	/**
	 * This test case d_Check the functionality of whether player contains a enough armies to deploy
	 * This a test case in IssueOrder Method
	 * @throws Exception
	 */
	@Test 
	////(expected= Exception.class)
	public void testIssueOrderSized_Check() throws Exception {
		String l_command="deploy india 3";
		String[] l_CommandArray = l_command.split(" ");
		String l_country=l_CommandArray[1];
		d_Game.startUpPhase();
		boolean l_flag=false;
		boolean l_flag_armies=false;
		String l_result="";
		for(Player l:d_Game.getAllPlayers()) {
			if(l.getPlayerName().equalsIgnoreCase("raj")) {	
				l.setPlayerArmies(3);
				if(l.getPlayerArmies()>=Integer.parseInt(l_CommandArray[2])) {	
					l_flag_armies=true;
					l.setOrder(l_command);
					for(Country l_C:l.getCountryList()) {
						if(l_C.getCountryName().equalsIgnoreCase(l_country)) {
							l_flag=true;
						}
					}
					l.issue_order();
					l_result=l.getResult();
				}
			}
			assertTrue(l_flag_armies);
		}
	}
}

//	@Test (expected= Exception.class)
//	public void testAddPlayer() throws Exception {
//		
//		String l_playerName = "raj";
//		String l_playerName1 ="raj";
//		
//		GameModelNew l_game = new GameModelNew();
//		
//		
//		l_game.addPlayer(l_playerName);
//		l_game.addPlayer(l_playerName1);
//	 }
//	@Test (expected= Exception.class)
//	public void testremovePlayer() throws Exception {
//		
//		String l_playerName = "raj";
//		String l_playerName1 ="raj";
//		
//		GameModelNew l_game = new GameModelNew();
//		
//		
//		l_game.removePlayer(l_playerName);
//		l_game.removePlayer(l_playerName1);
//	 }

