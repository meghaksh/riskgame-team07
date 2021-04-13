package org.soen6441.strategypattern;

import org.soen6441.model.Order;
import org.soen6441.observerpattern.LogEntryBuffer;

public abstract class Strategy {
	LogEntryBuffer d_Leb = new LogEntryBuffer();
	public abstract Order createOrder();
}
