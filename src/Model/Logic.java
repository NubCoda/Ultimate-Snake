package Model;

import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;

import Model.Interface.IActor;

/**
 * 
 * 
 */
public class Logic extends Observable implements Runnable {
	private CopyOnWriteArrayList<IActor> actors;
	private boolean isGameRunning;
	private long last = 0;
	private double delta = 0;
	private boolean isKilled = false;

	/**
	 * 
	 */
	public Logic() {
		this.actors = new CopyOnWriteArrayList<IActor>();
	}

	/**
	 * 
	 * @param actor
	 */
	public void addActor(IActor actor) {
		actors.add(actor);
	}

	/**
	 * 
	 */
	public void clearActors() {
		actors.clear();
	}

	/**
	 * 
	 * @param isGameRunning
	 */
	public void setGameRunning(boolean isGameRunning) {
		this.isGameRunning = isGameRunning;
	}

	/**
	 * 
	 * @param actor
	 * @return
	 */
	public IActor getActor(IActor actor) {
		return actors.get(actors.indexOf(actor));
	}

	/**
	 * 
	 */
	public void kill() {
		isKilled = true;
	}

	@Override
	public void run() {
		last = System.nanoTime();
		while (!isKilled) {
			try {
				if (isGameRunning) {
					long currentTime = System.nanoTime();
					delta = (currentTime - last) / 1000000.00;
					last = currentTime;
					for (IActor actor : actors) {
						actor.actuate(delta);
					}
					for (int i = 0; i < actors.size(); i++) {
						for (int j = i + 1; j < actors.size(); j++) {
							IActor s1 = actors.get(i);
							IActor s2 = actors.get(j);
							s1.checkCollision(s2);
						}
					}

				}
				setChanged();
				notifyObservers();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void removeActor(IActor actor) {
		this.actors.remove(actor);
	}

	public IActor getAtPosition(double x, double y, double width, double height) {
		IActor actor = null;
		for (int i = 0; i < actors.size(); i++) {
			if (actors.get(i).getBounding().contains(x, y, width, height)) {
				actor = actors.get(i);
				break;
			}
		}
		return actor;
	}
}