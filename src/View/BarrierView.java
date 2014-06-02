package View;

import java.util.Observable;
import java.util.Observer;

import Model.BarrierModel;
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
		BarrierModel barrierModel = ((BarrierModel) observable);
		this.x = barrierModel.getBounding().getX();
		this.y = barrierModel.getBounding().getY();
		System.out.println("Something happend");
	}
}
