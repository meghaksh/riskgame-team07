package org.soen6441.utility.state;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import org.soen6441.controller.GameEngine;
import org.soen6441.model.GameModelNew;
import org.soen6441.view.CommandPrompt;

public class EditTest {
	CommandPrompt d_CpView;
	GameModelNew d_GameModel;
	GameEngine d_Ge;
	Edit d_Ed;
	Phase d_P;
	
	@Before
	public void setTestContext() throws Exception {
		d_CpView= new CommandPrompt();
		d_GameModel=new GameModelNew();
		d_Ge= new GameEngine(d_CpView,d_GameModel);
		d_Ed= new Edit(d_Ge,d_CpView);
		
	}
	@Test
	public void testLoadMap() {
		String l_ExpectedMessage="Startup";
		String l_ActualMessage = "";
		d_Ed.loadMap("map5");
		d_P=d_Ge.getPhase();
		l_ActualMessage=d_P.getPhaseName();
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testAssignCountries() {
		String l_ExpectedMessage="Invalid command in state ";
		String l_ActualMessage = "";
		d_Ed.assignCountries();
		d_P=d_Ge.getPhase();
		l_ActualMessage=d_Ed.d_Leb.getResult();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testAddPlayers() {
		String l_ExpectedMessage="Invalid command in state ";
		String l_ActualMessage = "";
		d_Ed.addPlayers("zeal", "raj");
		l_ActualMessage=d_Ed.d_Leb.getResult();
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	}
