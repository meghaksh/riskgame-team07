package org.soen6441.observerpattern;

public class LogEntryBuffer extends Observable {
	private String d_Result;
	private Logger d_Logger;

	public LogEntryBuffer(){
		d_Logger=new Logger();
		this.attach(d_Logger);
	}
	
	public void setResult(String p_Result){
		this.d_Result=p_Result;
		log(this);
	}
	
	public String getResult(){
		return this.d_Result;
	}
}
