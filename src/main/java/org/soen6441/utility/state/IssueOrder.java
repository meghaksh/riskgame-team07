package org.soen6441.utility.state;

import org.soen6441.controller.GameEngine;
import org.soen6441.view.CommandPrompt;



public class IssueOrder extends Phase {

	public IssueOrder(GameEngine p_ge,CommandPrompt p_vw) {
		super(p_ge, p_vw);
		
		System.out.println("Issue order phase");
		ge.getPlayerController().playerIssueOrder();
		ge.setPhase(new ExecuteOrder(p_ge,p_vw));
	}
	
	public String editMap(String s) {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}
	public String editCountry(String s, String s1) {
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

	public String saveMap(String s) {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}
	
	public String loadMap(String s) {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}
	
	public String addPlayers(String s, String s1) {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}
	
	public void assignCountries() {
		
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
	

	}
	
	public void showMap() {
		ge.showMap(this);
	}
	public void endGame() {
		
	}
	
	public void next() {
		// cannot proceed without assigning countries

	}

	public String validateMap() {	
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}
	public String getPhaseName()
	{
		return "Issueorder";
	}

	
	

}
