package minigame.entities;

import java.util.HashMap;

public class ColorParser {

	private static HashMap<String, Color> colorMap;

	static {
		colorMap = new HashMap<String, Color>();

		for (Color color : Color.values()) {
			colorMap.put(color.toString().toLowerCase().replace("_", ""), color);
		}
	}

	private String toKey(String str) {
		return str.toLowerCase().replace("_", "").replace(" ", "");
	}

	public Color parse(String stringToParse) {
		if (stringToParse == null)
			throw new ColorFormatException();

		String key = toKey(stringToParse);

		if (!colorMap.containsKey(key))
			throw new ColorFormatException();

		return colorMap.get(key);
	}

	public class ColorFormatException extends RuntimeException {

		private static final long serialVersionUID = 1L;

	}

}
