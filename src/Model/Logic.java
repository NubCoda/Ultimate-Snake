package Model;

import java.util.Observable;
import java.util.Vector;

import ModelInterface.IActor;


public class Logic extends Observable implements Runnable {
	private Vector<IActor> actors;
	public Logic(Vector<IActor> actors) {
		this.actors = actors;
	}

	@Override
	public void run() {
		while (true) {
			try {
				for (IActor actor : actors) {
					actor.actuate();
				}
				setChanged();
				notifyObservers();
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
