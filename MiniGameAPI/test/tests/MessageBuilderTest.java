package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import minigame.view.MessageBuilder;
import minigame.view.MessageCodes;
import minigame.view.MessageBuilder.TextBuilder;

public class MessageBuilderTest {

	@Test
	public void plainText() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().text(randomString).build();
		assertEquals(randomString, message);
	}
	
	@Test
	public void combinedPlainText() {
		String randomString0 = TestUtil.createRandomString();
		String randomString1 = TestUtil.createRandomString();
		String message = MessageBuilder.builder().text(randomString0).text(randomString1).build();
		assertEquals(randomString0 + randomString1, message);
	}
	
	@Test
	public void darkRedText() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().darkRed().text(randomString).build();
		assertEquals(MessageCodes.DARK_RED + randomString, message);
	}
	
	@Test
	public void redText() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().red().text(randomString).build();
		assertEquals(MessageCodes.RED + randomString, message);
	}
	
	@Test
	public void goldText() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().gold().text(randomString).build();
		assertEquals(MessageCodes.GOLD + randomString, message);
	}
	
	@Test
	public void yellowText() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().yellow().text(randomString).build();
		assertEquals(MessageCodes.YELLOW + randomString, message);
	}
	
	@Test
	public void darkGreenText() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().darkGreen().text(randomString).build();
		assertEquals(MessageCodes.DARK_GREEN + randomString, message);
	}
	
	@Test
	public void greenText() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().green().text(randomString).build();
		assertEquals(MessageCodes.GREEN + randomString, message);
	}
	
	@Test
	public void aquaText() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().aqua().text(randomString).build();
		assertEquals(MessageCodes.AQUA + randomString, message);
	}
	
	@Test
	public void darkAquaText() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().darkAqua().text(randomString).build();
		assertEquals(MessageCodes.DARK_AQUA + randomString, message);
	}
	
	@Test
	public void darkBlueText() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().darkBlue().text(randomString).build();
		assertEquals(MessageCodes.DARK_BLUE + randomString, message);
	}
	
	@Test
	public void blueText() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().blue().text(randomString).build();
		assertEquals(MessageCodes.BLUE + randomString, message);
	}
	
	@Test
	public void lightPurpleText() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().lightPurple().text(randomString).build();
		assertEquals(MessageCodes.LIGHT_PURPLE + randomString, message);
	}
	
	@Test
	public void whiteText() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().white().text(randomString).build();
		assertEquals(MessageCodes.WHITE + randomString, message);
	}
	
	@Test
	public void grayText() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().gray().text(randomString).build();
		assertEquals(MessageCodes.GRAY + randomString, message);
	}
	
	@Test
	public void blackText() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().black().text(randomString).build();
		assertEquals(MessageCodes.BLACK + randomString, message);
	}
	
	@Test
	public void darkGray() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().darkGray().text(randomString).build();
		assertEquals(MessageCodes.DARK_GRAY + randomString, message);
	}
	
	@Test
	public void boldDarkRed() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().darkRed().bold().text(randomString).build();
		assertEquals(MessageCodes.DARK_RED + MessageCodes.BOLD + randomString, message);
	}
	
	@Test
	public void boldRandomTimesInARowAppendsOnlyOne() {
		int random = (int) (Math.random() * 100);
		String randomString = TestUtil.createRandomString();
		
		MessageBuilder builder = MessageBuilder.builder();
		TextBuilder textBuilder = builder.red();
		for (int i = 0; i < random; i++) {
			textBuilder = textBuilder.bold();
		}
		
		String message = textBuilder.text(randomString).build();
		
		assertEquals(MessageCodes.RED + MessageCodes.BOLD + randomString, message);
	}
	
	@Test
	public void italicDarkRed() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().darkRed().italic().text(randomString).build();
		assertEquals(MessageCodes.DARK_RED + MessageCodes.ITALIC + randomString, message);
	}
	
	@Test
	public void italicRandomTimesInARowAppendsOnlyOne() {
		int random = (int) (Math.random() * 100);
		String randomString = TestUtil.createRandomString();
		
		MessageBuilder builder = MessageBuilder.builder();
		TextBuilder textBuilder = builder.red();
		for (int i = 0; i < random; i++) {
			textBuilder = textBuilder.italic();
		}
		
		String message = textBuilder.text(randomString).build();
		
		assertEquals(MessageCodes.RED + MessageCodes.ITALIC + randomString, message);
	}
	
	@Test
	public void underlineDarkRed() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().darkRed().underline().text(randomString).build();
		assertEquals(MessageCodes.DARK_RED + MessageCodes.UNDERLINE + randomString, message);
	}
	
	@Test
	public void underlineRandomTimesInARowAppendsOnlyOne() {
		int random = (int) (Math.random() * 100);
		String randomString = TestUtil.createRandomString();
		
		MessageBuilder builder = MessageBuilder.builder();
		TextBuilder textBuilder = builder.red();
		for (int i = 0; i < random; i++) {
			textBuilder = textBuilder.underline();
		}
		
		String message = textBuilder.text(randomString).build();
		
		assertEquals(MessageCodes.RED + MessageCodes.UNDERLINE + randomString, message);
	}
	
	@Test
	public void magicDarkRed() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().darkRed().magic().text(randomString).build();
		assertEquals(MessageCodes.DARK_RED + MessageCodes.MAGIC + randomString, message);
	}
	
	@Test
	public void magicRandomTimesInARowAppendsOnlyOne() {
		int random = (int) (Math.random() * 100);
		String randomString = TestUtil.createRandomString();
		
		MessageBuilder builder = MessageBuilder.builder();
		TextBuilder textBuilder = builder.red();
		for (int i = 0; i < random; i++) {
			textBuilder = textBuilder.magic();
		}
		
		String message = textBuilder.text(randomString).build();
		
		assertEquals(MessageCodes.RED + MessageCodes.MAGIC + randomString, message);
	}
	
	@Test
	public void strikeThroughDarkRed() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().darkRed().strikeThrough().text(randomString).build();
		assertEquals(MessageCodes.DARK_RED + MessageCodes.STRIKE_THROUGH + randomString, message);
	}
	
	@Test
	public void resetAfterColor() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().darkAqua().reset().text(randomString).build();
		assertEquals(MessageCodes.DARK_AQUA + MessageCodes.RESET + randomString, message);
	}
	
	@Test
	public void resetRandomTimesInARowAppendsOnlyOne() {
		int random = (int) (Math.random() * 100);
		String randomString = TestUtil.createRandomString();
		
		MessageBuilder builder = MessageBuilder.builder();
		TextBuilder textBuilder = builder.red();
		for (int i = 0; i < random; i++) {
			textBuilder = textBuilder.reset();
		}
		
		String message = textBuilder.text(randomString).build();
		
		assertEquals(MessageCodes.RED + MessageCodes.RESET + randomString, message);
	}
	
	@Test
	public void resetBeforeColor() {
		String randomString = TestUtil.createRandomString();
		String message = MessageBuilder.builder().reset().darkAqua().text(randomString).build();
		assertEquals(MessageCodes.RESET + MessageCodes.DARK_AQUA + randomString, message);
	}
	
	@Test
	public void resetRandomTimesInARowBeforeColorAppendsOnlyOne() {
		int random = (int) (Math.random() * 100);
		String randomString = TestUtil.createRandomString();
		
		MessageBuilder builder = MessageBuilder.builder().reset();

		for (int i = 0; i < random; i++) {
			builder = builder.reset();
		}
		
		String message = builder.red().text(randomString).build();
		
		assertEquals(MessageCodes.RESET + MessageCodes.RED + randomString, message);
	}
	
}
