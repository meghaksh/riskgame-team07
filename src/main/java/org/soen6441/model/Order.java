package org.soen6441.model;
import java.util.Scanner;


/**
 * 
 * Class to issue order for players
 *
 */
public class Order {
	private String d_Order;
	private String d_CountryId;
	private String d_NoOfArmies;
	Scanner scan = new Scanner(System.in);
	
	public Order(String order) {
		System.out.println("Enter command: ");
		d_Order = scan.nextLine(); 
		//this.d_order = order;
		deploy();
	}

	/**
	 * Method to check if the command issued is correct or not
	 */
	public void deploy() {

		String[] splitted = d_Order.split(" ");
		if(splitted[0].equals("-deploy")) {
			d_CountryId = splitted[1];
			d_NoOfArmies = splitted[2];
			System.out.println("DEPLOY "+ d_NoOfArmies + " armies on "+ d_CountryId + " country" );
		}
		else {
			System.out.println("Invalid command");
		}

	}

	/**
	 * This method is used to execute the orders issued by the players.
	 */
	public void execute() {

		//CountryObject.deployArmies();
		// how to get country object with specific ID;
		// we need a list of all countries object for that

	}

}



