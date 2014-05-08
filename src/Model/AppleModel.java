package Model;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.Interface.Actor;
import Model.Interface.IConstants;
import Model.Interface.IElement;
import View.GamePanelView;

public class AppleModel extends Actor implements IElement{
	private BufferedImage bufferedImages;
	private GamePanelView gamePanelView;
	private long respawnSpeed = 10;
	private double spawnTime = 0;
	private boolean appleAlive = true;

	public AppleModel(GamePanelView gamePanelView, BufferedImage bufferedImage) {
		super(0, 0, bufferedImage);
		this.gamePanelView = gamePanelView;
		try {
			this.bufferedImages = ImageIO.read(new File(IConstants.APPLE_PAHT));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void moveApple() {
		bounding.x = Math.random()
				* ((double) (gamePanelView.getWidth()) - ((double) bufferedImages
						.getWidth()));
		bounding.y = Math.random()
				* ((double) (gamePanelView.getHeight()) - ((double) bufferedImages
						.getHeight()));
	}

	public Rectangle2D.Double getBounding(){
		return bounding;
	}

	public void actuate(double delta) {
		if(!appleAlive){
			appleAlive = true;
			moveApple();
			spawnTime = 0;
		} else {
			spawnTime+=delta;
			if(spawnTime>respawnSpeed){
				spawnTime = 0;
				moveApple();
			}
		}
		setChanged();
		notifyObservers();
	}

	public void checkCollision(Actor actor) {
		if(bounding.intersects(actor.getBounding())){
			appleAlive = false;
		}
	}
}
