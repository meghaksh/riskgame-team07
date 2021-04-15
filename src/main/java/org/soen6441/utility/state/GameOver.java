package org.soen6441.utility.state;

import org.soen6441.controller.GameEngine;
import org.soen6441.observerpattern.LogEntryBuffer;
import org.soen6441.view.CommandPrompt;
/**
 *The GameOver Phase extends the phase class and implements all the methods suitable for that particular phase.
 *It returns invalid command for others which are not compatible with this phase 
 */
public class GameOver extends Phase {
	LogEntryBuffer d_Leb;
	/**
	 * This is the constructor of GameOver which initializes Game engine object and command prompt object and assigning log entry buffer
	 * It then ends the game by giving relevant acknowledgement.
	 * @param p_Ge object of game engine
	 * @param p_Vw object of view
	 */
	public GameOver(GameEngine p_Ge, CommandPrompt p_Vw) {
		super(p_Ge, p_Vw);
		//d_Vw.clearTextArea();
		d_Vw.setCommandAcknowledgement("Game Over");
		d_Leb=new LogEntryBuffer();
		d_Leb.setResult("This is Game over phase");
	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String editMap(String p_S) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
		return null;
	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String editCountry(String p_S, String p_S1) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
		return null;
	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String editContinent(String p_S, String p_S1) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
		return null;
	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String editNeighbor(String p_S, String p_S1) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
		return null;
	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String saveMap(String p_S) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
		return null;
	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String validateMap() {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
		return null;
	}

	@Override
	public String loadMap(String p_S) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
		return null;
	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String addPlayers(String p_S, String p_S1) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
		return null;
	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public void assignCountries() {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public void showMap() {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String getPhaseName() {
		// TODO Auto-generated method stub
		return "GameOver";
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String tournament(String p_string, String p_CommandStringFromInput) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" );
		d_Leb.setResult("Invalid command in state ");
		return null;
	}

}
