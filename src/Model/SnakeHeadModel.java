package Model;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;

import Controller.MainController;
import Controller.OptionsController;
import Model.Interface.Difficulty;
import Model.Interface.Direction;
import Model.Interface.IActor;
import Model.Interface.IElement;
import Model.Interface.IPlayerBone;
import Model.Interface.ISphere;

/**
 * Diese Klasse ist das Model für den Kopfteil der Schlange.
 */
public class SnakeHeadModel extends Observable implements IPlayerBone {
	/*
	 * Die Variablen
	 */
	private Point2D.Double movement;
	private double lastMove = 0;
	private Ellipse2D.Double bounding;
	private Direction newDirection = Direction.NONE;
	private Direction direction = Direction.RIGHT;
	private IPlayerBone last;
	private int maxX;
	private int maxY;

	/**
	 * Der Konstruktor der Klasse.
	 * 
	 * @param maxX
	 *            Die maximale x-Position, auf welcher sich die Schlange
	 *            befinden kann
	 * @param maxY
	 *            Die maximale y-Position, auf welcher sich die Schlange
	 *            befinden kann
	 * @param x
	 *            x-Position des Kopfes
	 * @param y
	 *            y-Position des Kopfes
	 * @param width
	 *            Die Breite des Kopfes der Schlange
	 * @param height
	 *            Die Höhe des Kopfes der Schlange
	 */
	public SnakeHeadModel(int maxX, int maxY, double x, double y, int width,
			int height) {
		/*
		 * Setze das Bounding In dem Fall verwenden wir Ellipse2D, da die
		 * Schlangenteile Kreise sind.
		 */
		this.bounding = new Ellipse2D.Double(x, y, width, height);
		movement = new Point2D.Double(x, y);
		this.maxX = maxX;
		this.maxY = maxY;
	}

	@Override
	public void actuate(double delta) {
		lastMove += delta;
		/*
		 * Erzeuge an Hand der Schwierigkeit die Geschwindigkeit der Schlange
		 */
		Difficulty difficuty = Difficulty.fromString(OptionsController
				.getInstance().getOption("difficulty"));
		int speed = difficuty == Difficulty.SIMPLE ? 110
				: difficuty == Difficulty.MEDIUM ? 80 : 50;
		/*
		 * Bewege die Schlange / den Kopf und benachrichtige den Observer
		 * (SnakeHeadModel)
		 */
		if (lastMove > speed) {
			lastMove = 0;
			move();
			setChanged();
			notifyObservers();
		}
	}

	/**
	 * Diese Funktion berechnet die Bewegung der Schlange Je nach aktueller
	 * Richtung!
	 */
	private void move() {
		/*
		 * Wenn die neue Richtung != NONE ist, wird die aktuelle Richtung auf
		 * die neue Richtung gesetzt und die neue Richtung wieder auf NONE!
		 */
		if (newDirection != Direction.NONE) {
			direction = newDirection;
			newDirection = Direction.NONE;
		}

		/*
		 * Die Position vor der Bewegung des Kopfes zwischenspeichern.
		 */
		movement.x = bounding.x;
		movement.y = bounding.y;

		/*
		 * Je nach Richtung wird die Schlange bewegt. Wenn die Schlange über das
		 * Bounding hinausschießt, laufen wir durch die Wand! Dies ist zwar
		 * nicht klassisch Snake, doch ist es mit den Gegner usw. besser, da das
		 * Spiel sonst zu schwierig sein würde.
		 */
		switch (direction) {
		case DOWN:
			bounding.y = (bounding.y + bounding.getHeight()) % maxY;
			break;
		case UP:
			bounding.y -= bounding.getHeight();
			if (bounding.y < 0) {
				bounding.y += maxY;
			}
			break;
		case RIGHT:
			bounding.x = (bounding.x + bounding.getWidth()) % maxX;

			break;
		case LEFT:
			bounding.x -= bounding.getWidth();
			if (bounding.x < 0) {
				bounding.x += maxX;
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void checkCollision(IActor actor) {
		/*
		 * Wenn der Kopf nicht mit Objekten vom Typen IElement Kolidiert oder
		 * von Typ ISpehere (Bullet / Schuss) wird das Spiel beendet (bedeutet
		 * tot)
		 */
		if (bounding.intersects(actor.getBounding())
				&& !(actor instanceof IElement || actor instanceof ISphere)) {
			/*
			 * Führe die GameOver Funktion aus
			 */
			MainController.getInstance().gameOver();
		}
	}

	/**
	 * Diese Funktion verlängert die Schlange, wenn ein Apfel eingesammelt wird.
	 */
	public void increaseLength() {
		/*
		 * setze die Werte für das aktuell letzte Körperteil (X, Y)
		 */
		double x = last.getBounding().getX();
		double y = last.getBounding().getY();
		/*
		 * Je nach Richtung wird die x- oder y-Position des letzten Körperteils
		 * bestimmt
		 */
		switch (last.getDirection()) {
		case DOWN:
			y -= bounding.getHeight();
			break;
		case UP:
			y += bounding.getHeight();
			break;
		case RIGHT:
			x -= bounding.getWidth();
			break;
		case LEFT:
			x += bounding.getWidth();
			break;
		default:
			break;
		}
		/*
		 * Erzeuge neues letztes Teil der Schlange!
		 */
		last = MainController.getInstance().createSnakeTail((int) x, (int) y,
				last);
	}

	@Override
	public Rectangle2D getBounding() {
		return this.bounding.getBounds2D();
	}

	@Override
	public Point2D.Double getMovement() {
		return movement;
	}

	/**
	 * Setze das letzte Teil der Schlange!
	 * 
	 * @param last
	 *            Das Objekt des letzten Köperteils der Schlange.
	 */
	public void setLast(IPlayerBone last) {
		this.last = last;
	}

	/**
	 * Funktion zum Rotieren der Schlange
	 * 
	 * @param direction
	 *            Die neue Richtung, in welche die Schlange laufen soll.
	 */
	public void rotateSnake(Direction direction) {
		/*
		 * Je nach aktueller Richtung rotieren wir die Schlange und setzen die
		 * neue Richtung!
		 */
		if ((this.direction == Direction.RIGHT && (direction == Direction.UP || direction == Direction.DOWN))
				|| (this.direction == Direction.LEFT && (direction == Direction.UP || direction == Direction.DOWN))
				|| (this.direction == Direction.UP && (direction == Direction.RIGHT || direction == Direction.LEFT))
				|| (this.direction == Direction.DOWN && (direction == Direction.LEFT || direction == Direction.RIGHT))) {
			newDirection = direction;
		}
	}

	@Override
	public Direction getDirection() {
		return this.direction;
	}
}