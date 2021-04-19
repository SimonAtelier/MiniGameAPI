package usecases.broadcastplayerjoined;

import java.util.UUID;

import usecases.broadcastplayerjoined.BroadcastPlayerJoined.BroadcastGameMessageRequest;

public class BroadcastPlayerJoinedRequestModel implements BroadcastGameMessageRequest {

	private String gameName;
	private UUID joinedPlayer;
	
	public String getGameName() {
		return gameName;
	}
	
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	public UUID getJoinedPlayer() {
		return joinedPlayer;
	}
	
	public void setJoinedPlayer(UUID joinedPlayer) {
		this.joinedPlayer = joinedPlayer;
	}

}
