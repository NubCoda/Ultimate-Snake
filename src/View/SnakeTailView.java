package View;

import java.util.Observable;
import java.util.Observer;

import Model.Interface.IPlayerBone;

/**
 * 
 * 
 */
public class SnakeTailView extends SpriteView implements Observer {
	/**
	 * 
	 * @param path
	 * @param x
	 * @param y
	 * @param gamePanelView
	 */
	public SnakeTailView(String path, double x, double y,
			GamePanelView gamePanelView) {
		super(path, x, y, gamePanelView);
	}

	@Override
	public void update(Observable o, Object arg) {
		IPlayerBone tail = (IPlayerBone) o;
		this.x = tail.getX();
		this.y = tail.getY();
	}
}
