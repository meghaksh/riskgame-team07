package org.soen6441.utility.state;

import org.soen6441.controller.GameEngine;               import org.soen6441.view.CommandPrompt;
/**
 * The phase class is an abstract class which has all the methods representing the commands in the game.
 * There are different phases in the game which will be extending the methods of phase class.
 * The relevant phase will have the implementation of methods according to its phase.
 */
public abstract class Phase {
	/**
	 * Object of the GameEngine class to be used in all phases
	 */
	public GameEngine d_Ge;
	/**
	 * Command prompt to write phase acknowledgements
	 */
	public CommandPrompt d_Vw;
	/**
	 * This is the constructor for Phase class. It initializes Game engine and command prompt object
	 * @param p_Ge Object of Game Engine
	 * @param p_Vw Object of Command Prompt
	 */
	public Phase(GameEngine p_Ge, CommandPrompt p_Vw ){
		d_Ge = p_Ge;
		d_Vw=	p_Vw;
	}
	/**
	 * This method in phase calls the editMap method of the game engine and returns an acknowledgement.
	 * It does this if the command is applicable for that particular phase or else it will simply return String stating invalid command
	 * @param p_S Command that has been entered by the player
	 * @return String which gives the proper acknowledgement to player
	 */
	abstract public String editMap(String p_S);

	/**
	 * This method in phase calls the editCountry method of the game engine and returns an acknowledgement.
	 * It does this if the command is applicable for that particular phase or else it will simply return String stating invalid command
	 * @param p_S String which will be input to switch case of that function
	 * @param p_S1 Command that has been entered by the player
	 * @return String which gives the proper acknowledgement to player
	 */
	abstract public String editCountry(String p_S, String p_S1);
	/**
	 * This method in phase calls the editContinent method of the game engine and returns an acknowledgement.
	 * It does this if the command is applicable for that particular phase or else it will simply return String stating invalid command
	 * @param p_S String which will be input to switch case of that function
	 * @param p_S1 Command that has been entered by the player
	 * @return String which gives the proper acknowledgement to player
	 */
	abstract public String editContinent(String p_S, String p_S1);
	/**
	 * This method in phase calls the editNeighbor method of the game engine and returns an acknowledgement.
	 * It does this if the command is applicable for that particular phase or else it will simply return String stating invalid command
	 * @param p_S String which will be input to switch case of that function
	 * @param p_S1 Command that has been entered by the player
	 * @return String which gives the proper acknowledgement to player
	 */
	abstract public String editNeighbor(String p_S, String p_S1);
	/**
	 * This method in phase calls the saveMap method of the game engine and returns an acknowledgement.
	 * It does this if the command is applicable for that particular phase or else it will simply return String stating invalid command
	 * @param p_S Command that has been entered by the player
	 * @return String which gives the proper acknowledgement to player
	 */
	abstract public String saveMap(String p_S);
	/**
	 * This method in phase calls the validateMap method of the game engine and returns an acknowledgement.
	 * It does this if the command is applicable for that particular phase or else it will simply return String stating invalid command
	 * @return String which gives the proper acknowledgement to player
	 */
	abstract public String validateMap();
	/**
	 *  This method in phase calls the loadMap method of the game engine and returns an acknowledgement.
	 *  It does this if the command is applicable for that particular phase or else it will simply return String stating invalid command
	 * @param p_S Command that has been entered by the player
	 * @return String which gives the proper acknowledgement to player
	 */
	abstract public String loadMap(String p_S);
	/**
	 * This method in phase calls the editPlayer method of the game engine and returns an acknowledgement.
	 * It does this if the command is applicable for that particular phase or else it will simply return String stating invalid command
	 * @param p_S String which will be input to switch case of that function
	 * @param p_S1 Command that has been entered by the player
	 * @return String which gives the proper acknowledgement to player
	 */
	abstract public String  addPlayers(String p_S, String p_S1);
	/**
	 * This method in phase calls the assign countries method of the game engine .
	 * It will assign countries to all the players.
	 */
	abstract public void assignCountries();
	/**
	 * This method in phase calls the showmap method of the game engine .
	 * It will display the Map
	 */
	abstract public void showMap();
	/**
	 * This method just returns the name of the phase
	 * @return String of current phase name
	 */
	abstract public String getPhaseName();
	
	/**
	 * It is the tournament method of the game engine to play the game in tournament mode.
	 * It does this if the command is applicable for that particular phase or else it will simply return String stating invalid command
	 * @param p_String string input
	 * @param p_CommandStringFromInput input entered by the user. 
	 * @return Acknowledgement
	 */
	abstract public String tournament(String p_String, String p_CommandStringFromInput);
}

