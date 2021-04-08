package org.soen6441.strategypattern;

import org.soen6441.model.Order;

public class HumanPlayerStrategy extends Strategy {
	
	public HumanPlayerStrategy()
	{
		System.out.println("Human");
	}

	@Override
	public Order createOrder() {
		// TODO Auto-generated method stub
		return null;
	}

}
