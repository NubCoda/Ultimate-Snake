package Model;

import java.util.Observable;
import java.util.Vector;

import Model.Interface.IActor;
import View.SpriteView;

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
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
