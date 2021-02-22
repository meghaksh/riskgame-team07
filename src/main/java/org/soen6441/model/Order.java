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
		//System.out.println("Enter command: ");
		//d_Order = scan.nextLine(); 
		this.d_Order = order;
		this.d_GameModelNew = p_GameModelNew;
		deploy();
	}
	/**
	 * This method sends the result after execution of execute method
	 * @return returns if the order is successfully executed or not.
	 */
	public String getExecuteResult()
	{
		return d_ExecuteResult;
	}
	/**
	 * This method is used to set the result after the execution of the execute method.
	 * @param p_ExecuteResult
	 */
	public void setExecuteResult(String p_ExecuteResult)
	{
		d_ExecuteResult = p_ExecuteResult;
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
		int l_flag=0;
		
		for(Country c : d_GameModelNew.getMap().getCountryList())
		{
			if(c.getCountryName().equals(d_CountryName))
			{
				l_flag = 1;
				c.setNoOfArmies(d_NoOfArmies);
				setExecuteResult("The armies are succesfully deployed on "+d_CountryName);
			}
		}
		if(l_flag==0)
		{
			setExecuteResult("The armies are  not succesfully deployed on "+d_CountryName);
		}
		//CountryObject.deployArmies();
		// how to get country object with specific ID;
		// we need a list of all countries object for that

	}

}



