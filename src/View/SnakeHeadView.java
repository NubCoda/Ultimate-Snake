package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Observable;
import java.util.Observer;

import Model.Interface.Direction;
import Model.Interface.IPlayerBone;

/**
 * Zeichnet den Kopf der Schlange
 */
public class SnakeHeadView extends SpriteView implements Observer {
	private int rotation;
	private Direction direction;

	/**
	 * Der Konstruktor für die View-Klasse des Schlangenkopfes.
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
	public SnakeHeadView(String path, double x, double y,
			GamePanelView gamePanelView) {
		super(path, x, y, gamePanelView);
		this.direction = Direction.RIGHT;
	}

	@Override
	public void drawObjects(Graphics graphics) {
		/*
		 * Dreht das BufferedImage mithilfe des direction-Wertes. Der
		 * direction-Wert wird, wenn möglich durch die gedrücke Taste oder durch
		 * den letzten direction-Wert bestimmt. Der "oldTransform"-Wert setzt
		 * hierbei das Buffered Image in die richtige Position.
		 */
		Graphics2D g2 = (Graphics2D) graphics;
		AffineTransform oldTransorfm = g2.getTransform();
		AffineTransform at = new AffineTransform();
		rotation = 0;
		switch (direction) {
		case UP:
			rotation = 270;
			break;
		case DOWN:
			rotation = 90;
			break;
		case LEFT:
			rotation = 180;
			break;
		default:
			break;
		}
		at.rotate(Math.toRadians(rotation), (int) x + (10), (int) y + (10));
		g2.transform(at);
		g2.drawImage(bufferedImage, (int) x, (int) y, null);
		g2.setTransform(oldTransorfm);
	}

	@Override
	public void update(Observable o, Object arg) {
		/*
		 * Zeichnet das BufferedImage an dem x- bzw y-Wert des im Controller
		 * zusammengeführten Models Weist dem SnakeHeadView die aktuelle
		 * Direction der Snake zu.
		 */
		IPlayerBone head = (IPlayerBone) o;
		this.x = head.getBounding().getX();
		this.y = head.getBounding().getY();
		this.direction = head.getDirection();
	}
}
