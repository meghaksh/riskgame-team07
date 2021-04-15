package org.soen6441.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.soen6441.model.GameModelNew;
import org.soen6441.view.CommandPrompt;

public class GameEngineTest {
	
	CommandPrompt d_CpView;
	GameModelNew d_GameModel;
	GameEngine d_GameEngine;
	
	
	@Before
	public void setTestContext() {
		d_CpView = new CommandPrompt();
		d_GameModel = new GameModelNew();
		d_GameEngine = new GameEngine(d_CpView, d_GameModel);
	}
	
	
	
	
	@Test
	public void testTournamentMode() {
		
	}



}
