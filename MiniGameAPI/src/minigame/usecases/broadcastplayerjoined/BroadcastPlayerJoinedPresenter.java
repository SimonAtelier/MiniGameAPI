package usecases.broadcastplayerjoined;

import java.util.List;
import java.util.UUID;

import usecases.broadcastplayerjoined.BroadcastPlayerJoined.BroadcastGameMessageResponse;

public class BroadcastPlayerJoinedPresenter implements BroadcastGameMessageResponse {
	
	private BroadcastPlayerJoinedView view;
	
	public BroadcastPlayerJoinedPresenter(BroadcastPlayerJoinedView view) {
		this.view = view;
	}
	
	@Override
	public void onNoSuchGame(String gameName) {
		String message = BroadcastPlayerJoinedMessages.PLAYER_JOINED_NO_SUCH_GAME_MESSAGE;
		message = message.replace("$name$", gameName);
		view.displayInternalError(message);
	}

	@Override
	public void onPlayerJoinedIsInvalid() {
		String message = BroadcastPlayerJoinedMessages.PLAYER_JOINED_GAME_JOINED_PLAYER_ID_IS_INVALID;
		view.displayInternalError(message);
	}

	@Override
	public void onNotifyOthers(List<Broadcast> broadcasts) {
		String text = BroadcastPlayerJoinedMessages.PLAYER_JOINED_GAME_MESSAGE_OTHERS;
		for (Broadcast message : broadcasts) {
			String messageText = text.replace("$name$", message.getPlayerJoined() + "");
			view.displayMessage(message.getReceiver(), messageText);
		}
	}

	@Override
	public void onNotifyJoinedPlayer(UUID joinedPlayer) {
		String message = BroadcastPlayerJoinedMessages.PLAYER_JOINED_GAME_MESSAGE_SELF;
		view.displayMessage(joinedPlayer, message);
	}
	
}
