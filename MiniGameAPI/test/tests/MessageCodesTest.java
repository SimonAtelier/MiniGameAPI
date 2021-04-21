package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import view.MessageCodes;

public class MessageCodesTest {
	
	@Test
	public void darkRed() {
		assertEquals("§4", MessageCodes.DARK_RED);
	}
	
	@Test
	public void red() {
		assertEquals("§c", MessageCodes.RED);
	}
	
	@Test
	public void gold() {
		assertEquals("§6", MessageCodes.GOLD);
	}
	
	@Test
	public void yellow() {
		assertEquals("§e", MessageCodes.YELLOW);
	}
	
	@Test
	public void darkGreen() {
		assertEquals("§2", MessageCodes.DARK_GREEN);
	}
	
	@Test
	public void green() {
		assertEquals("§a", MessageCodes.GREEN);
	}
	
	@Test
	public void aqua() {
		assertEquals("§b", MessageCodes.AQUA);
	}
	
	@Test
	public void darkAqua() {
		assertEquals("§3", MessageCodes.DARK_AQUA);
	}
	
	@Test
	public void darkBlue() {
		assertEquals("§1", MessageCodes.DARK_BLUE);
	}
	
	@Test
	public void blue() {
		assertEquals("§9", MessageCodes.BLUE);
	}
	
	@Test
	public void lightPurple() {
		assertEquals("§d", MessageCodes.LIGHT_PURPLE);
	}
	
	@Test
	public void darkPurple() {
		assertEquals("§5", MessageCodes.DARK_PURPLE);
	}
	
	@Test
	public void white() {
		assertEquals("§f", MessageCodes.WHITE);
	}
	
	@Test
	public void gray() {
		assertEquals("§7", MessageCodes.GRAY);
	}
	
	@Test
	public void darkGray() {
		assertEquals("§8", MessageCodes.DARK_GRAY);
	}

	@Test
	public void black() {
		assertEquals("§0", MessageCodes.BLACK);
	}
	
	@Test
	public void reset() {
		assertEquals("§r", MessageCodes.RESET);
	}
	
	@Test
	public void bold() {
		assertEquals("§l", MessageCodes.BOLD);
	}
	
	@Test
	public void italic() {
		assertEquals("§o", MessageCodes.ITALIC);
	}
	
	@Test
	public void underline() {
		assertEquals("§n", MessageCodes.UNDERLINE);
	}
	
	@Test
	public void strikeThrough() {
		assertEquals("§m", MessageCodes.STRIKE_THROUGH);
	}
	
	@Test
	public void magic() {
		assertEquals("§k", MessageCodes.MAGIC);
	}

}
