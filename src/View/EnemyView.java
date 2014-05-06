package View;

import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("serial")
public class EnemyView extends SpriteView implements Observer {

	public EnemyView(String path, double x, double y,
			GamePanelView gamePanelView) {
		super(path, x, y, gamePanelView);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable observable, Object argObject) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean collidedWith(SpriteView spriteView) {
		// TODO Auto-generated method stub
		return false;
	}
}
