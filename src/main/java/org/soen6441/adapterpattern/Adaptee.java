package org.soen6441.adapterpattern;

import org.soen6441.controller.GameEngine;

public class Adaptee {
	
	
	
	
	public String loadConquestMap(String p_s,GameEngine p_GameEngine) {
		try {
		p_GameEngine.getGameModel().getMap().addContinent("Asia", "4");
		p_GameEngine.getGameModel().getMap().addContinent("Africa", "4");
		p_GameEngine.getGameModel().getMap().addContinent("Europe", "4");
		}catch(Exception p_E)
		{}
		
		return "success";
	}

}
