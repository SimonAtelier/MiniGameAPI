package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import minigame.entities.AbstractGameState;
import minigame.entities.Game;
import minigame.entities.Player;
import minigame.gateways.GameGateway;
import usecases.broadcastplayerjoined.Broadcast;
import usecases.broadcastplayerjoined.BroadcastPlayerJoined.BroadcastGameMessageResponse;
import usecases.broadcastplayerjoined.BroadcastPlayerJoinedMessages;
import usecases.broadcastplayerjoined.BroadcastPlayerJoinedPresenter;
import usecases.broadcastplayerjoined.BroadcastPlayerJoinedRequestModel;
import usecases.broadcastplayerjoined.BroadcastPlayerJoinedUseCase;
import usecases.broadcastplayerjoined.BroadcastPlayerJoinedView;

public class UseCaseBroadcastPlayerJoinedTest {

	private Game game;
	private Player joinedPlayer;
	private BroadcastPlayerJoinedRequestModel requestModel;
	private List<UUID> otherPlayers;

	@Before
	public void setUp() {
		joinedPlayer = new Player(UUID.randomUUID());
		otherPlayers = new ArrayList<UUID>();

		game = new Game();
		game.setName("MyGame");
		game.setGameState(new GameStateTestMock());

		for (int i = 0; i < 10; i++) {
			UUID uniquePlayerId = UUID.randomUUID();
			Player player = new Player(uniquePlayerId);
			game.join(player);
			otherPlayers.add(uniquePlayerId);
		}

		game.join(joinedPlayer);

		requestModel = new BroadcastPlayerJoinedRequestModel();
		requestModel.setGameName("MyGame");
		requestModel.setJoinedPlayer(joinedPlayer.getUniqueId());
	}

	@Test
	public void notifyOthersDoesNotNotifyJoinedPlayer() {
		PresenterMock presenter = new PresenterMock();
		BroadcastPlayerJoinedUseCase useCase = new BroadcastPlayerJoinedUseCase();
		useCase.setGameGateway(new GameGatewayTestMock());
		useCase.execute(requestModel, presenter);

		for (Broadcast broadcast : presenter.broadcasts) {
			assertTrue(broadcast.getPlayerJoined() != broadcast.getReceiver());
			assertTrue(broadcast.getReceiver() != joinedPlayer.getUniqueId());
		}
	}

	@Test
	public void successNotifiesJoinedPlayerAndOtherPlayers() {
		PresenterMock presenter = new PresenterMock();
		BroadcastPlayerJoinedUseCase useCase = new BroadcastPlayerJoinedUseCase();
		useCase.setGameGateway(new GameGatewayTestMock());
		useCase.execute(requestModel, presenter);

		assertEquals(0, presenter.joinedPlayerInvalidCount);
		assertEquals(0, presenter.noSuchGameCount);
		assertEquals(1, presenter.notifyJoinedPlayerCount);
		assertEquals(1, presenter.notifyOthersCount);
	}

	@Test
	public void broadcastsSizeIsEqualsToPlayerCountMinusOne() {
		PresenterMock presenter = new PresenterMock();
		BroadcastPlayerJoinedUseCase useCase = new BroadcastPlayerJoinedUseCase();
		useCase.setGameGateway(new GameGatewayTestMock());
		useCase.execute(requestModel, presenter);
		assertEquals(game.getPlayersCount() - 1, presenter.broadcasts.size());
	}

	@Test
	public void broadcastInfoPlayerJoinedIsPlayerJoined() {
		PresenterMock presenter = new PresenterMock();
		BroadcastPlayerJoinedUseCase useCase = new BroadcastPlayerJoinedUseCase();
		useCase.setGameGateway(new GameGatewayTestMock());
		useCase.execute(requestModel, presenter);

		for (Broadcast broadcast : presenter.broadcasts) {
			assertTrue(broadcast.getPlayerJoined() == joinedPlayer.getUniqueId());
		}
	}

