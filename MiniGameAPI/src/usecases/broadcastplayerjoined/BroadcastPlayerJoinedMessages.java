package usecases.broadcastplayerjoined;

public interface BroadcastPlayerJoinedMessages {

	static final String PLAYER_JOINED_GAME_MESSAGE_OTHERS = "'$name$' has joined the game.";
	
	static final String PLAYER_JOINED_GAME_MESSAGE_SELF = "You joined the game.";
	
	static final String PLAYER_JOINED_NO_SUCH_GAME_MESSAGE = "An internal error occured: The game '$name$' does not exist.";
	
	static final String PLAYER_JOINED_GAME_JOINED_PLAYER_ID_IS_INVALID = "An internal error occured: Invalid player-id.";
	
}
