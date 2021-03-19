package org.soen6441.model.orders;

import org.soen6441.model.Order;

public class Advance implements Order {
	public Advance() {
		/*Player Reference who is issuing advance command. 
		 * From & To countries
		 * Number of Armies 
		 * 
		 * 
		 * 
		 * Check if both countries(To & From) belongs to the same player. 
		 * If yes, then simply move the armies to the "To" country from "From"
		 * If No, Write the logic for the attack. 
		 * Once attack is won, assign random card to the player who won.
		 *  (Country which wins the attack, search for it's owner and assign a card to that owner ) */
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
