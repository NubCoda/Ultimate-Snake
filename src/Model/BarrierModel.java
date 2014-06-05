package Model;

import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Random;

import Controller.MainController;
import Model.Interface.IActor;
import Model.Interface.IEnemy;
import Model.Interface.ISphere;

public class BarrierModel extends Observable implements IActor, IEnemy {

	private Rectangle2D.Double bounding;
	private boolean barrierAlive = false;
	private boolean isAlive = true;
	private int maxX;
	private int maxY;

	public BarrierModel(int maxX, int maxY, int width, int height) {
		this.bounding = new Rectangle2D.Double(0, 0, width, height);
		this.maxX = maxX;
		this.maxY = maxY;
	}

	public void moveBarrier() {
		Random random = new Random();
		int x = 0;
		int y = 0;
		do {
			x = random.nextInt((int) (maxX - bounding.getWidth()));
			y = random.nextInt((int) (maxY - bounding.getHeight()));
		} while (!MainController.getInstance().checkPosition(
				x - (x % bounding.getWidth()), y - (y % bounding.getHeight())));
		bounding.x = (int) (x - (x % bounding.getWidth()));
		bounding.y = (int) (y - (y % bounding.getHeight()));
	}

	@Override
	public void actuate(double delta) {
		if (!barrierAlive) {
			barrierAlive = true;
			moveBarrier();
			setChanged();
			notifyObservers();
		}
	}

	/**
	 * 
	 */
	@Override
	public void checkCollision(IActor actor) {
		if (bounding.intersects(actor.getBounding())
				&& actor instanceof SnakeHeadModel) {
			MainController.getInstance().gameOver();
		} else if (bounding.intersects(actor.getBounding())
				&& actor instanceof ISphere) {
			((ISphere) actor).setGone(true);
			isAlive = false;
			setChanged();
			notifyObservers();
			MainController.getInstance().removeActor(this);
		}
	}

	@Override
	public Rectangle2D getBounding() {
		return bounding;
	}

	@Override
	public boolean isAlive() {
		return isAlive;
	}

}
