package org.soen6441.view;

import org.soen6441.controller.MapController;
import org.soen6441.model.Map;

public class StartGame {
	public static void main(String[] args) {
		Map d_mapModel = new Map();
		CommandPrompt d_cpView =  new CommandPrompt();
		MapController d_mapController = new MapController(d_cpView, d_mapModel);
	}
}

