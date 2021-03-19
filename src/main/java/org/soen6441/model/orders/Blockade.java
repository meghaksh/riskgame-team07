package org.soen6441.model.orders;

import org.soen6441.controller.PlayerController;
import org.soen6441.model.Country;
import org.soen6441.model.Order;
import org.soen6441.model.Player;

public class Blockade implements Order {
	
	private Player d_Player;
	private Country d_Country;
	private PlayerController d_Pc;
	
	public Blockade(Player p_player, Country p_TempCountry) {
		d_Player = p_player;
		d_Country = p_TempCountry;
	
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
