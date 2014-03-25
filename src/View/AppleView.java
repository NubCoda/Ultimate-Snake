package View;

import java.util.Observable;
import java.util.Observer;

import Model.AppleModel;

@SuppressWarnings("serial")
public class AppleView extends SpriteView implements Observer {

	public AppleView(String path, double x, double y,
			GamePanelView gamePanelView) {
		super(path, x, y, gamePanelView);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable observable, Object argObject) {
		System.out.println("Test");
		setRect(((AppleModel)observable).getApplePosition_x(), ((AppleModel)observable).getApplePosition_y(), 40, 40);
	}
}
