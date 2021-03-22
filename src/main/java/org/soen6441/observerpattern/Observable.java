package org.soen6441.observerpattern;

import java.util.ArrayList;


public class Observable {
	ArrayList<Observer> d_Observers= new ArrayList<Observer>();

	public void attach(Observer p_Observer){
		this.d_Observers.add(p_Observer);
	}
	public void detach(Observer p_Observer){
		if(this.d_Observers.size()!=0){
			this.d_Observers.remove(p_Observer);
		}
	}
	public void log(Observable p_Observable){
		for(Observer l_Observer: d_Observers){
			l_Observer.update(p_Observable);
		}
	}
}
