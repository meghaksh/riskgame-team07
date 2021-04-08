package org.soen6441.adapterpattern;

import org.soen6441.controller.GameEngine;

public class Adapter extends Target {
	
	private Adaptee d_Adaptee; 
	private GameEngine d_GameEngine;
	public Adapter(GameEngine p_GameEngine) {
		super(p_GameEngine);
		
	}
	
	public Adapter (Adaptee p_Adaptee,GameEngine p_GameEngine)
	{
		//this.Adapter(p_GameEngine);
		super(p_GameEngine);
		this.d_Adaptee=p_Adaptee;
		this.d_GameEngine=p_GameEngine;
	}
	
	public String loadMap(String p_S)
	{
		return this.d_Adaptee.loadConquestMap(p_S, d_GameEngine);

	}
	public String saveMap(String p_S)
	{
		return this.d_Adaptee.saveConquestMap(p_S,d_GameEngine);
	}
	
}
