package org.soen6441.adapterpattern;

import java.io.File;
import java.util.Scanner;

import org.soen6441.controller.GameEngine;

public class Adaptee {

public String loadConquestMap(String p_S,GameEngine p_GameEngine) {
	try {
		p_GameEngine.getGameModel().getMap().reset();
		String l_Path="resource\\";
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
		File l_File2 =new File(l_Path+p_S);
		Scanner l_Sc2 = new Scanner(l_File2);
		while(l_Sc2.hasNextLine())
		{
			String l_Line=l_Sc2.nextLine();
			if(l_Line.contains("Territories"))
			{
				l_Line=l_Sc2.nextLine();
				while(!l_Line.equals("") && l_Sc2.hasNextLine())
				{
					String[] l_Arr1=l_Line.split(",");
					for(int k=4;k<l_Arr1.length;k++)
					{
						
						p_GameEngine.getGameModel().getMap().addBorder(l_Arr1[0], l_Arr1[k]);
						
				}
					l_Line=l_Sc2.nextLine();
			}
		}
		}
		l_Sc.close();
		l_Sc2.close();
		String l_Result1=p_GameEngine.getGameModel().getMap().validateMap();
		if(l_Result1.equals("Map is not Valid")){
			p_GameEngine.getGameModel().getMap().reset();
			return l_Result1;
		}
	}catch(Exception p_E)
		{//System.out.println(p_E);
		}
		

		return "The Map is loaded with "+p_GameEngine.getGameModel().getMap().getContinentList().size()+" Continents and "+p_GameEngine.getGameModel().getMap().getCountryList().size()+" Countries";
	}

}
