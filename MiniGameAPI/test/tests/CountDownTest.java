package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import entities.CountDown;
import entities.CountDown.AlreadyPausedException;
import entities.CountDown.AlreadyStartedException;
import entities.CountDown.AlreadyStoppedException;
import entities.CountDownListener;

public class CountDownTest {

	private CountDown countDown;

	@Before
	public void setUp() {
		countDown = new CountDown();
	}

	@Test
	public void isStoppedByDefault() {
		assertEquals(true, countDown.isStopped());
	}

	@Test
	public void isPausedIsFalseByDefault() {
		assertEquals(false, countDown.isPaused());
	}

	@Test
	public void isStoppedReturnsFalseAfterStart() {
		countDown.start();
		assertEquals(false, countDown.isStopped());
	}

	@Test
	public void stopAfterStartReturnsTrueForIsStopped() {
		countDown.start();
		assertEquals(false, countDown.isStopped());
		countDown.stop();
		assertEquals(true, countDown.isStopped());
	}

	@Test
	public void getTickCountReturnsZeroByDefault() {
		long expected = 0;
		assertEquals(expected, countDown.getTickCount());
	}

	@Test
	public void tickStartedCountDownRandomTimesIncrementsTickCount() {
		int random = (int) (Math.random() * 1000);
		countDown.start();
		for (int i = 0; i < random; i++) {
			countDown.tick();
		}
		assertEquals(random, countDown.getTickCount());
	}

	@Test
	public void tickStoppedCountDownRandomTimesDoesNotIncrementTickCount() {
		int random = (int) (Math.random() * 500);
		for (int i = 0; i < random; i++) {
			countDown.tick();
		}
		assertEquals(0, countDown.getTickCount());
	}

	@Test
	public void stopResetsTickCountToZero() {
		countDown.start();
		countDown.tick();
		countDown.stop();
		assertEquals(0, countDown.getTickCount());
		countDown.start();
		countDown.tick();
		assertEquals(1, countDown.getTickCount());
	}

	@Test
	public void tickPausedCountDownRandomTimesDoesNotIncrementTickCount() {
		int random = (int) (Math.random() * 345);
		countDown.start();
		countDown.pause();
		for (int i = 0; i < random; i++) {
			countDown.tick();
		}
		assertEquals(0, countDown.getTickCount());
	}

	@Test
	public void pauseDoesNotResetTickCountToZero() {
		countDown.start();
		countDown.tick();
		countDown.pause();
		assertEquals(1, countDown.getTickCount());
	}

	@Test
	public void isPausedReturnsTrueAfterPause() {
		countDown.start();
		countDown.pause();
		assertEquals(true, countDown.isPaused());
	}

	@Test
	public void stopPaused() {
		countDown.start();
		countDown.tick();
		countDown.pause();
		countDown.stop();
		assertEquals(false, countDown.isPaused());
	}

	@Test
	public void secondsToCountDownAreZeroByDefault() {
		assertEquals(0, countDown.getSecondsToCountDown());
	}

	@Test
	public void setSecondsToCountDown() {
		int random = (int) (Math.random() * 301);
		countDown.setSecondsToCountDown(random);
		assertEquals(random, countDown.getSecondsToCountDown());
	}

	@Test
	public void isNotPausedIfStart() {
		countDown.start();
		assertEquals(false, countDown.isPaused());
	}

	@Test
	public void isNotStoppedIfStart() {
		countDown.start();
		assertEquals(false, countDown.isStopped());
	}

	@Test(expected = AlreadyStartedException.class)
	public void startTwiceThrowsException() {
		countDown.start();
		countDown.start();
	}

	@Test(expected = AlreadyStoppedException.class)
	public void stopWithoutStartThrowsException() {
		countDown.stop();
	}

	@Test(expected = AlreadyPausedException.class)
	public void pauseTwiceThrowsException() {
		countDown.pause();
		countDown.pause();
	}

