package org.soen6441.utility.state;

import org.soen6441.controller.GameEngine;
import org.soen6441.observerpattern.LogEntryBuffer;
import org.soen6441.view.CommandPrompt;

public class ExecuteOrder extends Phase {
	LogEntryBuffer d_Leb;
	public ExecuteOrder(GameEngine p_Ge, CommandPrompt p_Vw) {
		super(p_Ge, p_Vw);
		d_Ge.getPlayerController().playerNextOrder();
		d_Ge.showMap(this);
		d_Ge.setPhase(new Reinforcement(d_Ge,d_Vw));
		d_Leb=new LogEntryBuffer();
	}
	public String editMap(String p_S) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
		return null;
	}
	public String editCountry(String p_S, String p_S1) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
		return null;
	}

	public String editContinent(String p_S, String p_S1) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
		return null;
	}

	public String editNeighbor(String p_S, String p_S1) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
		return null;
	}

	public String saveMap(String p_S) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
		return null;
	}
	
	public String loadMap(String p_S) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
		return null;
	}
	
	public String addPlayers(String p_S, String p_S1) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
		return null;
	}
	
	public void assignCountries() {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
	}
	
	public void showMap() {
		d_Ge.showMap(this);
	}
	
	public void endGame() {
		
	}
	
	

	public String validateMap() {	
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
		return null;
	}
	public String getPhaseName()
	{
		return "ExecutePhase";
	}

}
