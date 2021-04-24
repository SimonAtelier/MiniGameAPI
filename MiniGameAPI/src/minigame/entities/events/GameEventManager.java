package minigame.entities.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameEventManager {

	private int listenersCount;
	private static GameEventManager instance;
	private HashMap<Class<?>, List<GameEventListener<? extends GameEvent>>> listenersMap;
	
	private GameEventManager() {
		listenersMap = new HashMap<>();
	}
	
	public <T extends GameEvent> void registerEventListener(Class<? extends T> typeFilter, GameEventListener<T> listener) {
		List<GameEventListener<? extends GameEvent>> listeners = listenersMap.get(typeFilter);
		if (listeners == null) {
			listeners = new ArrayList<>();
			listenersMap.put(typeFilter, listeners);
		}
		listeners.add(listener);
		listenersCount++;
	}
	
	public <T extends GameEvent> void broadcastEvent(T event) {
		List<GameEventListener<? extends GameEvent>> listeners = listenersMap.get(event.getClass());
		
		if (listeners == null)
			return;
		
		for (GameEventListener l : listeners)
			l.onEvent(event);
	}
	
	public void clearListeners() {
		listenersCount = 0;
	}
	
	public int getListenersCount() {
		return listenersCount;
	}
	
	public static GameEventManager getInstance() {
		if (instance == null)
			instance = new GameEventManager();
		return instance;
	}
	
}
