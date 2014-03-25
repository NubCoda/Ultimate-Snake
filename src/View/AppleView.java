package View;

import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("serial")
public class AppleView extends SpriteView implements Observer {

	public AppleView(String path, double x, double y,
			GamePanelView gamePanelView) {
		super(path, x, y, gamePanelView);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable observable, Object argObject) {
		// TODO Auto-generated method stub
	}
}
