package minigame.entities.events;

import minigame.entities.Game;
import minigame.entities.Player;

public class PlayerJoinedGameEvent extends GameEvent {
	
	private Game game;
	private Player player;
	
	public PlayerJoinedGameEvent(Game game, Player player) {
		this.game = game;
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Game getGame() {
		return game;
	}

}
