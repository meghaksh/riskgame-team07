package org.soen6441.model;
import org.soen6441.model.*;			import static org.junit.Assert.assertEquals;											
import java.util.ArrayList;				import java.util.List;import static org.junit.Assert.*;
import org.junit.Before;				import org.junit.Test;
public class GameModelTest {
	GameModelNew d_game = new GameModelNew();
	ArrayList<Player> check;
	List<String> l_Names;
	ArrayList<Player> d_list;
	List<String> l_checkNames;
	Player c1,c2;
	private Map d_map;
	@Before
	public void setTestContext() throws Exception
	{

		check =  new ArrayList<Player>();
		l_Names = new ArrayList<>();
		l_checkNames = new ArrayList<>();
		d_map = new Map();
		d_map.addContinent("asia","1");
		d_map.addCountry("india","asia");
		d_game = new GameModelNew(d_map);
		d_game.addPlayer("raj");
		d_game.addPlayer("kumar");
		c1 = new Player("raj");
		c2 = new Player("kumar");
		check.add(c1);
		check.add(c2);
	}

	@Test 
	//	(expected= Exception.class)
	public void testAddPlayer() throws Exception {
		for(Player l:check)
		{
			l_Names.add(l.getPlayerName());
		}
		for(Player l:d_game.getAllPlayers())
		{
			l_checkNames.add(l.getPlayerName());
		}
		assertEquals(l_checkNames,l_Names);

	}
	
	@Test 
	//	(expected= Exception.class)
	public void testRemovePlayer() throws Exception {
		d_game.removePlayer("raj");
		for(Player l:check)
		{
			l_Names.add(l.getPlayerName());
		}
		for(Player l:d_game.getAllPlayers())
		{
			l_checkNames.add(l.getPlayerName());
		}
		assertFalse(l_checkNames.equals(l_Names));
	}
	
	@Test 
	////	(expected= Exception.class)
	public void testAssignReinforcements() throws Exception {
		d_game.startUpPhase();
		for(Player l:d_game.getAllPlayers())
		{
			int value=l.getPlayerArmies();
			assertTrue(3<=value);
		}
	}
	
	@Test 
	////(expected= Exception.class)
	public void testIssueOrder() throws Exception {

		String l_command="deploy india 4";
		String[] l_CommandArray = l_command.split(" ");
		String l_country=l_CommandArray[1];
		d_game.startUpPhase();
		boolean l_flag=false;
		String l_result="";
		for(Player l:d_game.getAllPlayers())
		{
			if(l.getPlayerName().equalsIgnoreCase("raj"))
			{	
				l.setPlayerArmies(Integer.parseInt(l_CommandArray[2]));
				l.setOrder(l_command);

				for(Country l_C:l.getCountryList())
				{
					if(l_C.getCountryName().equalsIgnoreCase(l_country))
					{
						l_flag=true;
					}
				}
				l.issue_order();
				l_result=l.getResult();
			}
		}
		assertTrue(l_flag);
	}
	@Test 
	////(expected= Exception.class)
	public void testIssueOrderSizecheck() throws Exception {

		String l_command="deploy india 3";
		String[] l_CommandArray = l_command.split(" ");
		String l_country=l_CommandArray[1];
		d_game.startUpPhase();
		boolean l_flag=false;
		boolean l_flag_armies=false;
		String l_result="";
		for(Player l:d_game.getAllPlayers())
		{
			if(l.getPlayerName().equalsIgnoreCase("raj"))
			{	
				l.setPlayerArmies(3);
				if(l.getPlayerArmies()>=Integer.parseInt(l_CommandArray[2]))
				{	
					l_flag_armies=true;
					l.setOrder(l_command);

					for(Country l_C:l.getCountryList())
					{
						if(l_C.getCountryName().equalsIgnoreCase(l_country))
						{
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

