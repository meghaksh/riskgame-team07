package org.soen6441.utility.state;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.soen6441.controller.GameEngine;
import org.soen6441.model.GameModelNew;
import org.soen6441.view.CommandPrompt;

public class ExecuteOrderTest {
	
	CommandPrompt l_CpView;
	GameModelNew l_GameModel;
	GameEngine p_ge;
	ExecuteOrder eo;
	Phase p;
	
	@Before
	public void setTestContext() throws Exception {
		
		l_CpView= new CommandPrompt();
		l_GameModel=new GameModelNew();
		p_ge= new GameEngine(l_CpView,l_GameModel);
		eo= new ExecuteOrder(p_ge,l_CpView);
	}

	@Test
	public void testEditMap() {
		String l_ExpectedMessage="Invalid command in state ";
		String l_ActualMessage = "";
		eo.editMap("map5");
		p=p_ge.getPhase();
		l_ActualMessage=eo.leb.getResult();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testEditCountry() {
		String l_ExpectedMessage="Invalid command in state ";
		String l_ActualMessage = "";
		eo.editCountry("india","asia");
		l_ActualMessage=eo.leb.getResult();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testEditContinent() {
		String l_ExpectedMessage="Invalid command in state ";
		String l_ActualMessage = "";
		eo.editContinent("asia", "1");
		l_ActualMessage=eo.leb.getResult();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testEditNeighbor() {
		String l_ExpectedMessage="Invalid command in state ";
		String l_ActualMessage = "";
		eo.editNeighbor("india", "china");
		l_ActualMessage=eo.leb.getResult();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testSaveMap() {
		String l_ExpectedMessage="Invalid command in state ";
		String l_ActualMessage = "";
		eo.saveMap("map");
		l_ActualMessage=eo.leb.getResult();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testLoadMap() {
		String l_ExpectedMessage="Invalid command in state ";
		String l_ActualMessage = "";
		eo.loadMap("map");
		l_ActualMessage=eo.leb.getResult();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testAddPlayers() {
		String l_ExpectedMessage="Invalid command in state ";
		String l_ActualMessage = "";
		eo.addPlayers("zeal", "raj");
		l_ActualMessage=eo.leb.getResult();
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testAssignCountries() {
		String l_ExpectedMessage="Invalid command in state ";
		String l_ActualMessage = "";
		eo.assignCountries();
		l_ActualMessage=eo.leb.getResult();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testValidateMap() {
		String l_ExpectedMessage="Invalid command in state ";
		String l_ActualMessage = "";
		eo.validateMap();
		l_ActualMessage=eo.leb.getResult();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}

}
