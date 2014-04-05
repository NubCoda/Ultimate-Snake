package Controller;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Vector;

import Model.AppleModel;
import Model.Logic;
import Model.SnakeModel;
import Model.Interface.Direction;
import Model.Interface.IActor;
import Model.Interface.IConstants;
import View.AppleView;
import View.GamePanelView;
import View.MainView;
import View.SnakeView;

public class MainController {
	private static MainController mainController;
	private MainView mainView;
	private GamePanelView gamePanelView;
//	private SnakeModel snakeModel;
	private Logic logic;

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

	public void moveSnake(int direction) {
		//snakeModel.moveSnake(direction);
	}

	public void startGame() {
		// TODO: das Level nur beim Start bzw. beim Levelwechsel erstellen
//		Vector<IActor> actors = new Vector<IActor>();
		AppleView appleView = new AppleView(IConstants.APPLE_PAHT, 20,
				20, gamePanelView);
		AppleModel appleModel = null;
		SnakeModel snakeModel = new SnakeModel(120, 120, 3, Direction.RIGHT);
		SnakeView snakeView = new SnakeView(120, 120, gamePanelView, new Vector<Point2D.Double>(snakeModel.getBonesPosition().values()));
		appleModel = new AppleModel(gamePanelView);
		logic.addActor(appleModel);
		logic.addActor(snakeModel);
		appleModel.addObserver(appleView);
		snakeModel.addObserver(snakeView);
		gamePanelView.addActor(appleView);
		gamePanelView.addActors(snakeView.getActors());
		logic.setGameRunning(true);
	}

	public void pauseGame() {
		logic.setGameRunning(false);
	}

	public void restartGame() {

	}
}
