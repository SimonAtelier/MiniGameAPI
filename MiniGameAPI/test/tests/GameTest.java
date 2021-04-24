package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import minigame.entities.AbstractGameState;
import minigame.entities.Game;
import minigame.entities.Player;
import minigame.entities.Game.AlreadyJoinedException;
import minigame.entities.Game.CannotJoinException;
import minigame.entities.Game.CannotLeaveException;

public class GameTest {
	
	private Game game;
	
	@Before
	public void setUp() {
		game = new Game();
	}
	
	@Test
	public void getTickCountReturnsZeroByDefault() {
		assertEquals(0, game.getTickCount());
	}
	
	@Test
	public void tickGameNTimesGetTickCountReturnsN() {
		int ticks = (int) (Math.random() * 1000);
		for (int i = 0; i < ticks; i++) {
			game.tick();
		}
		assertEquals(ticks, game.getTickCount());
	}
	
	@Test
	public void getGameStateReturnsNullByDefault() {
		assertEquals(null, game.getGameState());
	}
	
	@Test
	public void getSetGameState() {
		GameStateTestMock gameStateTestMock = new GameStateTestMock();
		game.setGameState(gameStateTestMock);
		assertEquals(true, gameStateTestMock == game.getGameState());
	}
	
	@Test
	public void gameTicksGameState() {
		GameStateTestMock gameStateTestMock = new GameStateTestMock();
		int randomTickCount = (int) (Math.random() * 2000);
		game.setGameState(gameStateTestMock);
		for (int i = 0; i < randomTickCount; i++)
			game.tick();
		assertEquals(randomTickCount, gameStateTestMock.getTickCount());
	}
	
	@Test
	public void getGameAfterSetGameState() {
		GameStateTestMock gameStateTestMock = new GameStateTestMock();
		game.setGameState(gameStateTestMock);
		assertEquals(game, gameStateTestMock.getGame());
	}
	
	@Test
	public void playerJoinIsPassedToState() {
		PlayerJoinGameStateTestMock joinGameStateTestMock = new PlayerJoinGameStateTestMock();
		Player player = new Player(UUID.randomUUID());
		game.setGameState(joinGameStateTestMock);
		game.join(player);
		assertEquals(1, joinGameStateTestMock.getJoinCount());
		assertEquals(1, joinGameStateTestMock.getPlayers().size());
		assertEquals(player, joinGameStateTestMock.getPlayers().get(0));
	}
	
	@Test (expected =  CannotJoinException.class)
	public void playerJoinNullGameStateThrowsException() {
		game.join(new Player(UUID.randomUUID()));
	}
	
	@Test
	public void playerLeaveIsPassedToState() {
		PlayerLeaveGameStateTestMock playerLeaveGameStateTestMock = new PlayerLeaveGameStateTestMock();
		Player player = new Player(UUID.randomUUID());
		game.setGameState(playerLeaveGameStateTestMock);
		game.join(player);
		game.leave(player);
		assertEquals(1, playerLeaveGameStateTestMock.getLeaveCount());
		assertEquals(1, playerLeaveGameStateTestMock.getPlayers().size());
		assertEquals(player, playerLeaveGameStateTestMock.getPlayers().get(0));
	}
	
	@Test (expected = CannotLeaveException.class)
	public void playerLeaveOnNullGameStateThrowsException() {
		game.leave(new Player(UUID.randomUUID()));
	}
	
	@Test (expected = CannotJoinException.class)
	public void exceptionIsThrownIfGameStateDoesNotAllowJoin() {
		CanJoinGameStateTestMock gameState = new CanJoinGameStateTestMock();
		game.setGameState(gameState);
		game.join(new Player(UUID.randomUUID()));
	}
	
	@Test (expected = Test.None.class)
	public void joinIfGameStateAllowsJoinThrowsNoException() {
		CanJoinGameStateTestMock gameState = new CanJoinGameStateTestMock();
		gameState.setCanJoin(true);
		game.setGameState(gameState);
		game.join(new Player(UUID.randomUUID()));
	}
	
	@Test (expected = AlreadyJoinedException.class)
	public void joinTwiceThrowsException() {
		CanJoinGameStateTestMock gameState = new CanJoinGameStateTestMock();
		Player player = new Player(UUID.randomUUID());
		gameState.setCanJoin(true);
		game.setGameState(gameState);
		game.join(player);
		game.join(player);
	}
	
	@Test
	public void getPlayersCountReturnsZeroByDefault() {
		assertEquals(0, game.getPlayersCount());
	}
	
	@Test
	public void getPlayersCountAfterPlayersJoined() {
		int amount = (int) (Math.random() * 100);
		CanJoinGameStateTestMock gameState = new CanJoinGameStateTestMock();
		gameState.setCanJoin(true);
		game.setGameState(gameState);
		for (int i = 0; i < amount; i++) {
			game.join(new Player(UUID.randomUUID()));
		}
		assertEquals(amount, game.getPlayersCount());
	}
	
