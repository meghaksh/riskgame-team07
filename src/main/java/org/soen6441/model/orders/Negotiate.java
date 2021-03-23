package org.soen6441.model.orders;

import org.soen6441.model.Order;
import org.soen6441.model.Player;

public class Negotiate implements Order {
	Player d_SourcePlayer,d_TargetPlayer;

	public Negotiate(Player p_SourcePlayer, Player p_TargetPlayer) {
		System.out.println("Negotiate Created : " + p_SourcePlayer.getPlayerName() + " : " + p_TargetPlayer.getPlayerName());
		d_SourcePlayer = p_SourcePlayer;
		d_TargetPlayer = p_TargetPlayer;

	}

	@Override
	public void execute() {
		/*If Player has negotiate card, then it will add target player to negotiatedPlayerList and then remove that card*/
		if(this.d_SourcePlayer.getCardList().contains("Negotiate")) {
			this.d_SourcePlayer.getNegotiatedPlayerList().add(d_TargetPlayer);
			this.d_TargetPlayer.getNegotiatedPlayerList().add(d_SourcePlayer);
			d_SourcePlayer.removeCard("Negotiate");
		}
		else
		{
			d_SourcePlayer.setResult("\n"+d_SourcePlayer.getPlayerName()+" does not own Negotiate Card for Diplomacy with "+d_TargetPlayer.getPlayerName());
		}

	}


}
