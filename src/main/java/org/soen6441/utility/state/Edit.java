package org.soen6441.utility.state;

import org.soen6441.controller.GameEngine;
import org.soen6441.observerpattern.LogEntryBuffer;
import org.soen6441.view.CommandPrompt;

public   class Edit extends Phase {
	LogEntryBuffer leb;
	public Edit(GameEngine p_ge,CommandPrompt p_vw) {
		super(p_ge,p_vw);
		leb=new LogEntryBuffer();
	}
	public String loadMap(String s) {
		System.out.println("entering edit map  ");
		String l_AckMsg;
		try {  l_AckMsg =ge.getMapController().loadMap(s);
		  	}catch(Exception p_Exception)
			{
		  		l_AckMsg=p_Exception.getMessage();
		  	}
		leb.setResult(l_AckMsg);
		
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
		leb.setResult(l_AckMsg);
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
		leb.setResult(l_AckMsg);
		return l_AckMsg;
	}
	
	public String addPlayers(String s, String s1) {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" ); 
		leb.setResult("Invalid command in state ");
		return null;
	}
	
	public String editMap(String s) {
		System.out.println("entering edit map  ");
		String l_AckMsg;
		try {  l_AckMsg =ge.getMapController().loadMap(s);
		  	}catch(Exception p_Exception)
			{
		  		l_AckMsg=p_Exception.getMessage();
		  	}
		leb.setResult(l_AckMsg);
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
		leb.setResult(l_AckMsg);
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
		leb.setResult(l_AckMsg);
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
		leb.setResult(l_AckMsg);
		return l_AckMsg;
		
	}
	
	public void assignCountries() {
		vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName() +"\n");  
		leb.setResult("Invalid command in state ");
		
	}
	
	public void showMap() {
		ge.showMap(this);
	}
	
	public void endGame() {
		// terminates the game and goes to last

	}
	public String getPhaseName()
	{
		return "EditPhase";
	}
	
	
}
