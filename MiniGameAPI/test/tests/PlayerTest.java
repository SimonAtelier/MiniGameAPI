package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import minigame.entities.Player;
import minigame.entities.Player.PlayerIdCannotBeNullException;

public class PlayerTest {

	private Player playerOne;
	private Player playerTwo;
	
	@Before
	public void setUp() {
		playerOne = new Player(UUID.randomUUID());
		playerTwo = new Player(UUID.randomUUID());
	}
	
	@Test
	public void getSetUniqueId() {
		UUID uniquePlayerId = UUID.randomUUID();
		playerOne.setUniqueId(uniquePlayerId);
		assertEquals(uniquePlayerId, playerOne.getUniqueId());
	}
	
	@Test
	public void playerIsEqualsToItself() {
		assertTrue(playerOne.equals(playerOne));
		assertTrue(playerTwo.equals(playerTwo));
	}
	
	@Test
	public void playerIsEqualsToPlayerWithSameUniqueId() {
		playerTwo.setUniqueId(playerOne.getUniqueId());
		assertTrue(playerOne.equals(playerTwo));
	}
	
	@Test
	public void playersWithDifferentIdsAreNotEqual() {
		assertFalse(playerOne.equals(playerTwo));
	}
	
	@Test
	public void playerIsNotEqualsToNull() {
		assertFalse(playerOne.equals(null));
	}
	
	@Test
	public void constructWithIdReturnsIdWithGet() {
		UUID uniquePlayerId = UUID.randomUUID();
		Player player = new Player(uniquePlayerId);
		assertEquals(uniquePlayerId, player.getUniqueId());
	}
	
	@Test (expected = PlayerIdCannotBeNullException.class)
	public void setIdToNullThrowsException() {
		playerOne.setUniqueId(null);
	}
	
	@Test (expected = PlayerIdCannotBeNullException.class)
	public void constructPlayerWithNullIdThrowsException() {
		new Player(null);
	}
	
}
