package Model;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Observable;

import Controller.MainController;
import Controller.OptionsController;
import Model.Interface.Direction;
import Model.Interface.IActor;
import Model.Interface.IElement;
import Model.Interface.IPlayerBone;
import View.GamePanelView;

/**
 * 
 * 
 */
public class SnakeTailModel extends Observable implements IActor, IPlayerBone {
	public IPlayerBone vorgaenger;
	private Point2D.Double movement;
	private Ellipse2D.Double bounding;
	private int rotation;
	private Direction direction = Direction.NONE;

	/**
	 * 
	 * @param gamePanelView
	 * @param x
	 * @param y
	 * @param vorgaenger
	 * @param bufferedImage
	 */
	public SnakeTailModel(GamePanelView gamePanelView, double x, double y,
			IPlayerBone vorgaenger, BufferedImage bufferedImage) {
		this.bounding = new Ellipse2D.Double(x, y, bufferedImage.getWidth(),
				bufferedImage.getHeight());
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

	/**
	 * 
	 */
	public Rectangle2D getBounding() {
		return this.bounding.getBounds2D();
	}

	/**
	 * 
	 */
	public double getX() {
		return bounding.x;
	}

	/**
	 * 
	 */
	public double getY() {
		return bounding.y;
	}

	/**
	 * 
	 */
	public void actuate(double delta) {
		if (bounding.x != vorgaenger.getMovement().x
				|| bounding.y != vorgaenger.getMovement().y) {
			movement = new Point2D.Double(bounding.x, bounding.y);
			bounding.x = vorgaenger.getMovement().x;
			bounding.y = vorgaenger.getMovement().y;
			this.direction = vorgaenger.getDirection();
			setChanged();
			notifyObservers();
		}
	}

	@Override
	public void checkCollision(IActor actor) {
		if (bounding.intersects(actor.getBounding())
				&& (actor instanceof OpponentModel)) {
			MainController.getInstance().gameOver();
			OptionsController.getInstance().updateHighScore(
					MainController.getInstance().getCurrentPlayerInfo());
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