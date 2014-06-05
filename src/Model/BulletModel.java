package Model;

import java.awt.geom.Rectangle2D;
import java.util.Observable;

import Controller.MainController;
import Model.Interface.Direction;
import Model.Interface.IActor;
import Model.Interface.ISphere;

public class BulletModel extends Observable implements ISphere {
	private Rectangle2D.Double bounding;
	private double speed = 0.5;
	private double timeAlive = 0;
	private final double TIMETOLIVEINSECONDS = 5000;
	private boolean bulletIsGone = false;
	private Direction direction;

	public BulletModel(double midOfSnakeHeadGraphicX,
			double midOfSnakeHeadGraphicY, int width, int height,
			Direction direction) {
		this.direction = direction;
		this.bounding = new Rectangle2D.Double(midOfSnakeHeadGraphicX
				- (width / 2), midOfSnakeHeadGraphicY - (height / 2), width,
				height);

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
