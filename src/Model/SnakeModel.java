package Model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import Model.Interface.Direction;
import Model.Interface.IActor;
import Model.Interface.IPlayer;

public class SnakeModel extends Observable implements IActor, IPlayer {
	private double x;
	private double y;
	private Direction direction;
	private Map<Point, Point> bonesPoints;
	private long speed = 250;
	private long lastMove = 0;
	private int length;

	public SnakeModel(double x, double y, int length, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		bonesPoints = new HashMap<Point, Point>();
		this.length = length;
		for (int i = 1; i <= this.length; i++) {
			bonesPoints.put(new Point(((int)x)-20*i, (int)y), new Point(((int)x)-20*i, (int)y));
		}
	}

//	private void moveSnake(int direction) {
//
//	}

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
	public Map<Point, Point> getBonesPosition() {
		return bonesPoints;
	}

	@Override
	public void actuate(long delta) {
		lastMove+=(delta/1000000);
		if(lastMove>speed){
			lastMove = 0;
			// TODO schlange bewegen
			setChanged();
			notifyObservers();
		}
	}
}


/* ALT */
//private int x;
//private int y;
//private int length = 20;
//private int currentDirection = IConstants.RIGHT;
//private Map<Point, Integer> drawDirections;

//this.x = x;
//this.y = y;
//drawDirections = new HashMap<Point, Integer>();

//switch (direction) {
//case IConstants.RIGHT:
//	if (currentDirection == IConstants.UP
//			|| currentDirection == IConstants.DOWN) {
//		drawDirections.put(new Point(x, y),
//				Integer.valueOf(currentDirection));
//		currentDirection = currentDirection == IConstants.DOWN ? IConstants.LEFT
//				: direction;
//	}
//	break;
//case IConstants.LEFT:
//	if (currentDirection == IConstants.UP
//			|| currentDirection == IConstants.DOWN) {
//		drawDirections.put(new Point(x, y),
//				Integer.valueOf(currentDirection));
//		currentDirection = currentDirection == IConstants.DOWN ? IConstants.RIGHT
//				: direction;
//	}
//	break;
//case IConstants.UP:
//	if (currentDirection == IConstants.RIGHT
//			|| currentDirection == IConstants.LEFT) {
//		drawDirections.put(new Point(x, y),
//				Integer.valueOf(currentDirection));
//		currentDirection = direction;
//	}
//	break;
//case IConstants.DOWN:
//	if (currentDirection == IConstants.RIGHT
//			|| currentDirection == IConstants.LEFT) {
//		drawDirections.put(new Point(x, y),
//				Integer.valueOf(currentDirection));
//		currentDirection = direction;
//	}
//	break;
//}
//int drawX = (int) x;
//int drawY = (int) y;
//int curDir = currentDirection;
//Map<Point, Integer> newDrawDirections = new HashMap<Point, Integer>();
//System.out.println("------------------------------------");
//for (int i = 1; i <= length; i++) {
//	Point curPosition = new Point(drawX, drawY);
//	if (drawDirections.containsKey(curPosition)) {
//		System.out.println(curPosition + " " + curDir);
//		curDir = drawDirections.get(curPosition);
//		newDrawDirections.put(curPosition, curDir);
//	}
//	switch (curDir) {
//	case IConstants.RIGHT:
//		drawX -= 20;
//		break;
//	case IConstants.LEFT:
//		drawX += 20;
//		break;
//	case IConstants.UP:
//		drawY += 20;
//		break;
//	case IConstants.DOWN:
//		drawY -= 20;
//		break;
//	}
//}
//drawDirections = newDrawDirections;
//return x;
//return y;
//public Map<Point, Integer> getDrawDirections() {
//	return null;
////	return drawDirections;
//}
//return currentDirection;
//switch (currentDirection) {
//case IConstants.RIGHT:
//	x += 20;
//	break;
//case IConstants.LEFT:
//	x -= 20;
//	break;
//case IConstants.UP:
//	y -= 20;
//	break;
//case IConstants.DOWN:
//	y += 20;
//	break;
//}
//setChanged();
//notifyObservers();