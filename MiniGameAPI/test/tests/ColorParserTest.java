package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import minigame.entities.Color;
import minigame.entities.ColorParser;
import minigame.entities.ColorParser.ColorFormatException;

public class ColorParserTest {

	private ColorParser parser;

	@Before
	public void setUp() {
		parser = new ColorParser();
	}

	@Test(expected = ColorFormatException.class)
	public void parseEmptyStringThrowsException() {
		parser.parse("");
	}

	@Test
	public void parseUpperCaseEnumValuesWithUnderScore() {
		for (Color color : Color.values()) {
			assertEquals(color, parser.parse(color.toString()));
		}
	}

	@Test
	public void parseLowerCaseEnumValuesWithUnderScore() {
		for (Color color : Color.values()) {
			assertEquals(color, parser.parse(color.toString().toLowerCase()));
		}
	}

	@Test(expected = ColorFormatException.class)
	public void parseUnknownColorThrowsException() {
		parser.parse("DARK_GOLD");
	}

	@Test(expected = ColorFormatException.class)
	public void parseNullThrowsException() {
		parser.parse(null);
	}
	
	@Test
	public void parseUpperCaseEnumValuesWithoutUnderScore() {
		for (Color color : Color.values()) {
			assertEquals(color, parser.parse(color.toString().replace("_", " ")));
		}
	}
	
	@Test
	public void parseLowerCaseEnumValuesWithoutUnderScore() {
		for (Color color : Color.values()) {
			assertEquals(color, parser.parse(color.toString().toLowerCase().replace("_", " ")));
		}
	}
	
	@Test
	public void parseUpperCamelCaseWithUnderscores() {
		assertEquals(Color.BLACK, parser.parse("Black"));
		assertEquals(Color.DARK_GREEN, parser.parse("Dark_Green"));
		assertEquals(Color.DARK_RED, parser.parse("Dark_Red"));
		assertEquals(Color.GOLD, parser.parse("Gold"));
		assertEquals(Color.DARK_GRAY, parser.parse("Dark_Gray"));
		assertEquals(Color.GREEN, parser.parse("Green"));
		assertEquals(Color.RED, parser.parse("Red"));
		assertEquals(Color.YELLOW, parser.parse("Yellow"));
		assertEquals(Color.DARK_BLUE, parser.parse("Dark_Blue"));
		assertEquals(Color.DARK_AQUA, parser.parse("Dark_Aqua"));
		assertEquals(Color.DARK_PURPLE, parser.parse("Dark_Purple"));
		assertEquals(Color.GRAY, parser.parse("Gray"));
		assertEquals(Color.BLUE, parser.parse("Blue"));
		assertEquals(Color.AQUA, parser.parse("Aqua"));
		assertEquals(Color.LIGHT_PURPLE, parser.parse("Light_Purple"));
		assertEquals(Color.WHITE, parser.parse("White"));
	}
	
	@Test
	public void parseUpperCamelCaseWithBlanks() {
		assertEquals(Color.BLACK, parser.parse("Black"));
		assertEquals(Color.DARK_GREEN, parser.parse("Dark Green"));
		assertEquals(Color.DARK_RED, parser.parse("Dark Red"));
		assertEquals(Color.GOLD, parser.parse("Gold"));
		assertEquals(Color.DARK_GRAY, parser.parse("Dark Gray"));
		assertEquals(Color.GREEN, parser.parse("Green"));
		assertEquals(Color.RED, parser.parse("Red"));
		assertEquals(Color.YELLOW, parser.parse("Yellow"));
		assertEquals(Color.DARK_BLUE, parser.parse("Dark Blue"));
		assertEquals(Color.DARK_AQUA, parser.parse("Dark Aqua"));
		assertEquals(Color.DARK_PURPLE, parser.parse("Dark Purple"));
		assertEquals(Color.GRAY, parser.parse("Gray"));
		assertEquals(Color.BLUE, parser.parse("Blue"));
		assertEquals(Color.AQUA, parser.parse("Aqua"));
		assertEquals(Color.LIGHT_PURPLE, parser.parse("Light Purple"));
		assertEquals(Color.WHITE, parser.parse("White"));
	}
	
