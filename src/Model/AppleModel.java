package Model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;

import View.GamePanelView;

public class AppleModel extends Observable {

	private Rectangle bounding; //ränder
	double applePosition_x;
	double applePosition_y;
	private int maxWindowsize_x;
	private int maxWindowsize_y;
	private int minWindowsize_x;
	private int minWindowsize_y;
	private BufferedImage bufferedImages;
	private GamePanelView gamePanelView;


	public AppleModel(GamePanelView gamePanelView) {

//		bufferedImages = ImageIO.read(getClass().getClassLoader().getResourceAsStream("recources/apple_sprite.png"));
//		bounding = new Rectangle(newPosition_x, newPosition_y, bufferedImages.getWidth(), bufferedImages.getHeight());
		this.gamePanelView = gamePanelView;
		this.maxWindowsize_x = gamePanelView.getWidth()-40;
		this.maxWindowsize_y = gamePanelView.getHeight()-40;
		this.minWindowsize_x =0;
		this.minWindowsize_y =0;
	}



	public void update() {
//		applePosition_x = Math.random()*(double)(maxWindowsize_x-minWindowsize_x)-(double)minWindowsize_x;
//		applePosition_y = Math.random()*(double)(maxWindowsize_y-minWindowsize_y)-(double)minWindowsize_y;
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
