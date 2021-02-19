package org.soen6441.view;

import org.soen6441.controller.GameController;
import org.soen6441.model.GameModelNew;

public class StartGame {
	public static void main(String[] args) {
		//Map d_mapModel = new Map();
		GameModelNew d_GameModel = new GameModelNew();
		CommandPrompt d_CpView =  new CommandPrompt();
		GameController d_GameController = new GameController(d_CpView, d_GameModel);
		//MapController d_MapController = new MapController(d_cpView, d_mapModel);
		
	}
}

