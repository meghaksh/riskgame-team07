package org.soen6441.utility.state;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.soen6441.controller.GameEngine;
import org.soen6441.model.GameModelNew;
import org.soen6441.view.CommandPrompt;

public class ExecuteOrderTest {
	
	CommandPrompt d_CpView;
	GameModelNew d_GameModel;
	GameEngine d_Ge;
	ExecuteOrder d_Eo;
	Phase d_P;
	
	@Before
	public void setTestContext() throws Exception {
		
		d_CpView= new CommandPrompt();
		d_GameModel=new GameModelNew();
		d_Ge= new GameEngine(d_CpView,d_GameModel);
		d_Eo= new ExecuteOrder(d_Ge,d_CpView);
	}

	@Test
	public void testEditMap() {
		String l_ExpectedMessage="Invalid command in state ";
		String l_ActualMessage = "";
		d_Eo.editMap("map5");
		d_P=d_Ge.getPhase();
		l_ActualMessage=d_Eo.d_Leb.getResult();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testEditCountry() {
		String l_ExpectedMessage="Invalid command in state ";
		String l_ActualMessage = "";
		d_Eo.editCountry("india","asia");
		l_ActualMessage=d_Eo.d_Leb.getResult();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testEditContinent() {
		String l_ExpectedMessage="Invalid command in state ";
		String l_ActualMessage = "";
		d_Eo.editContinent("asia", "1");
		l_ActualMessage=d_Eo.d_Leb.getResult();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testEditNeighbor() {
		String l_ExpectedMessage="Invalid command in state ";
		String l_ActualMessage = "";
		d_Eo.editNeighbor("india", "china");
		l_ActualMessage=d_Eo.d_Leb.getResult();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testSaveMap() {
		String l_ExpectedMessage="Invalid command in state ";
		String l_ActualMessage = "";
		d_Eo.saveMap("map");
		l_ActualMessage=d_Eo.d_Leb.getResult();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testLoadMap() {
		String l_ExpectedMessage="Invalid command in state ";
		String l_ActualMessage = "";
		d_Eo.loadMap("map");
		l_ActualMessage=d_Eo.d_Leb.getResult();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testAddPlayers() {
		String l_ExpectedMessage="Invalid command in state ";
		String l_ActualMessage = "";
		d_Eo.addPlayers("zeal", "raj");
		l_ActualMessage=d_Eo.d_Leb.getResult();
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testAssignCountries() {
		String l_ExpectedMessage="Invalid command in state ";
		String l_ActualMessage = "";
		d_Eo.assignCountries();
		l_ActualMessage=d_Eo.d_Leb.getResult();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testValidateMap() {
		String l_ExpectedMessage="Invalid command in state ";
		String l_ActualMessage = "";
		d_Eo.validateMap();
		l_ActualMessage=d_Eo.d_Leb.getResult();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}

}
