package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import minigame.entities.AbstractGameState;
import minigame.entities.Game;
import minigame.entities.Player;

public class GameStateTest {

	private GameStateTestMock gameState;
	
	@Before
	public void setUp() {
		gameState = new GameStateTestMock();
	}
	
	@Test
	public void getGameReturnsNullByDefault() {
		assertEquals(null, gameState.getGame());
	}
	
	@Test
	public void getGameIsGameAfterSettingStateToState() {
		Game game = new Game();
		game.setGameState(gameState);
		assertEquals(game, gameState.getGame());
	}
	
	private class GameStateTestMock extends AbstractGameState {

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
			return false;
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
