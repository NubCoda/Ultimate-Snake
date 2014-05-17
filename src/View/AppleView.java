package View;

import java.util.Observable;
import java.util.Observer;

import Model.Interface.IActor;

public class AppleView extends SpriteView implements Observer {
	public AppleView(String path, double x, double y,
			GamePanelView gamePanelView) {
		super(path, x, y, gamePanelView);
	}

	@Override
	public void update(Observable observable, Object argObject) {
		IActor apple = ((IActor) observable);
		this.x = apple.getBounding().getX();
		this.y = apple.getBounding().getY();
	}
}
