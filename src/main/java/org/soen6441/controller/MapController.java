package org.soen6441.controller;

import org.soen6441.model.Map;

public class MapController {
	private Map d_map;
	public MapController(Map p_map){
		d_map = p_map;
	}
	
	public String EditContinent(String p_Str)throws Exception {
		String[] l_commandArray = p_Str.split(" ");
		int l_Counter = 1;
		int l_AddCounter = 0;
		int l_RemoveCounter = 0;
		String l_ReturnString = "";
		while(l_Counter<l_commandArray.length) {
			if(l_commandArray[l_Counter].equals("add")) {
				d_map.AddContinent(l_commandArray[l_Counter+1],l_commandArray[l_Counter+2] );
				l_Counter+=3;
				l_AddCounter+=1;
			}else if(l_commandArray[l_Counter].equals("remove")) {
				d_map.RemoveContinent(l_commandArray[l_Counter+1]);
				l_Counter+=2;
				l_RemoveCounter+=1;
			}else {
				break;
			}
		}
		if(l_AddCounter>0) {
			l_ReturnString += "Number of Countries Added : " + l_AddCounter + "\n";
		}
		if(l_RemoveCounter>0) {
			l_ReturnString += "Number of Countries Removed : " + l_RemoveCounter + "\n";
		}
		d_map.getContinents();
		return l_ReturnString;
	}
}

