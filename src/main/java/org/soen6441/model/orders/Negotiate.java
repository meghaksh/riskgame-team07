package org.soen6441.model.orders;

import org.soen6441.model.Order;
import org.soen6441.model.Player;

public class Negotiate implements Order {
	Player d_SourcePlayer,d_TargetPlayer;

	public Negotiate(Player p_SourcePlayer, Player p_TargetPlayer) {
		System.out.println("Negotiate Created : " + p_SourcePlayer.getPlayerName() + " : " + p_TargetPlayer.getPlayerName());
		d_SourcePlayer = p_SourcePlayer;
		d_TargetPlayer = p_TargetPlayer;
		d_SourcePlayer.removeCard("Negotiate");
	}

	@Override
	public void execute() {
		this.d_SourcePlayer.getNegotiatedPlayerList().add(d_TargetPlayer);
	}
	

}
