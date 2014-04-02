package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import Model.SnakeModel;
import ViewInterface.IConstants;

@SuppressWarnings("serial")
public class SnakeView extends SpriteView implements Observer {
	private BufferedImage head;
	private BufferedImage tail;
	private int length = 20;
	private int currentDirection = IConstants.RIGHT;
	private Map<Point, Integer> drawDirections;
	private GamePanelView gamePanelView;

	public SnakeView(String pathHead, String pathTail, double x, double y,
			GamePanelView gamePanelView) {
		super(pathHead, x, y, gamePanelView);
		this.gamePanelView = gamePanelView;
		head = loadImage(pathHead);
		tail = loadImage(pathTail);
		drawDirections = new HashMap<Point, Integer>();
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
		Graphics2D g2 = (Graphics2D) graphics;
		AffineTransform oldTransorfm = g2.getTransform();
		AffineTransform at = new AffineTransform();
		int rotation = 0;
		if (currentDirection == IConstants.UP) {
			rotation = -90;
		} else if (currentDirection == IConstants.DOWN) {
			rotation = 90;
		} else if (currentDirection == IConstants.LEFT) {
			rotation = 180;
		}
		at.rotate(Math.toRadians(rotation), (int) x+ (head.getWidth() / 2),
				(int) y+ (head.getHeight() / 2));
		g2.transform(at);
		g2.drawImage(head, (int) x, (int) y, null);
		g2.setTransform(oldTransorfm);
		int drawX = (int) x;
		int drawY = (int) y;
		int curDir = currentDirection;
		for (int i = 1; i <= length; i++) {
			Point curPosition = new Point(drawX, drawY);
			if (drawDirections.containsKey(curPosition)) {
				curDir = drawDirections.get(curPosition);
			}
			switch (curDir) {
			case IConstants.RIGHT:
				drawX -= tail.getWidth();
				break;
			case IConstants.LEFT:
				drawX += tail.getWidth();
				break;
			case IConstants.UP:
				drawY += tail.getHeight();
				break;
			case IConstants.DOWN:
				drawY -= tail.getHeight();
				break;
			}
			g2.drawImage(tail, drawX, drawY, null);
		}
	}
}
