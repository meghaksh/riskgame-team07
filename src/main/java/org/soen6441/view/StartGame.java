package org.soen6441.view;

import org.soen6441.controller.GameController;
import org.soen6441.model.GameModelNew;
/**
 * Driver class for the game.  
 */
public class StartGame {
	/**
	 * This main method is an entry point for the game. It initialize the GameModel, GameView (aka CommandPrompt) and GameController. Passes view and model in controller.
	 * 
	 * @param args default parameter for the main method which is not used anywhere in the game. 
	 */
	public static void main(String[] args) {
		GameModelNew d_GameModel = new GameModelNew();
		CommandPrompt d_CpView =  new CommandPrompt();
		GameController d_GameController = new GameController(d_CpView, d_GameModel);
	}
}

