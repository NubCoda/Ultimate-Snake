package View;

import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("serial")
public class EnemyView extends SpriteView implements Observer {

	public EnemyView(BufferedImage[] bufferedImages, double x, double y,
			GamePanelView gamePanelView) {
		super(bufferedImages, x, y, gamePanelView);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable observable, Object argObject) {
		// TODO Auto-generated method stub
	}
}
