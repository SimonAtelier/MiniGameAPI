package tests;

import java.util.UUID;

public class TestUtil {

	public static String createRandomString() {
		return UUID.randomUUID() + "";
	}
	
	public static int createRandomIntBetweenZeroAndIntMax() {
		return (int) (Math.random() * Integer.MAX_VALUE);
	}
	
}
