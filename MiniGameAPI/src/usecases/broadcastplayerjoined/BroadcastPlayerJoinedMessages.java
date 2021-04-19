package usecases.broadcastplayerjoined;

public interface BroadcastPlayerJoinedMessages {

	static final String PLAYER_JOINED_GAME_MESSAGE_OTHERS = "Der Spieler '$name$' hat das Spiel betreten.";
	
	static final String PLAYER_JOINED_GAME_MESSAGE_SELF = "Du hast das Spiel betreten.";
	
	static final String PLAYER_JOINED_NO_SUCH_GAME_MESSAGE = "Es existiert kein Spiel mit dem Namen '$name$'.";
	
	static final String PLAYER_JOINED_GAME_JOINED_PLAYER_ID_IS_INVALID = "Ungültige Spieler-Id.";
	
}
