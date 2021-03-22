package org.soen6441.utility.state;

import org.soen6441.controller.GameEngine;               import org.soen6441.view.CommandPrompt;

public abstract class Phase {
		public GameEngine d_Ge;
		public CommandPrompt d_Vw;
		public Phase(GameEngine p_Ge, CommandPrompt p_Vw ){
			d_Ge = p_Ge;
			d_Vw=	p_Vw;
		}
		// These are the commands for edit phase 
		abstract public String editMap(String p_S);
		abstract public String editCountry(String p_S, String p_S1);
		abstract public String editContinent(String p_S, String p_S1);
		abstract public String editNeighbor(String p_S, String p_S1);
		abstract public String saveMap(String p_S);
		abstract public String validateMap();
		//these are commands for startup phase 
		abstract public String loadMap(String p_S);
		abstract public String  addPlayers(String p_S, String p_S1);
		abstract public void assignCountries();
		//common comand for entire phases
		abstract public void showMap();
		abstract public String getPhaseName();
	}

