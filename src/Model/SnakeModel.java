package Model;

import java.awt.ItemSelectable;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Vector;

import Model.Interface.Direction;
import Model.Interface.IActor;
import Model.Interface.IConstants;
import Model.Interface.IPlayer;

public class SnakeModel extends Observable implements IActor, IPlayer {

	public SnakeModel(double x, double y) {
		if(Direction.RIGHT==Direction.RIGHT) System.out.println(true);
	}

	private void moveSnake(int direction) {

	}

	@Override
	public double getX() {
		return 0;
	}

	@Override
	public double getY() {
		return 0;
	}

	@Override
	public Direction getDirection() {
		return Direction.RIGHT;
	}

	@Override
	public Vector<Point> getBonesPosition() {
		return null;
	}

	@Override
	public void actuate(long delta) {

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