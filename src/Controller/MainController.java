package Controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
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
import View.StatusLabelView;
import View.Statusbar;

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
	private Statusbar statusbar;
	private StatusLabelView statusLabelViewPlayer;
	private StatusLabelView statusLabelViewScore;
	private StatusLabelView statusLabelViewDifficulty;
	private StatusLabelView stauLabelViewHighscore;
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
				.setLastPlayerFromFile();
		gameSettings = OptionsController.getInstance().getGameSettings();
		createWindow();
		logic = new Logic(gameSettings);
		logic.addObserver(gamePanelView);
		Thread t = new Thread(logic);
		t.start();
		MenuView title = new MenuView(50, 50, gamePanelView, "SNAKE", 48.0f);
		gamePanelView.addActor(title);
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
		statusbar = new Statusbar();
		statusbarModel = new StatusbarModel(playerHighscore, gameSettings);
		statusLabelViewPlayer = new StatusLabelView();
		statusbar.addLabelToVector(statusLabelViewPlayer);
		statusbarModel.addLabelToVector(statusLabelViewPlayer);
		statusbarModel.addKeyNameToVector("Spieler: ");
		statusLabelViewScore = new StatusLabelView();
		statusbar.addLabelToVector(statusLabelViewScore);
		statusbarModel.addLabelToVector(statusLabelViewScore);
		statusbarModel.addKeyNameToVector("Aktueller Score: ");
		stauLabelViewHighscore = new StatusLabelView();
		statusbar.addLabelToVector(stauLabelViewHighscore);
		statusbarModel.addLabelToVector(stauLabelViewHighscore);
		statusbarModel.addKeyNameToVector("Highscore: ");
		statusLabelViewDifficulty = new StatusLabelView();
		statusbar.addLabelToVector(statusLabelViewDifficulty);
		statusbarModel.addLabelToVector(statusLabelViewDifficulty);
		statusbarModel.addKeyNameToVector("Schwierigkeit: ");
		statusbar.addLabels();
		statusbarModel.repaintElements();
		statusbarModel.addObserver(statusbar);
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
			gamePanelView.clearActors();
			logic.clearActors();
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
		statusbarModel.repaintElements();
		logic.setGameRunning(true);
	}

	public void raiseScore() {
		playerHighscore.setCurrentScore(playerHighscore.getCurrentScore() + 2);
		statusbarModel.notifiyStatusbar();
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
			OpponentView opponentView1 = new OpponentView(
					IConstants.OPPONENT_PATH, 0, 60, gamePanelView);
			OpponentModel opponentModel1 = new OpponentModel(gamePanelView,
					opponentView1.getImage(), logic);
			opponentModel1.addObserver(opponentView1);
			logic.addActor(opponentModel1);
			gamePanelView.addActor(opponentView1);
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
	public void restartGame(boolean startGame) {
		logic.clearActors();
		gamePanelView.clearActors();
		isGameStarted = false;
		if (startGame) {
			startGame();
		}
	}
}