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
import java.util.TreeMap;

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
				

				d_Player.setResult("\n"+d_Player.getPlayerName()+" your attack on "+d_SourceCountry.getCountryName()+" was a Success!!");
				return;
			}
			TreeMap <Integer,Integer> l_AttackerArmies = new TreeMap<>(); 
			TreeMap <Integer,Integer> l_DefenderArmies = new TreeMap<>(); 
			TreeMap <Integer,Integer> l_AttackerArmiesinHand = new TreeMap<>();
			TreeMap <Integer,Integer> l_DefenderArmiesinHand = new TreeMap<>();

			for(int i=0;i<d_NumArmies;i++)
			{
				l_AttackerArmies.put(i, l_rand.nextInt(6));
			}
			for(int i=0;i<d_TargetCountry.getNoOfArmies();i++)
			{
				l_DefenderArmies.put(i, l_rand.nextInt(7));
			}
			l_AttackerArmiesinHand=l_AttackerArmies;
			l_DefenderArmiesinHand=l_DefenderArmies;
			int l_SizeDiff = l_AttackerArmies.size() - l_DefenderArmies.size(); 

			int l_MinArmies=Math.min(l_AttackerArmies.size(),l_DefenderArmies.size() );
			System.out.println("min armies"+l_MinArmies);

			try{
				TreeMap<Integer, Integer> returnedHashMap = ArmiestoFight(l_SizeDiff,l_MinArmies,l_AttackerArmies,l_DefenderArmies);
				System.out.println("returned hashmap "+returnedHashMap);
				Iterator<Map.Entry<Integer,Integer>> itr_Attacker=l_AttackerArmiesinHand.entrySet().iterator();
				Iterator<Map.Entry<Integer,Integer>> itr_Defender=l_DefenderArmiesinHand.entrySet().iterator();
				if(l_SizeDiff>0)
				{
					l_AttackerArmiesinHand = returnedHashMap;
					itr_Attacker = l_AttackerArmiesinHand.entrySet().iterator();
				}
				else if(l_SizeDiff<0)
				{
					l_DefenderArmiesinHand = returnedHashMap;
					itr_Defender = l_DefenderArmiesinHand.entrySet().iterator();
				}
				else
				{
					l_AttackerArmiesinHand = l_AttackerArmies;
					l_DefenderArmiesinHand = l_DefenderArmies;
				}
				//now attack
				
				int l_attackWin=0,l_defendWin=0;
				for(int i=0;i<returnedHashMap.size();i++)
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
				if(l_attackWin>l_defendWin)
				{
					d_TargetCountry.getCountryOwnerPlayer().removeCountry(d_TargetCountry);
					d_TargetCountry.setCountryOwnerPlayer(d_Player);
					d_TargetCountry.setNoOfArmies(d_NumArmies-l_MinArmies+l_attackWin);
					d_SourceCountry.setNoOfArmies(d_SourceCountry.getNoOfArmies()-d_NumArmies);
					d_Player.addCountry(d_TargetCountry);
					d_Player.setAtleastOneBattleWon(true);


					d_Player.setResult("\n"+d_Player.getPlayerName()+" your attack on "+d_TargetCountry.getCountryName()+" was a Success!!");
				}
				else
				{
					d_SourceCountry.setNoOfArmies(d_SourceCountry.getNoOfArmies()-d_NumArmies);
					d_TargetCountry.setNoOfArmies(d_TargetCountry.getNoOfArmies()-d_NumArmies+l_defendWin);
					d_Player.setResult("\n"+d_Player.getPlayerName()+" your attack on "+d_TargetCountry.getCountryName()+" was a Failure!!");
				}
			}catch(Exception p_E) {p_E.printStackTrace();}
			}

		}
	TreeMap<Integer,Integer> ArmiestoFight(int p_SizeDiffint,int  p_MinArmies, TreeMap <Integer,Integer> l_AttackerArmies,TreeMap <Integer,Integer> l_DefenderArmies)
		{
			TreeMap<Integer,Integer> returnHashMap = null;
			System.out.println("size difference"+p_SizeDiffint);
			if(p_SizeDiffint>0)
			{
				List<Entry<Integer, Integer>> list = new LinkedList<Entry<Integer, Integer>>(l_AttackerArmies.entrySet()); 
				Collections.sort(list, new Comparator<Entry<Integer, Integer>>()   
				{  
					public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2)   
					{   
						return o2.getValue().compareTo(o1.getValue());  
					}  
				});
				//TreeMap <Integer,Integer> l_SortedAttackerArmies = new TreeMap<>(); 
				//for (Entry<Integer, Integer> entry : list)   
			//{  
			//		l_SortedAttackerArmies.put(entry.getKey(), entry.getValue());  
			//	}
				TreeMap <Integer,Integer> l_AttackerArmiesinHand = new TreeMap<>(); 
				Iterator<Map.Entry<Integer,Integer>> itr = list.iterator();
			//	Iterator<Map.Entry<Integer,Integer>> itr = l_SortedAttackerArmies.entrySet().iterator();
				for(int i=0;i<p_MinArmies;i++)
				{
					Map.Entry<Integer,Integer> entry = itr.next(); 
					l_AttackerArmiesinHand.put(entry.getKey(),entry.getValue());
				}
				System.out.println("l_AttackerArmiesinHand"+l_AttackerArmiesinHand);
				returnHashMap = l_AttackerArmiesinHand;
			}
			else if(p_SizeDiffint<0)
			{
				List<Entry<Integer, Integer>> list = new LinkedList<Entry<Integer, Integer>>(l_DefenderArmies.entrySet()); 
				Collections.sort(list, new Comparator<Entry<Integer, Integer>>()   
				{  
					public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2)   
					{   
						return o2.getValue().compareTo(o1.getValue());  
					}  
				});
				//for(Entry<Integer,Integer> l_E : list)
				//{
				//	System.out.println(l_E.getKey()+" : "+ l_E.getValue());
				//}
			//	HashMap <Integer,Integer> l_SortedDefenderArmies = new HashMap<>(); 
			//	for (Entry<Integer, Integer> entry : list)   
			//	{  
			//		System.out.println(entry.getKey()+" : "+ entry.getValue());
			//		l_SortedDefenderArmies.put(entry.getKey(), entry.getValue());  
			//	}
			//	System.out.println("l_SortedDefenderArmies"+l_SortedDefenderArmies);
				TreeMap <Integer,Integer> l_DefenderArmiesinHand = new TreeMap<>(Collections.reverseOrder()); 
				Iterator<Map.Entry<Integer,Integer>> itr = list.iterator();
				for(int i=0;i<p_MinArmies;i++)
				{	
					System.out.println("inside for of min armies");
					Map.Entry<Integer,Integer> entry = itr.next(); 
					System.out.println(entry.getKey()+" : "+ entry.getValue());
					l_DefenderArmiesinHand.put(entry.getKey(),entry.getValue());
				}
				System.out.println("l_DefenderArmiesinHand"+l_DefenderArmiesinHand);
				returnHashMap =  l_DefenderArmiesinHand;
			}
			else
			{
				returnHashMap = l_AttackerArmies;
			}
			System.out.println("in armies to fight"+returnHashMap);
			return returnHashMap;
		}
		public int isValid()
		{

			int l_ReturnInt=0;
			if(d_Player.getNegotiatedPlayerList().contains(d_TargetCountry.getCountryOwnerPlayer()))
			{
				d_Player.setResult("\nThe targeted country "+d_TargetCountry.getCountryName()+" belongs to "+d_TargetCountry.getCountryOwnerPlayer().getPlayerName()+" which is negotiated player!");
				l_ReturnInt=0;
			}
			else if(d_TargetCountry.getCountryOwnerPlayer().getPlayerName().equals("Neutral Player"))
			{
				d_Player.setResult("\nThe targeted country "+d_TargetCountry.getCountryName()+" belongs to Neutral Player!");
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
