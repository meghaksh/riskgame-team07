package org.soen6441.utility.state;

import org.soen6441.controller.GameEngine;
import org.soen6441.view.CommandPrompt;

public   class Edit extends Phase {
	public Edit(GameEngine p_ge,CommandPrompt p_vw) {
		super(p_ge,p_vw);
	}
	public String loadMap(String s) {
		System.out.println("entering edit map  ");
		String l_AckMsg;
		try {  l_AckMsg =ge.getMapController().loadMap(s);
		  	}catch(Exception p_Exception)
			{
		  		l_AckMsg=p_Exception.getMessage();
		  	}
		
		
		ge.setPhase(new Startup(ge,vw));
		System.out.println("map has been loaded and phase has been changed");
		return l_AckMsg;
	}

	public String editCountry(String s, String s1) {
		System.out.println("entering edit country  ");
		String l_AckMsg;
		try {  l_AckMsg =ge.getMapController().editMap(s, s1);
		  	
		  	}catch(Exception p_Exception)
			{
		  		l_AckMsg=p_Exception.getMessage();
		  	}
		
		return l_AckMsg;
	}
	
	public String saveMap(String s) {	 
		System.out.println("entering save map ");
		String l_AckMsg;
		try {  l_AckMsg =ge.getMapController().saveMap(s);
		  	}catch(Exception p_Exception)
			{
		  		l_AckMsg=p_Exception.getMessage();
		  	}
		
		return l_AckMsg;
	}
	
	public void addPlayers() {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" ); 
	}
	
	public String editMap(String s) {
		System.out.println("entering edit map  ");
		String l_AckMsg;
		try {  l_AckMsg =ge.getMapController().loadMap(s);
		  	}catch(Exception p_Exception)
			{
		  		l_AckMsg=p_Exception.getMessage();
		  	}
		
		return l_AckMsg;
	}
	
	public String editContinent(String s, String s1) {
		System.out.println("entering edit continent  ");
		String l_AckMsg;
		try {  l_AckMsg =ge.getMapController().editMap(s, s1);
		  	}catch(Exception p_Exception)
			{
		  		l_AckMsg=p_Exception.getMessage();
		  	}
		
		return l_AckMsg;
	}
	
	public String editNeighbor(String s, String s1) {
		System.out.println("entering edit neighbor  ");
		String l_AckMsg;
		try {  l_AckMsg =ge.getMapController().editMap(s, s1);
		  	}catch(Exception p_Exception)
			{
		  		l_AckMsg=p_Exception.getMessage();
		  	}
		
		return l_AckMsg;
	}
	
	public String validateMap() {
		System.out.println("entering validatemap ");
		String l_AckMsg;
		try {  l_AckMsg =ge.getMapController().validateMap();
		  	}catch(Exception p_Exception)
			{
		  		l_AckMsg=p_Exception.getMessage();
		  	}
		
		return l_AckMsg;
		
	}
	
	public void assignCountries() {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName() +"\n");  
	}
	
	public void showMap() {
		// shows the edited map
	}
	
	public void endGame() {
		// terminates the game and goes to last

	}
	
	public void next() {
		// as the game flow doesn't depend on user and phases change internally i don't think this will be needed maybe?
		System.out.println("must load map a valid map to start the game ");
	}
}
