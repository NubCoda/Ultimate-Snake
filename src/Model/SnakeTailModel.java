package Model;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.util.Observable;

import Controller.MainController;
import Model.Interface.Direction;
import Model.Interface.IActor;
import Model.Interface.IEnemy;
import Model.Interface.IPlayerBone;

/**
 * 
 * 
 */
public class SnakeTailModel extends Observable implements IPlayerBone {
	public IPlayerBone vorgaenger;
	private Point2D.Double movement;
	private Ellipse2D.Double bounding;
	private int rotation;
	private Direction direction = Direction.NONE;
	private Direction newDirection = Direction.NONE;

	/**
	 * 
	 * @param gamePanelView
	 * @param x
	 * @param y
	 * @param vorgaenger
	 * @param bufferedImage
	 */
	public SnakeTailModel(double x, double y, IPlayerBone vorgaenger,
			int width, int height) {
		this.bounding = new Ellipse2D.Double(x, y, width, height);
		this.vorgaenger = vorgaenger;
		movement = new Point2D.Double(x, y);
	}

	/**
	 * 
	 * @return
	 */
	public int getRotation() {
		return rotation;
	}

	@Override
	public Rectangle2D getBounding() {
		return this.bounding.getBounds2D();
	}

	@Override
	public void actuate(double delta) {
		if ((bounding.x != vorgaenger.getMovement().x || bounding.y != vorgaenger
				.getMovement().y)
				&& (vorgaenger.getBounding().getX() != vorgaenger.getMovement().x || vorgaenger
						.getBounding().getY() != vorgaenger.getMovement().y)) {
			movement.x = bounding.x;
			movement.y = bounding.y;
			bounding.x = vorgaenger.getMovement().x;
			bounding.y = vorgaenger.getMovement().y;
			if (newDirection != direction) {
				direction = newDirection;
			}
			newDirection = vorgaenger.getDirection();
			setChanged();
			notifyObservers();
		}
	}

	@Override
	public void checkCollision(IActor actor) {
		if (bounding.intersects(actor.getBounding()) && actor instanceof IEnemy) {
			MainController.getInstance().gameOver();
		}
	}

	@Override
	public Double getMovement() {
		return movement;
	}

	@Override
	public Direction getDirection() {
		return direction;
	}
}