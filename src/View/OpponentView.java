package View;

import java.util.Observable;
import java.util.Observer;

import Model.Interface.IActor;

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
		IActor opponent = ((IActor) observable);
		this.x = opponent.getBounding().getX();
		this.y = opponent.getBounding().getY();
	}

}
