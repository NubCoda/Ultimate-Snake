package Model;

import java.util.Observable;

import ModelInterface.IActor;
import ViewInterface.IConstants;

public class SnakeModel extends Observable implements IActor{
	private int x;
	private int y;

	public SnakeModel(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void moveSnake(int direction) {
		switch (direction) {
		case IConstants.RIGHT:
			x += 20;
			break;
		}
		setChanged();
		notifyObservers();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public void actuate() {
		x += 20;
		setChanged();
		notifyObservers();
	}


}
