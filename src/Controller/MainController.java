package Controller;

import Model.AppleModel;
import Model.Logic;
import Model.SnakeHeadModel;
import Model.SnakeTailModel;
import Model.Interface.Direction;
import Model.Interface.IConstants;
import View.AppleView;
import View.GamePanelView;
import View.MainView;
import View.SnakeHeadView;
import View.SnakeTailView;

public class MainController {
	private static MainController mainController;
	private MainView mainView;
	private GamePanelView gamePanelView;
	private SnakeHeadModel snakeHeadModel;
	private Logic logic;
	private boolean hasGameStarted = false;

	private MainController() {
		createWindow();
		logic = new Logic();
		logic.addObserver(gamePanelView);
		Thread t = new Thread(logic);
		t.start();
	}

	public static MainController getInstance() {
		if (mainController == null) {
			mainController = new MainController();
		}
		return mainController;
	}

	private void createWindow() {
		mainView = new MainView();
		gamePanelView = new GamePanelView(mainView.getWidth(),
				mainView.getHeight());
		mainView.add(gamePanelView);
		mainView.setResizable(false);
		mainView.pack();
		mainView.setVisible(true);
		gamePanelView.setFocusable(true);
	}

	/**
	 * Main Funktion Gezeichnet wird nur, wenn der Benutzer das "Spiel"
	 * gestartet hat
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MainController.getInstance();
	}

	public void switchSnakeDirection(Direction direction) {
		snakeHeadModel.switchDirection(direction);
	}

	public void startGame() {
		// TODO: das Level nur beim Start bzw. beim Levelwechsel erstellen
		if (!hasGameStarted) {
			AppleView appleView = new AppleView(IConstants.APPLE_PAHT, 20, 20,
					gamePanelView);
			SnakeHeadView snakeHeadView = new SnakeHeadView(IConstants.SNAKE_HEAD_PAHT, 120, 120, gamePanelView, Direction.RIGHT);
			snakeHeadModel = new SnakeHeadModel(120, 120, Direction.RIGHT, snakeHeadView.getImage());
			SnakeTailView snakeTailView = new SnakeTailView(IConstants.SNAKE_TAIL_PAHT, 100, 120, gamePanelView);
			SnakeTailModel snakeTailModel = new SnakeTailModel(gamePanelView, 100, 120, snakeHeadModel, snakeTailView.getImage());
			SnakeTailView snakeTailView1 = new SnakeTailView(IConstants.SNAKE_TAIL_PAHT, 80, 120, gamePanelView);
			SnakeTailModel snakeTailModel1 = new SnakeTailModel(gamePanelView, 80, 120, snakeTailModel, snakeTailView1.getImage());
			SnakeTailView snakeTailView2 = new SnakeTailView(IConstants.SNAKE_TAIL_PAHT, 60, 120, gamePanelView);
			SnakeTailModel snakeTailModel2 = new SnakeTailModel(gamePanelView, 60, 120, snakeTailModel1, snakeTailView2.getImage());
			AppleModel appleModel = new AppleModel(gamePanelView, appleView.getImage());
			logic.addActor(appleModel);
			logic.addActor(snakeHeadModel);
			logic.addActor(snakeTailModel);
			logic.addActor(snakeTailModel1);
			logic.addActor(snakeTailModel2);
			appleModel.addObserver(appleView);
			snakeHeadModel.addObserver(snakeHeadView);
			snakeTailModel.addObserver(snakeTailView);
			snakeTailModel1.addObserver(snakeTailView1);
			snakeTailModel2.addObserver(snakeTailView2);
			gamePanelView.addActor(appleView);
			gamePanelView.addActor(snakeHeadView);
			gamePanelView.addActor(snakeTailView);
			gamePanelView.addActor(snakeTailView1);
			gamePanelView.addActor(snakeTailView2);
			hasGameStarted = true;
		}
		logic.setGameRunning(true);

	}

	public void pauseGame() {
		logic.setGameRunning(false);
	}

	public void restartGame() {

	}
}