	@Test
	public void sendRequestWithUnkownGameNameSendsNoSuchGameResponse() {
		String gameName = "UnknownNameTest";
		requestModel.setGameName(gameName);

		PresenterMock presenter = new PresenterMock();
		BroadcastPlayerJoinedUseCase useCase = new BroadcastPlayerJoinedUseCase();
		useCase.setGameGateway(new GameGatewayTestMock());
		useCase.execute(requestModel, presenter);

		assertEquals(presenter.noSuchGame, gameName);
		assertEquals(0, presenter.joinedPlayerInvalidCount);
		assertEquals(1, presenter.noSuchGameCount);
		assertEquals(0, presenter.notifyJoinedPlayerCount);
		assertEquals(0, presenter.notifyOthersCount);
	}

	@Test
	public void sendRequestWithNullGameSendsNoSuchGameResponse() {
		String gameName = null;
		requestModel.setGameName(gameName);

		PresenterMock presenter = new PresenterMock();
		BroadcastPlayerJoinedUseCase useCase = new BroadcastPlayerJoinedUseCase();
		useCase.setGameGateway(new GameGatewayTestMock());
		useCase.execute(requestModel, presenter);

		assertEquals(0, presenter.joinedPlayerInvalidCount);
		assertEquals(1, presenter.noSuchGameCount);
		assertEquals(0, presenter.notifyJoinedPlayerCount);
		assertEquals(0, presenter.notifyOthersCount);
	}

	@Test
	public void sendRequestWithNullPlayerSendsPlayerInvalidResponse() {
		UUID joinedPlayer = null;
		requestModel.setJoinedPlayer(joinedPlayer);

		PresenterMock presenter = new PresenterMock();
		BroadcastPlayerJoinedUseCase useCase = new BroadcastPlayerJoinedUseCase();
		useCase.setGameGateway(new GameGatewayTestMock());
		useCase.execute(requestModel, presenter);

		assertEquals(1, presenter.joinedPlayerInvalidCount);
		assertEquals(0, presenter.noSuchGameCount);
		assertEquals(0, presenter.notifyJoinedPlayerCount);
		assertEquals(0, presenter.notifyOthersCount);
	}

	@Test
	public void notifiedPlayersAreJoinedPlayers() {
		PresenterMock presenter = new PresenterMock();
		BroadcastPlayerJoinedUseCase useCase = new BroadcastPlayerJoinedUseCase();
		useCase.setGameGateway(new GameGatewayTestMock());
		useCase.execute(requestModel, presenter);

		for (Broadcast broadcast : presenter.broadcasts) {
			assertTrue(otherPlayers.contains(broadcast.getReceiver()));
		}
	}

	@Test
	public void joinedPlayerIsNotified() {
		PresenterMock presenter = new PresenterMock();
		BroadcastPlayerJoinedUseCase useCase = new BroadcastPlayerJoinedUseCase();
		useCase.setGameGateway(new GameGatewayTestMock());
		useCase.execute(requestModel, presenter);

		assertEquals(presenter.joinedPlayer, joinedPlayer.getUniqueId());
	}

	@Test
	public void presenterSendsMessagesAmountEqualsToPlayersCountWhenSuccessPath() {
		BroadcastPlayerJoinedViewMock view = new BroadcastPlayerJoinedViewMock();
		BroadcastPlayerJoinedPresenter presenter = new BroadcastPlayerJoinedPresenter(view);
		BroadcastPlayerJoinedUseCase useCase = new BroadcastPlayerJoinedUseCase();
		useCase.setGameGateway(new GameGatewayTestMock());
		useCase.execute(requestModel, presenter);

		assertEquals(game.getPlayersCount(), view.messageCount);
	}

