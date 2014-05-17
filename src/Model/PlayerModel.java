package Model;

import java.util.Observable;

public class PlayerModel extends Observable {

	
	
	public void setChanges() {
		setChanged();
		notifyObservers();
	}
}
