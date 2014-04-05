package Model;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.HashMap;
import java.util.Map;
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

	public SnakeModel(double x, double y, int length, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		bonesPoints = new Vector<Point2D.Double>();
		this.length = length;
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
			y += 20;
			break;
		case UP:
			y -= 20;
			break;
		case RIGHT:
			x += 20;
			break;
		case LEFT:
			x -= 20;
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

/* ALT */
// private int x;
// private int y;
// private int length = 20;
// private int currentDirection = IConstants.RIGHT;
// private Map<Point, Integer> drawDirections;

// this.x = x;
// this.y = y;
// drawDirections = new HashMap<Point, Integer>();

// switch (direction) {
// case IConstants.RIGHT:
// if (currentDirection == IConstants.UP
// || currentDirection == IConstants.DOWN) {
// drawDirections.put(new Point(x, y),
// Integer.valueOf(currentDirection));
// currentDirection = currentDirection == IConstants.DOWN ? IConstants.LEFT
// : direction;
// }
// break;
// case IConstants.LEFT:
// if (currentDirection == IConstants.UP
// || currentDirection == IConstants.DOWN) {
// drawDirections.put(new Point(x, y),
// Integer.valueOf(currentDirection));
// currentDirection = currentDirection == IConstants.DOWN ? IConstants.RIGHT
// : direction;
// }
// break;
// case IConstants.UP:
// if (currentDirection == IConstants.RIGHT
// || currentDirection == IConstants.LEFT) {
// drawDirections.put(new Point(x, y),
// Integer.valueOf(currentDirection));
// currentDirection = direction;
// }
// break;
// case IConstants.DOWN:
// if (currentDirection == IConstants.RIGHT
// || currentDirection == IConstants.LEFT) {
// drawDirections.put(new Point(x, y),
// Integer.valueOf(currentDirection));
// currentDirection = direction;
// }
// break;
// }
// int drawX = (int) x;
// int drawY = (int) y;
// int curDir = currentDirection;
// Map<Point, Integer> newDrawDirections = new HashMap<Point, Integer>();
// System.out.println("------------------------------------");
// for (int i = 1; i <= length; i++) {
// Point curPosition = new Point(drawX, drawY);
// if (drawDirections.containsKey(curPosition)) {
// System.out.println(curPosition + " " + curDir);
// curDir = drawDirections.get(curPosition);
// newDrawDirections.put(curPosition, curDir);
// }
// switch (curDir) {
// case IConstants.RIGHT:
// drawX -= 20;
// break;
// case IConstants.LEFT:
// drawX += 20;
// break;
// case IConstants.UP:
// drawY += 20;
// break;
// case IConstants.DOWN:
// drawY -= 20;
// break;
// }
// }
// drawDirections = newDrawDirections;
// return x;
// return y;
// public Map<Point, Integer> getDrawDirections() {
// return null;
// // return drawDirections;
// }
// return currentDirection;
// switch (currentDirection) {
// case IConstants.RIGHT:
// x += 20;
// break;
// case IConstants.LEFT:
// x -= 20;
// break;
// case IConstants.UP:
// y -= 20;
// break;
// case IConstants.DOWN:
// y += 20;
// break;
// }
// setChanged();
// notifyObservers();