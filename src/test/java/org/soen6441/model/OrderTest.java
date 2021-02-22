package org.soen6441.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {
	

	String order = "deploy india 3";
	Order o;
	Map l_map;
	private GameModelNew GameModelNew;
	@Before
	public void setContextOfOrder() throws Exception
	{

		l_map = new Map();
		l_map.addContinent("asia", "0");
		l_map.addCountry("india","asia");
		GameModelNew = new GameModelNew(l_map);
		o =  new Order(order,GameModelNew);
		
		
	}
	@Test
	public void testOrderExecute()
	{
		o.execute();
		for(Country c : l_map.getCountryList())
		{
			assertEquals(3,c.getNoOfArmies());
		}
		
	}
	

}
