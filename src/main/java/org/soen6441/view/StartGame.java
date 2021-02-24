package org.soen6441.view;

import org.soen6441.controller.GameEngine;	import org.soen6441.model.GameModelNew;

/**
 * Driver class for the game. It consist a single method main which is an entry point to the game.
 *  
 */
public class StartGame {

	/**
	 * This main method is an entry point for the game. 
	 * It initialize the GameModel, GameView (i.e. CommandPrompt) and GameEngine. 
	 * Passes view and model in controller.
	 * 
	 * @param p_Args default parameter for the main method which is not used anywhere in the game. 
	 */
	public static void main(String[] p_Args) {
		GameModelNew l_GameModel = new GameModelNew();
		CommandPrompt l_CpView =  new CommandPrompt();
		new GameEngine(l_CpView, l_GameModel);
	}
}