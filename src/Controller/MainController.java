package Controller;

import java.io.IOException;

import Model.AppleModel;
import View.AppleView;
import View.GamePanelView;
import View.MainView;

public class MainController implements Runnable {
	private static MainController mainController;
	private MainView mainView;
	private GamePanelView gamePanelView;

	public boolean IsGameRunning = false;
	public boolean IsWindowCreated = false;

	private MainController() {
		createWindow();
		Thread t = new Thread(this);
		t.run();
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

	@Override
	public void run() {
		AppleView appleView = new AppleView("./resources/apple_sprite.png", 20, 20, gamePanelView);
		AppleModel appleModel = null;

		try {
			appleModel = new AppleModel(gamePanelView);
			appleModel.addObserver(appleView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		gamePanelView.addActor(appleView);
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

}
