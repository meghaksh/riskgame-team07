package org.soen6441.utility.state;

import org.soen6441.adapterpattern.Adaptee;
import org.soen6441.adapterpattern.Adapter;
import org.soen6441.adapterpattern.Target;
import org.soen6441.controller.GameEngine;
import org.soen6441.observerpattern.LogEntryBuffer;
import org.soen6441.view.CommandPrompt;
/**
 *The Edit Phase extends the phase class and implements all the methods suitable for that particular phase.
 *It returns invalid command for others which are not compatible with this phase 
 */
public class Edit extends Phase {
	LogEntryBuffer d_Leb;
	/**
	 * This is the constructor of Edit class which initializes Game engine object and command prompt object and assigning log entry buffer
	 * @param p_Ge object of game engine 
	 * @param p_Vw object of view
	 */
	public Edit(GameEngine p_Ge,CommandPrompt p_Vw) {
		
		super(p_Ge,p_Vw);
		d_Leb=new LogEntryBuffer();
		d_Leb.setResult("This is the Edit Phase");
	}

	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String loadMap(String p_S) {

		String l_AckMsg;
		try { 
			Target l_TargetObject= new Target(d_Ge);
			l_AckMsg=l_TargetObject.loadMap(p_S);
			//l_AckMsg =d_Ge.getMapController().loadMap(p_S);
			
			//Target l_TargetObject= new Adapter(new Adaptee(),d_Ge);
			//l_AckMsg=l_TargetObject.loadMap(p_S.split(" ")[1]);
		}catch(Exception p_Exception)
		{
			l_AckMsg=p_Exception.getMessage();
		}
		d_Leb.setResult(l_AckMsg);

		d_Ge.setPhase(new Startup(d_Ge,d_Vw));
		return l_AckMsg;
	}

	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String editCountry(String p_S, String p_S1) {
		String l_AckMsg;
		try {  l_AckMsg =d_Ge.getMapController().editMap(p_S,p_S1);

		}catch(Exception p_Exception)
		{
			l_AckMsg=p_Exception.getMessage();
		}
		d_Leb.setResult(l_AckMsg);
		return l_AckMsg;
	}

	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String saveMap(String p_S) {	 
		String l_AckMsg;
		try {  l_AckMsg =d_Ge.getMapController().saveMap(p_S);
		}catch(Exception p_Exception)
		{
			l_AckMsg=p_Exception.getMessage();
		}
		d_Leb.setResult(l_AckMsg);
		return l_AckMsg;
	}

	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String addPlayers(String p_S, String p_S1) {
		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName()+"\n" ); 
		d_Leb.setResult("Invalid command in state ");
		return null;
	}

	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String editMap(String p_S) {
		System.out.println("entering edit map  ");
		String l_AckMsg;
		try {  l_AckMsg =d_Ge.getMapController().loadMap(p_S);
		}catch(Exception p_Exception)
		{
			l_AckMsg=p_Exception.getMessage();
		}
		d_Leb.setResult(l_AckMsg);
		return l_AckMsg;
	}

	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String editContinent(String p_S, String p_S1) {
		String l_AckMsg;
		try {  l_AckMsg =d_Ge.getMapController().editMap(p_S,p_S1);
		}catch(Exception p_Exception)
		{
			l_AckMsg=p_Exception.getMessage();
		}
		d_Leb.setResult(l_AckMsg);
		return l_AckMsg;
	}

	/**
	 *{@inheritDoc}
	 */
	@Override
	public String editNeighbor(String p_S, String p_S1) {
		String l_AckMsg;
		try {  l_AckMsg =d_Ge.getMapController().editMap(p_S,p_S1);
		}catch(Exception p_Exception)
		{
			l_AckMsg=p_Exception.getMessage();
		}
		d_Leb.setResult(l_AckMsg);
		return l_AckMsg;
	}

	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String validateMap() {
		String l_AckMsg;
		try {  l_AckMsg =d_Ge.getMapController().validateMap();
		}catch(Exception p_Exception)
		{
			l_AckMsg=p_Exception.getMessage();
		}
		d_Leb.setResult(l_AckMsg);
		return l_AckMsg;

	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public void assignCountries() {

		d_Vw.setCommandAcknowledgement("Invalid command in state " + this.getClass().getSimpleName() +"\n");  
		d_Leb.setResult("Invalid command in state ");

	}
	/**
	 *{@inheritDoc}
	 *
	 */
	@Override
	public void showMap() {
		d_Ge.showMap(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPhaseName()
	{
		return "EditPhase";
	}

	
}
