package Model;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;

import Model.Interface.IActor;
import Model.Interface.IConstants;
import Model.Interface.IElement;
import View.GamePanelView;

public class AppleModel extends Observable implements IActor, IElement{
	private BufferedImage bufferedImages;
	private GamePanelView gamePanelView;
	private boolean appleAlive = true;
	private Rectangle2D.Double bounding;
	public AppleModel(GamePanelView gamePanelView, BufferedImage bufferedImage) {
		this.bounding = new Rectangle2D.Double(0,0,bufferedImage.getWidth(),bufferedImage.getHeight());
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

	public Rectangle2D getBounding(){
		return bounding;
	}

	public void actuate(double delta) {
		if(!appleAlive){
			appleAlive = true;
			moveApple();
		}
		setChanged();
		notifyObservers();
	}

	public void checkCollision(IActor actor) {
		if(bounding.intersects(actor.getBounding()) && actor instanceof SnakeHeadModel){
			appleAlive = false;
			((SnakeHeadModel) actor).increaseLength();
		}
	}

	@Override
	public boolean checkPosition(Point point) {
		// TODO Auto-generated method stub
		return false;
	}
}
