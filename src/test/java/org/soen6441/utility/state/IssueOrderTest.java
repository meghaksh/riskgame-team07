package org.soen6441.utility.state;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.soen6441.controller.GameEngine;
import org.soen6441.model.GameModelNew;
import org.soen6441.view.CommandPrompt;

/**
 * This class tests the method return in the IssueOrder phase class. 
 */
public class IssueOrderTest {
	
	CommandPrompt d_CpView;
	GameModelNew d_GameModel;
	GameEngine d_Ge;
	IssueOrder d_Io;
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
		d_Io= new IssueOrder(d_Ge,d_CpView);
	}

	/**
	 * Tests that editmap is invalid for issue order phase.
	 */
	@Test
	public void testEditMap() {
		String l_ExpectedMessage="Invalid command in state IssueOrder";
		String l_ActualMessage = "";
		d_Io.editMap("map5");
		String [] l_ActualMessageArray=d_Io.d_Vw.getCommandAcknowledgement().split("/n");
		l_ActualMessage = l_ActualMessageArray[l_ActualMessageArray.length -1].trim();
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	/**
	 * Tests that editCountry is invalid for issue order phase.
	 */
	@Test
	public void testEditCountry() {
		String l_ExpectedMessage="Invalid command in state IssueOrder";
		String l_ActualMessage = "";
		d_Io.editCountry("india","asia");
		String [] l_ActualMessageArray=d_Io.d_Vw.getCommandAcknowledgement().split("/n");
		l_ActualMessage = l_ActualMessageArray[l_ActualMessageArray.length -1].trim();
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	/**
	 * Tests that editContinent is invalid for issue order phase.
	 */
	@Test
	public void testEditContinent() {
		String l_ExpectedMessage="Invalid command in state IssueOrder";
		String l_ActualMessage = "";
		d_Io.editContinent("asia", "1");
		String [] l_ActualMessageArray=d_Io.d_Vw.getCommandAcknowledgement().split("/n");
		l_ActualMessage = l_ActualMessageArray[l_ActualMessageArray.length -1].trim();
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	/**
	 * Tests that editNeighbor method is invalid for issue order phase.
	 */
	@Test
	public void testEditNeighbor() {
		String l_ExpectedMessage="Invalid command in state IssueOrder";
		String l_ActualMessage = "";
		d_Io.editNeighbor("india", "china");
		String [] l_ActualMessageArray=d_Io.d_Vw.getCommandAcknowledgement().split("/n");
		l_ActualMessage = l_ActualMessageArray[l_ActualMessageArray.length -1].trim();
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	/**
	 * Tests that savemap method is invalid for issue order phase.
	 */
	@Test
	public void testSaveMap() {
		String l_ExpectedMessage="Invalid command in state IssueOrder";
		String l_ActualMessage = "";
		d_Io.saveMap("map");
		String [] l_ActualMessageArray=d_Io.d_Vw.getCommandAcknowledgement().split("/n");
		l_ActualMessage = l_ActualMessageArray[l_ActualMessageArray.length -1].trim();
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	/**
	 * Tests that loadmap method is invalid for issue order phase.
	 */
	@Test
	public void testLoadMap() {
		String l_ExpectedMessage="Invalid command in state IssueOrder";
		String l_ActualMessage = "";
		d_Io.loadMap("map");
		String [] l_ActualMessageArray=d_Io.d_Vw.getCommandAcknowledgement().split("/n");
		l_ActualMessage = l_ActualMessageArray[l_ActualMessageArray.length -1].trim();
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	/**
	 * Tests that addplayer method is invalid for issue order phase.
	 */
	@Test
	public void testAddPlayers() {
		String l_ExpectedMessage="Invalid command in state IssueOrder";
		String l_ActualMessage = "";
		d_Io.addPlayers("zeal", "raj");
		String [] l_ActualMessageArray=d_Io.d_Vw.getCommandAcknowledgement().split("/n");
		l_ActualMessage = l_ActualMessageArray[l_ActualMessageArray.length -1].trim();
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	/**
	 * Tests that assigncountries method is invalid for issue order phase.
	 */
	@Test
	public void testAssignCountries() {
		String l_ExpectedMessage="Invalid command in state IssueOrder";
		String l_ActualMessage = "";
		d_Io.assignCountries();
		String [] l_ActualMessageArray=d_Io.d_Vw.getCommandAcknowledgement().split("/n");
		l_ActualMessage = l_ActualMessageArray[l_ActualMessageArray.length -1].trim();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	/**
	 * Tests that validatemap method is invalid for issue order phase.
	 */
	@Test
	public void testValidateMap() {
		String l_ExpectedMessage="Invalid command in state IssueOrder";
		String l_ActualMessage = "";
		d_Io.validateMap();
		String [] l_ActualMessageArray=d_Io.d_Vw.getCommandAcknowledgement().split("/n");
		l_ActualMessage = l_ActualMessageArray[l_ActualMessageArray.length -1].trim();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
}
