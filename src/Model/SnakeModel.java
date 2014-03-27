package Model;

import java.util.Observable;

import ViewInterface.Constants;

public class SnakeModel extends Observable {
	private int x;
	private int y;

	public SnakeModel(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void moveSnake(int direction) {
		switch (direction) {
		case Constants.RIGHT:
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
}
