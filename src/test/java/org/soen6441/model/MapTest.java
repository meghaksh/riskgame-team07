package org.soen6441.model;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * To test the methods of Map.java 
 * 
 *
 */
public class MapTest {
	Continent d_C0,d_C1;
	Country d_Country1,d_Country2,d_Country3,d_Country4,d_Country5;
	ArrayList<Country> d_Check;
	ArrayList<Continent> d_CheckContinent;
	Map l_Map;
	
	/**
	 * To set the context before each test case
	 * @throws Exception relevant for the map creation phase
	 */
	@Before
	public void setTestContext() throws Exception {
		d_C0 = new Continent("asia",0);
		d_C1 =  new Continent("africa",0);
		d_Country1 = new Country("india","asia");
		d_Country2 = new Country("china","asia");
		d_Country3 = new Country("japan","asia");
		d_Country4 = new Country("kenya","africa");
		d_Country5 = new Country("egypt","africa");
		d_Check =  new ArrayList<Country>();
		d_CheckContinent = new ArrayList<Continent>();
		l_Map = new Map();
		d_CheckContinent.add(d_C0);
		d_CheckContinent.add(d_C1);
		d_Check.add(d_Country1);
		d_Check.add(d_Country2);
		d_Check.add(d_Country3);
		d_Check.add(d_Country4);
		d_Check.add(d_Country5);
		l_Map.addContinent(d_C0.getContinentName(), "1");
		l_Map.addContinent(d_C1.getContinentName(), "1");
		l_Map.addCountry("india","asia");
		l_Map.addCountry("china","asia");
		l_Map.addCountry("japan","asia");
		l_Map.addCountry("kenya","africa");
		l_Map.addCountry("egypt","africa");
		l_Map.addBorder("egypt", "kenya");
		l_Map.addBorder("kenya", "japan");
		l_Map.addBorder("japan", "china");
		l_Map.addBorder("china", "india");
	}
	
	/**
	 * This test d_Checks the functionality of addCountry()
	 * 
	 */
	@Test 
	public void testAddCountry() {
		assertTrue(l_Map.getCountryList().contains(d_Country5));
	}
	
	/**
	 * This test d_Checks the functionality of addCountry() to see if it adds a country to the continent that does not exists
	 */
	@Test 
	public void testAddCountryContinentNotExists() {
		String l_ExpectedMessage = "Continent Doesn't Exist to add a Country";
		String l_ActualMessage = "";
		try {
			l_Map.addCountry("brazil", "SA");
		} catch (Exception e) {
			l_ActualMessage = e.getMessage();
		}
		assertEquals(l_ExpectedMessage, l_ActualMessage);
	}
	
	/**
	 * This test d_Checks the functionality of addCountry() to see if it catches exception thrown for country already exists
	 */
	@Test 
	public void testAddCountryCountryExists() {
		String l_ExpectedMessage = "Country Already Exist";
		String l_ActualMessage = "";
		try {
			l_Map.addCountry("egypt", "africa");
		} catch (Exception e) {
			l_ActualMessage = e.getMessage();
		}
		assertEquals(l_ExpectedMessage, l_ActualMessage);
	}

	/**
	 * This test d_Checks the functionality of removeCountry()
	 * @throws Exception If country does not exists
	 */
	@Test
	public void testRemoveCountry() throws Exception {
		l_Map.removeCountry("egypt", true);
		assertFalse(l_Map.getCountryList().contains(d_Country5));
	}
	
	/**
	 * This test d_Checks the functionality of removeCountry() to see if the exception is thrown for country does not exists  
	 */
	@Test
	public void testRemoveCountryThatDoesNotExists() {
		String l_ExpectedMessage = "Country does not exist !!";
		String l_ActualMessage = "";
		try {
			l_Map.removeCountry("congo", true);
		}  catch (Exception e) {
			l_ActualMessage = e.getMessage();
		}
		assertEquals(l_ExpectedMessage, l_ActualMessage);
	}

