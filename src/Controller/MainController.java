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

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		MainView mainView = new MainView();
		GamePanelView gamePanelView = new GamePanelView(
				mainView.getWidth(), mainView.getHeight());
		gamePanelView.setBackground(Color.CYAN);
		Vector<SpriteView> actors = new Vector<SpriteView>();
		BufferedImage[] apple = MainController.getInstance().loadImages("./resources/apple_sprite.png", 1);
		AppleView appleView = new AppleView(apple, 40, 150, gamePanelView);
		actors.add(appleView);
		appleView = new AppleView(apple, 40, 40, gamePanelView);
		actors.add(appleView);
		gamePanelView.setActors(actors);
		mainView.add(gamePanelView);
		mainView.setResizable(true);
		mainView.setVisible(true);
	}

}
