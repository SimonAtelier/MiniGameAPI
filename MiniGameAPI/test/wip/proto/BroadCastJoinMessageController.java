package wip.proto;

import entities.Game;
import entities.Player;
import entities.events.GameEventListener;
import entities.events.GameEventManager;
import entities.events.PlayerJoinedGameEvent;

public class BroadCastJoinMessageController {
	
	public BroadCastJoinMessageController() {
		GameEventManager.getInstance().registerEventListener(PlayerJoinedGameEvent.class, new PlayerJoinEventListener());
	}
	
	private void broadCastMessage(Player player, Game game) {
		System.out.println("Broadcast Message: player joined");
	}
	
	private class PlayerJoinEventListener implements GameEventListener<PlayerJoinedGameEvent> {

		@Override
		public void onEvent(PlayerJoinedGameEvent e) {
			broadCastMessage(e.getPlayer(), e.getGame());
		} 

	}	
	
}
