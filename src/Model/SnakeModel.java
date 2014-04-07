package Model;

import java.awt.geom.Point2D;
import java.util.Observable;
import java.util.Vector;

import Model.Interface.Direction;
import Model.Interface.IActor;
import Model.Interface.IPlayer;

public class SnakeModel extends Observable implements IActor, IPlayer {
	private double x;
	private double y;
	private Direction direction;
	private Vector<Point2D.Double> bonesPoints;
	private long speed = 250;
	private long lastMove = 0;
	private int length;
	private Direction newDirection = Direction.NONE;
	private Double maxX;
	private Double minX = new Double(0);
	private Double maxY;
	private Double minY = new Double(0);

	public SnakeModel(double x, double y, int length, Direction direction, Double maxX, Double maxY) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		bonesPoints = new Vector<Point2D.Double>();
		this.length = length;
		this.maxX = maxX;
		this.maxY = maxY;
		for (int i = 1; i <= this.length; i++) {
			switch (direction) {
			case DOWN:
				bonesPoints.add(new Point2D.Double(x, y - 20 * i));
				break;
			case UP:
				bonesPoints.add(new Point2D.Double(x, y + 20 * i));
				break;
			case RIGHT:
				bonesPoints.add(new Point2D.Double(x - 20 * i, y));
				break;
			case LEFT:
				bonesPoints.add(new Point2D.Double(x + 20 * i, y));
				break;
			default:
				break;
			}
		}
	}

	public void switchDirection(Direction direction) {
		if((this.direction==Direction.RIGHT && (direction==Direction.UP || direction==Direction.DOWN))
			|| (this.direction==Direction.LEFT && (direction==Direction.UP || direction==Direction.DOWN))
			|| (this.direction==Direction.UP && (direction==Direction.RIGHT || direction==Direction.LEFT))
			|| (this.direction==Direction.DOWN && (direction==Direction.LEFT || direction==Direction.RIGHT))
		){
			newDirection = direction;
		}
	}

	public void moveSnake() {
		Point2D.Double oldPos = new Point2D.Double(x, y);
		if (newDirection != Direction.NONE) {
			direction = newDirection;
			newDirection = Direction.NONE;
		}

		switch (direction) {
		case DOWN:
			y = (y+20)%maxY;
			break;
		case UP:
			y-=20;
			if(y<minY){
				y+= maxY;
			}
			break;
		case RIGHT:
			x = (x+20)%maxX;
			break;
		case LEFT:
			x-=20;
			if(x<minX){
				x+= maxX;
			}
			break;
		default:
			break;
		}
		for (Point2D.Double point : bonesPoints) {
			Point2D.Double tmp = (Point2D.Double) point.clone();
			point.setLocation(oldPos);
			oldPos = tmp;
		}
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public Vector<Point2D.Double> getBonesPosition() {
		return bonesPoints;
	}

	@Override
	public void actuate(long delta) {
		lastMove += (delta / 1000000);
		if (lastMove > speed) {
			lastMove = 0;
			moveSnake();
			// TODO schlange bewegen
			setChanged();
			notifyObservers();
		}
	}
}