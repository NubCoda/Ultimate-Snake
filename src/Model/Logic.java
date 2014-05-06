package Model;

import java.util.Observable;
import java.util.Vector;

import Model.Interface.IActor;

public class Logic extends Observable implements Runnable {
	private Vector<IActor> actors;
	private boolean isGameRunning;
	private long last = 0;
	private long delta = 0;

	public Logic() {
		this.actors = new Vector<IActor>();
		last = System.nanoTime();
	}

	public void addActor(IActor actor) {
		actors.add(actor);
	}

	public void setGameRunning(boolean isGameRunning) {
		this.isGameRunning = isGameRunning;
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (isGameRunning) {
					delta = System.nanoTime() - last;
					// System.out.println(((long) 1e9)/delta); => fps
					last = System.nanoTime();
					for (IActor actor : actors) {
						actor.actuate(delta);
					}

					setChanged();
					notifyObservers();
				}
				Thread.sleep(15);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
