package org.soen6441.model.orders;

import java.util.Iterator;

import org.soen6441.controller.PlayerController;
import org.soen6441.model.Country;
import org.soen6441.model.GameModelNew;
import org.soen6441.model.Order;
import org.soen6441.model.Player;

public class Blockade implements Order {
	
	private Player d_Player;
	private Country d_Country;
	private GameModelNew d_gameobj;
	
	public Blockade(Player p_player, Country p_TempCountry) {
		d_Player = p_player;
		d_Country = p_TempCountry;
		this.d_gameobj = d_Player.getGameModel();
	
	}


	@Override
	public void execute() {
		System.out.println("inside blockade");
		if(isValid())
		{
			d_Country.setNoOfArmies(d_Country.getNoOfArmies()*3);
			try {
				d_gameobj.addPlayer("NeutralPlayer");
			} catch (Exception e) {
	
				e.printStackTrace();
			}
			d_Player.getCountryList().remove(d_Country);
			for(Player l_Player : d_gameobj.getAllPlayers()) {
				if(l_Player.getPlayerName()=="NeutralPlayer")
					System.out.println("play"+l_Player);
					d_Country.setCountryOwnerPlayer(l_Player);
					l_Player.addCountry(d_Country);
			}

		}
		
	}
	
	public boolean isValid()
	{
		int l_Flag=0;

			Iterator<Country>l_It = d_Player.getCountryList().iterator();
			while(l_It.hasNext()) {
				Country l_TempCountry = (Country)l_It.next() ;
				if(d_Country==l_TempCountry) {
					l_Flag=1;
					break;
				}
			}
			if(l_Flag==1) {
				return true;
			} else {
				d_Player.setResult("\nThis country "+d_Country.getCountryName()+" doesnot belongs to "+d_Player.getPlayerName());
				return false;
			}	
	}

}
