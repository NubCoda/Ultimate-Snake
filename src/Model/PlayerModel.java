package Model;

import java.util.Observable;

/**
 * 
 * 
 */
public class PlayerModel extends Observable {

	public String getName() {
		return "Test";
	}

	public int getScore() {
		return 0;
	}

	public int getHighscore() {
		return 0;
	}

	public int getDifficulty() {
		return 0;
	}

	public void changePlayer() {
		
		setChanged();
		notifyObservers();
	}
}