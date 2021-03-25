package org.soen6441.model.orders;

import java.util.Iterator;						import org.soen6441.model.Country;
import org.soen6441.model.GameModelNew;			import org.soen6441.model.Order;
import org.soen6441.model.Player;

/**
 * Blockade class implements the Order interface and overrides the execute method.
 * The object of this class is created when the player issues blockade command.
 */
public class Blockade implements Order {
	private Player d_Player;
	private Country d_Country;
	private GameModelNew d_GameObj;

	/**
	 * Constructor which is called when the object of the blockade is created. 
	 * @param p_Player The player who issues the blockade card
	 * @param p_TempCountry The country on which blockade to be created
	 */
	public Blockade(Player p_Player, Country p_TempCountry) {
		setPlayer(p_Player);
		d_Country = p_TempCountry;
		this.d_GameObj = getPlayer().getGameModel();
	}

	/**
	 * This method is called in the execute order phase. 
	 * If blockade order is valid, it triples the number of armies to the country and makes the country neutral. (assigns to neutral player)
	 * It also removes the blockade card from the player's list. 
	 */
	@Override
	public void execute() {
		if(isValid()) {
			d_Country.setNoOfArmies(d_Country.getNoOfArmies()*3);
			getPlayer().getCountryList().remove(d_Country);
			for(Player l_Player : d_GameObj.getAllPlayers()) {
				if(l_Player.getPlayerName().equals("Neutral Player")) {
					d_Country.setCountryOwnerPlayer(l_Player);
					l_Player.setPlayerArmies(l_Player.getPlayerArmies()+d_Country.getNoOfArmies());
					l_Player.addCountry(d_Country);
				}
			}
		}
		getPlayer().removeCard("Blockade");
	}

	/**
	 * This method checks the validity of the blockade order issued by the player. 
	 * This method returns false if the country doesn't belong to the player or player does not have blockade card. 
	 * @return true if the order is valid, else false. 
	 */
	public boolean isValid() {
		int l_Flag=0;
		if(!getPlayer().getCardList().contains("Blockade")) {
			getPlayer().setResult("Player does not have a blockade card");
			return false;
		}
		Iterator<Country>l_It = getPlayer().getCountryList().iterator();
		while(l_It.hasNext()) {
			Country l_TempCountry = (Country)l_It.next() ;
			if(d_Country==l_TempCountry) {
				l_Flag=1;
				break;
			}
		}
		if(l_Flag==1) {
			getPlayer().setResult("The blockade was successfull");
			return true;
		} else {
			getPlayer().setResult("\nThis country "+d_Country.getCountryName()+" doesnot belongs to "+getPlayer().getPlayerName());
			return false;
		}	
	}

	/**
	 * Returns the player who issued the blockade order. 
	 * @return the player who issued the blockade order. 
	 */
	public Player getPlayer() {
		return d_Player;
	}

	/**
	 * Sets the player who issues the blockade order.
	 * @param d_Player Player who issues the blockade order. 
	 */
	public void setPlayer(Player d_Player) {
		this.d_Player = d_Player;
	}
}