	@Test (expected = AlreadyJoinedException.class)
	public void joinMultiplePlayersTwice() {
		int exceptionCount = 0;
		
		CanJoinGameStateTestMock gameState = new CanJoinGameStateTestMock();
		gameState.setCanJoin(true);
		game.setGameState(gameState);
		
		Player player0 = new Player(UUID.randomUUID());
		game.join(player0);
		
		int amount = (int) (Math.random() * 1000);
		for (int i = 0; i < amount; i++) {
			Player player = new Player(UUID.randomUUID());
			try {
				game.join(player);
				game.join(player);
			} catch (AlreadyJoinedException e) {
				exceptionCount++;
			}
		}
		
		game.join(player0);
		assertEquals(amount, exceptionCount);
	}
	
	@Test (expected = CannotLeaveException.class)
	public void playerLeaveButNotJoinedThrowsException() {
		game.leave(new Player(UUID.randomUUID()));
	}
	
	@Test
	public void playersCountJoinLeave() {
		Player player = new Player(UUID.randomUUID());
		CanJoinGameStateTestMock gameState = new CanJoinGameStateTestMock();
		gameState.canJoin = true;
		game.setGameState(gameState);
		game.join(player);
		assertEquals(1, game.getPlayersCount());
		game.leave(player);
		assertEquals(0, game.getPlayersCount());
	}
	
	@Test
	public void setGameStateNotifiesEnter() {
		EnterLeaveTestMock gameState = new EnterLeaveTestMock();
		game.setGameState(gameState);
		assertEquals(1, gameState.enterCount);
		assertEquals(0, gameState.leaveCount);
	}
	
	@Test
	public void setGameStateEnterNewLeaveOldGameStateIsNotified() {
		EnterLeaveTestMock gameStateOld = new EnterLeaveTestMock();
		game.setGameState(gameStateOld);
		assertEquals(1, gameStateOld.enterCount);
		assertEquals(0, gameStateOld.leaveCount);
		
		EnterLeaveTestMock gameStateNew = new EnterLeaveTestMock();
		game.setGameState(gameStateNew);
		
		assertEquals(1, gameStateOld.enterCount);
		assertEquals(1, gameStateOld.leaveCount);
	}
	
	@Test
	public void getSetName() {
		String randomString = UUID.randomUUID().toString();
		game.setName(randomString);
		assertEquals(randomString, game.getName());
	}
	
	@Test
	public void getPlayerAtReturnsJoinedPlayer() {
		int playersCount = (int) (Math.random() * 20) + 1;
		game.setGameState(new GameStateTestAdapter());
		for (int i = 0; i < playersCount; i++) {
			Player player = new Player(UUID.randomUUID());
			game.join(player);
			assertEquals(player, game.getPlayerAt(i));
		}
	}
	
	private class CanJoinGameStateTestMock extends GameStateTestAdapter {
		
		private boolean canJoin;
		
		@Override
		public boolean canJoin() {
			return canJoin;
		}
		
		public void setCanJoin(boolean canJoin) {
			this.canJoin = canJoin;
		}
		
	}
	
	private class PlayerLeaveGameStateTestMock extends GameStateTestAdapter {
		
		private int leaveCount;
		private List<Player> players;
		
		public PlayerLeaveGameStateTestMock() {
			players = new ArrayList<Player>();
		}
		
		@Override
		public void onPlayerLeave(Player player) {
			leaveCount++;
			players.add(player);
		}
		
		public List<Player> getPlayers() {
			return players;
		}
		
		public int getLeaveCount() {
			return leaveCount;
		}
		
	}
	
	private class PlayerJoinGameStateTestMock extends GameStateTestAdapter {

		private int joinCount;
		private List<Player> players;
		
		public PlayerJoinGameStateTestMock() {
			players = new ArrayList<Player>();
		}
		
		@Override
		public void onPlayerJoin(Player player) {
			joinCount++;
			players.add(player);
		}
		
		public int getJoinCount() {
			return joinCount;
		}
		
		public List<Player> getPlayers() {
			return players;
		}
		
	}
	
	private class GameStateTestMock extends GameStateTestAdapter {

		private long tickCount;
		
		@Override
		public void tick() {
			tickCount++;
		}
		
		public long getTickCount() {
			return tickCount;
		}
		
	}
	
	
	private class EnterLeaveTestMock extends GameStateTestAdapter {
		
		public int enterCount;
		public int leaveCount;
		
		@Override
		public void onEnterGameState() {
			enterCount++;
		}

		@Override
		public void onLeaveGameState() {
			leaveCount++;
		}
		
	}
	
	private class GameStateTestAdapter extends AbstractGameState {

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
			return true;
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