	@Test
	public void otherPlayersReceivedOneMessageEachWhenSuccessPath() {
		BroadcastPlayerJoinedViewMock view = new BroadcastPlayerJoinedViewMock();
		BroadcastPlayerJoinedPresenter presenter = new BroadcastPlayerJoinedPresenter(view);
		BroadcastPlayerJoinedUseCase useCase = new BroadcastPlayerJoinedUseCase();
		useCase.setGameGateway(new GameGatewayTestMock());
		useCase.execute(requestModel, presenter);

		for (UUID uniquePlayerId : otherPlayers) {
			List<String> messages = view.receivedMessages.get(uniquePlayerId);
			assertEquals(1, messages.size());
		}
	}

	@Test
	public void joinedPlayerReceivedOneMessageWhenSuccessPath() {
		BroadcastPlayerJoinedViewMock view = new BroadcastPlayerJoinedViewMock();
		BroadcastPlayerJoinedPresenter presenter = new BroadcastPlayerJoinedPresenter(view);
		BroadcastPlayerJoinedUseCase useCase = new BroadcastPlayerJoinedUseCase();
		useCase.setGameGateway(new GameGatewayTestMock());
		useCase.execute(requestModel, presenter);

		List<String> messages = view.receivedMessages.get(joinedPlayer.getUniqueId());
		assertEquals(1, messages.size());
	}

	@Test
	public void messageToOtherPlayers() {
		BroadcastPlayerJoinedViewMock view = new BroadcastPlayerJoinedViewMock();
		BroadcastPlayerJoinedPresenter presenter = new BroadcastPlayerJoinedPresenter(view);
		BroadcastPlayerJoinedUseCase useCase = new BroadcastPlayerJoinedUseCase();
		useCase.setGameGateway(new GameGatewayTestMock());
		useCase.execute(requestModel, presenter);

		String expectedMessage = BroadcastPlayerJoinedMessages.PLAYER_JOINED_GAME_MESSAGE_OTHERS.replace("$name$",
				joinedPlayer.getUniqueId() + "");

		for (UUID uniquePlayerId : otherPlayers) {
			List<String> messages = view.receivedMessages.get(uniquePlayerId);
			assertEquals(expectedMessage, messages.get(0));
		}
	}

	@Test
	public void messageToJoinedPlayer() {
		BroadcastPlayerJoinedViewMock view = new BroadcastPlayerJoinedViewMock();
		BroadcastPlayerJoinedPresenter presenter = new BroadcastPlayerJoinedPresenter(view);
		BroadcastPlayerJoinedUseCase useCase = new BroadcastPlayerJoinedUseCase();
		useCase.setGameGateway(new GameGatewayTestMock());
		useCase.execute(requestModel, presenter);

		String expectedMessage = BroadcastPlayerJoinedMessages.PLAYER_JOINED_GAME_MESSAGE_SELF;
		List<String> messages = view.receivedMessages.get(joinedPlayer.getUniqueId());
		assertEquals(expectedMessage, messages.get(0));
	}
	
	@Test
	public void messageWhenGameIsNull() {
		requestModel.setGameName(null);
		
		BroadcastPlayerJoinedViewMock view = new BroadcastPlayerJoinedViewMock();
		BroadcastPlayerJoinedPresenter presenter = new BroadcastPlayerJoinedPresenter(view);
		BroadcastPlayerJoinedUseCase useCase = new BroadcastPlayerJoinedUseCase();
		useCase.setGameGateway(new GameGatewayTestMock());
		useCase.execute(requestModel, presenter);

		String expectedMessage = BroadcastPlayerJoinedMessages.PLAYER_JOINED_NO_SUCH_GAME_MESSAGE;
		expectedMessage = expectedMessage.replace("$name$", "null");
		assertEquals(1, view.internalErrorCount);
		assertEquals(expectedMessage, view.errorMessage);
	}
	
