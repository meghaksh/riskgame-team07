package org.soen6441.model;
import java.util.Scanner;


/**
 * 
 * Class to issue order for players
 *
 */
public class Order {
	private String d_order;
	private String d_countryId;
	private String d_noOfArmies;
	//private HashMap<Integer, String> d_order_list;
	Scanner scan = new Scanner(System.in);
	public Order(String order) {
		System.out.println("Enter command: ");
		d_order = scan.nextLine(); 
		//this.d_order = order;
		deploy();
	}

	/**
	 * Method to check if the command issued is correct or not
	 */
	public void deploy() {

		String[] splitted = d_order.split(" ");
		//			if(i%2!=0) {
		//				if(splitted[i].contains("-add")) {
		//					System.out.println("ADD");
		//					
		//				}
		//				else if(splitted[i].contains("-remove")) {
		//					System.out.println("REMOVE");
		//				}

		if(splitted[0].equals("-deploy")) {
			d_countryId = splitted[1];
			d_noOfArmies = splitted[2];
			System.out.println("DEPLOY "+ d_noOfArmies + " armies on "+ d_countryId + " country" );
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



