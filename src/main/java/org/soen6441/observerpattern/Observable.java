package org.soen6441.observerpattern;

import java.util.ArrayList;


public class Observable {
ArrayList<Observer> observers= new ArrayList<Observer>();
	
	public void attach(Observer o)
	{
		
		this.observers.add(o);
	}
	public void detach(Observer o)
	{
		if(this.observers.size()!=0)
		{
		this.observers.remove(o);
		}
	}
	public void Log(Observable obs)
	{
		
		
		for(Observer ob: observers)
		{
			ob.update(obs);
		}
	}
}
