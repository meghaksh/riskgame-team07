package org.soen6441.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MapTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	
	@Test (expected= Exception.class)
	public void testAddCountry() throws Exception {
		
		String l_country = "india";
		String l_continent = "asia";
		
		Map l_map = new Map();
		
		
		l_map.AddCountry(l_country, l_continent);
		l_map.AddCountry(l_country, l_continent);
		
		
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
