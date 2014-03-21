package Controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import View.AppleView;
import View.GamePanelView;
import View.MainView;
import View.SnakeView;
import View.SpriteView;

public class MainController {

	private static MainController mainController;
	private MainView mainView;
	private GamePanelView gamePanelView;
	private Vector<SpriteView> actors;
	private BufferedImage[] bufferedImages;
	private AppleView appleView;
	private SnakeView snakeView;
	private Thread appleThread;
	private Runnable runnable;

	public boolean IsGameRunning = false;
	public boolean IsWindowCreated = false;

	private MainController() {
	}

	public BufferedImage[] loadImages(String path, int image) {
		BufferedImage[] bufferedImages = new BufferedImage[image];
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File(path));
			bufferedImages[0] = bufferedImage;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return bufferedImages;
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
	}

	private void spawnApples() {
		actors = new Vector<SpriteView>();
		bufferedImages = MainController.getInstance().loadImages(
				"./resources/apple_sprite.png", 1);
		appleView = new AppleView(bufferedImages, Math.random() * 100.234,
				Math.random() * 50.234, gamePanelView);
		actors.add(appleView);
		bufferedImages = MainController.getInstance().loadImages("./resources/head_sprite.png", 1);
		snakeView = new SnakeView(bufferedImages, mainView.getWidth() / 2, mainView.getHeight() / 2, gamePanelView);
		actors.add(snakeView);
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
