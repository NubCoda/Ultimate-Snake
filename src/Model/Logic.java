package Model;

import java.util.Observable;
import java.util.Vector;

import Model.Interface.Actor;

public class Logic extends Observable implements Runnable {
	private Vector<Actor> actors;
	private boolean isGameRunning;
	private long last = 0;
	private double delta = 0;

	public Logic() {
		this.actors = new Vector<Actor>();
	}

	public void addActor(Actor actor){
		actors.add(actor);
	}

	public void setGameRunning(boolean isGameRunning) {
		this.isGameRunning = isGameRunning;
	}

	@Override
	public void run() {
		last = System.currentTimeMillis();
		while (true) {
			try {
				if(isGameRunning){
					long currentTime = System.currentTimeMillis();
					delta = (System.currentTimeMillis() - last)/1000.00;
//					System.out.println(((long) 1e9)/delta); => fps
					last = currentTime;
					for (Actor actor : actors) {
						actor.actuate(delta);
					}
					for (int i = 0; i < actors.size(); i++) {
						for (int j = i+1; j < actors.size(); j++) {
							Actor s1 = actors.elementAt(i);
							Actor s2 = actors.elementAt(j);
							s1.checkCollision(s2);
						}
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
