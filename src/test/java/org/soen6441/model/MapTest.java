package org.soen6441.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MapTest {
	
//	@Rule
//	public ExpectedException exception = ExpectedException.none();
//	(expected= Exception.class)
	
	@Test 
	public void testAddCountry() throws Exception {
	
		Country c1 = new Country("india","asia");
		Country c2 = new Country("china","asia");
		Country c3 = new Country("japan","asia");
		ArrayList<Country> check =  new ArrayList<Country>();
		check.add(c1);
		check.add(c2);
		check.add(c3);

		Map l_map = new Map();
		l_map.AddCountry("india","asia");
		l_map.AddCountry("china","asia");
		l_map.AddCountry("japan","asia");

		
		for (Continent co: l_map.getContinentList()){
		if (co.getContinentName().equals("asia")){
			assertEquals(check,co.getCountryList());
		}
		
	}
		
	
		
//		l_map.AddCountry(l_country, l_continent);
//		l_map.AddCountry("china", l_continent);
		
		
		
		// to check if country has been added to the list or not
//		for(Country c: l_map.getCountryList()){
//			if(c.getCountryName().contains(l_country)) {
//				flag=true;
//			}
//		}
		
		
		//Boolean flag2=false;
		//to check if the country has been added to the correct continent or not
//		for (Continent co: l_map.getContinentList()){
//			if (co.getContinentName().equals(l_continent)){
//				for(Country c1: co.getCountryList()){
//					if(c1.getCountryName().contains(l_country)) {
//						flag2=true;
//					}
//				}
//			}
//			
//		}
//		
		
		
		//assertTrue(flag);
		

		
		//assertTrue(flag2);
		
		
		
		
		
		
		
		
		
//		try {
//			l_map.AddCountry(l_country, l_continent);
//			l_map.AddCountry(l_country, l_continent);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			message = e.getMessage();
//			System.out.println(message);
//			assertFalse(message.contains(("Country Already Exist")));
//		}
				
	}

}
