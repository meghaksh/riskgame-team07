package org.soen6441.model;

import java.util.ArrayList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MapTest {
	Continent c0,c1;
	Country cc1,cc2,cc3;
	Country cc4,cc5;
	ArrayList<Country> check;
	ArrayList<Continent> checkContinent;
	Map l_map;
	
	/**
	 * To set the context before each test case
	 * @throws Exception
	 */
	@Before
	public void setTestContext() throws Exception {
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
		l_map.addContinent(c0.getContinentName(), "1");
		l_map.addContinent(c1.getContinentName(), "1");
		l_map.addCountry("india","asia");
		l_map.addCountry("china","asia");
		l_map.addCountry("japan","asia");
		l_map.addCountry("kenya","africa");
		l_map.addCountry("egypt","africa");
		l_map.addBorder("egypt", "kenya");
		l_map.addBorder("kenya", "japan");
		l_map.addBorder("japan", "china");
		l_map.addBorder("china", "india");
	}
	
	/**
	 * This test checks the functionality of addCountry()
	 * @throws Exception
	 */
	@Test 
	public void testAddCountry() throws Exception {
		assertTrue(l_map.getCountryList().contains(cc5));
	}
	
	/**
	 * This test checks the functionality of addCountry() to see if it adds a country to the continent that does not exists
	 */
	@Test 
	public void testAddCountryContinentNotExists() {
		String l_ExpectedMessage = "Continent Doesn't Exist to add a Country";
		String l_ActualMessage = "";
		try {
			l_map.addCountry("brazil", "SA");
		} catch (Exception e) {
			l_ActualMessage = e.getMessage();
		}
		assertEquals(l_ExpectedMessage, l_ActualMessage);
	}
	
	/**
	 * This test checks the functionality of addCountry() to see if it catches exception thrown for country already exists
	 */
	@Test 
	public void testAddCountryCountryExists() {
		String l_ExpectedMessage = "Country Already Exist";
		String l_ActualMessage = "";
		try {
			l_map.addCountry("egypt", "africa");
		} catch (Exception e) {
			l_ActualMessage = e.getMessage();
		}
		assertEquals(l_ExpectedMessage, l_ActualMessage);
	}

	/**
	 * This test checks the functionality of removeCountry()
	 * @throws Exception
	 */
	@Test
	public void testRemoveCountry() throws Exception {
		l_map.removeCountry("egypt", true);
		assertFalse(l_map.getCountryList().contains(cc5));
	}
	
	/**
	 * This test checks the functionality of removeCountry() to see if the exception is thrown for country does not exists  
	 */
	@Test
	public void testRemoveCountryThatDoesNotExists() {
		String l_ExpectedMessage = "Country does not exist !!";
		String l_ActualMessage = "";
		try {
			l_map.removeCountry("congo", true);
		}  catch (Exception e) {
			l_ActualMessage = e.getMessage();
		}
		assertEquals(l_ExpectedMessage, l_ActualMessage);
	}

	/**
	 * This test checks the functionality of addBorder()
	 */
	@Test
	public void testAddBorder() {
		try {
			l_map.addBorder("india", "china");
			l_map.addBorder("india", "kenya");
		} catch (Exception e) {
			e.printStackTrace();
		}		

		for(Country c: l_map.getCountryList()) {
			if(c.getCountryName().equals("india")) {
				assertTrue(c.getBorder().contains("china"));
			}
		}
	}
	
	@Test
	public void testAddBorderNeighborDoesNotExist() {
		String l_ExpectedMessage = "Neighbour Country does not exists!";
		String l_ActualMessage = "";
		try {
			l_map.addBorder("india", "congo");
		} catch (Exception e) {
			l_ActualMessage = e.getMessage();
		}
		
		assertEquals(l_ExpectedMessage, l_ActualMessage);
	}

	@Test
	public void testAddBorderCountryDoesNotExist() {
		String l_ExpectedMessage = "Country does not exists!";
		String l_ActualMessage = "";
		try {
			l_map.addBorder("congo", "india");
		} catch (Exception e) {
			l_ActualMessage = e.getMessage();
		}
		assertEquals(l_ExpectedMessage, l_ActualMessage);
	}
	
	@Test
	public void testAddBorderNeighborExist() {
		String l_ExpectedMessage = "Neighbor Already Exist";
		String l_ActualMessage = "";
		try {
			l_map.addBorder("japan", "china");
		} catch (Exception e) {
			l_ActualMessage = e.getMessage();
		}
		assertEquals(l_ExpectedMessage, l_ActualMessage);
	}
	
	@Test
	public void testRemoveBorder() throws Exception {
		l_map.removeBorder("china", "india");
		
		for(Country c: l_map.getCountryList()) {
			if(c.getCountryName().equals("china")) {
				assertFalse(c.getBorder().contains("india"));
			}
		}
	}
	
	@Test
	public void testRemoveBorderCountryDoesNotExist() {
		String l_ExpectedMessage = "Country does not exists!";
		String l_ActualMessage = "";
		try {
			l_map.removeBorder("congo", "india");
		} catch (Exception e) {
			l_ActualMessage = e.getMessage();
		}
		
		assertEquals(l_ExpectedMessage, l_ActualMessage);
	}
	
	@Test
	public void testRemoveBorderNeighborDoesNotExist() {
		String l_ExpectedMessage = "Neighbour Country does not exists!";
		String l_ActualMessage = "";
		try {
			l_map.removeBorder("india", "congo");
		} catch (Exception e) {
			l_ActualMessage = e.getMessage();
		}
		
		assertEquals(l_ExpectedMessage, l_ActualMessage);
	}

	@Test
	public void testAddContinent() throws Exception{
		assertTrue(l_map.getContinentList().contains(c0));
	}
	
	@Test
	public void testAddContinentContinentControlValue() {
		String l_ExpectedMessage = "Continent control value cannot be 0";
		String l_ActualMessage = "";
		try {
			l_map.addContinent("europe", "0");
		} catch (Exception e) {	
			l_ActualMessage = e.getMessage();
		}
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testAddContinentContinentExists() {
		String l_ExpectedMessage = "Continent Already Exists";
		String l_ActualMessage = "";
		try {
			l_map.addContinent("asia", "1");
		} catch (Exception e) {	
			l_ActualMessage = e.getMessage();
		}
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	@Test
	public void testRemoveContinent(){
		String expectedMessage = "Continent does not exist !!";
	    String msg = "";
		try {
			l_map.removeContinent(c0.getContinentName());
			l_map.removeContinent(c0.getContinentName());
		} catch (Exception e) {
			msg = e.getMessage();
		}	
		assertEquals(expectedMessage,msg);
	}
	
	@Test
	public void testContinentForCountryExists() {
			for(Country c : l_map.getCountryList()) {
				int flag=0;
				for(Continent cc : l_map.getContinentList()) {
					if(cc.getContinentName().equals(c.getContinentName())) {
						flag=1;break;
					}
				}
				assertEquals(1,flag);
			}
	}
	
	@Test
	public void testRemoveCountryFromContinent() {
		l_map.removeCountryFromContinent(cc1.getCountryName(), c0.getCountryList());		
		assertFalse(c0.getCountryList().contains(cc1));
	}
	
	@Test
	public void testRemoveAllCountryFromContinent() {
		String l_expectedMessage="";
		try {
			l_map.removeAllCountryInContinent(c1);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		int flag=0;
		for(Country c : c1.getCountryList()) {
			if(l_map.getCountryList().contains(c)) {
				flag =1;break;
			}
		}
		assertEquals(0,flag);
	}
	
	@Test
	public void testValidateMap() throws Exception {
		String l_Result = l_map.validateMap();
		assertEquals("Map is not Valid",l_Result);
		l_map.addBorder("india", "egypt");
		String l_Result1 = l_map.validateMap();
		assertEquals("Map is Valid",l_Result1);
	}
}


