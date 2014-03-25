package View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("serial")
public class SnakeView extends SpriteView implements Observer {
	private BufferedImage head;
	private BufferedImage tail;

	public SnakeView(String pathHead, String pathTail, double x, double y,
			GamePanelView gamePanelView) {
		super(pathHead, x, y, gamePanelView);
		head = loadImage(pathHead);
		tail = loadImage(pathTail);
	}

	@Override
	public void update(Observable observable, Object argObject) {
		// TODO Auto-generated method stub
	}

	@Override
	public void drawObjects(Graphics graphics) {
		graphics.drawImage(head, (int) x, (int) y, null);
		graphics.drawImage(tail, (int) x-40, (int) y, null);
		graphics.drawImage(tail, (int) x-40*2, (int) y, null);
		graphics.drawImage(tail, (int) x-40*3, (int) y, null);
	}
}
