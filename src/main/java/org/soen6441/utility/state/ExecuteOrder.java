package org.soen6441.utility.state;

import org.soen6441.controller.GameEngine;
import org.soen6441.observerpattern.LogEntryBuffer;
import org.soen6441.view.CommandPrompt;

public class ExecuteOrder extends Phase {
	LogEntryBuffer leb;
	public ExecuteOrder(GameEngine p_ge, CommandPrompt p_vw) {
		super(p_ge, p_vw);
		leb=new LogEntryBuffer();
		System.out.println("execute order phase");
		ge.getPlayerController().playerNextOrder();
		ge.showMap(this);
		ge.setPhase(new Reinforcement(ge,vw));
	}
	public String editMap(String s) {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		leb.setResult("Invalid command in state ");
		return null;
	}
	public String editCountry(String s, String s1) {
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

	public String saveMap(String s) {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		leb.setResult("Invalid command in state ");
		return null;
	}
	
	public String loadMap(String s) {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		leb.setResult("Invalid command in state ");
		return null;
	}
	
	public String addPlayers(String s, String s1) {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		leb.setResult("Invalid command in state ");
		return null;
	}
	
	public void assignCountries() {
		
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		leb.setResult("Invalid command in state ");
	

	}
	
	public void showMap() {
		ge.showMap(this);
	}
	
	public void endGame() {
		
	}
	
	

	public String validateMap() {	
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		leb.setResult("Invalid command in state ");
		return null;
	}
	public String getPhaseName()
	{
		return "ExecutePhase";
	}

}
