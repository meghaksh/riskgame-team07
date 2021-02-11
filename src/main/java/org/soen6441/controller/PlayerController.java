package org.soen6441.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.soen6441.model.Player;

public class PlayerController {

	Scanner d_scan;
	ArrayList <Player> d_players;
	
	PlayerController(ArrayList p_players)
	{
		d_scan =new Scanner(System.in);
		d_players = p_players;
	}
	/*Iterator it = d_players.iterator();
	
	while(it.hasNext())
	{
		String order = d_scan.nextLine();
		Player l_tempPlayer = (Player)it.next();
		l_tempPlayer.issue_order();
	}
	*/
}
