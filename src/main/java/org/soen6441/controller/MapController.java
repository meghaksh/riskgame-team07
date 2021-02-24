package org.soen6441.controller;

import org.soen6441.model.Map;
/**
 * This class update the MapModel. Also receives the acknowledgement for the update and pass it back to the parent GameController.
 */
public class MapController {
	private Map d_mapModel;
	
	/**
	 * This is a constructor which receives Map object as parameter which is used throughout the class
	 * @param p_map This is a reference of Map object (within GameModel) passed from parent GameController
	 */
	public MapController(Map p_map) {
		d_mapModel = p_map;
	}
	
	/**
	 * This method calls the savemap method of the mapmodel to save the map in file.
	 * @param p_str the command given by user to save map
	 * @return l_Result a feedback message on success or error of save function.
	 * @throws Exception throws exception when there is any error during savemap
	 */
	public String saveMap(String p_Str)throws Exception {
		String[] l_CommandArray = p_Str.split(" ");
		String l_Result=d_mapModel.saveMap(l_CommandArray[1]);
		return l_Result;
	}
	
	/**
	 * This method calls the loadmap method of mapmodel and passes the filename parameter to load a map which is in file
	 * @param p_str It is the command from the CommandPrompt
	 * @return l_result a feedback message on success or error
	 * @throws Exception throws exception if there is any exception occured during loading the map
	 */
	public String loadMap(String p_str)throws Exception{
		String[] l_CommandArray = p_str.split(" ");
		if(l_CommandArray[0].equals("editmap")) {
			String l_Result=d_mapModel.loadMap(l_CommandArray[1]);
			l_Result=l_Result+" You Can Now Edit IT";
			return l_Result;
		}else {
			if(l_CommandArray.length<2)
			{
				throw new Exception("Please Enter valid Filename");
			}
			String l_Result=d_mapModel.loadMap(l_CommandArray[1]);
			l_Result=l_Result+" You Can Now Proceed To Add Players";
			return l_Result;
		}
		
	/**
	 * This method calls the Validate map method from the mapmodel class	
	 */
	}
	public String validateMap() throws Exception {
		return d_mapModel.validateMap();
	}
	
	/**
	 * This method takes the keyword and entire command from the CommandPrompt and then checks it with all the cases.
	 * After checking it performs the specific functionality and returns the feedback accordingly
	 * @param p_command The Keyword to access the specific case in the switchcase
	 * @param p_str The entire command from the CommandPrompt
	 * @return l_ReturnString returns feedback on every functionality performed
	 * @throws Exception throws exception if any error in performing a certain functionality
	 */
	public String editMap(String p_command, String p_str)throws Exception {
		String[] l_CommandArray = p_str.split(" ");
		int l_Counter = 1;
		int l_AddContinentCounter = 0;
		int l_RemoveContinentCounter = 0;
		int l_AddCountryCounter=0;
		int l_RemoveCountryCounter=0;
		int l_AddBorderCounter=0;
		int l_RemoveBorderCounter=0;
		String l_ReturnString = "";
		while(l_Counter<l_CommandArray.length) {
			if(l_CommandArray[l_Counter].equals("-add")) {
				switch(p_command) {
					case "editcontinent":
						if(l_CommandArray.length<4) {
							throw new Exception ("Please add control value for the continent");
						}
						d_mapModel.addContinent(l_CommandArray[l_Counter+1],l_CommandArray[l_Counter+2]);
						l_Counter+=3;
						l_AddContinentCounter+=1;
						break;
					case "editcountry":
						if(l_CommandArray.length<4) {
							throw new Exception ("Please add continent for the country");
						}
						d_mapModel.addCountry(l_CommandArray[l_Counter+1],l_CommandArray[l_Counter+2]);
						l_Counter+=3;
						l_AddCountryCounter+=1;
						break;
					case "editneighbor":
						if(l_CommandArray.length<4) {
							throw new Exception ("Please add neighbor for the country ");
						}
						d_mapModel.addBorder(l_CommandArray[l_Counter+1], l_CommandArray[l_Counter+2]);
						l_Counter+=3;
						l_AddBorderCounter+=1;
						break;
						
				}

			}else if(l_CommandArray[l_Counter].equals("-remove")) {
				switch(p_command) {
					case "editcontinent":
						if(l_CommandArray.length<3) {
							throw new Exception ("Please add continent to remove");
						}
						d_mapModel.removeContinent(l_CommandArray[l_Counter+1]);
						l_Counter+=2;
						l_RemoveContinentCounter+=1;
						break;
					case "editcountry":
						if(l_CommandArray.length<3) {
							throw new Exception ("Please add country to remove");
						}
						d_mapModel.removeCountry(l_CommandArray[l_Counter+1],true); 
						l_Counter+=2;
						l_RemoveCountryCounter+=1;
						break;
					case "editneighbor":
						if(l_CommandArray.length<4) {
							throw new Exception ("Please add neighbor to remove");
						}
						d_mapModel.removeBorder(l_CommandArray[l_Counter+1], l_CommandArray[l_Counter+2]);
						l_Counter+=3;
						l_RemoveBorderCounter+=1;
						break;
				}
				
			}else {
				
				throw new Exception("Please Enter a Valid Command");
			}
		}
		if(l_AddContinentCounter>0) {
			l_ReturnString += "Number of Continents Added : " + l_AddContinentCounter + "\n";
		}
		if(l_RemoveContinentCounter>0) {
			l_ReturnString += "Number of Continents Removed : " + l_RemoveContinentCounter + "\n";
		}
		if(l_AddCountryCounter>0) {
			l_ReturnString += "Number of Countries Added : " + l_AddCountryCounter + "\n";
		}
		if(l_RemoveCountryCounter>0) {
			l_ReturnString += "Number of Countries Removed : " + l_RemoveCountryCounter + "\n";
		}
		if(l_AddBorderCounter>0) {
			l_ReturnString += "Number of Borders Added : " + l_AddBorderCounter + "\n";
		}
		if(l_RemoveBorderCounter>0) {
			l_ReturnString += "Number of Borders Removed : " + l_RemoveBorderCounter + "\n";
		}
		d_mapModel.getContinents();
		if(d_mapModel.getCountryList().size()>0) {
			d_mapModel.getCountries();
		}
		return l_ReturnString;
	}
}

