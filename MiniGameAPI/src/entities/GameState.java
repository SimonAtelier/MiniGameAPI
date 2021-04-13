package entities;

import java.util.UUID;

public interface GameState {
	
	void tick(Game game);
	
	void onPlayerJoin(UUID uniquePlayerId);
	
	void onPlayerLeave(UUID uniquePlayerId);
	
	boolean canJoin();
	
}
