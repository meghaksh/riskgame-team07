package org.soen6441.model;
import org.soen6441.model.*;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
public class GameModelTest {
	GameModelNew d_game = new GameModelNew();
	ArrayList<Player> check;
	List<String> l_Names;
	ArrayList<Player> d_list;
	List<String> l_checkNames;
	
	private Map d_map;
	@Before
	public void setTestContext() throws Exception
	{
		
		check =  new ArrayList<Player>();
		l_Names = new ArrayList<>();
		l_checkNames = new ArrayList<>();
		
		d_map = new Map();
		d_map.AddCountry("india","asia");
		d_map.AddCountry("china","asia");
		d_map.AddContinent("asia","0");
	}
	
	@Test 
//	(expected= Exception.class)
	public void testAddPlayer() throws Exception {
		
		Player c1 = new Player("raj");
		Player c2 = new Player("kumar");
		check.add(c1);
		check.add(c2);
		d_game.addPlayer("raj");
		d_game.addPlayer("kumar");
		for(Player l:check)
		{
		l_Names.add(l.getPlayerName());
		}
		for(Player l:d_game.getAllPlayers())
		{
		l_checkNames.add(l.getPlayerName());
		}
		//assertEquals(check,d_game.getAllPlayers());
		assertEquals(l_checkNames,l_Names);
		
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
}
