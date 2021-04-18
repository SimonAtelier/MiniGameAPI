package entities;

import java.util.ArrayList;
import java.util.List;

import entities.events.GameEventManager;
import entities.events.PlayerJoinedGameEvent;

public class Game {

	private long tickCount;
	private GameState gameState;
	private List<Player> players;

	public Game() {
		players = new ArrayList<Player>();
	}

	public void tick() {
		incrementTickCount();
		tickGameState();
	}

	public void join(Player player) {
		if (gameStateIsNull())
			throw new CannotJoinException();

		if (gameStateDoesNotAllowJoin())
			throw new CannotJoinException();

		if (playerAlreadyJoined(player))
			throw new AlreadyJoinedException();

		addPlayer(player);
		firePlayerJoinedEvent(player);
		notifyGameStateAboutPlayerJoin(player);
	}

	public void leave(Player player) {
		if (!playerAlreadyJoined(player))
			throw new CannotLeaveException();

		removePlayer(player);
		notifyGameStateAboutPlayerLeave(player);
	}
	
	private void firePlayerJoinedEvent(Player player) {
		GameEventManager.getInstance().broadcastEvent(new PlayerJoinedGameEvent(this, player));
	}

	private void notifyGameStateAboutPlayerJoin(Player player) {
		gameState.onPlayerJoin(player);
	}

	private void notifyGameStateAboutPlayerLeave(Player player) {
		gameState.onPlayerLeave(player);
	}

	private void incrementTickCount() {
		tickCount++;
	}

	private void addPlayer(Player player) {
		players.add(player);
	}

	private void removePlayer(Player player) {
		players.remove(player);
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

	private boolean playerAlreadyJoined(Player player) {
		return players.contains(player);
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
