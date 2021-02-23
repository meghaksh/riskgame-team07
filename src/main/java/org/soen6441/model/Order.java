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
	private String d_ExecuteResult="";
	
	public Order(String order, GameModelNew p_GameModelNew) {
		this.d_Order = order;
		this.d_GameModelNew = p_GameModelNew;
		System.out.println("gAme model new: "+ d_GameModelNew);
		deploy();
	}
	/**
	 * This method sends the result after execution of execute method
	 * @return returns if the order is successfully executed or not.
	 */
	public String getExecuteResult() {
		return this.d_ExecuteResult;
	}
	/**
	 * This method is used to set the result after the execution of the execute method.
	 * @param p_ExecuteResult
	 */
	public void setExecuteResult(String p_ExecuteResult) {
		this.d_ExecuteResult = p_ExecuteResult;
	}

	/**
	 * Method to check if the command issued is correct or not
	 */
	public void deploy() {

		String[] splitted = d_Order.split(" ");
		if(splitted[0].equals("deploy")) {
			this.d_CountryName = splitted[1];
			this.d_NoOfArmies = Integer.parseInt(splitted[2]);
			System.out.println("DEPLOY "+ d_NoOfArmies + " armies on "+ d_CountryName + " country" );
		}
		else {
			System.out.println("Invalid command");
		}
	}
	
	public String getOrder() {
		return this.d_Order;
	}

	/**
	 * This method is used to execute the orders issued by the players.
	 */
	public void execute() {
		int l_flag=0;
		for(Country l_Country : d_GameModelNew.getSelectedMap().getCountryList()) {
			System.out.println("Country"+ l_Country.getCountryName());
			if(l_Country.getCountryName().equals(d_CountryName)) {
				l_flag = 1;
				System.out.println("No of armies: "+d_NoOfArmies);
				l_Country.setNoOfArmies(d_NoOfArmies);
				setExecuteResult("\n"+"The armies are succesfully deployed on "+d_CountryName);
			}
		}
		if(l_flag==0) {
			setExecuteResult("\n"+"The armies are  not succesfully deployed on "+d_CountryName);
		}
	}
}