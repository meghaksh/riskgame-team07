package org.soen6441.utility.state;

import org.soen6441.controller.GameEngine;
import org.soen6441.observerpattern.LogEntryBuffer;
import org.soen6441.view.CommandPrompt;
/**
*The Reinforcement Phase extends the phase class and implements all the methods suitable for that particular phase.
*It returns invalid command for others which are not compatible with this phase 
*/
public class Reinforcement extends Phase {
	/**
	 * object of LogEntryBuffer class to log in the logfile
	 */	
	LogEntryBuffer d_Leb;
	/**
	 * This is the constructor of Reinforcement class which initializes Game engine object and command prompt object and assigning log entry buffer
	 * It then calls the assign reinforcement armies method and then displays all the assigned armies and territories
	 * @param p_Ge object of game engine
	 * @param p_Vw object of view
	 */
	public Reinforcement(GameEngine p_Ge, CommandPrompt p_Vw) {
		super(p_Ge, p_Vw);
		d_Leb=new LogEntryBuffer();
		d_Leb.setResult("This is the Reinforcement Phase");
		try{
			d_Ge.getGameModel().assignReinforcementArmies();
			}catch(Exception e){
			d_Vw.setCommandAcknowledgement(e.getMessage()+"\n");
			d_Leb.setResult(e.getMessage());
			}
		
		d_Ge.setPhase(new IssueOrder(d_Ge,d_Vw));
	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String editMap(String p_S) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String editCountry(String p_S, String p_S1) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String editContinent(String p_S, String p_S1) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String editNeighbor(String p_S, String p_S1) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String saveMap(String p_S) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String validateMap() {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String loadMap(String p_S) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String addPlayers(String p_S, String p_S1) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		return null;
	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public void assignCountries() {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public void showMap() {
		d_Ge.showMap(this);
	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String getPhaseName()
	{
		return "ReinforcementPhase";
	}
	
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String tournament(String p_string, String p_CommandStringFromInput) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
		return null;
	}

}
