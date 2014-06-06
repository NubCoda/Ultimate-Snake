package Controller;

import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;

import Model.AppleModel;
import Model.BarrierModel;
import Model.BulletModel;
import Model.DatabaseConnectionModel;
import Model.GameSoundModel;
import Model.Logic;
import Model.OpponentModel;
import Model.SnakeHeadModel;
import Model.SnakeTailModel;
import Model.StatusbarModel;
import Model.Interface.Difficulty;
import Model.Interface.Direction;
import Model.Interface.IActor;
import Model.Interface.IConstants;
import Model.Interface.IPlayerBone;
import Properties.Player;
import View.AppleView;
import View.BarrierView;
import View.BulletView;
import View.GamePanelView;
import View.KeyListenerView;
import View.MainView;
import View.MenuView;
import View.OpponentView;
import View.SnakeHeadView;
import View.SnakeTailView;
import View.SpriteView;
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
	private GameSoundModel gameSoundBackground;
	private KeyListenerView keyListenerView;

	/**
	 * 
	 */
	private MainController() {
		initializeGame();
	}

	private void initializeGame() {
		connectionModel = new DatabaseConnectionModel();
		player = connectionModel.getSinglePlayer(OptionsController
				.getInstance().getOption("player"));
		if (player == null) {
			String playerName = JOptionPane
					.showInputDialog("Spielernamen angeben!");
			connectionModel.createPlayer(playerName);
			player = connectionModel.getSinglePlayer(playerName);
			OptionsController.getInstance().setOption("player",
					player.getPlayerName());
			try {
				OptionsController.getInstance().storeOptions();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		gameSoundBackground = new GameSoundModel(IConstants.GAME_SOUND_PATH);
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
		keyListenerView = new KeyListenerView();
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
			player.setBulletCount(0);
			player.setScore(0);

			int maxX = gamePanelView.getWidth();
			int maxY = gamePanelView.getHeight();

			// Apple
			AppleView appleView = new AppleView(IConstants.APPLE_PATH, 0, 0,
					gamePanelView);
			AppleModel appleModel = new AppleModel(maxX, maxY, appleView
					.getImage().getWidth(), appleView.getImage().getHeight());
			appleModel.addObserver(appleView);
			logic.addActor(appleModel);
			gamePanelView.addActor(appleView);

			// SnakeHead
			SnakeHeadView snakeHeadView = new SnakeHeadView(
					IConstants.SNAKE_HEAD_PATH, 120, 120, gamePanelView);
			snakeHeadModel = new SnakeHeadModel(maxX, maxY, 120, 120,
					snakeHeadView.getImage().getWidth(), snakeHeadView
							.getImage().getHeight());
			snakeHeadModel.addObserver(snakeHeadView);
			logic.addActor(snakeHeadModel);
			gamePanelView.addActor(snakeHeadView);

			// Snake-Tail
			IPlayerBone vorgaenger = snakeHeadModel;
			for (int count = 1; count <= 3; count++) {
				SnakeTailView snakeTailView = new SnakeTailView(
						IConstants.SNAKE_TAIL_PATH, 120 - vorgaenger
								.getBounding().getWidth(), 120, gamePanelView);
				SnakeTailModel snakeTailModel = new SnakeTailModel(100, 120,
						vorgaenger, snakeTailView.getImage().getWidth(),
						snakeTailView.getImage().getHeight());
				snakeTailModel.addObserver(snakeTailView);
				logic.addActor(snakeTailModel);
				gamePanelView.addActor(snakeTailView);
				vorgaenger = snakeTailModel;
			}
			snakeHeadModel.setLast(vorgaenger);
		}
		gamePanelView.addKeyListener(keyListenerView);
		statusbarModel.updateStatus();
		logic.setGameRunning(true);
		gameSoundBackground.playSound();
	}

	/**
	 * 
	 */
	public void pauseGame() {
		gamePanelView.removeKeyListener(keyListenerView);
		logic.setGameRunning(false);
		gameSoundBackground.stopSound();
	}

	/**
	 * 
	 */
	public void restartGame() {
		gameSoundBackground.stopSound();
		logic.setGameRunning(false);
		logic.clearActors();
		gamePanelView.clearActors();
		isGameStarted = false;
		startGame();
	}

	/**
	 * 
	 */
	public void gameOver() {
		try {
			Thread.sleep(650);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gamePanelView.removeKeyListener(keyListenerView);
		gameSoundBackground.stopSound();
		logic.setGameRunning(false);
		isGameStarted = false;
		logic.clearActors();
		gamePanelView.clearActors();
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
		player.setBulletCount(player.getBulletCount() + 2);
		snakeHeadModel.increaseLength();
		player.setScore(player.getScore() + 2);
		statusbarModel.updateStatus();
		boolean spawnNewEnemy = false;
		int multiplikator = 0;
		long time = System.currentTimeMillis();
		switch (Difficulty.fromString(OptionsController.getInstance().getOption(
				"difficulty"))) {
		case SIMPLE:
			multiplikator = 8;
			break;
		case MEDIUM:
			multiplikator = 4;
			break;
		case DIFFICULT:
			multiplikator = 2;
			break;
		}
		if (time % multiplikator == 0) {
			spawnNewEnemy = true;
		}
		if (spawnNewEnemy) {
			OpponentView opponentView = new OpponentView(
					IConstants.OPPONENT_PATH, 0, 60, gamePanelView);
			OpponentModel opponentModel = new OpponentModel(
					gamePanelView.getWidth(), gamePanelView.getHeight(),
					opponentView.getImage().getWidth(), opponentView.getImage()
							.getHeight());
			opponentModel.addObserver(opponentView);
			logic.addActor(opponentModel);
			gamePanelView.addActor(opponentView);
			BarrierView barrierView = new BarrierView(IConstants.BARRIER_PATH,
					0, 60, gamePanelView);
			BarrierModel barrierModel = new BarrierModel(
					gamePanelView.getWidth(), gamePanelView.getHeight(),
					barrierView.getImage().getWidth(), barrierView.getImage()
							.getHeight());
			barrierModel.addObserver(barrierView);
			logic.addActor(barrierModel);
			gamePanelView.addActor(barrierView);
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
		player.setBulletCount(newPlayer.getScore());
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

	/**
	 * 
	 */
	public void shoot() {
		if (player.getBulletCount() > 0) {
			player.setBulletCount(player.getBulletCount() - 1);
			statusbarModel.updateStatus();
			BulletView bulletView = new BulletView(IConstants.BULLET_PATH,
					snakeHeadModel.getBounding().getCenterX(), snakeHeadModel
							.getBounding().getCenterY(), gamePanelView);
			BulletModel bulletModel = new BulletModel(snakeHeadModel
					.getBounding().getCenterX(), snakeHeadModel.getBounding()
					.getCenterY(), bulletView.getImage().getWidth(), bulletView
					.getImage().getHeight(), snakeHeadModel.getDirection());
			bulletModel.addObserver(bulletView);
			logic.addActor(bulletModel);
			gamePanelView.addActor(bulletView);
		}
	}

	public void removeSpriteView(SpriteView spriteView) {
		gamePanelView.removeActor(spriteView);
	}

	public void removeActor(IActor actor) {
		logic.removeActor(actor);
	}

	public IActor getActorAt(double x, double y, double width, double height) {
		return logic.getAtPosition(x, y, width, height);
	}

	public IPlayerBone createSnakeTail(int x, int y, IPlayerBone vorgaenger) {
		SnakeTailView newTailView = new SnakeTailView(
				IConstants.SNAKE_TAIL_PATH, x, y, gamePanelView);
		SnakeTailModel newTailModel;
		newTailModel = new SnakeTailModel(x, y,
				(IPlayerBone) getActor(vorgaenger), newTailView.getImage()
						.getWidth(), newTailView.getImage().getHeight());
		newTailModel.addObserver(newTailView);
		gamePanelView.addActor(newTailView);
		logic.addActor(newTailModel);
		return newTailModel;
	}
}