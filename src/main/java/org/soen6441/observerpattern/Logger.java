package org.soen6441.observerpattern;

import java.io.FileWriter;			import java.io.PrintWriter;
import java.io.BufferedWriter;		import java.io.File;

public class Logger implements Observer {
	private static int D_Count=0;
	public void update(Observable p_Observable){
		String l_Updated= ((LogEntryBuffer) p_Observable).getResult();
		String l_Path="resource\\LogFile";
		if(D_Count==0){
			try{
				PrintWriter l_PrintWriter = new PrintWriter(l_Path);
				l_PrintWriter.println("");
				l_PrintWriter.close();
				D_Count++;

			}catch(Exception l_Exp ){}
		}

		try {
			File l_File = new File(l_Path);
			FileWriter l_FileWriter = new FileWriter(l_File, true);
			BufferedWriter l_BufferedWriter = new BufferedWriter(l_FileWriter);
			PrintWriter l_PrintWriter = new PrintWriter(l_BufferedWriter);
			l_PrintWriter.println(l_Updated);
			l_PrintWriter.close();
			l_BufferedWriter.close();
			l_FileWriter.close();
		}catch(Exception l_Exp) {}
	}
}
