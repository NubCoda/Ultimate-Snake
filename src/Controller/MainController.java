package Controller;

import java.util.Vector;

import javax.swing.JOptionPane;

import View.AppleView;
import View.GamePanelView;
import View.MainView;
import View.SpriteView;

public class MainController {

	private static MainController mainController;
	private MainView mainView;
	private GamePanelView gamePanelView;
	private Vector<SpriteView> actors;
	private AppleView appleView;
	private Thread appleThread;
	private Runnable runnable;

	public boolean IsGameRunning = false;
	public boolean IsWindowCreated = false;

	private MainController() {
	}

	public static MainController getInstance() {
		if (mainController == null) {
			mainController = new MainController();
		}
		return mainController;
	}

	private void createWindow() {
		mainView = new MainView();
		mainView.setBounds(1024, 600, 1024, 600);
		gamePanelView = new GamePanelView(mainView.getWidth(),
				mainView.getHeight());
		mainView.add(gamePanelView);
		mainView.setVisible(true);
		IsWindowCreated = true;
		gamePanelView.setFocusable(true);
	}

	// FIXME: SpawnApples im Model fuer Apples bzw. Items
	private void spawnApples() {
		actors = new Vector<SpriteView>();
		appleView = new AppleView("./resources/apple_sprite.png", Math.random() * 100.234,
				Math.random() * 50.234, gamePanelView);
		actors.add(appleView);
		gamePanelView.setActors(actors);
		runnable = new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						appleView.x = Math.random() * 100.234;
						appleView.y = Math.random() * 100.234;
						mainView.repaint();
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			}
		};
	}

	/**
	 * Main Funktion Gezeichnet wird nur, wenn der Benutzer das "Spiel"
	 * gestartet hat
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		getInstance().createWindow();
		while (getInstance().IsWindowCreated) {
			if (getInstance().IsGameRunning) {
				getInstance().spawnApples();
				getInstance().appleThread = new Thread(getInstance().runnable);
				getInstance().appleThread.run();
			} else {
				if (!getInstance().IsGameRunning
						&& getInstance().appleThread != null) {
					System.out.println("YO");
				}
			}
		}
	}

}
