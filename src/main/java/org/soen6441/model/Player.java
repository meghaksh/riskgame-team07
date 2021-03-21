package org.soen6441.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import org.soen6441.model.orders.Advance;
import org.soen6441.model.orders.Airlift;
import org.soen6441.model.orders.Blockade;
import org.soen6441.model.orders.Bomb;
import org.soen6441.model.orders.Deploy;
import org.soen6441.model.orders.Negotiate;

/**
 * The Player class represents the actual player participating in the game.
 */
public class Player {
	private String d_PlayerName="";
	private int d_PlayerId;
	private String d_PlayerColor = "";
	private int d_Armies;
	private int d_TempArmies;
	private int d_ResultInteger;
	private ArrayList<Country> d_Countries = new ArrayList<Country>();
	private Queue<Order> d_Order = new LinkedList<Order>();
	private ArrayList<Continent> d_Continents = new ArrayList<Continent>();
	private String d_Result="";
	private String d_StringOrder="";
	private GameModelNew d_GameModelNew;
	private ArrayList<String> d_Cards = new ArrayList<String>();

	/**
	 * default constructor of Player class	
	 */
	public Player() {}
	/**
	 * constructor of Player class with only player name as the parameters
	 * @param p_PlayerName  Name of the player
	 */
	Player(String p_PlayerName, GameModelNew p_GameModelNew) {
		this.d_PlayerName = p_PlayerName;
		this.d_GameModelNew = p_GameModelNew;
	}
	/**
	 * constructor with player name, player id, and player color as parameters 
	 * @param p_PlayerName Name of the player
	 * @param p_PlayerId Id of the player
	 * @param p_PlayerColor color of the player
	 */
	Player(String p_PlayerName,int p_PlayerId,String p_PlayerColor) {
		d_PlayerName = p_PlayerName;
		d_PlayerId = p_PlayerId;
		d_PlayerColor = p_PlayerColor;
	}
	/**
	 * addCountry method adds the given country to the player's country list.
	 * @param p_Country country object
	 */
	public void addCountry(Country p_Country) {
		d_Countries.add(p_Country);
	}
	/**
	 * removeCountry removes the given country from the player's country list
	 * @param p_Country Name of the country to be removed
	 */
	public void removeCountry(Country p_Country) {
		d_Countries.remove(p_Country);
	}
	/**
	 * get method for player name
	 * @return returns player name
	 */
	public String getPlayerName() {
		return this.d_PlayerName;
	}
	/**
	 * get method for player id 
	 * @return returns player id
	 */
	public int getPlayerId() {
		return this.d_PlayerId;
	}

	/**
	 * get method for player color
	 * @return returns player color
	 */
	public String getPlayerColor() {
		return this.d_PlayerColor;
	}

	/**
	 * set method for player id 
	 * @param p_PlayerId player id of player
	 */
	public void setPlayerId(int p_PlayerId) {
		this.d_PlayerId = p_PlayerId;
	}
	/**
	 * set method for player color
	 * @param p_PlayerColor Player Color of the player
	 */
	public void setPlayerColor(String p_PlayerColor) {
		this.d_PlayerColor = p_PlayerColor;
	}
	/**
	 * set method for allocating armies to player
	 * @param p_Armies Armies off the player
	 */
	public void setPlayerArmies(int p_Armies) {
		this.d_Armies = p_Armies;
		this.d_TempArmies = p_Armies;
	}
	/**
	 * get method for armies of player
	 * @return returns armies of players
	 */
	public int getPlayerArmies() {
		return this.d_Armies;
	}
	
	public GameModelNew getGameModel() {
		return this.d_GameModelNew;
	}
	
