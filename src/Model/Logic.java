package Model;

import java.util.Observable;
import java.util.Vector;

import ModelInterface.IActor;


public class Logic extends Observable implements Runnable {
	private Vector<IActor> actors;
	public Logic(Vector<IActor> actors) {
		this.actors = actors;
	}


//	@Override
//	public void run() {
//		AppleView appleView = new AppleView("./resources/apple_sprite.png", 20, 20, gamePanelView);
//		AppleModel appleModel = null;
//		SnakeView snakeView = new SnakeView("./resources/head_sprite.png", "./resources/tail_sprite.png", 120, 120, gamePanelView);
//		snakeModel = new SnakeModel(120, 120);
//		appleModel = new AppleModel(gamePanelView);
//		appleModel.addObserver(appleView);
//		snakeModel.addObserver(snakeView);
//		gamePanelView.addActor(appleView);
//		gamePanelView.addActor(snakeView);
//	}

	@Override
	public void run() {
		while (true) {
			try {
				for (IActor actor : actors) {
					actor.actuate();
				}
				setChanged();
				notifyObservers();
				Thread.sleep(600);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
