package usecases.broadcastplayerjoined;

import java.util.UUID;

public interface BroadcastPlayerJoinedView {
	
	void displayMessage(UUID uniquePlayerId, String message);
	
	void displayInternalError(String message);

}
