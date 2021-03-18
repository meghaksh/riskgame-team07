package org.soen6441.utility.state;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.soen6441.controller.GameEngine;
import org.soen6441.model.GameModelNew;
import org.soen6441.model.Map;
import org.soen6441.model.Player;
import org.soen6441.observerpattern.LogEntryBuffer;
import org.soen6441.view.CommandPrompt;

public class EditTest {
	CommandPrompt l_CpView;
	GameModelNew l_GameModel;
	GameEngine p_ge;
	Edit ed;
	Phase p;
	
	@Before
	public void setTestContext() throws Exception {
		l_CpView= new CommandPrompt();
		l_GameModel=new GameModelNew();
		p_ge= new GameEngine(l_CpView,l_GameModel);
		ed= new Edit(p_ge,l_CpView);
		
	}
	@Test
	public void testLoadMap() {
		String l_ExpectedMessage="Startup";
		String l_ActualMessage = "";
		ed.loadMap("map5");
		p=p_ge.getPhase();
		l_ActualMessage=p.getPhaseName();
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testAssignCountries() {
		String l_ExpectedMessage="Invalid command in state ";
		String l_ActualMessage = "";
		ed.assignCountries();
		p=p_ge.getPhase();
		l_ActualMessage=ed.leb.getResult();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testAddPlayers() {
		String l_ExpectedMessage="Invalid command in state ";
		String l_ActualMessage = "";
		ed.addPlayers("zeal", "raj");
		l_ActualMessage=ed.leb.getResult();
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	}
