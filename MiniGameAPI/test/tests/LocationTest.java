package tests;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import minigame.entities.Location;

public class LocationTest {
	
	private Location location;
	
	@Before
	public void setUp() {
		location = new Location();
	}
	
	@Test
	public void getXReturnsZeroByDefault() {
		assertEquals(0, location.getX(), 0.0001);
	}
	
	@Test
	public void getYReturnsZeroByDefault() {
		assertEquals(0, location.getY(), 0.0001);
	}
	
	@Test
	public void getZRetrunsZeroByDefautl() {
		assertEquals(0, location.getZ(), 0.0001);
	}
	
	@Test
	public void getPitchReturnsZeroByDefault() {
		assertEquals(0, location.getPitch(), 0.0001);
	}
	
	@Test
	public void getYawReturnZeroByDefault() {
		assertEquals(0, location.getYaw(), 0.0001);
	}
	
	@Test
	public void getWorldReturnsEmptyStringByDefault() {
		assertEquals("", location.getWorld());
	}
	
	@Test
	public void setRandomX() {
		double random = Math.random() * Double.MAX_VALUE;
		location.setX(random);
		assertEquals(random, location.getX(), 0.0001);
	}
	
	@Test
	public void setRandomY() {
		double random = Math.random() * Double.MAX_VALUE;
		location.setY(random);
		assertEquals(random, location.getY(), 0.0001);
	}
	
	@Test
	public void setRandomZ() {
		double random = Math.random() * Double.MAX_VALUE;
		location.setZ(random);
		assertEquals(random, location.getZ(), 0.0001);
	}
	
	@Test
	public void setRandomPitch() {
		double random = Math.random() * Double.MAX_VALUE;
		location.setPitch(random);
		assertEquals(random, location.getPitch(), 0.0001);
	}
	
	@Test
	public void setRandomYaw() {
		double random = Math.random() * Double.MAX_VALUE;
		location.setYaw(random);
		assertEquals(random, location.getYaw(), 0.0001);
	}
	
	@Test
	public void setRandomWorld() {
		String random = UUID.randomUUID().toString();
		location.setWorld(random);
		assertEquals(random, location.getWorld());
	}

}
