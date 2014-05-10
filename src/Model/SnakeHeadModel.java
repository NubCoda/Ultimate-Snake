package Model;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.util.Observable;

import Controller.MainController;
import Model.Interface.IActor;
import Model.Interface.Direction;
import Model.Interface.IConstants;
import Model.Interface.IPlayerBone;

public class SnakeHeadModel extends Observable implements IActor, IPlayerBone {
	private Point2D.Double movement;

	private int speed = IConstants.SNAKE_SPEED;
	private double lastMove = 0;
	private Ellipse2D.Double bounding;
	private Direction newDirection = Direction.NONE;
	private Direction direction = Direction.RIGHT;

	private IPlayerBone last;

	public SnakeHeadModel(double x, double y, BufferedImage bufferedImage) {
		this.bounding = new Ellipse2D.Double(x, y, bufferedImage.getWidth(),
				bufferedImage.getHeight());
		movement = new Point2D.Double(0,0);
	}

	public void actuate(double delta) {
		lastMove += delta;
		if (lastMove > speed) {
			lastMove = 0;
			move();
			setChanged();
			notifyObservers();
		}
	}

	private void move() {
			if (newDirection != Direction.NONE) {
				direction = newDirection;
				newDirection = Direction.NONE;
			}
			
			movement = new Point2D.Double(bounding.x, bounding.y);
			
			// TODO bewegung in die derzeitige richtung um bestimmte pixel
			switch (direction) {
			case DOWN:
				bounding.y += bounding.getHeight();
				break;
			case UP:
				bounding.y -= bounding.getHeight();
				break;
			case RIGHT:
				bounding.x += bounding.getWidth();
				break;
			case LEFT:
				bounding.x -= bounding.getWidth();
				break;
			default:
				break;
			}
			
	}

	public void checkCollision(IActor actor) {
		if (bounding.intersects(actor.getBounding())) {
			last.getX();
			last.getY();
		}
	}

	public Rectangle2D getBounding() {
		return this.bounding.getBounds2D();
	}

	public Point2D.Double getMovement() {
		return movement;
	}
	
	public void setLast(IPlayerBone last){
		this.last = last;
	}
	
	public void rotateSnake(Direction direction) {
		if ((this.direction == Direction.RIGHT && (direction == Direction.UP || direction == Direction.DOWN))
				|| (this.direction == Direction.LEFT && (direction == Direction.UP || direction == Direction.DOWN))
				|| (this.direction == Direction.UP && (direction == Direction.RIGHT || direction == Direction.LEFT))
				|| (this.direction == Direction.DOWN && (direction == Direction.LEFT || direction == Direction.RIGHT))) {
			newDirection = direction;
		}
	}

	@Override
	public double getX() {
		return bounding.x;
	}

	@Override
	public double getY() {
		return bounding.y;
	}

	@Override
	public Direction getDirection() {
		return this.direction;
	}
}