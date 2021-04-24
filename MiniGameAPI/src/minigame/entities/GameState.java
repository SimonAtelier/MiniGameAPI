package minigame.entities;

public interface GameState {
	
	void tick();
	
	void onPlayerJoin(Player player);
	
	void onPlayerLeave(Player player);
	
	boolean canJoin();
	
	Game getGame();
	
	void setGame(Game game);
	
	void onEnterGameState();
	
	void onLeaveGameState();
	
}
