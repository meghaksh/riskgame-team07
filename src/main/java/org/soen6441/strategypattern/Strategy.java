package org.soen6441.strategypattern;

import org.soen6441.model.Order;

public abstract class Strategy {
	public abstract Order createOrder();
}
