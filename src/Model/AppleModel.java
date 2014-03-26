package Model;

import java.awt.image.BufferedImage;
import java.util.Observable;

import View.GamePanelView;

public class AppleModel extends Observable {

	double applePosition_x;
	double applePosition_y;
	private BufferedImage bufferedImages;
	private GamePanelView gamePanelView;


	public AppleModel(GamePanelView gamePanelView) {
		this.gamePanelView = gamePanelView;
	}



	public void moveApple() {
		applePosition_x = Math.random()*(double)(gamePanelView.getWidth()-0)-(double)0;
		applePosition_y = Math.random()*(double)(gamePanelView.getHeight()-0)-(double)0;
		setChanged();
		notifyObservers();
	}
	public double getApplePosition_x() {
		return applePosition_x;
	}
	public void setApplePosition_x(double x) {
		this.applePosition_x=x;
	}
	public double getApplePosition_y() {
		return applePosition_y;
	}
	public void setApplePosition_y(double y) {
		this.applePosition_y=y;
	}

	public BufferedImage getBufferedImages() {
		return bufferedImages;
	}



	public void setBufferedImages(BufferedImage bufferedImages) {
		this.bufferedImages = bufferedImages;
	}

}
