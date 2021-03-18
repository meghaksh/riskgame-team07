package org.soen6441.utility.state;

import org.soen6441.controller.GameEngine;
import org.soen6441.observerpattern.*;
import org.soen6441.view.CommandPrompt;

public class Reinforcement extends Phase {
		LogEntryBuffer leb;

	public Reinforcement(GameEngine p_ge, CommandPrompt p_vw) {
		super(p_ge, p_vw);
		leb=new LogEntryBuffer();
		System.out.println("entering reinforcement phase");
		try{
			ge.getGameModel().assignReinforcementArmies();
			ge.showAllPlayerWithArmies();
		}catch(Exception e)
		{
			vw.setCommandAcknowledgement(e.getMessage()+"\n");
			leb.setResult(e.getMessage().toString());
			
		}
		
		ge.setPhase(new IssueOrder(ge,vw));
		
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


	public String validateMap() {
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
	public String getPhaseName()
	{
		return "ReinforcementPhase";
	}

}
