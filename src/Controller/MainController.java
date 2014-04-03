package Controller;

import java.util.Vector;

import Model.AppleModel;
import Model.Logic;
import Model.SnakeModel;
import Model.Interface.IActor;
import View.AppleView;
import View.GamePanelView;
import View.MainView;
import View.SnakeView;

public class MainController {
	private static MainController mainController;
	private MainView mainView;
	private GamePanelView gamePanelView;
	private SnakeModel snakeModel;
	public boolean IsGameRunning = false;
	public boolean IsWindowCreated = false;

	private MainController() {
		createWindow();
		Vector<IActor> actors = new Vector<IActor>();
		AppleView appleView = new AppleView("./resources/apple_sprite.png", 20,
				20, gamePanelView);
		AppleModel appleModel = null;
//		SnakeView snakeView = new SnakeView("./resources/head_sprite.png",
//				"./resources/tail_sprite.png", 120, 120, gamePanelView);
		snakeModel = new SnakeModel(120, 120);
		appleModel = new AppleModel(gamePanelView);
		actors.add(appleModel);
		actors.add(snakeModel);
		appleModel.addObserver(appleView);
//		snakeModel.addObserver(snakeView);
		gamePanelView.addActor(appleView);
//		gamePanelView.addActor(snakeView);

		Logic logic = new Logic(actors);
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
		IsWindowCreated = true;
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
		snakeModel.moveSnake(direction);
	}
}
