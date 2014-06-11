package Model;

import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Random;

import Controller.MainController;
import Model.Interface.IActor;
import Model.Interface.IEnemy;
import Model.Interface.ISphere;

/**
 * Diese Klasse ist das Model für Barrieren. Ein Observable um nach
 * Observer-Pattern zu implementieren.
 */
public class BarrierModel extends Observable implements IActor, IEnemy {
	private Rectangle2D.Double bounding;
	private boolean barrierAlive = false;
	private boolean isAlive = true;
	private int maxX;
	private int maxY;

	/**
	 * Konstruktor der BarrierModel-Klasse
	 * 
	 * @param maxX
	 *            Die maximale x-Position, auf welcher sich die Barriere
	 *            befinden kann
	 * @param maxY
	 *            Die maximale y-Position, auf welcher sich die Barriere
	 *            befinden kann
	 * @param width
	 *            Die Breite der Barriere
	 * @param height
	 *            Die Höhe der Barriere
	 */
	public BarrierModel(int maxX, int maxY, int width, int height) {
		this.bounding = new Rectangle2D.Double(0, 0, width, height);
		this.maxX = maxX;
		this.maxY = maxY;
	}

	/**
	 * Funktion zum Positionieren der Barriere
	 */
	public void moveBarrier() {
		/*
		 * Neuen Random-Wert generieren, um den Baumstamm zu positionieren
		 */
		Random random = new Random();
		int x = 0;
		int y = 0;
		/*
		 * Verhindere, dass die Barriere auf einem bestehendem SpriteView
		 * erzeugt wird Prüfe also solange, bis keine "Kollision" stattfindet
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
	public void actuate(double delta) {
		/*
		 * Nur wenn boolesche Variable false ist
		 */
		if (!barrierAlive) {
			/*
			 * Setze sie auf true Positioniere den Baumstamm und benachrichtige
			 * den Observer.
			 */
			barrierAlive = true;
			moveBarrier();
			setChanged();
			notifyObservers();
		}
	}

	@Override
	public void checkCollision(IActor actor) {
		/*
		 * Wenn das Bounding mit dem Kopf der Schlange kollidiert sterben wir!
		 */
		if (bounding.intersects(actor.getBounding())
				&& actor instanceof SnakeHeadModel) {
			/*
			 * Führe die Funktion aus, wenn das Spiel vorbei ist.
			 */
			MainController.getInstance().gameOver();
			/*
			 * Jedoch kann der Baumstamm auch mit einer Kugel kollidieren In
			 * diesem Fall "stirbt" der Baumstamm und der Schuss, der verwendet
			 * wurde
			 */
		} else if (bounding.intersects(actor.getBounding())
				&& actor instanceof ISphere) {
			/*
			 * ISphere - Interface für die Bullets Entferne die Kugel und den
			 * Baumstamm! Benachrichtige die Observer zum Neunzeichnen der
			 * Oberfläche
			 */
			((ISphere) actor).setGone(true);
			isAlive = false;
			setChanged();
			notifyObservers();
			MainController.getInstance().removeActor(this);
		}
	}

	@Override
	public Rectangle2D getBounding() {
		return bounding;
	}

	@Override
	public boolean isAlive() {
		return isAlive;
	}
}