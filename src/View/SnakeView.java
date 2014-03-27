package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.ImageCapabilities;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;

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
		// super.paintComponent(g);

		// create the transform, note that the transformations happen
		// in reversed order (so check them backwards)
//		AffineTransform at = new AffineTransform();
//		at.
		// 4. translate it to the center of the component
		// at.translate((int) x-40, (int) y);

		// 3. do the actual rotation
//		at.rotate(Math.toRadians(90));

		// 2. just a scale because this image is big
		// at.scale(0.5, 0.5);

		// 1. translate the object so that you rotate it around the
		// center (easier :))
		// at.translate(-head.getWidth()/2, -head.getHeight()/2);

		// draw the image
//		Graphics2D g2d = (Graphics2D) graphics;
		// g2d.drawImage(head, at, null);
//		g2d.drawImage(head, at, null);
		graphics.drawImage(head, (int) x, (int) y, null);
		graphics.drawImage(tail, (int) x - 20, (int) y, null);
		graphics.drawImage(tail, (int) x - 20 * 2, (int) y, null);
		graphics.drawImage(tail, (int) x - 20 * 3, (int) y, null);
	}
}
