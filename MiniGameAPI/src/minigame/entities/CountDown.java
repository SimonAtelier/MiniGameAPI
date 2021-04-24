package minigame.entities;

public class CountDown {

	private long tickCount;
	private long secondsToCountDown;
	private long secondsLeft;
	private State state;
	private CountDownListener listener;

	public CountDown() {
		state = new StoppedState();
	}

	public void tick() {
		state.tick();
	}

	public void start() {
		state.start();
	}

	public void stop() {
		state.stop();
	}

	public void pause() {
		state.pause();
	}
	
	private void fireOneSecondOver() {
		if (listener == null)
			return;
		listener.onOneSecondOver(this);
	}
	
	private void fireStarted() {
		if (listener == null)
			return;
		listener.onCountDownStarted(this);
	}

	public boolean isStopped() {
		return state.isStopped();
	}

	public boolean isPaused() {
		return state.isPaused();
	}

	public long getTickCount() {
		return tickCount;
	}

	private void setTickCount(long tickCount) {
		this.tickCount = tickCount;
	}

	public long getSecondsToCountDown() {
		return secondsToCountDown;
	}

	public void setSecondsToCountDown(long secondToCountDown) {
		this.secondsToCountDown = secondToCountDown;
		this.secondsLeft = secondToCountDown;
	}

	public long getSecondsLeft() {
		return secondsLeft;
	}

	private void setState(State state) {
		this.state = state;
		state.onEnterState();
	}
	
	public CountDownListener getCountDownListener() {
		return listener;
	}
	
	public void setCountDownListener(CountDownListener listener) {
		this.listener = listener;
	}

	private abstract class State {

		public abstract void tick();

		public abstract void start();

		public abstract void stop();

		public abstract void pause();

		public abstract boolean isStopped();

		public abstract boolean isPaused();

		public abstract void onEnterState();

	}

	private class StoppedState extends State {

		@Override
		public void tick() {
			// Do nothing
		}

		@Override
		public void start() {
			setState(new StartedState());
		}

		@Override
		public void stop() {
			throw new AlreadyStoppedException();
		}

		@Override
		public void pause() {
			setState(new PausedState());
		}

		@Override
		public boolean isStopped() {
			return true;
		}

		@Override
		public boolean isPaused() {
			return false;
		}

		@Override
		public void onEnterState() {
			setTickCount(0);
		}

	}

	private class StartedState extends State {

		@Override
		public void tick() {
			tickCount++;
			if (tickedOneSeconds() && stillSecondsLeft()) {
				secondsLeft--;
				fireOneSecondOver();
			}
		}

		private boolean tickedOneSeconds() {
			return tickCount % 20 == 0;
		}

		private boolean stillSecondsLeft() {
			return secondsLeft > 0;
		}

		@Override
		public void start() {
			throw new AlreadyStartedException();
		}

		@Override
		public void stop() {
			setState(new StoppedState());
		}

		@Override
		public void pause() {
			setState(new PausedState());
		}

		@Override
		public boolean isStopped() {
			return false;
		}

		@Override
		public boolean isPaused() {
			return false;
		}

		@Override
		public void onEnterState() {
			fireStarted();
		}

	}

	private class PausedState extends State {

		@Override
		public void tick() {
			// Do nothing
		}

		@Override
		public void start() {
			setState(new StartedState());
		}

		@Override
		public void stop() {
			setState(new StoppedState());
		}

		@Override
		public void pause() {
			throw new AlreadyPausedException();
		}

		@Override
		public boolean isStopped() {
			return false;
		}

		@Override
		public boolean isPaused() {
			return true;
		}

		@Override
		public void onEnterState() {
			// Do nothing
		}

	}

	public class AlreadyPausedException extends RuntimeException {

		private static final long serialVersionUID = 1L;

	}

	public class AlreadyStartedException extends RuntimeException {

		private static final long serialVersionUID = 1L;

	}

	public class AlreadyStoppedException extends RuntimeException {

		private static final long serialVersionUID = 1L;

	}

}
