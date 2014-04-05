package View;

import java.util.Observable;
import java.util.Observer;

import Model.AppleModel;

@SuppressWarnings("serial")
public class AppleView extends SpriteView implements Observer {
	public AppleView(String path, double x, double y,
			GamePanelView gamePanelView) {
		super(path, x, y, gamePanelView);
		width = 20;
		height = 20;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable observable, Object argObject) {

		setRect(((AppleModel) observable).getX(),
				((AppleModel) observable).getY(), 20, 20);
	}

	@Override
	public boolean collidedWith(SpriteView spriteView) {
		if(this.intersects(spriteView)){
			System.out.println("kollision apple");
			return true;
		}
		return false;
	}
}
