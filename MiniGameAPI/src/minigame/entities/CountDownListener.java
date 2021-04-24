package minigame.entities;

public interface CountDownListener {
	
	void onCountDownStarted(CountDown countDown);
	
	void onOneSecondOver(CountDown countDown);
	
}
