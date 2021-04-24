package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.Test;

import minigame.entities.Game;
import minigame.entities.Player;
import minigame.entities.events.PlayerJoinedGameEvent;

public class PlayerJoinedGameEventTest {
	
	@Test
	public void createEventWithPlayerReturnsPlayer() {
		Player player = new Player(UUID.randomUUID());
		PlayerJoinedGameEvent event = new PlayerJoinedGameEvent(new Game(), player);
		assertEquals(player, event.getPlayer());
	}
	
	@Test
	public void createEventWithGameReturnsGame() {
		Game game = new Game();
		Player player = new Player(UUID.randomUUID());
		PlayerJoinedGameEvent event = new PlayerJoinedGameEvent(game, player);
		assertTrue(event.getGame() == game);
	}

}
