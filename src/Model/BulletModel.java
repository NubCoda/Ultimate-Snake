package Model;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Observable;

import javax.imageio.ImageIO;

import Model.Interface.IActor;
import Model.Interface.IConstants;
import Model.Interface.IElement;

public class BulletModel extends Observable implements IActor, IElement {
	private BufferedImage bufferedImages;
	private boolean opponentAlive = true;
	private Rectangle2D.Double bounding;

	public BulletModel(double midOfSnakeHeadGraphicX,
			double midOfSnakeHeadGraphicY, double x, double y,
			List<BulletModel> bullets) {
		try {
			this.bufferedImages = ImageIO
					.read(new File(IConstants.BULLET_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.bounding = new Rectangle2D.Double(x, y, bufferedImages.getWidth(),
				bufferedImages.getHeight());
	}

	public Rectangle2D getBounding() {
		return bounding;
	}

	public void actuate(double delta) {

		if (opponentAlive) {
			// checkMovement();
			// moveOpponent();
			setChanged();
			notifyObservers();
		}
	}

	public void checkCollision(IActor actor) {
		if (bounding.intersects(actor.getBounding())
				&& actor instanceof SnakeHeadModel) {
			setOpponentAlive(false);
		}

	}

	public void setOpponentAlive(boolean alive) {
		this.opponentAlive = alive;
	}

	public BufferedImage getBufferedImage() {
		return this.bufferedImages;
	}

}
