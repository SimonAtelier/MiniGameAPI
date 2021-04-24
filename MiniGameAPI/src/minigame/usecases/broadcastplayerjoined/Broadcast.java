package usecases.broadcastplayerjoined;

import java.util.UUID;

public class Broadcast {
	
	private UUID playerJoined;
	private UUID receiver;
	
	public UUID getPlayerJoined() {
		return playerJoined;
	}
	
	public void setPlayerJoined(UUID playerJoined) {
		this.playerJoined = playerJoined;
	}
	
	public UUID getReceiver() {
		return receiver;
	}
	
	public void setReceiver(UUID receiver) {
		this.receiver = receiver;
	}
	
}
