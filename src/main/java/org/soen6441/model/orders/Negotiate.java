package org.soen6441.model.orders;

import org.soen6441.model.Order;
import org.soen6441.model.Player;

public class Negotiate implements Order {
	private Player d_SourcePlayer,d_TargetPlayer;

	public Negotiate(Player p_SourcePlayer, Player p_TargetPlayer) {
		System.out.println("Negotiate Created : " + p_SourcePlayer.getPlayerName() + " : " + p_TargetPlayer.getPlayerName());
		set_SourcePlayer(p_SourcePlayer);
		d_TargetPlayer = p_TargetPlayer;

	}

	@Override
	public void execute() {
		/*If Player has negotiate card, then it will add target player to negotiatedPlayerList and then remove that card*/
		if(this.get_SourcePlayer().getCardList().contains("Negotiate")) {
			this.get_SourcePlayer().getNegotiatedPlayerList().add(d_TargetPlayer);
			this.d_TargetPlayer.getNegotiatedPlayerList().add(get_SourcePlayer());
			get_SourcePlayer().removeCard("Negotiate");
			d_SourcePlayer.setResult("Negotation with "+d_TargetPlayer.getPlayerName()+" successfull.");
		}
		else
		{
			get_SourcePlayer().setResult("\n"+get_SourcePlayer().getPlayerName()+" does not own Negotiate Card for Diplomacy with "+d_TargetPlayer.getPlayerName());
		}

	}

	public Player get_SourcePlayer() {
		return d_SourcePlayer;
	}

	public void set_SourcePlayer(Player d_SourcePlayer) {
		this.d_SourcePlayer = d_SourcePlayer;
	}


}
