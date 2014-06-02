package View;

import java.util.Observable;
import java.util.Observer;

import Controller.MainController;
import Model.Interface.IActor;
import Model.Interface.IEnemy;

/**
 * 
 * 
 */
public class OpponentView extends SpriteView implements Observer {
	/**
	 * 
	 * @param path
	 * @param x
	 * @param y
	 * @param gamePanelView
	 */
	public OpponentView(String path, double x, double y,
			GamePanelView gamePanelView) {
		super(path, x, y, gamePanelView);
	}

	@Override
	public void update(Observable observable, Object argObject) {
		IEnemy opponent = ((IEnemy) observable);
		this.x = opponent.getBounding().getX();
		this.y = opponent.getBounding().getY();
		if(!opponent.isAlive()){
			MainController.getInstance().removeSpriteView(this);
			observable.deleteObserver(this);
		}
	}

}
