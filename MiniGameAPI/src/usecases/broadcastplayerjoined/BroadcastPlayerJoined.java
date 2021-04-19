package usecases.broadcastplayerjoined;

import java.util.List;
import java.util.UUID;

import gateways.GameGateway;

public interface BroadcastPlayerJoined {
	
	void execute(BroadcastGameMessageRequest request, BroadcastGameMessageResponse response);
	
	void setGameGateway(GameGateway gameGateway);
	
	public interface BroadcastGameMessageRequest {
		
		String getGameName();

		UUID getJoinedPlayer();
		
	}
	
	public interface BroadcastGameMessageResponse {
		
		void onPlayerJoinedIsInvalid();
		
		void onNoSuchGame(String gameName);

		void onNotifyJoinedPlayer(UUID joinedPlayer);
		
		void onNotifyOthers(List<Broadcast> broadcasts);
		
	}

}
