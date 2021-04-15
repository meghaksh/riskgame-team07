package org.soen6441.controller;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.soen6441.model.GameModelNew;
import org.soen6441.view.CommandPrompt;

/**
 * 
 * To test the tournament mode of GameEngine Class
 *
 */
public class GameEngineTest {

	CommandPrompt d_CpView;
	GameModelNew d_GameModel;
	GameEngine d_GameEngine;
	String l_InputString;

	/**
	 * To set the context before each test case
	 * @throws Exception for the methods implemented
	 */
	@Before
	public void setTestContext() throws Exception {
		d_CpView = new CommandPrompt();
		d_GameModel = new GameModelNew();
		d_GameEngine = new GameEngine(d_CpView, d_GameModel);
	}

	/**
	 * To test if the specified map file mentioned in the tournament mode exists or not 
	 */
	@Test
	public void testTournamentFileNotExists()  {
		String l_ActualMessage="";
		String l_ExpectedMessage = "File does not Exists";

		l_InputString = "tournament -M map9,map99 -P benevolent,aggressive -G 2 -D 10";
		try {
			d_GameEngine.tournament(l_InputString);
		} catch (Exception e) {
			l_ActualMessage = e.getMessage();
		}
		assertEquals(l_ExpectedMessage, l_ActualMessage);
	}



}