	/**
	 * This test d_Checks the functionality of addBorder()
	 */
	@Test
	public void testAddBorder() {
		try {
			l_Map.addBorder("india", "china");
			l_Map.addBorder("india", "kenya");
		} catch (Exception e) {
			e.printStackTrace();
		}		

		for(Country c: l_Map.getCountryList()) {
			if(c.getCountryName().equals("india")) {
				assertTrue(c.getBorder().contains("china"));
			}
		}
	}
	
	/**
	 * To test addBorder() to d_Check if exception is thrown and caught on adding neighbouring country that does not exists
	 */
	@Test
	public void testAddBorderNeighborDoesNotExist() {
		String l_ExpectedMessage = "Neighbour Country does not exists!";
		String l_ActualMessage = "";
		try {
			l_Map.addBorder("india", "congo");
		} catch (Exception e) {
			l_ActualMessage = e.getMessage();
		}
		
		assertEquals(l_ExpectedMessage, l_ActualMessage);
	}

	/**
	 * To test addBorder() to d_Check if exception is thrown and caught on adding country that does not exists
	 */
	@Test
	public void testAddBorderCountryDoesNotExist() {
		String l_ExpectedMessage = "Country does not exists!";
		String l_ActualMessage = "";
		try {
			l_Map.addBorder("congo", "india");
		} catch (Exception e) {
			l_ActualMessage = e.getMessage();
		}
		assertEquals(l_ExpectedMessage, l_ActualMessage);
	}
	
	/**
	 * To test addBorder() to d_Check if exception is thrown and caught on adding border that already exists
	 */
	@Test
	public void testAddBorderNeighborExist() {
		String l_ExpectedMessage = "Neighbor Already Exist";
		String l_ActualMessage = "";
		try {
			l_Map.addBorder("japan", "china");
		} catch (Exception e) {
			l_ActualMessage = e.getMessage();
		}
		assertEquals(l_ExpectedMessage, l_ActualMessage);
	}
	
	/**
	 * to test the functionality of removeBorder()
	 * @throws Exception If border does not exists, or country does not exists
	 */
	@Test
	public void testRemoveBorder() throws Exception {
		l_Map.removeBorder("china", "india");
		
		for(Country l_Country: l_Map.getCountryList()) {
			if(l_Country.getCountryName().equals("china")) {
				assertFalse(l_Country.getBorder().contains("india"));
			}
		}
	}
	
	/**
	 * To test removeBorder() to d_Check if exception is thrown and caught on adding country that does not exists
	 */
	@Test
	public void testRemoveBorderCountryDoesNotExist() {
		String l_ExpectedMessage = "Country does not exists!";
		String l_ActualMessage = "";
		try {
			l_Map.removeBorder("congo", "india");
		} catch (Exception e) {
			l_ActualMessage = e.getMessage();
		}
		
		assertEquals(l_ExpectedMessage, l_ActualMessage);
	}
	
	/**
	 * To test removeBorder() to d_Check if exception is thrown and caught on adding neighboring country that does not exists
	 */
	@Test
	public void testRemoveBorderNeighborDoesNotExist() {
		String l_ExpectedMessage = "Neighbour Country does not exists!";
		String l_ActualMessage = "";
		try {
			l_Map.removeBorder("india", "congo");
		} catch (Exception e) {
			l_ActualMessage = e.getMessage();
		}
		assertEquals(l_ExpectedMessage, l_ActualMessage);
	}

	/**
	 * To d_Check the functionality of addContinent
	 * 
	 */
	@Test
	public void testAddContinent() {
		assertTrue(l_Map.getContinentList().contains(d_C0));
	}
	
