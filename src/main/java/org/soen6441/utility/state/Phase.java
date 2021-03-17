package org.soen6441.utility.state;

import org.soen6441.controller.GameEngine;
import org.soen6441.observerpattern.Observable;
import org.soen6441.view.CommandPrompt;
public abstract class Phase {
	
		public GameEngine ge;
		public CommandPrompt vw;
		public Phase(GameEngine p_ge, CommandPrompt p_vw ) {
			ge = p_ge;
			vw=	p_vw;
		}

		
		// These are the commands for edit phase 
		abstract public String editMap(String s);
		abstract public String editCountry(String s, String s1);
		abstract public String editContinent(String s, String s1);
		abstract public String editNeighbor(String s, String s1);
		abstract public String saveMap(String s);
		abstract public String validateMap();
	
		
		
		//these are commands for startup phase 
		abstract public String loadMap(String s);
		abstract public String  addPlayers(String s, String s1);
		abstract public void assignCountries();

		
		//common comand for entire phases
		abstract public void showMap();
		
		
		// end command
		abstract public void endGame();

		
		
		/**
		 *  Common method to all States. 
		 */
		
	}

