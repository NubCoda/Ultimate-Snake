package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Observable;
import java.util.Observer;

import Controller.MainController;
import Model.Interface.Direction;
import Model.Interface.ISphere;

/**
 * Zeichnet das Geschoss der Schlange
 */
public class BulletView extends SpriteView implements Observer {
	private int rotation;
	private Direction direction;

	/**
	 * Initialisiert das von der Schlange Feuerbare Geschoss. Das Buffered Image
	 * wird je nach Richtung in welche sich die Schlange bewegt gezeichnet und
	 * ändert seine Ausrichtung dementsprechend
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
	public BulletView(String path, double x, double y,
			GamePanelView gamePanelView) {
		super(path, x, y, gamePanelView);
		this.direction = Direction.RIGHT;
	}

	@Override
	public void drawObjects(Graphics graphics) {
		/*
		 * Dreht das Geschoss abhängig von der Richtung, in welche sich die
		 * Schlange bewegt & zeichnet es in der Mitte des Schlangenkopfes.
		 */
		Graphics2D g2 = (Graphics2D) graphics;
		AffineTransform oldTransorfm = g2.getTransform();
		AffineTransform at = new AffineTransform();
		rotation = 180;
		switch (direction) {
		case UP:
			rotation = 90;
			break;
		case DOWN:
			rotation = 270;
			break;
		case LEFT:
			rotation = 0;
			break;
		default:
			break;
		}
		at.rotate(Math.toRadians(rotation),
				(int) x + (this.bufferedImage.getWidth() / 2), (int) y
						+ (this.bufferedImage.getHeight() / 2));
		g2.transform(at);
		g2.drawImage(bufferedImage, (int) x, (int) y, null);
		g2.setTransform(oldTransorfm);
	}

	@Override
	public void update(Observable observable, Object argObject) {
		/*
		 * Neue Position und Richtung des Geschosses aus dem Model holen und
		 * wenn das Geschoss aufgebraucht ist, dies aus dem Spiel entfernen
		 */
		ISphere bullet = ((ISphere) observable);
		this.x = bullet.getBounding().getX();
		this.y = bullet.getBounding().getY();
		this.direction = bullet.getDirection();
		if (bullet.isGone()) {
			MainController.getInstance().removeSpriteView(this);
			observable.deleteObserver(this);
		}
	}
}