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
		// TODO Head und je nach der Groesse die tail mehr mal zeichnen
	}
}
