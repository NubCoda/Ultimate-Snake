package Model;

import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Random;

import Controller.MainController;
import Model.Interface.IActor;
import Model.Interface.IElement;

/**
 * Diese Klasse ist das Model für den Apfel. Ein Observable um nach
 * Observer-Pattern zu implementieren.
 */
public class AppleModel extends Observable implements IElement {
	/*
	 * Variablen
	 */
	private boolean appleAlive = false;
	private Rectangle2D.Double bounding;
	private int maxX;
	private int maxY;

	/**
	 * Konstruktor des AppleModels
	 * 
	 * @param maxX
	 *            Die maximale x-Position, auf welcher sich der Apfel befinden
	 *            kann
	 * @param maxY
	 *            Die maximale y-Position, auf welcher sich der Apfel befinden
	 *            kann
	 * @param width
	 *            Die Breite des Apfels
	 * @param height
	 *            Die Höhe des Apfels
	 */
	public AppleModel(int maxX, int maxY, int width, int height) {
		/*
		 * Erzeuge das Bounding
		 */
		this.bounding = new Rectangle2D.Double(0, 0, width, height);
		this.maxX = maxX;
		this.maxY = maxY;
	}

	/**
	 * Funktion zum neusetzen des Apfels
	 */
	public void moveApple() {
		/*
		 * Neuen Random-Wert generieren, um den Apfel zu positionieren
		 */
		Random random = new Random();
		int x = 0;
		int y = 0;
		/*
		 * Verhindere, dass der Apfel auf einem bestehenden SpriteView erzeugt
		 * wird Prüfe also solange, bis keine "Kollision" passiert
		 */
		do {
			x = random.nextInt((int) (maxX - bounding.getWidth()));
			y = random.nextInt((int) (maxY - bounding.getHeight()));
		} while (MainController.getInstance().getActorAt(
				x - (x % bounding.getWidth()), y - (y % bounding.getHeight()),
				bounding.getWidth(), bounding.getHeight()) != null);
		bounding.x = (int) (x - (x % bounding.getWidth()));
		bounding.y = (int) (y - (y % bounding.getHeight()));

	}

	@Override
	public Rectangle2D getBounding() {
		return bounding;
	}

	@Override
	public void actuate(double delta) {
		/*
		 * Nur wenn die boolesche Variable false ist
		 */
		if (!appleAlive) {
			/*
			 * Setze Variable auf True Berechne die Position und benachrichtige
			 * den Observer (AppleView)
			 */
			appleAlive = true;
			moveApple();
			setChanged();
			notifyObservers();
		}
	}

	@Override
	public void checkCollision(IActor actor) {
		/*
		 * Wenn das Bounding mit dem Kopf der Schlange kollidiert Wird der
		 * Punktestand erhöht und die Schlange verlängert
		 */
		if (bounding.intersects(actor.getBounding())
				&& actor instanceof SnakeHeadModel) {
			/*
			 * Setze die Variable auf false, damit die Position des Apfels
			 * neuberechnet werden kann
			 */
			appleAlive = false;
			MainController.getInstance().raiseScore();
		}
	}
}