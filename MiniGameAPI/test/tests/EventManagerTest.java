package tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import entities.Game;
import entities.GameState;
import entities.Player;
import entities.events.GameEventListener;
import entities.events.GameEventManager;
import entities.events.PlayerJoinedGameEvent;

public class EventManagerTest {
	
	@Before
	public void setUp() {
		GameEventManager.getInstance().clearListeners();
	}
	
	@Test
	public void getInstanceIsNotNull() {
		GameEventManager eventManager = GameEventManager.getInstance();
		assertNotNull(eventManager);
	}

	@Test
	public void testGetInstanceSingletonBehavior() {
		GameEventManager eventManager = GameEventManager.getInstance();
		GameEventManager eventManager2 = GameEventManager.getInstance();
		assertTrue(eventManager == eventManager2);
	}
	
	@Test
	public void listenerCountIsOneAfterRegisterListener() {
		GameEventListener<PlayerJoinedGameEvent> listener = new GameEventListener<PlayerJoinedGameEvent>() {

			@Override
			public void onEvent(PlayerJoinedGameEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
		GameEventManager.getInstance().registerEventListener(PlayerJoinedGameEvent.class, listener);
		assertEquals(1, GameEventManager.getInstance().getListenersCount());
	}
	
	@Test
	public void listenerCountIsCountIsCountAfterRegisterListener() {
		int count = (int) (Math.random() * 100);
		GameEventListener<PlayerJoinedGameEvent> listener = new GameEventListener<PlayerJoinedGameEvent>() {

			@Override
			public void onEvent(PlayerJoinedGameEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		for (int i = 0; i < count; i++) {
			GameEventManager.getInstance().registerEventListener(PlayerJoinedGameEvent.class, listener);
		}
		
		assertEquals(count, GameEventManager.getInstance().getListenersCount());
	}
	
	@Test
	public void broadcastPlayerJoinEvent() {
		PlayerJoinListenerTestMock listenerTestMock = new PlayerJoinListenerTestMock();
		PlayerJoinedGameEvent event = new PlayerJoinedGameEvent(new Game(), new Player(UUID.randomUUID()));
		GameEventManager.getInstance().registerEventListener(PlayerJoinedGameEvent.class, listenerTestMock);
		GameEventManager.getInstance().broadcastEvent(event);
		assertEquals(1, listenerTestMock.notificationCount);
	}
	
	@Test
	public void broadcastPlayerJoinEventRandomTimes() {
		int count = (int) (Math.random() * 100);
		PlayerJoinListenerTestMock listenerTestMock = new PlayerJoinListenerTestMock();
		PlayerJoinedGameEvent event = new PlayerJoinedGameEvent(new Game(), new Player(UUID.randomUUID()));
		GameEventManager.getInstance().registerEventListener(PlayerJoinedGameEvent.class, listenerTestMock);
		
		for (int i = 0; i < count; i++)
			GameEventManager.getInstance().broadcastEvent(event);
		
		
		assertEquals(count, listenerTestMock.notificationCount);
	}
	
	@Test
	public void playerJoinsGameFiresEvent() {
		Player player = new Player(UUID.randomUUID());
		PlayerJoinListenerTestMock listenerTestMock = new PlayerJoinListenerTestMock();
		GameEventManager.getInstance().registerEventListener(PlayerJoinedGameEvent.class, listenerTestMock);
		Game game = new Game();
		game.setGameState(new GameStateTestMock());
		game.join(player);
		assertEquals(1, listenerTestMock.notificationCount);
		assertEquals(player, listenerTestMock.e.getPlayer());
		assertEquals(game, listenerTestMock.e.getGame());
	}
	
	private class PlayerJoinListenerTestMock implements GameEventListener<PlayerJoinedGameEvent>{
		
		public int notificationCount = 0;
		public PlayerJoinedGameEvent e;
		
		@Override
		public void onEvent(PlayerJoinedGameEvent e) {
			notificationCount++;
			this.e = e;
		}
		
	}
	
	private class GameStateTestMock implements GameState {

		@Override
		public void tick() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPlayerJoin(Player player) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPlayerLeave(Player player) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean canJoin() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public Game getGame() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setGame(Game game) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onEnterGameState() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onLeaveGameState() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