	@Test
	public void messageWhenGameNameIsUnknown() {
		requestModel.setGameName("InvalidName");
		
		BroadcastPlayerJoinedViewMock view = new BroadcastPlayerJoinedViewMock();
		BroadcastPlayerJoinedPresenter presenter = new BroadcastPlayerJoinedPresenter(view);
		BroadcastPlayerJoinedUseCase useCase = new BroadcastPlayerJoinedUseCase();
		useCase.setGameGateway(new GameGatewayTestMock());
		useCase.execute(requestModel, presenter);

		String expectedMessage = BroadcastPlayerJoinedMessages.PLAYER_JOINED_NO_SUCH_GAME_MESSAGE;
		expectedMessage = expectedMessage.replace("$name$", "InvalidName");
		assertEquals(1, view.internalErrorCount);
		assertEquals(expectedMessage, view.errorMessage);
	}
	
	@Test
	public void messageWhenJoinedPlayerIsNull() {
		requestModel.setJoinedPlayer(null);
		
		BroadcastPlayerJoinedViewMock view = new BroadcastPlayerJoinedViewMock();
		BroadcastPlayerJoinedPresenter presenter = new BroadcastPlayerJoinedPresenter(view);
		BroadcastPlayerJoinedUseCase useCase = new BroadcastPlayerJoinedUseCase();
		useCase.setGameGateway(new GameGatewayTestMock());
		useCase.execute(requestModel, presenter);

		String expectedMessage = BroadcastPlayerJoinedMessages.PLAYER_JOINED_GAME_JOINED_PLAYER_ID_IS_INVALID;
		assertEquals(1, view.internalErrorCount);
		assertEquals(expectedMessage, view.errorMessage);
	}

	private class PresenterMock implements BroadcastGameMessageResponse {

		int joinedPlayerInvalidCount;
		int noSuchGameCount;
		int notifyJoinedPlayerCount;
		int notifyOthersCount;

		String noSuchGame;
		UUID joinedPlayer;
		List<Broadcast> broadcasts;

		@Override
		public void onPlayerJoinedIsInvalid() {
			joinedPlayerInvalidCount++;
		}

		@Override
		public void onNoSuchGame(String gameName) {
			this.noSuchGame = gameName;
			noSuchGameCount++;
		}

		@Override
		public void onNotifyJoinedPlayer(UUID joinedPlayer) {
			this.joinedPlayer = joinedPlayer;
			notifyJoinedPlayerCount++;
		}

		@Override
		public void onNotifyOthers(List<Broadcast> broadcasts) {
			this.broadcasts = broadcasts;
			notifyOthersCount++;
		}

	}

	private class GameGatewayTestMock implements GameGateway {

		@Override
		public Game findGameByName(String gameName) {
			if (game.getName().equals(gameName))
				return game;
			return null;
		}

	}

	private class BroadcastPlayerJoinedViewMock implements BroadcastPlayerJoinedView {

		int internalErrorCount;
		int messageCount;
		String errorMessage;
		HashMap<UUID, List<String>> receivedMessages = new HashMap<UUID, List<String>>();

		@Override
		public void displayMessage(UUID uniquePlayerId, String message) {
			List<String> messages = receivedMessages.get(uniquePlayerId);
			if (messages == null) {
				messages = new ArrayList<String>();
				receivedMessages.put(uniquePlayerId, messages);
			}
			messages.add(message);

			messageCount++;

			boolean correctReceiver = otherPlayers.contains(uniquePlayerId)
					|| uniquePlayerId == joinedPlayer.getUniqueId();
			assertTrue(correctReceiver);
		}

		@Override
		public void displayInternalError(String message) {
			errorMessage = message;
			internalErrorCount++;
		}

	}

	private class GameStateTestMock extends AbstractGameState {

		@Override
		public void tick() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPlayerJoin(Player player) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPlayerLeave(Player player) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean canJoin() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public void onEnterGameState() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onLeaveGameState() {
			// TODO Auto-generated method stub

		}

	}

}
