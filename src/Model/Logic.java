package Model;

import java.util.Observable;
import java.util.Vector;

import ModelInterface.Actor;


public class Logic extends Observable implements Runnable {
	private Vector<Actor> actors;
	public Logic(Vector<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public void run() {
		while (true) {
			try {
				for (Actor actor : actors) {
					actor.actuate();
				}
				setChanged();
				notifyObservers();
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
