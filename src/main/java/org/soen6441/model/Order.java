package org.soen6441.model;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * 
 * Class to issue order for players
 *
 */
public class Order {
	private String d_Order;
	private String d_CountryName;
	private int d_NoOfArmies;
	Scanner scan = new Scanner(System.in);
	private GameModelNew d_GameModelNew;
	private ArrayList<Country> d_Countries;
	
	public Order(String order, GameModelNew p_GameModelNew) {
		//System.out.println("Enter command: ");
		//d_Order = scan.nextLine(); 
		this.d_Order = order;
		this.d_GameModelNew = p_GameModelNew;
		deploy();
	}

	/**
	 * Method to check if the command issued is correct or not
	 */
	public void deploy() {

		String[] splitted = d_Order.split(" ");
		if(splitted[0].equals("deploy")) {
			d_CountryName = splitted[1];
			d_NoOfArmies = Integer.parseInt(splitted[2]);
			System.out.println("DEPLOY "+ d_NoOfArmies + " armies on "+ d_CountryName + " country" );
		}
		else {
			System.out.println("Invalid command");
		}

	}

	/**
	 * This method is used to execute the orders issued by the players.
	 */
	public void execute() {
		
		for(Country c : d_GameModelNew.getMap().getCountryList())
		{
			if(c.getCountryName().equals(d_CountryName))
			{
				c.setNoOfArmies(d_NoOfArmies);
			}
		}
		//CountryObject.deployArmies();
		// how to get country object with specific ID;
		// we need a list of all countries object for that

	}

}



