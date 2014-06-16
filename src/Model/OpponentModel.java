package Model;

import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Random;

import Controller.MainController;
import Model.Interface.IActor;
import Model.Interface.IEnemy;
import Model.Interface.IPlayerBone;
import Model.Interface.ISphere;

/**
 * Dies ist die Klasse für den Gegner, der sich auch dauerhaft bewegt. Er
 * implementiert das Interface IEnemy
 */
public class OpponentModel extends Observable implements IEnemy {
	/*
	 * Die Variablen
	 */
	private boolean opponentAlive = true;
	private Rectangle2D.Double bounding;
	private int nextJumpY = 0;
	private int nextJumpX = 0;
	private int maxJumpLength = 100;
	private int minJumpLength = 50;
	private double padding = 50;
	private double maxX;
	private double maxY;

	/**
	 * Konstruktor des Gegners
	 * 
	 * @param maxX
	 *            Die maximale x-Position, auf welcher sich der Gegner befinden
	 *            kann
	 * @param maxY
	 *            Die maximale y-Position, auf welcher sich der Gegner befinden
	 *            kann
	 * @param width
	 *            Die Breite des Gegners
	 * @param height
	 *            Die Höhe des Gegners
	 */
	public OpponentModel(int maxX, int maxY, int width, int height) {
		/*
		 * Erzeuge das Bounding
		 */
		this.bounding = new Rectangle2D.Double(0, 0, width, height);
		this.maxX = maxX;
		this.maxY = maxY;
		
		/*
		 * Kalkuliere die Startposition
		 */
		setStartPosition();
	}

	/**
	 * Diese Funktion kalkuliert die Startposition
	 */
	private void setStartPosition() {
		double x = 0;
		double y = 0;
		/*
		 * Berechne die Position solange bis es bei der Startposition keine
		 * Überschneidungen mit anderen grafischen Objekten gibt
		 */
		do {
			x = (Math.random() * ((maxX - padding) / 2) + padding);
			y = (Math.random() * ((maxY - padding) / 2) + padding);
		} while (MainController.getInstance().getActorAt(x, y,
				bounding.getWidth(), bounding.getHeight()) != null);
		bounding.x = x;
		bounding.y = y;
	}

	/**
	 * Diese Funktion bewegt den Gegner durch die Gegend
	 */
	public void moveOpponent() {
		double x = 0;
		double y = 0;
		IActor actor;
		/*
		 * Mache das hier wieder solange, bis die Bewegung nicht zur Kollision
		 * führt mit anderen grafischen Elementen!
		 */
		do {
			x = bounding.x + getNextMovement(true);
			y = bounding.y + getNextMovement(false);
			actor = MainController.getInstance().getActorAt(x, y,
					bounding.getWidth(), bounding.getHeight());
		} while (actor != null
				&& (!actor.equals(this) || !(actor instanceof IPlayerBone)));
		bounding.x = x;
		bounding.y = y;
	}

	/**
	 * Berechnet die nächste Bewegung kleiner schöner Algorithmus
	 * 
	 * @param movementOfX
	 *            Wahrheitswert welcher angibt, ob die x- oder y-Bewegung
	 *            berechnet werden soll
	 * @return Gitb die Pixel zurück, um welche sich der Gegner bewegen soll
	 */
	private int getNextMovement(boolean movementOfX) {
		int nextJump = 0;
		int nextDirectionValue = 0;
		/*
		 * Wenn True springen wir um X wenn nicht, dann um Y
		 */
		if (movementOfX) {
			nextJump = nextJumpX;
		} else {
			nextJump = nextJumpY;
		}
		/*
		 * Berechne den nächsten Sprung!
		 */
		if (nextJump == 0) {
			Random random = new Random();
			Boolean direction = random.nextBoolean();
			nextJump = (int) (Math.random() * (maxJumpLength - minJumpLength) + minJumpLength);
			if (direction) {
				nextJump *= -1;
			}
		}

		if (nextJump < 0) {
			nextJump += 1;
			nextDirectionValue = 1;
		} else if (nextJump > 0) {
			nextJump -= 1;
			nextDirectionValue = -1;
		}
		if (movementOfX) {
			nextJumpX = nextJump;
		} else {
			nextJumpY = nextJump;
		}
		return nextDirectionValue;
	}

	/**
	 * Diese Funktion prüft ob man sich über X oder Y hinbeweg bewegt Entweder
	 * über die maximalen oder minimalen Werte
	 */
	private void checkMovement() {
		if (bounding.x <= bounding.getWidth()) {
			bounding.x = bounding.getWidth();
			nextJumpX = nextJumpX * -1;
		}
		if (bounding.x >= maxX - bounding.getWidth()) {
			bounding.x = maxX - bounding.getWidth();
			nextJumpX = nextJumpX * -1;
		}
		if (bounding.y <= bounding.getHeight()) {
			bounding.y = bounding.getHeight();
			nextJumpY = nextJumpY * -1;
		}
		if (bounding.y >= maxY - bounding.getHeight()) {
			bounding.y = maxY - bounding.getHeight();
			nextJumpY = nextJumpY * -1;
		}
	}

	@Override
	public Rectangle2D getBounding() {
		return bounding;
	}

	@Override
	public void actuate(double delta) {
		if (opponentAlive) {
			/*
			 * Bewege den Gegner und benachrichtige den Observer zum
			 * Neuzeichnen.
			 */
			checkMovement();
			moveOpponent();
			setChanged();
			notifyObservers();
		}
	}

	@Override
	public void checkCollision(IActor actor) {
		/*
		 * Bei Kollision mit einem Schuss stirbt der Gegner! Des Weiteren wird
		 * der Schuss gelöscht um nicht den Speicherverbrauch so gering wie
		 * möglich zu halten. Performance!
		 */
		if (bounding.intersects(actor.getBounding())
				&& actor instanceof ISphere) {
			/*
			 * Benachrichtige Observer und entferne "tote" Elemente.
			 */
			((ISphere) actor).setGone(true);
			setOpponentAlive(false);
			setChanged();
			notifyObservers();
			MainController.getInstance().removeActor(this);
		}
	}

	/**
	 * Wird ausgeführt, wenn der Gegner stirbt
	 * 
	 * @param alive
	 *            Wahrheitswert welcher angibt, ob der Gegner am Leben ist oder
	 *            nicht
	 */
	public void setOpponentAlive(boolean alive) {
		this.opponentAlive = alive;
	}

	@Override
	public boolean isAlive() {
		return opponentAlive;
	}
}