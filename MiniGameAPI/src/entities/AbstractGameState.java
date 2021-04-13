package entities;

public abstract class AbstractGameState implements GameState {

	private Game game;
	
	@Override
	public Game getGame() {
		return game;
	}
	
	
	@Override
	public void setGame(Game game) {
		this.game = game;
	}
	
}
