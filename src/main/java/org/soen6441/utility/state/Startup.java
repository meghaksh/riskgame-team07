package org.soen6441.utility.state;

import org.soen6441.controller.GameEngine;
import org.soen6441.view.CommandPrompt;



public class Startup extends Phase {
	public Startup(GameEngine p_ge,CommandPrompt p_vw) {
		super(p_ge, p_vw);
	}
	
	public void showMap() {
		//call showmap function
	}

	public String loadMap(String s) {
		//throws exception 
		System.out.println("map has been already been loaded");
		return null;
	}

	public String editCountry(String s, String s1) {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}

	public String saveMap(String s) {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}

	public void addPlayers() {
		 // call the addplayer function and get to next phase
		ge.setPhase(new IssueOrder(ge,vw));
	}
	
	public String editMap(String s) {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
		
	}
	
	public String editContinent(String s, String s1) {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}

	public String editNeighbor(String s, String s1) {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}
	
	public void assignCountries() {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );// CANNOT ASSIGN COUNTRIES IN THIS PHASE BEFORE ADDING PLAYERS
	}
	
	public String validateMap() {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}
	
	public void endGame() {
		//terminates the game
	}

	public void next() {
		// please add players before proceeding to next phase 
	}
}
