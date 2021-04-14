package org.soen6441.observerpattern;

import java.io.Serializable;

/**
 * Interface observer which has one update method which is called when observable notifies it. 
 */
public interface Observer extends Serializable {
	/**
	 * This method updates the file on each log entry in LogEntryBuffer.
	 * @param p_Observable Oject of observable who notifies the Observer.
	 */
	public void update(Observable p_Observable);
}
