package org.soen6441.utility.state;

import org.soen6441.controller.GameEngine;                   import org.soen6441.observerpattern.LogEntryBuffer;
import org.soen6441.view.CommandPrompt;

public class Startup extends Phase {
	LogEntryBuffer d_Leb;
	public Startup(GameEngine p_Ge,CommandPrompt p_Vw) {
		super(p_Ge, p_Vw);
		d_Leb=new LogEntryBuffer();
	}
	
	public void showMap() {
		d_Ge.showMap(this);
	}

	public String loadMap(String p_S) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
		return null;
	}

	public String editCountry(String p_S, String p_S1) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
		return null;
	}

	public String saveMap(String p_S) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
		return null;
	}

	public String addPlayers(String p_S, String p_S1) {
		String l_AckMsg;
		try {  l_AckMsg =d_Ge.getPlayerController().editPlayer(p_S,p_S1);
		  	
		  	}catch(Exception p_Exception)
			{
		  		l_AckMsg=p_Exception.getMessage();
		  	}
		d_Leb.setResult(l_AckMsg);
		return l_AckMsg;

	}

	public String editMap(String p_S) {
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
	
	public void assignCountries() {
		try {  d_Ge.assignCountries();
		  	}catch(Exception p_Exception)
			{
		  		d_Vw.setCommandAcknowledgement(p_Exception.getMessage());
		  		d_Leb.setResult(p_Exception.getMessage().toString());
		  		d_Ge.setPhase(new Startup(d_Ge,d_Vw));
		  		return;
		  	}
		d_Ge.setPhase(new Reinforcement(d_Ge,d_Vw));
	}
	
	public String validateMap() {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
		return null;
	}
	
	public String getPhaseName()
	{
		return "Startup";
	}
	
}
