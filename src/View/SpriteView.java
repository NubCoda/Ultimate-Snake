package View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import View.Interface.IDrawable;

/**
 * Parent Klasse der einzelnen Sprites
 */
public abstract class SpriteView implements IDrawable {
	protected GamePanelView gamePanelView;
	protected BufferedImage bufferedImage;
	protected double x;
	protected double y;

	/**
	 * Konstruktor für das Intitialisieren und Positionieren eines Bildes.
	 * Übergabeparameter sind Position(x,y), das GamePanel und der Sourcepath.
	 * 
	 * @param path
	 *            Pfad des Bildes
	 * @param x
	 *            x-Position, an welcher gezeichnet wird
	 * @param y
	 *            y-Position, an welcher gezeichnet wird
	 * @param gamePanelView
	 *            Das Panel auf welchem das Bild gezeichnet wird
	 */
	public SpriteView(String path, double x, double y,
			GamePanelView gamePanelView) {
		bufferedImage = loadImage(path);
		this.x = x;
		this.y = y;
		this.gamePanelView = gamePanelView;
	}

	/**
	 * Methode, welche über den gelieferten Pfad versucht ein BufferedImage zu
	 * erzeugen und zurückzuliefern
	 * 
	 * @param path
	 *            Pfad zum Bild
	 * @return BufferedImage aus Pfad
	 */
	protected BufferedImage loadImage(String path) {
		BufferedImage bufferedImage = null;
		if (path != null && !path.isEmpty()) {
			try {
				bufferedImage = ImageIO.read(new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bufferedImage;
	}

	@Override
	public void drawObjects(Graphics graphics) {
		/*
		 * Das BufferedImage wird an der Position(x,y) gezeichnet. "null" wird
		 * übergeben, weil kein Imageobserver benutzt wird.
		 */
		if (bufferedImage != null) {
			graphics.drawImage(bufferedImage, (int) x, (int) y, null);
		}
	}

	/**
	 * @return BufferedImage des Sprites
	 */
	public BufferedImage getImage() {
		return bufferedImage;
	}
}