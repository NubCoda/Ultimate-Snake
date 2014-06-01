package Controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import Model.AppleModel;
import Model.Logic;
import Model.OpponentModel;
import Model.SnakeHeadModel;
import Model.SnakeTailModel;
import Model.StatusbarModel;
import Model.Interface.Direction;
import Model.Interface.IActor;
import Model.Interface.IConstants;
import Properties.GameSettings;
import Properties.PlayerHighscore;
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
	private PlayerHighscore playerHighscore;
	private GameSettings gameSettings;
	private boolean isGameStarted = false;
	private StatusbarView statusbar;
	private JLabel statusLabelPlayer;
	private JLabel statusLabelScore;
	private JLabel statusLabelDifficulty;
	private JLabel stauLabelHighscore;
	private SnakeTailModel snakeTailModel;
	private SnakeTailView snakeTailView;
	private StatusbarModel statusbarModel;

	/**
	 * 
	 */
	private MainController() {
		intializeGame();
	}

	private void intializeGame() {
		playerHighscore = OptionsController.getInstance()
				.getAndSetLastPlayerFromFile();
		if (playerHighscore == null) {
			boolean created = false;
			String playerName = null;
			while (playerName == null || playerName.isEmpty()) {
				playerName = JOptionPane
						.showInputDialog("Spielernamen angeben!");
				while (!created) {
					playerName = JOptionPane
							.showInputDialog("Spielernamen angeben!");
					created = OptionsController.getInstance().createPlayer(
							playerName);
				}
			}
			playerHighscore = OptionsController.getInstance().getSinglePlayer(
					playerName);
		}
		gameSettings = OptionsController.getInstance().getGameSettings();
		createWindow();
		logic = new Logic(gameSettings);
		logic.addObserver(gamePanelView);
		Thread t = new Thread(logic);
		t.start();
		MenuView gameTitle = new MenuView(50, 50, gamePanelView, "SNAKE", 48.0f);
		gamePanelView.addActor(gameTitle);
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
		initiliazeStatusbar();
		new MainView(gamePanelView, statusbar);
		gamePanelView.registerKeyboardAction(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						logic.kill();
						OptionsController.getInstance().saveToFile(
								playerHighscore, gameSettings.getDifficulty());
						for (Frame frame : Frame.getFrames()) {
							frame.dispose();
						}
					}
				}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
	}

	private void initiliazeStatusbar() {
		statusbar = new StatusbarView();
		statusbarModel = new StatusbarModel(gameSettings);
		statusLabelPlayer = new JLabel();
		statusbar.addLabelToVector(statusLabelPlayer);
		statusbarModel.addLabelToVector(statusLabelPlayer);
		statusbarModel.addKeyNameToVector("Spieler: ");
		statusLabelScore = new JLabel();
		statusbar.addLabelToVector(statusLabelScore);
		statusbarModel.addLabelToVector(statusLabelScore);
		statusbarModel.addKeyNameToVector("Aktueller Score: ");
		stauLabelHighscore = new JLabel();
		statusbar.addLabelToVector(stauLabelHighscore);
		statusbarModel.addLabelToVector(stauLabelHighscore);
		statusbarModel.addKeyNameToVector("Highscore: ");
		statusLabelDifficulty = new JLabel();
		statusbar.addLabelToVector(statusLabelDifficulty);
		statusbarModel.addLabelToVector(statusLabelDifficulty);
		statusbarModel.addKeyNameToVector("Schwierigkeit: ");
		statusbar.addLabels();
		statusbarModel.setValuesOfPlayer(playerHighscore);
		statusbarModel.addObserver(statusbar);
	}

	/**
	 * 
	 * @param direction
	 */
	public void switchSnakeDirection(Direction direction) {
		snakeHeadModel.rotateSnake(direction);
	}

	public GameSettings getCurrentGameSettings() {
		return gameSettings;
	}

	public PlayerHighscore getCurrentPlayerInfo() {
		return playerHighscore;
	}

	/**
	 * 
	 */
	public void startGame() {
		// TODO: dies koennte das Level 1 sein
		if (!isGameStarted) {
			playerHighscore.setCurrentScore(0);
			logic.clearActors();
			gamePanelView.clearActors();
			isGameStarted = true;
			AppleView appleView = new AppleView(IConstants.APPLE_PAHT, 0, 0,
					gamePanelView);
			SnakeHeadView snakeHeadView = new SnakeHeadView(
					IConstants.SNAKE_HEAD_PAHT, 120, 120, gamePanelView);
			gameSettings = OptionsController.getInstance().getGameSettings();
			snakeHeadModel = new SnakeHeadModel(gamePanelView, 120, 120,
					snakeHeadView.getImage(), logic);
			snakeHeadModel.setSpeedByDifficulty(gameSettings.getDifficulty());
			snakeTailView = new SnakeTailView(IConstants.SNAKE_TAIL_PAHT, 100,
					120, gamePanelView);
			snakeTailModel = new SnakeTailModel(gamePanelView, 100, 120,
					snakeHeadModel, snakeTailView.getImage());
			SnakeTailView snakeTailView1 = new SnakeTailView(
					IConstants.SNAKE_TAIL_PAHT, 80, 120, gamePanelView);
			SnakeTailModel snakeTailModel1 = new SnakeTailModel(gamePanelView,
					80, 120, snakeTailModel, snakeTailView1.getImage());
			SnakeTailView snakeTailView2 = new SnakeTailView(
					IConstants.SNAKE_TAIL_PAHT, 60, 120, gamePanelView);
			SnakeTailModel snakeTailModel2 = new SnakeTailModel(gamePanelView,
					60, 120, snakeTailModel1, snakeTailView2.getImage());
			snakeHeadModel.setLast(snakeTailModel2);
			AppleModel appleModel = new AppleModel(gamePanelView,
					appleView.getImage());
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
			gamePanelView.addActor(appleView);
			gamePanelView.addActor(snakeHeadView);
			gamePanelView.addActor(snakeTailView);
			gamePanelView.addActor(snakeTailView1);
			gamePanelView.addActor(snakeTailView2);
		} else {
			gameSettings = OptionsController.getInstance().getGameSettings();
			snakeHeadModel.setSpeedByDifficulty(gameSettings.getDifficulty());

		}
		statusbarModel.setValuesOfPlayer(playerHighscore);
		logic.setGameRunning(true);
	}

	private void spawnEnemy() {
		boolean spawnNewEnemy = false;
		int multiplikator = 0;
		switch (gameSettings.getDifficulty()) {
		case 1:
			multiplikator = 16;
			break;
		case 2:
			multiplikator = 12;
			break;
		case 3:
			multiplikator = 6;
			break;
		}
		if (playerHighscore.getCurrentScore() % multiplikator == 0) {
			spawnNewEnemy = true;
		}
		if (spawnNewEnemy) {
			OpponentView opponentView = new OpponentView(
					IConstants.OPPONENT_PATH, 0, 60, gamePanelView);
			OpponentModel opponentModel = new OpponentModel(gamePanelView,
					opponentView.getImage(), logic);
			opponentModel.addObserver(opponentView);
			logic.addActor(opponentModel);
			gamePanelView.addActor(opponentView);
		}
	}

	public void raiseScore() {
		playerHighscore.setCurrentScore(playerHighscore.getCurrentScore() + 2);
		statusbarModel.setValuesOfPlayer(playerHighscore);
		spawnEnemy();
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
	public void restartGame(boolean startGame) {
		logic.clearActors();
		gamePanelView.clearActors();
		isGameStarted = false;
		playerHighscore.setCurrentScore(0);
		if (startGame) {
			startGame();
		}
	}

	public void setPlayerHighscore(PlayerHighscore playerHighscore) {
		this.playerHighscore = playerHighscore;
		statusbarModel.setValuesOfPlayer(playerHighscore);
	}

	public void displayRanking() {
		if (!isGameStarted) {
			Vector<PlayerHighscore> topPlayerVector = OptionsController
					.getInstance().getTopPlayers();
			int counter = 1;
			logic.clearActors();
			gamePanelView.clearActors();
			for (PlayerHighscore playerHighscore : topPlayerVector) {
				MenuView topPlayer = new MenuView(50, 9.5 * counter,
						gamePanelView, playerHighscore.getHighscore() + " - "
								+ playerHighscore.getPlayer().getPlayerName(),
						48.0f);
				gamePanelView.addActor(topPlayer);
				counter++;
			}
		} else {
			logic.setGameRunning(false);
			JOptionPane.showMessageDialog(null,
					"Nicht während des Spiels möglich!");
			logic.setGameRunning(true);
		}
	}

	public void gameOver() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(650);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.run();
		isGameStarted = false;
		logic.setGameRunning(false);
		logic.clearActors();
		gamePanelView.clearActors();
		MenuView gameOverTitle = new MenuView(50, 40, gamePanelView,
				"GAME OVER!", 48.0f);
		gamePanelView.addActor(gameOverTitle);
		if (playerHighscore.getCurrentScore() > playerHighscore.getHighscore()) {
			MenuView highScoreTitle = new MenuView(50, 50, gamePanelView,
					"Neuer Highscore!", 48.0f);
			gamePanelView.addActor(highScoreTitle);
			OptionsController.getInstance().updateHighScore(playerHighscore);
		} else {
			MenuView currentScoreTitle = new MenuView(50, 50, gamePanelView,
					"Erreichte Punkte:", 48.0f);
			gamePanelView.addActor(currentScoreTitle);
		}
		MenuView scoreTitle = new MenuView(50, 60, gamePanelView,
				String.valueOf(playerHighscore.getCurrentScore()), 48.0f);
		gamePanelView.addActor(scoreTitle);
	}
}