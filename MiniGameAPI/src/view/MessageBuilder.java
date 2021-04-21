package view;

public class MessageBuilder {

	StringBuffer buffer;

	private MessageBuilder() {
		buffer = new StringBuffer();
	}
	
	public static MessageBuilder builder() {
		return new MessageBuilder();
	}

	public String build() {
		return buffer.toString();
	}
	
	public MessageBuilder text(String text) {
		buffer.append(text);
		return this;
	}

	public MessageBuilder black() {
		buffer.append(MessageCodes.BLACK);
		return this;
	}

	public MessageBuilder darkBlue() {
		buffer.append(MessageCodes.DARK_BLUE);
		return this;
	}

	public MessageBuilder darkGreen() {
		buffer.append(MessageCodes.DARK_GREEN);
		return this;
	}

	public MessageBuilder darkAqua() {
		buffer.append(MessageCodes.DARK_AQUA);
		return this;
	}

	public MessageBuilder darkRed() {
		buffer.append(MessageCodes.DARK_RED);
		return this;
	}

	public MessageBuilder darkPurple() {
		buffer.append(MessageCodes.DARK_PURPLE);
		return this;
	}

	public MessageBuilder gold() {
		buffer.append(MessageCodes.GOLD);
		return this;
	}

	public MessageBuilder gray() {
		buffer.append(MessageCodes.GRAY);
		return this;
	}

	public MessageBuilder darkGray() {
		buffer.append(MessageCodes.DARK_GRAY);
		return this;
	}

	public MessageBuilder blue() {
		buffer.append(MessageCodes.BLUE);
		return this;
	}

	public MessageBuilder green() {
		buffer.append(MessageCodes.GREEN);
		return this;
	}

	public MessageBuilder aqua() {
		buffer.append(MessageCodes.AQUA);
		return this;
	}

	public MessageBuilder red() {
		buffer.append(MessageCodes.RED);
		return this;
	}

	public MessageBuilder lightPurple() {
		buffer.append(MessageCodes.LIGHT_PURPLE);
		return this;
	}

	public MessageBuilder yellow() {
		buffer.append(MessageCodes.YELLOW);
		return this;
	}

	public MessageBuilder white() {
		buffer.append(MessageCodes.WHITE);
		return this;
	}

	public MessageBuilder magic() {
		buffer.append(MessageCodes.MAGIC);
		return this;
	}

	public MessageBuilder bold() {
		buffer.append(MessageCodes.BOLD);
		return this;
	}
	
	public MessageBuilder strikeThrough() {
		buffer.append(MessageCodes.STRIKE_THROUGH);
		return this;
	}
	
	public MessageBuilder underline() {
		buffer.append(MessageCodes.UNDERLINE);
		return this;
	}
	
	public MessageBuilder italic() {
		buffer.append(MessageCodes.ITALIC);
		return this;
	}
	
	public MessageBuilder reset() {
		buffer.append(MessageCodes.RESET);
		return this;
	}
	
}
