package org.soen6441.strategypattern;

import org.soen6441.model.Order;

public class RandomPlayerStrategy extends Strategy {

	/*
	 * A random computer player strategy that deploys on a random country,
	 * attacks random neighboring countries, and moves armies randomly between its countries
	 * */
	public RandomPlayerStrategy()
	{
		System.out.println("Random");
	}
	@Override
	public Order createOrder() {
		// TODO Auto-generated method stub
		return null;
	}
}
