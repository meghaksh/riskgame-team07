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
	public String SaveMap(String p_str)throws Exception {
		String[] l_commandArray = p_str.split(" ");
		String l_result=d_mapModel.SaveMap(l_commandArray[1]);
		return l_result;
	}
	public String LoadMap(String p_str)throws Exception{
		String[] l_commandArray = p_str.split(" ");
		if(l_commandArray[0].equals("editmap")) {
			String l_result=d_mapModel.LoadMap(l_commandArray[1]);
			l_result=l_result+". You Can Now Edit IT";
			return l_result;
		}else {
			String l_result=d_mapModel.LoadMap(l_commandArray[1]);
			l_result=l_result+". You Can Now Proceed To Add Players";
			return l_result;
		}
		
	}
	public String ValidateMap() {
		return d_mapModel.validateMap();
	}
	public String EditMap(String p_command, String p_str)throws Exception {
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
						d_mapModel.AddContinent(l_commandArray[l_counter+1],l_commandArray[l_counter+2]);
						l_counter+=3;
						l_addContinentCounter+=1;
						break;
					case "editcountry":
						d_mapModel.AddCountry(l_commandArray[l_counter+1],l_commandArray[l_counter+2]);
						l_counter+=3;
						l_addCountryCounter+=1;
						break;
					case "editneighbor":
						d_mapModel.AddBorder(l_commandArray[l_counter+1], l_commandArray[l_counter+2]);
						l_counter+=3;
						break;
				}

			}else if(l_commandArray[l_counter].equals("remove")) {
				switch(p_command) {
					case "editcontinent":
						d_mapModel.RemoveContinent(l_commandArray[l_counter+1]);
						l_counter+=2;
						l_removeContinentCounter+=1;
						break;
					case "editcountry":
						d_mapModel.RemoveCountry(l_commandArray[l_counter+1]);
						l_counter+=2;
						l_removeCountryCounter+=1;
						break;
					case "editneighbor":
						d_mapModel.RemoveBorder(l_commandArray[l_counter+1], l_commandArray[l_counter+2]);
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