	@Test
	public void parseUpperCamelCaseWithoutBlanks() {
		assertEquals(Color.BLACK, parser.parse("Black"));
		assertEquals(Color.DARK_GREEN, parser.parse("DarkGreen"));
		assertEquals(Color.DARK_RED, parser.parse("DarkRed"));
		assertEquals(Color.GOLD, parser.parse("Gold"));
		assertEquals(Color.DARK_GRAY, parser.parse("DarkGray"));
		assertEquals(Color.GREEN, parser.parse("Green"));
		assertEquals(Color.RED, parser.parse("Red"));
		assertEquals(Color.YELLOW, parser.parse("Yellow"));
		assertEquals(Color.DARK_BLUE, parser.parse("DarkBlue"));
		assertEquals(Color.DARK_AQUA, parser.parse("Dark Aqua"));
		assertEquals(Color.DARK_PURPLE, parser.parse("DarkPurple"));
		assertEquals(Color.GRAY, parser.parse("Gray"));
		assertEquals(Color.BLUE, parser.parse("Blue"));
		assertEquals(Color.AQUA, parser.parse("Aqua"));
		assertEquals(Color.LIGHT_PURPLE, parser.parse("LightPurple"));
		assertEquals(Color.WHITE, parser.parse("White"));
	}
	
	@Test
	public void parseLowerCamelCaseWithoutBlanks() {
		assertEquals(Color.BLACK, parser.parse("black"));
		assertEquals(Color.DARK_GREEN, parser.parse("darkGreen"));
		assertEquals(Color.DARK_RED, parser.parse("darkRed"));
		assertEquals(Color.GOLD, parser.parse("gold"));
		assertEquals(Color.DARK_GRAY, parser.parse("darkGray"));
		assertEquals(Color.GREEN, parser.parse("green"));
		assertEquals(Color.RED, parser.parse("red"));
		assertEquals(Color.YELLOW, parser.parse("yellow"));
		assertEquals(Color.DARK_BLUE, parser.parse("darkBlue"));
		assertEquals(Color.DARK_AQUA, parser.parse("darkAqua"));
		assertEquals(Color.DARK_PURPLE, parser.parse("darkPurple"));
		assertEquals(Color.GRAY, parser.parse("gray"));
		assertEquals(Color.BLUE, parser.parse("blue"));
		assertEquals(Color.AQUA, parser.parse("aqua"));
		assertEquals(Color.LIGHT_PURPLE, parser.parse("lightPurple"));
		assertEquals(Color.WHITE, parser.parse("white"));
	}
	
	@Test
	public void parseLowerCamelCaseWithUnderscores() {
		assertEquals(Color.BLACK, parser.parse("black"));
		assertEquals(Color.DARK_GREEN, parser.parse("dark_Green"));
		assertEquals(Color.DARK_RED, parser.parse("dark_Red"));
		assertEquals(Color.GOLD, parser.parse("gold"));
		assertEquals(Color.DARK_GRAY, parser.parse("dark_Gray"));
		assertEquals(Color.GREEN, parser.parse("green"));
		assertEquals(Color.RED, parser.parse("red"));
		assertEquals(Color.YELLOW, parser.parse("yellow"));
		assertEquals(Color.DARK_BLUE, parser.parse("dark_Blue"));
		assertEquals(Color.DARK_AQUA, parser.parse("dark_Aqua"));
		assertEquals(Color.DARK_PURPLE, parser.parse("dark_Purple"));
		assertEquals(Color.GRAY, parser.parse("gray"));
		assertEquals(Color.BLUE, parser.parse("blue"));
		assertEquals(Color.AQUA, parser.parse("aqua"));
		assertEquals(Color.LIGHT_PURPLE, parser.parse("light_Purple"));
		assertEquals(Color.WHITE, parser.parse("white"));
	}
	
	@Test
	public void parseMixedCasesWithRandomBlanksAndUnderscores() {
		assertEquals(Color.BLACK, parser.parse("blA C k"));
		assertEquals(Color.DARK_GREEN, parser.parse("d_ArK_Green"));
		assertEquals(Color.DARK_RED, parser.parse("dark_RED"));
		assertEquals(Color.GOLD, parser.parse("goLD"));
		assertEquals(Color.DARK_GRAY, parser.parse("D ar_k GRay"));
		assertEquals(Color.GREEN, parser.parse("grE E n"));
		assertEquals(Color.RED, parser.parse("Red"));
		assertEquals(Color.YELLOW, parser.parse("YeL___ low"));
		assertEquals(Color.DARK_BLUE, parser.parse("d_A rK_Blue"));
		assertEquals(Color.DARK_AQUA, parser.parse("da___r    k_Aqua"));
		assertEquals(Color.DARK_PURPLE, parser.parse("dark_Pu   rple"));
		assertEquals(Color.GRAY, parser.parse("gra   ____y"));
		assertEquals(Color.BLUE, parser.parse("bl___ue"));
		assertEquals(Color.AQUA, parser.parse("aqua"));
		assertEquals(Color.LIGHT_PURPLE, parser.parse("li__ght_Purple"));
		assertEquals(Color.WHITE, parser.parse("wh  it__e"));
	}

}
