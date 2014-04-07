package View;

import java.util.Observable;
import java.util.Observer;

import Model.AppleModel;
import Model.Interface.IElement;

@SuppressWarnings("serial")
public class AppleView extends SpriteView implements Observer {
	public AppleView(String path, double x, double y,
			GamePanelView gamePanelView) {
		super(path, x, y, gamePanelView);
		width = 20;
		height = 20;
	}

	@Override
	public void update(Observable observable, Object argObject) {
		IElement apple = ((IElement) observable);
		this.x = apple.getX();
		this.y = apple.getY();
	}

	@Override
	public boolean collidedWith(SpriteView spriteView) {
		if(this.intersects(spriteView)){
			return true;
		}
		return false;
	}
}
