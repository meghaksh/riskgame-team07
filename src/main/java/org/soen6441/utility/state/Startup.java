package org.soen6441.utility.state;

import org.soen6441.controller.GameEngine;
import org.soen6441.observerpattern.LogEntryBuffer;
import org.soen6441.view.CommandPrompt;



public class Startup extends Phase {
	LogEntryBuffer leb;
	public Startup(GameEngine p_ge,CommandPrompt p_vw) {
		super(p_ge, p_vw);
		leb=new LogEntryBuffer();
		System.out.println("startup phase");
	}
	
	public void showMap() {
		ge.showMap(this);
	}

	public String loadMap(String s) {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		leb.setResult("Invalid command in state ");
		//throws exception 
		System.out.println("map has been already been loaded");
		return null;
	}

	public String editCountry(String s, String s1) {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		leb.setResult("Invalid command in state ");
		return null;
	}

	public String saveMap(String s) {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		leb.setResult("Invalid command in state ");
		return null;
	}

	public String addPlayers(String s, String s1) {
		
		System.out.println("entering add players ");
		String l_AckMsg;
		try {  l_AckMsg =ge.getPlayerController().editPlayer(s, s1);
		  	
		  	}catch(Exception p_Exception)
			{
		  		l_AckMsg=p_Exception.getMessage();
		  	}
		leb.setResult(l_AckMsg);
		return l_AckMsg;
//		ge.setPhase(new IssueOrder(ge,vw));
	}
		 // call the addplayer function and get to next phase
		
	
	
	
	public String editMap(String s) {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		leb.setResult("Invalid command in state ");
		return null;
		
	}
	
	public String editContinent(String s, String s1) {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		leb.setResult("Invalid command in state ");
		return null;
	}

	public String editNeighbor(String s, String s1) {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		leb.setResult("Invalid command in state ");
		return null;
	}
	
	public void assignCountries() {

		System.out.println("entering assigncountries ");
		try {  ge.assignCountries();
			  
		  	}catch(Exception p_Exception)
			{
		  		vw.setCommandAcknowledgement(p_Exception.getMessage());
		  		leb.setResult(p_Exception.getMessage().toString());
		  		ge.setPhase(new Startup(ge,vw));
		  		return;
		  	}
		
		ge.setPhase(new Reinforcement(ge,vw));
		
	}
	
	public String validateMap() {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		leb.setResult("Invalid command in state ");
		return null;
	}
	
	public void endGame() {
		//terminates the game
	}

	public void next() {
		// please add players before proceeding to next phase 
	}
	public String getPhaseName()
	{
		return "Startup";
	}
	
}
