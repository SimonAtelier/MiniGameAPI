package usecases.broadcastplayerjoined;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import entities.Game;
import gateways.GameGateway;

public class BroadcastPlayerJoinedUseCase implements BroadcastPlayerJoined {

	private Game game;
	private BroadcastGameMessageRequest request;
	private BroadcastGameMessageResponse response;
	private GameGateway gameGateway;
	
	@Override
	public void execute(BroadcastGameMessageRequest request, BroadcastGameMessageResponse response) {
		setRequest(request);
		setResponse(response);
		findGame();
		
		if (joinedPlayerIsNull()) {
			response.onPlayerJoinedIsInvalid();
			return;
		}
		
		if (noGameFound()) {
			response.onNoSuchGame(request.getGameName() + "");
			return;
		}
		
		notifyJoinedPlayer();
		notifyOtherPlayers();
	}
	
	private boolean joinedPlayerIsNull() {
		return getRequest().getJoinedPlayer() == null;
	}
	
	private void notifyJoinedPlayer() {
		response.onNotifyJoinedPlayer(getRequest().getJoinedPlayer());
	}
	
	private void notifyOtherPlayers() {
		response.onNotifyOthers(createBroadcasts());
	}
	
	private void findGame() {
		game = getGameGateway().findGameByName(getRequest().getGameName());
	}
	
	private boolean noGameFound() {
		return getGame() == null;
	}
		
	private List<Broadcast> createBroadcasts() {
		List<Broadcast> broadcasts = new ArrayList<Broadcast>();
		
		for (int i = 0; i < getGame().getPlayersCount(); i++) {
			UUID receiver = getGame().getPlayerAt(i).getUniqueId();
			if (receiver.equals(getJoinedPlayerId()))
				continue;
			broadcasts.add(createBroadcast(receiver));
		}
		
		return broadcasts;
	}
	
	private Broadcast createBroadcast(UUID receiver) {
		Broadcast broadcast = new Broadcast();
		broadcast.setPlayerJoined(getJoinedPlayerId());
		broadcast.setReceiver(receiver);
		return broadcast;
	}
	
	private Game getGame() {
		return game;
	}
	
	private UUID getJoinedPlayerId() {
		return getRequest().getJoinedPlayer();
	}
	
	private BroadcastGameMessageRequest getRequest() {
		return request;
	}
	
	private void setRequest(BroadcastGameMessageRequest request) {
		this.request = request;
	}

	private void setResponse(BroadcastGameMessageResponse response) {
		this.response = response;
	}
	
	private GameGateway getGameGateway() {
		return gameGateway;
	}

	@Override
	public void setGameGateway(GameGateway gameGateway) {
		this.gameGateway = gameGateway;
	}

}