	@Test
	public void isNotStoppedIfPaused() {
		countDown.pause();
		assertEquals(false, countDown.isStopped());
	}

	@Test
	public void fromPausedToStart() {
		int random = (int) (Math.random() * 400);
		countDown.pause();
		countDown.start();
		for (int i = 0; i < random; i++) {
			countDown.tick();
		}
		assertEquals(random, countDown.getTickCount());
	}

	@Test
	public void resume() {
		int random = (int) (Math.random() * 441);
		countDown.start();
		for (int i = 0; i < random; i++) {
			countDown.tick();
		}
		countDown.pause();
		countDown.tick();

		countDown.start();
		assertEquals(random, countDown.getTickCount());
	}

	@Test
	public void secondsLeftReturnsZeroByDefault() {
		assertEquals(0, countDown.getSecondsLeft());
	}

	@Test
	public void secondsLeftIsEqualToSecondsToCountDownAfterSet() {
		int random = (int) (Math.random() * 1000);
		countDown.setSecondsToCountDown(random);
		assertEquals(random, countDown.getSecondsLeft());
	}

	@Test
	public void countDownOneSecondWithTwentyTicks() {
		countDown.setSecondsToCountDown(10);
		countDown.start();

		for (int i = 0; i < 20; i++) {
			countDown.tick();
		}

		assertEquals(9, countDown.getSecondsLeft());
	}

	@Test
	public void secondsToCountDownAreNotChangedByTick() {
		countDown.setSecondsToCountDown(10);

		for (int i = 0; i < 20; i++) {
			countDown.tick();
		}

		assertEquals(10, countDown.getSecondsToCountDown());
	}

	@Test
	public void countDownMoreSecondsThanSetSecondsLeftIsZero() {
		int seconds = 10;
		countDown.setSecondsToCountDown(seconds);
		countDown.start();
		for (int i = 0; i < (seconds + 1) * 20; i++) {
			countDown.tick();
		}
		assertEquals(0, countDown.getSecondsLeft());
	}

	@Test
	public void getCountDownListenerIsNullByDefault() {
		assertEquals(null, countDown.getCountDownListener());
	}

	@Test
	public void getSetCountDownListener() {
		CountDownListener listener = new CountDownListenerTestMock();
		countDown.setCountDownListener(listener);
		assertEquals(true, listener == countDown.getCountDownListener());
	}

	@Test
	public void onOneSecondOver() {
		CountDownListenerTestMock listener = new CountDownListenerTestMock();
		countDown.setCountDownListener(listener);
		countDown.setSecondsToCountDown(10);
		countDown.start();
		for (int i = 0; i < 40; i++) {
			countDown.tick();
		}
		assertEquals(2, listener.getInvokedTimes());
	}

	@Test
	public void eventSource() {
		CountDownListenerTestMock listener = new CountDownListenerTestMock();
		countDown.setCountDownListener(listener);
		countDown.setSecondsToCountDown(1);
		countDown.start();
		for (int i = 0; i < 20; i++) {
			countDown.tick();
		}
		assertEquals(1, listener.getInvokedTimes());
		assertEquals(true, countDown == listener.getInvoker());
	}
	
	@Test
	public void startNotifiesListener() {
		CountDownListenerTestMock testMock = new CountDownListenerTestMock();
		countDown.setCountDownListener(testMock);
		countDown.setSecondsToCountDown(10);
		countDown.start();
		assertEquals(1, testMock.invokedTimesStart);
	}

	private class CountDownListenerTestMock implements CountDownListener {

		private CountDown invoker;
		private int invokedTimes = 0;
		private int invokedTimesStart;

		@Override
		public void onOneSecondOver(CountDown countDown) {
			invokedTimes++;
			invoker = countDown;
		}

		@Override
		public void onCountDownStarted(CountDown countDown) {
			invokedTimesStart++;
			invoker = countDown;
		}

		public int getInvokedTimes() {
			return invokedTimes;
		}

		public CountDown getInvoker() {
			return invoker;
		}

	}

}
