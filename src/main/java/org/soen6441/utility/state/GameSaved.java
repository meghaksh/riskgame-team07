package org.soen6441.utility.state;

import org.soen6441.controller.GameEngine;
import org.soen6441.view.CommandPrompt;

public class GameSaved extends Phase {

	public GameSaved(GameEngine p_Ge, CommandPrompt p_Vw) {
		super(p_Ge, p_Vw);
		d_Vw.clearTextArea();
		d_Vw.setCommandAcknowledgement("Game Saved");
	}

	@Override
	public String editMap(String p_S) {
		return null;
	}

	@Override
	public String editCountry(String p_S, String p_S1) {
		return null;
	}

	@Override
	public String editContinent(String p_S, String p_S1) {
		return null;
	}

	@Override
	public String editNeighbor(String p_S, String p_S1) {
		return null;
	}

	@Override
	public String saveMap(String p_S) {
		return null;
	}

	@Override
	public String validateMap() {
		return null;
	}

	@Override
	public String loadMap(String p_S) {
		return null;
	}

	@Override
	public String addPlayers(String p_S, String p_S1) {
		return null;
	}

	@Override
	public void assignCountries() {
		
	}

	@Override
	public void showMap() {
		
	}

	@Override
	public String getPhaseName() {
		return null;
	}

	@Override
	public String tournament(String p_string, String p_CommandStringFromInput) {
		return null;
	}

}
