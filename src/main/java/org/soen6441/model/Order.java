package org.soen6441.model;
import java.util.Scanner;

/**
 * 
 * Class for Order of the players
 *
 */
public class Order {
	private String d_Order;
	private String d_CountryName;
	private int d_NoOfArmies;
	private GameModelNew d_GameModelNew;
	private String d_ExecuteResult="";
	
	public Order(String order, GameModelNew p_GameModelNew) {
		this.d_Order = order;
		this.d_GameModelNew = p_GameModelNew;
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
	 * @param p_ExecuteResult String to set the executed result
	 */
	public void setExecuteResult(String p_ExecuteResult) {
		this.d_ExecuteResult = p_ExecuteResult;
	}

	/**
	 * Method to check if the command issued is correct or not and set country name and the armies
	 */
	public void deploy() {
		String[] splitted = d_Order.split(" ");
		if(splitted[0].equals("deploy")) {
			this.d_CountryName = splitted[1];
			this.d_NoOfArmies = Integer.parseInt(splitted[2]);
		}
		else {
			System.out.println("Invalid command");
		}
	}
	/**
	 * This method returns the order of the player
	 * @return the order issues by the player
	 */
	public String getOrder() {
		return this.d_Order;
	}

	/**
	 * This method is used to execute the orders issued by the players.
	 */
	public void execute() {
		int l_flag=0;
		for(Country l_Country : d_GameModelNew.getSelectedMap().getCountryList()) {
			if(l_Country.getCountryName().equals(d_CountryName)) {
				l_flag = 1;
				int l_Armies = l_Country.getNoOfArmies();
				l_Country.setNoOfArmies(d_NoOfArmies+l_Armies);
				setExecuteResult("\n"+"The armies are succesfully deployed on "+d_CountryName);
			}
		}
		if(l_flag==0) {
			setExecuteResult("\n"+"The armies are  not succesfully deployed on "+d_CountryName);
		}
	}
}