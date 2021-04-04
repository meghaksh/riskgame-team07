package org.soen6441.adapterpattern;

import java.io.File;
import java.util.Scanner;

import org.soen6441.controller.GameEngine;
import org.soen6441.model.Continent;

public class Adaptee {

public String loadConquestMap(String p_S,GameEngine p_GameEngine) {
	try {
		System.out.println("reached in loadmap");
		String l_Path="resource\\",l_Result;
		File l_File =new File(l_Path+p_S);
		Scanner l_Sc = new Scanner(l_File);
		while(l_Sc.hasNextLine())
		{
				String l_Line=l_Sc.nextLine();
				if(l_Line.contains("Continents"))
				{	
					l_Line=l_Sc.nextLine();
					while(!l_Line.equals("") && l_Sc.hasNextLine())
					{
						String[] l_Arr = l_Line.split("=", 2);
						System.out.println(l_Arr[0]+" This is name");
						System.out.println(l_Arr[1]+" This is control value");
						p_GameEngine.getGameModel().getMap().addContinent(l_Arr[0],l_Arr[1]);
						l_Line=l_Sc.nextLine();
					}
				}
				if(l_Line.contains("Territories"))
				{
					l_Line=l_Sc.nextLine();
					while(!l_Line.equals("") && l_Sc.hasNextLine())
					{
						
						String[] l_Arr1=l_Line.split(",");
						p_GameEngine.getGameModel().getMap().addCountry(l_Arr1[0], l_Arr1[3]);
						l_Line=l_Sc.nextLine();
					}
				}
			}
		}catch(Exception p_E)
		{System.out.println(p_E);}

		return "success";
	}

}
