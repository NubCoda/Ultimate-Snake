package Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Vector;

import Model.AppleModel;
import Model.DatabaseConnectionModel;
import Model.Logic;
import Model.OpponentModel;
import Model.SnakeHeadModel;
import Model.SnakeTailModel;
import Model.StatusbarModel;
import Model.Interface.Difficuty;
import Model.Interface.Direction;
import Model.Interface.IActor;
import Model.Interface.IConstants;
import Properties.Player;
import View.AppleView;
import View.GamePanelView;
import View.MainView;
import View.MenuView;
import View.OpponentView;
import View.SnakeHeadView;
import View.SnakeTailView;
import View.StatusbarView;

/**
 *
 *
 */
public class MainController {
	private static MainController MAIN_CONTROLLER_INSTANCE;
	private GamePanelView gamePanelView;
	private Logic logic;
	private SnakeHeadModel snakeHeadModel;
	private StatusbarView statusbar;
	private StatusbarModel statusbarModel;
	private Player player;
	private DatabaseConnectionModel connectionModel;
	private boolean isGameStarted;

	/**
	 * 
	 */
	private MainController() {
		connectionModel = new DatabaseConnectionModel();
		player = connectionModel.getSinglePlayer(OptionsController
				.getInstance().getOption("player"));
		createWindow();
		logic = new Logic();
		logic.addObserver(gamePanelView);
		Thread t = new Thread(logic);
		t.start();
	}

	/**
	 * 
	 * @return
	 */
	public static MainController getInstance() {
		if (MAIN_CONTROLLER_INSTANCE == null) {
			MAIN_CONTROLLER_INSTANCE = new MainController();
		}
		return MAIN_CONTROLLER_INSTANCE;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MainController.getInstance();
	}

	/**
	 * 
	 * @param actor
	 * @return
	 */
	public IActor getActor(IActor actor) {
		return logic.getActor(actor);
	}

	/**
	 * 
	 */
	private void createWindow() {
		gamePanelView = new GamePanelView(800, 600);
		statusbarModel = new StatusbarModel(player);
		statusbar = new StatusbarView();
		statusbarModel.addObserver(statusbar);
		new MainView(gamePanelView, statusbar);
		statusbarModel.updateStatus();
		MenuView title = new MenuView(50, 50, gamePanelView, "SNAKE", 48.0f);
		gamePanelView.addActor(title);
	}

	/**
	 * 
	 * @param direction
	 */
	public void switchSnakeDirection(Direction direction) {
		snakeHeadModel.rotateSnake(direction);
	}

	/**
	 * 
	 * @param playerName
	 * @throws IOException
	 */
	public void createPlayer(String playerName) throws IOException {
		connectionModel.createPlayer(playerName);
		Player newPlayer = connectionModel.getSinglePlayer(playerName);
		player.setPlayerName(newPlayer.getPlayerName());
		player.setPlayerId(newPlayer.getPlayerId());
		player.setScore(newPlayer.getScore());
		player.setHighscore(newPlayer.getHighscore());
		statusbarModel.updateStatus();
		OptionsController.getInstance().setOption("player",
				player.getPlayerName());
		OptionsController.getInstance().storeOptions();
	}

	/**
	 * 
	 */
	public void startGame() {
		if (!isGameStarted) {
			isGameStarted = true;
			gamePanelView.clearActors();
			logic.clearActors();
			player.setScore(0);
			statusbarModel.updateStatus();
			AppleView appleView = new AppleView(IConstants.APPLE_PAHT, 0, 0,
					gamePanelView);
			SnakeHeadView snakeHeadView = new SnakeHeadView(
					IConstants.SNAKE_HEAD_PAHT, 120, 120, gamePanelView);
			snakeHeadModel = new SnakeHeadModel(gamePanelView, 120, 120,
					snakeHeadView.getImage(), logic);
			SnakeTailView snakeTailView = new SnakeTailView(
					IConstants.SNAKE_TAIL_PATH, 100, 120, gamePanelView);
			SnakeTailModel snakeTailModel = new SnakeTailModel(gamePanelView,
					100, 120, snakeHeadModel, snakeTailView.getImage());
			SnakeTailView snakeTailView1 = new SnakeTailView(
					IConstants.SNAKE_TAIL_PATH, 80, 120, gamePanelView);
			SnakeTailModel snakeTailModel1 = new SnakeTailModel(gamePanelView,
					80, 120, snakeTailModel, snakeTailView1.getImage());
			SnakeTailView snakeTailView2 = new SnakeTailView(
					IConstants.SNAKE_TAIL_PATH, 60, 120, gamePanelView);
			SnakeTailModel snakeTailModel2 = new SnakeTailModel(gamePanelView,
					60, 120, snakeTailModel1, snakeTailView2.getImage());
			snakeHeadModel.setLast(snakeTailModel2);
			AppleModel appleModel = new AppleModel(gamePanelView,
					appleView.getImage());
			OpponentView opponentView1 = new OpponentView(
					IConstants.OPPONENT_PATH, 0, 60, gamePanelView);
			OpponentModel opponentModel1 = new OpponentModel(gamePanelView,
					opponentView1.getImage());
			opponentModel1.addObserver(opponentView1);
			appleModel.addObserver(appleView);
			snakeHeadModel.addObserver(snakeHeadView);
			snakeTailModel.addObserver(snakeTailView);
			snakeTailModel1.addObserver(snakeTailView1);
			snakeTailModel2.addObserver(snakeTailView2);
			logic.addActor(appleModel);
			logic.addActor(snakeHeadModel);
			logic.addActor(snakeTailModel);
			logic.addActor(snakeTailModel1);
			logic.addActor(snakeTailModel2);
			logic.addActor(opponentModel1);
			gamePanelView.addActor(appleView);
			gamePanelView.addActor(snakeHeadView);
			gamePanelView.addActor(snakeTailView);
			gamePanelView.addActor(snakeTailView1);
			gamePanelView.addActor(snakeTailView2);
			gamePanelView.addActor(opponentView1);
			logic.setGameRunning(true);
		}
	}

