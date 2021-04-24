package minigame.entities.events;

public interface GameEventListener<T extends GameEvent> {

	void onEvent(T e);
	
}