	/**
	 * To test addContinent() to d_Check  the continent control value
	 */
	@Test
	public void testAddContinentContinentControlValue() {
		String l_ExpectedMessage = "Continent control value cannot be 0";
		String l_ActualMessage = "";
		try {
			l_Map.addContinent("europe", "0");
		} catch (Exception e) {	
			l_ActualMessage = e.getMessage();
		}
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	/**
	 * To test addContinent() and d_Check if the Continent already Exists or not
	 */
	@Test
	public void testAddContinentContinentExists() {
		String l_ExpectedMessage = "Continent Already Exists";
		String l_ActualMessage = "";
		try {
			l_Map.addContinent("asia", "1");
		} catch (Exception e) {	
			l_ActualMessage = e.getMessage();
		}
		assertEquals(l_ExpectedMessage,l_ActualMessage);
	}
	
	/**
	 * To test removeContinent() for its functionality
	 */
	@Test
	public void testRemoveContinent(){
		String expectedMessage = "Continent does not exist !!";
	    String msg = "";
		try {
			l_Map.removeContinent(d_C0.getContinentName());
			l_Map.removeContinent(d_C0.getContinentName());
		} catch (Exception e) {
			msg = e.getMessage();
		}	
		assertEquals(expectedMessage,msg);
	}
	
	/**
	 * To test Continent and d_Check if country already exits or not
	 */
	@Test
	public void testContinentForCountryExists() {
			for(Country c : l_Map.getCountryList()) {
				int l_Flag=0;
				for(Continent cc : l_Map.getContinentList()) {
					if(cc.getContinentName().equals(c.getContinentName())) {
						l_Flag=1;break;
					}
				}
				assertEquals(1,l_Flag);
			}
	}
	
	/**
	 * To test functionality removeCountry() from the continent
	 */
	@Test
	public void testRemoveCountryFromContinent() {
		l_Map.removeCountryFromContinent(d_Country1.getCountryName(), d_C0.getCountryList());		
		assertFalse(d_C0.getCountryList().contains(d_Country1));
	}
	
	/**
	 * To test removeAllCountryFromContinent() method of Map.java
	 */
	@Test
	public void testRemoveAllCountryFromContinent() {
		try {
			l_Map.removeAllCountryInContinent(d_C1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int l_Flag=0;
		for(Country c : d_C1.getCountryList()) {
			if(l_Map.getCountryList().contains(c)) {
				l_Flag =1;break;
			}
		}
		assertEquals(0,l_Flag);
	}
	
	/**
	 * To test the Validation of Map and Check whether it is a connected graph or not 
	 * @throws Exception for Add borders and if continent is not a connected subgraph
	 */
	@Test
	public void testValidateMap() throws Exception {
		String l_Actual="", l_Expected="Map is Valid";
		l_Map.addBorder("india", "kenya");
		l_Map.addBorder("kenya", "egypt");
		l_Map.addBorder("india", "japan");
		l_Actual = l_Map.validateMap();
		assertEquals(l_Expected,l_Actual);
	}


/**
 * To test the Validation of Map and Check whether it is not a connected graph (Here continent is a connnected subgraph but the two continents are not)
 * @throws Exception for Add borders and if continent is not a connected subgraph
 */
@Test
public void testValidateMapFalse() throws Exception {
	String l_Actual="", l_Expected="Map is not Valid";
	l_Map.addBorder("kenya", "egypt");
	l_Map.addBorder("india", "japan");
	l_Actual = l_Map.validateMap();
	assertEquals(l_Expected,l_Actual);
}


/**
 * To test the Validation of Map and Check whether continent is a connected subgraph or not
 * @throws Exception for Add borders and if continent is not a connected subgraph
 */
@Test
public void testValidateMapForContinents() throws Exception {
	String l_Result="", l_ResultExpected = "";
	String  l_Actual="", l_Expected="The countries inside asia are not internally Connected";
	l_Map.addBorder("india", "kenya");
	l_Map.addBorder("kenya", "egypt");
	try {
	l_Result = l_Map.validateMap();
	} catch (Exception e) {
		l_Actual = e.getMessage();
	}
	assertEquals(l_Expected,l_Actual);
	assertEquals(l_ResultExpected,l_Result);
}
}