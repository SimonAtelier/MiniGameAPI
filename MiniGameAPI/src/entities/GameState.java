package entities;

import java.util.UUID;

public interface GameState {
	
	void tick();
	
	void onPlayerJoin(UUID uniquePlayerId);
	
	void onPlayerLeave(UUID uniquePlayerId);
	
	boolean canJoin();
	
	Game getGame();
	
	void setGame(Game game);
	
	void onEnterGameState();
	
	void onLeaveGameState();
	
}
