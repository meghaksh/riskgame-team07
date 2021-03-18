package org.soen6441.utility.state;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.soen6441.controller.GameEngine;
import org.soen6441.model.GameModelNew;
import org.soen6441.view.CommandPrompt;

public class IssueOrderTest {
	
	CommandPrompt l_CpView;
	GameModelNew l_GameModel;
	GameEngine p_ge;
	IssueOrder io;
	Phase p;
	
	@Before
	public void setTestContext() throws Exception {
		
		l_CpView= new CommandPrompt();
		l_GameModel=new GameModelNew();
		p_ge= new GameEngine(l_CpView,l_GameModel);
		io= new IssueOrder(p_ge,l_CpView);
	}

	@Test
	public void testEditMap() {
		String l_ExpectedMessage="Invalid command in state IssueOrder";
		String l_ActualMessage = "";
		io.editMap("map5");
		String [] l_ActualMessageArray=io.vw.getCommandAcknowledgement().split("/n");
		l_ActualMessage = l_ActualMessageArray[l_ActualMessageArray.length -1].trim();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testEditCountry() {
		String l_ExpectedMessage="Invalid command in state IssueOrder";
		String l_ActualMessage = "";
		io.editCountry("india","asia");
		String [] l_ActualMessageArray=io.vw.getCommandAcknowledgement().split("/n");
		l_ActualMessage = l_ActualMessageArray[l_ActualMessageArray.length -1].trim();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testEditContinent() {
		String l_ExpectedMessage="Invalid command in state IssueOrder";
		String l_ActualMessage = "";
		io.editContinent("asia", "1");
		String [] l_ActualMessageArray=io.vw.getCommandAcknowledgement().split("/n");
		l_ActualMessage = l_ActualMessageArray[l_ActualMessageArray.length -1].trim();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testEditNeighbor() {
		String l_ExpectedMessage="Invalid command in state IssueOrder";
		String l_ActualMessage = "";
		io.editNeighbor("india", "china");
		String [] l_ActualMessageArray=io.vw.getCommandAcknowledgement().split("/n");
		l_ActualMessage = l_ActualMessageArray[l_ActualMessageArray.length -1].trim();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testSaveMap() {
		String l_ExpectedMessage="Invalid command in state IssueOrder";
		String l_ActualMessage = "";
		io.saveMap("map");
		String [] l_ActualMessageArray=io.vw.getCommandAcknowledgement().split("/n");
		l_ActualMessage = l_ActualMessageArray[l_ActualMessageArray.length -1].trim();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testLoadMap() {
		String l_ExpectedMessage="Invalid command in state IssueOrder";
		String l_ActualMessage = "";
		io.loadMap("map");
		String [] l_ActualMessageArray=io.vw.getCommandAcknowledgement().split("/n");
		l_ActualMessage = l_ActualMessageArray[l_ActualMessageArray.length -1].trim();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testAddPlayers() {
		String l_ExpectedMessage="Invalid command in state IssueOrder";
		String l_ActualMessage = "";
		io.addPlayers("zeal", "raj");
		String [] l_ActualMessageArray=io.vw.getCommandAcknowledgement().split("/n");
		l_ActualMessage = l_ActualMessageArray[l_ActualMessageArray.length -1].trim();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testAssignCountries() {
		String l_ExpectedMessage="Invalid command in state IssueOrder";
		String l_ActualMessage = "";
		io.assignCountries();
		String [] l_ActualMessageArray=io.vw.getCommandAcknowledgement().split("/n");
		l_ActualMessage = l_ActualMessageArray[l_ActualMessageArray.length -1].trim();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testValidateMap() {
		String l_ExpectedMessage="Invalid command in state IssueOrder";
		String l_ActualMessage = "";
		io.validateMap();
		String [] l_ActualMessageArray=io.vw.getCommandAcknowledgement().split("/n");
		l_ActualMessage = l_ActualMessageArray[l_ActualMessageArray.length -1].trim();
		System.out.println(l_ActualMessage);
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}


	@Test
	public void testNext() {
		//fail("Not yet implemented");
	}

}