	/**
	 * set Continent list for the player. It consists of only those continent objects whose all countries belong to this player.
	 */
	public void setContinentsList() {
		ArrayList <Continent> l_MapContinents = d_GameModelNew.getSelectedMap().getContinentList();
		for(Continent l_MapContinent : l_MapContinents) {
			int l_Flag=0;
			outerloop:
				for(Country l_CountryOfContinent : l_MapContinent.getCountryList()) {
					for(Country l_CountryOfPlayer: d_Countries) {
						if(!(l_CountryOfPlayer==l_CountryOfContinent)) {
							l_Flag =1;break outerloop;
						}
					}
				}
			if(l_Flag==0) {
				d_Continents.add(l_MapContinent);
			}
		}
	}
	/**
	 * get method for continent list of the player
	 * @return returns the list of continents
	 */
	public ArrayList<Continent> getContinentList() {
		return d_Continents;
	}

	/**
	 * The getResult return the result whether the order was added to the order list or not to the Player controller.
	 * @return returns the result of issue order
	 */
	public String getResult() {
		return this.d_Result;
	}
	/**
	 * set method for result of issue order
	 * @param p_Result the result after issuing an order
	 */
	public void setResult(String p_Result) {
		this.d_Result = p_Result;
	}
	/**
	 * The setOrder method gets the order in string format for that player.
	 * @param p_Order Order entered by the player
	 */
	public void setOrder(String p_Order) {
		this.d_StringOrder = p_Order;
	}

	/**
	 * get method for the size of the country list of the player
	 * @return size of the country list
	 */
	public int getCountriesSize() {
		return this.d_Countries.size();
	}
	/**
	 * Get method for the Country list of the player
	 * @return returns the country list of the player
	 */
	public ArrayList<Country> getCountryList() {
		return this.d_Countries;
	}
	/**
	 * get method for the Order list size of the player
	 * @return size of the order list 
	 */
	public int getOrderSize() {
		return this.d_Order.size();
	}
	/**
	 * get method for the result Integer. It is a flag which defines the result of the issue order method
	 * @return integer set for determining the result of issue order method
	 */
	public int getResultInteger() {
		return this.d_ResultInteger;
	}
	/**
	 * get method for the card if the player owns it or not.
	 * @param l_TypeOfCard the string that indicates the type of card
	 * @return true if the card type exists in the list of player.
	 */
	public boolean getCard(String l_TypeOfCard)
	{
		return d_Cards.contains(l_TypeOfCard);
		/*Order l_returnCard = null;
		for(Order l_tempCard : d_Cards)
		{
			if(l_tempCard.getClass().getName().equals(l_TypeOfCard))
			{
				l_returnCard = l_tempCard;
				break;
			}
		}
		d_Cards.remove(l_returnCard);
		return l_returnCard;*/
	}
	/**
	 * set method for adding the card to the card list belonging to the player
	 * @param l_Card the Card object that belongs to the player
	 */
	public void setCard(String l_Card)
	{
		d_Cards.add(l_Card);
	}
	
	public void removeCard(String l_Card)
	{
	d_Cards.remove(l_Card);
	}
	public ArrayList getCardList()
	{
	return d_Cards;
	}
	

