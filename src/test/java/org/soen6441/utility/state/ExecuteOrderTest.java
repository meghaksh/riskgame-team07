package org.soen6441.utility.state;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.soen6441.controller.GameEngine;
import org.soen6441.model.GameModelNew;
import org.soen6441.view.CommandPrompt;

/**
 * This class tests the methods written inside ExecuteOrder phase class. 
 */
public class ExecuteOrderTest {
	/**
	 * This is the command prompt object
	 */
	CommandPrompt d_CpView;
	/**
	 * This is the game model new object
	 */
	GameModelNew d_GameModel;
	/**
	 * This is the game engine object
	 */
	GameEngine d_Ge;
	/**
	 * This is the ExecuteOrder class object
	 */
	ExecuteOrder d_Eo;
	/**
	 * This is the Phase class object
	 */
	Phase d_P;
	
	/**
	 * This method sets the context before each method is executed. 
	 * @throws Exception any exception that is thrown while setting up the context. 
	 */
	@Before
	public void setTestContext() throws Exception {
		
		d_CpView= new CommandPrompt();
		d_GameModel=new GameModelNew();
		d_Ge= new GameEngine(d_CpView,d_GameModel);
		d_Eo= new ExecuteOrder(d_Ge,d_CpView);
	}

	/**
	 * This method tests that editmap method is invalid for the Execute Order phase. 
	 */
	@Test
	public void testEditMap() {
		try {
			String l_ExpectedMessage="Invalid command in state ";
			String l_ActualMessage = "";
			d_Eo.editMap("map5");
			d_P=d_Ge.getPhase();
			l_ActualMessage=d_Eo.d_Leb.getResult();
			assertEquals(l_ExpectedMessage,l_ActualMessage);
		}catch(Exception p_Exp) {}
		
	}
	
	/**
	 * This method tests that editCountry method is invalid for the execute phase. 
	 */
	@Test
	public void testEditCountry() {
		try {
			String l_ExpectedMessage="Invalid command in state ";
			String l_ActualMessage = "";
			d_Eo.editCountry("india","asia");
			l_ActualMessage=d_Eo.d_Leb.getResult();
			assertEquals(l_ExpectedMessage,l_ActualMessage);
		}catch(Exception p_Exp) {}
	}
	
	/**
	 * This method tests that editContinent method is invalid for the execute phase.
	 */
	@Test
	public void testEditContinent() {
		try {
			String l_ExpectedMessage="Invalid command in state ";
			String l_ActualMessage = "";
			d_Eo.editContinent("asia", "1");
			l_ActualMessage=d_Eo.d_Leb.getResult();
			assertEquals(l_ExpectedMessage,l_ActualMessage);
		}catch(Exception p_Exp) {}
	}
	
	/**
	 * This method tests that editNeighbor method is invalid for the execute phase.
	 */
	@Test
	public void testEditNeighbor() {
		try {
			String l_ExpectedMessage="Invalid command in state ";
			String l_ActualMessage = "";
			d_Eo.editNeighbor("india", "china");
			l_ActualMessage=d_Eo.d_Leb.getResult();
			assertEquals(l_ExpectedMessage,l_ActualMessage);
		}catch(Exception p_Exp) {}
	}
	/**
	 * This method tests that savemap method is invalid for the execute phase.
	 */
	@Test
	public void testSaveMap() {
		try {
			String l_ExpectedMessage="Invalid command in state ";
			String l_ActualMessage = "";
			d_Eo.saveMap("map");
			l_ActualMessage=d_Eo.d_Leb.getResult();
			assertEquals(l_ExpectedMessage,l_ActualMessage);
		}catch(Exception p_Exp) {}
	}
	
	/**
	 * This method tests that loadmap is invalid for the execute phase.
	 */
	@Test
	public void testLoadMap() {
		try {
			String l_ExpectedMessage="Invalid command in state ";
			String l_ActualMessage = "";
			d_Eo.loadMap("map");
			l_ActualMessage=d_Eo.d_Leb.getResult();
			assertEquals(l_ExpectedMessage,l_ActualMessage);
		}catch(Exception p_Exp) {}
	}
	
	/**
	 * This method tests that addPlayer method is invalid for the execute order phase.
	 */
	@Test
	public void testAddPlayers() {
		try {
			String l_ExpectedMessage="Invalid command in state ";
			String l_ActualMessage = "";
			d_Eo.addPlayers("zeal", "raj");
			l_ActualMessage=d_Eo.d_Leb.getResult();
			assertEquals(l_ExpectedMessage,l_ActualMessage);
		}catch(Exception p_Exp) {}
	}
	
	/**
	 * This method tests that assigncountris method is invalid for the execute order phase.
	 */
	@Test
	public void testAssignCountries() {
		try {
			String l_ExpectedMessage="Invalid command in state ";
			String l_ActualMessage = "";
			d_Eo.assignCountries();
			l_ActualMessage=d_Eo.d_Leb.getResult();
			assertEquals(l_ExpectedMessage,l_ActualMessage);
		}catch(Exception p_Exp) {}
	}
	
	/**
	 * This method tests that validatemap method is invalid for the execute phase. 
	 */
	@Test
	public void testValidateMap() {
		try {
			String l_ExpectedMessage="Invalid command in state ";
			String l_ActualMessage = "";
			d_Eo.validateMap();
			l_ActualMessage=d_Eo.d_Leb.getResult();
			assertEquals(l_ExpectedMessage,l_ActualMessage);
		}catch(Exception p_Exp) {}
		
	}

}
