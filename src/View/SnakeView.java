package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import Model.SnakeModel;
import ViewInterface.IConstants;

@SuppressWarnings("serial")
public class SnakeView extends SpriteView implements Observer {
	private BufferedImage head;
	private BufferedImage tail;
	private int length = 3;
	private int currentDirection = IConstants.RIGHT;
	private Map<Point, Integer> drawDirections;

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
		drawDirections = ((SnakeModel) observable).getDrawDirections();
		currentDirection = ((SnakeModel) observable).getDirection();
	}

	@Override
	public void drawObjects(Graphics graphics) {
		//TODO / FIXME : die durchgelaufenen positionen muessen geloescht werden
		Graphics2D g2 = (Graphics2D) graphics;
		AffineTransform oldTransorfm = g2.getTransform();
		AffineTransform at = new AffineTransform();
		int rotation = 0;
		if (currentDirection == IConstants.UP) {
			rotation = -90;
		} else if (currentDirection == IConstants.DOWN) {
			rotation = +90;
		} else if (currentDirection == IConstants.LEFT) {
			rotation = +180;
		}
		at.rotate(Math.toRadians(rotation), (int) x+ (head.getWidth() / 2),
				(int) y+ (head.getHeight() / 2));
		g2.transform(at);
		g2.drawImage(head, (int) x, (int) y, null);
		g2.setTransform(oldTransorfm);
		int drawX = (int) x - tail.getWidth();
		int drawY = (int) y;
		for (int i = 1; i <= length; i++) {
			Point curPosition = new Point(drawX, drawY);
			if (drawDirections.containsKey(curPosition)) {
				switch (drawDirections.get(curPosition)) {
				case IConstants.RIGHT:
					drawY += tail.getHeight();
					break;
				case IConstants.LEFT:
					drawY -= tail.getHeight();
					break;
				case IConstants.UP:
					drawX += tail.getWidth();
					break;
				case IConstants.DOWN:
					drawX -= tail.getWidth();
					break;
				}
			}
			g2.drawImage(tail, drawX, drawY, null);
			drawX -= tail.getWidth();
		}
	}
}
