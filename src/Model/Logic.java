package Model;

import java.awt.Point;
import java.util.Observable;
import java.util.Vector;

import Model.Interface.IActor;

/**
 * 
 * 
 */
public class Logic extends Observable implements Runnable {
	private Vector<IActor> actors;
	private boolean isGameRunning;
	private long last = 0;
	private double delta = 0;
	private boolean isKilled = false;

	/**
	 * 
	 */
	public Logic() {
		this.actors = new Vector<IActor>();
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
				// TODO Meunu hier unterbringen und neuzeichenen auffordern
				if (isGameRunning) {
					long currentTime = System.nanoTime();
					delta = (currentTime - last) / 1000000.00;
					// System.out.println(((long) 1e9)/delta); => fps
					last = currentTime;
					for (IActor actor : actors) {
						actor.actuate(delta);
					}
					for (int i = 0; i < actors.size(); i++) {
						for (int j = i + 1; j < actors.size(); j++) {
							IActor s1 = actors.elementAt(i);
							IActor s2 = actors.elementAt(j);
							s1.checkCollision(s2);
						}
					}
				}
				setChanged();
				notifyObservers();
				Thread.sleep(15);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void gameOver() {
		setGameRunning(false);
		//TODO
	}
}