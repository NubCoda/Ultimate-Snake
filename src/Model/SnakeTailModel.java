package Model;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.util.Observable;

import Controller.MainController;
import Model.Interface.Direction;
import Model.Interface.IActor;
import Model.Interface.IEnemy;
import Model.Interface.IPlayerBone;

/**
 * Diese Klasse ist das Model der Körperteile von der Schlange
 */
public class SnakeTailModel extends Observable implements IPlayerBone {
	/*
	 * Die Variablen
	 */
	public IPlayerBone vorgaenger;
	private Point2D.Double movement;
	private Ellipse2D.Double bounding;
	private Direction direction = Direction.NONE;
	private Direction newDirection = Direction.NONE;

	/**
	 * Der Konstruktor der Klasse
	 * 
	 * @param x
	 *            Die x-Startposition des Koperteils der Schlange
	 * @param y
	 *            Die y-Startposition des Koperteils der Schlange
	 * @param vorgaenger
	 *            Das Objekt des vorangehenden Körperteil der Schlange
	 * @param width
	 *            Die Breite des Köperteils der Schlange
	 * @param height
	 *            Die Höhe des Köperteils der Schlange
	 */
	public SnakeTailModel(double x, double y, IPlayerBone vorgaenger,
			int width, int height) {
		/*
		 * Hier wie bei dem Kopf Ellipse2D anstatt Rectangle2D.
		 */
		this.bounding = new Ellipse2D.Double(x, y, width, height);
		this.vorgaenger = vorgaenger;
		movement = new Point2D.Double(x, y);
	}

	@Override
	public Rectangle2D getBounding() {
		return this.bounding.getBounds2D();
	}

	@Override
	public void actuate(double delta) {
		/*
		 * Wenn der vorangehende Körperteil eine neue Bewegung durchgeführt hat
		 * und dies nicht seine aktuelle Position ist, dann wird dieses
		 * Körperteil auf die alte Position des vorangehenden Körperteils
		 * bewegt.
		 */
		if ((bounding.x != vorgaenger.getMovement().x || bounding.y != vorgaenger
				.getMovement().y)
				&& (vorgaenger.getBounding().getX() != vorgaenger.getMovement().x || vorgaenger
						.getBounding().getY() != vorgaenger.getMovement().y)) {
			movement.x = bounding.x;
			movement.y = bounding.y;
			bounding.x = vorgaenger.getMovement().x;
			bounding.y = vorgaenger.getMovement().y;
			if (newDirection != direction) {
				direction = newDirection;
			}
			newDirection = vorgaenger.getDirection();
			/*
			 * Benachrichtige Observer
			 */
			setChanged();
			notifyObservers();
		}
	}

	@Override
	public void checkCollision(IActor actor) {
		/*
		 * Wenn wir mit einem Gegner kolidieren stirbt die Schlange. Jedoch kann
		 * der Gegner in dem Fall kein Baumstamm sein, da dieser sich nicht
		 * bewegt und somit nicht den Körper der Schlange berühren kann.
		 */
		if (bounding.intersects(actor.getBounding()) && actor instanceof IEnemy) {
			MainController.getInstance().gameOver();
		}
	}

	@Override
	public Double getMovement() {
		return movement;
	}

	@Override
	public Direction getDirection() {
		return direction;
	}
}