package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;

import View.GamePanelView;

public class AppleModel extends Observable {

	double applePosition_x;
	double applePosition_y;
	private BufferedImage bufferedImages;
	private GamePanelView gamePanelView;

	public AppleModel(GamePanelView gamePanelView) {
		this.gamePanelView = gamePanelView;
		try {
			File image = new File("./resources/apple_sprite.png"); 
			this.bufferedImages =  ImageIO.read(image);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void moveApple() {

		applePosition_x = Math.random() * ((double) (gamePanelView.getWidth())
				- ((double) bufferedImages.getWidth()));
		System.out.println(applePosition_x);
		applePosition_y = Math.random() * ((double) (gamePanelView.getHeight())
				- ((double) bufferedImages.getHeight()));
		setChanged();
		notifyObservers();
	}

	public double getApplePosition_x() {
		return applePosition_x;
	}

	public double getApplePosition_y() {
		return applePosition_y;
	}

	
}
