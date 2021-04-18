package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import entities.Player;

public class PlayerTest {

	private Player playerOne;
	private Player playerTwo;
	
	@Before
	public void setUp() {
		playerOne = new Player();
		playerOne.setUniqueId(UUID.randomUUID());
		playerTwo = new Player();
		playerTwo.setUniqueId(UUID.randomUUID());
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
	
}
