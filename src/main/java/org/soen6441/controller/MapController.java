package org.soen6441.controller;

import org.soen6441.model.Map;
/**
 * This class update the MapModel. Also receives the acknowledgement for the update and pass it back to the parent GameController.
 */
public class MapController {
	private Map d_mapModel;
	
	/**
	 * This is a constructor which receives Map object as parameter which is used throughout the class
	 * 
	 * @param p_map This is a reference of Map object (within GameModel) passed from parent GameController
	 */
	public MapController(Map p_map) {
		d_mapModel = p_map;
	}
	
	/**
	 * This method calls the savemap method of the mapmodel to save the map in file.
	 * 
	 * @param p_str the command given by user to save map
	 * @return a feedback message on success or error of save function.
	 * @throws Exception throws exception when there is any error during savemap
	 */
	public String saveMap(String p_Str)throws Exception {
		String[] l_CommandArray = p_Str.split(" ");
		String l_Result=d_mapModel.saveMap(l_CommandArray[1]);
		return l_Result;
	}
	public String loadMap(String p_str)throws Exception{
		String[] l_commandArray = p_str.split(" ");
		if(l_commandArray[0].equals("editmap")) {
			String l_result=d_mapModel.loadMap(l_commandArray[1]);
			l_result=l_result+" You Can Now Edit IT";
			return l_result;
		}else {
			String l_result=d_mapModel.loadMap(l_commandArray[1]);
			l_result=l_result+" You Can Now Proceed To Add Players";
			return l_result;
		}
		
	}
	public String validateMap() {
		return d_mapModel.validateMap();
	}
	public String editMap(String p_command, String p_str)throws Exception {
		String[] l_commandArray = p_str.split(" ");
		int l_counter = 1;
		int l_addContinentCounter = 0;
		int l_removeContinentCounter = 0;
		int l_addCountryCounter=0;
		int l_removeCountryCounter=0;
		String l_ReturnString = "";
		while(l_counter<l_commandArray.length) {
			if(l_commandArray[l_counter].equals("add")) {
				switch(p_command) {
					case "editcontinent":
						d_mapModel.addContinent(l_commandArray[l_counter+1],l_commandArray[l_counter+2]);
						l_counter+=3;
						l_addContinentCounter+=1;
						break;
					case "editcountry":
						d_mapModel.addCountry(l_commandArray[l_counter+1],l_commandArray[l_counter+2]);
						l_counter+=3;
						l_addCountryCounter+=1;
						break;
					case "editneighbor":
						d_mapModel.addBorder(l_commandArray[l_counter+1], l_commandArray[l_counter+2]);
						l_counter+=3;
						break;
				}

			}else if(l_commandArray[l_counter].equals("remove")) {
				switch(p_command) {
					case "editcontinent":
						d_mapModel.removeContinent(l_commandArray[l_counter+1]);
						l_counter+=2;
						l_removeContinentCounter+=1;
						break;
					case "editcountry":
						d_mapModel.removeCountry(l_commandArray[l_counter+1],true); 
						l_counter+=2;
						l_removeCountryCounter+=1;
						break;
					case "editneighbor":
						d_mapModel.removeBorder(l_commandArray[l_counter+1], l_commandArray[l_counter+2]);
						l_counter+=3;
						break;
				}
				
			}else {
				break;
			}
		}
		if(l_addContinentCounter>0) {
			l_ReturnString += "Number of Continents Added : " + l_addContinentCounter + "\n";
		}
		if(l_removeContinentCounter>0) {
			l_ReturnString += "Number of Continents Removed : " + l_removeContinentCounter + "\n";
		}
		if(l_addCountryCounter>0) {
			l_ReturnString += "Number of Countries Added : " + l_addCountryCounter + "\n";
		}
		if(l_removeCountryCounter>0) {
			l_ReturnString += "Number of Countries Removed : " + l_removeCountryCounter + "\n";
		}
		d_mapModel.getContinents();
		if(d_mapModel.getCountryList().size()>0) {
			d_mapModel.getCountries();
		}
		return l_ReturnString;
	}
}