	/**
	 * 
	 */
	public void pauseGame() {
		logic.setGameRunning(false);
	}

	/**
	 * 
	 */
	public void restartGame() {
		if (isGameStarted) {
			logic.setGameRunning(false);
			logic.clearActors();
			gamePanelView.clearActors();
			isGameStarted = false;
			player.setScore(0);
			startGame();
		}
	}

	/**
	 * 
	 */
	public void gameOver() {
		logic.setGameRunning(false);
		isGameStarted = false;
		MenuView gameOverTitle = new MenuView(50, 40, gamePanelView,
				"Game Over", 48.0f);
		gamePanelView.addActor(gameOverTitle);
		if (player.getScore() > player.getHighscore()) {
			MenuView highScoreTitle = new MenuView(50, 50, gamePanelView,
					"Neue Highscore!", 48.0f);
			gamePanelView.addActor(highScoreTitle);
			player.setHighscore(player.getScore());
			connectionModel.updatePlayerScore(player);
		} else {
			MenuView currentScoreTitle = new MenuView(50, 50, gamePanelView,
					"Erreichte Punkte:", 48.0f);
			gamePanelView.addActor(currentScoreTitle);
		}
		MenuView highScoreTitleScore = new MenuView(50, 60, gamePanelView,
				String.valueOf(player.getScore()), 48.0f);
		gamePanelView.addActor(highScoreTitleScore);
	}

	/**
	 * 
	 */
	public void raiseScore() {
		snakeHeadModel.increaseLength();
		player.setScore(player.getScore() + 2);
		statusbarModel.updateStatus();
		boolean spawnNewEnemy = false;
		int multiplikator = 0;
		switch (Difficuty.fromString(OptionsController.getInstance().getOption(
				"difficulty"))) {
		case SIMPLE:
			multiplikator = 16;
			break;
		case MEDIUM:
			multiplikator = 12;
			break;
		case DIFFICULT:
			multiplikator = 6;
			break;
		}
		if (player.getScore() % multiplikator == 0) {
			spawnNewEnemy = true;
		}
		if (spawnNewEnemy) {
			OpponentView opponentView = new OpponentView(
					IConstants.OPPONENT_PATH, 0, 60, gamePanelView);
			OpponentModel opponentModel = new OpponentModel(gamePanelView,
					opponentView.getImage());
			opponentModel.addObserver(opponentView);
			logic.addActor(opponentModel);
			gamePanelView.addActor(opponentView);
		}
	}

	/**
	 * 
	 */
	public void displayRanking() {
		Vector<Player> topPlayerVector = connectionModel.getTopPlayers();
		int counter = 1;
		logic.clearActors();
		gamePanelView.clearActors();
		for (Player topPlayer : topPlayerVector) {
			MenuView topPlayerView = new MenuView(50, 9.5 * counter,
					gamePanelView, topPlayer.getHighscore() + " - "
							+ topPlayer.getPlayerName(), 48.0f);
			gamePanelView.addActor(topPlayerView);
			counter++;
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getPlayerName() {
		return player.getPlayerName();
	}

	/**
	 * 
	 * @return
	 */
	public Vector<Player> getPlayers() {
		return connectionModel.getPlayers();
	}

	/**
	 * 
	 * @param playerName
	 */
	public void changePlayer(String playerName) {
		Player newPlayer = connectionModel.getSinglePlayer(playerName);
		player.setPlayerName(newPlayer.getPlayerName());
		player.setPlayerId(newPlayer.getPlayerId());
		player.setScore(newPlayer.getScore());
		player.setHighscore(newPlayer.getHighscore());
	}

	/**
	 * 
	 * @throws IOException
	 */
	public void optionsChanged() throws IOException {
		statusbarModel.updateStatus();
		OptionsController.getInstance().setOption("player",
				player.getPlayerName());
		OptionsController.getInstance().storeOptions();
	}
}