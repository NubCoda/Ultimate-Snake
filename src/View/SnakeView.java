package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import Model.SnakeModel;

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
		this.x = ((SnakeModel) observable).getX();
		this.y = ((SnakeModel) observable).getY();
	}

	@Override
	public void drawObjects(Graphics graphics) {
		Graphics2D g2 = (Graphics2D) graphics;
		AffineTransform oldTransorfm = g2.getTransform();
		AffineTransform at = new AffineTransform();
		at.rotate(Math.toRadians(90), x+(head.getWidth()/2), y+(head.getHeight()/2));
		g2.transform(at);
		g2.drawImage(head, (int)x, (int)y, null);
		g2.setTransform(oldTransorfm);
		g2.drawImage(tail, (int) x - 20, (int) y, null);
		g2.drawImage(tail, (int) x - 20 * 2, (int) y, null);
		g2.drawImage(tail, (int) x - 20 * 3, (int) y, null);
	}
}
