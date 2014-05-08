package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Observable;
import java.util.Observer;

import Model.Interface.Direction;
import Model.Interface.IPlayerBone;

@SuppressWarnings("serial")
public class SnakeHeadView extends SpriteView implements Observer{
	private Direction direction;
	public SnakeHeadView(String path, double x, double y,
			GamePanelView gamePanelView, Direction direction) {
		super(path, x, y, gamePanelView);
		this.direction = direction;
	}

	@Override
	public void drawObjects(Graphics graphics) {
		Graphics2D g2 = (Graphics2D) graphics;
		AffineTransform oldTransorfm = g2.getTransform();
		AffineTransform at = new AffineTransform();
		int rotation;
		switch (direction) {
		case UP:
			rotation = -90;
			break;
		case DOWN:
			rotation = 90;
			break;
		case LEFT:
			rotation = 180;
			break;
		default:
			rotation = 0;
			break;
		}
		at.rotate(Math.toRadians(rotation), (int) x + (10),
				(int) y + (10));
		g2.transform(at);
		g2.drawImage(bufferedImage, (int) x, (int) y, null);
		g2.setTransform(oldTransorfm);
	}

	@Override
	public void update(Observable o, Object arg) {
		IPlayerBone head = (IPlayerBone)o;
		this.x = head.getX();
		this.y = head.getY();
		this.direction = head.getDirection();
	}
}
