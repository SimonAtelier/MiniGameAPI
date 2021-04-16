package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import entities.ItemId;

public class ItemIdTest {
	
	@Test
	public void idsAreNotEmpty() {
		for (ItemId itemId : ItemId.values()) {
			assertEquals(false, itemId.getId().isEmpty());
		}
	}
	
	@Test
	public void idStartsWithMinecaftColon() {
		for (ItemId itemId : ItemId.values()) {
			assertEquals(true, itemId.getId().startsWith("minecraft:"));
		}
	}
	
	@Test
	public void minecraftColonConstantToLowerCaseEqualsId() {
		for (ItemId itemId : ItemId.values()) {
			String expected = "minecraft:" + itemId.toString().toLowerCase();
			assertEquals(expected, itemId.getId());
		}
	}
		
}
