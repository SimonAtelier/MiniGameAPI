package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {

	private long tickCount;
	private GameState gameState;
	private List<UUID> players;
	
	public Game() {
		players = new ArrayList<UUID>();
	}
	
	public void tick() {
		incrementTickCount();
		tickGameState();
	}
	
	public void join(UUID uniquePlayerId) {		
		if (gameState == null)
			throw new CannotJoinException();
		
		if (!gameState.canJoin())
			throw new CannotJoinException();
		
		if (players.contains(uniquePlayerId))
			throw new AlreadyJoinedException();
		
		gameState.onPlayerJoin(uniquePlayerId);
		players.add(uniquePlayerId);
	}
	
	public void leave(UUID uniquePlayerId) {
		if (!players.contains(uniquePlayerId))
			throw new CannotLeaveException();
		
		if (gameState == null)
			throw new CannotLeaveException();
		
		gameState.onPlayerLeave(uniquePlayerId);
	}
	
	private void incrementTickCount() {
		tickCount++;
	}
	 
	private void tickGameState() {
		if (getGameState() == null)
			return;
		
		getGameState().tick();
	}
	
	public long getTickCount() {
		return tickCount;
	}
	
	public int getPlayersCount() {
		return players.size();
	}
	
	public GameState getGameState() {
		return gameState;
	}
	
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
		gameState.setGame(this);
	}
	
	public class AlreadyJoinedException extends RuntimeException {
		
		private static final long serialVersionUID = 1L;
		
	}
	
	public class CannotJoinException extends RuntimeException {

		private static final long serialVersionUID = 1L;
		
	}
	
	public class CannotLeaveException extends RuntimeException {
		
		private static final long serialVersionUID = 1L;
		
	}
	
}
