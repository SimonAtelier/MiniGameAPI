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
		if (gameStateIsNull())
			throw new CannotJoinException();

		if (gameStateDoesNotAllowJoin())
			throw new CannotJoinException();

		if (playerAlreadyJoined(uniquePlayerId))
			throw new AlreadyJoinedException();

		addPlayer(uniquePlayerId);

		notifyGameStateAboutPlayerJoin(uniquePlayerId);
	}

	public void leave(UUID uniquePlayerId) {
		if (!playerAlreadyJoined(uniquePlayerId))
			throw new CannotLeaveException();

		if (gameStateIsNull())
			throw new CannotLeaveException();

		removePlayer(uniquePlayerId);

		notifyGameStateAboutPlayerLeave(uniquePlayerId);
	}

	private void notifyGameStateAboutPlayerJoin(UUID uniquePlayerId) {
		gameState.onPlayerJoin(uniquePlayerId);
	}

	private void notifyGameStateAboutPlayerLeave(UUID uniquePlayerId) {
		gameState.onPlayerLeave(uniquePlayerId);
	}

	private void incrementTickCount() {
		tickCount++;
	}

	private void addPlayer(UUID uniquePlayerId) {
		players.add(uniquePlayerId);
	}

	private void removePlayer(UUID uniquePlayerId) {
		players.remove(uniquePlayerId);
	}

	private void tickGameState() {
		if (gameStateIsNull())
			return;

		getGameState().tick();
	}

	private boolean gameStateIsNull() {
		return getGameState() == null;
	}

	private boolean gameStateDoesNotAllowJoin() {
		return !getGameState().canJoin();
	}

	private boolean playerAlreadyJoined(UUID uniquePlayerId) {
		return players.contains(uniquePlayerId);
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
		if (this.gameState != null)
			this.gameState.onLeaveGameState();
		this.gameState = gameState;
		gameState.setGame(this);
		gameState.onEnterGameState();
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
