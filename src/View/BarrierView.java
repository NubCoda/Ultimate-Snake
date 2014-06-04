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
public class BarrierView extends SpriteView implements Observer {
	/**
	 * 
	 * @param path
	 * @param x
	 * @param y
	 * @param gamePanelView
	 */
	public BarrierView(String path, double x, double y,
			GamePanelView gamePanelView) {
		super(path, x, y, gamePanelView);
	}

	@Override
	public void update(Observable observable, Object argObject) {
		IEnemy barrier = ((IEnemy) observable);
		this.x = barrier.getBounding().getX();
		this.y = barrier.getBounding().getY();
		if(!barrier.isAlive()){
			MainController.getInstance().removeSpriteView(this);
			observable.deleteObserver(this);
		}
	}
}
