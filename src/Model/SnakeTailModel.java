package Model;

import java.awt.Rectangle;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import Model.Interface.Actor;
import Model.Interface.Direction;
import Model.Interface.IConstants;
import Model.Interface.IPlayerBone;
import View.GamePanelView;

public class SnakeTailModel extends Actor implements IPlayerBone{
	private IPlayerBone vorgaenger;
	private int lastMove;
	private Point2D.Double oldPos;
	private int speed = IConstants.SNAKE_SPEED;
	
	public SnakeTailModel(GamePanelView gamePanelView, double x, double y, IPlayerBone vorgaenger, BufferedImage bufferedImage){
		super(x, y, bufferedImage);
		this.vorgaenger = vorgaenger;
	}

	public Direction getDirection() {
		return Direction.NONE;
	}

	public Rectangle2D.Double getBounding() {
		return this.bounding;
	}

	public double getX() {
		return bounding.x;
	}

	public double getY() {
		return bounding.y;
	}
	
	public void actuate(double delta) {
//		lastMove += delta;
//		if (lastMove > speed) {
//			lastMove = 0;
			oldPos = new Point2D.Double(bounding.getMinX(), bounding.getMinY());
			bounding.x = vorgaenger.getOldPos().x;
			bounding.y = vorgaenger.getOldPos().y;
			setChanged();
			notifyObservers();
//		}
	}

	@Override
	public void checkCollision(Actor actor) {
		if(bounding.intersects(actor.getBounding())){
			System.out.println("Collision Tail");
		}
	}

	@Override
	public Double getOldPos() {
		return oldPos;
	}
}
