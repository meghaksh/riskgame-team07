package org.soen6441.model.orders;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.soen6441.model.Country;
import org.soen6441.model.Order;
import org.soen6441.model.Player;

public class Advance implements Order {
	Country d_SourceCountry,d_TargetCountry;
	Player d_Player;
	int d_NumArmies;
//	HashMap<Integer, String> d_Cards = new HashMap<>();	
	public Advance() {
		
		
		/*Source should not be equal to target
		 * Source and target should be neighbors
		 * Source should have at least one army present after move/attack 
		 * win happens only when number of armies of defender is zero. and then only card is issued.
		 * Attack with the lesser number of armies than the player owns. */
		/*Player Reference who is issuing advance command. 
		 * From & To countries
		 * Number of Armies 
		 * 
		 * 
		 * 
		 * Check if both countries(To & From) belongs to the same player. 
		 * If yes, then simply move the armies to the "To" country from "From"
		 * If No, Write the logic for the attack. 
		 * Once attack is won, assign random card to the player who won.
		 *  (Country which wins the attack, search for it's owner and assign a card to that owner ) */
	}
	public Advance(Player l_Player, Country l_SourceCountry, Country l_TargetCountry, int l_NumArmies1) 
	{
		d_Player = l_Player;
		d_SourceCountry = l_SourceCountry;
		d_TargetCountry = l_TargetCountry;
		d_NumArmies = l_NumArmies1;
	//	int i=0;
	//	d_Cards.put(i++, "Bomb");
	//	d_Cards.put(i++, "Blockade");
	//	d_Cards.put(i++, "Negotiate");
	//	d_Cards.put(i++,"Airlift");
		
	}
	@Override
	public void execute()
	{
		Random l_rand = new Random();
		int l_flag = isValid();
		if(l_flag == 1)
		{
			d_SourceCountry.setNoOfArmies(d_SourceCountry.getNoOfArmies()-d_NumArmies);
			d_TargetCountry.setNoOfArmies(d_TargetCountry.getNoOfArmies()+d_NumArmies);
		}
		if(l_flag==2)
		{
			if(d_TargetCountry.getNoOfArmies()==0)
			{
				d_TargetCountry.getCountryOwnerPlayer().removeCountry(d_TargetCountry);
				d_TargetCountry.setCountryOwnerPlayer(d_Player);
				d_TargetCountry.setNoOfArmies(d_NumArmies);
				d_SourceCountry.setNoOfArmies(d_SourceCountry.getNoOfArmies()-d_NumArmies);
				d_Player.addCountry(d_TargetCountry);
				d_Player.setAtleastOneBattleWon(true);
		//		int l_cardInteger = l_rand.nextInt(4);
				
			//	d_Player.setCard(d_Cards.get(l_cardInteger));
				
				d_Player.setResult("\n"+d_Player.getPlayerName()+" your attack on "+d_SourceCountry+" was a Success!!");
				return;
			}
			HashMap <Integer,Integer> l_AttackerArmies = new HashMap<>(); 
			HashMap <Integer,Integer> l_DefenderArmies = new HashMap<>(); 
			HashMap <Integer,Integer> l_AttackerArmiesinHand = new HashMap<>();
			HashMap <Integer,Integer> l_DefenderArmiesinHand = new HashMap<>();
			
			for(int i=0;i<d_NumArmies;i++)
			{
				l_AttackerArmies.put(i, l_rand.nextInt(6));
			}
			for(int i=0;i<d_TargetCountry.getNoOfArmies();i++)
			{
				l_AttackerArmies.put(i, l_rand.nextInt(7));
			}
			int l_SizeDiff = l_AttackerArmies.size() - l_DefenderArmies.size(); 
		
		HashMap<Integer, Integer> returnedHashMap = ArmiestoFight(l_SizeDiff,l_AttackerArmies,l_DefenderArmies);
		if(l_SizeDiff>0)
		{
			l_AttackerArmiesinHand = returnedHashMap;
		}
		else if(l_SizeDiff<0)
		{
			l_DefenderArmiesinHand = returnedHashMap;
		}
		else
		{
			l_AttackerArmiesinHand = l_AttackerArmies;
			l_DefenderArmiesinHand = l_DefenderArmies;
		}
		//now attack
		Iterator<Map.Entry<Integer,Integer>> itr_Attacker = l_AttackerArmiesinHand.entrySet().iterator();
		Iterator<Map.Entry<Integer,Integer>> itr_Defender = l_DefenderArmiesinHand.entrySet().iterator();
		int l_attackWin=0,l_defendWin=0;
		for(int i=0;i<l_AttackerArmiesinHand.size();i++)
		{
			Map.Entry<Integer,Integer> entry_Attack = itr_Attacker.next(); 
			Map.Entry<Integer,Integer> entry_Defend = itr_Defender.next(); 
			if(entry_Attack.getValue()>=entry_Defend.getValue())
			{
				l_attackWin++;
			}
			else
			{
				l_defendWin++;
			}
		}
		if(l_attackWin>=l_defendWin)
		{
			d_TargetCountry.getCountryOwnerPlayer().removeCountry(d_TargetCountry);
			d_TargetCountry.setCountryOwnerPlayer(d_Player);
			d_TargetCountry.setNoOfArmies(l_attackWin);
			d_SourceCountry.setNoOfArmies(d_SourceCountry.getNoOfArmies()-d_NumArmies);
			d_Player.addCountry(d_TargetCountry);
			d_Player.setAtleastOneBattleWon(true);
			
	//		int l_cardInteger = l_rand.nextInt(4);
			
	//		d_Player.setCard(d_Cards.get(l_cardInteger));
			
			d_Player.setResult("\n"+d_Player.getPlayerName()+" your attack on "+d_SourceCountry+" was a Success!!");
		}
		else
		{
			d_Player.setResult("\n"+d_Player.getPlayerName()+" your attack on "+d_SourceCountry+" was a Failure!!");
		}
	}
		
	}
	HashMap<Integer,Integer> ArmiestoFight(int sizeDiff, HashMap <Integer,Integer> l_AttackerArmies,HashMap <Integer,Integer> l_DefenderArmies)
	{
		HashMap<Integer,Integer> returnHashMap = null;
		if(sizeDiff>0)
		{
			List<Entry<Integer, Integer>> list = new LinkedList<Entry<Integer, Integer>>(l_AttackerArmies.entrySet()); 
			Collections.sort(list, new Comparator<Entry<Integer, Integer>>()   
			{  
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2)   
			{   
				return o2.getValue().compareTo(o1.getValue());  
			}  
			});
			HashMap <Integer,Integer> l_SortedAttackerArmies = new HashMap<>(); 
			for (Entry<Integer, Integer> entry : list)   
			{  
				l_SortedAttackerArmies.put(entry.getKey(), entry.getValue());  
			}
			HashMap <Integer,Integer> l_AttackerArmiesinHand = new HashMap<>(); 
			Iterator<Map.Entry<Integer,Integer>> itr = l_SortedAttackerArmies.entrySet().iterator();
			for(int i=0;i<sizeDiff;i++)
			{
				Map.Entry<Integer,Integer> entry = itr.next(); 
				l_AttackerArmiesinHand.put(entry.getKey(),entry.getValue());
			}
			 returnHashMap = l_AttackerArmiesinHand;
		}
		else if(sizeDiff<0)
		{
			List<Entry<Integer, Integer>> list = new LinkedList<Entry<Integer, Integer>>(l_DefenderArmies.entrySet()); 
			Collections.sort(list, new Comparator<Entry<Integer, Integer>>()   
			{  
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2)   
			{   
				return o2.getValue().compareTo(o1.getValue());  
			}  
			});
			HashMap <Integer,Integer> l_SortedDefenderArmies = new HashMap<>(); 
			for (Entry<Integer, Integer> entry : list)   
			{  
				l_SortedDefenderArmies.put(entry.getKey(), entry.getValue());  
			}
			HashMap <Integer,Integer> l_DefenderArmiesinHand = new HashMap<>(); 
			Iterator<Map.Entry<Integer,Integer>> itr = l_SortedDefenderArmies.entrySet().iterator();
			for(int i=0;i<sizeDiff;i++)
			{
				Map.Entry<Integer,Integer> entry = itr.next(); 
				l_DefenderArmiesinHand.put(entry.getKey(),entry.getValue());
			}
			returnHashMap =  l_DefenderArmiesinHand;
		}
		return returnHashMap;
	}
	public int isValid()
	{
		
		int l_ReturnInt=0;
		if(d_Player.getNegotiatedPlayerList().contains(d_TargetCountry.getCountryOwnerPlayer()))
		{
			d_Player.setResult("\nThe targeted country "+d_TargetCountry+" belongs to "+d_TargetCountry.getCountryOwnerPlayer()+" which is negotiated player!");
			l_ReturnInt=0;
		}
		if(d_TargetCountry.getCountryOwnerPlayer().getPlayerName().equals("Neutral Player"))
		{
			d_Player.setResult("\nThe targeted country "+d_TargetCountry+" belongs to Neutral Player!");
			l_ReturnInt=0;
		}
		else if(d_SourceCountry==d_TargetCountry)
		{
			d_Player.setResult("\nThe source country and target country cannot be same!");
			l_ReturnInt= 0;
		}
		else if(!d_SourceCountry.getBorder().contains(d_TargetCountry.getCountryName()))
		{
			d_Player.setResult("\nThe source country and target country are not neighbours!");
			l_ReturnInt= 0;
		}
		else if(d_SourceCountry.getNoOfArmies()-d_NumArmies < 1)
		{
			d_Player.setResult("\nThe source country should be left with atleast one army!");
			l_ReturnInt= 0;
		}
		else
		{
			if(d_Player.getCountryList().contains(d_SourceCountry) && d_Player.getCountryList().contains(d_TargetCountry))
			{
				d_Player.setResult("\nThe source country and target country belong to the same player");
				l_ReturnInt= 1;
			}
			else if(d_Player.getCountryList().contains(d_SourceCountry) && !d_Player.getCountryList().contains(d_TargetCountry))
			{
				l_ReturnInt= 2;
			}
			
		}
		return l_ReturnInt;
		
	}
}
