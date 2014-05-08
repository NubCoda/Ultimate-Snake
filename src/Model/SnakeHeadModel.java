package Model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import Model.Interface.Actor;
import Model.Interface.Direction;
import Model.Interface.IConstants;
import Model.Interface.IPlayerBone;

public class SnakeHeadModel extends Actor implements IPlayerBone {
	private Direction direction;
	private Direction newDirection = Direction.NONE;
	private Point2D.Double oldPos;
	private int speed = IConstants.SNAKE_SPEED;
	
	public SnakeHeadModel(double x, double y, Direction direction, BufferedImage bufferedImage) {
		super(x, y, bufferedImage);
		this.direction = direction;
	}

	public void actuate(double delta) {
		move(delta);
		setChanged();
		notifyObservers();
	}

	private void move(double delta) {
		oldPos = new Point2D.Double(bounding.getMinX(), bounding.getMinY());
		if (newDirection != Direction.NONE) {
			direction = newDirection;
			newDirection = Direction.NONE;
		}
		
		switch (direction) {
		case DOWN:
			bounding.y += speed * delta;
			break;
		case UP:
			bounding.y -= speed * delta;
			break;
		case RIGHT:
			bounding.x += speed * delta;
			break;
		case LEFT:
			bounding.x -= speed * delta;
			break;
		default:
			break;
		}
	}

	public void checkCollision(Actor actor) {
		if(bounding.intersects(actor.getBounding())){
			System.out.println("Collision HEad");
		}
	}

	public Rectangle2D.Double getBounding() {
		return this.bounding;
	}
	
	@Override
	public Direction getDirection() {
		return direction;
	}
	
	public Point2D.Double getOldPos(){
		return oldPos;
	}

	public void switchDirection(Direction direction) {
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
}
