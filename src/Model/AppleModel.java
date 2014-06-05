package Model;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Random;

import Controller.MainController;
import Model.Interface.IActor;
import Model.Interface.IElement;
import View.GamePanelView;

/**
 * 
 * 
 */
public class AppleModel extends Observable implements IElement {
	private boolean appleAlive = false;
	private Rectangle2D.Double bounding;
	private GamePanelView gamePanelView;

	/**
	 * 
	 * @param gamePanelView
	 * @param bufferedImage
	 */
	public AppleModel(GamePanelView gamePanelView, BufferedImage bufferedImage) {
		this.bounding = new Rectangle2D.Double(0, 0, bufferedImage.getWidth(),
				bufferedImage.getHeight());
		this.gamePanelView = gamePanelView;
	}

	/**
	 * 
	 */
	public void moveApple() {
		Random random = new Random();
		int x = 0;
		int y = 0;
		do {
			x = random.nextInt((int) (gamePanelView.getWidth() - bounding
					.getWidth()));
			y = random.nextInt((int) (gamePanelView.getHeight() - bounding
					.getHeight()));
		} while (!MainController.getInstance().checkPosition(x- (x % bounding.getWidth()), y - (y % bounding.getHeight())));
		bounding.x = (int) (x - (x % bounding.getWidth()));
		bounding.y = (int) (y - (y % bounding.getHeight()));

	}

	@Override
	public Rectangle2D getBounding() {
		return bounding;
	}

	@Override
	public void actuate(double delta) {
		if (!appleAlive) {
			appleAlive = true;
			moveApple();
			setChanged();
			notifyObservers();
		}
	}

	@Override
	public void checkCollision(IActor actor) {
		if (bounding.intersects(actor.getBounding())
				&& actor instanceof SnakeHeadModel) {
			appleAlive = false;
			MainController.getInstance().raiseScore();
		}
	}
}
