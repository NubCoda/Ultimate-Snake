package Model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import ModelInterface.IActor;
import ViewInterface.IConstants;

public class SnakeModel extends Observable implements IActor{
	private int x;
	private int y;
	private int currentDirection = IConstants.RIGHT;
	private Map<Point, Integer> drawDirections;
	public SnakeModel(int x, int y) {
		this.x = x;
		this.y = y;
		drawDirections = new HashMap<Point, Integer>();
	}

	public void moveSnake(int direction) {
		switch (direction) {
		case IConstants.RIGHT:
			if(currentDirection==IConstants.UP || currentDirection==IConstants.DOWN){
				drawDirections.put(new Point(x, y), Integer.valueOf(direction));
			}
			break;
		case IConstants.LEFT:
			if(currentDirection==IConstants.UP || currentDirection==IConstants.DOWN){
				drawDirections.put(new Point(x, y), Integer.valueOf(direction));
			}
			break;
		case IConstants.UP:
			if(currentDirection==IConstants.RIGHT || currentDirection==IConstants.LEFT){
				drawDirections.put(new Point(x, y), Integer.valueOf(direction));
			}
			break;
		case IConstants.DOWN:
			if(currentDirection==IConstants.RIGHT || currentDirection==IConstants.LEFT){
				drawDirections.put(new Point(x, y), Integer.valueOf(direction));
			}
			break;
		}
		// "wenden" nicht erlauben -> wenn schlange nach oben läuft kann diese nicht plötzlich nach untent laufen
		// dasselbe mit unten=> oben; rechts=> links und links => rechts
		currentDirection = direction;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Map<Point, Integer> getDrawDirections(){
		return drawDirections;
	}

	public int getDirection(){
		return currentDirection;
	}

	@Override
	public void actuate() {
		switch (currentDirection) {
		case IConstants.RIGHT:
			x += 20;
			break;
		case IConstants.LEFT:
			x -= 20;
			break;
		case IConstants.UP:
			y -= 20;
			break;
		case IConstants.DOWN:
			y += 20;
			break;
		}
		setChanged();
		notifyObservers();
	}
}
