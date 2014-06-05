package Model;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Observable;

import Controller.MainController;
import Model.Interface.Direction;
import Model.Interface.IActor;
import Model.Interface.ISphere;
import View.GamePanelView;

public class BulletModel extends Observable implements ISphere {
	private BufferedImage bufferedImages;
	private GamePanelView gamePanelView;
	private Rectangle2D.Double bounding;
	private double speed = 0.5;
	private double timeAlive = 0;
	private final double TIMETOLIVEINSECONDS = 5000;
	private boolean bulletIsGone = false;
	// private List<OpponentModel> opponents1;
	private Direction direction;

	public BulletModel(double midOfSnakeHeadGraphicX,
			double midOfSnakeHeadGraphicY, BufferedImage bufferedImage,
			GamePanelView gamePanelView, Direction direction) {
		this.gamePanelView = gamePanelView;
		this.bufferedImages = bufferedImage;
		this.direction = direction;
		this.bounding = new Rectangle2D.Double(midOfSnakeHeadGraphicX
				- (bufferedImage.getWidth() / 2), midOfSnakeHeadGraphicY
				- (bufferedImage.getHeight() / 2), bufferedImage.getWidth(),
				bufferedImage.getHeight());

	}

	public Rectangle2D getBounding() {
		return bounding;
	}

	public void actuate(double delta) {
		if (!bulletIsGone) {

			timeAlive += delta;
			if (timeAlive > TIMETOLIVEINSECONDS) {
				this.bulletIsGone = true;
			}
			if (this.direction == Direction.DOWN) {
				bounding.y += speed * delta;
			} else if (this.direction == Direction.RIGHT) {
				bounding.x += speed * delta;
			} else if (this.direction == Direction.LEFT) {
				bounding.x -= speed * delta;
			} else if (this.direction == Direction.UP) {
				bounding.y -= speed * delta;
			}
		} else {
			MainController.getInstance().removeActor(this);
		}
		setChanged();
		notifyObservers();
	}

	public void checkCollision(IActor actor) {
		// System.out.println(actor);
		// if (bounding.intersects(actor.getBounding()) && actor instanceof
		// IBarrier) {
		// System.out.println("bullet goes");
		// this.bulletIsGone = true;
		// setChanged();
		// notifyObservers();
		// MainController.getInstance().removeActor(this);
		// }
	}

	public BufferedImage getBufferedImage() {
		return this.bufferedImages;
	}

	@Override
	public Direction getDirection() {
		return this.direction;
	}

	@Override
	public boolean isGone() {
		return bulletIsGone;
	}

	@Override
	public void setGone(boolean b) {
		this.bulletIsGone = b;
	}

}
