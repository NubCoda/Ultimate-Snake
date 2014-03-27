package Controller;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;

import Model.AppleModel;
import Model.SnakeModel;
import View.AppleView;
import View.GamePanelView;
import View.MainView;
import View.SnakeView;
import ViewInterface.Constants;

public class MainController implements Runnable {
	private static MainController mainController;
	private MainView mainView;
	private GamePanelView gamePanelView;
	// public boolean IsGameRunning = false;
	// public boolean IsWindowCreated = false;
	private SnakeModel snakeModel;

	public boolean IsGameRunning = false;
	public boolean IsWindowCreated = false;

	private MainController() {
		createWindow();
		Thread t = new Thread(this);
		t.run();
		System.out.println("Test");
		System.out.println("Hallo");
	}


	    private static class Holder {
	    	private static MainController instance = new MainController();
	    }
	public static MainController getInstance() {
		return Holder.instance;
//		if (mainController == null) {
//			System.out.println("TEst");
//			synchronized (MainController.class) {
//				System.out.println("TEst");
//				if (mainController == null) {
//					mainController = new MainController();
//					System.out.println("TEst");
//				}
//			}
//		}
//		return mainController;
	}

	private void createWindow() {
		mainView = new MainView();
		gamePanelView = new GamePanelView(mainView.getWidth(),
				mainView.getHeight());
		mainView.add(gamePanelView);
		mainView.setVisible(true);
		// IsWindowCreated = true;
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

	@Override
	public void run() {
		AppleView appleView = new AppleView("./resources/apple_sprite.png", 20,
				20, gamePanelView);
		AppleModel appleModel = null;
		SnakeView snakeView = new SnakeView("./resources/head_sprite.png",
				"./resources/tail_sprite.png", 120, 120, gamePanelView);
		snakeModel = new SnakeModel(120, 120);
		snakeModel.addObserver(snakeView);
		appleModel = new AppleModel(gamePanelView);
		appleModel.addObserver(appleView);

		gamePanelView.addActor(appleView);
		gamePanelView.addActor(snakeView);
		while (true) {
			try {
				appleModel.update();
				gamePanelView.repaint();
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void moveSnake(int direction) {
		snakeModel.moveSnake(direction);
	}
}
