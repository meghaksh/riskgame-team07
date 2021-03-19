package org.soen6441.model.orders;

import org.soen6441.model.Order;

public class Advance implements Order {
	public Advance() {
		
		
		/*Source should not be equal to target
		 * Source and target should be neighbors
		 * Source should have at least one army present after move/attack 
		 * win happens only when number of armies of defender is zero. and then only card is issued.
		 * Attack with the lesser number of armies than the player owns. */
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
