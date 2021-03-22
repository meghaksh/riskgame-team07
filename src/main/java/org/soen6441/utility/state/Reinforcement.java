package org.soen6441.utility.state;

import org.soen6441.controller.GameEngine;				import org.soen6441.observerpattern.*;
import org.soen6441.view.CommandPrompt;

public class Reinforcement extends Phase {
		LogEntryBuffer d_Leb;

	public Reinforcement(GameEngine p_Ge, CommandPrompt p_Vw) {
		super(p_Ge, p_Vw);
		d_Leb=new LogEntryBuffer();
		try{
			d_Ge.getGameModel().assignReinforcementArmies();
			d_Ge.showAllPlayerWithArmies();
			}catch(Exception e){
			d_Vw.setCommandAcknowledgement(e.getMessage()+"\n");
			d_Leb.setResult(e.getMessage().toString());
			}
		
		d_Ge.setPhase(new IssueOrder(d_Ge,d_Vw));
	}

	public String editMap(String p_S) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}

	public String editCountry(String p_S, String p_S1) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}

	public String editContinent(String p_S, String p_S1) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}
	
	public String editNeighbor(String p_S, String p_S1) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}

	public String saveMap(String p_S) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}

	public String validateMap() {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}

	public String loadMap(String p_S) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}

	public String addPlayers(String p_S, String p_S1) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}
	
	public void assignCountries() {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
	}
	
	public void showMap() {
		d_Ge.showMap(this);
	}

	public String getPhaseName()
	{
		return "ReinforcementPhase";
	}

}
