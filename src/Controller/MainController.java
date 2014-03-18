package Controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import View.AppleView;
import View.GamePanelView;
import View.MainView;
import View.SpriteView;

public class MainController {

	private static MainController mainController;

	/**
	 * Makes it impossible to instantite
	 */
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

	
	public static void spawnApples() {
		
	}
	
	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		final MainView mainView = new MainView();
		mainView.setBounds(1024, 600, 1024, 600);
		final GamePanelView gamePanelView = new GamePanelView(
				mainView.getWidth(), mainView.getHeight());
		gamePanelView.setBackground(Color.CYAN);
		final Vector<SpriteView> actors = new Vector<SpriteView>();
		final BufferedImage[] apple = MainController.getInstance().loadImages("./resources/apple_sprite.png", 1);
		mainView.add(gamePanelView);
		mainView.setVisible(true);
		while(true) {
			Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(1000);
						AppleView appleView = new AppleView(apple, Math.random() * 100.234 , Math.random() * 50.234, gamePanelView);
						actors.add(appleView);
						gamePanelView.setActors(actors);
						mainView.repaint();
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			Thread thread = new Thread(runnable);
			thread.start();
		}
	}

}
