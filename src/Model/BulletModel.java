package Model;

import java.awt.geom.Rectangle2D;
import java.util.Observable;

import Controller.MainController;
import Model.Interface.Direction;
import Model.Interface.IActor;
import Model.Interface.ISphere;

/**
 * Die Klasse für das Model des Schusses
 */
public class BulletModel extends Observable implements ISphere {
	/*
	 * Die Variabeln der Klasse
	 */
	private Rectangle2D.Double bounding;
	private double speed = 0.5;
	private double timeAlive = 0;
	private final double TIMETOLIVEINSECONDS = 5000;
	private boolean bulletIsGone = false;
	private Direction direction;

	/**
	 * Der Konstruktor der Klasse
	 * 
	 * @param midOfSnakeHeadGraphicX
	 *            Die x-Position der Mitte des Schlangenkopfes
	 * @param midOfSnakeHeadGraphicY
	 *            Die y-Position der Mitte des Schlangenkopfes
	 * @param width
	 *            Die Breite des Geschosses
	 * @param height
	 *            Die Höhe des Geschosses
	 * @param direction
	 *            Die aktuelle Richtung des Kopfes der Schlange
	 */
	public BulletModel(double midOfSnakeHeadGraphicX,
			double midOfSnakeHeadGraphicY, int width, int height,
			Direction direction) {
		this.direction = direction;
		this.bounding = new Rectangle2D.Double(midOfSnakeHeadGraphicX
				- (width / 2), midOfSnakeHeadGraphicY - (height / 2), width,
				height);

	}

	@Override
	public Rectangle2D getBounding() {
		return bounding;
	}

	@Override
	public void actuate(double delta) {
		/*
		 * Nur wenn der Schuss am "leben" ist
		 */
		if (!bulletIsGone) {
			/*
			 * setze die Variable auf true wenn die Zeit überschritten wurde
			 */
			timeAlive += delta;
			if (timeAlive > TIMETOLIVEINSECONDS) {
				this.bulletIsGone = true;
			}
			/*
			 * Je nach Richtung ändern wir die Position Dabei multiplizieren wir
			 * die Geschwindigkeit mit Delta
			 */
			if (this.direction == Direction.DOWN) {
				bounding.y += speed * delta;
			} else if (this.direction == Direction.RIGHT) {
				bounding.x += speed * delta;
			} else if (this.direction == Direction.LEFT) {
				bounding.x -= speed * delta;
			} else if (this.direction == Direction.UP) {
				bounding.y -= speed * delta;
			}
		} else {
			/*
			 * Entferne den Schuss im anderen Fall und benachrichtige die
			 * Observer
			 */
			MainController.getInstance().removeActor(this);
		}
		setChanged();
		notifyObservers();
	}

	@Override
	public void checkCollision(IActor actor) {
	}

	@Override
	public Direction getDirection() {
		return this.direction;
	}

	@Override
	public boolean isGone() {
		return bulletIsGone;
	}

	@Override
	public void setGone(boolean b) {
		this.bulletIsGone = b;
	}
}