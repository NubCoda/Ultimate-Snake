package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;

import ModelInterface.IActor;
import View.GamePanelView;

public class AppleModel extends Observable implements IActor {

	double applePosition_x;
	double applePosition_y;
	private BufferedImage bufferedImages;
	private GamePanelView gamePanelView;

	public AppleModel(GamePanelView gamePanelView) {
		this.gamePanelView = gamePanelView;
		try {

			this.bufferedImages = ImageIO.read(new File(
					"./resources/apple_sprite.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void moveApple() {

		applePosition_x = Math.random()
				* ((double) (gamePanelView.getWidth()) - ((double) bufferedImages
						.getWidth()));
		applePosition_y = Math.random()
				* ((double) (gamePanelView.getHeight()) - ((double) bufferedImages
						.getHeight()));
		setChanged();
		notifyObservers();
	}

	public double getApplePosition_x() {
		return applePosition_x;
	}

	public double getApplePosition_y() {
		return applePosition_y;
	}

	@Override
	public void actuate() {
//		moveApple();
	}

}
