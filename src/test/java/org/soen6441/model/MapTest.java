package org.soen6441.model;

import java.util.ArrayList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MapTest {

	//	@Rule
	//	public ExpectedException exception = ExpectedException.none();
	//	(expected= Exception.class)

	Continent c0,c1;
	Country cc1,cc2,cc3;
	Country cc4,cc5;
	ArrayList<Country> check;
	ArrayList<Continent> checkContinent;
	Map l_map;




	@Before
	public void setTestContext() throws Exception
	{
		c0 = new Continent("asia",0);
		c1 =  new Continent("africa",0);
		cc1 = new Country("india","asia");
		cc2 = new Country("china","asia");
		cc3 = new Country("japan","asia");
		cc4 = new Country("kenya","africa");
		cc5 = new Country("egypt","africa");
		check =  new ArrayList<Country>();
		checkContinent = new ArrayList<Continent>();
		l_map = new Map();
		checkContinent.add(c0);
		checkContinent.add(c1);
		check.add(cc1);
		check.add(cc2);
		check.add(cc3);
		check.add(cc4);
		check.add(cc5);
		l_map.addContinent(c0.getContinentName(), "0");
		l_map.addContinent(c1.getContinentName(), "0");
		l_map.addCountry("india","asia");
		l_map.addCountry("china","asia");
		l_map.addCountry("japan","asia");
		l_map.addCountry("kenya","africa");
		l_map.addCountry("egypt","africa");
	}



	@Test 
	public void testAddCountry() throws Exception {

		assertTrue(l_map.getCountryList().contains(cc5));

	}


	@Test
	public void testRemoveCountry() throws Exception {

		l_map.removeCountry("egypt", true);

		assertFalse(l_map.getCountryList().contains(cc5));

	}


	@Test
	public void testAddBorder() {
		l_map.addBorder("india", "china");
		l_map.addBorder("india", "kenya");

		for(Country c: l_map.getCountryList()) {
			if(c.getCountryName().equals("india")) {
				assertTrue(c.getBorder().contains("china"));
			}
		}
	}

	@Test
	public void testRemoveBorder() {

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

	@Test
	public void testAddContinent() throws Exception{

		assertTrue(l_map.getContinentList().contains(c0));
	}
	@Test
	public void testRemoveContinent() throws Exception{
		l_map.removeContinent(c0.getContinentName());	
		assertFalse(l_map.getContinentList().contains(c0));

	}
	@Test
	public void testContinentForCountryExists() throws Exception{
		
			for(Country c : l_map.getCountryList())
			{
				int flag=0;
				for(Continent cc : l_map.getContinentList())
				{
					if(cc.getContinentName().equals(c.getContinentName()))
					{
						flag=1;break;
					}
				}
				assertEquals(1,flag);
			}
			
	}
	@Test
	public void testRemoveCountryFromContinent() throws Exception{
		l_map.removeCountryFromContinent(cc1.getCountryName(), c0.getCountryList());		
		assertFalse(c0.getCountryList().contains(cc1));
	}
	@Test
	public void testRemoveAllCountryFromContinent() throws Exception{
		l_map.removeAllCountryInContinent(c1);
		int flag=0;
		for(Country c : c1.getCountryList() )
		{
			if(l_map.getCountryList().contains(c)) {
				flag =1;break;
			}
		}
		assertEquals(0,flag);
		//assertEquals(check,l_map.getCountryList());
	}
	




}


