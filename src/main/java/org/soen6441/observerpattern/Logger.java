package org.soen6441.observerpattern;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.File;
import org.soen6441.controller.GameEngine;


 

public class Logger implements Observer {
	private static int count=0;
	
	
	public void update(Observable o)
	{
		
		
		String updated= ((LogEntryBuffer) o).getResult();
		String l_Path="resource\\LogFile";
		if(count==0)
		{
			try
			{
			PrintWriter pr = new PrintWriter(l_Path);
			pr.println("");
			pr.close();
			count++;
			
			}catch(Exception e )
			{}
		}
		
		try {
			File file = new File(l_Path);
			FileWriter fr = new FileWriter(file, true);
			BufferedWriter br = new BufferedWriter(fr);
			PrintWriter pr = new PrintWriter(br);
			pr.println(updated);
			pr.close();
			br.close();
			fr.close();
		}catch(Exception e) 
		{}
		
		
		
		
		
		
		
		
	}

}
