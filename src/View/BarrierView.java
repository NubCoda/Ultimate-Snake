package View;

import java.util.Observable;
import java.util.Observer;

import Model.Interface.IActor;

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
		IActor barrier = ((IActor) observable);
		this.x = barrier.getBounding().getX();
		this.y = barrier.getBounding().getY();
	}
}
