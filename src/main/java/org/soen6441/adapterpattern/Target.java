package org.soen6441.adapterpattern;

import org.soen6441.controller.GameEngine;

public class Target {
	GameEngine d_GameEngine;
	
	
	
	public Target() {}
	
	
	public Target(GameEngine p_GameEngine)
	{
		d_GameEngine=p_GameEngine;
	}
	
	

	public String loadMap(String p_S) throws Exception
	{
		
		return d_GameEngine.getMapController().loadMap(p_S);
	}
}