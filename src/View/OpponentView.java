package View;

import java.util.Observable;
import java.util.Observer;

import Model.Interface.Direction;
import Model.Interface.IActor;

@SuppressWarnings("serial")
public class OpponentView extends SpriteView implements Observer {
	
	private int rotation;
	private Direction direction;
	
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
