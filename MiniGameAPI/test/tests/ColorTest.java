package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import entities.Color;

public class ColorTest {

	@Test
	public void blackExists() {
		assertEquals("BLACK", Color.BLACK.toString());
	}
	
	@Test
	public void darkGreenExists() {
		assertEquals("DARK_GREEN", Color.DARK_GREEN.toString());
	}
	
	@Test
	public void darkRedExits() {
		assertEquals("DARK_RED", Color.DARK_RED.toString());
	}
	
	@Test
	public void goldExists() {
		assertEquals("GOLD", Color.GOLD.toString());
	}
	
	@Test
	public void darkGrayExists() {
		assertEquals("DARK_GRAY", Color.DARK_GRAY.toString());
	}
	
	@Test
	public void greenExists() {
		assertEquals("GREEN", Color.GREEN.toString());
	}
	
	@Test
	public void redExists() {
		assertEquals("RED", Color.RED.toString());
	}
	
	@Test
	public void yellowExists() {
		assertEquals("YELLOW", Color.YELLOW.toString());
	}
	
	@Test
	public void darkBlueExists() {
		assertEquals("DARK_BLUE", Color.DARK_BLUE.toString());
	}
	
	@Test
	public void darkAquaExists() {
		assertEquals("DARK_AQUA", Color.DARK_AQUA.toString());
	}
	
	@Test
	public void darkPurpleExists() {
		assertEquals("DARK_PURPLE", Color.DARK_PURPLE.toString());
	}
	
	@Test
	public void grayExists() {
		assertEquals("GRAY", Color.GRAY.toString());
	}
	
	@Test
	public void blueExists() {
		assertEquals("BLUE", Color.BLUE.toString());
	}
	
	@Test
	public void aquaExists() {
		assertEquals("AQUA", Color.AQUA.toString());
	}
	
	@Test
	public void lightPurpleExists() {
		assertEquals("LIGHT_PURPLE", Color.LIGHT_PURPLE.toString());
	}
	
	@Test
	public void sixteenColorsInTotal() {
		assertEquals(16, Color.values().length);
	}
	
	@Test
	public void whiteExists() {
		assertEquals("WHITE", Color.WHITE.toString());
	}
	
}
