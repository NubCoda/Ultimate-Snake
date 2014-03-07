package View;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import ViewInterface.IDrawable;
import ViewInterface.IMoveable;

@SuppressWarnings("serial")
public class SpriteView extends Rectangle2D.Double implements IDrawable, IMoveable {


	private GamePanelView gamePanelView;
	private BufferedImage[] bufferedImages;

	public SpriteView(BufferedImage[] bufferedImages, double x, double y, GamePanelView gamePanelView) {
		this.bufferedImages = bufferedImages;
		this.x = x;
		this.y = y;
		this.gamePanelView = gamePanelView;
	}

	/* (non-Javadoc)
	 * @see ViewInterface.IMoveable#move(java.awt.Point)
	 */
	@Override
	public void move(Point point) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see ViewInterface.IDrawable#drawObjects(java.awt.Graphics)
	 */
	@Override
	public void drawObjects(Graphics graphics) {
		graphics.drawImage(bufferedImages[0], (int) x, (int) y, null);
	}

	public void doLogic() {

	}
}