	/**
	 * The issue order method checks the order issued by the player whether the country it is asking for is in its country list or not
	 * and whether it has sufficient armies and it sets the result accordingly. 
	 * If the country is in the country list and if the player has sufficient armies than the order is added to its order list.
	 * There are 5 cases 
	 * <ul><li>when the result integer is 1 - The number of armies asked to deploy on a country in the list of the player is less than the number of armies with the players.The order is added into the Order List and the armies are subtracted from the armies of the player.
	 * <li>when the result integer is 2 - When the number of armies after successfully adding the order in the list becomes zero. The order is added to the Order list and the armies are subtracted from the armies of the player.
	 * <li>when the result integer is 3 - When the country asked to deploy armies doesn't belongs to the player.The order is not added to the order list.
	 * <li>when the result integer is 4 - When the number of armies asked to deploy is more than the number of armies with the player.The order is not added to the order list. 
	 * <li>when the result integer is 5 - When incorrect command is entered.The order is not added to the order list.
	 * </ul>
	 */
	public void issue_order() {
		int l_Flag = 0;
		d_ResultInteger = 0;
		String[] l_StringList = d_StringOrder.split(" ");
		String l_OrderType = l_StringList[0];
		switch(l_OrderType) {
		
		case "deploy":
			int l_NumArmies = Integer.parseInt(l_StringList[2]);
			//Country l_SourceCountry = (Country)filter(e -> e.getCountryName().equals(l_StringList[1])).forEach(e : d_GameModelNew.getSelectedMap().getCountryList());
			//System.out.println("inside deploy switch case "+l_SourceCountry.getCountryName());
			for(Country l_TempCountry: d_GameModelNew.getSelectedMap().getCountryList() )
			{
				if(l_TempCountry.getCountryName().equals(l_StringList[1]))
				{
					 d_Order.add(new Deploy(this,l_TempCountry,l_NumArmies));
					 break;
				}
			}
			break;
		case "end":
			
			break;
		case "advance" :
			int l_NumArmies1 = Integer.parseInt(l_StringList[2]);
			Country l_SourceCountry = null, l_TargetCountry = null;
			//Country l_SourceCountry = (Country) d_GameModelNew.getSelectedMap().getCountryList().stream().filter(e -> e.getCountryName().equals(l_StringList[1]));
			for(Country l_TempCountry: d_GameModelNew.getSelectedMap().getCountryList() )
			{
				if(l_TempCountry.getCountryName().equals(l_StringList[1]))
				{
					 l_SourceCountry = l_TempCountry;
					 break;
				}
			}
			for(Country l_TempCountry: d_GameModelNew.getSelectedMap().getCountryList() )
			{
				if(l_TempCountry.getCountryName().equals(l_StringList[1]))
				{
					l_TargetCountry = l_TempCountry;
					 break;
				}
			}
			d_Order.add(new Advance(this,l_SourceCountry,l_TargetCountry,l_NumArmies1));
			break;
		case "bomb":
			for(Country l_TempCountry: d_GameModelNew.getSelectedMap().getCountryList() )
			{
				if(l_TempCountry.getCountryName().equals(l_StringList[1]))
				{
					 d_Order.add(new Bomb(this,l_TempCountry));
					 break;
				}
			}break;
		case "blockade":
			for(Country l_TempCountry: d_GameModelNew.getSelectedMap().getCountryList() )
			{
				if(l_TempCountry.getCountryName().equals(l_StringList[1]))
				{
					 d_Order.add(new Blockade(this,l_TempCountry));
					 break;
				}
			}
			break;
		case "airlift":
			int l_NumArmies2 = Integer.parseInt(l_StringList[2]);
			Country l_SourceCountry1 = null, l_TargetCountry1 = null;
			//Country l_SourceCountry = (Country) d_GameModelNew.getSelectedMap().getCountryList().stream().filter(e -> e.getCountryName().equals(l_StringList[1]));
			for(Country l_TempCountry: d_GameModelNew.getSelectedMap().getCountryList() )
			{
				if(l_TempCountry.getCountryName().equals(l_StringList[1]))
				{
					 l_SourceCountry1 = l_TempCountry;
					 break;
				}
			}
			for(Country l_TempCountry: d_GameModelNew.getSelectedMap().getCountryList() )
			{
				if(l_TempCountry.getCountryName().equals(l_StringList[1]))
				{
					l_TargetCountry1 = l_TempCountry;
					 break;
				}
			}
			d_Order.add(new Airlift(this,l_SourceCountry1,l_TargetCountry1,l_NumArmies2));
			break;
		case "negotiate":
			d_Order.add(new Negotiate(this));
			break;
			
		}
			
	}
	/**
	 * This method removes the first order in the queue Order list
	 * @return returns the first order in the Order List
	 */
	public Order next_order() {
		return d_Order.remove();
	}
}